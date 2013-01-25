package rak248.xi.ir;

import java.util.ArrayList;
import java.util.List;

import rak248.asm.AddressingMode;
import rak248.asm.Tile;
import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.OpIR.opEnum;

public class Optimize {
	
	// returns a SeqIR where none of the children or nested children are SEQ's
	public static SyntaxIR foldSEQ(SyntaxIR s) {
		ArrayList<VisualizableIRNode> newChildren = new ArrayList<VisualizableIRNode>();
		for(VisualizableIRNode c : s.getChildren()) {
			SyntaxIR child = foldSEQ((SyntaxIR) c);
			if(child instanceof SeqIR) {
				for(VisualizableIRNode newChild : child.getChildren()) {
					newChildren.add(newChild);
				}
			} else {
				newChildren.add(child);
			}
		}
		s.setChildren(newChildren);
		return s;
	}
	
	public static void foldMem(SyntaxIR comp){
		for(VisualizableIRNode node : comp.getChildren()) {
			foldMem((SyntaxIR)node);
			if(node instanceof MemIR){
				MemIR mem = (MemIR) node;
				//See if the memIR's only child is a MemIR
				if(mem.getChildren().get(0) instanceof MemIR){
					EseqIR eseq = new EseqIR();
					TempIR temp = new TempIR("_memfold"+SyntaxIR.getFreshLabel());
					MoveIR move = new MoveIR(temp,(SyntaxIR) (mem.getChildren().get(0)));
					eseq.addChild(move);
					eseq.addChild(temp);
					mem.getChildren().set(0, eseq);
				}
			}
		}
	}
	
	public static void foldOp(SyntaxIR comp){
		ArrayList<VisualizableIRNode> newChildren = new ArrayList<VisualizableIRNode>();
		for (VisualizableIRNode node: comp.getChildren()){
			foldOp((SyntaxIR) node);
			if (node instanceof OpIR){
				if (((OpIR) node).getChildren().size() == 1){
					SyntaxIR child = (SyntaxIR) ((SyntaxIR) node).getChildren().get(0);
					if (child instanceof OpIR){
						EseqIR eseq = new EseqIR();
						TempIR temp = new TempIR("_opfold"+SyntaxIR.getFreshLabel());
						MoveIR move = new MoveIR(temp,child);
						eseq.addChild(move);
						eseq.addChild(temp);
						((SyntaxIR) node).getChildren().set(0, eseq);
						newChildren.add(node);
					}
					else{
						newChildren.add(node);
					}
				}
				//opIR has two children
				else {
					SyntaxIR child1 = (SyntaxIR) ((SyntaxIR) node).getChildren().get(0);
					SyntaxIR child2 = (SyntaxIR) ((SyntaxIR) node).getChildren().get(1);
					if (child1 instanceof OpIR){
						EseqIR newChild1 = new EseqIR();
						TempIR temp = new TempIR("_opfold"+SyntaxIR.getFreshLabel());
						MoveIR move = new MoveIR(temp,child1);
						newChild1.addChild(move);
						newChild1.addChild(temp);
						((SyntaxIR) node).getChildren().set(0, newChild1);
					}
					if (child2 instanceof OpIR){
						EseqIR newChild2 = new EseqIR();
						TempIR temp = new TempIR("_opfold"+SyntaxIR.getFreshLabel());
						MoveIR move = new MoveIR(temp,child2);
						newChild2.addChild(move);
						newChild2.addChild(temp);
						((SyntaxIR) node).getChildren().set(1, newChild2);
					}
					newChildren.add(node);
				}
			}
			else{
				newChildren.add(node);
			}
		}
		comp.setChildren(newChildren);
	}
	
