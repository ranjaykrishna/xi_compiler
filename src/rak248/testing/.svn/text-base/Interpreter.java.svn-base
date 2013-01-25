package rak248.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.CallIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.EseqIR;
import rak248.xi.ir.ExpIR;
import rak248.xi.ir.ExpressionIR;
import rak248.xi.ir.JumpIR;
import rak248.xi.ir.CJumpIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.ReturnIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.SeqIR;
import rak248.xi.ir.StatementIR;
import rak248.xi.ir.TempIR;

public class Interpreter {
	
	private HashMap<String, Integer> labels;
	private HashMap<String, Long> temps;
	private HashMap<Long,Long> memory;
	private Stack<Integer> returnStack;
	private SyntaxIR root;
	
	private int pointer;
	private Long finalReturn;
	private Long memPointer;
	
	private ArrayList<HashMap<String,Long>> Symtables;
	
	public Interpreter(SyntaxIR top){
		labels = new HashMap<String, Integer>();
		temps = new HashMap<String, Long>();
		memory = new HashMap<Long,Long>();
		returnStack = new Stack<Integer>();
		
		pointer = 0;
		memPointer = 0l;
		finalReturn = 0l;
		
		Symtables = new ArrayList<HashMap<String, Long>>();
		Symtables.add(temps);
		
		SeqIR program = new SeqIR("root");
		program.setChildren(top.getChildren());
		root = program;
	}
	
	/**
	 * this method is responsible for executing the program
	 * @return the int value that the main method is supposed to return
	 */
	public Long runProgram(){
		collectLabels();
		try{
			while ( true ){
				executeStatement();
			}
		}
		catch (EndOfProgramException e) {
			System.out.println("The program returned:"+finalReturn);
			return finalReturn;
			
		} 
		catch (InterpreterException e) {
			e.printStackTrace();
		}
		return finalReturn;
	}
	
