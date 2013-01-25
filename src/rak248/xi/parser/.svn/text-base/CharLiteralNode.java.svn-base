package rak248.xi.parser;

import java.util.HashMap;

import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;


public class CharLiteralNode extends ExpressionNode{
	
	private int value;
	
	public CharLiteralNode(String string, Position lleft, Position lright){
		Position newPos = new Location(lleft.unit(),lleft.lineStart(),lright.lineEnd(),lleft.columnStart(),lright.columnEnd());
		setPosition(newPos);
		setLabel("CharLiteral: "+string);
		value = (int)string.charAt(0);
	}
	
	public Type typeCheck(SymbolTable table) {
		return new Type(Type.typeEnum.INT);
	}
	
	public SyntaxIR translate(HashMap<String, String> map){
		return new ConstIR(value);
	}
	
}
