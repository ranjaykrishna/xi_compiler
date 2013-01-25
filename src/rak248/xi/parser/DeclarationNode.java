package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.CJumpIR;
import rak248.xi.ir.CallIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.ExpressionIR;
import rak248.xi.ir.JumpIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.ir.SeqIR;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.DeclaredIdentifierException;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;

public class DeclarationNode extends StatementNode {

	private String id;
	private Type t;
	private ArrayList<String> ids;
	private ArrayList<DeclarationNode> decList;
	private TempIR endTemp;
	private ArrayList<TempIR> endTempList;

	public DeclarationNode(String id, Type t, Position position,
			Position position2) {
		// addChild(new LHSNode(id,t,position,position));
		Position newPos = new Location(position.unit(), position.lineStart(),
				position2.lineEnd(), position.columnStart(), position2
						.columnEnd());
		setPosition(newPos);
		this.id = id;
		this.t = t;
		ids = null;
		setLabel("decl:" + id + ", type: " + t.toString());
		endTemp = null;
		endTempList = null;
	}
	
	
	public DeclarationNode(ArrayList<String> id2, Type t2, Position position) {
		Position newPos = new Location(position.unit(), position.lineStart(),
				position.lineEnd(), position.columnStart(), position
						.columnEnd());
		setPosition(newPos);
		ids = id2;
		id = null;
		this.t = t2;
		decList = new ArrayList<DeclarationNode>();
		for(String id2L : id2) {
			decList.add(new DeclarationNode(id2L, t2, position, position));
		}
		setLabel("decl:" + ids +", type: " + t.toString());
		endTemp = null;
		endTempList = new ArrayList<TempIR>();
	}
	
	public String getId(){
		return id;
	}
	
	public Type getDeclarationType(){
		return t;
	}
	
	public ArrayList<String> getAllIds() {
		ArrayList<String> ret = new ArrayList<String>();
		if(ids != null) {
			ret.addAll(ids);
		}
		if(id != null) {
			ret.add(id);
		}
		return ret;
	}
	
	public ArrayList<DeclarationNode> getDecList(){
		return decList;
	}
	
	public Type typeCheck(SymbolTable table)
			throws UndeclaredIdentifierException, TypeError,
			DeclaredIdentifierException {
		setSymTable(table);
		if(ids != null) {
			for(DeclarationNode decNode : decList) {
				decNode.typeCheck(table);
			}
			return new Type(typeEnum.UNIT);
		}
		VarNode vNode = new VarNode(id, position(), position());
		Type temp = null;
		try {
			temp = table.lookup(vNode);
		}
		// if it's not in scope yet...
		catch (UndeclaredIdentifierException e) {
			// add it to the table
			if (t.isArray()) {
				ArrayList<ExpressionNode> sizeList = t.getSizeList();
				for (ExpressionNode ele : sizeList) {
					Type temporary = ele.typeCheck(table);
					if (!temporary.isInt()) {
						String message = "Expected int for size of array. Got "
								+ temporary + ".";
						message += " Occurred at position: " + this.position();
						throw new TypeError(message);
					}
				}
			}
			table.add(vNode, t);
			return new Type(typeEnum.UNIT);
		}
		// if it is in scope, check the type is the same.
		if (!temp.equals(t)) {
			String message = "Identifier '" + id
					+ "' already declared with a different type in this scope.";
			message += "Second declaration occured at position: "
					+ this.position();
			throw new TypeError(message);
		}
		// throws an error because its being declared again in the same scope
		else if (table.isInCurrentScope(vNode)) {
			String message = "Identifier '" + id
					+ "' already declared in this scope.";
			throw new DeclaredIdentifierException(message, this.position());
		}
		/**
		 * resolve shadowing else{ int i = 0; while(table.lookup2(vNode) !=
		 * null){ i++; vNode = new VarNode(id+i,position(),position()); }
		 * this.id = id+i; setLabel("decl: "+id+", type: "+t.toString());
		 * table.add(vNode, t); }
		 */
		// return the type of what is being assigned
		return new Type(typeEnum.UNIT);
	}

