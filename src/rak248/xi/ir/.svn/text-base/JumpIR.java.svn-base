package rak248.xi.ir;

import java.util.ArrayList;

import rak248.asm.AddressingMode;
import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;

public class JumpIR extends StatementIR {
	
	public JumpIR(NameIR l) {
		setLabel("JUMP");
		addChild(l);
	}
	
	public SyntaxIR foldESEQ() {
		ArrayList<VisualizableIRNode> children = getChildren();
		SyntaxIR c = ((SyntaxIR) children.get(0)).foldESEQ();
		if(c instanceof EseqIR) {
			EseqIR child = (EseqIR) c;
			SeqIR seq = child.getS();
			seq.addChild(new JumpIR(new NameIR(child.getE().label())));
			seq.addChild(child.getL());
			return seq;
		}
		this.getChildren().set(0, c);
		return this;
	}
	
	public SyntaxIR foldCALL(){
		return this;
	}
	
	public ArrayList<Tile> generateTile(TempTable tTable) {
		String label = ((NameIR) getChildren().get(0)).getLabel();
		label = label.substring(7,label.length());
		Tile t = new Tile(1, "jmp", new AddressingMode(label, true), null);
		ArrayList<Tile> result = new ArrayList<Tile>();
		result.add(t);
		return result;
	}
	
	public ArrayList<Tile> generateTileWindows(TempTable tTable) {
		String label = ((NameIR) getChildren().get(0)).getLabel();
		label = label.substring(7,label.length());
		Tile t = new Tile(1, "jmp", new AddressingMode(label, true), null);
		ArrayList<Tile> result = new ArrayList<Tile>();
		result.add(t);
		return result;
	}
	
	public String prettyPrint() {
		String ret = "Jump(" + getChildren().get(0).label() + ")";
		return ret;
	}
}
