package rak248.xi.parser;

import java.util.HashMap;

import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.Type.typeEnum;

public class IntegerLiteralNode extends ExpressionNode{
	
	private long value;
	
	public IntegerLiteralNode(long l, Position position, Position position2){
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("int literal:"+l);
		value = l;
	}
	
	public Type typeCheck(SymbolTable table) {
		return new Type(typeEnum.INT);
	}
	
	
	public SyntaxIR translate(HashMap<String, String> map){
		return new ConstIR(value);
	}
	
	public String toString(){
		return ""+value;
	}
}