	public SyntaxIR translate(HashMap<String, String> map) {
		if(ids != null) {
			SeqIR seq = new SeqIR();
			for(DeclarationNode decNode:decList) {
				seq.addChild(decNode.translate(map));
				endTempList.add(decNode.endTemp);
			}
			return seq;
		}
		//first value is the one we care about since we need a mutable value
		//Too bad Integer is immutable
		int[] numTemps = {0};
		if(map == null) {                                     // TODO: THERE IS NOT WAY THAT THIS IS CORRECT
			String tempid = getSymTable().getFreshName(id);   //       BUT THIS IS HOW IT WAS BEFORE MERGING
			SeqIR s = new SeqIR();
			TempIR dest = new TempIR(id, tempid);
			s.addIR(new MoveIR(dest, new ConstIR(0)));
			endTemp = dest;
			return s;                                        //       TRANSLATES
		}
		String tempid = getSymTable().getFreshName(id);
		if (t.isArray()) {
			ArrayList<ExpressionNode> sizeList = t.getSizeList();
			// -move(size, MUL(ALL SIZES))
			// CHECK TO SEE IF GETSIZELIST IS NULLS OR WHATEVER
			// IT IS WHEN THERE ARE NO INTS BUT JUST DIMENSIONS
			/*ExpressionIR numElements;
			if (sizeList.size() == 1) {
				numElements = (ExpressionIR) sizeList.get(0).translate(map);
			} else { // Why do we go backwards to multiply these?
				numElements = (ExpressionIR) sizeList.get(sizeList.size() - 1)
						.translate(map);
				for (int i = sizeList.size() - 2; i >= 0; i--) {
					numElements = new OpIR(opEnum.TIMES,
							(ExpressionIR) sizeList.get(i).translate(map),
							numElements);
				}
			}*/
			SeqIR seq = new SeqIR();
			TempIR dest = new TempIR(id, tempid);
			seq.addChild(new MoveIR(dest,new ConstIR(0)));
			TempIR size = new TempIR("_dim"+SyntaxIR.getFreshLabel()+"size");
			numTemps[0]++;
			//add(new VarNode(size.label(),nullPos,nullPos), null);
			seq.addChild(new MoveIR(size, sizeList.get(0).translate(map)));
			
			// allocate memory
			CallIR call = new CallIR(new NameIR("_I_alloc_i"), new OpIR(
					                        opEnum.LSHIFT,new OpIR(opEnum.PLUS, size, new ConstIR(1)),new ConstIR(3)));
			ExpressionIR a = new OpIR(opEnum.PLUS, call, new ConstIR(8));
			TempIR memAddress = new TempIR("_mem"+SyntaxIR.getFreshLabel()+"Address");
			numTemps[0]++;
			seq.addChild(new MoveIR(memAddress, a));
			
			// sets the length
			seq.addChild(new MoveIR(new MemIR(new OpIR(opEnum.MINUS, memAddress, new ConstIR(8))), size));
			
			ArrayList<TempIR> sizeListTranslate = new ArrayList<TempIR>();
			sizeListTranslate.add(size);
			recursiveInit(seq, sizeList.size()-1, memAddress, 1, sizeList,map,sizeListTranslate,numTemps);
			
			/*TempIR temp = new TempIR("arraySize");
			MoveIR move = new MoveIR(temp, numElements);
			seq.addChild(move);
			CallIR call = new CallIR(new NameIR("_I_alloc_i"), new OpIR(
					opEnum.PLUS, temp, new ConstIR(sizeList.size())));
			ExpressionIR memAddress = new OpIR(opEnum.PLUS, call,
					new ConstIR(8));*/
			seq.addChild(new MoveIR(dest, memAddress));
			map.put(id, tempid);
			
			//System.out.println("SEQ:"+seq);
			//Add label for number of temps to take off stack
			//seq.addChild(new LabelIR("_block"+SyntaxIR.getFreshLabel()+"End_"+numTemps[0]));
			endTemp = dest;
			return seq;
		}
		map.put(id, tempid);
		SeqIR s = new SeqIR();
		TempIR dest = new TempIR(id, tempid);
		s.addIR(new MoveIR(dest, new ConstIR(0)));
		endTemp = dest;
		return s;
	}