	public static void foldCJump(SyntaxIR s){
		SeqIR ret = new SeqIR(s.label()); 
		for (int i = 0; i< s.getChildren().size(); i++){
			VisualizableIRNode node = s.getChildren().get(i);
			if (node instanceof CJumpIR && !((CJumpIR)node).isWhile){
				CJumpIR cjump = (CJumpIR) node;
				//Now we need to find four basic blocks
				//The block that starts (and ends) with the CJUMP
				SeqIR cJumpBlock = new SeqIR("cJumpBlock");
				cJumpBlock.addIR(cjump);
				//The block that starts at the true label which will be immediately after the CJUMP
				SeqIR trueBlock = new SeqIR("trueBlock");
				//the second child is the true label
				trueBlock = findBasicBlock(s,i+1);
				//And finally the block that starts at the false label
				SeqIR falseBlock = new SeqIR("falseBlock");
				//the third child is the false label
				falseBlock = findBasicBlock(s,i+1+trueBlock.getChildren().size());
				//System.out.println("true block: " + trueBlock);
				//System.out.println("false block: " + falseBlock);
				//Now we need to rearrange these blocks such that they are in the order
				// cjumpBlock falseBlock trueBlock
				//   if the true block ends in a jump we can remove it but we must add
				//   the same jump to the end of the false block.
				// if the true block ends with a label then we know that there is no
				// false (else) block so we can simply invert the condition
				
				//This should find the first thing after the end of the trueBlock
				SyntaxIR nextLabel = (SyntaxIR) s.getChildren().get(i+trueBlock.getChildren().size());
				//This is the end of the trueBlock
				SyntaxIR endOfTrue = (SyntaxIR) trueBlock.getChildren().get(trueBlock.getChildren().size()-1);
				//System.out.println(endOfTrue);
				//System.out.println("cjump before end of true: " + cjump);
				//System.out.println("end of true: " + endOfTrue);
				if(endOfTrue instanceof JumpIR){
					JumpIR end = (JumpIR) endOfTrue;
					//trueBlock.getChildren().remove(trueBlock.getChildren().size()-1);
					//falseBlock.addChild(end);
					cjump.getChildren().set(0, new OpIR(opEnum.NOT,(ExpressionIR) cjump.getChildren().get(0)));
					String k = cjump.getlt();
					cjump.setlt(cjump.getlf());
					cjump.setlf(k);
					//ret.addIR(cjump);
					cjump.updateLabel();
					//Add the cjump IR object because we are not changing it
					ret.addChild(cjump);
					//Add all false block stuff to the return list after the cjump
					for(VisualizableIRNode child : trueBlock.getChildren()){
						ret.addIR((SyntaxIR) child);
					}
					for(VisualizableIRNode child : falseBlock.getChildren()){
						ret.addIR((SyntaxIR) child);
					}
					
				}else{
					System.out.println("This should never execute!");
					//System.out.println("cjump before: " + cjump.label());
					//System.out.println("cjump after: " + cjump.label());
					//System.out.println("false block: " + falseBlock);
					//Since the falseBlock is now the true block with add the trueBlock first
					for(VisualizableIRNode child : trueBlock.getChildren()){
						ret.addIR((SyntaxIR) child);
					}
					for(VisualizableIRNode child : falseBlock.getChildren()){
						ret.addIR((SyntaxIR) child);
					}
				}
				//before we end this iteration of the loop we have to make sure that 
				// i is set correctly so that we don't process nodes more than once
				i+=trueBlock.getChildren().size()+falseBlock.getChildren().size();	
			}
			else if (node instanceof CJumpIR && ((CJumpIR)node).isWhile){
				CJumpIR cjump = (CJumpIR) node;
				ExpressionIR condition = (ExpressionIR) cjump.getChildren().get(0);
				ExpressionIR notCondition = new OpIR(opEnum.NOT, condition);
				String lt = cjump.getlt();
				String lf = cjump.getlf();
				cjump = new CJumpIR(notCondition,lf,lt,false,true);
				ret.addIR(cjump);
			}
			else{
				ret.addIR((SyntaxIR) node);
			}
		}
		s.setChildren(ret.getChildren());
	}
	
	public static SeqIR findBasicBlock(VisualizableIRNode s, int index){
		SyntaxIR seq = (SyntaxIR) s;
		SeqIR ret = new SeqIR("BasicBlockFinder");
		for(int i = index;i<seq.getChildren().size();i++){
			SyntaxIR node = (SyntaxIR) seq.getChildren().get(i);
			//System.out.println(node);
			if(node instanceof JumpIR){
				ret.addIR(node);
				return ret;
			}
			else if(node instanceof CJumpIR) {
				return ret;
			}else if(node instanceof LabelIR && i != index){
				return ret;
			//}else if(node instanceof ReturnIR){ // ADD BACK IF GET RID OF DEAD JUMP AFTER RETURNS
			//	return ret;
		    }else{//not a label or jump so the block isn't over yet
				ret.addIR(node);
			}
		}
		return ret;
	}
	
