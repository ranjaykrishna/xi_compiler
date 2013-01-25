package rak248.opt;

import java.util.HashSet;

import rak248.xi.ir.TempIR;

public class Copy {
	
	private TempIR dest;
	private TempIR src;
	
	public Copy(TempIR d, TempIR s){
		setDest(d);
		setSrc(s);
	}

	public void setDest(TempIR dest) {
		this.dest = dest;
	}

	public TempIR getDest() {
		return dest;
	}

	public void setSrc(TempIR src) {
		this.src = src;
	}

	public TempIR getSrc() {
		return src;
	}

	public static String prettyPrint(HashSet<Copy> gen) {
		String ret = "[";
		for (Copy copy: gen){
			ret += copy.getDest().prettyPrint() + "=" + copy.getSrc().prettyPrint() + ", ";
		}
		return ret+"]";
	}
	
	public int hashcode(){
		return dest.hashCode()^src.hashCode();
	}
	
	public boolean cequals(TempIR temp){
		if (dest.getId().equals(temp.getId()) || src.getId().equals(temp.getId())){
			return true;
		}
		return false;
	}
}