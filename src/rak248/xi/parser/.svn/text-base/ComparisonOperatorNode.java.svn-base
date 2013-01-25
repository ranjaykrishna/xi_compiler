package rak248.xi.parser;

import edu.cornell.cs.cs4120.xi.Position;
import rak248.xi.Location;
import rak248.xi.SyntaxNode;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.Type.typeEnum;

public class ComparisonOperatorNode extends SyntaxNode{
	
	private int typeOfComparison;
	
	public ComparisonOperatorNode(int i, Position position, Position position2) {
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		switch(i){
		case Sym.EQUAL:     setLabel("==");break;
		case Sym.NOT_EQUAL: setLabel("!=");break;
		case Sym.LEQ:       setLabel("<=");break;
		case Sym.LT:        setLabel("<");break;
		case Sym.GEQ:       setLabel(">=");break;
		case Sym.GT:        setLabel(">");break;
		case Sym.AND:       setLabel("&");break;
		case Sym.OR:        setLabel("|");break;
		}
		typeOfComparison = i;
	}
	
	public boolean checksEquality() {
		return typeOfComparison == Sym.EQUAL || typeOfComparison == Sym.NOT_EQUAL;
	}
	
	// comparison operator doesn't do any typechecking
	// expression nodes do the type checking on either side
	public Type typeCheck(SymbolTable table) {
		return new Type(typeEnum.BOOL);
	}

	public boolean isBoolOp() {
		return typeOfComparison == Sym.AND || typeOfComparison == Sym.OR || typeOfComparison == Sym.EQUAL;
	}
	
	public int getTypeOfComparison() {
		return typeOfComparison;
	}
}
