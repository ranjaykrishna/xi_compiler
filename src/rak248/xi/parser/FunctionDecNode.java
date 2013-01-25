package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.Mangler;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.ReturnIR;
import rak248.xi.ir.SeqIR;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;

public class FunctionDecNode extends SyntaxNode{
	LinkedHashMap<String,Type> parameters;
	ArrayList<Type> returnTypes;
	ArrayList<Type> arrayParams;
	ReturnNode ro;
	private NameIR pointer;
	private String id;
	private String mangled;

	//function ::= IDENTIFIER:id parameters_paren:p return_type:r OPEN_BRACE statements:s return_opt:ro CLOSE_BRACE
	//{: RESULT = new FunctionDecNode(id,p,r,s,ro); :};
	public FunctionDecNode(String id, LinkedHashMap<String,Type> p, ArrayList<Type> r, StatementsNode s,ReturnNode ro, Position position, Position position2) {
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel(id);
		this.id = id;
		parameters = p;
		returnTypes = r;
		ArrayList<Type> params = new ArrayList<Type>(p.size());
		for (String v : p.keySet()){
			params.add(p.get(v));
		}
		arrayParams = params;
		setType(new Type(Type.typeEnum.FUNCTION,params.toArray(new Type[p.size()]),
			                               r.toArray(new Type[r.size()])));
		if(ro != null) {
			s.addReturn(ro, ro.position(), ro.position());
		}
		addChild(s);
		this.ro = ro;
		pointer = null;
	}
	
	public Type typeCheck(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		// need to check that r == ro
		// check that s is type unit
		SymbolTable childTable = new SymbolTable(table);
		childTable.setCurrentFunction(getLabel());
		ArrayList<VisualizableTreeNode> children = getChildren();
		StatementsNode s = (StatementsNode) children.get(0);
		for(String id : parameters.keySet()) {
			childTable.add(new VarNode(id, position(), position()), parameters.get(id));
		}
		Type temp = s.typeCheck(childTable);
		if(!(temp.isUnit() || temp.isVoid())) {
			String message = "TypeError: Function body must be of type unit or void.";
			message += " Is currently type " + temp;
			message += " Occurred at: " + s.position();
			throw new TypeError(message);
		}
		if(returnTypes.size() > 0 && temp.isUnit()) {
			String message = " Type error: need to return something here. ";
			message += " Occurred at: " + s.position();
			throw new TypeError(message);
		}
		setSymTable(childTable);
		print = true;
		return new Type(typeEnum.UNIT);
	}
	
	public String getMangled() {
		return mangled;
	}
	
	@Override
	public SyntaxIR translate(HashMap<String, String> map){
		
		map = new HashMap<String, String>();
		map.putAll(SymbolTable.globals);
		SeqIR seq  = new SeqIR(": "+ label());
		mangled = Mangler.mangle(label(),returnTypes,arrayParams);
		LabelIR label = new LabelIR(mangled);
		label.setParams(arrayParams);
		label.setRets(returnTypes);
		pointer = new NameIR(id);
		seq.addChild(label);
		for(int i = 0; i < parameters.keySet().size(); i ++){
			TempIR t = new TempIR("_args" + i, getSymTable().getFreshNameSpecial("_args" + i, "_args" + i));
			//seq.addChild(t);
			map.put((String) parameters.keySet().toArray()[i], "_args" + i);
		}
		for (VisualizableTreeNode node: getChildren()){
			SyntaxIR ir = ((SyntaxNode) node).translate(map);
			seq.addChild(ir);
		}
		if (!hasReturn(this)){
			seq.addChild(new ReturnIR());
		}
		return seq;
	}
	
public SyntaxIR translateClass(HashMap<TempIR, Integer> fieldOffset, TempIR thisNode, HashMap<String, String> map, String name){
		SeqIR seq  = new SeqIR(": "+ label());
		mangled = Mangler.mangle(label(),returnTypes,arrayParams);
		LabelIR label = new LabelIR(mangled);
		label.setParams(arrayParams);
		label.setRets(returnTypes);
		pointer = new NameIR(id);
		Type classType = new Type(typeEnum.ABSTRACT, name);
		//parameters.put("this", classType);
		seq.addChild(label);
		thisNode = new TempIR("thisNode", getSymTable().getFreshNameSpecial("_args"+0, "_args"+0));
		for(int i = 0; i < parameters.keySet().size(); i ++){
			TempIR t = new TempIR("_args" + (i+1), getSymTable().getFreshNameSpecial("_args" + (i+1), "_args" + (i+1)));
			//seq.addChild(t);
			map.put((String) parameters.keySet().toArray()[i], "_args" + (i+1));
		}
		for (VisualizableTreeNode node: getChildren()){
			SyntaxIR ir = ((SyntaxNode) node).translate(map);
			ir.replaceFields(fieldOffset, thisNode);
			seq.addChild(ir);
		}
		if (!hasReturn(this)){
			seq.addChild(new ReturnIR());
		}

		return seq;
	}

	public NameIR getPointer() {
		return pointer;
	}
}
