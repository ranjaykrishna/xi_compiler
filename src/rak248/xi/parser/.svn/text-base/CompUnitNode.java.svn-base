package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;

import rak248.util.VisualizableIRNode;
import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.CompUnitIR;
import rak248.xi.ir.SeqIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;

public class CompUnitNode extends SyntaxNode {

	private UseNode u;
	private FunctionsNode fs;
	private ArrayList<SyntaxNode> globals;
	private ArrayList<ClassNode> classes;
	private ArrayList<String> listOfDVS;
	private ArrayList<String> sizeSetup;
	
	public CompUnitNode(UseNode u2, BodyNode fs2, Position position,
			Position position2) {
		super();
		listOfDVS = new ArrayList<String>();
		sizeSetup = new ArrayList<String>();
		classes = new ArrayList<ClassNode>();
		if(position == null || position2 == null) {
			setPosition(null);
		} else {
			Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
			setPosition(newPos);
		}
		super.addChild(u2);
		this.u = u2;
		//We need to put the functions in a FunctionsNode and add the classes as direct children
		ArrayList<VisualizableTreeNode> children = fs2.getChildren();
		FunctionsNode fs = null;
		globals = new ArrayList<SyntaxNode>();
		for(VisualizableTreeNode child : children){
			if(child instanceof ClassNode){
				classes.add((ClassNode) child);
				super.addChild((ClassNode)child);
			}else if(child instanceof FunctionsNode){
				//The functionDecNodes are all individuall wrapped in FunctionsNodes
				FunctionsNode funcs = (FunctionsNode)child;
				for(VisualizableTreeNode subFunc : funcs.getChildren()){
					FunctionDecNode f = (FunctionDecNode)subFunc;
					if(fs!=null)
						fs.addFunction(f);
					else
						fs = new FunctionsNode(f, f.position(), f.position());
				}
			}else if(child instanceof DeclarationNode){
				
				globals.add((DeclarationNode)child);
			}else if(child instanceof AssignmentNode){
				globals.add((AssignmentNode) child);
			}
		}
		//Add the functionsNode as a child now
		if(fs != null)
			super.addChild(fs);
		this.fs = fs;
		setLabel("CompUnit");
	}

	public String toString(){
		return "This is a comp node";	
	}
	
	public Type typeCheck(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		if(!u.typeCheck(table).isUnit()) {
			String message = "TypeError: Use node does not type check. Fix it.";
			message = message + " Occured at position: " + u.position();
			throw new TypeError(message);
		}
		for(ClassNode cNode : classes) {
			if(!cNode.typeCheck(table).isUnit()) {
				String message = "TypeError: The list of classes does not type check. Fix it.";
				message = message + " Occured at position: " + fs.position();
				throw new TypeError(message);
			}
		}
		for(SyntaxNode global : globals) {
			if(!global.typeCheck(table).isUnit()) {
				String message = "TypeError: The list of classes does not type check. Fix it.";
				message = message + " Occured at position: " + fs.position();
				throw new TypeError(message);
			}
		}
		if(fs == null) {
			setSymTable(table);
			return new Type(typeEnum.UNIT);
		}
		fs.setTypeCheckingByCompUnit(true);
		if(!fs.typeCheck(table).isUnit()) {
			String message = "TypeError: The list of functions does not type check. Fix it.";
			message = message + " Occured at position: " + fs.position();
			throw new TypeError(message);
		}
		setSymTable(table);
		return new Type(typeEnum.UNIT);
	}
	
	public SyntaxIR translate(HashMap<String, String> map){
		CompUnitIR seq = new CompUnitIR();
		LinkedList<ClassNode> queue = new LinkedList<ClassNode>();
		for(SyntaxNode global : globals) {
			seq.addChild(global.translate(map));
		}
		SymbolTable.globals.putAll(map);
		for(VisualizableTreeNode node : getChildren()) {
			if(node instanceof ClassNode) {
				queue.add((ClassNode) node);
			}
		}
		while(queue.size() > 0) {
			ClassNode node = queue.pop();
			if(node.canBeTranslated()) {
				SyntaxIR us = ((ClassNode) node).translate(map);
				seq.addChild(us);
				listOfDVS.add(((ClassNode) node).getDVASMSetup());
				sizeSetup.add(node.getSizeSetup());
			} else {
				queue.addLast(node);
			}
		} 
		for (VisualizableTreeNode node: getChildren()){
			if (node instanceof FunctionsNode){
				SyntaxIR us = ((FunctionsNode) node).translate(map);
				seq.addChild(us);
			}
		}
		seq.setListOfDVS(listOfDVS);
		seq.setSizeSetup(sizeSetup);
		return seq;
	}

	public void setListOfDVS(ArrayList<String> listOfDVS) {
		this.listOfDVS = listOfDVS;
	}

	public ArrayList<String> getListOfDVS() {
		return listOfDVS;
	}
	
	public void setSizeSetup(ArrayList<String> sizeSetup) {
		this.sizeSetup = sizeSetup;
	}
	
	public ArrayList<String> getSizeSetup() {
		return sizeSetup;
	}
	
}
