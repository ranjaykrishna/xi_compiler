package rak248.xi.ir;

import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;

public class CJumpSquash {
	
	public static void foldCJump(SyntaxIR s){
		SeqIR ret = new SeqIR(s.label()); 
		for (int i = 0; i< s.getChildren().size(); i++){
			VisualizableIRNode node = s.getChildren().get(i);
			if (node instanceof CJumpIR){
				CJumpIR cjump = (CJumpIR) node;
				if (cjump.isWhile){
					foldCJump((SyntaxIR) node);
					ret.addChild((SyntaxIR) node);
				}
				else if (cjump.hasElse){
					
				}
				else{
					SyntaxIR lt = (SyntaxIR) s.getChildren().get(i+1);
					SyntaxIR tr = (SyntaxIR) s.getChildren().get(i+2);
					SyntaxIR jp = (SyntaxIR) s.getChildren().get(i+3);
					
					foldCJump(tr);
					//remove the true part
					s.getChildren().remove(i+1);
					s.getChildren().remove(i+1);
					s.getChildren().remove(i+1);
					SeqIR seq = new SeqIR();
					seq.addChild(lt);
					seq.addChild(tr);
					seq.addChild(jp);
					insertAfterBasicBlock(s,i+1,seq);
				}
			}
			else{
				foldCJump((SyntaxIR) node);
				ret.addChild((SyntaxIR) node);
			}
		}
		s.setChildren(ret.getChildren());
	}
	
	public static void insertAfterBasicBlock(SyntaxIR s, int i, SeqIR seq){
		
	}
}