	//This function initializes arrays recursively
	//Given a sequence, the number of dimensions, the pointer to the previous array
	//the current dimension, and the sizeList we init this array and all subarrays
	//Requires: The initial setup for the first dimension be completed
	public void recursiveInit(SeqIR seq, int dimsLeft, TempIR prevDim, int curDim, ArrayList<ExpressionNode> sizeList,HashMap<String,String> map,ArrayList<TempIR> sizeListTranslate, int[] numTemps){
		if(dimsLeft==0){
			//nothing left to do so just return
			return;
		}
		//Now for each element in the previous dimension we need to alloc a new array and then call this recursive init
		//on the new array pointer
		
		LabelIR lh = new LabelIR("w_head" + SyntaxIR.getFreshLabel());	//head
		LabelIR lt = new LabelIR("w_true" + SyntaxIR.getFreshLabel());	//true
		LabelIR lf = new LabelIR("w_false" + SyntaxIR.getFreshLabel());	//false
		TempIR ct = new TempIR("_ct"+SyntaxIR.getFreshLabel());
		numTemps[0]++;
		TempIR len = new TempIR("_len"+SyntaxIR.getFreshLabel());
		numTemps[0]++;
		//(ExpressionIR) sizeList.get(curDim-1).translate(map)
		//sizeListTranslate.
		seq.addChild(new MoveIR(len, sizeListTranslate.get(curDim-1)));
		seq.addChild(new MoveIR(ct, new ConstIR(0)));
		seq.addChild(lh);
		seq.addChild(new CJumpIR(new OpIR(opEnum.NOT, new OpIR(opEnum.LT, ct, len)), lt.label(), lf.label(),false,true)); // (OP(GT,Temp(a),0),lt,lf)
		seq.addChild(lf);//Body of while loop
		
		TempIR sizeDim = new TempIR("sizeDim"+SyntaxIR.getFreshLabel());
		numTemps[0]++;
		seq.addChild(new MoveIR(sizeDim,(ExpressionIR) sizeList.get(curDim).translate(map)));
		sizeListTranslate.add(sizeDim);
		//get the new array pointer
		CallIR call = new CallIR(new NameIR("_I_alloc_i"),new OpIR(opEnum.TIMES, 
				                   new OpIR(opEnum.PLUS,sizeListTranslate.get(curDim),new ConstIR(1)),new ConstIR(8)));
		//move the pointer to a temp
		TempIR memA = new TempIR("memA"+SyntaxIR.getFreshLabel());
		numTemps[0]++;
		seq.addChild(new MoveIR(memA, call));
		
		//put the size in the first slot of this new array
		seq.addChild(new MoveIR(new MemIR(memA),sizeList.get(curDim).translate(map)));
		//put the pointer in the slot in the previous dimension
		TempIR tempDim = new TempIR("prevDim"+SyntaxIR.getFreshLabel());
		seq.addChild(new MoveIR(tempDim,new OpIR(opEnum.PLUS,memA,new ConstIR(8))));
		seq.addChild(new MoveIR(new MemIR(new OpIR(opEnum.PLUS,prevDim,new OpIR(opEnum.LSHIFT,ct,new ConstIR(3)))), tempDim));
		
		
		numTemps[0]++;
		
		//call recursively to init the array we just created
		recursiveInit(seq, dimsLeft-1, tempDim , curDim+1, sizeList,map,sizeListTranslate,numTemps);
		//increment ct and end while
		seq.addChild(new MoveIR(ct, new OpIR(opEnum.PLUS, ct, new ConstIR(1))));
		//end body of while so we jump back to lh
		seq.addChild(new JumpIR(new NameIR(lh.label())));
		seq.addChild(lt);
		//do nothing
		return;
	}

