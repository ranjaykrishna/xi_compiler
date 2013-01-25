package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.EseqIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.SeqIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;

public class StatementsNode extends StatementNode{
	
	public StatementsNode() {
		setLabel("Statements");
	}

	public StatementsNode addStatement(StatementNode c, Position position, Position position2) {
		addChild(c);
		if(position != null){
			Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
			setPosition(newPos);
		}else{
			Position newPos = new Location(position2.unit(),position2.lineStart(),position2.lineEnd(),position2.columnStart(),position2.columnEnd());
			setPosition(newPos);
		}
		return this;
	}

	public void addStatement(BreakNode breakNode) {
		addChild(breakNode);
		
	}
	
	public Type typeCheck(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		setSymTable(table);
		ArrayList<VisualizableTreeNode> children = getChildren();
		if(children.size() == 0) {
			return new Type(Type.typeEnum.UNIT);
		}
		for(int i = 0; i < children.size() - 1; i++) {
			SyntaxNode child = (SyntaxNode) children.get(i);
			if(child instanceof StatementsNode) {
				SymbolTable childTable = new SymbolTable(table);
				if(!child.typeCheck(childTable).isUnit()) {
					String message = "TypeError: statements block must be of type unit at " + child.position();
					throw new TypeError(message);
				}
				child.setSymTable(childTable);
				child.print = true;
			} else {
				if(!child.typeCheck(table).isUnit()) {
					String message = "TypeError: statement must be of type unit at " + child.position();
					throw new TypeError(message);
				}
			}
		}
		Type lastType;
		SyntaxNode child = (SyntaxNode) children.get(children.size() - 1);
		if(child instanceof StatementsNode) {
			SymbolTable childTable = new SymbolTable(table);
			lastType = child.typeCheck(childTable);
			if(!lastType.isUnit() && !lastType.isVoid()) {
				String message = "TypeError: statements block must be of type unit or void not '"+lastType+"' at " + child.position();
				//System.out.println(lastType);
				throw new TypeError(message);
			}
			child.setSymTable(childTable);
			child.print = true;
		} else {
			lastType = child.typeCheck(table);
			if(!(lastType.isUnit() || lastType.isVoid())) {
				String message = "TypeError: statement must be of type unit or void not '"+lastType+"' at " + child.position();
				throw new TypeError(message);
			}
		}
		return lastType;
	}

	public void addReturn(ReturnNode r, Position position, Position position2) {
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		addChild(r);
	}
	
	public SyntaxIR translate(HashMap<String, String> map){
		ArrayList<VisualizableTreeNode> children = getChildren();
			SeqIR seq  = new SeqIR("");
			for (VisualizableTreeNode node: children){
				SyntaxIR ir = ((SyntaxNode) node).translate(map);
				if (!(node instanceof DeclarationNode) || ir instanceof SeqIR){
					seq.addChild(ir);
				}
			}
			//Add a label for the end of the block to facilitate removal of variables in this scope
			//we append the number of local variables to make code generation simple
			HashMap<VarNode,Type> realMap = this.symTable.getTable();
			int numTemps = realMap.size();
			for(VarNode key : realMap.keySet()){
				if(map.get(key.label()).startsWith("_args")){
					numTemps--;
				}
			}if(numTemps<0)numTemps=0;
			//seq.addChild(new LabelIR("_block"+SyntaxIR.getFreshLabel()+"End_"+numTemps));
			return seq;
	}
	
}
