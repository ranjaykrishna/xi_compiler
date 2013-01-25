package rak248.xi;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.AbstractSyntaxNode;
import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.ir.StatementIR;
import rak248.xi.parser.ConditionNode;
import rak248.xi.parser.ReturnNode;
import rak248.xi.parser.StatementNode;
import rak248.xi.parser.WhileNode;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;


public class SyntaxNode implements AbstractSyntaxNode{
	Position position;
	private String label;
	private Type type;
	protected SymbolTable symTable;
	public boolean print = false;
	
	private ArrayList<VisualizableTreeNode> children;
	
	public SyntaxNode() {
		setChildren(new ArrayList<VisualizableTreeNode>());
		setLabel("DEADBEEF");
		setType(null);
	}

	@Override
	public String label() {
		return getLabel();
	}
	
	@Override
	public Iterable<VisualizableTreeNode> children() {
		
		return getChildren();
	}
	
	@Override
	public Position position() {
		return position;
	}
	
	public void addChild(SyntaxNode node){
		getChildren().add(node);
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}
	
	public Type typeCheck(SymbolTable s) throws TypeError, UndeclaredIdentifierException {
		System.err.println("Something is calling SyntaxNode's typeCheck method at pos:"+this.position());
		return null;
	}

	public ArrayList<VisualizableTreeNode> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<VisualizableTreeNode> children) {
		this.children = children;
	}

	public SymbolTable getSymTable() {
		return symTable;
	}

	public void setSymTable(SymbolTable symTable) {
		this.symTable = symTable;
	}
	
	public void setPosition(Position pos){
		this.position = pos;
	}
	
	public String toString(){
		String ret  = label ;
		return ret;
	}
	
	public boolean equals(Object other){
		if (other instanceof SyntaxNode){
			SyntaxNode oo = (SyntaxNode) other;
			return label.equals(oo.getLabel()) && type.equals(oo.getType());
		}
		return false;
	}
	
	public SyntaxIR translate(){
		System.err.println("Something is calling SyntaxNode's translate method");
		System.out.println(this.getClass());
		System.out.println(1/0);
		return null;
	}
	
	public SyntaxIR translate(HashMap<String, String> map){
		System.err.println("Something is calling SyntaxNode's translate method");
		System.out.println(this.getClass());
		System.out.println(1/0);
		return null;
	}

	public boolean getPrint() {
		return print;
	}
	
	public boolean hasReturn(SyntaxNode s){
		if(s instanceof WhileNode) {
			return false;
		} else if(s instanceof ConditionNode) {
			ConditionNode cNode = (ConditionNode) s;
			if(((ArrayList<VisualizableTreeNode>) cNode.children()).size() == 2) {
				return false;
			} else {
				return hasReturn((StatementNode) ((ArrayList<VisualizableTreeNode>) cNode.children()).get(1)) && hasReturn((StatementNode) ((ArrayList<VisualizableTreeNode>) cNode.children()).get(2));
			}
		} else if (s instanceof ReturnNode) 
			return true;
		
		boolean output = false;
		for (VisualizableTreeNode node: s.getChildren()){
			output =  output || hasReturn((SyntaxNode) node);
		}
		return output;
	}
}