	// write this entire thing in xi
	// create an array of size...sizeList+1
	// sizeList to tempList holding sizes	
	/*public MemIR initArray(ArrayList<ExpressionNode> sizeList, int index, SeqIR seq) {
		TempIR size = new TempIR("dim0size");
		seq.addChild(new MoveIR(size, sizeList.get(0).translate()));
		
		// allocate memory
		CallIR call = new CallIR(new NameIR("_I_alloc_i"), new OpIR(opEnum.PLUS, size, new ConstIR(1)));
		ExpressionIR a = new OpIR(opEnum.PLUS, call, new ConstIR(8));
		TempIR memAddress = new TempIR("mem0Address");
		seq.addChild(new MoveIR(memAddress, a));
		
		// sets the length
		seq.addChild(new MoveIR(new MemIR(new OpIR(opEnum.MINUS, memAddress, new ConstIR(8))), size));
		
		Stack<SyntaxIR> stack = new Stack<SyntaxIR>();
		//Start to init the rest of the dimensions (we should be able to combine these?)
		for(int i = 1; i < sizeList.size(); i++) {
			//We already have a base array so for each of those cells we need to allocate a new array
			TempIR subDimSize = new TempIR("dim"+i+"size");
			seq.addChild(new MoveIR(subDimSize,sizeList.get(i).translate()));
			//allocate memory
			CallIR subCall = new CallIR(new NameIR("_I_alloc_i"), new OpIR(opEnum.PLUS, subDimSize, new ConstIR(1)));
			ExpressionIR subA = new OpIR(opEnum.PLUS,subCall,new ConstIR(8));
			TempIR subMemAddress = new TempIR("mem"+i+"Address");
			//Move the above address into the previous dimension's array at the correct index 
			//To know where to put this we need to have stored all the previous dimensions
			seq.addChild(new MoveIR(subMemAddress,subA));
			seq.addChild(new MoveIR(new MemIR(new OpIR(opEnum.MINUS,new TempIR("mem"+(i-1)+"Address"),new ConstIR(8))),subMemAddress));
			//Next we need to go through the rest of the positions in the previous dimension to init each of their arrays
			
			LabelIR lh = new LabelIR("w_head" + SyntaxIR.getFreshLabel());	//head
			LabelIR lt = new LabelIR("w_true" + SyntaxIR.getFreshLabel());	//true
			LabelIR lf = new LabelIR("w_false" + SyntaxIR.getFreshLabel());	//false
			TempIR ct = new TempIR();
			TempIR len = new TempIR();
			seq.addChild(new MoveIR(len, new TempIR("dim"+(i-1)+"size")));
			seq.addChild(new MoveIR(ct, new ConstIR(0)));
			seq.addChild(lh);
			//seq.addChild(new CJumpIR(new OpIR(opEnum.LT, ct, len), lt.label(), lf.label())); // (OP(GT,Temp(a),0),lt,lf)
			seq.addChild(lt);//Body of while loop
			
			
			seq.addChild(lf);
			seq.addChild(new MoveIR(ct, new OpIR(opEnum.PLUS, ct, new ConstIR(1))));
			seq.addChild(new MoveIR())
		}
		while(!stack.isEmpty()) {
			seq.addChild(stack.pop());
		}
		return null;
		
		
	}*/
	/*
	 * address initialize array(d::L, seq)
	 * 
	 * addr = allocate something of size d + 1 MOVE(addr - 8, d) for other
	 * address addr + 8*i MOVE(other_addr, initialize_array(L, seq));
	 */

	/*
	 * PSEUDO-CODE FOR MORE EFFICIENT WAY OF ALLOCATING/INITIALIZING ARRAYS
	 * 
	 * size initialize_array(start_mem, d::L, seq)
	 * 
	 * start_mem - 8 = d // MOVE fPtr = start_mem + d*8 + 1 sL =
	 * initialize_array(fPtr L) for(i=1 i < d) nextPtrAddr = start_mem + 8*i
	 * pointeeArr = fPtr + sL*i Move(nextPtrAddr, pointeeArr)
	 * initialize_array(pointeeArr)
	 * 
	 * return sL*d + d + 1
	 */
	
	public TempIR getEndTemp() {
		return endTemp;
	}
}
