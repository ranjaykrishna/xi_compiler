package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.CallIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.EseqIR;
import rak248.xi.ir.ExpressionIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.TempIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.Type.typeEnum;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;


public class StringLiteralNode extends ExpressionNode{
	
	private String s;

	public StringLiteralNode(String s, Position position, Position position2){
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel(s);
		this.s = s;
	}
	
	public Type typeCheck(SymbolTable table) {
		setSymTable(table);
		ArrayList<ExpressionNode> temp = new ArrayList<ExpressionNode>();
		temp.add(new IntegerLiteralNode(getLabel().length(), position(), position()));
		return new Type(Type.typeEnum.INT_ARRAY, temp);
	}
	
	
	public SyntaxIR translate(HashMap<String, String> map){
		ArrayList<ExpressionNode> charList = new ArrayList<ExpressionNode>();
		for(char let : s.toCharArray()) {
			charList.add(new CharLiteralNode(Character.toString(let), position(), position()));
		}
		ArrayList<ExpressionIR> list = new ArrayList<ExpressionIR>();
		// translate all the elements of the list
		for(ExpressionNode node : charList) {
			list.add((ExpressionIR) node.translate(map));
		}
		//TempIR start = new TempIR(getSymTable().getFreshName());
		EseqIR seq = new EseqIR(charList.toString());
		MoveIR move;
		//set up IR nodes
		OpIR lshift;
		OpIR add;
		
		CallIR call = new CallIR(new NameIR("_I_alloc_i"),new OpIR(opEnum.TIMES, new ConstIR(8), new ConstIR(charList.size()+1)));
		TempIR start = new TempIR(getSymTable().getFreshName());
		int numTemps = 1;
		seq.addChild(new MoveIR(start, call));
		MemIR mem = new MemIR(start);
		
		//move the size of the array into the start of the list
		move = new MoveIR(mem, new ConstIR(list.size()));
		seq.addChild(move);
		/*iterate through the list and generate a bunch of moves that
		 * move the appropriate translation to the next spot in memory
		 */
		for(int i = 0; i < list.size(); i++) {
			lshift = new OpIR(opEnum.LSHIFT, new ConstIR(i+1), new ConstIR(3));
			add = new OpIR(opEnum.PLUS, start, lshift);
			mem = new MemIR(add);
			move = new MoveIR(mem, list.get(i));
			seq.addChild(move);
		}
		//Add a label for the end of the block to facilitate removal of variables in this scope
		//we append the number of local variables to make code generation simple
		String syn = SyntaxIR.getFreshLabel();
		seq.addChild(new LabelIR("_block"+syn+"End_"+numTemps));

		//we do it before start since this is an ESeq
		
		//after adding everything, the last thing should be the pointer to the
		//start of the list
		seq.addChild(new OpIR(opEnum.PLUS, start, new ConstIR(8)));
		
		return seq;
	}
}
