package rak248.xi.ir;

import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;

public class ExpressionIR extends SyntaxIR{
	
	public ExpressionIR(){
		setLabel("DEADBEEF");
	}
	
	public boolean contains(TempIR x) {
		for(VisualizableIRNode child : getChildren()) {
			if(child.equals(x)) {
				return true;
			} else
				return ((ExpressionIR) child).contains(x);
		}
		return false;
	}
}
