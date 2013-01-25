package rak248.xi.ir;

import java.util.ArrayList;
import java.util.HashMap;

import rak248.asm.AddressingMode;
import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;

public class MemIR extends ExpressionIR {
	
	private boolean isThis;
	private int retSize = -999; // this is used when first child of CallIR is a MemIR
	                            // which happens for method calls

	public MemIR(ExpressionIR exprIR) {
		setLabel("MEM");
		addChild(exprIR);
		isThis = false;
	}
	
	public SyntaxIR foldESEQ() {
		SyntaxIR child1 = ((SyntaxIR) getChildren().get(0)).foldESEQ();
		getChildren().set(0, child1);
		if(child1 instanceof EseqIR) {
			EseqIR result = new EseqIR();
			result.addChild(((EseqIR) child1).getS());
			result.addChild(((EseqIR) child1).getL());
			result.addChild(new MemIR(((EseqIR) child1).getE()));
			return result;
		}
		return this;
	}
	
	public SyntaxIR foldCALL(){
		ArrayList<VisualizableIRNode> ch = new ArrayList<VisualizableIRNode>();
		for (VisualizableIRNode node: getChildren()){
			boolean callFold = false;
			if(node instanceof CallIR) {
				for(int i = 1; i < ((CallIR) node).getChildren().size(); i++) {
					if(((CallIR) node).getChildren().get(i) instanceof CallIR) {
						callFold = true;
					}
				}
			}
			if(callFold) {
				SyntaxIR child = ((SyntaxIR) node).foldCALL();
				ch.add(child); }
			else { ch.add(node);}
		}
		setChildren(ch);
		return this;
	}
	
