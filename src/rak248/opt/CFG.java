package rak248.opt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.CJumpIR;
import rak248.xi.ir.ExpIR;
import rak248.xi.ir.JumpIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.ReturnIR;

public class CFG{
	
	private ArrayList<CFGNode> headlist;
	private CFGNode current;
	private HashMap<String, CFGNode> labels;
	private HashMap<CFGNode, CFGNode> jumpmap;
	
	public CFG(ArrayList<VisualizableIRNode> nodes) {
		headlist = new ArrayList<CFGNode>();
		current = null;
		labels = new HashMap<String, CFGNode>();
		jumpmap = new HashMap<CFGNode, CFGNode>();
		for(VisualizableIRNode nodeVIN : nodes) {
			SyntaxIR node = (SyntaxIR) nodeVIN;
			if(node instanceof LabelIR) {
				if(!((LabelIR) node).getName().startsWith("_block")) {
					CFGNode next = new CFGNode();
					next.setIrnode(node);
					labels.put(((LabelIR) node).getName(), next);
				}
			}
		}
		for(VisualizableIRNode nodeVIN : nodes) {
			SyntaxIR node = (SyntaxIR) nodeVIN;
			if(node instanceof CJumpIR) {
				CFGNode next = new CFGNode();
				String label = ((CJumpIR) node).getlt();
				if(label.startsWith("LABEL")) {
					label = label.substring(7, label.length());
				}
				CFGNode left = labels.get(label);
				String lf = ((CJumpIR) node).getlf();
				lf = lf.substring(7, lf.length());
				CFGNode right = labels.get(lf);
				next.setIrnode(node);
				jumpmap.put(right, next);
				jumpmap.put(left, next);
			}
			if(node instanceof JumpIR) {
				CFGNode next = new CFGNode();
				String label = ((NameIR) node.getChildren().get(0)).getLabel();
				if(label.startsWith("LABEL")) {
					label = label.substring(7, label.length());
				}
				CFGNode jump = labels.get(label);
				next.setIrnode(node);
				jumpmap.put(jump, next);
			}
		}
		for(VisualizableIRNode nodeVIN : nodes) {
			CFGNode head = null;
			SyntaxIR node = (SyntaxIR) nodeVIN;
			if((node instanceof MoveIR) || (node instanceof ExpIR)) {
				CFGNode next = new CFGNode();
				next.addParent(current);
				if(current != null) {
					current.setChild1(next);
				}
				current = next;
				next.setIrnode((SyntaxIR) node);
			} else if(node instanceof JumpIR) {
				if(current == null) {
					String label = ((NameIR)(node.getChildren().get(0))).getLabel();
					if(label.startsWith("LABEL")) {
						label = label.substring(7, label.length());
					}
					//labels.remove(label);
				}
				if(current != null) {
					CFGNode jumpCFG = new CFGNode();
					for(CFGNode cnode : jumpmap.values()) {
						if(cnode.getIrnode().equals(node)) {
							jumpCFG = cnode;
						}
					}
					jumpCFG.setIrnode(node);
					String label = ((NameIR)(node.getChildren().get(0))).getLabel();
					if(label.startsWith("LABEL")) {
						label = label.substring(7, label.length());
					}	
					CFGNode next = labels.get(label);
					current.setChild1(jumpCFG);
					jumpCFG.addParent(current);
					jumpCFG.setChild1(next);
					next.addParent(jumpCFG);
					current = null;
					next = null;
				}
				current = null;
			} else if(node instanceof LabelIR) {
				String label = ((LabelIR) node).getName();
				if(label.startsWith("_I")) {
					CFGNode next = labels.get(label);
					current = next;
					head = current;
					headlist.add(head);
					next.setParents(new ArrayList<CFGNode>());
				} else if(!((LabelIR) node).getName().startsWith("_block")){
					CFGNode next = labels.get(label);
					if(current != null) {
						next.addParent(current);
						current.setChild1(next);
					}
					current = next;
					if(jumpmap.get(next) != null) {
						jumpmap.get(next).setChild1(next);
						next.addParent(jumpmap.get(next));
					}
				}
			} else if(node instanceof CJumpIR) {
				String lt = ((CJumpIR) node).getlt();
				if(lt.startsWith("LABEL:")) 
					lt = lt.substring(7, lt.length());
				CFGNode left = labels.get(lt);
				String lf = ((CJumpIR) node).getlf();
				lf = lf.substring(7, lf.length());
				CFGNode right = labels.get(lf);
				CFGNode next = null;
				for(CFGNode cnode : jumpmap.values()) {
					if(cnode.getIrnode().equals(node)) {
						next = cnode;
					}
				}
				next.setIrnode(node);
				next.setChild1(left);
				next.setChild2(right);
				left.addParent(next);
				right.addParent(next);
				if(current != null) {
					next.addParent(current);
					current.setChild1(next);
				}
				current = next;
			} else if(node instanceof ReturnIR) {
				CFGNode next = new CFGNode();
				next.setIrnode(node);
				current.setChild1(next);
				next.addParent(current);
				current = null;
			}
		}
	}
	
	public static void printDot(CFGNode root, String filename){
		String graph = "digraph test {\n";
		HashSet<CFGNode> done = new HashSet<CFGNode>();
		graph += root.print(done);
		graph += "}";
		FileWriter f;
		try {
			f = new FileWriter(filename);
			BufferedWriter b = new BufferedWriter(f);
			b.write(graph);
			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void printDotToStdOut(CFGNode root, String name){
		String graph = "digraph " + name + " {\n";
		HashSet<CFGNode> done = new HashSet<CFGNode>();
		graph += root.print(done);
		graph += "}";
		System.out.println(graph);
		
	}
	
	public ArrayList<CFGNode> getHeadList() {
		return headlist;
	}
}
