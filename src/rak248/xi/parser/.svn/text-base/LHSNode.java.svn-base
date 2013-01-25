package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.DeclaredIdentifierException;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;

public class LHSNode extends SyntaxNode{
	private String id;
	private Type t;
	private boolean isArrayLookup = false;
	
	public LHSNode(Position position, Position position2){
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("underscore");
	}
	
	public LHSNode(String id, Position position, Position position2){
		this.id = id;
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("id:"+id);
	}
	
	public LHSNode(String id, Type t, Position position, Position position2){
		this.id = id;
		this.setType(t);
		this.t = t;
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("id: "+id+":"+t);
	}
	
	public LHSNode(ArrayLookUpNode a, Position position, Position position2){
		isArrayLookup = true;
		addChild(a);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("id: "+id+":"+t);
	}
	
	public LHSNode(AccessorNode a, Position position2) {
		isArrayLookup = false;
		addChild(a);
		Position newPos = new Location(position2.unit(),position2.lineStart(),position2.lineEnd(),position2.columnStart(),position2.columnEnd());
		setPosition(newPos);
		id = a.getLabel();
		setLabel("id: "+id);
	}

	public String getId(){
		return id;
	}
	
	public Type getType(){
		return t;
	}
	
	public Type typeCheck(SymbolTable table) throws UndeclaredIdentifierException, TypeError {
		setSymTable(table);
		// if it's an underscore, throw the stuff away
		ArrayList<VisualizableTreeNode> children = getChildren();
		if(children.size() == 1 && children.get(0) instanceof ArrayLookUpNode) {
			return ((ArrayLookUpNode) children.get(0)).typeCheck(table);
		}
		if(children.size() == 1 && children.get(0) instanceof AccessorNode) {
			return ((AccessorNode) children.get(0)).typeCheck(table);
		}
		if(getLabel() == "underscore") {
			return new Type(typeEnum.UNDERSCORE);
		}
		VarNode vNode = new VarNode(id, position(), position());
		Type temp = null;
		try{
			temp = table.lookup(vNode);
			if(t != null) {
				if (table.isInCurrentScope(vNode)){
					String message = "Variable '" + id + "' has already been declared.";
					throw new DeclaredIdentifierException(message,position());
				}
			}
		}
		// if it's not in scope yet...
		catch (UndeclaredIdentifierException e) {
			// add it to the table
			if(t == null) {
				String message = "Variable '" + id + "' was not declared in this scope.";
				message = message + " Occured at position: " + this.position();
				throw new TypeError(message);
			}
			table.add(vNode, t);
			return t;
		}
		// return the type of what is being assigned
		return temp;
	}
	
	// still need to do stuff with lists I think
	public SyntaxIR translate(HashMap<String, String> map) {
		if(isArrayLookup) {
			return ((ArrayLookUpNode) getChildren().get(0)).translate(map);
		}
		if(getLabel().equals("underscore")) {
			return new TempIR("underscore","underscore");
		}
		if (t == null){ //t is the type associated with the id if there is no type it is not a declaration
			if(getChildren().size() == 1 && getChildren().get(0) instanceof AccessorNode) {
				return ((AccessorNode) getChildren().get(0)).translate(map);
			}
			if(map == null) {
				return new TempIR(id,getSymTable().lookUp(id));
			}
			try{
				return new TempIR(map.get(id), getSymTable().lookUp(map.get(id)));
			} catch(Exception e) {
				return new TempIR(id,getSymTable().lookUp(id));
			}
		}
		else{
			if(map == null) {
				return new TempIR(id,getSymTable().getFreshName(id));
			}
			String tempid = getSymTable().getFreshName(id);
			map.put(id, tempid);
			return new TempIR(id,tempid);
		}
	}
}
