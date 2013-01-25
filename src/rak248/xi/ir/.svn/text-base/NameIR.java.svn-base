package rak248.xi.ir;

import java.util.ArrayList;

import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.xi.SyntaxIR;

public class NameIR extends ExpressionIR {

	private String pointer;
	
	public NameIR(String label) {
		pointer = label;
		setLabel("NAME: " + label);
	}
	
	public String getLabel(){
		return pointer;
	}

	public SyntaxIR foldESEQ() {
		return this;
	}
	
	public SyntaxIR foldCALL() {
		return this;
	}
	
	/*
	 * label: 
	 */
	public ArrayList<Tile> generateTile(TempTable tTable) {
		Tile t = new Tile(0, pointer + ": ", null, null);
		ArrayList<Tile> result = new ArrayList<Tile>();
		result.add(t);
		return result;
	}
	
	public ArrayList<Tile> generateTileWindows(TempTable tTable) {
		Tile t = new Tile(0, pointer + ": ", null, null);
		ArrayList<Tile> result = new ArrayList<Tile>();
		result.add(t);
		return result;
	}
	
	public String prettyPrint(){
		return pointer;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof NameIR) {
			if(prettyPrint().equals(((NameIR) arg0).prettyPrint()))
				return true;
		}
		return false;
	}
}
