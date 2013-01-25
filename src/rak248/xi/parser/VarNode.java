package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.CJumpIR;
import rak248.xi.ir.ExpIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.SeqIR;
import rak248.xi.ir.StatementIR;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.UndeclaredIdentifierException;


public class VarNode extends SyntaxNode{

	private String id;
	
	public VarNode(String str, Position left, Position right) {
		if(left==null) System.out.println("LEFT IS NULL");
		if(right==null) System.out.println("RIGHT IS NULL");
		Position newPos = new Location(left.unit(),left.lineStart(),right.lineEnd(),left.columnStart(),right.columnEnd());
		setPosition(newPos);
		setLabel(str);
		id = str;
	}
	
	public Type typeCheck(SymbolTable table, String className) throws UndeclaredIdentifierException {
		Type ret;
		if(className != null) {
			ret = table.lookUpMethod(className, id);
			if(ret == null) {
				throw new UndeclaredIdentifierException(this);
			}
			return ret;
		}
		setSymTable(table);
		
		className = table.getCurrentClass();
		try {
			return table.lookup(this);
		} catch(UndeclaredIdentifierException e) {
			ret = table.lookUpMethod(className, id);
		}
		if(ret != null) {
			return ret;
		} else {
			// check globals?
			throw new UndeclaredIdentifierException(this);
		}
	}
	
	public Type typeCheck(SymbolTable table) throws UndeclaredIdentifierException {
		return typeCheck(table, null);
	}
	
	public String getID() {
		return id;
	}
	
	public int hashCode() {
		return id.hashCode();
	}
	
	public boolean equals(Object o) {
		if(o instanceof VarNode) {
			return id.equals(((VarNode) o).getID());
		}
		return false;
	}
	
	
	public SyntaxIR translate(HashMap<String, String> map){
		if(map == null) {
			return new TempIR(id,getSymTable().lookUp(id));
		}
		try{
			return new TempIR(map.get(id), getSymTable().lookUp(map.get(id)));
		} catch(Exception e) {
			return new TempIR(id,getSymTable().lookUp(id));
		}
	}
}
