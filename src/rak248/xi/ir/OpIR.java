package rak248.xi.ir;

import java.util.ArrayList;

import rak248.asm.AddressingMode;
import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.OpIR.opEnum;

public class OpIR extends ExpressionIR implements Comparable{
	
	public static enum opEnum {DIVIDE, MINUS, NOT_EQUAL, NOT, UNARY_MINUS, LT, OR, GEQ,  // NOT SURE IF WE NEED UNARY_MINUS OR NOT
		PLUS, EQUAL, TIMES, LEQ, GT, AND, LSHIFT, RSHIFT, MODULO};
	
	private opEnum op;
	
	public OpIR(opEnum op, SyntaxIR ir) {
		this.op = op;
		addChild(ir);
		setLabel(opToString());
	}

	public OpIR(opEnum i, ExpressionIR e1, SyntaxIR e2) {
		op = i;
		addChild(e1);
		addChild(e2);
		setLabel(opToString());
	}

	public void setOp(opEnum op) {
		this.op = op;
		setLabel(opToString());
	}

	public opEnum getOp() {
		return op;
	}
	
	public String opToString(){
		switch(op){
		case UNARY_MINUS:	return "UNARY_MINUS";
		case NOT:			return "NOT";
		case NOT_EQUAL:		return "NOT_EQUAL";
		case LT:			return "LT";
		case OR:			return "OR";
		case GEQ:			return "GEQ";
		case EQUAL:			return "EQUAL";
		case LEQ:			return "LEQ";
		case GT:			return "GT";
		case AND:			return "AND";
		case DIVIDE:		return "DIVIDE";
		case MODULO:		return "MODULO";
		case MINUS:			return "MINUS";
		case PLUS:			return "PLUS";
		case TIMES:			return "TIMES";
		case LSHIFT:		return "LSHIFT";
		case RSHIFT:		return "RSHIFT";
		default:			return "DEADBEEF";	
		}
	}
	
	public String opToPrettyString(){
		switch(op){
		case UNARY_MINUS:	return "-";
		case NOT:			return "!";
		case NOT_EQUAL:		return "!=";
		case LT:			return "<";
		case OR:			return "|";
		case GEQ:			return ">=";
		case EQUAL:			return "==";
		case LEQ:			return "<=";
		case GT:			return ">";
		case AND:			return "&";
		case DIVIDE:		return "/";
		case MODULO:		return "%";
		case MINUS:			return "-";
		case PLUS:			return "+";
		case TIMES:			return "*";
		case LSHIFT:		return "<<";
		case RSHIFT:		return ">>";
		default:			return "DEADBEEF";	
		}
	}
	
	public boolean returnsBool(){
		switch(op){
		case NOT_EQUAL:
		case NOT:
		case LT:
		case OR:
		case GEQ:
		case EQUAL:
		case LEQ:
		case GT:
		case AND:
			return true;
		default:
			return false;
		}
	}
	
	public boolean returnsInt(){
		switch(op){
		case DIVIDE:
		case MINUS:
		case UNARY_MINUS:
		case PLUS:
		case MODULO:
		case TIMES:
		case LSHIFT:
		case RSHIFT:
			return true;
		default:
			return false;
		}
	}
	
	public String opToAssembly(){
		switch(op){
		case UNARY_MINUS:	return "neg";
		case NOT:			return "btc";
		case NOT_EQUAL:		return "jne";
		case LT:			return "js";
		case OR:			return "or";
		case GEQ:			return "jge";
		case EQUAL:			return "je";
		case LEQ:			return "jle";
		case GT:			return "jg";
		case AND:			return "and";
		case DIVIDE:		return "div";
		case MODULO:		return "MODULO FAIL";
		case MINUS:			return "subq";
		case PLUS:			return "add";
		case TIMES:			return "imul";
		case LSHIFT:		return "shl";
		case RSHIFT:		return "shr";
		default:			return "FAIL";	
		}
	}
	