	public ArrayList<Tile> generateTile(TempTable tTable){
		ArrayList<Tile> resultant = new ArrayList<Tile>();
		if(getChildren().get(0) instanceof OpIR) {
			resultant.addAll(((SyntaxIR) getChildren().get(0)).generateTile(tTable));
			resultant.add(new Tile(1, "movq", new AddressingMode(true, "r12"), new AddressingMode("r13")));
			resultant.add(new Tile(1, "movq", new AddressingMode("r13"), new AddressingMode("r12")));
			return resultant;
		}
		TempIR addr = (TempIR) getChildren().get(0);
		String funcname = tTable.getCurrentFunction().label();
		int lastindex = funcname.lastIndexOf("_");
		String letter = funcname.substring(lastindex+1, lastindex+2);
		Tile t1 = new Tile();
		Tile t2 = new Tile();
		t1.setScore(1);
		t2.setScore(1);
		t2.setDestAddrMode(new AddressingMode("r12"));
		t1.setDestAddrMode(new AddressingMode("r12"));
		t1.setOpCode("movq");
		t2.setOpCode("movq");
		if(addr.getId().startsWith("__args")) {
			String id = addr.getId();
			 if(letter.equals("t")) {
				if(id.startsWith("__args0")) {
					t1.setSrcAddrMode(new AddressingMode(true, "rsi")); }
				else if(id.startsWith("__args1")) {
					t1.setSrcAddrMode(new AddressingMode(true, "rdx")); }
				else if(id.startsWith("__args2")) {
					t1.setSrcAddrMode(new AddressingMode(true, "rcx")); }
				else if(id.startsWith("__args3")) {
					t1.setSrcAddrMode(new AddressingMode(true, "r8")); }
				else if(id.startsWith("__args4")) {
					t1.setSrcAddrMode(new AddressingMode(true, "r9")); }
				else if(id.startsWith("__args")) {
					t1.setSrcAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 5) * 8, "rbp"));
					t1.setDestAddrMode(new AddressingMode("r14"));
					t2.setDestAddrMode(new AddressingMode("r12"));
					t2.setSrcAddrMode(new AddressingMode(true, "r14"));
					resultant.add(t1);
					resultant.add(t2);
					return resultant;
				}
			 } else {
				 if(id.startsWith("__args0")) {
						t1.setDestAddrMode(new AddressingMode(true, "rdi")); }
					else if(id.startsWith("__args1")) {
						t1.setDestAddrMode(new AddressingMode(true, "rsi")); }
					else if(id.startsWith("__args2")) {
						t1.setDestAddrMode(new AddressingMode(true, "rdx")); }
					else if(id.startsWith("__args3")) {
						t1.setDestAddrMode(new AddressingMode(true, "rcs")); }
					else if(id.startsWith("__args4")) {
						t1.setDestAddrMode(new AddressingMode(true, "r8")); }
					else if(id.startsWith("__args5")) {
						t1.setDestAddrMode(new AddressingMode(true, "r9")); }
					else if(id.startsWith("__args")) {
						t1.setSrcAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 3) * 8, "rbp"));
						t1.setDestAddrMode(new AddressingMode("r14"));
						t2.setDestAddrMode(new AddressingMode("r12"));
						t2.setSrcAddrMode(new AddressingMode(true, "r14"));
						resultant.add(t1);
						resultant.add(t2);
						return resultant;
					}
			 }
		}
		HashMap<String, String> regs = tTable.getCurrentFunction().getRegAlloc();
		t1.setScore(1);
		t1.setOpCode("movq");
		t2 = new Tile();
		t2.setScore(1);
		t2.setOpCode("movq");
		if(!(addr.getId().startsWith("__args"))) {
			HashMap<String, Integer> stack = tTable.getCurrentFunction().getStackLocation();
			if(regs.containsKey(addr.getId())) {
				t1.setDestAddrMode(new AddressingMode(regs.get(addr.getId())));
			} else {
				int offset = stack.get(addr.getId());
				offset = offset * -1;
				t1.setSrcAddrMode(new AddressingMode(offset, "rbp"));
				t1.setDestAddrMode(new AddressingMode("r13"));
				t2.setSrcAddrMode(new AddressingMode(true, "r13"));
				t2.setDestAddrMode(new AddressingMode("r12"));
				resultant.add(t1);
				resultant.add(t2);
				return resultant;
			}
		}
		t1.setSrcAddrMode(new AddressingMode("r12"));
		resultant.add(t1);
		return resultant;
	}
	
	public ArrayList<Tile> generateTileWindows(TempTable tTable){
		ArrayList<Tile> resultant = new ArrayList<Tile>();
		if(getChildren().get(0) instanceof OpIR) {
			resultant.addAll(((SyntaxIR) getChildren().get(0)).generateTileWindows(tTable));
			resultant.add(new Tile(1, "movq", new AddressingMode(true, "r12"), new AddressingMode("r13")));
			resultant.add(new Tile(1, "movq", new AddressingMode("r13"), new AddressingMode("r12")));
			return resultant;
		}
		TempIR addr = (TempIR) getChildren().get(0);
		String funcname = tTable.getCurrentFunction().label();
		int lastindex = funcname.lastIndexOf("_");
		String letter = funcname.substring(lastindex+1, lastindex+2);
		Tile t1 = new Tile();
		Tile t2 = new Tile();
		t1.setScore(1);
		t2.setScore(1);
		t2.setDestAddrMode(new AddressingMode("r12"));
		t1.setDestAddrMode(new AddressingMode("r12"));
		t1.setOpCode("movq");
		t2.setOpCode("movq");
		if(addr.getId().startsWith("__args")) {
			String id = addr.getId();
			 if(letter.equals("t")) {
				if(id.startsWith("__args0")) {
					t1.setSrcAddrMode(new AddressingMode(true, "rdx")); }
				else if(id.startsWith("__args1")) {
					t1.setSrcAddrMode(new AddressingMode(true, "r8")); }
				else if(id.startsWith("__args2")) {
					t1.setSrcAddrMode(new AddressingMode(true, "r9")); }
				else if(id.startsWith("__args")) {
					t1.setSrcAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 5) * 8, "rbp"));
					t1.setDestAddrMode(new AddressingMode("r14"));
					t2.setDestAddrMode(new AddressingMode("r12"));
					t2.setSrcAddrMode(new AddressingMode(true, "r14"));
					resultant.add(t1);
					resultant.add(t2);
					return resultant;
				}
			 } else {
				 if(id.startsWith("__args0")) {
						t1.setDestAddrMode(new AddressingMode(true, "rcx")); }
					else if(id.startsWith("__args1")) {
						t1.setDestAddrMode(new AddressingMode(true, "rdx")); }
					else if(id.startsWith("__args2")) {
						t1.setDestAddrMode(new AddressingMode(true, "r8")); }
					else if(id.startsWith("__args3")) {
						t1.setDestAddrMode(new AddressingMode(true, "r9")); }
					else if(id.startsWith("__args")) {
						t1.setSrcAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 3) * 8, "rbp"));
						t1.setDestAddrMode(new AddressingMode("r14"));
						t2.setDestAddrMode(new AddressingMode("r12"));
						t2.setSrcAddrMode(new AddressingMode(true, "r14"));
						resultant.add(t1);
						resultant.add(t2);
						return resultant;
					}
			 }
		}
		HashMap<String, String> regs = tTable.getCurrentFunction().getRegAlloc();
		t1.setScore(1);
		t1.setOpCode("movq");
		t2 = new Tile();
		t2.setScore(1);
		t2.setOpCode("movq");
		if(!(addr.getId().startsWith("__args"))) {
			HashMap<String, Integer> stack = tTable.getCurrentFunction().getStackLocation();
			if(regs.containsKey(addr.getId())) {
				t1.setDestAddrMode(new AddressingMode(regs.get(addr.getId())));
			} else {
				int offset = stack.get(addr.getId());
				offset = offset * -1;
				t1.setSrcAddrMode(new AddressingMode(offset, "rbp"));
				t1.setDestAddrMode(new AddressingMode("r13"));
				t2.setSrcAddrMode(new AddressingMode(true, "r13"));
				t2.setDestAddrMode(new AddressingMode("r12"));
				resultant.add(t1);
				resultant.add(t2);
				return resultant;
			}
		}
		t1.setSrcAddrMode(new AddressingMode("r12"));
		resultant.add(t1);
		return resultant;
	}
	
	public String prettyPrint(){
		String ret = "[" + ((SyntaxIR) getChildren().get(0)).prettyPrint() + "]";
		return ret;
	}

	public void setThis(boolean isThis) {
		this.isThis = isThis;
	}

	public boolean isThis() {
		return isThis;
	}

	public int getRetSize() {
		return retSize;
	}

	public void setRetSize(int retSize) {
		this.retSize = retSize;
	}
}
