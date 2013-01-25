package rak248.xi.parser;

import java.util.HashMap;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.CallIR;
import rak248.xi.ir.ExpIR;
import rak248.xi.ir.Mangler;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.SeqIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;

public class FunctionsNode extends SyntaxNode {
	
	private boolean typeCheckingByCompUnit;

	public FunctionsNode(FunctionDecNode f,Position position, Position position2) {
		addFunction(f);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("List of Functions"); 
	}
	
	public FunctionsNode(Position position, Position position2) {
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("List of Functions"); 
	}

	public FunctionsNode addFunction(FunctionDecNode f) {
		addChild(f);
		return this;
	}
	
	public Type typeCheck(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		setSymTable(table);
		if(typeCheckingByCompUnit) {
			getSymTable().setCurrentClass(null);
		}
		for(VisualizableTreeNode vtn : getChildren()) {
			FunctionDecNode child = (FunctionDecNode) vtn;
			if(!child.typeCheck(table).isUnit()) {
				String message = "TypeError: function declaration must be of type unit at " + child.position();
				throw new TypeError(message);
			}
		}
		return new Type(typeEnum.UNIT);
	}
	
	public SyntaxIR translate(HashMap<String, String> map){
		SeqIR seq = new SeqIR();
		for (VisualizableTreeNode node: getChildren()){
			SyntaxIR ir = ((FunctionDecNode) node).translate(map);
			if(ir.label().startsWith("_Imain")) {
				seq.getChildren().add(0, ir);
			} else{
				seq.addChild(ir);
			}
		}
		return seq;
	}
	
	public boolean getTypeCheckingByCompUnit() {
		return typeCheckingByCompUnit;
	}
	
	public void setTypeCheckingByCompUnit(boolean b) {
		typeCheckingByCompUnit = b;
	}
	
}
