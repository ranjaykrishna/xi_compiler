package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.CallIR;
import rak248.xi.ir.ExpIR;
import rak248.xi.ir.ExpressionIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.Mangler;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;


public class FunctionCallNode extends SyntaxNode{
	
	String id;
	ArrayList<ExpressionNode> args;
	Boolean returns = false;
	private int retsize;
	private NameIR callLabel;
	private String methodClass;
	
	public FunctionCallNode(String id, ArrayList<ExpressionNode> args, Position position, Position position2) {
		this.id = id;
		this.args = args;
		for(ExpressionNode child : args) {
			addChild(child);
		}
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("Function call: " + id);
		setCallLabel(new NameIR(id));
	}
	
	public Type typeCheck(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		return typeCheck(table, null);
	}
	
	public Type typeCheck(SymbolTable table, String methodClassName) throws TypeError, UndeclaredIdentifierException {
		setSymTable(table);
		ArrayList<VisualizableTreeNode> children = getChildren();
		VarNode newVar = new VarNode(id, position(), position());
		Type origType = null;
		if(methodClassName != null) {
			methodClass = methodClassName;
			origType = table.lookUpMethod(methodClassName, id);
		} else {
			methodClassName = table.getCurrentClass();
			if(methodClassName != null) {
				methodClass = methodClassName;
				origType = table.lookUpMethod(methodClassName, id);
			}
			if(origType == null) {
				origType = table.lookup(newVar);
			}
		}
		if(origType == null) {
			String message = "Function '" + id + "' used without declaration. ";
			message += "Occurred at position: " + position();
			throw new TypeError(message);
		}
		if(!origType.isFunction()) {
			String message = "Function expected where '" + origType +"' was used. ";
			message += "Occurred at position: " + position();
			throw new TypeError(message);
		}
		Type[] params = origType.getParameters();
		Type[] returnTypes = origType.getReturnTypes();
		
		if(returnTypes.length > 0) {
			returns = true;
		}
		
		
		if(params.length != args.size()) {
			String message = "Incorrect number of arguments used when calling function";
			message += " '" + id + "'.  Occurred at position: " + position();
			throw new TypeError(message);
		}
		for(int i = 0; i < params.length; i ++) {
			Type temp = args.get(i).typeCheck(table);
			if(!params[i].equals(temp)) {
				String message = "Incorrect type when calling method '" + id + "'.";
				message += params[i] + " was expected and " + args.get(i) + " was used.";
				message += "Occurred at position: " + position();
				throw new TypeError(message);
			}
		}
		retsize = returnTypes.length;
		if(returnTypes.length == 1) {
			setType(returnTypes[0]);
			return returnTypes[0];
			
		}
		setType(new Type(typeEnum.TUPLE, returnTypes));
		return new Type(typeEnum.TUPLE, returnTypes);
	}
	
	
	public SyntaxIR translate(HashMap<String, String> map){
		// create String arrays for the list of keys and vals
		String child = getSymTable().getCurrentClass();
		String parent = SymbolTable.getInheritanceTree().get(child);
		boolean foundID = false;
		if(getSymTable().getMethods().get(child) != null) {
			if(getSymTable().getMethods().get(child).keySet().contains(id))
				foundID = true;
		}
		while(parent != null) {
			if(getSymTable().getMethods().get(parent).keySet().contains(id)) {
				foundID = true;
			}
			child = parent;
			parent = SymbolTable.getInheritanceTree().get(child);
		}
		ExpressionNode exp = null;
		if(foundID) {
			//getChildren().add(1, (VisualizableTreeNode) new ThisNode(position()));
			exp = new ExpressionNode();
			exp.addChild(new ThisNode(position()));
		}
		if(args.size() == 0) {
			CallIR call = new CallIR();
			Type t = getSymTable().lookup(new VarNode(id,new Location("",0,0,0,0),new Location("",0,0,0,0)));
			String mangled = Mangler.mangle(id, t);
			call.addChild(new NameIR(mangled));
			call.setretsize(retsize);
			if(foundID) {
				SyntaxIR trans = exp.translate(map);
				call.addChild(trans);
			}
			return call;
		}
		String[] keyList = new String[args.size()];
		String[] valList = new String[args.size()];
		// create list of indices
		int[] indices = new int[args.size()];
		for(int i = 0; i < args.size(); i ++) {
			ExpressionNode node = args.get(i);
			/*
			 * Try to lookup the variable by looking up its label.
			 * If it exists, add its key and value and index in args
			 * to the appropriate arrays
			 */
			if(node.getChildren().get(0) instanceof VarNode) {
				String temp = node.getChildren().get(0).label();
				if(map != null) {  // MIGHT NEED TO ADD THIS BACK!!!!!!!!
					if(map.get(temp).startsWith("_args")){
						temp = map.get(temp);
					}
				}
				valList[i] = getSymTable().lookUp(temp);
				keyList[i] = temp;
				indices[i] = 1;
			} else {
				valList[i] = null;
				keyList[i] = null;
				indices[i] = 0;
			}
		}
		// create new CallIR
		CallIR call = new CallIR();
		call.setretsize(retsize);
		// add function name as child
		Type t = getSymTable().lookup(new VarNode(id,new Location("",0,0,0,0),new Location("",0,0,0,0)));
		String mangled = Mangler.mangle(id, t);
		callLabel = new NameIR(mangled);
		call.addChild(callLabel);
		/* 
		 * For each of the children, check to see if it was a variable.
		 * If it was, you want to create a TempIR with the same key and value
		 * as the original parameter.
		 * Otherwise, translate the node as normal.
		 */
		if(foundID)
			call.addChild(exp.translate(map));
		for(int i = 0; i < args.size(); i ++) {
			if(indices[i] == 1) {
				// nothing new is added to the symbol table (it's the same temp)
				// because getFreshName() is never called.
				call.addChild(new TempIR(keyList[i], valList[i]));
			} else
				call.addChild(args.get(i).translate(map));
		}
		if(returns) {
			return call;
		}
		return new ExpIR(call);
	}

	public void setCallLabel(NameIR nameIR) {
		this.callLabel = nameIR;
	}

	public NameIR getCallLabel() {
		return callLabel;
	}
	
	public String getId() {
		return id;
	}
	
	public String getMethodClass() {
		return methodClass;
	}
}
