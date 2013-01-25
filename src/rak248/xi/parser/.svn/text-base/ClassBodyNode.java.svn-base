package rak248.xi.parser;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import rak248.xi.Location;
import rak248.xi.SyntaxNode;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;
import edu.cornell.cs.cs4120.xi.Position;

public class ClassBodyNode extends SyntaxNode {
	
	private FunctionsNode funcNode;

	public ClassBodyNode(){
		setLabel("ClassBody");
		Position newPos = new Location("DEADBEEF", 0, 0, 0, 0);
		setPosition(newPos);
		funcNode = new FunctionsNode(position(), position());
		addChild(funcNode);
	}
	
	public void add(StatementNode f) {
		addChild(f);
		
	}

	public void add(FunctionsNode f) {
		funcNode.addChild((SyntaxNode) f.getChildren().get(0));
		getChildren().set(0, funcNode);
	}
	
	public Type typeCheck(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		setSymTable(table);
		Type unit = new Type(typeEnum.UNIT);
		if(getChildren().get(0) instanceof FunctionsNode) {
			FunctionsNode func = (FunctionsNode) getChildren().get(0);
			getChildren().remove(0);
			getChildren().add(func);
		}
		for(VisualizableTreeNode c : children()) {
			SyntaxNode child = (SyntaxNode) c;
			if(!child.typeCheck(table).isUnit()) {
				String message = "TypeError: ClassBodyNode must have children of type unit at " + this.position();
				throw new TypeError(message);
			}
		}
		return unit;
	}
	
	public String toString() {
		String ret = "body:";
		ret += "\t"+funcNode;
		return ret;
	}
	
}
