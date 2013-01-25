package rak248.opt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import rak248.xi.SyntaxIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.TempIR;

public class RegAllocation {
	
	public static HashMap<String,InterferenceGraphNode> createGraph(HashSet<CFGNode> method){
		HashMap<String,InterferenceGraphNode> graph = new HashMap<String,InterferenceGraphNode>();
		for (CFGNode node: method){
			ArrayList<InterferenceGraphNode> seenNeighbors = new ArrayList<InterferenceGraphNode>();
			for (TempIR temp: node.getOut()){
				
				InterferenceGraphNode n = graph.get(temp.getId());
				if (n == null){
					n = new InterferenceGraphNode(temp);
					graph.put(temp.getId(),n);
				}
				
				for(InterferenceGraphNode neighbor : seenNeighbors) {
					if(!neighbor.getNeighbors().contains(n))
						neighbor.getNeighbors().add(n);
					if(!n.getNeighbors().contains(neighbor))
						n.getNeighbors().add(neighbor);
				}
				seenNeighbors.add(n); 
			}
		}
		return graph;
	}
	
	public static HashMap<String, HashMap<String, String>> registerAllocate(ArrayList<CFGNode> roots) {
		HashMap<String, HashMap<String, String>> ret = new HashMap<String,HashMap<String,String>>();
		for (CFGNode method: roots){
			HashSet<CFGNode> m = new HashSet<CFGNode>();
			new Worklist().getAllNodes(method,m);
			HashMap<String,InterferenceGraphNode> h = createGraph(m);
			ArrayList<String> regs = getCallerRegisters();
			Coalescing.combineMove(m,h,regs.size());
			HashMap<String,String> max = kempeColoring(h,regs,(LabelIR) method.getIrnode());
			ret.put(((LabelIR)method.getIrnode()).getName(),max);
		}
		return ret;
	}
	
	public static void printCheck(ArrayList<CFGNode> roots){
		for (CFGNode method: roots){
			System.out.println("NEW METHOD");
			HashSet<CFGNode> m = new HashSet<CFGNode>();
			new Worklist().getAllNodes(method,m);
			HashMap<String,InterferenceGraphNode> h = createGraph(m);
			for (InterferenceGraphNode ign: h.values()){
				System.out.println(ign);
			}
			System.out.println("COLORING: "+prettyPrint(kempeColoring(h,getCallerRegisters(),(LabelIR) method.getIrnode())));
		}
	}
	
	private static String prettyPrint(HashMap<String, String> hashMap) {
		String ret = "";
		for (String temp: hashMap.keySet()){
			ret += temp + ": " + hashMap.get(temp) + "\n";
		}
		return ret;
	}

	public static ArrayList<String> getCallerRegisters(){
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("r10");
		ret.add("r11");
		ret.add("r15");
		//ret.add("rdx");
		//ret.add("rdi");
		//ret.add("rsi");
		ret.add("r8" );
		ret.add("r9" );
		return ret;
	}
	
