package rak248.xi.ir;

import java.util.ArrayList;

import rak248.Driver;
import rak248.asm.AddressingMode;
import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.parser.FunctionCallNode;
import rak248.xi.parser.ProcedureCallNode;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;

public class CallIR extends ExpressionIR {
	
	private int retsize = -1;
	
	//constructor used for LENGTH call
	public CallIR(NameIR funcName, SyntaxIR arg) {
		retsize = lookupRetSize(funcName);
		setLabel("CALL");
		addChild(funcName);
		addChild(arg);
	}
	
	public CallIR() {
		setLabel("CALL");
	}
	
	
	public CallIR(NameIR funcName) {
		retsize = lookupRetSize(funcName);
		setLabel("CALL");
		addChild(funcName);
	}
	
	public CallIR(MemIR funcName, FunctionCallNode node) {
		retsize = lookupRetSize(node);
		funcName.setRetSize(retsize);
		setLabel("CALL");
		addChild(funcName);
	}
	
	public CallIR(MemIR funcName, ProcedureCallNode node) {
		retsize = lookupRetSize(node);
		funcName.setRetSize(retsize);
		setLabel("CALL");
		addChild(funcName);
	}

	
	private int lookupRetSize(FunctionCallNode node) {
		String cName = node.getMethodClass();
		String mName = node.getId();
		Type f = SymbolTable.lookUpMethod(cName, mName);
		return f.getReturnTypes().length;
	}
	
	private int lookupRetSize(ProcedureCallNode node) {
		String cName = node.getMethodClass();
		String mName = node.getId();
		Type f = SymbolTable.lookUpMethod(cName, mName);
		return f.getReturnTypes().length;
	}

	// SOMEWHAT DEPRECATED
	private int lookupRetSize(NameIR funcName) {
		return SymbolTable.getFunctionRetSize(funcName.getLabel());
	}

	public SyntaxIR foldESEQ() {
		if(getChildren().size() == 1) {
			return this;
		}
		EseqIR ret = new EseqIR();
		CallIR call = new CallIR();
		boolean okToReturnThis = true;
		int i = 0;
		SeqIR seq = new SeqIR();
		for (VisualizableIRNode node: getChildren()){
			SyntaxIR child = ((SyntaxIR) node).foldESEQ();
			if (child instanceof EseqIR){
				StatementIR stm = ((EseqIR) child).getS();
				ExpressionIR exp = ((EseqIR) child).getE();
				StatementIR lab = (((EseqIR) child).getL());
				seq.addChild(lab);
				TempIR tmp = new TempIR("_call"+getFreshLabel());
				//ret.addChild(new MoveIR(tmp, new ConstIR(0)));
				ret.addChild(stm);
				ret.addChild(new MoveIR(tmp, exp));
				call.addChild(tmp);
				i++;
				okToReturnThis = false;
			} else{
				call.addChild((SyntaxIR) child);
			}
		}
		if (okToReturnThis){
			return this;
		}
		else{
			ret.addChild(seq);
			//ret.addChild(new LabelIR("_block"+getFreshLabel()+"_End_"+i));
			ret.addChild(call);
			return ret;
		}
	}
	
	public SyntaxIR foldCALL(){
		EseqIR eseq = new EseqIR();
		CallIR call = new CallIR();
		call.setretsize(getretsize());
		call.setretsize(retsize);
		int i = 0;
		for (VisualizableIRNode node: getChildren()){
			if (node instanceof CallIR){
				TempIR temp = new TempIR("_call"+getFreshLabel());
				//eseq.addChild(new MoveIR(temp, new ConstIR(0)));
				SyntaxIR inside = ((CallIR) node).foldCALL();
				MoveIR move = new MoveIR(temp,inside);
				eseq.addChild(move);
				call.addChild(temp);
				i++;
			}
			else{
				call.addChild(((SyntaxIR) node).foldCALL());
			}	
		}
		//eseq.addChild(new LabelIR("_block"+SyntaxIR.getFreshLabel()+"End_"+i));
		eseq.addChild(call);
		return eseq;
	}
	
