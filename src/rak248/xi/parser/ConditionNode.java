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
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.SeqIR;
import rak248.xi.ir.StatementIR;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.Type.typeEnum;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;

public class ConditionNode extends StatementNode{

	public static int i = 0;
	public ConditionNode(){
		setLabel("PLEASE DONT CALL CONDITIONNODE CONSTRUCTOR " + i);
		i += 1;
	}
	
	public ConditionNode(ExpressionNode e, StatementNode c, StatementNode cl, Position position, Position position2) {
		addChild(e);
		addChild(c);
		addChild(cl);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("matched if statement");
	}

	public ConditionNode(ExpressionNode e, StatementNode c, Position position, Position position2) {
		addChild(e);
		addChild(c);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("unmatched if statement");
	}
	
	public void addChild(SyntaxNode n) {
		setLabel("matched if statement");
		super.addChild(n);
	}
	
	public StatementNode getFirstStatement() {
		return (StatementNode) getChildren().get(1);
	}
	
	public Type typeCheck(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		setSymTable(table);
		ArrayList<VisualizableTreeNode> children = getChildren();
		ExpressionNode e = (ExpressionNode) children.get(0);
		StatementNode c = (StatementNode) children.get(1);
		StatementNode c2 = null;
		SymbolTable childTable = new SymbolTable(table);
		SymbolTable childTable2 = new SymbolTable(table);
		if(children.size() > 2) {
			c2 = (StatementNode) children.get(2);
			c2.setSymTable(childTable2);
			c2.print = true;
		}
		c.setSymTable(childTable);
		c.print = true;
		if(!e.typeCheck(childTable).isBool()){
			String message = "Type error: If statement did not get passed a bool. ";
			message = message + "Occured at position: " + e.position();
			throw new TypeError(message);
		}
		if(c2 == null) {
			Type cType = c.typeCheck(childTable);
			if(cType.isVoid() || cType.isUnit()) {
				return new Type(typeEnum.UNIT);
			}
			else {
				String message = "Type error: Statements in conditional if branch are inappropriate";
				message = message + " type.  Occured at position: " + c.position();
				throw new TypeError(message);
			}
		}
		else {
			c2.setSymTable(childTable2);
			c2.print = true;
			Type cType = c.typeCheck(childTable);
			Type c1Type = c2.typeCheck(childTable2);
			if(cType.isVoid() && c1Type.isVoid()) {
				return new Type(typeEnum.VOID);
			}
			//else if((c.typeCheck(childTable).isUnit() || c.typeCheck(childTable).isVoid()) && (c1.typeCheck(childTable2).isUnit() || c1.typeCheck(childTable2).isVoid())) {
			else if((cType.isUnit() || cType.isVoid()) && (c1Type.isUnit() || c1Type.isVoid())) {
				return new Type(typeEnum.UNIT);
			}
			else {
				String message = "Type error: Statements in conditional if branch are inappropriate";
				message = message + " type.  Occured at position: " + c.position();
				throw new TypeError(message);
			}
		}	
	}
	
	@Override
	public SyntaxIR translate(HashMap<String, String> map){
		ArrayList<VisualizableTreeNode> children = getChildren();
		//ExpressionIR e = (ExpressionIR) ((ExpressionNode) getChildren().get(0)).translate(map);
		StatementIR c = (StatementIR) ((StatementNode) getChildren().get(1)).translate(map);
		LabelIR lTrue = new LabelIR("if_true" + SyntaxIR.getFreshLabel());
		LabelIR lFalse = new LabelIR("if_false" + SyntaxIR.getFreshLabel());
		LabelIR lBottom = new LabelIR("if_end" + SyntaxIR.getFreshLabel());
		SeqIR seq = new SeqIR("");
		TempIR e = new TempIR("_cHidden"+SyntaxIR.getFreshLabel());
		//System.out.println(e);
		ExpressionIR expr = (ExpressionIR) ((ExpressionNode) getChildren().get(0)).translate(map);
		
		if(children.size() == 2) {
			if (expr instanceof ConstIR){
				ConstIR cons = (ConstIR) expr;
				if (cons.getValue() != 0){
					seq.addChild(c);
				}
				return seq;
			}
			seq.addChild(new MoveIR(e, expr));
			seq.addChild(new CJumpIR(e, lTrue.label(), lFalse.label(),false,false));
			seq.addChild(lFalse);
			seq.addChild(new JumpIR(new NameIR(lBottom.label())));
			seq.addChild(lTrue);
			seq.addChild(c);
			seq.addChild(new JumpIR(new NameIR(lBottom.label())));
			seq.addChild(lBottom);
			//seq.addChild(new LabelIR("_block"+SyntaxIR.getFreshLabel()+"End_1"));
			return seq;
		} else if(children.size() == 3) {
			StatementIR c2 = (StatementIR) ((StatementNode) getChildren().get(2)).translate(map);
			if (expr instanceof ConstIR){
				ConstIR cons = (ConstIR) expr;
				if (cons.getValue() != 0){
					seq.addChild(c);
				}
				else{
					seq.addChild(c2);
				}
				return seq;
			}
			seq.addChild(new MoveIR(e, expr));
			seq.addChild(new CJumpIR(e, lTrue.label(), lFalse.label(),true,false));
			seq.addChild(lFalse);
			seq.addChild(c2);
			seq.addChild(new JumpIR(new NameIR(lBottom.label())));
			seq.addChild(lTrue);
			seq.addChild(c);
			seq.addChild(new JumpIR(new NameIR(lBottom.label())));
			seq.addChild(lBottom);
			//seq.addChild(new LabelIR("_block"+SyntaxIR.getFreshLabel()+"End_1"));
			return seq;
		} else {
			System.err.println("ConditionNode has wrong number of children");
			return null;
		}
	}

}
