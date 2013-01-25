package rak248.xi.ir;

import java.util.ArrayList;
import java.util.HashMap;

import rak248.asm.AddressingMode;
import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.typeChecker.Type;

public class MoveIR extends StatementIR {

	public MoveIR(ExpressionIR e1, SyntaxIR e2) {
		addChild(e1);
		addChild(e2);
		setLabel("MOVE");
	}
	
	public SyntaxIR foldESEQ() {
		SyntaxIR child1 = ((SyntaxIR) getChildren().get(0)).foldESEQ();
		SyntaxIR child2 = ((SyntaxIR) getChildren().get(1)).foldESEQ();
		getChildren().set(0, child1);
		getChildren().set(1, child2);
		SeqIR ret = new SeqIR();
		if(!(child1 instanceof EseqIR) && !(child2 instanceof EseqIR)) {
			return this;
		} else if(child1 instanceof EseqIR && !(child2 instanceof EseqIR)) {
			ret.addChild(((EseqIR) child1).getS());
			ret.addChild(new MoveIR(((EseqIR) child1).getE(), (ExpressionIR) child2));
			ret.addChild(((EseqIR) child1).getL());
		} else if(!(child1 instanceof EseqIR) && (child2 instanceof EseqIR)) {
			/*SeqIR innerSeq = (SeqIR) ((SyntaxIR) child2.getChildren().get(0));
			SyntaxIR label = (SyntaxIR) ((SyntaxIR)innerSeq.getChildren().get(innerSeq.getChildren().size()-1));
			if(label instanceof LabelIR && ((LabelIR)label).getName().startsWith("_block")){
				innerSeq.getChildren().remove(innerSeq.getChildren().size()-1);
				((EseqIR) child2).setChildren(child2.getChildren());
			}			
			MoveIR opir = new MoveIR((ExpressionIR) child1, ((EseqIR) child2).getE());
			ret.addChild(((EseqIR) child2).getS());
			ret.addChild(opir);
			if(label instanceof LabelIR && ((LabelIR)label).getName().startsWith("_block")){
				ret.addChild(label);
			} */
			MoveIR opir = new MoveIR((ExpressionIR) child1, ((EseqIR) child2).getE());
			ret.addChild(((EseqIR) child2).getS());
			ret.addChild(opir);
			ret.addChild(((EseqIR) child2).getL());
		} else {
			ret.addChild(((EseqIR) child1).getS());
			ExpressionIR child1E = ((EseqIR) child1).getE();
			ret.addChild(((EseqIR) child2).getS());
			MoveIR opir = new MoveIR(child1E, ((EseqIR) child2).getE());
			ret.addChild(opir);
			ret.addChild(((EseqIR) child1).getL());
			ret.addChild(((EseqIR) child2).getL());
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
	
	public ArrayList<Tile> generateTile(TempTable tTable) {
		SyntaxIR left = ((SyntaxIR) getChildren().get(0));
		SyntaxIR right = ((SyntaxIR) getChildren().get(1));
		ArrayList<Tile> resultant = new ArrayList<Tile>();
		ArrayList<Tile> rightTile = right.generateTile(tTable);
		resultant.addAll(rightTile);
		/*
		 * If the left is a temp, you're either moving into a register or onto the stack.
		 * If you're moving into an argument, you're moving into a special register.
		 * You assume that what you're moving to is in register R12.
		 */
		if(left instanceof TempIR) {
			String funcname = tTable.getCurrentFunction().label();
			int lastindex = funcname.lastIndexOf("_");
			String letter = funcname.substring(lastindex+1, lastindex+2);
			Tile t1 = new Tile();
			/*
			 * If you're moving into an argument register, t1 has a special destination.
			 */
			if(((TempIR) left).getId().startsWith("__args")) {
				String id = ((TempIR) left).getId();
				 if(letter.equals("t")) {
					if(id.startsWith("__args0")) {
						t1.setDestAddrMode(new AddressingMode("rsi")); }
					else if(id.startsWith("__args1")) {
						t1.setDestAddrMode(new AddressingMode("rdx")); }
					else if(id.startsWith("__args2")) {
						t1.setDestAddrMode(new AddressingMode("rcx")); }
					else if(id.startsWith("__args3")) {
						t1.setDestAddrMode(new AddressingMode("r8")); }
					else if(id.startsWith("__args4")) {
						t1.setDestAddrMode(new AddressingMode("r9")); }
					else if(id.startsWith("__args")) {
						t1.setDestAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 5) * 8, "rbp"));
					} else {
						int offset = tTable.getCurrentFunction().getStackLocation().get(id); 
						t1.setDestAddrMode(new AddressingMode(-1 * (offset), "rbp")); }
				 } else {
					 if(id.startsWith("__args0")) {
							t1.setDestAddrMode(new AddressingMode("rdi")); }
						else if(id.startsWith("__args1")) {
							t1.setDestAddrMode(new AddressingMode("rsi")); }
						else if(id.startsWith("__args2")) {
							t1.setDestAddrMode(new AddressingMode("rdx")); }
						else if(id.startsWith("__args3")) {
							t1.setDestAddrMode(new AddressingMode("rcs")); }
						else if(id.startsWith("__args4")) {
							t1.setDestAddrMode(new AddressingMode("r8")); }
						else if(id.startsWith("__args5")) {
							t1.setDestAddrMode(new AddressingMode("r9")); }
						else if(id.startsWith("__args")) {
							t1.setDestAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 3) * 8, "rbp"));
						} else {
							int offset = tTable.getCurrentFunction().getStackLocation().get(id); 
							t1.setDestAddrMode(new AddressingMode(-1 * (offset), "rbp")); }
				 }
			}
			/*
			 * If you're not moving into an argument, you have to look up the temp in the registers
			 * If it's not in the registers, it must be on the stack somewhere.
			 */
			HashMap<String, String> regs = tTable.getCurrentFunction().getRegAlloc();
			t1.setScore(1);
			t1.setOpCode("movq");
			if(!((TempIR) left).getId().startsWith("__args")) {
				if(regs.containsKey(((TempIR) left).getId())) {
					t1.setDestAddrMode(new AddressingMode(regs.get(((TempIR) left).getId())));
				} else {
					HashMap<String, Integer> stack = tTable.getCurrentFunction().getStackLocation();
					int offset = stack.get(((TempIR) left).getId());
					offset = offset * -1;
					t1.setDestAddrMode(new AddressingMode(offset, "rbp"));
				}
			}
			t1.setSrcAddrMode(new AddressingMode("r12"));
			resultant.add(t1);
			return resultant;
		}
		// left is a MemIR
		/*
		 * If the left is a memIR, you have to add an instruction if the temp was spilled instead of in a register.
		 * If the temp was spilled into the stack, then you're going to look it up and move the result to r13.
		 * Then you move r12 into the memory address at r13.
		 */
		else {
			String funcname = tTable.getCurrentFunction().label();
			int lastindex = funcname.lastIndexOf("_");
			String letter = funcname.substring(lastindex+1, lastindex+2);
			Tile t1 = new Tile();
			Tile t2 = new Tile();
			t1.setScore(1);
			t2.setScore(1);
			t1.setOpCode("movq");
			t2.setOpCode("movq");
			if(left.getChildren().get(0) instanceof OpIR) {
				resultant.add(new Tile(1, "movq", new AddressingMode("r12"), new AddressingMode("r13")));
				resultant.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				resultant.addAll(((SyntaxIR) left.getChildren().get(0)).generateTile(tTable));
				resultant.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				resultant.add(new Tile(1, "movq", new AddressingMode("r13"), new AddressingMode(true,"r12")));
				return resultant;
			}
			TempIR leftTemp = (TempIR) left.getChildren().get(0);
			if(((TempIR) leftTemp).getId().startsWith("__args")) {
				String id = ((TempIR) leftTemp).getId();
				 if(letter.equals("t")) {
					if(id.startsWith("__args0")) {
						t1.setDestAddrMode(new AddressingMode(true, "rsi")); }
					else if(id.startsWith("__args1")) {
						t1.setDestAddrMode(new AddressingMode(true, "rdx")); }
					else if(id.startsWith("__args2")) {
						t1.setDestAddrMode(new AddressingMode(true, "rcx")); }
					else if(id.startsWith("__args3")) {
						t1.setDestAddrMode(new AddressingMode(true, "r8")); }
					else if(id.startsWith("__args4")) {
						t1.setDestAddrMode(new AddressingMode(true, "r9")); }
					else if(id.startsWith("__args")) {
						t1.setSrcAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 5) * 8, "rbp"));
						t1.setDestAddrMode(new AddressingMode("r13"));
						t2.setDestAddrMode(new AddressingMode(true, "r13"));
						t2.setSrcAddrMode(new AddressingMode("r12"));
						resultant.add(t1);
						resultant.add(t2);
						return resultant;
					} else {
						int offset = tTable.getCurrentFunction().getStackLocation().get(id); 
						t1.setSrcAddrMode(new AddressingMode(-1 * (offset), "rbp"));
						t1.setDestAddrMode(new AddressingMode("r13"));
						t2.setDestAddrMode(new AddressingMode(true, "r13"));
						t2.setSrcAddrMode(new AddressingMode("r12"));
						resultant.add(t1);
						resultant.add(t2);
						return resultant; }
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
							t1.setDestAddrMode(new AddressingMode("r13"));
							t2.setDestAddrMode(new AddressingMode(true, "r13"));
							t2.setSrcAddrMode(new AddressingMode("r12"));
							resultant.add(t1);
							resultant.add(t2);
							return resultant;
						} else {
							int offset = tTable.getCurrentFunction().getStackLocation().get(id); 
							t1.setSrcAddrMode(new AddressingMode(-1 * (offset), "rbp"));
							t1.setDestAddrMode(new AddressingMode("r13"));
							t2.setDestAddrMode(new AddressingMode(true, "r13"));
							t2.setSrcAddrMode(new AddressingMode("r12"));
							resultant.add(t1);
							resultant.add(t2);
							return resultant;
						}
				 }
				 t1.setSrcAddrMode(new AddressingMode("r12"));
				 resultant.add(t1);
				 return resultant;
			}
			HashMap<String, String> regs = tTable.getCurrentFunction().getRegAlloc();
			t1.setScore(1);
			t1.setOpCode("movq");
			if(!((TempIR) leftTemp).getId().startsWith("__args")) {
				if(regs.containsKey(((TempIR) leftTemp).getId())) {
					t1.setDestAddrMode(new AddressingMode(true, regs.get(((TempIR) leftTemp).getId())));
					t1.setSrcAddrMode(new AddressingMode("r12"));
					resultant.add(t1);
					return resultant;
				} else {
					HashMap<String, Integer> stack = tTable.getCurrentFunction().getStackLocation();
					int offset = stack.get(((TempIR) leftTemp).getId());
					offset = offset * -1;
					t1.setSrcAddrMode(new AddressingMode(offset, "rbp"));
					t1.setDestAddrMode(new AddressingMode("r13"));
					t2.setDestAddrMode(new AddressingMode(true, "r13"));
					t2.setSrcAddrMode(new AddressingMode("r12"));
					resultant.add(t1);
					resultant.add(t2);
					return resultant;
				}
			}
			System.out.println("should not get here");
			return null;
			
		}
	}
	
	public ArrayList<Tile> generateTileWindows(TempTable tTable) {
		SyntaxIR left = ((SyntaxIR) getChildren().get(0));
		SyntaxIR right = ((SyntaxIR) getChildren().get(1));
		ArrayList<Tile> resultant = new ArrayList<Tile>();
		ArrayList<Tile> rightTile = right.generateTileWindows(tTable);
		resultant.addAll(rightTile);
		/*
		 * If the left is a temp, you're either moving into a register or onto the stack.
		 * If you're moving into an argument, you're moving into a special register.
		 * You assume that what you're moving to is in register R12.
		 */
		if(left instanceof TempIR) {
			String funcname = tTable.getCurrentFunction().label();
			int lastindex = funcname.lastIndexOf("_");
			String letter = funcname.substring(lastindex+1, lastindex+2);
			Tile t1 = new Tile();
			/*
			 * If you're moving into an argument register, t1 has a special destination.
			 */
			if(((TempIR) left).getId().startsWith("__args")) {
				String id = ((TempIR) left).getId();
				 if(letter.equals("t")) {
					if(id.startsWith("__args0")) {
						t1.setDestAddrMode(new AddressingMode("rdx")); }
					else if(id.startsWith("__args1")) {
						t1.setDestAddrMode(new AddressingMode("r8")); }
					else if(id.startsWith("__args2")) {
						t1.setDestAddrMode(new AddressingMode("r9")); }
					else if(id.startsWith("__args")) {
						t1.setDestAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 5) * 8, "rbp"));
					} else {
						int offset = tTable.getCurrentFunction().getStackLocation().get(id); 
						t1.setDestAddrMode(new AddressingMode(-1 * (offset), "rbp")); }
				 } else {
					 if(id.startsWith("__args0")) {
							t1.setDestAddrMode(new AddressingMode("rcx")); }
						else if(id.startsWith("__args1")) {
							t1.setDestAddrMode(new AddressingMode("rdx")); }
						else if(id.startsWith("__args2")) {
							t1.setDestAddrMode(new AddressingMode("r8")); }
						else if(id.startsWith("__args3")) {
							t1.setDestAddrMode(new AddressingMode("r9")); }
						else if(id.startsWith("__args")) {
							t1.setDestAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 3) * 8, "rbp"));
						} else {
							int offset = tTable.getCurrentFunction().getStackLocation().get(id); 
							t1.setDestAddrMode(new AddressingMode(-1 * (offset), "rbp")); }
				 }
			}
			/*
			 * If you're not moving into an argument, you have to look up the temp in the registers
			 * If it's not in the registers, it must be on the stack somewhere.
			 */
			HashMap<String, String> regs = tTable.getCurrentFunction().getRegAlloc();
			t1.setScore(1);
			t1.setOpCode("movq");
			if(!((TempIR) left).getId().startsWith("__args")) {
				if(regs.containsKey(((TempIR) left).getId())) {
					t1.setDestAddrMode(new AddressingMode(regs.get(((TempIR) left).getId())));
				} else {
					HashMap<String, Integer> stack = tTable.getCurrentFunction().getStackLocation();
					int offset = stack.get(((TempIR) left).getId());
					offset = offset * -1;
					t1.setDestAddrMode(new AddressingMode(offset, "rbp"));
				}
			}
			t1.setSrcAddrMode(new AddressingMode("r12"));
			resultant.add(t1);
			return resultant;
		}
		// left is a MemIR
		/*
		 * If the left is a memIR, you have to add an instruction if the temp was spilled instead of in a register.
		 * If the temp was spilled into the stack, then you're going to look it up and move the result to r13.
		 * Then you move r12 into the memory address at r13.
		 */
		else {
			String funcname = tTable.getCurrentFunction().label();
			int lastindex = funcname.lastIndexOf("_");
			String letter = funcname.substring(lastindex+1, lastindex+2);
			Tile t1 = new Tile();
			Tile t2 = new Tile();
			t1.setScore(1);
			t2.setScore(1);
			t1.setOpCode("movq");
			t2.setOpCode("movq");
			if(left.getChildren().get(0) instanceof OpIR) {
				resultant.add(new Tile(1, "movq", new AddressingMode("r12"), new AddressingMode("r13")));
				resultant.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				resultant.addAll(((SyntaxIR) left.getChildren().get(0)).generateTileWindows(tTable));
				resultant.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				resultant.add(new Tile(1, "movq", new AddressingMode("r13"), new AddressingMode(true,"r12")));
				return resultant;
			}
			TempIR leftTemp = (TempIR) left.getChildren().get(0);
			if(((TempIR) leftTemp).getId().startsWith("__args")) {
				String id = ((TempIR) leftTemp).getId();
				 if(letter.equals("t")) {
					if(id.startsWith("__args0")) {
						t1.setDestAddrMode(new AddressingMode(true, "rdx")); }
					else if(id.startsWith("__args1")) {
						t1.setDestAddrMode(new AddressingMode(true, "r8")); }
					else if(id.startsWith("__args2")) {
						t1.setDestAddrMode(new AddressingMode(true, "r9")); }
					else if(id.startsWith("__args")) {
						t1.setSrcAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 5) * 8, "rbp"));
						t1.setDestAddrMode(new AddressingMode("r13"));
						t2.setDestAddrMode(new AddressingMode(true, "r13"));
						t2.setSrcAddrMode(new AddressingMode("r12"));
						resultant.add(t1);
						resultant.add(t2);
						return resultant;
					} else {
						int offset = tTable.getCurrentFunction().getStackLocation().get(id); 
						t1.setSrcAddrMode(new AddressingMode(-1 * (offset), "rbp"));
						t1.setDestAddrMode(new AddressingMode("r13"));
						t2.setDestAddrMode(new AddressingMode(true, "r13"));
						t2.setSrcAddrMode(new AddressingMode("r12"));
						resultant.add(t1);
						resultant.add(t2);
						return resultant; }
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
							t1.setDestAddrMode(new AddressingMode("r13"));
							t2.setDestAddrMode(new AddressingMode(true, "r13"));
							t2.setSrcAddrMode(new AddressingMode("r12"));
							resultant.add(t1);
							resultant.add(t2);
							return resultant;
						} else {
							int offset = tTable.getCurrentFunction().getStackLocation().get(id); 
							t1.setSrcAddrMode(new AddressingMode(-1 * (offset), "rbp"));
							t1.setDestAddrMode(new AddressingMode("r13"));
							t2.setDestAddrMode(new AddressingMode(true, "r13"));
							t2.setSrcAddrMode(new AddressingMode("r12"));
							resultant.add(t1);
							resultant.add(t2);
							return resultant;
						}
				 }
				 t1.setSrcAddrMode(new AddressingMode("r12"));
				 resultant.add(t1);
				 return resultant;
			}
			HashMap<String, String> regs = tTable.getCurrentFunction().getRegAlloc();
			t1.setScore(1);
			t1.setOpCode("movq");
			if(!((TempIR) leftTemp).getId().startsWith("__args")) {
				if(regs.containsKey(((TempIR) leftTemp).getId())) {
					t1.setDestAddrMode(new AddressingMode(true, regs.get(((TempIR) leftTemp).getId())));
					t1.setSrcAddrMode(new AddressingMode("r12"));
					resultant.add(t1);
					return resultant;
				} else {
					HashMap<String, Integer> stack = tTable.getCurrentFunction().getStackLocation();
					int offset = stack.get(((TempIR) leftTemp).getId());
					offset = offset * -1;
					t1.setSrcAddrMode(new AddressingMode(offset, "rbp"));
					t1.setDestAddrMode(new AddressingMode("r13"));
					t2.setDestAddrMode(new AddressingMode(true, "r13"));
					t2.setSrcAddrMode(new AddressingMode("r12"));
					resultant.add(t1);
					resultant.add(t2);
					return resultant;
				}
			}
			System.out.println("should not get here");
			return null;
			
		}
	}
	
	public String prettyPrint() {
		String ret = ((SyntaxIR) getChildren().get(0)).prettyPrint() + " = " + ((SyntaxIR) getChildren().get(1)).prettyPrint();
		return ret;
	}
}
