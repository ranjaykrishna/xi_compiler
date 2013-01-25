package rak248.xi.parser;

import edu.cornell.cs.cs4120.xi.Position;
import rak248.xi.Location;
import rak248.xi.SyntaxNode;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;

public class NotNode extends SyntaxNode{
	private boolean negate = false;

	public NotNode(Position position, Position position2) {
		negate = true;
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("NOT:"+negate);
	}

	public NotNode addNot() {
		negate = !negate;
		setLabel("Not:"+negate);
		return this;
	}
	
	public Type typeCheck(SymbolTable s) throws TypeError, UndeclaredIdentifierException {
		return new Type(Type.typeEnum.UNIT);
	}

}
