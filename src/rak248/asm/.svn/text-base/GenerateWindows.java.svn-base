package rak248.asm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.CJumpIR;
import rak248.xi.ir.CallIR;
import rak248.xi.ir.CompUnitIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.ExpIR;
import rak248.xi.ir.JumpIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.ir.ReturnIR;
import rak248.xi.ir.TempIR;

public class GenerateWindows {
	
	public static Instruction generateTile(SyntaxIR ir,TempTable tTable){
		if (ir instanceof OpIR)       return generateTile((OpIR)       ir,tTable);
		if (ir instanceof ExpIR)      return generateTile((ExpIR)      ir,tTable);
		if (ir instanceof MemIR)      return generateTile((MemIR)      ir,tTable);
		if (ir instanceof TempIR)     return generateTile((TempIR)     ir,tTable);
		if (ir instanceof NameIR)     return generateTile((NameIR)     ir,tTable);
		if (ir instanceof CallIR)     return generateTile((CallIR)     ir,tTable);
		if (ir instanceof JumpIR)     return generateTile((JumpIR)     ir,tTable);
		if (ir instanceof MoveIR)     return generateTile((MoveIR)     ir,tTable);
		if (ir instanceof ConstIR)    return generateTile((ConstIR)    ir,tTable);
		if (ir instanceof CJumpIR)    return generateTile((CJumpIR)    ir,tTable);
		if (ir instanceof LabelIR)    return generateTile((LabelIR)    ir,tTable);
		if (ir instanceof ReturnIR)   return generateTile((ReturnIR)   ir,tTable);
		if (ir instanceof CompUnitIR) return generateTile((CompUnitIR) ir,tTable);
		else return null;
	}
	
	//Ranjay,
	public static Instruction generateTile(NameIR name, TempTable tTable){
		Tile t = new Tile(0, name.getLabel() + ": ", null, null);
		ArrayList<Tile> result = new ArrayList<Tile>();
		result.add(t);
		Instruction inst = new Instruction();
		inst.setTiles(result);
		return inst;
	}
	
	//Ranjay,
	public static ExpressionInstruction generateTile(ConstIR cons, TempTable tTable){
		ExpressionInstruction inst = new ExpressionInstruction();
		AddressingMode addr = new AddressingMode(cons.getValue());
		inst.setAddr(addr);
		return inst;
	}
	
	//Ranjay,
	public static Instruction generateTile(CJumpIR jump, TempTable tTable){
		SyntaxIR child = (SyntaxIR) jump.getChildren().get(0);
		Instruction childInst = generateTile(child,tTable);
		String lt = jump.getlt();
		if(lt.startsWith("LABEL")){
			lt = lt.substring(7, lt.length());
		}
		Tile t2 = new Tile(1, "cmp", new AddressingMode(1), ((ExpressionInstruction) childInst).getAddr());
		Tile t3 = new Tile(1, "je", new AddressingMode(lt, true), null);
		Instruction inst = new Instruction();
		inst.addAll(childInst.getTiles());
		inst.add(t2);
		inst.add(t3);
		return inst;
	}
	
	//Ranjay,
	public static Instruction generateTile(CompUnitIR comp, TempTable tTable){
		Instruction result = new Instruction();
		result.add(new Tile(0,".text",null,null));
		for(VisualizableIRNode childs : comp.getChildren()) {
			SyntaxIR child = (SyntaxIR) childs;
			Instruction newT = generateTile(child,tTable);
			result.addAll(newT.getTiles());
		}
		result.add(new Tile(0,".text",null,null));
		return result;
	}
	
	//Ranjay,
	public static Instruction generateTile(ExpIR exp, TempTable tTable){
		Instruction inst = generateTile((SyntaxIR) exp.getChildren().get(0),tTable);
		return inst;
	}
	
	//Ranjay,
	public static Instruction generateTile(JumpIR jump, TempTable tTable){
		String label = ((NameIR) jump.getChildren().get(0)).getLabel();
		if (label.startsWith("LABEL")){
			label = label.substring(7,label.length());
		}
		Tile t = new Tile(1, "jmp", new AddressingMode(label, true), null);
		Instruction inst = new Instruction();
		inst.add(t);
		return inst;
	}
	
