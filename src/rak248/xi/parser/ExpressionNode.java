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
import rak248.xi.ir.ExpressionIR;
import rak248.xi.ir.JumpIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.TempIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;


public class ExpressionNode extends SyntaxNode{

	boolean parFlag;
	Type e1T;
	private ArrayList<ExpressionNode> bracketIndex;
	
	public ExpressionNode() {
		parFlag = false;
		setLabel("Expression DEADBEEF");
	}
	
	public ExpressionNode(ExpressionNode e1, MathFunctionNode m, ExpressionNode e2, Position position, Position position2) {
		parFlag = false;
		addChild(e1);
		addChild(m);
		addChild(e2);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("mathematical function");
	}
	
	public ExpressionNode(ExpressionNode e1, ComparisonOperatorNode m, ExpressionNode e2, Position position, Position position2) {
		parFlag = false;
		addChild(e1);
		addChild(m);
		addChild(e2);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("comparison function");
	}

	//not node or minus node is the first argument
	public ExpressionNode(SyntaxNode n, SyntaxNode f, Position position, Position position2) {
		parFlag = false;
		addChild(n);
		addChild(f);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("unary expression");
	}

	public ExpressionNode(SyntaxNode f, Position position, Position position2) {
		parFlag = false;
		addChild(f);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		if(f instanceof ExpressionNode) {
			parFlag = ((ExpressionNode) f).getParFlag();
		}
		setLabel("expression");
	}
	
	public ExpressionNode(String s, ExpressionNode e, Position position, Position position2){
		parFlag = false;
		if(!s.equals("length")) {
			System.err.println("Should never use this constructor with a string that isn't length");
		}
		setLabel(s);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		addChild(e);
	}

	public ExpressionNode(ArrayList<SyntaxNode> l) {
		setLabel("expression list");
		for(SyntaxNode s: l){
			addChild(s);
		}
	}
	
	public ExpressionNode(ArrayList<ExpressionNode> e, Position position, Position position2) {
		if (e.size() == 1){
			addChild(e.get(0));
			Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
			setPosition(newPos);
			setLabel("expression");
		}
		else{
			Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
			setPosition(newPos);
			ListNode ln = new ListNode(e,position,position2);
			addChild(ln);
			setLabel("List Literal: "+ln.toString());
		}
	}

	public ExpressionNode(ArrayList<ExpressionNode> e,
			ArrayList<ExpressionNode> b, Position position, Position position2) {
		if (b == null){
			if (e.size() == 1){
				addChild(e.get(0));
				Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
				setPosition(newPos);
				setLabel("expression");
				e.get(0).setParFlag(true);
			}
			else{
				Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
				setPosition(newPos);
				ListNode ln = new ListNode(e,position,position2);
				addChild(ln);
				setLabel("List Literal: "+ln.toString());
			}
		}
		else{
			ListNode ln = new ListNode(e,position,position2);
			addChild(ln);
			Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
			setPosition(newPos);
			bracketIndex = b;
			setLabel("Inline Array Lookup: " + ln.toString() + " at location: "+b.toString());
		}
	}

