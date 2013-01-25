package rak248.opt;

import java.util.ArrayList;
import java.util.HashMap;

import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.Type;

public class StaticStack {
	
	public static void staticStackFrame(SyntaxIR comp, HashMap<String, HashMap<String, String>> reg){
		int index = 0;
		for (VisualizableIRNode node: comp.getChildren()){
			if ( (node instanceof LabelIR) && ((LabelIR)node).getName().startsWith("_I")){
				LabelIR label = ((LabelIR)node);
				String name = label.getName();
				label.setStackLocation(new HashMap<String, Integer>());
				label.setRegAlloc(new HashMap<String, String>());
				int i = 8;
				i = saveCallerSaved(label,i);
				if (reg == null){
					i = saveSpilledTemps(label,comp,i,index);
				}
				else{
					i = saveTemps(label,comp,i,index,reg.get(name));
				}
				int odd = i/8;
				if (odd%2 == 0){
					label.getStackLocation().put("_nothing",i); i = i + 8;	
				}
				i = saveArguments(label,i);
			}
			index++;
		}
	}


	private static int saveArguments(LabelIR label, int i) {
		ArrayList<Type> params = label.getParams();
		int regs = 6;
		if (label.getRets().size() > 1){
			regs = 5;
		}
		int num = params.size();
		for (int j = num; j > regs; j--){
			label.getStackLocation().put("_arg"+j, i);
			i++;
		}
		return i;
	}


	private static int saveTemps(LabelIR label, SyntaxIR comp, int i,
			int index, HashMap<String, String> hashMap) {
		index++;
		boolean bool = true;
		while(bool){
			VisualizableIRNode node = comp.getChildren().get(index);
			index = index+1;
			if (index>=comp.getChildren().size()){
				bool = false;
			}
			if (node instanceof LabelIR){
				if ( ((LabelIR)node).getName().startsWith("_I") ){
					bool = false;
				}
			}
			if (node instanceof MoveIR){
				VisualizableIRNode temp = ((MoveIR) node).getChildren().get(0);
				if (temp instanceof TempIR){
					String id = ((TempIR) temp).getId();
					if (hashMap.containsKey(id) && !hashMap.get(id).equals("stack")){
						if ( !label.getRegAlloc().containsKey(id)){
							label.getRegAlloc().put(id, hashMap.get(id));
						}
					}
					else if ( !label.getStackLocation().containsKey(id) ){
						label.getStackLocation().put(id,i);
						i = i + 8;
					}
					else{
						//System.err.println(temp);
					}
				}
			}
		}
		return i;
	}


	private static int saveSpilledTemps(LabelIR label, SyntaxIR comp, int i,int index) {
		index++;
		boolean bool = true;
		while(bool){
			VisualizableIRNode node = comp.getChildren().get(index);
			index = index+1;
			if (index>=comp.getChildren().size()){
				bool = false;
			}
			if (node instanceof LabelIR){
				if ( ((LabelIR)node).getName().startsWith("_I") ){
					bool = false;
				}
			}
			if (node instanceof MoveIR){
				VisualizableIRNode temp = ((MoveIR) node).getChildren().get(0);
				if (temp instanceof TempIR){
					String id = ((TempIR) temp).getId();
					if ( !label.getStackLocation().containsKey(id) ){
						label.getStackLocation().put(id,i);
						i = i + 8;
					}
				}
			}
		}
		return i;
	}


	private static int saveCallerSaved(LabelIR label, int i) {
		label.getStackLocation().put("rax",i); i = i + 8;
		label.getStackLocation().put("rcx",i); i = i + 8;
		label.getStackLocation().put("rdx",i); i = i + 8;
		label.getStackLocation().put("rsp",i); i = i + 8;
		label.getStackLocation().put("rdi",i); i = i + 8;
		label.getStackLocation().put("rsi",i); i = i + 8;
		label.getStackLocation().put("r8" ,i); i = i + 8;
		label.getStackLocation().put("r9" ,i); i = i + 8;
		label.getStackLocation().put("r10",i); i = i + 8;
		label.getStackLocation().put("r11",i); i = i + 8;
		label.getStackLocation().put("r15",i); i = i + 8;
		return i;
	}
}
