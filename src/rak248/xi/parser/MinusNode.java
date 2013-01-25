package rak248.xi.parser;

import edu.cornell.cs.cs4120.xi.Position;
import rak248.xi.Location;
import rak248.xi.SyntaxNode;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;

public class MinusNode extends SyntaxNode{

	private boolean negate = false;
	
	public MinusNode(Position position, Position position2) {
		negate = true;
		Position tempPosition = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(tempPosition);
		setLabel("Minus:"+negate);
	}

	public MinusNode addMinus() {
		negate = !negate;
		setLabel("Minus:"+negate);
		return this;
	}
	
	public Type typeCheck(SymbolTable s) throws TypeError, UndeclaredIdentifierException {
		return new Type(Type.typeEnum.UNIT);
	}

}
