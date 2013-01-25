package rak248.xi.parser;

import java.util.HashMap;

import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.JumpIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.NameIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;

public class BreakNode extends StatementNode {
	
	boolean isContinue;
	
	public BreakNode(Position position, Position position2){
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		isContinue = false;
		setLabel("BREAK");
	}
	
	public BreakNode(Position position, Position position2, boolean b){
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("CONTINUE");
		isContinue = b;
		
	}
	
	public Type typeCheck(SymbolTable s) {
		return new Type(Type.typeEnum.VOID);
	}
	
	public SyntaxIR translate(HashMap<String, String> map){
		if(isContinue) {
			return new JumpIR(new NameIR(LabelIR.getWhileLabelHeadStack().peek().label()));
		}
		return new JumpIR(new NameIR(LabelIR.getWhileLabelStack().peek().label()));
	}
	

}
