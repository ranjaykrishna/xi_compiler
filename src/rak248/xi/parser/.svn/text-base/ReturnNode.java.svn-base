package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.CallIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.ExpressionIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.ir.ReturnIR;
import rak248.xi.ir.SeqIR;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;

public class ReturnNode extends StatementNode{
	
	public ReturnNode(ArrayList<ExpressionNode> r, Position position, Position position2) {
		for(ExpressionNode node : r) {
			addChild(node);
		}
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("return node");
	}
	
	public Type typeCheck(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		setSymTable(table);
		Type[] arrayOfTypes = new Type[getChildren().size()];
		for(int i = 0; i < arrayOfTypes.length; i ++) {
			arrayOfTypes[i] =  ((ExpressionNode)getChildren().get(i)).typeCheck(table);
		}
		String curFunc = table.getCurrentFunction();
		VarNode newVar = new VarNode(curFunc, position(), position());
		//System.out.println("return cur class:"+table.getCurrentClass());
		//System.out.println(newVar);
		Type origType = table.lookup(newVar);
		//System.out.println("origType:"+origType);
		Type[] returnTypes = origType.getReturnTypes();
		Type[] thisRetNodeTyes = arrayOfTypes;
		if(returnTypes.length != thisRetNodeTyes.length) {
			String message = "Attempting to return incorect amount of expressions.";
			message += " Occurred at position: " + position();
			throw new TypeError(message);
		}
		for(int i = 0; i < returnTypes.length; i ++) {
			if(!returnTypes[i].equals(thisRetNodeTyes[i])) {
				String message = "Attempting to return incorect type of expression.";
				message += " Attempting to return " + thisRetNodeTyes[i] + " when ";
				message += returnTypes[i] + " is expected.";
				message += " Occurred at position: " + position();
				throw new TypeError(message);
			}
		}
		return new Type(typeEnum.VOID);
	}
	
	public Type[] getReturnTypes(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		Type[] returnTypes = new Type[getChildren().size()];
		for(int i = 0; i < returnTypes.length; i++) {
			returnTypes[i] = ((SyntaxNode) getChildren().get(i)).typeCheck(table);
		}
		return returnTypes;
	}
	
	
	public SyntaxIR translate(HashMap<String, String> map){
		ArrayList<VisualizableTreeNode> children = getChildren();
		if(children.size() == 0) {
			return new ReturnIR();
		}
		else{
			ReturnIR ret = new ReturnIR();
			SeqIR seq = new SeqIR();
			if(children.size() == 1) {
				SyntaxNode node = (SyntaxNode) children.get(0);
				//System.out.println("rn map:"+map);
				SyntaxIR ir = node.translate(map);
				ret.addChild(ir);
				return ret;
			}
			else {
				int x = SyntaxIR.getFreshNum(children.size());
				for(int i = 0; i < children.size(); i ++) {
					TempIR rets = new TempIR("_tempret"+(i));
					MoveIR moves = new MoveIR(rets, ((SyntaxNode) children.get(i)).translate(map));
					seq.addChild(moves);
				}
				for(int i = 0; i < children.size(); i ++) {
					TempIR rets = new TempIR("_rret"+(i));
					MoveIR moves = new MoveIR(rets, new TempIR("_tempret"+(i)));
					seq.addChild(moves);
					ret.addChild(rets);
				}
				seq.addChild(ret);
				return seq;
			}
		}
	}
}