	//Ranjay,
	public static Instruction generateTile(LabelIR label, TempTable tTable){
		ArrayList<Tile> result = new ArrayList<Tile>();
		if(label.getName().startsWith("_I")){
			Tile globl = new Tile(0, ".globl "+label.getName(),null,null);
			result.add(globl);
			Tile t = new Tile(0, label.getName() + ": ", null, null);
			result.add(t);
			tTable.setCurrentFunction(label);
			result.add(new Tile(1, "movq", new AddressingMode("rsp"), new AddressingMode("rbp")));
			result.add(new Tile(1, "sub", new AddressingMode(label.getStackLocation().size()*8),new AddressingMode("rsp")));
		}
		else{
			result.add(new Tile(0, label.getName() + ": ", null, null));
		}
		Instruction inst = new Instruction();
		inst.setTiles(result);
		return inst;
	}
	
	//Ranjay
	public static Instruction generateTile(MoveIR move, TempTable tTable){
		Instruction inst = new Instruction();
		Instruction childDest = generateTile((SyntaxIR) move.getChildren().get(0),tTable);
		Instruction childSrc = generateTile((SyntaxIR) move.getChildren().get(1),tTable);
		inst.addAll(childSrc.getTiles());
		inst.addAll(childDest.getTiles());
		AddressingMode addr1 = ((ExpressionInstruction) childSrc).getAddr();
		AddressingMode addr2 = ((ExpressionInstruction) childDest).getAddr();
		if (addr1.getMemAccess() && addr2.getMemAccess()){
			inst.add(new Tile(1,"movq",addr1,new AddressingMode("r12")));
			inst.add(new Tile(1,"movq",new AddressingMode("r12"),addr2));
			return inst;
		}
		else if (addr2.getMemAccess()){
			Instruction iii = new Instruction();
			iii.addAll(childSrc.getTiles());
			iii.add(new Tile(1,"pushq",addr1,null));
			iii.addAll(childDest.getTiles());
			iii.add(new Tile(1,"popq",new AddressingMode("r12"),null));
			iii.add(new Tile(1,"movq",new AddressingMode("r12"),addr2));
			return iii;
		}
		inst.add(new Tile(1,"movq",addr1,addr2));
		return inst;
	}
	
	//Ranjay
	public static Instruction generateTile(ReturnIR ret, TempTable tTable){
		Instruction inst = new Instruction();
		if(ret.getChildren().size() > 1) {
			inst.add(new Tile(1,"movq",new AddressingMode("rdi"),new AddressingMode("rax")));
			/*int i = 0;
			for(VisualizableIRNode child: ret.getChildren()){
				ExpressionInstruction childInst = (ExpressionInstruction) generateTile((SyntaxIR) child,tTable);
				inst.addAll(childInst.getTiles());
				Tile t = new Tile(1, "movq", childInst.getAddr(), new AddressingMode(i*8, "rdi"));
				inst.add(t);
				i++;
			}*/
		}
		if(ret.getChildren().size() == 1){
			Instruction retVal = generateTile((SyntaxIR) ret.getChildren().get(0),tTable);
			inst.addAll(retVal.getTiles());
			Tile t = new Tile(1, "movq", ((ExpressionInstruction)retVal).getAddr(), new AddressingMode("rax"));
			inst.add(t);
		}
		
		//Before we return we need to pop all the local variables and throw them away
		AddressingMode rbp = new AddressingMode("rbp");
		AddressingMode rsp = new AddressingMode("rsp");
		Integer newLabel = tTable.getNewJumpLabel();
		inst.add(new Tile(1, "ret" + newLabel + ": ", null, null));
		inst.add(new Tile(1, "cmp", rbp, rsp));
		inst.add(new Tile(1, "je", new AddressingMode("retend" + newLabel, true), null));
		inst.add(new Tile(1,"popq",new AddressingMode("r12"),null));
		inst.add(new Tile(1, "jmp", new AddressingMode("ret" + newLabel, true), null));
		inst.add(new Tile(1, "retend" + newLabel + ": ", null, null));
		inst.add(new Tile(1, "ret", null, null));
		return inst;
	}
	
