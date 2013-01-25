package rak248.opt;

import java.util.ArrayList;
import java.util.HashSet;

import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.CompUnitIR;
import rak248.xi.ir.SeqIR;

public class IRGen {
	private CompUnitIR program;
	
	public IRGen(ArrayList<CFGNode> roots) {
		program = new CompUnitIR();
		for(CFGNode root : roots) {
			SeqIR seq = new SeqIR();
			HashSet<CFGNode> doneList = new HashSet<CFGNode>();
			convertToIR(root, seq, doneList);
			program.addChildren(seq);
		}
	}

	private void convertToIR(CFGNode root, SeqIR seq, HashSet<CFGNode> doneList) {
		if(doneList.contains(root)) {
			return;
		}
		doneList.add(root);
		seq.addChild(root.getIrnode());
		//no children
		if(root.getChild2() == null && root.getChild1() == null) {
			return;
		}
		//1 child
		else if(root.getChild2() == null && root.getChild1() != null) {
			convertToIR(root.getChild1(), seq, doneList);
		}
		//2 children
		else {
			SeqIR temp = new SeqIR();
			convertToIR(root.getChild2(), temp, doneList);
			for(VisualizableIRNode c : temp.getChildren()) {
				seq.addChild((SyntaxIR) c);
			}
			convertToIR(root.getChild1(), seq, doneList);
		}
	}
	
	public CompUnitIR getIR() {
		return program;
	}
}