	//fold over ops with consts
	public static void foldConst(SyntaxIR node){
		ArrayList<VisualizableIRNode> newchildren = new ArrayList<VisualizableIRNode>();
		for (VisualizableIRNode n: node.getChildren()){
			foldConst((SyntaxIR) n);
			if (n instanceof OpIR){
				ArrayList<VisualizableIRNode> children = ((SyntaxIR) n).getChildren();
				if (children.size() == 1){
					SyntaxIR first = (SyntaxIR) children.get(0);
					if (first instanceof ConstIR){
						long value = newConstUnary(((OpIR) n).getOp(),((ConstIR) first).getValue());
						ConstIR cons = new ConstIR(value);
						newchildren.add(cons);
					}
					else{
						newchildren.add(n);
					}
				}
				else if (children.size() == 2){
					SyntaxIR first = (SyntaxIR) children.get(0);
					SyntaxIR second = (SyntaxIR) children.get(1);
					if ((first instanceof ConstIR)&&(second instanceof ConstIR)){
						//System.out.println(node);
						long value = newConstBinary(((OpIR) n).getOp(),((ConstIR) first).getValue(),((ConstIR) second).getValue());
						ConstIR cons = new ConstIR(value);
						//System.out.println(cons);
						newchildren.add(cons);
					}
					else if (first instanceof ConstIR || second instanceof ConstIR){
						newchildren.add(identity((OpIR) n));
					}
					else{
						newchildren.add(n);
					}
				}
				else{
					newchildren.add(n);
				}
			}
			/*else if (n instanceof CJumpIR){
				CJumpIR cjump = (CJumpIR) n;
				if (cjump.isWhile || !cjump.hasElse){ //whiles and single if statements without else
					ExpressionIR exp = (ExpressionIR) cjump.getChildren().get(0);
					if (exp instanceof ConstIR){
						if ( ((ConstIR) exp).getValue() != 0 ){
							newchildren.add( new JumpIR(new NameIR(cjump.getlt())) );
						}
						else{
							newchildren.add( new JumpIR(new NameIR(cjump.getlf())) );
						}
					}
					else{
						newchildren.add(n);
					}
				}
				else { //has an else
					ExpressionIR exp = (ExpressionIR) cjump.getChildren().get(0);
					if (exp instanceof ConstIR){
						if ( ((ConstIR) exp).getValue() != 0 ){
							newchildren.add( new JumpIR(new NameIR(cjump.getlt())) );
						}
						else{ //complicated
							newchildren.add( new JumpIR(new NameIR(cjump.getlf())) );
						}
					}
					else{
						newchildren.add(n);
					}
				}
			}*/
			else{
				newchildren.add(n);
			}
		
		}
		((SyntaxIR) node).setChildren(newchildren);
	}

		
	//n will be an opIR 
	private static VisualizableIRNode identity(OpIR n) {
		ExpressionIR e = null;
		ConstIR cons = null;
		if (n.getChildren().get(0) instanceof ConstIR){
			cons = (ConstIR) n.getChildren().get(0);
		}
		else{
			e = (ExpressionIR) n.getChildren().get(0);
		}
		if (n.getChildren().get(1) instanceof ConstIR){
			cons = (ConstIR) n.getChildren().get(1);
		}
		else{
			e = (ExpressionIR) n.getChildren().get(1);
		}
		
		switch (n.getOp()){
		case PLUS: 
			if (cons.getValue() == 0){
				return e;
			}
			break;
		case TIMES:
			if (cons.getValue() == 0){
				return cons;
			}
			else if (cons.getValue() == 1){
				return e;
			}
			break;
		case AND:
			if (cons.getValue() != 0){
				return e;
			}
			else if (cons.getValue() == 0){
				return cons;
			}
			break;
		case OR:
			if (cons.getValue() == 0){
				return e;
			}
			else if (cons.getValue() == 1){
				return cons;
			}
		}
		
		
		return n;
	}


	private static long newConstUnary(opEnum op, long l) {
		switch (op){
		case UNARY_MINUS:
			return -l;
		case NOT:
			if (l > 0) return 0;
			else return 1;
		default:
			throw new RuntimeException("OP "+ op + " can't be conputed here as an unary op");
		}
	}


