package rak248.xi.ir;

import java.util.ArrayList;

import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;

public class CompUnitIR extends SyntaxIR {
	
	private ArrayList<String> listOfDVS;
	private ArrayList<String> sizeSetup;
	
	public CompUnitIR(){
		setLabel("CompUnit");
		setListOfDVS(new ArrayList<String>());
		setSizeSetup(new ArrayList<String>());
	}
	
	public void addChildren(SeqIR child) {
		for(VisualizableIRNode c : child.getChildren()) {
			addChild((SyntaxIR) c);
		}
	}
	
	public SyntaxIR foldESEQ() {
		ArrayList<VisualizableIRNode> children = getChildren();
		for(int i = 0; i < children.size(); i++) {
			children.set(i, ((SyntaxIR) children.get(i)).foldESEQ());
		}
		return this;
	}
	
	public SyntaxIR foldCALL(){
		ArrayList<VisualizableIRNode> children = getChildren();
		for(int i = 0; i < children.size(); i++) {
			children.set(i, ((SyntaxIR) children.get(i)).foldCALL());
		}
		return this;
	}
	
	public ArrayList<Tile> generateTile(TempTable tTable) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		result.add(new Tile(0,".text",null,null));
		for(VisualizableIRNode childs : getChildren()) {
			SyntaxIR child = (SyntaxIR) childs;
			ArrayList<Tile> newT = child.generateTile(tTable);
			result.addAll(newT);
		}
		result.add(new Tile(0,".text",null,null));
		return result;
	}
	
	public ArrayList<Tile> generateTileWindows(TempTable tTable) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		result.add(new Tile(0,".text",null,null));
		for(VisualizableIRNode childs : getChildren()) {
			SyntaxIR child = (SyntaxIR) childs;
			ArrayList<Tile> newT = child.generateTileWindows(tTable);
			result.addAll(newT);
		}
		result.add(new Tile(0,".text",null,null));
		return result;
	}
	
	public String prettyPrint() {
		String ret = "Something is calling this at CompUnit, yo";
		return ret;
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
