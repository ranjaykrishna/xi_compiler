package rak248.xi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.util.VisualizableIRNode;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.Type;


public class SyntaxIR implements VisualizableIRNode{
	
	private ArrayList<VisualizableIRNode> children = new ArrayList<VisualizableIRNode>();
	private String label;
	private static int index = 0;
	
	public SyntaxIR(){
		setLabel("DEADBEEF");
	}
	
	public void convertToIR(SyntaxNode sn){
		
	}
	
	public String simpleToString() {
		return "Label: " + label;
	}
	
	public String toString(){
		String ret =label+":[";
		for (VisualizableIRNode node : children){
			ret += node.toString() + ", ";
		}
		if (children.size() == 0){
			ret = ret.substring(0,ret.length()-2);
		}
		else{
			ret = ret.substring(0,ret.length()-2)+ "]";
		}
		return ret;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String label() {
		return label;
	}

	@Override
	public Iterable<VisualizableIRNode> children() {
		return getChildren();
	}

	public ArrayList<VisualizableIRNode> getChildren() {
		return children;
	}
	
	public void setChildren(ArrayList<VisualizableIRNode> children) {
		this.children = children;
	}
	
	public void addChild(SyntaxIR list){
		getChildren().add(list);
	}
	

	
	public static String getFreshLabel() {
		String newLabel = ((Integer)index).toString();
		index++;
		return newLabel;
	}
	
	public static int getFreshNum(int i){
		int temp = index;
		index += i;
		return temp;
	}
	
	public SyntaxIR foldESEQ() {
		System.err.println("Super SyntaxIR foldESEQ is being called");
		return null;
	}
	
	public SyntaxIR foldCALL(){
		System.err.println("Super SyntaxIR foldCALL is being called");
		return null;
	}
	
	public ArrayList<Tile> generateTile(TempTable tTable) {
		return null;
	}
	
	public ArrayList<Tile> generateTileWindows(TempTable tTable) {
		return null;
	}
	
	public String prettyPrint() {
		return "ERROR: should not call syntaxIR's pretty print method";
	}
	
	public void updateTemp(TempIR oldTemp, TempIR newTemp) {
		int i = 0;
		if(this instanceof MoveIR){
			if(this.getChildren().get(0) instanceof TempIR)
				i = 1;
			//System.out.println("move ir instance");
			//System.out.println(this);
			//System.out.println(newTemp);
		} else if(this instanceof TempIR && ((TempIR) this).getId().equals(oldTemp.getId())) {
			((TempIR) this).setId(newTemp.getId());
			//this.updateTemp(oldTemp, newTemp);
		}
		for(;i < getChildren().size(); i ++) {
			SyntaxIR child = (SyntaxIR) getChildren().get(i);
			if((child instanceof TempIR) && ((TempIR) child).getId().equals(oldTemp.getId())) {
				((TempIR) child).setId(newTemp.getId());
				child.updateTemp(oldTemp, newTemp);
			}
			else {
				child.updateTemp(oldTemp, newTemp);
			}
		}
	}
	
	public ArrayList<MemIR> findThis(ArrayList<MemIR> found) {
		for(VisualizableIRNode child : getChildren()) {
			System.out.println("find this syntax child:"+child);
			if(child instanceof MemIR && ((MemIR) child).isThis()) {
				found.add((MemIR) child);
			}
			found.addAll(((SyntaxIR) child).findThis(found));
		}
		return found;
	}

	public void replaceFields(HashMap<TempIR, Integer> fieldOffset,
			TempIR thisIR) {
		for(int i = 0; i < getChildren().size(); i ++) {
			SyntaxIR child = (SyntaxIR) getChildren().get(i);
			if(child instanceof TempIR) {
				for(TempIR field : fieldOffset.keySet()) {
					if(field.prettyPrint().equals(child.prettyPrint())) {
						Integer val = fieldOffset.get(field)+8;
						ConstIR offset = new ConstIR(val);
						OpIR op = new OpIR(opEnum.PLUS, thisIR, offset);
						MemIR mem = new MemIR(op);
						getChildren().set(i, mem);
					}
				}
			}
			child.replaceFields(fieldOffset, thisIR);
		}
		
	}
}