	public Type typeCheck(SymbolTable s) throws TypeError, UndeclaredIdentifierException {
		setSymTable(s);
		print = true;
		ArrayList<VisualizableTreeNode> children = getChildren();
		int size = children.size();
		/*
		 * Checks first if the element is of one size.  That mean's its either 1 fil or
		 * a function call length(string).
		 */
		if (label().startsWith("Inline Array Lookup: ")){
			Type arr = ((ListNode) getChildren().get(0)).typeCheck(s);
			for (ExpressionNode e: bracketIndex){
				Type t = e.typeCheck(s);
				if (!t.isInt()){
					throw new TypeError("TypeError: bracket index is not an int" + this.position());
				}
			}
			return arr.subtractDimensions(bracketIndex.size(), this.position());
		}
		else if(size == 1) {
			SyntaxNode child = (SyntaxNode) children.get(0);
			/* ensures that if it's a length call, that the type is an array */
			if(label().equals("length")) {
				if(child.typeCheck(s).isArray()) {
					e1T = new Type(typeEnum.INT);
					return e1T;
				} else {
					String message = "TypeError: length expects an array at " + this.position();
					throw new TypeError(message);
				}
			// if it's not a length call, it's a FIL
			} else {
				e1T = child.typeCheck(s);
				return e1T;
			}
		}
		/*
		 * If there's 2 elements, it's either a not node and a bool or 2 FILs
		 */
		else if (size == 2) {
			SyntaxNode n = ((SyntaxNode) children.get(0));
			Type t = ((SyntaxNode) children.get(1)).typeCheck(s);
			e1T = t;
			// ensure that if it's a not node, it's being used with a bool node
			if(n instanceof MinusNode) {
				if(!t.isInt()) {
					String message = "TypeError: unary operator - requires an int argument at " + this.position();
					throw new TypeError(message);
				} else {
					return t;
				}
			}
			if(n instanceof NotNode) {
				if(!t.isBool()) {
					String message = "TypeError: unary operator ! requires a bool argument at " + this.position();
					throw new TypeError(message);
				} else {
					return new Type(typeEnum.BOOL);
				}
				
			}
			throw new TypeError("Should never happen in ExpressionNode");
		/*
		 * If it's 3 children, it's either an OP(e1, e2)
		 */
		} 
		else if(size == 0){
			String[] sp = this.toString().split(" ");
			if(!sp[0].equals("New") || !sp[2].equals("Declaration")) {
				System.err.println("Daniel's string split hack was a bad idea");
				return null;
			}
			return new Type(typeEnum.ABSTRACT, sp[1]);
		}else {
			/*for(VisualizableTreeNode child:children) {
				if(((SyntaxNode) child).getChildren().size() > 0)
					System.out.println("expr node children:"+((SyntaxNode) child).getChildren().get(0));
			}*/
			Type t1 = ((ExpressionNode) children.get(0)).typeCheck(s);
			e1T = t1;
			SyntaxNode op = ((SyntaxNode) children.get(1));
			Type t2 = op.typeCheck(s);
			Type t3 = ((ExpressionNode) children.get(2)).typeCheck(s);
			/*
			 * See if its a math function or a comparison operator
			 * Ensure the types on either side are both int or both bool.
			 */
			if(op instanceof MathFunctionNode) {
				if(t1.isInt() && t3.isInt()) {
					return new Type(typeEnum.INT);
				}
				else if(t1.isArray() && t3.isArray() && t1.equals(t3) && ((MathFunctionNode) op).getTypeOfMathOp() == Sym.PLUS) {
					return t1;
				}
				else if(t1.isInt() && (((ExpressionNode) children.get(0)).getParFlag() == true) && t3.isIntArray() && ((MathFunctionNode) op).getTypeOfMathOp() == Sym.PLUS) {
					ArrayList<ExpressionNode> el = new ArrayList<ExpressionNode>();
					el.add((ExpressionNode) children.get(0));
					ListNode ln = new ListNode(el, ((SyntaxNode) children.get(0)).position(),  ((SyntaxNode) children.get(0)).position());
					children.set(0,ln);
					t1 = ((SyntaxNode) children.get(0)).typeCheck(s);
					e1T = t3;
					return t3;
				}
				else if(t3.isInt() && (((ExpressionNode) children.get(2)).getParFlag() == true) && t1.isIntArray() && ((MathFunctionNode) op).getTypeOfMathOp() == Sym.PLUS) {
					ArrayList<ExpressionNode> el = new ArrayList<ExpressionNode>();
					el.add((ExpressionNode) children.get(2));
					ListNode ln = new ListNode(el, ((SyntaxNode) children.get(2)).position(),  ((SyntaxNode) children.get(2)).position());
					children.set(2,ln);
					t1 = ((SyntaxNode) children.get(2)).typeCheck(s);
					e1T = t1;
					return t1;
				}
				else if(t1.isBool() && (((ExpressionNode) children.get(0)).getParFlag() == true) && t3.isBoolArray() && ((MathFunctionNode) op).getTypeOfMathOp() == Sym.PLUS) {
					ArrayList<ExpressionNode> el = new ArrayList<ExpressionNode>();
					el.add((ExpressionNode) children.get(0));
					ListNode ln = new ListNode(el, ((SyntaxNode) children.get(0)).position(),  ((SyntaxNode) children.get(0)).position());
					children.set(0,ln);
					t1 = ((SyntaxNode) children.get(0)).typeCheck(s);
					e1T = t3;
					return t3;
				}
				else if(t3.isBool() && (((ExpressionNode) children.get(2)).getParFlag() == true) && t1.isBoolArray() && ((MathFunctionNode) op).getTypeOfMathOp() == Sym.PLUS) {
					ArrayList<ExpressionNode> el = new ArrayList<ExpressionNode>();
					el.add((ExpressionNode) children.get(2));
					ListNode ln = new ListNode(el, ((SyntaxNode) children.get(2)).position(),  ((SyntaxNode) children.get(2)).position());
					children.set(2,ln);
					t1 = ((SyntaxNode) children.get(2)).typeCheck(s);
					e1T = t1;
					return t1;
				}
				else {
					String message = "TypeError: unsupported operand type(s) for " + op.label() + ": '"+ t1.toString() +"' and '" + t3.toString() + "' at " + this.position();
					throw new TypeError(message);
				}
			}
			
			else if(op instanceof ComparisonOperatorNode) {
				boolean eqTestHoist = ((ComparisonOperatorNode) op).checksEquality();
				if(eqTestHoist) {
					if(t1.isAbstract() && t3.isAbstract()) {
						Type currentClassType = new Type(typeEnum.ABSTRACT, s.getCurrentClass());
						eqTestHoist = t1.equals(currentClassType) || t3.equals(currentClassType);
					} else {
						eqTestHoist = t1.equals(t3);
					}
				}
				
				if(eqTestHoist) {
					return new Type(typeEnum.BOOL);
				}
				else if(((ComparisonOperatorNode) op).isBoolOp() && t1.isBool() && t3.isBool()) {
					return t1;
				} else if(!((ComparisonOperatorNode) op).isBoolOp() && t1.isInt() && t3.isInt()) {
					return new Type(typeEnum.BOOL);
				}
				else if(((ComparisonOperatorNode) op).isBoolOp() && t1.isNull())
					return new Type(typeEnum.BOOL);
				else if(((ComparisonOperatorNode) op).isBoolOp() && t3.isNull())
					return new Type(typeEnum.BOOL);
				else {
					String message = "TypeError: unsupported operand type(s) for " + op.label() + ": '"+ t1.toString() +"' and '" + t3.toString() + "' at " + this.position();
					throw new TypeError(message);
				}
			}
			throw new TypeError("Should never happen in ExpressionNode");
		}
	}
	
