package rak248.opt;

import java.util.HashSet;

import rak248.xi.ir.ConstIR;
import rak248.xi.ir.TempIR;

public class Const {
	private TempIR temp;
	private ConstIR cons;
	
	public Const(TempIR d, ConstIR s){
		temp = d;
		cons = s;
	}

	public void setTemp(TempIR dest) {
		temp = dest;
	}

	public TempIR getTemp() {
		return temp;
	}

	public void setCons(ConstIR src) {
		cons = src;
	}

	public ConstIR getCons() {
		return cons;
	}

	public static String prettyPrint(HashSet<Const> gen) {
		String ret = "[";
		for (Const copy: gen){
			ret += copy.getTemp().prettyPrint() + "=" + copy.getCons().prettyPrint() + ", ";
		}
		return ret+"]";
	}
	
	public int hashcode(){
		return temp.getId().hashCode();
	}
	
	public boolean cequals(TempIR temp){
		return temp.getId().equals(temp.getId());
	}
}
