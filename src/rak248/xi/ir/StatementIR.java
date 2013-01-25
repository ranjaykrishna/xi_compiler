package rak248.xi.ir;

import rak248.xi.SyntaxIR;

public class StatementIR extends SyntaxIR{

	private boolean isCond;
	
	public void setCond(boolean val) {
		isCond = val;
	}
	
	public boolean getCond() {
		return isCond;
	}
}