	public SyntaxIR foldESEQ() {
		SyntaxIR child1 = ((SyntaxIR) getChildren().get(0)).foldESEQ();
		SyntaxIR child2 = null;
		EseqIR ret = new EseqIR();
		getChildren().set(0, child1);
		if(getChildren().size() > 1) {
			child2 = ((SyntaxIR) getChildren().get(1)).foldESEQ();
			getChildren().set(1, child2);
		}
		if(!(child1 instanceof EseqIR) && child2 == null) {
			return this;
		} else if(child1 instanceof EseqIR && child2 == null) {
			ret.addChild(((EseqIR) child1).getS());
			ret.addChild(((EseqIR) child1).getL());
			ret.addChild(new OpIR(op, ((EseqIR) child1).getE()));
		} else if(!(child1 instanceof EseqIR) && !(child2 instanceof EseqIR)) {
			return this;
		} else if(child1 instanceof EseqIR && !(child2 instanceof EseqIR)) {
			ret.addChild(((EseqIR) child1).getS());
			ret.addChild(((EseqIR) child1).getL());
			ret.addChild(new OpIR(op, ((EseqIR) child1).getE(), child2));
		} else if(!(child1 instanceof EseqIR) && child2 instanceof EseqIR) {
			TempIR temp = new TempIR("OP_temp" + SyntaxIR.getFreshLabel());
			MoveIR move = new MoveIR(temp, (ExpressionIR) child1);
			OpIR opir = new OpIR(op, temp, ((EseqIR) child2).getE());
			ret.addChild(move);
			ret.addChild(((EseqIR) child2).getS());
			ret.addChild(((EseqIR) child2).getL());
			//ret.addChild(new LabelIR("_block"+getFreshLabel()+"_End_1"));
			ret.addChild(opir);
			/*if(child1 instanceof TempIR) {                      // DO WE NEED THIS PRETTY SURE NO
				ret.addChild(new MoveIR((TempIR) child1, temp));  // THE OLD CODE WASN'T EVEN AN ESEQ
			} */
		} else {
			ret.addChild(((EseqIR) child1).getS());
			TempIR temp = new TempIR("OP_temp" + getFreshLabel());
			MoveIR move1 = new MoveIR(temp, ((EseqIR) child1).getE());
			ret.addChild(move1);
			ret.addChild(((EseqIR) child2).getS());
			ret.addChild(((EseqIR) child1).getL());
			ret.addChild(((EseqIR) child2).getL());
			OpIR opir = new OpIR(op, temp, ((EseqIR) child2).getE());
			//ret.addChild(new LabelIR("_block"+getFreshLabel()+"_End_1"));
			ret.addChild(opir);
		}
		return ret;
	}
	
	public SyntaxIR foldCALL(){
		EseqIR eseq = new EseqIR();
		ExpressionIR op1 = (ExpressionIR) getChildren().get(0);
		ExpressionIR op2 = null;
		if(getChildren().size() > 1) {
			op2 = (ExpressionIR) getChildren().get(1);
		}
		
		if (op1 instanceof CallIR){
			TempIR temp = new TempIR("_call"+getFreshLabel());
			MoveIR move = new MoveIR(temp,op1.foldCALL());
			eseq.addChild(move);
			//eseq.addChild(new LabelIR("_block"+getFreshLabel()+"_End_1"));
			op1 = temp;
		}
		
		if (op2 instanceof CallIR){
			TempIR temp = new TempIR("_call"+getFreshLabel());
			MoveIR move = new MoveIR(temp,op2.foldCALL());
			eseq.addChild(move);
			//eseq.addChild(new LabelIR("_block"+getFreshLabel()+"_End_1"));
			op2 = temp;
		}
		OpIR op = null;
		if(op2 != null) {
			op = new OpIR(getOp(),op1,op2);
		} else {
			op = new OpIR(getOp(), op1);
		}
		eseq.addChild(op);
		return eseq;
	}
	
