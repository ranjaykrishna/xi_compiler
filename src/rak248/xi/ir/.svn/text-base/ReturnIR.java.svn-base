package rak248.xi.ir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import rak248.asm.AddressingMode;
import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;

public class ReturnIR extends StatementIR {
	
	private HashMap<TempIR, String> registersUsed;
	
	public ReturnIR(ExpressionIR exprIR) {
		this();
		addChild(exprIR);
		registersUsed = new HashMap<TempIR, String>();
		
	}
	
	public ReturnIR() {
		setLabel("RETURN");
		registersUsed = new HashMap<TempIR, String>();
		
	}
	
	public SyntaxIR foldESEQ() {
		SyntaxIR ret;
		ArrayList<VisualizableIRNode> children = getChildren();
		if(children.size() == 0) {
			return this;
		}
		SyntaxIR child = ((SyntaxIR) children.get(0)).foldESEQ();
		if(child instanceof EseqIR) {
			ret = new SeqIR();
			//System.out.println("child:"+Optimize.foldSEQ(child));
			SyntaxIR s = ((EseqIR) child).getS();
			SyntaxIR e = ((EseqIR) child).getE();
			SyntaxIR l = ((EseqIR) child).getL();
			l=l.foldESEQ();
			Optimize.foldSEQ(l);
			//System.out.println("lfold:"+Optimize.foldSEQ(l));
			ret.addChild(s);
			for(int i = 0; i < l.getChildren().size(); i ++) {
				SyntaxIR c = (SyntaxIR) l.getChildren().get(i);
				if(!(c instanceof LabelIR)) {
					ret.addChild((SyntaxIR) l.getChildren().remove(i));
					i = i -1;
				}
			}
			ret.addChild(new ReturnIR((ExpressionIR) e));
			ret.addChild(l);
		} else {
			ret = this;
		}
		return ret;
	}
	
	public SyntaxIR foldCALL(){
		ArrayList<VisualizableIRNode> ch = new ArrayList<VisualizableIRNode>();
		for (VisualizableIRNode node: getChildren()){
			ch.add(((SyntaxIR) node).foldCALL());
		}
		setChildren(ch);
		return this;
	}
	
	/*
	 * If return IR is called with a temp starting with "_ret", then we can assume
	 * that all of the ret's are at the top of the stack.
	 */
	public ArrayList<Tile> generateTile(TempTable tTable) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		if(getChildren().size() > 1) {
			for(int i = 0; i < getChildren().size(); i ++) {
				result.addAll(((SyntaxIR) getChildren().get(i)).generateTile(tTable));
				Tile t = new Tile(1, "pushq", new AddressingMode("r12"), null);
				result.add(t);
			}
			for(int i = getChildren().size()-1; i >= 0; i --) {
				//result.addAll(((SyntaxIR) getChildren().get(i)).generateTile(tTable));
				Tile t = new Tile(1, "popq", new AddressingMode(i*8, "rdi"), null);
				result.add(t);
			}
			//result.add(new Tile(1, "movq", new AddressingMode("rdi"), new AddressingMode("rax")));
		}
		else if(getChildren().size() == 1){
			ArrayList<Tile> retVal = ((SyntaxIR) getChildren().get(0)).generateTile(tTable);
			result.addAll(retVal);
			Tile t = new Tile(1, "movq", new AddressingMode("r12"), new AddressingMode("rax"));
			result.add(t);
		}
		
		//Before we return we need to pop all the local variables and throw them away
		AddressingMode rbp = new AddressingMode("rbp");
		AddressingMode rsp = new AddressingMode("rsp");
		Integer newLabel = tTable.getNewJumpLabel();
		result.add(new Tile(1, "ret" + newLabel + ": ", null, null));
		result.add(new Tile(1, "cmp", rbp, rsp));
		result.add(new Tile(1, "je", new AddressingMode("retend" + newLabel, true), null));
		result.add(new Tile(1,"popq",new AddressingMode("r12"),null));
		result.add(new Tile(1, "jmp", new AddressingMode("ret" + newLabel, true), null));
		result.add(new Tile(1, "retend" + newLabel + ": ", null, null));
		Tile t2 = new Tile(1, "ret", null, null);
		HashSet<String> stringsDone = new HashSet<String>();
		for(String reg : registersUsed.values()) {
			if(!stringsDone.contains(reg)) {
				stringsDone.add(reg);
				result.add(new Tile(1, "popq", new AddressingMode(reg), null));
			}
		}
		result.add(t2);
		return result;
	}
	
	public ArrayList<Tile> generateTileWindows(TempTable tTable) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		if(getChildren().size() > 1) {
			for(int i = 0; i < getChildren().size(); i ++) {
				result.addAll(((SyntaxIR) getChildren().get(i)).generateTileWindows(tTable));
				Tile t = new Tile(1, "pushq", new AddressingMode("r12"), null);
				result.add(t);
			}
			for(int i = getChildren().size()-1; i >= 0; i --) {
				//result.addAll(((SyntaxIR) getChildren().get(i)).generateTile(tTable));
				Tile t = new Tile(1, "popq", new AddressingMode(i*8, "rdx"), null);
				result.add(t);
			}
			//result.add(new Tile(1, "movq", new AddressingMode("rdi"), new AddressingMode("rax")));
		}
		else if(getChildren().size() == 1){
			ArrayList<Tile> retVal = ((SyntaxIR) getChildren().get(0)).generateTileWindows(tTable);
			result.addAll(retVal);
			Tile t = new Tile(1, "movq", new AddressingMode("r12"), new AddressingMode("rax"));
			result.add(t);
		}
		
		//Before we return we need to pop all the local variables and throw them away
		AddressingMode rbp = new AddressingMode("rbp");
		AddressingMode rsp = new AddressingMode("rsp");
		Integer newLabel = tTable.getNewJumpLabel();
		result.add(new Tile(1, "ret" + newLabel + ": ", null, null));
		result.add(new Tile(1, "cmp", rbp, rsp));
		result.add(new Tile(1, "je", new AddressingMode("retend" + newLabel, true), null));
		result.add(new Tile(1,"popq",new AddressingMode("r12"),null));
		result.add(new Tile(1, "jmp", new AddressingMode("ret" + newLabel, true), null));
		result.add(new Tile(1, "retend" + newLabel + ": ", null, null));
		Tile t2 = new Tile(1, "ret", null, null);
		HashSet<String> stringsDone = new HashSet<String>();
		for(String reg : registersUsed.values()) {
			if(!stringsDone.contains(reg)) {
				stringsDone.add(reg);
				result.add(new Tile(1, "popq", new AddressingMode(reg), null));
			}
		}
		result.add(t2);
		return result;
	}
	
	public String prettyPrint(){
		String ret = "RETURN: ";
		for (VisualizableIRNode node: getChildren()){
			ret += ((SyntaxIR) node).prettyPrint() + ", ";
		}
		return ret.substring(0,ret.length()-2);
	}

	public void setRegistersUsed(HashMap<TempIR, String> registersUsed) {
		this.registersUsed = registersUsed;
	}

	public HashMap<TempIR, String> getRegistersUsed() {
		return registersUsed;
	}
}