	/**
	 * executes the instruction the pointer is pointing to.
	 * @throws InterpreterException
	 * @throws EndOfProgramException
	 */
	private void executeStatement() throws InterpreterException,EndOfProgramException {
		temps = Symtables.get(0);
		StatementIR smt =  (StatementIR) root.getChildren().get(pointer);
		//System.out.println("TEMPS: " + temps);
		//System.out.println(smt);
		
		if (smt instanceof JumpIR){
			String dest = ((NameIR) smt.getChildren().get(0)).getLabel();
			pointer = labels.get(dest.substring(7));
		}else if (smt instanceof CJumpIR){
			CJumpIR cj = (CJumpIR) smt;
			Long e = executeExpression((ExpressionIR) cj.getChildren().get(0));
			if (e == 1){
				String lt = cj.getlt().substring(7);
				pointer = labels.get(lt);
			}else{
				String lf = cj.getlf().substring(7);
				pointer = labels.get(lf);
			}
		}else if (smt instanceof LabelIR){
			pointer++;
		}else if (smt instanceof ExpIR){
			executeExpression( (ExpressionIR) smt.getChildren().get(0) );
			pointer++;
		}else if (smt instanceof ReturnIR){
			if (!smt.getChildren().isEmpty()){
				finalReturn = executeExpression( (ExpressionIR) smt.getChildren().get(0) );
			}
			Symtables.remove(0);
			if (returnStack.empty()){
				throw new EndOfProgramException();
			}else{
				temps = Symtables.get(0);
				pointer = returnStack.pop();
			}
		}else if (smt instanceof MoveIR){
			ExpressionIR e1 = (ExpressionIR) smt.getChildren().get(0);
			Long e2 = executeExpression( (ExpressionIR) smt.getChildren().get(1) );
			if (e1 instanceof TempIR){
				String key = ((TempIR) e1).getId();
				if(key.startsWith("__args")) {
					key = key.substring(1, key.indexOf("@"));
				}
				temps.put(key, e2);
				//System.err.println("HEREYOUGO: " + temps+" real: "+e1);
			}else{//mem
				Long s = executeExpression((ExpressionIR) e1.getChildren().get(0) );
				memory.put(s, e2);
			}
			pointer++;
		}
		
	}
	/**
	 * calculates what the expression should translate to in assembly
	 * @param expr is an ExpressionIR that we are trying to calculate
	 * @return either type int or a boolean (1 or 0)
	 * @throws InterpreterException
	 * @throws EndOfProgramException 
	 */
	private Long executeExpression(ExpressionIR expr) throws InterpreterException, EndOfProgramException{
		temps = Symtables.get(0);
		if (expr instanceof ConstIR){
			return ((ConstIR) expr).getValue();
		}else if (expr instanceof TempIR){
			TempIR temp = (TempIR) expr;
			String key = temp.getId();
			if(key.startsWith("__args")) {
				key = key.substring(1, key.indexOf("@"));
			}
			return temps.get(key);
		}
		else if (expr instanceof CallIR){
			CallIR call = (CallIR) expr;
			if (((NameIR) call.getChildren().get(0)).getLabel().contains("_I_alloc_i")){
				memPointer = memPointer+100000;
				return memPointer;
			}
			else if (((NameIR) call.getChildren().get(0)).getLabel().contains("_Ilength_i")){
				Long t = executeExpression( (ExpressionIR) call.getChildren().get(1) );
				return memory.get(t-8);
			}
			// <THIS WAS FROM INPUTINTERPRETER>
			else if (((NameIR) call.getChildren().get(0)).getLabel().contains("println")){
				ExpressionIR exx = (ExpressionIR) call.getChildren().get(1);
				if (exx.getChildren().isEmpty()){
					Long t = executeExpression( (ExpressionIR) exx );
					Long len = memory.get(t-8);
					String s = "";
					int index = 0;
					for (int i = 0; i<len; i++){
						Long b = memory.get(t+index);
						byte[] bytes = new byte[1];
						bytes[0]  = (byte) b.byteValue();
						String str = new String(bytes);
						s += str;
						index += 8;
					}
					System.out.println(s);
					return 0l;
				}
				Long t = executeExpression( (ExpressionIR) exx.getChildren().get(0) );
				Long len = memory.get(t-8);
				String s = "";
				int index = 0;
				for (int i = 0; i<len; i++){
					Long b = memory.get(t+index);
					byte[] bytes = new byte[1];
					bytes[0]  = (byte) b.byteValue();
					String str = new String(bytes);
					s += str;
					index += 8;
				}
				System.out.println(s);
				return 0l;
			}
			// <END OF CODE FROM INPUT INTERPRETER>
			/*
			else if (((NameIR) call.getChildren().get(0)).getLabel().contains("println")){
				Integer t = executeExpression( (ExpressionIR) call.getChildren().get(1) );
				return memory.get(t-8);
			}
			else if (((NameIR) call.getChildren().get(0)).getLabel().contains("println")){
				ExpressionIR exx = (ExpressionIR) call.getChildren().get(1);
				Integer t = executeExpression( (ExpressionIR) exx.getChildren().get(0) );
				int len = memory.get(t-8);
				String s = "";
				int index = 0;
				for (int i = 0; i<len; i++){
					int b = memory.get(t+index);
					byte[] bytes = new byte[1];
					bytes[0]  = (byte) b;
					String str = new String(bytes);
					s += str;
					index += 8;
				}
				System.out.println(s);
				return 0;
			}
			*/
			else if (((NameIR) call.getChildren().get(0)).getLabel().contains("_Iassert_pb")){
				Long t = executeExpression( (ExpressionIR) call.getChildren().get(1) );
				if(t.intValue() == 1) {
					return 1l;
				} else {
					throw new InterpreterException("Assertion failure");
				}
			}
			else if (((NameIR) call.getChildren().get(0)).getLabel().contains("_IunparseInt_aii")){
				Long t = executeExpression((ExpressionIR) call.getChildren().get(1));
				byte[] bytes = ("" + t).getBytes();
				memPointer += 100000;
				for(int i = 0; i < bytes.length; i++) {
					byte b = bytes[i];
					memory.put(memPointer + (i+1)*8, (long) bytes[i]);
				}
				
				memory.put(memPointer, (long)bytes.length);
				//memory.put(memPointer+8, t);
				return memPointer+8;
			}
			else if (((NameIR) call.getChildren().get(0)).getLabel().contains("outOfBounds")){
				System.out.println("Array index out of bounds");
				throw new ArrayIndexOutOfBoundsException();
			}
			temps = new HashMap<String, Long>(Symtables.get(0));
			Symtables.add(0, temps);
			setUpParams(call);
			returnStack.push(pointer);
			String label = ((NameIR) call.getChildren().get(0)).getLabel();
			pointer = labels.get(label);
			boolean over = false;
			while (!over){
				if (root.getChildren().get(pointer) instanceof ReturnIR){
					over = true;
				}
				executeStatement();
			}
			//System.out.println(label+" returned "+finalReturn);
			return finalReturn;
		}else if(expr instanceof OpIR){
			OpIR op = (OpIR) expr;
			if(op.getChildren().size()==1){//unary operations
				if(op.returnsBool()){
					Long b = executeExpression((ExpressionIR) op.getChildren().get(0));
					if (b == 0) return 1l;
					else return 0l;
				}else if(op.returnsInt()){
					Long i = (Long) executeExpression((ExpressionIR) op.getChildren().get(0));
					 return -i;
				}
			}else if(op.getChildren().size()==2){
				Long op1 = executeExpression((ExpressionIR) op.getChildren().get(0)).longValue();
				Long op2 = executeExpression((ExpressionIR) op.getChildren().get(1)).longValue();
				switch(op.getOp()){
				case PLUS:		return (op1+op2);
				case MINUS:		return (op1-op2);
				case LSHIFT:	return (op1<<op2);
				case RSHIFT:	return (op1>>op2);
				case TIMES:		return (op1*op2);
				case DIVIDE:	return (op1/op2);
				case EQUAL:
								if (op1 == op2) return 1l;
								else return 0l;
				case NOT_EQUAL:
								if (op1 != op2) return 1l;
								else return 0l;
				case LT:
								if (op1 < op2) return 1l;
								else return 0l;
				case LEQ:
								if (op1 <= op2) return 1l;
								else return 0l;
				case GEQ:
								if (op1 >= op2) return 1l;
								else return 0l;
				case GT:
								if (op1 > op2) return 1l;
								else return 0l;
				case AND:
								if (op1 == 1 && op2 == 1) return 1l;
								else return 0l;
				case OR:
								if (op1 == 1 || op2 == 1) return 1l;
								else return 0l;
				case MODULO:
								return (op1 % op2);
				default:
								System.err.println("RANJAY DIDN'T IMPLEMENT");
				}
			}
		}else if(expr instanceof NameIR){
			throw new InterpreterException("NAME IR should not be called by the interpreter");			
		}else if(expr instanceof MemIR){
			Long loc = executeExpression((ExpressionIR) expr.getChildren().get(0));
			return memory.get(loc);
		}else if(expr instanceof EseqIR){
			throw new InterpreterException("ESEQ IR should not be called by the interpreter");
		}else{
			throw new InterpreterException("cant be an expression of this type");
		}
		return null;
	}

	/**
	 * sets up all the arguments of a function call
	 * @param call is the CallIR node who is being called
	 * @throws InterpreterException
	 * @throws EndOfProgramException
	 */
	private void setUpParams(CallIR call) throws InterpreterException, EndOfProgramException {
		temps = Symtables.get(0);
		for (int i = 1; i < call.getChildren().size(); i++){
			Long param = executeExpression((ExpressionIR) call.getChildren().get(i));
			temps.put("_args"+(i-1),param);
			//System.out.println(call+": "+param);
		}
	}

	/**
	 * collects all the labels in the assembly and saves them in a Hashmap.
	 */
	private void collectLabels() {
		int i = 0;
		for (VisualizableIRNode node: root.getChildren()){
			if (node instanceof LabelIR){
				String s = ((LabelIR) node).getName();
				if (s.contains("main")){
					pointer = i;
				}
				labels.put(s, i);
			}
			i++;
		}
	}
	
	
}
