package rak248.xi.ir;

import java.util.ArrayList;

import rak248.asm.AddressingMode;
import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;

public class ExpIR extends StatementIR {

	public ExpIR(ExpressionIR exp) {
		addChild(exp);
		setLabel("ExpIR");
	}
	
	public SyntaxIR foldESEQ() {
		ExpressionIR child = (ExpressionIR) ((SyntaxIR) getChildren().get(0)).foldESEQ();
		if(child instanceof EseqIR) {
			//This might go too deep sometimes...not sure why so many nested Sequences
			/*
			SeqIR temp = new SeqIR();
			SeqIR innerSeq = (SeqIR) ((SyntaxIR) child.getChildren().get(0));
			SyntaxIR deeper = ((SyntaxIR)innerSeq.getChildren().get(0));
			SyntaxIR label = (SyntaxIR) ((SyntaxIR)deeper.getChildren().get(deeper.getChildren().size()-1));
			if(label instanceof LabelIR && ((LabelIR)label).getName().startsWith("_block")){
				deeper.getChildren().remove(deeper.getChildren().size()-1);
				((EseqIR) child).setChildren(child.getChildren());
			}			
			temp.addChild(((EseqIR) child).getS());
			temp.addChild(new ExpIR(((EseqIR) child).getE()));
			if(label instanceof LabelIR && ((LabelIR)label).getName().startsWith("_block")){
				temp.addChild(label);
			}
			return temp;
			*/
			SeqIR ret = new SeqIR();
			EseqIR childEseq = (EseqIR) child;
			ret.extend(childEseq.getS());
			ret.addChild(new ExpIR(childEseq.getE()));
			ret.extend(childEseq.getL());
			return ret;
		}
		getChildren().set(0, child);
		return this;
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
	
	public ArrayList<Tile> generateTile(TempTable tTable){
		ArrayList<Tile> result = ((SyntaxIR)getChildren().get(0)).generateTile(tTable);
		//result.add(new Tile(1,"popq",new AddressingMode("r12"),null));
		//tTable.popCalled();
		return result;
	}

	public ArrayList<Tile> generateTileWindows(TempTable tTable){
		ArrayList<Tile> result = ((SyntaxIR)getChildren().get(0)).generateTileWindows(tTable);
		//result.add(new Tile(1,"popq",new AddressingMode("r12"),null));
		//tTable.popCalled();
		return result;
	}
	
	public String prettyPrint(){
		String ret = ((SyntaxIR) getChildren().get(0)).prettyPrint();
		return ret;
	}
}
