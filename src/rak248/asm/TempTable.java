package rak248.asm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import rak248.xi.ir.LabelIR;
import rak248.xi.ir.TempIR;
import rak248.xi.parser.VarNode;


public class TempTable {
	private TempTable parent;
	private HashMap<String, Integer> table;
	private LabelIR currentFunction;
	private int stackSize;
	private static int jmpLabel;
	
	public TempTable(){
		table = new HashMap<String,Integer>();
		stackSize = 0;
		parent = null;
	}
	
	/** creates a new SymbolTable with p set as the parent */
	public TempTable(TempTable p){
		stackSize = 0;
		table = new HashMap<String,Integer>();
		parent = p;
		setCurrentFunction(p.getCurrentFunction());
	}
	
	/** looks for id in the SymbolTable recursing through its parent tables
	 *  if necessary */
	public Integer lookup(TempIR id) {
		Integer i = table.get(id.getId());
		if(i != null)
			return i;
		else if(parent != null)
			return parent.lookup(id);
		else
			return null;
	}
	
	
	public void add(TempTable s){
		HashMap<String, Integer> sTable = s.getTempTable();
		for(String v : sTable.keySet()) {
			Integer t = sTable.get(v);
			table.put(v, t);
		}
	}
	
	/** Adds mapping id->t to calling SymbolTable */
	public void add(TempIR id, Integer t){
		table.put(id.getId(), t);
	}
	
	public String toString(){
		String ret = table.toString();
		if (parent == null) return ret;
		else {
			String par = parent.toString();
			String total = ret.substring(0,ret.length()-1) + "; " +  par.substring(1);
			return total;
		}
	}
	
	public void combineSymTable(TempTable other){
		HashMap<String, Integer> b = other.getTempTable();
		this.table.putAll(b);
	}

	private HashMap<String, Integer> getTempTable() {
		return table;
	}

	public LabelIR getCurrentFunction() {
		return currentFunction;
	}

	public void setCurrentFunction(LabelIR currentFunction) {
		this.currentFunction = currentFunction;
	}
	
	
	public HashMap<String, Integer> getTable(){
		return table;
	}
	
	public boolean isInCurrentScope(TempIR id){
		return table.containsKey(id.getId());
	}
	
	public void pushCalled() {
		//System.out.println("push called: " + table);
		/*for(int i = 0; i < table.values().size(); i ++) {
			Object[] keys = (Object[]) table.keySet().toArray();
			table.put((String) (keys[i]), (((Integer)table.values().toArray()[i]) + 8));
		}*/
		//System.out.println(table);
		stackSize++;
		//System.out.println(stackSize);
	}
	
	public void popCalled() {
		/*for(int i = 0; i < table.values().size(); i ++) {
			Object[] keys = (Object[]) table.keySet().toArray();
			table.put((String) (keys[i]), (((Integer)table.values().toArray()[i]) - 8));
		}*/
		stackSize--;
		//System.out.println(stackSize);
	}
	
	public int getStackSize() {
		return stackSize;
	}
	
	public void setStackSize(int t) {
		stackSize = t;
	}
	
	public int getNewJumpLabel() {
		return jmpLabel++;
	}
}