	//Ranjay
	public static ExpressionInstruction generateTile(TempIR temp, TempTable tTable){
		ExpressionInstruction inst = new ExpressionInstruction();
		String funcname = tTable.getCurrentFunction().label();
		int lastindex = funcname.lastIndexOf("_");
		String letter = funcname.substring(lastindex+1, lastindex+2);
		String id = temp.getId();
		if(id.startsWith("_ret")) {
			Integer offset = Integer.parseInt((id.substring(4,id.length())));
			offset = 1*(offset)*8;
			inst.setAddr(new AddressingMode(offset, "rax"));                                             		
		}
		else if(id.startsWith("_rret")) {
			Integer offset = Integer.parseInt((id.substring(5,id.length())));
			offset = 1*(offset)*8;
			inst.setAddr(new AddressingMode(offset, "rcx"));                                             		
		} 
		else if(!(letter.equals("t"))) {
			if(id.startsWith("_args0"))
				inst.setAddr(new AddressingMode("rcx"));
			else if(id.startsWith("_args1"))
				inst.setAddr(new AddressingMode("rdx"));
			else if(id.startsWith("_args2"))
				inst.setAddr(new AddressingMode("r8"));
			else if(id.startsWith("_args3"))
				inst.setAddr(new AddressingMode("r9"));
			else if(id.startsWith("_args"))
				inst.setAddr(new AddressingMode((Integer.parseInt(id.substring(5,id.length())) - 5) * 8, "rbp"));          
			else {																								 //DANIEL!!, FIX line 203 & 226 so that
				HashMap<String, String> regs = tTable.getCurrentFunction().getRegAlloc();						//we can have more than 10 arguments					
				HashMap<String, Integer> stack = tTable.getCurrentFunction().getStackLocation();
				if(regs.containsKey(id)) {
					inst.setAddr(new AddressingMode(regs.get(id)));
				} 
				else {
					//System.out.println(id);
					int offset = stack.get(id);
					inst.setAddr(new AddressingMode(-offset, "rbp"));
				}
			}
		} else {
			if(id.startsWith("_args0")) {
				inst.setAddr(new AddressingMode("rdx")); }
			else if(id.startsWith("_args1")) {
				inst.setAddr(new AddressingMode("r8")); }
			else if(id.startsWith("_args2")) {
				inst.setAddr(new AddressingMode("r9")); }
			else if(id.startsWith("_args")) {
				inst.setAddr(new AddressingMode((Integer.parseInt(id.substring(5,id.length())) - 3) * 8, "rbp"));			
			} 
			else {
				HashMap<String, String> regs = tTable.getCurrentFunction().getRegAlloc();
				if(regs.containsKey(id)) {
					inst.setAddr(new AddressingMode(regs.get(id)));
				} 
				else {
					HashMap<String, Integer> stack = tTable.getCurrentFunction().getStackLocation();
					int offset = stack.get(id);
					inst.setAddr(new AddressingMode(-offset, "rbp"));
				}
			}
		}
		return inst;
	}
	