	public ArrayList<Tile> generateTile(TempTable tTable){
		ArrayList<Tile> ret = new ArrayList<Tile>();
		//Before we deal with the current arguments we need to save all registers
		//that we are supposed to according to the ABI
		//The Caller-saved registers and the order we will push them in is
		//rcx,rdx,rsp,rsi,rdi,r8,r9,r10,r11
		AddressingMode addrRAX = new AddressingMode("rax");
		//ret.add(new Tile(1, "movq", addrRAX, new AddressingMode(tTable.getCurrentFunction().getStackLocation().get("rax"),"rbp")));
		AddressingMode addrRCX = new AddressingMode("rcx");
		ret.add(new Tile(1, "movq", addrRCX, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rcx"),"rbp")));
		//System.out.println("rcx location:"+-1*tTable.getCurrentFunction().getStackLocation().get("rcx"));
		AddressingMode addrRDX = new AddressingMode("rdx");
		ret.add(new Tile(1, "movq", addrRDX, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rdx"),"rbp")));
		AddressingMode addrRSP = new AddressingMode("rsp");
		ret.add(new Tile(1, "movq", addrRSP, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rsp"),"rbp")));
		AddressingMode addrRSI = new AddressingMode("rsi");
		ret.add(new Tile(1, "movq", addrRSI, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rsi"),"rbp")));
		AddressingMode addrRDI = new AddressingMode("rdi");
		ret.add(new Tile(1, "movq", addrRDI, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rdi"),"rbp")));
		AddressingMode addrR8 = new AddressingMode("r8");
		ret.add(new Tile(1, "movq", addrR8, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r8"),"rbp")));
		AddressingMode addrR9 = new AddressingMode("r9");
		ret.add(new Tile(1, "movq", addrR9, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r9"),"rbp")));
		AddressingMode addrR10 = new AddressingMode("r10");
		ret.add(new Tile(1, "movq", addrR10, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r10"),"rbp")));
		AddressingMode addrR11 = new AddressingMode("r11");
		ret.add(new Tile(1, "movq", addrR11, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r11"),"rbp")));
		AddressingMode addrRBX = new AddressingMode("rbx");
		ret.add(new Tile(1, "pushq", addrRBX, null));
		AddressingMode addrRBP = new AddressingMode("rbp");
		ret.add(new Tile(1, "pushq", addrRBP, null));
		
		//Now that we have saved the caller-saved registers we get the tilings
		//for the arguments given to the function
		ArrayList<ArrayList<Tile>> argumentTileLists = new ArrayList<ArrayList<Tile>>();
		for(int i = 1; i < getChildren().size(); i++){
			argumentTileLists.add(((SyntaxIR)getChildren().get(i)).generateTile(tTable));
			
		}
		//System.out.println("argtilelists"+argumentTileLists);
		//Now that we have the arguments we must push them on the stack and or registers
		AddressingMode addr12 = new AddressingMode("r12");
		AddressingMode addrR13 = new AddressingMode("r13");
		if(retsize > 1) {
			AddressingMode rsp = new AddressingMode("rsp");
			AddressingMode r12 = new AddressingMode("r12");
			//ret.add(new Tile(1, "movq", new AddressingMode(retsize+1), addrRDI));
			CallIR call = new CallIR(new NameIR("_I_alloc_i"), new ConstIR(8*(retsize+1)));
			call.retsize = 1;
			ret.addAll(call.generateTile(tTable));
			ret.add(new Tile(1, "movq", addr12, addrRDI));
			ret.add(new Tile(1, "movq", new AddressingMode(retsize), new AddressingMode(0,"rdi")));
			ret.add(new Tile(1, "add", new AddressingMode(8), addrRDI));
		}
		if((argumentTileLists.size() <= 6 && retsize <=1) || (argumentTileLists.size() <=5 && retsize > 1)) {
			for(int i = 0; i < argumentTileLists.size(); i ++) {
				ret.addAll(argumentTileLists.get(i));
				ret.add(new Tile(1, "pushq", new AddressingMode("r12"), null));
			}
		} else {
			if(retsize > 1) {
				for(int i = argumentTileLists.size() -1; i >=5; i --) {
					ret.addAll(argumentTileLists.get(i));
					ret.add(new Tile(1, "pushq", new AddressingMode("r12"), null));
				}
				for(int i = 0; i < 5; i ++) {
					ret.addAll(argumentTileLists.get(i));
					ret.add(new Tile(1, "pushq", new AddressingMode("r12"), null));
				}
			} else {
				for(int i = argumentTileLists.size() -1; i >=6; i --) {
					ret.addAll(argumentTileLists.get(i));
					ret.add(new Tile(1, "pushq", new AddressingMode("r12"), null));
				}
				for(int i = 0; i < 6; i ++) {
					ret.addAll(argumentTileLists.get(i));
					ret.add(new Tile(1, "pushq", new AddressingMode("r12"), null));
				}
			}
		}
		switch(argumentTileLists.size()){
		case 6:
			if(retsize <= 1) {
				//push to r8
				//ret.addAll(argumentTileLists.get(5));
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to r8
				ret.add(new Tile(1,"movq",addrR13,addrR9));
			}
		case 5:
			//push to r8
			//ret.addAll(argumentTileLists.get(4));
			//the argument tile will push the value on the stack so we pop into r13
			ret.add(new Tile(1,"popq",addrR13,null));
			//then we can move it to r8
			if(retsize > 1) {
				ret.add(new Tile(1,"movq",addrR13,addrR9));
			} else {
				ret.add(new Tile(1,"movq",addrR13,addrR8));
			}
		case 4:
			//push to rcx
			//ret.addAll(argumentTileLists.get(3));
			//the argument tile will push the value on the stack so we pop into r13
			ret.add(new Tile(1,"popq",addrR13,null));
			//then we can move it to rcx
			if(retsize > 1) {
				ret.add(new Tile(1,"movq",addrR13,addrR8));
			} else {
				ret.add(new Tile(1,"movq",addrR13,addrRCX));
			}
		case 3:
			//push to rdx
			//ret.addAll(argumentTileLists.get(2));
			//the argument tile will push the value on the stack so we pop into r13
			ret.add(new Tile(1,"popq",addrR13,null));
			//then we can move it to rdx
			if(retsize > 1) {
				ret.add(new Tile(1,"movq",addrR13,addrRCX));
			} else {
				ret.add(new Tile(1,"movq",addrR13,addrRDX));
			}
		case 2:
			//push to rsi
			//ret.addAll(argumentTileLists.get(1));
			//the argument tile will push the value on the stack so we pop into r13
			ret.add(new Tile(1,"popq",addrR13,null));
			//then we can move it to rsi
			if(retsize > 1) {
				ret.add(new Tile(1,"movq",addrR13,addrRDX));
			} else {
				ret.add(new Tile(1,"movq",addrR13,addrRSI));
			}
		case 1:
			//push to rdi
			//ret.addAll(argumentTileLists.get(0));
			//the argument tile will push the value on the stack so we pop into r13
			ret.add(new Tile(1,"popq",addrR13,null));
			//then we can move it to rdi
			if(retsize > 1) {
				ret.add(new Tile(1,"movq",addrR13,addrRSI));
			} else {
				ret.add(new Tile(1,"movq",addrR13,addrRDI));
			}
		case 0:
			//Do nothing
			break;
		default://we have more than five arguments
			//push the first 6 arguments to registers
			//and we're not using RDI (not returning a tuple)
			if(retsize <= 1) {
				//push to r9
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to r8
				ret.add(new Tile(1,"movq",addrR13,addrR9));
				//push to r8
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to r8
				ret.add(new Tile(1,"movq",addrR13,addrR8));
				//push to rcx
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rcx
				ret.add(new Tile(1,"movq",addrR13,addrRCX));
				//push to rdx
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rdx
				ret.add(new Tile(1,"movq",addrR13,addrRDX));
				//push to rsi
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rsi
				ret.add(new Tile(1,"movq",addrR13,addrRSI));
				//push to rdi
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rdi
				ret.add(new Tile(1,"movq",addrR13,addrRDI));
			}
			//we're returning a tuple
			else {
				//push to r9
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to r8
				ret.add(new Tile(1,"movq",addrR13,addrR9));
				//push to r8
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rcx
				ret.add(new Tile(1,"movq",addrR13,addrR8));
				//push to rcx
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rdx
				ret.add(new Tile(1,"movq",addrR13,addrRCX));
				//push to rdx
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rsi
				ret.add(new Tile(1,"movq",addrR13,addrRDX));
				//push to rsi
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rdi
				ret.add(new Tile(1,"movq",addrR13,addrRSI));
			}
		}
		//Now we can finally make the call
		//if its a mem, different addressing mode
		AddressingMode func;
		/*if(getChildren().get(0) instanceof MemIR) {
			ret.add(new Tile(0, "movq", new AddressingMode("r13")))
			func = new AddressingMode(getChildren().get(0), true);
		}
		else*/
			func = new AddressingMode(((NameIR)getChildren().get(0)).getLabel(), true);
		//System.out.println("stack size: " + tTable.getStackSize());
		//if the stack isn't 16 bit aligned, we need to do this
		AddressingMode rbp = new AddressingMode("rbp");
		AddressingMode rsp = new AddressingMode("rsp");
		AddressingMode r12 = new AddressingMode("r12");
		AddressingMode r13 = new AddressingMode("r13");
		//Check if the 3rd bit is 1 or zero and set the carry bit accordingly
		ret.add(new Tile(0, "movq", new AddressingMode(0), new AddressingMode("rbx")));
		ret.add(new Tile(1, "bt", new AddressingMode(3), rsp));
		Integer callInt = tTable.getNewJumpLabel();
		//jump to rspJmp if stack pointer is even
		ret.add(new Tile(1, "jnc", new AddressingMode("rspJmp" + callInt, true), null));
		//stack pointer was odd
		ret.add(new Tile(1, "movq", new AddressingMode(1), r12));
		ret.add(new Tile(1, "jmp", new AddressingMode("rspend" + callInt, true), null));
		ret.add(new Tile(1, "rspJmp" + callInt + ": ", null, null));
		//save that stack pointer was even in r12
		ret.add(new Tile(1, "movq", new AddressingMode(0), r12));
		ret.add(new Tile(1, "rspend" + callInt + ": ", null, null));
		//check rbp for being divisible by 8(odd) or 16(even)
		ret.add(new Tile(1, "bt", new AddressingMode(3), rbp));
		ret.add(new Tile(1, "jnc", new AddressingMode("rbpJmp" + callInt, true), null));
		//didn't jump since base pointer is odd
		ret.add(new Tile(1, "movq", new AddressingMode(1), r13));
		ret.add(new Tile(1, "jmp", new AddressingMode("rbpend" + callInt, true), null));
		ret.add(new Tile(1, "rbpJmp" + callInt + ": ", null, null));
		// base pointer is even
		ret.add(new Tile(1, "movq", new AddressingMode(0), r13));
		ret.add(new Tile(1, "rbpend" + callInt + ": ", null, null));
		//Now we just need to logically xnor r12 and r13, i.e. if they are both zero or both one
		//Test r1,r2 sets the parity flag to the xnor so we will use that
		ret.add(new Tile(1, "test", r12, r13));
		//jump to extraPush(which doesn't make an extra Push) if they were equal
		ret.add(new Tile(1, "jnp", new AddressingMode("extraPush"+callInt,true),null));
		//ret.add(new Tile(1, "sub", r12, r13));
		//ret.add(new Tile(1, "jne", new AddressingMode("extraPush" + callInt, true), null));
		ret.add(new Tile(1, "pushq", new AddressingMode(0), null));
		//also store value in rbx(a callee saved register) so we can know we were odd after return
		ret.add(new Tile(1, "movq", new AddressingMode(1),new AddressingMode("rbx")));
		ret.add(new Tile(1, "extraPush" + callInt + ": ", null, null));
		//otherwise store 0 in rbx
		ret.add(new Tile(1,"call",func,null));
		ret.add(new Tile(1, "cmp", new AddressingMode(1), new AddressingMode("rbx")));
		//if rbx is 1 then we had to push an extra frame before so pop it off if rbx = 1
		ret.add(new Tile(1, "jne", new AddressingMode("extraPop" + callInt, true), null));
		ret.add(new Tile(1, "popq", r13, null));
		ret.add(new Tile(1, "extraPop" + callInt + ": ", null, null));
		ret.add(new Tile(1, "movq", new AddressingMode(0),new AddressingMode("rbx")));
		//after the function returns we need to restore the caller-saved registers
		//assuming the ret call moves the stack pointer back to before the arguments we should 
		if(retsize > 1) {
			ret.add(new Tile(1, "movq", addrRDI, addrRAX));
		}
		// now we need to pop off extra arguments that we moved onto the stack
		if(retsize > 1 && argumentTileLists.size() > 5) {
			for(int i = argumentTileLists.size(); i > 5; i --) {
				ret.add(new Tile(1,"popq",new AddressingMode("r12"),null));
				//System.out.println("extra pop");
			}
		} else if (argumentTileLists.size() > 6) {
			for(int i = argumentTileLists.size(); i > 6; i --) {
				ret.add(new Tile(1,"popq",new AddressingMode("r12"),null));
				//System.out.println("extra pop");
			}
		}
		ret.add(new Tile(1, "popq", addrRBP, null));
		ret.add(new Tile(1, "popq", addrRBX, null));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r11"),"rbp"),addrR11));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r10"),"rbp"),addrR10));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r9"),"rbp"),addrR9));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r8"),"rbp"),addrR8));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rdi"),"rbp"),addrRDI));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rsi"),"rbp"),addrRSI));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rsp"),"rbp"),addrRSP));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rdx"),"rbp"),addrRDX));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rcx"),"rbp"),addrRCX));
		//After the function returns we should push the return value in case we are called as an expression
		ret.add(new Tile(1,"movq",addrRAX,new AddressingMode("r12")));
		return ret;
	}
	
	public ArrayList<Tile> generateTileWindows(TempTable tTable){
		ArrayList<Tile> ret = new ArrayList<Tile>();
		//Before we deal with the current arguments we need to save all registers
		//that we are supposed to according to the ABI
		//The Caller-saved registers and the order we will push them in is
		//rcx,rdx,rsp,rsi,rdi,r8,r9,r10,r11
		AddressingMode addrRAX = new AddressingMode("rax");
		//ret.add(new Tile(1, "movq", addrRAX, new AddressingMode(tTable.getCurrentFunction().getStackLocation().get("rax"),"rbp")));
		AddressingMode addrRCX = new AddressingMode("rcx");
		ret.add(new Tile(1, "movq", addrRCX, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rcx"),"rbp")));
		//System.out.println("rcx location:"+-1*tTable.getCurrentFunction().getStackLocation().get("rcx"));
		AddressingMode addrRDX = new AddressingMode("rdx");
		ret.add(new Tile(1, "movq", addrRDX, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rdx"),"rbp")));
		AddressingMode addrRSP = new AddressingMode("rsp");
		ret.add(new Tile(1, "movq", addrRSP, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rsp"),"rbp")));
		AddressingMode addrRSI = new AddressingMode("rsi");
		ret.add(new Tile(1, "movq", addrRSI, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rsi"),"rbp")));
		AddressingMode addrRDI = new AddressingMode("rdi");
		ret.add(new Tile(1, "movq", addrRDI, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rdi"),"rbp")));
		AddressingMode addrR8 = new AddressingMode("r8");
		ret.add(new Tile(1, "movq", addrR8, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r8"),"rbp")));
		AddressingMode addrR9 = new AddressingMode("r9");
		ret.add(new Tile(1, "movq", addrR9, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r9"),"rbp")));
		AddressingMode addrR10 = new AddressingMode("r10");
		ret.add(new Tile(1, "movq", addrR10, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r10"),"rbp")));
		AddressingMode addrR11 = new AddressingMode("r11");
		ret.add(new Tile(1, "movq", addrR11, new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r11"),"rbp")));
		AddressingMode addrRBX = new AddressingMode("rbx");
		ret.add(new Tile(1, "pushq", addrRBX, null));
		AddressingMode addrRBP = new AddressingMode("rbp");
		ret.add(new Tile(1, "pushq", addrRBP, null));
		
		//Now that we have saved the caller-saved registers we get the tilings
		//for the arguments given to the function
		ArrayList<ArrayList<Tile>> argumentTileLists = new ArrayList<ArrayList<Tile>>();
		for(int i = 1; i < getChildren().size(); i++){
			argumentTileLists.add(((SyntaxIR)getChildren().get(i)).generateTileWindows(tTable));
			
		}
		//System.out.println("argtilelists"+argumentTileLists);
		//Now that we have the arguments we must push them on the stack and or registers
		AddressingMode addr12 = new AddressingMode("r12");
		AddressingMode addrR13 = new AddressingMode("r13");
		if(retsize > 1) {
			AddressingMode rsp = new AddressingMode("rsp");
			AddressingMode r12 = new AddressingMode("r12");
			//ret.add(new Tile(1, "movq", new AddressingMode(retsize+1), addrRDI));
			CallIR call = new CallIR(new NameIR("_I_alloc_i"), new ConstIR(8*(retsize+1)));
			call.retsize = 1;
			ret.addAll(call.generateTileWindows(tTable));
			ret.add(new Tile(1, "movq", addr12, addrRDI));
			ret.add(new Tile(1, "movq", new AddressingMode(retsize), new AddressingMode(0,"rcx")));
			ret.add(new Tile(1, "add", new AddressingMode(8), addrRDI));
		}
		if((argumentTileLists.size() <= 4 && retsize <=1) || (argumentTileLists.size() <=3 && retsize > 1)) {
			for(int i = 0; i < argumentTileLists.size(); i ++) {
				ret.addAll(argumentTileLists.get(i));
				ret.add(new Tile(1, "pushq", new AddressingMode("r12"), null));
			}
		} else {
			if(retsize > 1) {
				for(int i = argumentTileLists.size() -1; i >=3; i --) {
					ret.addAll(argumentTileLists.get(i));
					ret.add(new Tile(1, "pushq", new AddressingMode("r12"), null));
				}
				for(int i = 0; i < 3; i ++) {
					ret.addAll(argumentTileLists.get(i));
					ret.add(new Tile(1, "pushq", new AddressingMode("r12"), null));
				}
			} else {
				for(int i = argumentTileLists.size() -1; i >=4; i --) {
					ret.addAll(argumentTileLists.get(i));
					ret.add(new Tile(1, "pushq", new AddressingMode("r12"), null));
				}
				for(int i = 0; i < 4; i ++) {
					ret.addAll(argumentTileLists.get(i));
					ret.add(new Tile(1, "pushq", new AddressingMode("r12"), null));
				}
			}
		}
		switch(argumentTileLists.size()){
		case 4:
			//push to rcx
			//ret.addAll(argumentTileLists.get(3));
			//the argument tile will push the value on the stack so we pop into r13
			//then we can move it to rcx
			if(retsize <= 1) {
				ret.add(new Tile(1,"movq",addrR13,addrR9));
			}
		case 3:
			//push to rdx
			//ret.addAll(argumentTileLists.get(2));
			//the argument tile will push the value on the stack so we pop into r13
			ret.add(new Tile(1,"popq",addrR13,null));
			//then we can move it to rdx
			if(retsize > 1) {
				ret.add(new Tile(1,"movq",addrR13,addrR9));
			} else {
				ret.add(new Tile(1,"movq",addrR13,addrR8));
			}
		case 2:
			//push to rsi
			//ret.addAll(argumentTileLists.get(1));
			//the argument tile will push the value on the stack so we pop into r13
			ret.add(new Tile(1,"popq",addrR13,null));
			//then we can move it to rsi
			if(retsize > 1) {
				ret.add(new Tile(1,"movq",addrR13,addrR8));
			} else {
				ret.add(new Tile(1,"movq",addrR13,addrRDX));
			}
		case 1:
			//push to rdi
			//ret.addAll(argumentTileLists.get(0));
			//the argument tile will push the value on the stack so we pop into r13
			ret.add(new Tile(1,"popq",addrR13,null));
			//then we can move it to rdi
			if(retsize > 1) {
				ret.add(new Tile(1,"movq",addrR13,addrRDX));
			} else {
				ret.add(new Tile(1,"movq",addrR13,addrRCX));
			}
		case 0:
			//Do nothing
			break;
		default://we have more than five arguments
			//push the first 6 arguments to registers
			//and we're not using RDI (not returning a tuple)
			if(retsize <= 1) {
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rcx
				ret.add(new Tile(1,"movq",addrR13,addrR9));
				//push to rdx
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rdx
				ret.add(new Tile(1,"movq",addrR13,addrR8));
				//push to rsi
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rsi
				ret.add(new Tile(1,"movq",addrR13,addrRDX));
				//push to rdi
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rdi
				ret.add(new Tile(1,"movq",addrR13,addrRCX));
			}
			//we're returning a tuple
			else {
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rdx
				ret.add(new Tile(1,"movq",addrR13,addrR9));
				//push to rdx
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rsi
				ret.add(new Tile(1,"movq",addrR13,addrR8));
				//push to rsi
				//the argument tile will push the value on the stack so we pop into r13
				ret.add(new Tile(1,"popq",addrR13,null));
				//then we can move it to rdi
				ret.add(new Tile(1,"movq",addrR13,addrRDX));
			}
		}
		//Now we can finally make the call
		AddressingMode func = new AddressingMode(((NameIR)getChildren().get(0)).getLabel(), true);
		//System.out.println("stack size: " + tTable.getStackSize());
		//if the stack isn't 16 bit aligned, we need to do this
		AddressingMode rbp = new AddressingMode("rbp");
		AddressingMode rsp = new AddressingMode("rsp");
		AddressingMode r12 = new AddressingMode("r12");
		AddressingMode r13 = new AddressingMode("r13");
		//Check if the 3rd bit is 1 or zero and set the carry bit accordingly
		ret.add(new Tile(0, "movq", new AddressingMode(0), new AddressingMode("rbx")));
		ret.add(new Tile(1, "bt", new AddressingMode(3), rsp));
		Integer callInt = tTable.getNewJumpLabel();
		//jump to rspJmp if stack pointer is even
		ret.add(new Tile(1, "jnc", new AddressingMode("rspJmp" + callInt, true), null));
		//stack pointer was odd
		ret.add(new Tile(1, "movq", new AddressingMode(1), r12));
		ret.add(new Tile(1, "jmp", new AddressingMode("rspend" + callInt, true), null));
		ret.add(new Tile(1, "rspJmp" + callInt + ": ", null, null));
		//save that stack pointer was even in r12
		ret.add(new Tile(1, "movq", new AddressingMode(0), r12));
		ret.add(new Tile(1, "rspend" + callInt + ": ", null, null));
		//check rbp for being divisible by 8(odd) or 16(even)
		ret.add(new Tile(1, "bt", new AddressingMode(3), rbp));
		ret.add(new Tile(1, "jnc", new AddressingMode("rbpJmp" + callInt, true), null));
		//didn't jump since base pointer is odd
		ret.add(new Tile(1, "movq", new AddressingMode(1), r13));
		ret.add(new Tile(1, "jmp", new AddressingMode("rbpend" + callInt, true), null));
		ret.add(new Tile(1, "rbpJmp" + callInt + ": ", null, null));
		// base pointer is even
		ret.add(new Tile(1, "movq", new AddressingMode(0), r13));
		ret.add(new Tile(1, "rbpend" + callInt + ": ", null, null));
		//Now we just need to logically xnor r12 and r13, i.e. if they are both zero or both one
		//Test r1,r2 sets the parity flag to the xnor so we will use that
		ret.add(new Tile(1, "test", r12, r13));
		//jump to extraPush(which doesn't make an extra Push) if they were equal
		ret.add(new Tile(1, "jnp", new AddressingMode("extraPush"+callInt,true),null));
		//ret.add(new Tile(1, "sub", r12, r13));
		//ret.add(new Tile(1, "jne", new AddressingMode("extraPush" + callInt, true), null));
		ret.add(new Tile(1, "pushq", new AddressingMode(0), null));
		//also store value in rbx(a callee saved register) so we can know we were odd after return
		ret.add(new Tile(1, "movq", new AddressingMode(1),new AddressingMode("rbx")));
		ret.add(new Tile(1, "extraPush" + callInt + ": ", null, null));
		//otherwise store 0 in rbx
		//in windows, you need 32 bytes of shadow space
		ret.add(new Tile(1,"pushq",new AddressingMode(0), null));
		ret.add(new Tile(1,"pushq",new AddressingMode(0), null));
		ret.add(new Tile(1,"pushq",new AddressingMode(0), null));
		ret.add(new Tile(1,"pushq",new AddressingMode(0), null));
		ret.add(new Tile(1,"call",func,null));
		ret.add(new Tile(1,"popq",new AddressingMode(0), null));
		ret.add(new Tile(1,"popq",new AddressingMode(0), null));
		ret.add(new Tile(1,"popq",new AddressingMode(0), null));
		ret.add(new Tile(1,"popq",new AddressingMode(0), null));
		ret.add(new Tile(1, "cmp", new AddressingMode(1), new AddressingMode("rbx")));
		//if rbx is 1 then we had to push an extra frame before so pop it off if rbx = 1
		ret.add(new Tile(1, "jne", new AddressingMode("extraPop" + callInt, true), null));
		ret.add(new Tile(1, "popq", r13, null));
		ret.add(new Tile(1, "extraPop" + callInt + ": ", null, null));
		ret.add(new Tile(1, "movq", new AddressingMode(0),new AddressingMode("rbx")));
		//after the function returns we need to restore the caller-saved registers
		//assuming the ret call moves the stack pointer back to before the arguments we should 
		if(retsize > 1) {
			ret.add(new Tile(1, "movq", addrRCX, addrRAX));
		}
		// now we need to pop off extra arguments that we moved onto the stack
		if(retsize > 1 && argumentTileLists.size() > 3) {
			for(int i = argumentTileLists.size(); i > 3; i --) {
				ret.add(new Tile(1,"popq",new AddressingMode("r12"),null));
				//System.out.println("extra pop");
			}
		} else if (argumentTileLists.size() > 4) {
			for(int i = argumentTileLists.size(); i > 4; i --) {
				ret.add(new Tile(1,"popq",new AddressingMode("r12"),null));
				//System.out.println("extra pop");
			}
		}
		ret.add(new Tile(1, "popq", addrRBP, null));
		ret.add(new Tile(1, "popq", addrRBX, null));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r11"),"rbp"),addrR11));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r10"),"rbp"),addrR10));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r9"),"rbp"),addrR9));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r8"),"rbp"),addrR8));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rdi"),"rbp"),addrRDI));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rsi"),"rbp"),addrRSI));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rsp"),"rbp"),addrRSP));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rdx"),"rbp"),addrRDX));
		ret.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rcx"),"rbp"),addrRCX));
		//After the function returns we should push the return value in case we are called as an expression
		ret.add(new Tile(1,"movq",addrRAX,new AddressingMode("r12")));
		return ret;
	}
	
	public int getretsize() {
		/* THIS IS A WORK IN PROGRESS */
		try {
			if(((ArrayList<VisualizableIRNode>) this.children()).get(0) instanceof MemIR) {
				MemIR m = (MemIR) ((ArrayList<VisualizableIRNode>) this.children()).get(0);
				if(m.getRetSize() != 999) {
					return m.getRetSize();
				}
			}
			
			NameIR name = (NameIR) ((ArrayList<VisualizableIRNode>) this.children()).get(0);
			
			int i = SymbolTable.getFunctionRetSize(name.getLabel());
			if(i == -1) {
				System.err.println("Problem getting retSize in CallIR");
				Driver.printTreeToStdOut(this);
			}
			
			return i;
		} catch (Exception e) {
			System.err.println("Shouldn't happen anymore");
			e.printStackTrace();
			return retsize;
		}
	}
	
	public void setretsize(int in) {

		if(retsize != -1 && retsize != in) {
			//System.out.println("These prints are in CallIR setretsize().  I'm leaving them because I'm pretty sure the calls resulting in this are wrong but the function is also deprecated");
			//Driver.printTreeToStdOut(this);
			//System.out.println("WAS: " + retsize + " SETTING TO: " +in);
			//System.err.println("Setting ret size to a different value than it was!  See above prints.  Shouldn't happen but also shouldn't matter after latest hack");
		}
		retsize = in;
	}
	
	public String prettyPrint() {
		String ret = ((SyntaxIR) getChildren().get(0)).prettyPrint();
		ret += "(";
		for (int i = 1; i < getChildren().size(); i++){
			ret += ((SyntaxIR) getChildren().get(i)).prettyPrint() + ", ";
		}
		if (getChildren().size() != 1){
			ret = ret.substring(0,ret.length()-2);
		}
		ret += ")";
		return ret;
	}
	
}
