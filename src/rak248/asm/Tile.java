package rak248.asm;

import java.util.ArrayList;

public class Tile {
	private int score;
	private AddressingMode srcAddrMode;
	private AddressingMode destAddrMode;
	private String opcode;
	
	public Tile() {
		
	}
	
	public Tile(int score, String opcode, AddressingMode srcAddrMode, AddressingMode destAddrMode) {
		this.score = score;
		this.opcode = opcode;
		this.srcAddrMode = srcAddrMode;
		this.destAddrMode = destAddrMode;
	}
	
	public String toString(){
		String ret = "Tile toString failed";
		//Single argument opcodes use only the source
		if(srcAddrMode == null) {
			ret = opcode;
		}
		else if(destAddrMode==null){
			ret = opcode+" "+srcAddrMode;
		}else{
			ret = opcode+" "+srcAddrMode+","+destAddrMode;
		}
		return ret;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setOpCode(String opcode) {
		this.opcode = opcode;
	}
	
	public String getOpcode() {
		return opcode;
	}

	public void setSrcAddrMode(AddressingMode srcAddrMode) {
		this.srcAddrMode = srcAddrMode;
	}

	public AddressingMode getSrcAddrMode() {
		return srcAddrMode;
	}

	public void setDestAddrMode(AddressingMode destAddrMode) {
		this.destAddrMode = destAddrMode;
	}

	public AddressingMode getDestAddrMode() {
		return destAddrMode;
	}
	
	public Tile copy() {
		Tile copy = new Tile();
		copy.setOpCode(this.opcode);
		copy.setScore(this.score);
		copy.setSrcAddrMode(this.srcAddrMode);
		copy.setDestAddrMode(this.destAddrMode);
		return copy;
	}
}
