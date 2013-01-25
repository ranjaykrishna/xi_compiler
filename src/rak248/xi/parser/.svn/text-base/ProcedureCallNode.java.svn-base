package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.CallIR;
import rak248.xi.ir.EseqIR;
import rak248.xi.ir.ExpIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.Mangler;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;


public class ProcedureCallNode extends StatementNode {

	private String id;
	private ArrayList<ExpressionNode> arg;
	private String methodClass;
	
	
	public ProcedureCallNode(String id, ArrayList<ExpressionNode> arg, Position position, Position position2) {
		addChild(new VarNode(id, position, position2));
		for (ExpressionNode e: arg){
			addChild(e);
		}
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("procedure call");
		this.id = id;
		this.arg = arg;
	}
	
	public ProcedureCallNode(String value, ArrayList<ExpressionNode> args,
			Position position, int argsright) {
		// TODO Auto-generated constructor stub
	}

	public Type typeCheck(SymbolTable table) throws UndeclaredIdentifierException, TypeError {
		return typeCheck(table, null);
	}
	
	public Type typeCheck(SymbolTable table, String methodClassName) throws TypeError, UndeclaredIdentifierException {
		setSymTable(table);
		ArrayList<VisualizableTreeNode> cList = getChildren();

		Type procType = null;
		if(methodClassName != null) {
			procType = table.lookUpMethod(methodClassName, id);
			methodClass = methodClassName;
		} else {
			methodClassName = table.getCurrentClass();
			if(methodClassName != null) {
				methodClass = methodClassName;
				procType = table.lookUpMethod(methodClassName, id);
			}
			if(procType == null) {
				procType = table.lookup((VarNode) cList.get(0));
			}
		}
		
		if(procType == null) {
			String message = "Procedure '" + id + "' used without declaration. ";
			message += "Occurred at position: " + position();
			throw new TypeError(message);
		}
		
		Type[] parameters = procType.getParameters();
		Type[] returnType = procType.getReturnTypes();
		if((cList.size()-1) != parameters.length) {
			String message = "Type error at procedure call: '" + id + "'.  Incorrect amount";
			message = message + " of parameters.  Occured at position: ";
			message = message + ((SyntaxNode)cList.get(0)).position();
			throw new TypeError(message);
		}
		else {
			for(int i = 1; i < cList.size(); i ++) {
				
				if(!((ExpressionNode) cList.get(i)).typeCheck(table).equals(parameters[i-1])) {
					String message = "Type error at procedure call: '" + id + "'.  Incorrect ";
					message = message + "paramater type passed.  Occured at position: ";
					message = message + ((SyntaxNode)cList.get(0)).position();
					throw new TypeError(message);
				}
			}
		}
		if(returnType.length != 0) {
			String message = "Function call occured when procedure call expected.";
			message = message + " Occured with procedure call: '" + id +"'";
			message = message + ". Occured at position: ";
			message = message + ((SyntaxNode)cList.get(0)).position();
			throw new TypeError(message);
		}
		return new Type(typeEnum.UNIT);
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
		if(foundID) {
			getChildren().add(1, (VisualizableTreeNode) new ThisNode(position()));
			ExpressionNode exp = new ExpressionNode();
			exp.addChild(new ThisNode(position()));
			arg.add(0, exp);
		}
		if(arg.size() == 0) {
			CallIR call = new CallIR();
			Type t = getSymTable().lookup(new VarNode(id,new Location("",0,0,0,0),new Location("",0,0,0,0)));
			String mangled = Mangler.mangle(id, t);
			call.addChild(new NameIR(mangled));
			return new ExpIR(call);
		}
		String[] keyList = new String[arg.size()];
		String[] valList = new String[arg.size()];
		// create list of indices
		int[] indices = new int[arg.size()];
		for(int i = 0; i < arg.size(); i ++) {
			ExpressionNode node = arg.get(i);
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
		// add function name as child
		Type t = getSymTable().lookup(new VarNode(id,new Location("",0,0,0,0),new Location("",0,0,0,0)));
		String mangled = Mangler.mangle(id, t);
		call.addChild(new NameIR(mangled));
		/* 
		 * For each of the children, check to see if it was a variable.
		 * If it was, you want to create a TempIR with the same key and value
		 * as the original parameter.
		 * Otherwise, translate the node as normal.
		 */
		for(int i = 0; i < arg.size(); i ++) {
			if(indices[i] == 1) {
				// nothing new is added to the symboltable (it's the same temp)
				// because getFreshName() is never called.
				call.addChild(new TempIR(keyList[i], valList[i]));
			} else
				call.addChild(arg.get(i).translate(map));
		}
		//EseqIR eseq = (EseqIR) call.getChildren().get(call.getChildren().size()-1);
		//LabelIR secondToLast = (LabelIR) eseq.getChildren().get(eseq.getChildren().size()-2);
		//SyntaxIR last = (SyntaxIR) eseq.getChildren().get(eseq.getChildren().size()-1);
		return new ExpIR(call);
	}

	public String getId() {
		return id;
	}
	
	public String getMethodClass() {
		return methodClass;
	}
	
}
