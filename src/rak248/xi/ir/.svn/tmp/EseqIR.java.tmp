package rak248.xi.ir;

import java.util.ArrayList;

import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;

public class EseqIR extends ExpressionIR {

	private SeqIR S;
	private SeqIR L;
	private ExpressionIR E;
	
	public EseqIR(String string) {
		setLabel("ESEQ: "+string);
	}
	
	public EseqIR() {
		setLabel("ESEQ");
	}
	
	public SeqIR getS() {
		if(S != null) {
			return S;
		}
		SeqIR seq = new SeqIR();
		L = new SeqIR();
		ArrayList<VisualizableIRNode> children = getChildren();
		int size = children.size();
		int i = 0;
		for(i = 0; i < size - 1; i++) {
			SyntaxIR child = (SyntaxIR) children.get(i);
			if(child instanceof LabelIR && ((LabelIR) child).getName().startsWith("_block")) {
				break;
			}
			seq.addChild(child);
		}
		for( ; i < size - 1; i++) {
			L.addChild((SyntaxIR) children.get(i));
		}
		S = seq;
		return seq;
	}
	
	public void addChild(SeqIR s) {
		if(s instanceof SeqIR) {
			extend(s);
		}
	}
	
	public SeqIR getL() {
		if(L != null) {
			return L;
		}
		getS();
		return L;
	}
	
	public ExpressionIR getE() {
		if(E != null) {
			return E;
		}
		ArrayList<VisualizableIRNode> children = getChildren();
		if(children.size() == 0) {
			return null;
		}
		if((children.get(children.size() - 1)) instanceof ExpIR) {
			E = (ExpressionIR) ((SyntaxIR) children.get(children.size() - 1)).getChildren().get(0);
			return E;
		}
		//System.out.println("last: " + this);
		E = (ExpressionIR) children.get(children.size() - 1);
		return E;
	}
	
	public void extend(SeqIR s) {
		for(VisualizableIRNode child : s.getChildren()) {
			addChild((SyntaxIR) child);
		}
	}

	public SyntaxIR foldESEQ() {
		ExpressionIR es = (ExpressionIR) getE().foldESEQ();
		if(es instanceof EseqIR) {
			EseqIR e = (EseqIR) es;
			EseqIR newEseq = new EseqIR();
			
			newEseq.addChild(this.getS().foldESEQ());
			newEseq.addChild(e.getS().foldESEQ());
			
			newEseq.extend(this.getL());
			newEseq.extend(e.getL());
			newEseq.addChild(e.getE());
			return newEseq;
		} else {
			EseqIR newEseq = new EseqIR();
			newEseq.addChild(getS().foldESEQ());
			newEseq.addChild(getL());
			newEseq.addChild(es);
			return newEseq;
		}
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
}
