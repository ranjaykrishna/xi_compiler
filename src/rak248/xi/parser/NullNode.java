package rak248.xi.parser;

import java.util.HashMap;

import edu.cornell.cs.cs4120.xi.Position;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.MemIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;

public class NullNode extends SyntaxNode {

	public NullNode(Position position) {
		this.setPosition(position);
	}
	
	public Type typeCheck(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		setSymTable(table);
		Type nullType = new Type(typeEnum.NULL,"null");
		setType(nullType);
		return nullType;
	}
	
	public SyntaxIR translate(HashMap<String, String> map) {
		//MemIR nullMem = new MemIR(new ConstIR(0));
		ConstIR nullMem = new ConstIR(0);
		return nullMem;
	}

}
