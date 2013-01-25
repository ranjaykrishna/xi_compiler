package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cornell.cs.cs4120.xi.Position;

import rak248.util.VisualizableIRNode;
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
import rak248.xi.ir.SeqIR;
import rak248.xi.ir.TempIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;


public class ListNode extends ExpressionNode{
	
	ArrayList<ExpressionNode> subList;
	
	public ListNode(ArrayList<ExpressionNode> ar, Position position, Position position2){
		subList = ar;
		setLabel(this.toString());
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
	}
	
	public Type typeCheck(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		setSymTable(table);
		if(subList.size() == 0) {
			throw new TypeError("TypeError: lists must have at least one element at " + this.position());
		}
		Type tOfListE = subList.get(0).typeCheck(table);
		for(int i = 1; i < subList.size(); i++) {
			if(!tOfListE.equals(subList.get(i).typeCheck(table))) {
				throw new TypeError("TypeError: elements in a list must all have the same type at " + this.position());
			}
		}
		return tOfListE.addDimension(this.position());
	}
	
	public SyntaxIR translate(HashMap<String, String> map) {
		ArrayList<ExpressionIR> list = new ArrayList<ExpressionIR>();
		// translate all the elements of the list
		for(ExpressionNode node : subList) {
			list.add((ExpressionIR) node.translate(map));
		}
		//TempIR start = new TempIR(getSymTable().getFreshName());
		EseqIR seq = new EseqIR(subList.toString());
		MoveIR move;
		//set up IR nodes
		OpIR lshift;
		OpIR add;
		//MemIR mem = new MemIR(start);
		
		CallIR call = new CallIR(new NameIR("_I_alloc_i"),new OpIR(opEnum.TIMES, new ConstIR(8), new ConstIR(list.size()+1)));
		TempIR start = new TempIR(getSymTable().getFreshName());
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
		//seq.addChild(new LabelIR("_block"+SyntaxIR.getFreshLabel()+"End_1"));
		//we do it before start since this is an ESeq
		
		//after adding everything, the last thing should be the pointer to the
		//start of the list
		seq.addChild(new OpIR(opEnum.PLUS, start, new ConstIR(8)));
		
		return seq;
	}
	
	public String toString(){
		String crazy = "[";
		for(ExpressionNode a: subList){
			crazy += a.toString() + ", ";
		}
		crazy += "]";
		return crazy;
		
	}
}