	//Ranjay
	public static ExpressionInstruction generateTile(OpIR op, TempTable tTable){
		ExpressionInstruction inst = new ExpressionInstruction();
		if (op.getChildren().size()>1){
			ExpressionInstruction child1 = (ExpressionInstruction) generateTile((SyntaxIR) op.getChildren().get(0),tTable);
			ExpressionInstruction child2 = (ExpressionInstruction) generateTile((SyntaxIR) op.getChildren().get(1),tTable);
			inst.addAll(child1.getTiles());
			inst.add(new Tile(1,"pushq", child1.getAddr(),null));
			inst.addAll(child2.getTiles());
			inst.add(new Tile(1,"movq",child2.getAddr(), new AddressingMode("r12") ));
			inst.add(new Tile(1,"popq", new AddressingMode("r13"),null));
			if (op.getOp() == opEnum.DIVIDE || op.getOp() == opEnum.MODULO){
				inst.add(new Tile(1, "pushq", new AddressingMode("rdx"), null));
				inst.add(new Tile(1, "pushq", new AddressingMode("rax"), null));
				inst.add(new Tile(1, "movq",new AddressingMode("r13"),new AddressingMode("rax")));
				//we need to sign extend r13 into rdx
				inst.add(new Tile(1, "cmp", new AddressingMode(0),new AddressingMode("r13")));
				//if r12 is less than 0 we move FFFFFFFFFFFFFFFF to rdx otherwise move zero
				int div = tTable.getNewJumpLabel();
				inst.add(new Tile(1, "jl", new AddressingMode("div"+div,true),null));				
				//fall through to moving zero
				inst.add(new Tile(1, "movq",new AddressingMode(0),new AddressingMode("rdx")));
				inst.add(new Tile(1, "jmp", new AddressingMode("divEnd"+div, true), null));
				inst.add(new Tile(1, "div"+div+":",null,null));
				inst.add(new Tile(1, "movq",new AddressingMode(-1),new AddressingMode("rdx")));
				inst.add(new Tile(1, "divEnd"+div+":",null,null));
				inst.add(new Tile(1,"idiv",new AddressingMode("r12"),null));
				//The result is put in RAX so we need to push that on the stack
				if (op.getOp()==opEnum.DIVIDE)
					inst.add(new Tile(1, "movq", new AddressingMode("rax"),new AddressingMode("r12")));
				else
					inst.add(new Tile(1, "movq", new AddressingMode("rdx"),new AddressingMode("r12")));
				inst.add(new Tile(1, "popq", new AddressingMode("rax"), null));
				inst.add(new Tile(1, "popq", new AddressingMode("rdx"), null));
				inst.setAddr(new AddressingMode("r12"));
			}
			else if (op.returnsBool() && op.getOp() != opEnum.AND && op.getOp() != opEnum.OR){
				int x = tTable.getNewJumpLabel();
				inst.add(new Tile(1,"cmp",new AddressingMode("r12"),new AddressingMode("r13")));
				inst.add(new Tile(1,op.opToAssembly(),new AddressingMode("_true"+x,true),null));
				inst.add(new Tile(1,"movq",new AddressingMode(0),new AddressingMode("r12")));
				inst.add(new Tile(1,"jmp",new AddressingMode("_end"+x,true),null));
				inst.add(new Tile(1,"_true"+x+": ",null,null));
				inst.add(new Tile(1,"movq",new AddressingMode(1),new AddressingMode("r12")));
				inst.add(new Tile(1,"_end"+x+": ",null,null));
				inst.setAddr(new AddressingMode("r12"));
			}
			else if(op.getOp() == opEnum.LSHIFT || op.getOp() == opEnum.RSHIFT){
				inst.add(new Tile(1,op.opToAssembly(),new AddressingMode(3),new AddressingMode("r13") ));
				inst.setAddr(new AddressingMode("r13"));
			}
			else{
				inst.add(new Tile(1,op.opToAssembly(),new AddressingMode("r12"),new AddressingMode("r13") ));
				inst.setAddr(new AddressingMode("r13"));
			}
		}
		else{
			ExpressionInstruction child = (ExpressionInstruction) generateTile((SyntaxIR) op.getChildren().get(0),tTable);
			inst.addAll(child.getTiles());
			switch (op.getOp()){
			case NOT: 
				inst.add(new Tile(1,"btc",new AddressingMode(0),child.getAddr()));
				inst.setAddr(child.getAddr());
				break;
			case UNARY_MINUS:
				inst.add(new Tile(1,"neg",child.getAddr(),null));
				inst.setAddr(child.getAddr());
				break;
			}
		}
		return inst;
	}
	
