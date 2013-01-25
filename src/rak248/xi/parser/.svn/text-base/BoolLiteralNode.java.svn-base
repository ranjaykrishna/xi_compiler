package rak248.xi.parser;

import java.util.HashMap;

import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.Type.typeEnum;

public class BoolLiteralNode extends ExpressionNode{
	
	private int bool;
	private String cool = "";
	
	public BoolLiteralNode(String b, Position position, Position position2){
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("bool literal:"+b);
		cool = b;
		if (b.equals("true")) bool = 1; 
		else bool = 0;
	}
	
	public Type typeCheck(SymbolTable table){
		return new Type(typeEnum.BOOL);
	}
	
	public SyntaxIR translate(HashMap<String, String> map){
		return new ConstIR(bool,cool);
	}

	public void setBool(int bool) {
		this.bool = bool;
	}

	public int getBool() {
		return bool;
	}
}
