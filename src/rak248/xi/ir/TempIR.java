package rak248.xi.ir;

import java.util.ArrayList;
import java.util.HashMap;

import rak248.asm.AddressingMode;
import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.xi.SyntaxIR;

public class TempIR extends ExpressionIR implements Comparable {
	
	private String id;
	private String actual;
	
	public TempIR(String act,String id) {
		if(id.equals("thisNode"))
			id = "_args0";
		setLabel("TEMP: "+ id);
		this.id = id;
		setActual(act);
	}
	
	public TempIR() {
		setLabel("STATIC TEMP: " );//+ getFreshName());
	}
	
	//used for hidden temps (setting up lists)
	public TempIR(String id) {
		if(id.equals("thisNode"))
			id = "_args0";
		setLabel("HIDDEN TEMP: " + id);
		this.id = id;
	}
	
	public SyntaxIR foldESEQ() {
		return this;
	}
	
	public SyntaxIR foldCALL(){
		return this;
	}
	
	public int hashCode(){
		return id.hashCode();
	}
	
	public String getId(){
		return id;
	}
	
	public ArrayList<Tile> generateTile(TempTable tTable) {
		Tile t1 = new Tile();
		Tile t2 = new Tile();
		t1.setScore(1);
		t2.setScore(1);
		t1.setOpCode("movq");
		t2.setOpCode("movq");
		String funcname = tTable.getCurrentFunction().label();
		int lastindex = funcname.lastIndexOf("_");
		String letter = funcname.substring(lastindex+1, lastindex+2);
		if(id.startsWith("__ret")) {
			Integer offset = Integer.parseInt((id.substring(5,id.indexOf("@"))));
			offset = 1*(offset)*8;
			//System.out.println("offset:"+offset);
			t1.setSrcAddrMode(new AddressingMode(offset, "rax"));
		} else if(!(letter.equals("t"))) {
			if(id.startsWith("__args0")) {
				t1.setSrcAddrMode(new AddressingMode("rdi")); }
			else if(id.startsWith("__args1")) {
				t1.setSrcAddrMode(new AddressingMode("rsi")); }
			else if(id.startsWith("__args2")) {
				t1.setSrcAddrMode(new AddressingMode("rdx")); }
			else if(id.startsWith("__args3")) {
				t1.setSrcAddrMode(new AddressingMode("rcx")); }
			else if(id.startsWith("__args4")) {
				t1.setSrcAddrMode(new AddressingMode("r8")); }
			else if(id.startsWith("__args5")) {
				t1.setSrcAddrMode(new AddressingMode("r9")); }
			else if(id.startsWith("__args")) {
				t1.setSrcAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 5) * 8, "rbp"));
			} else {
				HashMap<String, String> regs = tTable.getCurrentFunction().getRegAlloc();
				HashMap<String, Integer> stack = tTable.getCurrentFunction().getStackLocation();
				if(regs.containsKey(id)) {
					t1.setSrcAddrMode(new AddressingMode(regs.get(id)));
				} else {
					ArrayList<Tile> resultant = new ArrayList<Tile>();
					int offset = stack.get(id);
					offset = offset * -1;
					t1.setSrcAddrMode(new AddressingMode(offset, "rbp"));
					t1.setDestAddrMode(new AddressingMode("r12"));
					resultant.add(t1);
					return resultant;
				}
				t1.setDestAddrMode(new AddressingMode("r12")); }
		} else {
			if(id.startsWith("__args0")) {
				t1.setSrcAddrMode(new AddressingMode("rsi")); }
			else if(id.startsWith("__args1")) {
				t1.setSrcAddrMode(new AddressingMode("rdx")); }
			else if(id.startsWith("__args2")) {
				t1.setSrcAddrMode(new AddressingMode("rcx")); }
			else if(id.startsWith("__args3")) {
				t1.setSrcAddrMode(new AddressingMode("r8")); }
			else if(id.startsWith("__args4")) {
				t1.setSrcAddrMode(new AddressingMode("r9")); }
			else if(id.startsWith("__args")) {
				t1.setSrcAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 3) * 8, "rbp"));
			} else {
				HashMap<String, String> regs = tTable.getCurrentFunction().getRegAlloc();
				if(regs.containsKey(id)) {
					t1.setSrcAddrMode(new AddressingMode(regs.get(id)));
				} else {
					ArrayList<Tile> resultant = new ArrayList<Tile>();
					HashMap<String, Integer> stack = tTable.getCurrentFunction().getStackLocation();
					int offset = stack.get(id);
					offset = offset * -1;
					t1.setSrcAddrMode(new AddressingMode(offset, "rbp"));
					t1.setDestAddrMode(new AddressingMode("r12"));
					resultant.add(t1);
					return resultant;
				}
				t1.setDestAddrMode(new AddressingMode("r12")); }
		}
		t1.setDestAddrMode(new AddressingMode("r12"));
		ArrayList<Tile> result = new ArrayList<Tile>();
		result.add(t1);
		return result;
	}
	
	public ArrayList<Tile> generateTileWindows(TempTable tTable) {
		Tile t1 = new Tile();
		Tile t2 = new Tile();
		t1.setScore(1);
		t2.setScore(1);
		t1.setOpCode("movq");
		t2.setOpCode("movq");
		String funcname = tTable.getCurrentFunction().label();
		int lastindex = funcname.lastIndexOf("_");
		String letter = funcname.substring(lastindex+1, lastindex+2);
		if(id.startsWith("__ret")) {
			Integer offset = Integer.parseInt((id.substring(5,id.indexOf("@"))));
			offset = 1*(offset)*8;
			//System.out.println("offset:"+offset);
			t1.setSrcAddrMode(new AddressingMode(offset, "rax"));
		} else if(!(letter.equals("t"))) {
			if(id.startsWith("__args0")) {
				t1.setSrcAddrMode(new AddressingMode("rcx")); }
			else if(id.startsWith("__args1")) {
				t1.setSrcAddrMode(new AddressingMode("rdx")); }
			else if(id.startsWith("__args2")) {
				t1.setSrcAddrMode(new AddressingMode("r8")); }
			else if(id.startsWith("__args3")) {
				t1.setSrcAddrMode(new AddressingMode("r9")); }
			else if(id.startsWith("__args")) {
				t1.setSrcAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 5) * 8, "rbp"));
			} else {
				HashMap<String, String> regs = tTable.getCurrentFunction().getRegAlloc();
				HashMap<String, Integer> stack = tTable.getCurrentFunction().getStackLocation();
				if(regs.containsKey(id)) {
					t1.setSrcAddrMode(new AddressingMode(regs.get(id)));
				} else {
					ArrayList<Tile> resultant = new ArrayList<Tile>();
					int offset = stack.get(id);
					offset = offset * -1;
					t1.setSrcAddrMode(new AddressingMode(offset, "rbp"));
					t1.setDestAddrMode(new AddressingMode("r12"));
					resultant.add(t1);
					return resultant;
				}
				t1.setDestAddrMode(new AddressingMode("r12")); }
		} else {
			if(id.startsWith("__args0")) {
				t1.setSrcAddrMode(new AddressingMode("rdx")); }
			else if(id.startsWith("__args1")) {
				t1.setSrcAddrMode(new AddressingMode("r8")); }
			else if(id.startsWith("__args2")) {
				t1.setSrcAddrMode(new AddressingMode("r9")); }
			else if(id.startsWith("__args")) {
				t1.setSrcAddrMode(new AddressingMode((Integer.parseInt(id.substring(6,7)) - 3) * 8, "rbp"));
			} else {
				HashMap<String, String> regs = tTable.getCurrentFunction().getRegAlloc();
				if(regs.containsKey(id)) {
					t1.setSrcAddrMode(new AddressingMode(regs.get(id)));
				} else {
					ArrayList<Tile> resultant = new ArrayList<Tile>();
					HashMap<String, Integer> stack = tTable.getCurrentFunction().getStackLocation();
					int offset = stack.get(id);
					offset = offset * -1;
					t1.setSrcAddrMode(new AddressingMode(offset, "rbp"));
					t1.setDestAddrMode(new AddressingMode("r12"));
					resultant.add(t1);
					return resultant;
				}
				t1.setDestAddrMode(new AddressingMode("r12")); }
		}
		t1.setDestAddrMode(new AddressingMode("r12"));
		ArrayList<Tile> result = new ArrayList<Tile>();
		result.add(t1);
		return result;
	}
	
	public String prettyPrint(){
		/*if (actual != null){
			return actual;
		}*/
		return id;
	}

	@Override
	public int compareTo(Object arg0) {
		if(arg0 instanceof TempIR) {
			return this.getId().compareTo(((TempIR)arg0).getId());
		}
		return -1;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof TempIR) {
			if(this.getId().equals(((TempIR) arg0).getId())) 
				return true;
		}
		return false;
	}
	
	public void setId(String id) {
		if(id.equals("thisNode")) {
			id = "_args0";
		}
		this.id = id;
		setLabel("TEMP: " + id);
	}
	
	public int hashcode(){
		return id.hashCode();
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getActual() {
		return actual;
	}
	
}