	public SyntaxIR translate(HashMap<String, String> map){
		ArrayList<VisualizableTreeNode> children = getChildren();
		int size = children.size();
		if (label().startsWith("Inline Array Lookup: ")){
			EseqIR child = (EseqIR) ((ListNode) children.get(0)).translate(map);
			EseqIR eseq = new EseqIR();
			TempIR temp = new TempIR("_inline"+SyntaxIR.getFreshLabel());
			MoveIR move = new MoveIR(temp,child);
			eseq.addChild(move);
			for (ExpressionNode exp: bracketIndex){
				ExpressionIR e = (ExpressionIR) exp.translate(map);
				OpIR op = new OpIR(opEnum.PLUS,temp,new OpIR(opEnum.LSHIFT,e,new ConstIR(3)));
				MemIR mem = new MemIR(op);
				MoveIR m = new MoveIR(temp,mem);
				eseq.addChild(m);
			}
			eseq.addChild(temp);
			return eseq;
		}
		else if(size == 1) {
			SyntaxNode child = (SyntaxNode) children.get(0);
			/* ensures that if it's a length call, that the type is an array */
			if(label().equals("length")) {
				Type typ;
				try {
					typ = ((SyntaxNode) children.get(0)).typeCheck(getSymTable());
					SyntaxIR m = child.translate(map);
					OpIR op = new OpIR(OpIR.opEnum.MINUS,(ExpressionIR) m,new ConstIR(8));
					return new MemIR(op);
					//Type[] param = {typ};
					//Type[] rets = {new Type(typeEnum.INT)};
					//typ = new Type(typeEnum.FUNCTION,param,rets);
					//String mangled = Mangler.mangle("length", typ);
					//return new CallIR(new NameIR(mangled), child.translate(map));
				} catch (UndeclaredIdentifierException e) {
					System.err.println("length of the variable could be looked up");
				} catch (TypeError e) {
					System.err.println("this should not happen because type check as already been done");
				}
			// if it's not a length call, it's a FIL
			} else {
				return child.translate(map);
			}
		} else if (size == 2) {
			SyntaxNode n = ((SyntaxNode) children.get(0));
			SyntaxNode e = (SyntaxNode) children.get(1);
			// ensure that if it's a not node, it's being used with a bool node
			if(n instanceof MinusNode) {
				return new OpIR(opEnum.UNARY_MINUS, e.translate(map)); //
			}
			if(n instanceof NotNode) {
				return new OpIR(opEnum.NOT, e.translate(map));
			}
		} else {
			//System.out.println("e node symtab:"+map);
			ExpressionIR e1 = (ExpressionIR) ((ExpressionNode) children.get(0)).translate(map);
			SyntaxNode op = ((SyntaxNode) children.get(1));
			ExpressionIR e2 = (ExpressionIR) ((ExpressionNode) children.get(2)).translate(map);
			opEnum i = opEnum.DIVIDE; // just so it compiles
			if(op instanceof MathFunctionNode) {
				switch(((MathFunctionNode) op).getTypeOfMathOp()) {
				case Sym.DIVIDE:
					i = opEnum.DIVIDE;
					break;
				case Sym.MINUS:
					i = opEnum.MINUS;
					break;
				case Sym.PLUS:
					i = opEnum.PLUS;
					if(e1T.isArray()) {
						EseqIR seq = new EseqIR();
						TempIR array1 = new TempIR("Array1-" + getSymTable().getFreshName());
						int numTemps = 1;
						TempIR array2 = new TempIR("Array2-" + getSymTable().getFreshName());
						numTemps++;
						seq.addChild(new MoveIR(array1, e1));
						seq.addChild(new MoveIR(array2, e2));
						TempIR arrLen1 = new TempIR("Array1Len-" + getSymTable().getFreshName());
						numTemps++;
						seq.addChild(new MoveIR(arrLen1, new MemIR(new OpIR(opEnum.MINUS, array1, new ConstIR(8)))));
						TempIR newArrSize = new TempIR("newSize" + getSymTable().getFreshName());
						numTemps++;
						seq.addChild(new MoveIR(newArrSize, new OpIR(opEnum.PLUS, arrLen1, new MemIR(new OpIR(opEnum.MINUS, array2, new ConstIR(8))))));
						CallIR call = new CallIR(new NameIR("_I_alloc_i"), new OpIR(opEnum.TIMES, new ConstIR(8), new OpIR(opEnum.PLUS, newArrSize, new ConstIR(1))));
						TempIR start = new TempIR(getSymTable().getFreshName());
						numTemps++;
						seq.addChild(new MoveIR(start, call));
						MemIR mem = new MemIR(start);

						//move the size of the array into the start of the list
						MoveIR move = new MoveIR(mem, newArrSize);
						seq.addChild(move);
						seq.addChild(new MoveIR(start, new OpIR(opEnum.PLUS, start, new ConstIR(8))));
						
						LabelIR lh = new LabelIR("w_head" + SyntaxIR.getFreshLabel());	//head
						LabelIR lt = new LabelIR("w_true" + SyntaxIR.getFreshLabel());	//true
						LabelIR lf = new LabelIR("w_false" + SyntaxIR.getFreshLabel());	//false
						TempIR ct = new TempIR("ct-"+SyntaxIR.getFreshLabel());
						numTemps++;
						seq.addChild(new MoveIR(ct, new ConstIR(0)));
						
						seq.addChild(lh);
						seq.addChild(new CJumpIR(new OpIR(opEnum.NOT, (new OpIR(opEnum.LT, ct, arrLen1))), lt.label(), lf.label(), false, true));
						seq.addChild(lf);
						OpIR dst = new OpIR(opEnum.PLUS, start, new OpIR(opEnum.TIMES, ct, new ConstIR(8)));
						OpIR src = new OpIR(opEnum.PLUS, array1, new OpIR(opEnum.TIMES, ct, new ConstIR(8)));
						seq.addChild(new MoveIR(new MemIR(dst), new MemIR(src)));
						seq.addChild(new MoveIR(ct, new OpIR(opEnum.PLUS, ct, new ConstIR(1))));
						seq.addChild(new JumpIR(new NameIR(lh.label())));
						seq.addChild(lt);
						
						LabelIR lh2 = new LabelIR("w_head" + SyntaxIR.getFreshLabel());	//head
						LabelIR lt2 = new LabelIR("w_true" + SyntaxIR.getFreshLabel());	//true
						LabelIR lf2 = new LabelIR("w_false" + SyntaxIR.getFreshLabel());	//false
						
						TempIR ct2 = new TempIR("ct2-"+SyntaxIR.getFreshLabel());
						numTemps++;
						seq.addChild(new MoveIR(ct2, new ConstIR(0)));
						seq.addChild(lh2);
						seq.addChild(new CJumpIR(new OpIR(opEnum.NOT, new OpIR(opEnum.LT, ct, newArrSize)), lt2.label(), lf2.label(), false, true));
						seq.addChild(lf2);
						
						dst = new OpIR(opEnum.PLUS, start, new OpIR(opEnum.TIMES, ct, new ConstIR(8)));
						src = new OpIR(opEnum.PLUS, array2, new OpIR(opEnum.TIMES, ct2, new ConstIR(8)));
						seq.addChild(new MoveIR(new MemIR(dst), new MemIR(src)));
						seq.addChild(new MoveIR(ct, new OpIR(opEnum.PLUS, ct, new ConstIR(1))));
						seq.addChild(new MoveIR(ct2, new OpIR(opEnum.PLUS, ct2, new ConstIR(1))));
						seq.addChild(new JumpIR(new NameIR(lh2.label())));
						seq.addChild(lt2);
						
						//Add a label for the end of the block to facilitate removal of variables in this scope
						//we append the number of local variables to make code generation simple
						//seq.addChild(new LabelIR("_block"+SyntaxIR.getFreshLabel()+"End_"+numTemps));
						//We do it before start since this is an Eseq
						
						seq.addChild(start);
						return seq;
					}
					break;
				case Sym.TIMES:
					i = opEnum.TIMES;
					break;
				case Sym.MODULO:
					i = opEnum.MODULO;
					break;
				default:
					System.err.println("Didn't catch op in ExpressionNode math op translate");
				}
				return new OpIR(i, e1, e2);
			} else if(op instanceof ComparisonOperatorNode) {
				if(((ComparisonOperatorNode) op).getTypeOfComparison() == Sym.OR) {
					EseqIR seq = new EseqIR();
					LabelIR lt = new LabelIR("shortOrTrue" + SyntaxIR.getFreshLabel());
					LabelIR lf = new LabelIR("shortOrFalse" + SyntaxIR.getFreshLabel());
					LabelIR lb = new LabelIR("shortOrBottom" + SyntaxIR.getFreshLabel());
					TempIR out = new TempIR("orResult"+SyntaxIR.getFreshLabel());
					CJumpIR cjump = new CJumpIR(e1, lt.label(), lf.label(),false,true);
					seq.addChild(new MoveIR(out, new ConstIR(0)));
					seq.addChild(cjump);
					seq.addChild(new JumpIR(new NameIR(lf.label())));
					seq.addChild(lt);
					seq.addChild(new MoveIR(out, new ConstIR(1, "true")));
					seq.addChild(new JumpIR(new NameIR(lb.label())));
					seq.addChild(lf);
					seq.addChild(new MoveIR(out, e2));
					seq.addChild(new JumpIR(new NameIR(lb.label())));
					seq.addChild(lb);
					
					//Add a label for the end of the block to facilitate removal of variables in this scope
					//we append the number of local variables to make code generation simple
					int numTemps = 1;
					//seq.addChild(new LabelIR("_block"+SyntaxIR.getFreshLabel()+"End_"+numTemps));
					//We do it before out since this is an Eseq
					
					seq.addChild(out);					
					return seq;
				}
				
				if(((ComparisonOperatorNode) op).getTypeOfComparison() == Sym.AND) {
					EseqIR seq = new EseqIR();
					LabelIR lt = new LabelIR("shortANDTrue" + SyntaxIR.getFreshLabel());
					LabelIR lf = new LabelIR("shortANDFalse" + SyntaxIR.getFreshLabel());
					LabelIR lb = new LabelIR("shortANDBottom" + SyntaxIR.getFreshLabel());
					TempIR out = new TempIR("andResult"+SyntaxIR.getFreshLabel());
					CJumpIR cjump = new CJumpIR(e1, lt.label(), lf.label(),false,true);
					seq.addChild(new MoveIR(out, new ConstIR(0)));
					seq.addChild(cjump);
					seq.addChild(lf);
					seq.addChild(new MoveIR(out, new ConstIR(0, "false")));
					seq.addChild(new JumpIR(new NameIR(lb.label())));
					seq.addChild(lt);
					seq.addChild(new MoveIR(out, e2));
					seq.addChild(new JumpIR(new NameIR(lb.label())));
					seq.addChild(lb);
					
					//Add a label for the end of the block to facilitate removal of variables in this scope
					//we append the number of local variables to make code generation simple
					int numTemps = 1;
					//seq.addChild(new LabelIR("_block"+SyntaxIR.getFreshLabel()+"End_"+numTemps));
					//We do it before out since this is an Eseq
					
					seq.addChild(out);
					return seq;
				}
				
				switch(((ComparisonOperatorNode) op).getTypeOfComparison()) {
				case Sym.NOT_EQUAL:
					i = opEnum.NOT_EQUAL;
					break;
				case Sym.LT:
					i = opEnum.LT;
					break;
				case Sym.GEQ:
					i = opEnum.GEQ;
					break;
				case Sym.EQUAL:
					i = opEnum.EQUAL;
					break;
				case Sym.LEQ:
					i = opEnum.LEQ;
					break;
				case Sym.GT:
					i = opEnum.GT;
					break;
				default:
					System.err.println("Didn't catch op in ExpressionNode comparison op translate"); // should never happen
				}
				return new OpIR(i, e1, e2);
			}
		}
		return null;
	}
	
	public void setParFlag(boolean flag) {
		parFlag = flag;
	}
	
	public boolean getParFlag() {
		return parFlag;
	}
	
	public void setTypeExpr(Type t) {
		e1T = t;
	}
}