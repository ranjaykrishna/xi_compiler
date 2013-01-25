package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.CJumpIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.ExpressionIR;
import rak248.xi.ir.JumpIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.ir.SeqIR;
import rak248.xi.ir.StatementIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;


public class WhileNode extends StatementNode{

	public WhileNode(ExpressionNode args, StatementsNode s, Position position, Position position2) {
		addChild(args);
		addChild(s);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("while loop");
	}

	public WhileNode(ExpressionNode args, StatementNode b, Position position, Position position2) {
		addChild(args);
		addChild(b);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("while loop");
	}

	public Type typeCheck(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		ArrayList<VisualizableTreeNode> children = getChildren();
		ExpressionNode args = (ExpressionNode) children.get(0);
		SymbolTable childTable = new SymbolTable(table);
		if(!args.typeCheck(childTable).isBool()) {
			String message = "TypeError: while loop argument must be of type bool at " + args.position();
			throw new TypeError(message);
		}
		
		StatementsNode s = null;
		StatementNode t = null;
		if(children.get(1) instanceof StatementsNode){
			s = (StatementsNode) children.get(1);
		}
		else {
			t = (StatementNode) children.get(1);
		}
		//Type bodyType = args.typeCheck(childTable);
		//if(!bodyType.isUnit()) {
		//	String message = "TypeError: while loop body must be of type unit. Is currently type " + bodyType;
		//	message += ". Occurred at position: "+ s.position();
		//	throw new TypeError(message);
		//}
		if(s != null) {
			Type bodyType = s.typeCheck(childTable);
			if(!(bodyType.isUnit() || bodyType.isVoid())) {
				String message = "TypeError: while loop body must be of type unit. Is currently type " + bodyType;
				message += ". Occurred at position: "+ s.position();
				throw new TypeError(message);
			}
		}
		else {
			Type bodyType = t.typeCheck(childTable);
			if(!(bodyType.isUnit() || bodyType.isVoid())) {
				String message = "TypeError: while loop body must be of type unit or void. Is currently type " + bodyType;
				message += ". Occurred at position: "+ t.position();
				throw new TypeError(message);
			}
		}
		setSymTable(childTable);
		print = true;
		return new Type(Type.typeEnum.UNIT);
	}
	
	
	public SyntaxIR translate(HashMap<String, String> map){
		if (getChildren().size() < 2){
			System.err.println("improper while node");
			return null;
		}
		else{
			ExpressionIR condition = (ExpressionIR) ((SyntaxNode) getChildren().get(0)).translate(map);
			LabelIR lh = new LabelIR("w_head" + SyntaxIR.getFreshLabel());	//head
			LabelIR lt = new LabelIR("w_true" + SyntaxIR.getFreshLabel());	//true
			LabelIR lf = new LabelIR("w_false" + SyntaxIR.getFreshLabel());	//false
			//optimizations for while(false) S => ;
			if (condition instanceof ConstIR){
				if ( ((ConstIR) condition).getValue() == 0){
					return new SeqIR();
				}
			}
			
			LabelIR.getWhileLabelStack().push(lt);
			LabelIR.getWhileLabelHeadStack().push(lh);
			StatementIR statement = (StatementIR) ((SyntaxNode) getChildren().get(1)).translate(map);
			LabelIR.getWhileLabelStack().pop();
			LabelIR.getWhileLabelHeadStack().pop();
			SeqIR seq = new SeqIR("");
			//beginning of loop
			seq.addChild(lh);
			CJumpIR jmp = new CJumpIR(new OpIR(opEnum.NOT, condition), lt.label(), lf.label(),false,true);
			seq.addChild(jmp);
			//beginning of statements inside the loop
			seq.addChild(lf);
			seq.addChild(statement);
			//add normal jump
			JumpIR njmp = new JumpIR(new NameIR(lh.label()));
			seq.addChild(njmp);
			//end of loop
			seq.addChild(lt);
			return seq;
		}
	}
}
