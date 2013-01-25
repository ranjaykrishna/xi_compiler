package rak248.xi.ir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import rak248.asm.AddressingMode;
import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.xi.SyntaxIR;
import rak248.xi.typeChecker.Type;

public class LabelIR extends StatementIR{
	private static Stack<LabelIR> whileEndLabels = new Stack<LabelIR>();
	private static Stack<LabelIR> whileHeadLabels = new Stack<LabelIR>();
	private String name;
	private HashMap<String,Integer> stackLocation;
	private HashMap<TempIR, String> registersUsed;
	private HashMap<String, String> regAlloc; 
	
	private ArrayList<Type> rets;
	private ArrayList<Type> params;
	private boolean isLEA;
	
	public LabelIR(String id) {
		name = id;
		setLabel("LABEL: "+ id);
		registersUsed = new HashMap<TempIR, String>();
	}
	
	public LabelIR() {
		setLabel("SLABEL: ");// + getFreshName());
	}
	
	public static Stack<LabelIR> getWhileLabelStack() {
		return whileEndLabels;
	}

	public static Stack<LabelIR> getWhileLabelHeadStack() {
		return whileHeadLabels;
	}
	
	public String getName(){
		return name;
	}
	
	public SyntaxIR foldESEQ() {
		return this;
	}
	
	public SyntaxIR foldCALL() {
		return this;
	}

	public ArrayList<Tile> generateTile(TempTable tTable) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		
		//the following tile is to add a assembler directive for functions
		//it might be better to handle it somewhere else
		if(name.startsWith("_I")){
			Tile globl = new Tile(0, ".globl "+name,null,null);
			result.add(globl);
		}
		if(name.startsWith("_block")){
			//for now include the label to help with locating it
			//result.add(new Tile(1,name+":",null,null));
			int numPops = Integer.parseInt(name.substring(name.lastIndexOf("_")+1));
			for(int i = 0; i < numPops;i++){
				//result.add(new Tile(1,"popq",new AddressingMode("r13"),null));
				//tTable.popCalled();
			}
		}else{
			Tile t = new Tile(0, name + ": ", null, null);
			result.add(t);
		}
		//Stuff to do before a function starts
		if(name.startsWith("_I")) {
			//tTable = new TempTable();
			tTable.setCurrentFunction(this);
			//set the base pointer to the current stack pointer
			result.add(new Tile(1, "movq", new AddressingMode("rsp"), new AddressingMode("rbp")));
			//bump the pointer to be at the top of allocated space
			result.add(new Tile(1, "sub", new AddressingMode(this.getStackLocation().size()*8),new AddressingMode("rsp")));
		}
		HashSet<String> stringsDone = new HashSet<String>();
		for(String reg : registersUsed.values()) {
			if(!stringsDone.contains(reg)) {
				stringsDone.add(reg);
				//result.add(new Tile(1, "pushq", new AddressingMode(reg), null));
			}
		}
		return result;
	}
	
	public ArrayList<Tile> generateTileWindows(TempTable tTable) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		
		//the following tile is to add a assembler directive for functions
		//it might be better to handle it somewhere else
		if(name.startsWith("_I")){
			Tile globl = new Tile(0, ".globl "+name,null,null);
			result.add(globl);
		}
		if(name.startsWith("_block")){
			//for now include the label to help with locating it
			//result.add(new Tile(1,name+":",null,null));
			int numPops = Integer.parseInt(name.substring(name.lastIndexOf("_")+1));
			for(int i = 0; i < numPops;i++){
				//result.add(new Tile(1,"popq",new AddressingMode("r13"),null));
				//tTable.popCalled();
			}
		}else{
			Tile t = new Tile(0, name + ": ", null, null);
			result.add(t);
		}
		//Stuff to do before a function starts
		if(name.startsWith("_I")) {
			//tTable = new TempTable();
			tTable.setCurrentFunction(this);
			//set the base pointer to the current stack pointer
			result.add(new Tile(1, "movq", new AddressingMode("rsp"), new AddressingMode("rbp")));
			//bump the pointer to be at the top of allocated space
			result.add(new Tile(1, "sub", new AddressingMode(this.getStackLocation().size()*8),new AddressingMode("rsp")));
		}
		HashSet<String> stringsDone = new HashSet<String>();
		for(String reg : registersUsed.values()) {
			if(!stringsDone.contains(reg)) {
				stringsDone.add(reg);
				//result.add(new Tile(1, "pushq", new AddressingMode(reg), null));
			}
		}
		return result;
	}

	public void setStackLocation(HashMap<String,Integer> stackLocation) {
		this.stackLocation = stackLocation;
	}

	public HashMap<String,Integer> getStackLocation() {
		return stackLocation;
	}
	
	public String prettyPrint(){
		String ret = name;
		return ret;
	}
	
	public void setRegistersUsed(HashMap<TempIR, String> registersUsed) {
		this.registersUsed = registersUsed;
	}

	public HashMap<TempIR, String> getRegistersUsed() {
		return registersUsed;
	}

	public void setRegAlloc(HashMap<String, String> regAlloc) {
		this.regAlloc = regAlloc;
	}

	public HashMap<String, String> getRegAlloc() {
		return regAlloc;
	}

	public ArrayList<Type> getRets() {
		return rets;
	}

	public void setRets(ArrayList<Type> rets) {
		this.rets = rets;
	}

	public ArrayList<Type> getParams() {
		return params;
	}

	public void setParams(ArrayList<Type> params) {
		this.params = params;
	}

	public void setLEA(boolean b) {
		this.isLEA = b;
	}
	
	public boolean getLEA() {
		return isLEA;
	}
}