	public static HashMap<String, String> kempeColoring(HashMap<String, InterferenceGraphNode> h,ArrayList<String> registers, LabelIR label){
		HashMap<TempIR,Integer> coloring = kempe(registers.size(),h);
		HashMap<String,String> ret = new HashMap<String, String>();
		HashMap<Integer,String> taken = new HashMap<Integer, String>();
		for (TempIR temp: coloring.keySet()){
			if (temp.getId().startsWith("_args")){
				if (label.getRets().size() > 1){
					if (temp.getId().startsWith("_args0")){
						ret.put(temp.getId(),"rsi");
						taken.put(coloring.get(temp), "rsi");
						for (InterferenceGraphNode connect: h.get(temp.getId()).getCoalesce())
							ret.put(connect.getTemp().getId(), "rsi");
					}
					else if (temp.getId().startsWith("_args1")){
						ret.put(temp.getId(),"rdx");
						taken.put(coloring.get(temp), "rdx");
						for (InterferenceGraphNode connect: h.get(temp.getId()).getCoalesce())
							ret.put(connect.getTemp().getId(), "rdx");
					}
					else if (temp.getId().startsWith("_args2")){
						ret.put(temp.getId(),"rcx");
						taken.put(coloring.get(temp), "rcx");
						for (InterferenceGraphNode connect: h.get(temp.getId()).getCoalesce())
							ret.put(connect.getTemp().getId(), "rcx");
					}
					else if (temp.getId().startsWith("_args3")){
						ret.put(temp.getId(),"r8");
						taken.put(coloring.get(temp), "r8");
						for (InterferenceGraphNode connect: h.get(temp.getId()).getCoalesce())
							ret.put(connect.getTemp().getId(), "r8");
					}
					else if (temp.getId().startsWith("_args4")){
						ret.put(temp.getId(),"r9");
						taken.put(coloring.get(temp), "r9");
						for (InterferenceGraphNode connect: h.get(temp.getId()).getCoalesce())
							ret.put(connect.getTemp().getId(), "r9");
					}
				}
				else{
					if (temp.getId().startsWith("_args0")){
						ret.put(temp.getId(),"rdi");
						taken.put(coloring.get(temp), "rdi");
						for (InterferenceGraphNode connect: h.get(temp.getId()).getCoalesce())
							ret.put(connect.getTemp().getId(), "rdi");
					}
					else if (temp.getId().startsWith("_args1")){
						ret.put(temp.getId(),"rsi");
						taken.put(coloring.get(temp), "rsi");
						for (InterferenceGraphNode connect: h.get(temp.getId()).getCoalesce())
							ret.put(connect.getTemp().getId(), "rsi");
					}
					else if (temp.getId().startsWith("_args2")){
						ret.put(temp.getId(),"rdx");
						taken.put(coloring.get(temp), "rdx");
						for (InterferenceGraphNode connect: h.get(temp.getId()).getCoalesce())
							ret.put(connect.getTemp().getId(), "rdx");
					}
					else if (temp.getId().startsWith("_args3")){
						ret.put(temp.getId(),"rcx");
						taken.put(coloring.get(temp), "rcx");
						for (InterferenceGraphNode connect: h.get(temp.getId()).getCoalesce())
							ret.put(connect.getTemp().getId(), "rcx");
					}
					else if (temp.getId().startsWith("_args4")){
						ret.put(temp.getId(),"r8");
						taken.put(coloring.get(temp), "r8");
						for (InterferenceGraphNode connect: h.get(temp.getId()).getCoalesce())
							ret.put(connect.getTemp().getId(), "r8");
					}
					else if (temp.getId().startsWith("_args5")){
						ret.put(temp.getId(),"r9");
						taken.put(coloring.get(temp), "r9");
						for (InterferenceGraphNode connect: h.get(temp.getId()).getCoalesce())
							ret.put(connect.getTemp().getId(), "r9");
					}
				}
			}
		}
		for (TempIR temp: coloring.keySet()){
			if (!temp.getId().startsWith("_args")){
				Integer x = coloring.get(temp);
				if (taken.containsKey(x)){
					ret.put(temp.getId(), taken.get(x));
				}
				else{
					int o = 0;
					while(o<registers.size()&&taken.containsValue(registers.get(o))){
						o++;
					}
					if (o<registers.size()){
						ret.put(temp.getId(), registers.get(o));
						taken.put(x, registers.get(o));
						for (InterferenceGraphNode connect: h.get(temp.getId()).getCoalesce())
							ret.put(connect.getTemp().getId(), registers.get(o));
					}
					else{
						ret.put(temp.getId(), "stack");
						taken.put(x, "stack");
						for (InterferenceGraphNode connect: h.get(temp.getId()).getCoalesce())
							ret.put(connect.getTemp().getId(), "stack");
					}
				}
			}
		}
		return ret;
	}

	public static HashMap<TempIR,Integer> kempe(int k,HashMap<String, InterferenceGraphNode> h){
		HashMap<TempIR,Integer> coloring= new HashMap<TempIR,Integer>();
		Stack<InterferenceGraphNode> stack = new Stack<InterferenceGraphNode>();
		
		for (InterferenceGraphNode ign: h.values()){
			if (ign.getNeighbors().size() < k){
				stack.push(ign);
			}
			else if (ign.getNeighbors().size() > k){
				return kempe(ign.getNeighbors().size(),h);
			}
			else{
				assignColor(ign,coloring,stack);
			}
		}
		while (!stack.empty()){
			InterferenceGraphNode ign = stack.pop();
			assignColor(ign,coloring,stack);
		}
		return coloring;
		
	}

	private static void assignColor(InterferenceGraphNode node,
			HashMap<TempIR, Integer> coloring,
			Stack<InterferenceGraphNode> stack) {
		int color = 0;
		boolean f = checkColor(node,coloring,color,stack);
		while(!f){
			color++;
			f = checkColor(node,coloring,color,stack);
		}
		coloring.put(node.getTemp(),color);
		
	}

	private static boolean checkColor(InterferenceGraphNode node,
			HashMap<TempIR, Integer> coloring, int color,
			Stack<InterferenceGraphNode> stack) {
		for (InterferenceGraphNode ign: node.getNeighbors()){
			if (!stack.contains(ign) && coloring.get(ign.getTemp()) != null){
				if (coloring.get(ign.getTemp()) == color){
					return false;
				}
			}
		}
		return true;
	}
	
	
	
}
