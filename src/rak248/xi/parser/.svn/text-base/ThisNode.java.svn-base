package rak248.xi.parser;

import java.util.HashMap;

import edu.cornell.cs.cs4120.xi.Position;
import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.UndeclaredIdentifierException;

public class ThisNode extends SyntaxNode {

	public ThisNode(Position position) {
		setLabel("THIS");
		Position newPos = new Location(position.unit(),position.lineStart(),position.lineEnd(),position.columnStart(),position.columnEnd());
		setPosition(newPos);
	}

	public ThisNode() {
		setLabel("THIS");
	}
	
	public Type typeCheck(SymbolTable table) throws UndeclaredIdentifierException {
		setSymTable(table);
		return table.lookup(new VarNode("this", position(), position()));
	}
	
	public SyntaxIR translate(HashMap<String, String> map) {
		return new TempIR("thisNode");
	}

}