	/*
	 * pop r13
	 * pop r12
	 * op r13, r12
	 * push r12
	 * 
	 * if bool:
	 * pop r13
	 * pop r12
	 * cmp r13, r12
	 * jcc l0
	 * l1:
	 * mov 0 r12
	 * jmp end
	 * l0
	 * mov 1 r12
	 * end:
	 * push r12
	 */
	public ArrayList<Tile> generateTile(TempTable tTable) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		SyntaxIR left = (SyntaxIR) getChildren().get(0);
		result.addAll(left.generateTile(tTable));
		if(getChildren().size() > 1) {
			AddressingMode r13 = new AddressingMode("r13");
			AddressingMode r12 = new AddressingMode("r12");
			AddressingMode r14 = new AddressingMode("r14");
			SyntaxIR right = (SyntaxIR) getChildren().get(1);
			/*
			 * Special tiling for if we do
			 * mov(temp(a), plus(temp(b), times(const(), temp(c))))
			 */
			/*if(op.equals(opEnum.PLUS) && right instanceof OpIR && ((OpIR) right).getOp().equals(opEnum.TIMES)) {
				if(right.getChildren().get(0) instanceof ConstIR) {
					int val = ((ConstIR) right.getChildren().get(0)).getValue();
					if(val == 2 || val == 4 || val == 8 || val == 16) {
						result.addAll(((SyntaxIR) right.getChildren().get(1)).generateTile(tTable));
						Tile t1 = new Tile(1, "popq", new AddressingMode("r13"), null);
						Tile t2 = new Tile(1, "popq", new AddressingMode("r12"), null);
						Tile t3 = new Tile(1, "lea", new AddressingMode("r12", "r13", val), r14);
						Tile t4 = new Tile(1, "pushq", r14, null);
						result.add(t1);
						result.add(t2);
						result.add(t3);
						result.add(t4);
						return result;
					}
				}
			}*/
			Tile t1 = new Tile(1, "movq", r12, r13);
			//Tile t2 = new Tile(1, "popq", new AddressingMode("r12"), null);
			Tile t3 = new Tile(1, null, null, null);
			Tile t4 = new Tile(1, "movq", null,new AddressingMode("r12"));
			switch(op) {
			case NOT_EQUAL:
				t3.setOpCode("jnz");
				break;
			case LT:
				t3.setOpCode("jl");
				break;
			case OR:
				result.add(t1);
				result.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				result.addAll(right.generateTile(tTable));
				result.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				t3.setOpCode("or");
				t3.setSrcAddrMode(r13);
				t3.setDestAddrMode(r12);
				result.add(t3);
				return result;
			case GEQ:
				t3.setOpCode("jge");
				break;
			case EQUAL:
				t3.setOpCode("je");
				break;
			case LEQ:
				t3.setOpCode("jle");
				break;
			case GT:
				t3.setOpCode("jg");
				break;
			case AND:
				result.add(t1);
				result.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				result.addAll(right.generateTile(tTable));
				result.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				t3.setOpCode("and");
				t3.setDestAddrMode(r12);
				t3.setSrcAddrMode(r13);
				result.add(t3);
				return result;
			case DIVIDE:
				//move left side into r13
				result.add(t1);
				//move right side into r12
				result.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				result.addAll(right.generateTile(tTable));
				result.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				t3.setOpCode("idiv");
				//idiv divides RDX:RAX by the operand so we need to move r13 into rax and 0 into rdx
				//and give it the operand r12
				
				result.add(new Tile(1, "pushq", new AddressingMode("rdx"), null));
				result.add(new Tile(1, "pushq", new AddressingMode("rax"), null));
				//result.add(new Tile(1, "pushq", new AddressingMode("rdx"), null));
				result.add(new Tile(1, "movq",r13,new AddressingMode("rax")));
				//we need to sign extend r13 into rdx
				result.add(new Tile(1, "cmp", new AddressingMode(0),r13));
				//if r12 is less than 0 we move FFFFFFFFFFFFFFFF to rdx otherwise move zero
				int div = tTable.getNewJumpLabel();
				result.add(new Tile(1, "jl", new AddressingMode("div"+div,true),null));				
				//fall through to moving zero
				result.add(new Tile(1, "movq",new AddressingMode(0),new AddressingMode("rdx")));
				result.add(new Tile(1, "jmp", new AddressingMode("divEnd"+div, true), null));
				result.add(new Tile(1, "div"+div+":",null,null));
				result.add(new Tile(1, "movq",new AddressingMode(-1),new AddressingMode("rdx")));
				result.add(new Tile(1, "divEnd"+div+":",null,null));
				t3.setSrcAddrMode(r12);
				t3.setDestAddrMode(null);
				result.add(t3);
				//The result is put in RAX so we need to push that on the stack
				result.add(new Tile(1, "movq", new AddressingMode("r14"), new AddressingMode("rdx")));
				result.add(new Tile(1, "movq", new AddressingMode("rax"),r12));
				result.add(new Tile(1, "popq", new AddressingMode("rax"), null));
				result.add(new Tile(1, "popq", new AddressingMode("rdx"), null));
				return result;
			case MINUS:
				t3.setOpCode("subq");
				break;
			case PLUS:
				t3.setOpCode("add");
				break;
			case TIMES:
				t3.setOpCode("imul");
				break;
			case MODULO:
				//move left side into r13
				result.add(t1);
				//move right side into 
				result.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				result.addAll(right.generateTile(tTable));
				result.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				
				t3.setOpCode("idiv");
				result.add(new Tile(1, "pushq", new AddressingMode("rdx"), null));
				result.add(new Tile(1, "pushq", new AddressingMode("rax"), null));
				//idiv divides RDX:RAX by the operand so we need to move r13 into rax and 0 into rdx
				//and give it the operand r12
				result.add(new Tile(1, "movq",r13,new AddressingMode("rax")));
				//we need to sign extend r12 into rdx
				result.add(new Tile(1, "cmp", new AddressingMode(0),r13));
				//if r12 is less than 0 we move FFFFFFFFFFFFFFFF to rdx otherwise move zero
				div = tTable.getNewJumpLabel();
				result.add(new Tile(1, "jl", new AddressingMode("div"+div,true),null));				
				//fall through to moving zero
				result.add(new Tile(1, "movq",new AddressingMode(0),new AddressingMode("rdx")));
				result.add(new Tile(1, "jmp", new AddressingMode("divEnd"+div, true), null));
				result.add(new Tile(1, "div"+div+":",null,null));
				result.add(new Tile(1, "movq",new AddressingMode(-1),new AddressingMode("rdx")));
				result.add(new Tile(1, "divEnd"+div+":",null,null));
				t3.setSrcAddrMode(r12);
				t3.setDestAddrMode(null);
				result.add(t3);
				//The result is put in RDX so we need to push that on the stack
				result.add(new Tile(1, "movq", new AddressingMode("rdx"),r12));
				result.add(new Tile(1, "popq", new AddressingMode("rax"), null));
				result.add(new Tile(1, "popq", new AddressingMode("rdx"), null));
				return result;
			case LSHIFT:
				//left side is in r12
				t3.setOpCode("shl");
				t3.setSrcAddrMode(new AddressingMode(3));
				t3.setDestAddrMode(r12);
				result.add(t3);
				return result;
			case RSHIFT:	
				t3.setOpCode("shr");
				break;
			default:
				System.out.println("op is: " + op);
				t3.setOpCode("BROKE!");	
			}
			if(this.returnsBool()) {
				//move left side into r13
				result.add(t1);
				//move right side into r12
				result.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				result.addAll(right.generateTile(tTable));
				result.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				//switch these since we now swapped left and right sides
				Tile cmpTile = new Tile(1, "cmp", r12, r13);
				result.add(cmpTile);
				int lblID = tTable.getNewJumpLabel();
				Tile jmpTile = new Tile(1, t3.getOpcode(), null, null);
				jmpTile.setSrcAddrMode(new AddressingMode("_op" + lblID, true));
				result.add(jmpTile);
				result.add(new Tile(1, "movq", new AddressingMode(0), r12));
				result.add(new Tile(1, "jmp", new AddressingMode("_end" + lblID, true), null));
				result.add(new Tile(1, "_op" + lblID + ": ", null, null));
				result.add(new Tile(1, "movq", new AddressingMode(1), r12));
				result.add(new Tile(1, "_end" + lblID + ": ", null, null));
				//result is already put in r12 correctly nothing else to do
				return result;
			} else {
				//before left side was in r12 and right was in r13
				//now we need to move left into r13 since it has the
				result.add(t1);
				//right side is in r12
				result.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				result.addAll(right.generateTile(tTable));
				result.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				//switched these to be correct for switched left and right side
				t3.setSrcAddrMode(new AddressingMode("r12"));
				t3.setDestAddrMode(new AddressingMode("r13"));
				result.add(t3);
				//move r13 into r12
				t4.setSrcAddrMode(r13);
				result.add(t4);
				return result;
			}
		// it's a '!a' or a '-a'
		} else {
			//only value is already in r12
			Tile t3 = new Tile();
			t3.setScore(1);
			switch(op) {
			case UNARY_MINUS:
				t3.setOpCode("neg");
				break;
			case NOT:
				t3.setOpCode("btc");
				t3.setSrcAddrMode(new AddressingMode(0));
				t3.setDestAddrMode(new AddressingMode("r12"));
				result.add(t3);
				//result already gets put in r12 nothing else to do
				//System.out.println(result);
				return result;
			}
			//System.out.println("shouldnt get here");
			t3.setSrcAddrMode(new AddressingMode("r12"));
			result.add(t3);
			//result is already in r12 nothing else to do
			return result;
		}
		
	}
	
	public ArrayList<Tile> generateTileWindows(TempTable tTable) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		SyntaxIR left = (SyntaxIR) getChildren().get(0);
		result.addAll(left.generateTileWindows(tTable));
		if(getChildren().size() > 1) {
			AddressingMode r13 = new AddressingMode("r13");
			AddressingMode r12 = new AddressingMode("r12");
			AddressingMode r14 = new AddressingMode("r14");
			SyntaxIR right = (SyntaxIR) getChildren().get(1);
			/*
			 * Special tiling for if we do
			 * mov(temp(a), plus(temp(b), times(const(), temp(c))))
			 */
			/*if(op.equals(opEnum.PLUS) && right instanceof OpIR && ((OpIR) right).getOp().equals(opEnum.TIMES)) {
				if(right.getChildren().get(0) instanceof ConstIR) {
					int val = ((ConstIR) right.getChildren().get(0)).getValue();
					if(val == 2 || val == 4 || val == 8 || val == 16) {
						result.addAll(((SyntaxIR) right.getChildren().get(1)).generateTile(tTable));
						Tile t1 = new Tile(1, "popq", new AddressingMode("r13"), null);
						Tile t2 = new Tile(1, "popq", new AddressingMode("r12"), null);
						Tile t3 = new Tile(1, "lea", new AddressingMode("r12", "r13", val), r14);
						Tile t4 = new Tile(1, "pushq", r14, null);
						result.add(t1);
						result.add(t2);
						result.add(t3);
						result.add(t4);
						return result;
					}
				}
			}*/
			Tile t1 = new Tile(1, "movq", r12, r13);
			//Tile t2 = new Tile(1, "popq", new AddressingMode("r12"), null);
			Tile t3 = new Tile(1, null, null, null);
			Tile t4 = new Tile(1, "movq", null,new AddressingMode("r12"));
			switch(op) {
			case NOT_EQUAL:
				t3.setOpCode("jnz");
				break;
			case LT:
				t3.setOpCode("jl");
				break;
			case OR:
				result.add(t1);
				result.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				result.addAll(right.generateTileWindows(tTable));
				result.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				t3.setOpCode("or");
				t3.setSrcAddrMode(r13);
				t3.setDestAddrMode(r12);
				result.add(t3);
				return result;
			case GEQ:
				t3.setOpCode("jge");
				break;
			case EQUAL:
				t3.setOpCode("je");
				break;
			case LEQ:
				t3.setOpCode("jle");
				break;
			case GT:
				t3.setOpCode("jg");
				break;
			case AND:
				result.add(t1);
				result.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				result.addAll(right.generateTileWindows(tTable));
				result.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				t3.setOpCode("and");
				t3.setDestAddrMode(r12);
				t3.setSrcAddrMode(r13);
				result.add(t3);
				return result;
			case DIVIDE:
				//move left side into r13
				result.add(t1);
				//move right side into r12
				result.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				result.addAll(right.generateTileWindows(tTable));
				result.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				t3.setOpCode("idiv");
				//idiv divides RDX:RAX by the operand so we need to move r13 into rax and 0 into rdx
				//and give it the operand r12
				
				result.add(new Tile(1, "pushq", new AddressingMode("rdx"), null));
				result.add(new Tile(1, "pushq", new AddressingMode("rax"), null));
				//result.add(new Tile(1, "pushq", new AddressingMode("rdx"), null));
				result.add(new Tile(1, "movq",r13,new AddressingMode("rax")));
				//we need to sign extend r13 into rdx
				result.add(new Tile(1, "cmp", new AddressingMode(0),r13));
				//if r12 is less than 0 we move FFFFFFFFFFFFFFFF to rdx otherwise move zero
				int div = tTable.getNewJumpLabel();
				result.add(new Tile(1, "jl", new AddressingMode("div"+div,true),null));				
				//fall through to moving zero
				result.add(new Tile(1, "movq",new AddressingMode(0),new AddressingMode("rdx")));
				result.add(new Tile(1, "jmp", new AddressingMode("divEnd"+div, true), null));
				result.add(new Tile(1, "div"+div+":",null,null));
				result.add(new Tile(1, "movq",new AddressingMode(-1),new AddressingMode("rdx")));
				result.add(new Tile(1, "divEnd"+div+":",null,null));
				t3.setSrcAddrMode(r12);
				t3.setDestAddrMode(null);
				result.add(t3);
				//The result is put in RAX so we need to push that on the stack
				result.add(new Tile(1, "movq", new AddressingMode("r14"), new AddressingMode("rdx")));
				result.add(new Tile(1, "movq", new AddressingMode("rax"),r12));
				result.add(new Tile(1, "popq", new AddressingMode("rax"), null));
				result.add(new Tile(1, "popq", new AddressingMode("rdx"), null));
				return result;
			case MINUS:
				t3.setOpCode("subq");
				break;
			case PLUS:
				t3.setOpCode("add");
				break;
			case TIMES:
				t3.setOpCode("imul");
				break;
			case MODULO:
				//move left side into r13
				result.add(t1);
				//move right side into 
				result.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				result.addAll(right.generateTileWindows(tTable));
				result.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				
				t3.setOpCode("idiv");
				result.add(new Tile(1, "pushq", new AddressingMode("rdx"), null));
				result.add(new Tile(1, "pushq", new AddressingMode("rax"), null));
				//idiv divides RDX:RAX by the operand so we need to move r13 into rax and 0 into rdx
				//and give it the operand r12
				result.add(new Tile(1, "movq",r13,new AddressingMode("rax")));
				//we need to sign extend r12 into rdx
				result.add(new Tile(1, "cmp", new AddressingMode(0),r13));
				//if r12 is less than 0 we move FFFFFFFFFFFFFFFF to rdx otherwise move zero
				div = tTable.getNewJumpLabel();
				result.add(new Tile(1, "jl", new AddressingMode("div"+div,true),null));				
				//fall through to moving zero
				result.add(new Tile(1, "movq",new AddressingMode(0),new AddressingMode("rdx")));
				result.add(new Tile(1, "jmp", new AddressingMode("divEnd"+div, true), null));
				result.add(new Tile(1, "div"+div+":",null,null));
				result.add(new Tile(1, "movq",new AddressingMode(-1),new AddressingMode("rdx")));
				result.add(new Tile(1, "divEnd"+div+":",null,null));
				t3.setSrcAddrMode(r12);
				t3.setDestAddrMode(null);
				result.add(t3);
				//The result is put in RDX so we need to push that on the stack
				result.add(new Tile(1, "movq", new AddressingMode("rdx"),r12));
				result.add(new Tile(1, "popq", new AddressingMode("rax"), null));
				result.add(new Tile(1, "popq", new AddressingMode("rdx"), null));
				return result;
			case LSHIFT:
				//left side is in r12
				t3.setOpCode("shl");
				t3.setSrcAddrMode(new AddressingMode(3));
				t3.setDestAddrMode(r12);
				result.add(t3);
				return result;
			case RSHIFT:	
				t3.setOpCode("shr");
				break;
			default:
				System.out.println("op is: " + op);
				t3.setOpCode("BROKE!");	
			}
			if(this.returnsBool()) {
				//move left side into r13
				result.add(t1);
				//move right side into r12
				result.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				result.addAll(right.generateTileWindows(tTable));
				result.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				//switch these since we now swapped left and right sides
				Tile cmpTile = new Tile(1, "cmp", r12, r13);
				result.add(cmpTile);
				int lblID = tTable.getNewJumpLabel();
				Tile jmpTile = new Tile(1, t3.getOpcode(), null, null);
				jmpTile.setSrcAddrMode(new AddressingMode("_op" + lblID, true));
				result.add(jmpTile);
				result.add(new Tile(1, "movq", new AddressingMode(0), r12));
				result.add(new Tile(1, "jmp", new AddressingMode("_end" + lblID, true), null));
				result.add(new Tile(1, "_op" + lblID + ": ", null, null));
				result.add(new Tile(1, "movq", new AddressingMode(1), r12));
				result.add(new Tile(1, "_end" + lblID + ": ", null, null));
				//result is already put in r12 correctly nothing else to do
				return result;
			} else {
				//before left side was in r12 and right was in r13
				//now we need to move left into r13 since it has the
				result.add(t1);
				//right side is in r12
				result.add(new Tile(1, "pushq", new AddressingMode("r13"), null));
				result.addAll(right.generateTileWindows(tTable));
				result.add(new Tile(1, "popq", new AddressingMode("r13"), null));
				//switched these to be correct for switched left and right side
				t3.setSrcAddrMode(new AddressingMode("r12"));
				t3.setDestAddrMode(new AddressingMode("r13"));
				result.add(t3);
				//move r13 into r12
				t4.setSrcAddrMode(r13);
				result.add(t4);
				return result;
			}
		// it's a '!a' or a '-a'
		} else {
			//only value is already in r12
			Tile t3 = new Tile();
			t3.setScore(1);
			switch(op) {
			case UNARY_MINUS:
				t3.setOpCode("neg");
				break;
			case NOT:
				t3.setOpCode("btc");
				t3.setSrcAddrMode(new AddressingMode(0));
				t3.setDestAddrMode(new AddressingMode("r12"));
				result.add(t3);
				//result already gets put in r12 nothing else to do
				//System.out.println(result);
				return result;
			}
			//System.out.println("shouldnt get here");
			t3.setSrcAddrMode(new AddressingMode("r12"));
			result.add(t3);
			//result is already in r12 nothing else to do
			return result;
		}
		
	}
	
	public String prettyPrint() {
		String ret = "";
		if(getChildren().size() == 1) {
			ret = this.opToPrettyString() +((SyntaxIR) getChildren().get(0)).prettyPrint();
		} else {
			ret = "("+((SyntaxIR) getChildren().get(0)).prettyPrint() + " " + this.opToPrettyString() + " " + ((SyntaxIR) getChildren().get(1)).prettyPrint()+")";
		}
		return ret;
	}

	@Override
	public int compareTo(Object arg0) {
		Integer arg = arg0.hashCode();
		Integer hash = hashCode();
		return(arg.compareTo(hash));
	}
	
	public int hashCode() {
		return prettyPrint().length();
	}
}