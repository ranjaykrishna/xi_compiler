package rak248.opt;

import rak248.xi.SyntaxIR;
import rak248.xi.ir.TempIR;

public class Phi {
	private TempIR out;
	private TempIR in1;
	private TempIR in2;
	
	public Phi(TempIR in1, TempIR in2) {
		this.in1 = in1;
		this.in2 = in2;
		String name = in1.getId().substring(in1.getId().indexOf("_"), in1.getId().indexOf("@"));
		TempIR t = new TempIR(name+"@"+SyntaxIR.getFreshLabel());
		out = t;
	}
	
	public String toString() {
		String ret = out.prettyPrint() + " = Phi(" + in1.prettyPrint() + ", " + in2.prettyPrint() + ")";
		return ret;
	}
	
	public TempIR getIn1() {
		return in1;
	}
	
	public TempIR getIn2() {
		return in2;
	}
	
	public TempIR getOut() {
		return out;
	}
}