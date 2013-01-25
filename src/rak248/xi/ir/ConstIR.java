package rak248.xi.ir;

import java.util.ArrayList;

import rak248.asm.AddressingMode;
import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;

public class ConstIR extends ExpressionIR{
	
	private long value;
	
	public ConstIR(long value2) {
		this.setValue(value2);
		setLabel("CONST: " + value2);
	}
	
	public ConstIR(int value, String bool){
		this.setValue(value);
		setLabel("CONST: " + bool);
	}

	public void setValue(long value2) {
		this.value = value2;
	}

	public long getValue() {
		return value;
	}
	
	public SyntaxIR foldESEQ() {
		return this;
	}
	
	public SyntaxIR foldCALL() {
		return this;
	}
	
	public ArrayList<Tile> generateTile(TempTable tTable) {
		Tile t = new Tile();
		t.setScore(1);
		t.setOpCode("movq");
		t.setDestAddrMode(new AddressingMode("r12"));
		t.setSrcAddrMode(new AddressingMode(value));
		ArrayList<Tile> result = new ArrayList<Tile>();
		result.add(t);
		return result;
	}
	
	public ArrayList<Tile> generateTileWindows(TempTable tTable) {
		Tile t = new Tile();
		t.setScore(1);
		t.setOpCode("movq");
		t.setDestAddrMode(new AddressingMode("r12"));
		t.setSrcAddrMode(new AddressingMode(value));
		ArrayList<Tile> result = new ArrayList<Tile>();
		result.add(t);
		return result;
	}

	public String prettyPrint() {
		String ret = "";
		ret += value;
		return ret;
	}
}