	private static long newConstBinary(opEnum op, long l, long m) {
		switch (op){
		case NOT_EQUAL:
			if (l != m) return 1;
			else return 0;
		case LT:
			if (l < m) return 1;
			else return 0;
		case OR:
			if (l>0 || m>0) return 1;
			else return 0;
		case GEQ:
			if (l >= m) return 1;
			else return 0;
		case EQUAL:
			if (l == m) return 1;
			else return 0;
		case LEQ:
			if (l <= m) return 1;
			else return 0;
		case GT:
			if (l > m) return 1;
			else return 0;
		case AND:
			if (l>0 && m>0) return 1;
			else return 0;
		case DIVIDE:
			return l/m;
		case MINUS:
			return l-m;
		case PLUS:
			return l+m;
		case TIMES:
			return l*m;
		case LSHIFT:
			return l<<m;
		case RSHIFT:	
			return l>>m;
		case MODULO:
			return l%m;
		default:
			throw new RuntimeException("OP "+ op + " can't be conputed here as a binary op");	
		}
	}
	
	/*
	 * replaces:
	 * push r12 : b
	 * pop r13 : a
	 * with
	 * mov r12, r13
	 */
	public static void foldPushPop(ArrayList<Tile> sequence) {
		for(int i = 1; i < sequence.size(); i++) {
			Tile a = sequence.get(i);
			Tile b = sequence.get(i-1);
			if(a.getOpcode().equals("popq") && b.getOpcode().equals("pushq")) {
				Tile newTile = new Tile(1, "movq", b.getSrcAddrMode(), a.getSrcAddrMode());
				sequence.set(i, newTile);
				sequence.remove(i-1);
			}
		}
	}
	
	/*
	 * Replaces mov r12, r12
	 * with nothing
	 * 
	 * replaces
	 * mov r12, r13 : b
	 * mov r13, r14 : a
	 * with
	 * mov r12, r14
	 */
	public static void foldMov(ArrayList<Tile> sequence) {
		for(int i = 0; i < sequence.size(); i ++) {
			Tile a = sequence.get(i);
			AddressingMode src = a.getSrcAddrMode();
			AddressingMode dst = a.getDestAddrMode();
			if(src != null & dst != null && src.getMemAccess() == true && dst.getMemAccess() == true) {
				// do nothing
			}
			else if(src != null & dst != null && src.equals(dst)) {
				sequence.remove(i);
			}
		}
		for(int i = 1; i < sequence.size(); i ++) {
			Tile a = sequence.get(i);
			Tile b = sequence.get(i-1);
			AddressingMode asrc = a.getSrcAddrMode();
			AddressingMode adest = a.getDestAddrMode();
			AddressingMode bsrc = b.getSrcAddrMode();
			AddressingMode bdest = b.getDestAddrMode();
			Boolean notNull = asrc != null && adest != null && bsrc != null && bdest != null;
			if(notNull && asrc.getMemAccess() == true && bdest.getMemAccess() == true) {
				// do nothing
			}
			else if(notNull) {
				if(bdest.equals(asrc) && a.getOpcode().equals("movq") && b.getOpcode().equals("movq")) {
					Tile newTile = new Tile(1, "movq", bsrc, adest);
					sequence.set(i, newTile);
					sequence.remove(i-1);
				}
			}
		}
	}


	public static void removeMove(CompUnitIR compUnitIR) {
		ArrayList<VisualizableIRNode> children = new ArrayList<VisualizableIRNode>();
		for (VisualizableIRNode v: compUnitIR.getChildren()){
			if (v instanceof MoveIR){
				ExpressionIR child1 = (ExpressionIR) ((SyntaxIR) v).getChildren().get(0);
				ExpressionIR child2 = (ExpressionIR) ((SyntaxIR) v).getChildren().get(1);
				if (child1 instanceof TempIR && child2 instanceof TempIR){
					if (!((TempIR) child1).getId().equals(((TempIR) child2).getId())){
						children.add(v);
					}
				}
				else{
					children.add(v);
				}
			}
			else{
				children.add(v);
			}
		}
		compUnitIR.setChildren(children); 
		
	}
	
	

}
