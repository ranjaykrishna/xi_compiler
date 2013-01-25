package rak248.xi.parser;

import edu.cornell.cs.cs4120.xi.Position;
import rak248.xi.Location;
import rak248.xi.SyntaxNode;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.Type.typeEnum;

public class MathFunctionNode extends SyntaxNode{
	
	private int typeOfMathOp;

	public MathFunctionNode(int i, Position position, Position position2) {
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		switch(i){
		case Sym.PLUS:   setLabel("+");break;
		case Sym.MINUS:  setLabel("-");break;
		case Sym.TIMES:  setLabel("*");break;
		case Sym.DIVIDE: setLabel("/");break;
		case Sym.MODULO: setLabel("%");break;
		}
		typeOfMathOp = i;
	}
	
	public Type typeCheck(SymbolTable table) {
		return new Type(typeEnum.INT);
	}
	
	public int getTypeOfMathOp() {
		return typeOfMathOp;
	}

}