	//Ranjay
	public static Instruction generateTile(CallIR call, TempTable tTable){
		ExpressionInstruction inst = new ExpressionInstruction();
		//Push caller saved: rcx,rdx,rsp,rsi,rdi,r8,r9,r10,r11
		inst.add(new Tile(1, "movq", new AddressingMode("rcx"), new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rcx"),"rbp")));
		inst.add(new Tile(1, "movq", new AddressingMode("rdx"), new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rdx"),"rbp")));
		inst.add(new Tile(1, "movq", new AddressingMode("rsp"), new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rsp"),"rbp")));
		inst.add(new Tile(1, "movq", new AddressingMode("rsi"), new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rsi"),"rbp")));
		inst.add(new Tile(1, "movq", new AddressingMode("rdi"), new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rdi"),"rbp")));
		inst.add(new Tile(1, "movq", new AddressingMode("r8" ), new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r8" ),"rbp")));
		inst.add(new Tile(1, "movq", new AddressingMode("r9" ), new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r9" ),"rbp")));
		inst.add(new Tile(1, "movq", new AddressingMode("r10"), new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r10"),"rbp")));
		inst.add(new Tile(1, "movq", new AddressingMode("r11"), new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r11"),"rbp")));
		inst.add(new Tile(1, "movq", new AddressingMode("r15"), new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r15"),"rbp")));
		inst.add(new Tile(1, "pushq",new AddressingMode("rbx"), null));
		inst.add(new Tile(1, "pushq",new AddressingMode("rbp"), null));
		
		//handle arguments
		Instruction args = handleArguments(call,tTable);
		inst.addAll(args.getTiles());
		
		//make call
		Instruction ooo = makeCall(call,tTable);
		inst.addAll(ooo.getTiles());
		
		//pop caller saved
		inst.add(new Tile(1, "popq", new AddressingMode("rbp"), null));
		inst.add(new Tile(1, "popq", new AddressingMode("rbx"), null));
		inst.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r15"),"rbp"),new AddressingMode("r15")));
		inst.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r11"),"rbp"),new AddressingMode("r11")));
		inst.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r10"),"rbp"),new AddressingMode("r10")));
		inst.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r9" ),"rbp"),new AddressingMode("r9" )));
		inst.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("r8" ),"rbp"),new AddressingMode("r8" )));
		inst.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rdi"),"rbp"),new AddressingMode("rdi")));
		inst.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rsi"),"rbp"),new AddressingMode("rsi")));
		inst.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rsp"),"rbp"),new AddressingMode("rsp")));
		inst.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rdx"),"rbp"),new AddressingMode("rdx")));
		inst.add(new Tile(1, "movq", new AddressingMode(-1*tTable.getCurrentFunction().getStackLocation().get("rcx"),"rbp"),new AddressingMode("rcx")));
		inst.setAddr(new AddressingMode("rax"));
		return inst;
	}
	
	//Ranjay but needs to be optimized
	private static Instruction handleArguments(CallIR call, TempTable tTable) {
		Instruction inst = new Instruction();
		//Now that we have saved the caller-saved registers we get the tilings
		//for the arguments given to the function
		ArrayList<ExpressionInstruction> argumentTileLists = new ArrayList<ExpressionInstruction>();
		for(int i = 1; i < call.getChildren().size(); i++){
			argumentTileLists.add((ExpressionInstruction) generateTile((SyntaxIR)call.getChildren().get(i),tTable));
			
		}
		//Now that we have the arguments we must push them on the stack and or registers
		AddressingMode addr12 = new AddressingMode("r12");
		AddressingMode addrR9 = new AddressingMode("r9");
		AddressingMode addrR8 = new AddressingMode("r8");
		AddressingMode addrRDX = new AddressingMode("rdx");
		AddressingMode addrRCX = new AddressingMode("rcx");
		AddressingMode addrRSI = new AddressingMode("rsi");
		AddressingMode addrRDI = new AddressingMode("rdi");
		int retsize = call.getretsize();
		if(retsize > 1) {
			CallIR c = new CallIR(new NameIR("_I_alloc_i"), new ConstIR(8*(retsize+1)));
			c.setretsize(1);
			ExpressionInstruction ex = (ExpressionInstruction) generateTile(c,tTable);
			inst.addAll(ex.getTiles());
			inst.add(new Tile(1, "movq", ex.getAddr(), new AddressingMode("rcx")));
			inst.add(new Tile(1, "movq", new AddressingMode(retsize), new AddressingMode(0,"rcx")));
			inst.add(new Tile(1, "add", new AddressingMode(8), new AddressingMode("rcx")));
		}
		if((argumentTileLists.size() <= 4 && retsize <=1) || (argumentTileLists.size() <=3 && retsize > 1)) {
			for(int i = 0; i < argumentTileLists.size(); i ++) {
				inst.addAll(argumentTileLists.get(i).getTiles());
				inst.add(new Tile(1, "pushq", argumentTileLists.get(i).getAddr(), null));
			}
		} 
		else {
			if(retsize > 1) {
				for(int i = argumentTileLists.size() -1; i >=3; i --) {
					inst.addAll(argumentTileLists.get(i).getTiles());
					inst.add(new Tile(1, "pushq", argumentTileLists.get(i).getAddr(), null));
				}
				for(int i = 0; i < 3; i ++) {
					inst.addAll(argumentTileLists.get(i).getTiles());
					inst.add(new Tile(1, "pushq", argumentTileLists.get(i).getAddr(), null));
				}
			} else {
				for(int i = argumentTileLists.size() -1; i >=4; i --) {
					inst.addAll(argumentTileLists.get(i).getTiles());
					inst.add(new Tile(1, "pushq", argumentTileLists.get(i).getAddr(), null));
				}
				for(int i = 0; i < 4; i ++) {
					inst.addAll(argumentTileLists.get(i).getTiles());
					inst.add(new Tile(1, "pushq", argumentTileLists.get(i).getAddr(), null));
				}
			}
		}
		switch(argumentTileLists.size()){
		case 4:
			if(retsize <= 1) {
				inst.add(new Tile(1,"popq",addrR9,null));
			}
		case 3:
			if(retsize > 1) {
				inst.add(new Tile(1,"popq",addrR9,null));
			} else {
				inst.add(new Tile(1,"popq",addrR8,null));
			}
		case 2:
			if(retsize > 1) {
				inst.add(new Tile(1,"popq",addrR8,null));
			} else {
				inst.add(new Tile(1,"popq",addrRDX,null));
			}
		case 1:
			if(retsize > 1) {
				inst.add(new Tile(1,"popq",addrRDX,null));
			} else {
				inst.add(new Tile(1,"popq",addrRCX,null));
			}
		case 0:
			//Do nothing
			break;
		default://we have more than five arguments
			//push the first 6 arguments to registers
			//and we're not using RDI (not returning a tuple)
			if(retsize <= 1) {
				inst.add(new Tile(1,"popq",addrR9,null));
				inst.add(new Tile(1,"popq",addrR8,null));
				inst.add(new Tile(1,"popq",addrRDX,null));
				inst.add(new Tile(1,"popq",addrRCX,null));
			}
			//we're returning a tuple
			else {
				inst.add(new Tile(1,"popq",addrR9,null));
				inst.add(new Tile(1,"popq",addrR8,null));
				inst.add(new Tile(1,"popq",addrRDX,null));
			}
		}
		return inst;
	}

	//Colin check that this is correct
	private static Instruction makeCall(CallIR call, TempTable tTable) {
		Instruction inst = new Instruction();
		//Now we can finally make the call
		AddressingMode func = new AddressingMode(((NameIR)call.getChildren().get(0)).getLabel(), true);
		//if the stack isn't 16 bit aligned, we need to do this
		AddressingMode rbp = new AddressingMode("rbp");
		AddressingMode rsp = new AddressingMode("rsp");
		AddressingMode r12 = new AddressingMode("r12");
		AddressingMode r13 = new AddressingMode("r13");
		//Check if the 3rd bit is 1 or zero and set the carry bit accordingly
		inst.add(new Tile(0, "movq", new AddressingMode(0), new AddressingMode("rbx")));
		inst.add(new Tile(1, "bt", new AddressingMode(3), rsp));
		Integer callInt = tTable.getNewJumpLabel();
		//jump to rspJmp if stack pointer is even
		inst.add(new Tile(1, "jnc", new AddressingMode("rspJmp" + callInt, true), null));
		//stack pointer was odd
		inst.add(new Tile(1, "movq", new AddressingMode(1), r12));
		inst.add(new Tile(1, "jmp", new AddressingMode("rspend" + callInt, true), null));
		inst.add(new Tile(1, "rspJmp" + callInt + ": ", null, null));
		//save that stack pointer was even in r12
		inst.add(new Tile(1, "movq", new AddressingMode(0), r12));
		inst.add(new Tile(1, "rspend" + callInt + ": ", null, null));
		//check rbp for being divisible by 8(odd) or 16(even)
		inst.add(new Tile(1, "bt", new AddressingMode(3), rbp));
		inst.add(new Tile(1, "jnc", new AddressingMode("rbpJmp" + callInt, true), null));
		//didn't jump since base pointer is odd
		inst.add(new Tile(1, "movq", new AddressingMode(1), r13));
		inst.add(new Tile(1, "jmp", new AddressingMode("rbpend" + callInt, true), null));
		inst.add(new Tile(1, "rbpJmp" + callInt + ": ", null, null));
		// base pointer is even
		inst.add(new Tile(1, "movq", new AddressingMode(0), r13));
		inst.add(new Tile(1, "rbpend" + callInt + ": ", null, null));
		//Now we just need to logically xnor r12 and r13, i.e. if they are both zero or both one
		//Test r1,r2 sets the parity flag to the xnor so we will use that
		inst.add(new Tile(1, "test", r12, r13));
		//jump to extraPush(which doesn't make an extra Push) if they were equal
		inst.add(new Tile(1, "jnp", new AddressingMode("extraPush"+callInt,true),null));
		//ret.add(new Tile(1, "sub", r12, r13));
		//ret.add(new Tile(1, "jne", new AddressingMode("extraPush" + callInt, true), null));
		inst.add(new Tile(1, "pushq", new AddressingMode(0), null));
		//also store value in rbx(a callee saved register) so we can know we were odd after return
		inst.add(new Tile(1, "movq", new AddressingMode(1),new AddressingMode("rbx")));
		inst.add(new Tile(1, "extraPush" + callInt + ": ", null, null));
		//otherwise store 0 in rbx
		//windows requires 32 bytes of ghost space
		inst.add(new Tile(1, "pushq", new AddressingMode(0), null));
		inst.add(new Tile(1, "pushq", new AddressingMode(0), null));
		inst.add(new Tile(1, "pushq", new AddressingMode(0), null));
		inst.add(new Tile(1, "pushq", new AddressingMode(0), null));
		inst.add(new Tile(1,"call",func,null));
		inst.add(new Tile(1, "popq", r13, null));
		inst.add(new Tile(1, "popq", r13, null));
		inst.add(new Tile(1, "popq", r13, null));
		inst.add(new Tile(1, "popq", r13, null));
		inst.add(new Tile(1, "cmp", new AddressingMode(1), new AddressingMode("rbx")));
		//if rbx is 1 then we had to push an extra frame before so pop it off if rbx = 1
		inst.add(new Tile(1, "jne", new AddressingMode("extraPop" + callInt, true), null));
		inst.add(new Tile(1, "popq", r13, null));
		inst.add(new Tile(1, "extraPop" + callInt + ": ", null, null));
		inst.add(new Tile(1, "movq", new AddressingMode(0),new AddressingMode("rbx")));
		//assuming the ret call moves the stack pointer back to before the arguments we should 
		if(call.getretsize() > 1) {
			inst.add(new Tile(1, "movq", new AddressingMode("rcx"), new AddressingMode("rax")));
		}
		// now we need to pop off extra arguments that we moved onto the stack
		int argumentsize = call.getChildren().size() - 1;
		if(call.getretsize() > 1 && argumentsize > 3) {
			for(int i = argumentsize; i > 3; i --) {
				inst.add(new Tile(1,"popq",new AddressingMode("r12"),null));
			}
		} else if (argumentsize > 4) {
			for(int i = argumentsize; i > 4; i --) {
				inst.add(new Tile(1,"popq",new AddressingMode("r12"),null));
			}
		}
		return inst;
	}

	//Ranjay
	public static ExpressionInstruction generateTile(MemIR mem, TempTable tTable){
		ExpressionInstruction inst = new ExpressionInstruction();
		SyntaxIR address = (SyntaxIR) mem.getChildren().get(0);
		Instruction childInst = generateTile(address,tTable);
		inst.addAll(childInst.getTiles());
		AddressingMode addr = ((ExpressionInstruction) childInst).getAddr();
		if (addr.getMemAccess()){
			inst.add(new Tile(1,"movq", addr, new AddressingMode("r12")));
			inst.setAddr(new AddressingMode(true,"r12"));
		}
		else{
			inst.setAddr(new AddressingMode(true,addr.getBaseReg()));
		}
		return inst;
	}

}
