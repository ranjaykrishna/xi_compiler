package rak248.xi.ir;

import java.util.ArrayList;

import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;

public class SeqIR extends StatementIR {

	
	
	public SeqIR(String addOn) {
		setLabel("SEQ"+addOn);
	}
	
	public SeqIR() {
		setLabel("SEQ");
	}
	
	public void addIR(SyntaxIR nodeIR) {
		addChild(nodeIR);
	}
	
	public void addChild(SeqIR s) {
		if(s instanceof SeqIR) {
			extend(s);
		}
	}
	
	public void extend(SeqIR s) {
		for(VisualizableIRNode child : s.getChildren()) {
			addIR((SyntaxIR) child);
		}
	}

	public SyntaxIR foldESEQ() {
		SeqIR newSeq = new SeqIR();
		for(VisualizableIRNode c : getChildren()) {
			StatementIR child = (StatementIR) ((SyntaxIR) c).foldESEQ();
			newSeq.addChild(child);
		}
		return newSeq;
	}
	
	public SyntaxIR foldCALL(){
		ArrayList<VisualizableIRNode> ch = new ArrayList<VisualizableIRNode>();
		for (VisualizableIRNode node: getChildren()){
			SyntaxIR child = ((SyntaxIR) node).foldCALL();
			ch.add(child);
		}
		setChildren(ch);
		return this;
	}
	
	public ArrayList<Tile> generateTile(TempTable tTable) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		for(VisualizableIRNode childs : getChildren()) {
			SyntaxIR child = (SyntaxIR) childs;
			result.addAll(child.generateTile(tTable));
		}
		return result;
	}
	
	public ArrayList<Tile> generateTileWindows(TempTable tTable) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		for(VisualizableIRNode childs : getChildren()) {
			SyntaxIR child = (SyntaxIR) childs;
			result.addAll(child.generateTileWindows(tTable));
		}
		return result;
	}
}
