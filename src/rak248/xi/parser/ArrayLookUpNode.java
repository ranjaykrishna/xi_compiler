package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.CJumpIR;
import rak248.xi.ir.CallIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.EseqIR;
import rak248.xi.ir.ExpIR;
import rak248.xi.ir.ExpressionIR;
import rak248.xi.ir.JumpIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;


public class ArrayLookUpNode extends SyntaxNode{

	private String id;
	
	public ArrayLookUpNode(String id, ArrayList<ExpressionNode> exp, Position left, Position right) {
		addChild(new VarNode(id, left, left));
		for(ExpressionNode e: exp){
			addChild(e);
		}
		Position newPos = new Location(left.unit(),left.lineStart(),right.lineEnd(),left.columnStart(),right.columnEnd());
		setPosition(newPos);
		setLabel("array lookup");
		this.id = id;
	}
	
	public ArrayLookUpNode(FunctionCallNode func, ArrayList<ExpressionNode> exp,
			Position left, Position right) {
		addChild(func);
		for(ExpressionNode e: exp){
			addChild(e);
		}
		Position newPos = new Location(left.unit(),left.lineStart(),right.lineEnd(),left.columnStart(),right.columnEnd());
		setPosition(newPos);
		setLabel("array lookup from function call");
	}

	public Type typeCheck(SymbolTable s) throws TypeError, UndeclaredIdentifierException {
		setSymTable(s);
		ArrayList<VisualizableTreeNode> children = getChildren();
		Type t;
		SyntaxNode child = (SyntaxNode) children.get(0);
		if(child instanceof FunctionCallNode) {
			t = ((FunctionCallNode) child).typeCheck(s);
		} else {
			VarNode var = (VarNode) children.get(0);
			t = s.lookup(var);
		}
		if(!t.isArray()) {
			String message = "TypeError: can't do an array lookup from a non array at " + child.position();
			throw new TypeError(message);
		}
		if(t.getArraySize().length < children.size() - 1) { // this was originally > but I think this is right now?
			String message = "TypeError: array doesn't have that many dimensions at " + child.position();
			throw new TypeError(message);
		}
		
		for(int i = 1; i < children.size(); i++) {
			ExpressionNode e = (ExpressionNode) children.get(i);
			if(!e.typeCheck(s).isInt()) {
				String message = "TypeError: array lookup index must be of type int at " + e.position();
				throw new TypeError(message);
			}
		}
		setType(t.subtractDimensions(children.size() - 1, this.position()));
		return t.subtractDimensions(children.size() - 1, this.position());
	}
	
	// DEPRECATED in favor of Gtranslate
	/*
	public SyntaxIR translate(HashMap<String, String> map) {
		// creates an arraylist of all the children translated
		ArrayList<ExpressionIR> indeces = new ArrayList<ExpressionIR>();
		if(map == null) {
			indeces.add(new TempIR(id, getSymTable().lookUp(id)));
		} else if(map.get(id) != null) {
			try{
				indeces.add(new TempIR(id,map.get(id)));
			} catch(Exception e) {
				indeces.add(new TempIR(id,map.get(id)));
			}

		} else{ 
			indeces.add(new TempIR(id, getSymTable().lookUp(id)));
		}
		for(int i = 1; i < getChildren().size(); i ++) {
			indeces.add((ExpressionIR)((SyntaxNode)(getChildren().get(i))).translate(map));
		}
		MemIR memoryIR;
		//look up the index of the first array
		//create a constant 3
		ConstIR three = new ConstIR(3);
		//perform a lshift with the 2nd child and 3 (multiply by 8)
		OpIR lshift = new OpIR(opEnum.LSHIFT, indeces.get(1), three);
		//add that lshifted index to the translation of the name of the array
		OpIR addingIR = new OpIR(opEnum.PLUS, indeces.get(0), lshift);
		//look that up in memory
		memoryIR = new MemIR(addingIR);
		for(int i = 1; i < indeces.size()-1; i ++) {
			lshift = new OpIR(opEnum.LSHIFT, indeces.get(i+1), three);
			addingIR = new OpIR(opEnum.PLUS, memoryIR, lshift);
			memoryIR = new MemIR(addingIR);
		}
		return memoryIR;
	}
	*/

	
	/*  Somewhat hacked together for array out of bounds stuff 
	 *  but it works and is built to be more robust against possible
	 *  problems we're currently having with folding cjumps
	 */
	
	public SyntaxIR translate(HashMap<String, String> map) {
		// creates an arraylist of all the children translated
		ArrayList<ExpressionIR> indeces = new ArrayList<ExpressionIR>();
		HashMap<Integer, TempIR> tempmap = new HashMap<Integer, TempIR>();
		if(map == null) {
			//System.out.println("tempID " + getSymTable().lookUp(id));
			indeces.add(new TempIR(id, getSymTable().lookUp(id)));
		} else if(map.get(id) != null) {
			try{
				//System.out.println("tempID " + map.get(id));
				indeces.add(new TempIR(id,map.get(id)));
			} catch(Exception e) {
				indeces.add(new TempIR(id,map.get(id)));
			}
		} else{ 
			indeces.add(new TempIR(id, getSymTable().lookUp(id)));
		}
		EseqIR seq = new EseqIR();
		for(int i = 1; i < getChildren().size(); i ++) {
			indeces.add((ExpressionIR)((SyntaxNode)(getChildren().get(i))).translate(map));
			//System.out.println(((SyntaxNode) getChildren().get(i)).getChildren().get(0));
			if(((SyntaxNode) getChildren().get(i)).getChildren().get(0) instanceof ArrayLookUpNode) {
				TempIR index = new TempIR("index"+SyntaxIR.getFreshLabel());
				tempmap.put(i-1, index);
				seq.addChild(new MoveIR(index, new ConstIR(0)));
			}
		}
		MemIR memoryIR;
		//look up the index of the first array
		//create a constant 3
		ConstIR three = new ConstIR(3);
		//perform a lshift with the 2nd child and 3 (multiply by 8)
		OpIR lshift = new OpIR(opEnum.LSHIFT, indeces.get(1), three);
		//add that lshifted index to the translation of the name of the array
		OpIR addingIR = new OpIR(opEnum.PLUS, indeces.get(0), lshift);
		//look that up in memory
		memoryIR = new MemIR(addingIR);
		//LabelIR blockend = new LabelIR("_block"+SyntaxIR.getFreshLabel()+"End_2");
		for(int i = 0; i < indeces.size()-1; i ++) {
			LabelIR lf = new LabelIR("f_outOfBounds" + SyntaxIR.getFreshLabel());	//true
			LabelIR lt = new LabelIR("t_inBounds" + SyntaxIR.getFreshLabel());	//false
			LabelIR lb = new LabelIR("b_lookup" + SyntaxIR.getFreshLabel());
			TempIR index = new TempIR("index"+SyntaxIR.getFreshLabel());
			TempIR arrAddr = new TempIR("arrAddr"+SyntaxIR.getFreshLabel());
			if(tempmap.get(i) != null) {
				seq.addChild(new MoveIR(tempmap.get(i), indeces.get(i+1)));
			} else {
				seq.addChild(new MoveIR(index, indeces.get(i+1)));
			}
			if(i == 0) {
				seq.addChild(new MoveIR(arrAddr, indeces.get(0)));
			} else{
				seq.addChild(new MoveIR(arrAddr, memoryIR));
			}
			ExpressionIR cond;
			if(tempmap.get(i) != null) {
				cond = new OpIR(opEnum.AND, new OpIR(opEnum.GT, tempmap.get(i), new ConstIR(-1)), new OpIR(opEnum.LT, tempmap.get(i), new MemIR(new OpIR(opEnum.MINUS, arrAddr, new ConstIR(8)))));
			} else {
				cond = new OpIR(opEnum.AND, new OpIR(opEnum.GT, index, new ConstIR(-1)), new OpIR(opEnum.LT, index, new MemIR(new OpIR(opEnum.MINUS, arrAddr, new ConstIR(8)))));
			}
			
			
			// THIS IS WHAT IT WAS BEFORE ADDING THE INDEX OUT OF BOUNDS STUFF
			//cond = new OpIR(opEnum.LT, index, new MemIR(new OpIR(opEnum.MINUS, arrAddr, new ConstIR(8))));
			CJumpIR cjump = new CJumpIR(cond, lt.label(), lf.label(),false,false);
			seq.addChild(cjump);
			seq.addChild(lf);
			//seq.addChild(blockend);
			seq.addChild(new ExpIR(new CallIR(new NameIR("_I_outOfBounds_p"))));
			//seq.addChild(new JumpIR(new NameIR(lb.label())));
			seq.addChild(lt);
			if(tempmap.get(i) != null) {
				lshift = new OpIR(opEnum.LSHIFT, tempmap.get(i), three);
			} else {
				lshift = new OpIR(opEnum.LSHIFT, index, three);
			}
			//lshift = new OpIR(opEnum.LSHIFT, index, three);
			addingIR = new OpIR(opEnum.PLUS, arrAddr, lshift);
			memoryIR = new MemIR(addingIR);
			seq.addChild(new JumpIR(new NameIR(lb.label())));
			
			//seq.addChild(blockend);
			seq.addChild(lb);
		}	
		seq.addChild(memoryIR);
		return seq;
	}
}
