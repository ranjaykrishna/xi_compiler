package rak248.opt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.ExpressionIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.TempIR;
import rak248.xi.ir.CJumpIR;

public class Worklist {
	
	private LinkedList<CFGNode> queue = new LinkedList<CFGNode>();
	private ArrayList<HashSet<CFGNode>> allNodes = new ArrayList<HashSet<CFGNode>>();
	
	public Worklist(){}
	
//=======================================================================================
//  live variable analysis
//=======================================================================================
	public void setupDefUse(ArrayList<CFGNode> roots){
		for(CFGNode root: roots) {
			HashSet<CFGNode> hs = new HashSet<CFGNode>();
			getAllNodes(root, hs);
			allNodes.add(hs);
			for (CFGNode node: hs){
				setUse(node);
				setDef(node);
			}
		}
	}
	
	public void liveVariableAnalysis(){
		for (HashSet<CFGNode> hh : allNodes){
		queue = new LinkedList<CFGNode>();	
		queue.addAll(hh);
		while (!queue.isEmpty()){
			CFGNode node = queue.pop();
			TreeSet<TempIR> outs = setOut(node);
			node.setOut(outs);
			TreeSet<TempIR> ins = setIn(node);
			if (ins.size() != node.getIn().size()){
				for (CFGNode n: node.getParents()){
					if (!queue.contains(n) && n != null){
						queue.push(n);
					}
				}
			}
			node.setIn(ins);			
		}
		}
	}

	private TreeSet<TempIR> setOut(CFGNode node) {
		TreeSet<TempIR> tree = new TreeSet<TempIR>();
		if (node.getChild1() != null){
			tree.addAll(node.getChild1().getIn());
		}
		if (node.getChild2() != null){
			tree.addAll(node.getChild2().getIn());
		}
		return tree;
	}

	private TreeSet<TempIR> setIn(CFGNode node) {
		TreeSet<TempIR> tree = new TreeSet<TempIR>();
		tree.addAll(node.getOut());
		for (TempIR temp: node.getDef()){
			if (tree.contains(temp)){
				tree.remove(temp);
			}
		}
		tree.addAll(node.getUse());
		return tree;
	}

	private void setDef(CFGNode node) {
		TreeSet<TempIR> d = new TreeSet<TempIR>();
		SyntaxIR n = node.getIrnode();
		if (n instanceof MoveIR){
			VisualizableIRNode l = n.getChildren().get(0);
			if (l instanceof TempIR){
				d.add((TempIR) l);
			}
		}
		node.setDef(d);
	}

	private void setUse(CFGNode node) {
		TreeSet<TempIR> u = new TreeSet<TempIR>();
		SyntaxIR n = node.getIrnode();
		ArrayList<TempIR> temps = useHelper(n);
		for (TempIR temp: temps){
			u.add(temp);
		}
		node.setUse(u);
	}
	
	private ArrayList<TempIR> useHelper(SyntaxIR n) {
		ArrayList<TempIR> ret = new ArrayList<TempIR>();
		int i = 0;
		if (n instanceof MoveIR){
			if (n.getChildren().get(0) instanceof TempIR){
				i = 1;
			}
		}
		for (;i<n.getChildren().size();i++){
			VisualizableIRNode child = n.getChildren().get(i);
			if (child instanceof TempIR){
				ret.add((TempIR) child);
			}
			ret.addAll(useHelper((SyntaxIR) child));
		}
		return ret;
	}
	
	public void getAllNodes(CFGNode node, HashSet<CFGNode> done) {
		if(done.contains(node)){
			return;
		}
		done.add(node);
		if(node.getChild1() != null) {
			getAllNodes(node.getChild1(), done);
		}
		if(node.getChild2() != null) {
			getAllNodes(node.getChild2(), done);
		}
	}
//=======================================================================================
//  Copy Propagation
//=======================================================================================	
	public void setupGenKill(ArrayList<CFGNode> roots){
		for(CFGNode root: roots) {
			HashSet<CFGNode> hs = new HashSet<CFGNode>();
			getAllNodes(root, hs);
			allNodes.add(hs);
			for (CFGNode node: hs){
				setGen(node);
				setKill(node);
			}
		}
	}
	
	private void setKill(CFGNode node) {
		TreeSet<TempIR> k = new TreeSet<TempIR>();
		if (node.getIrnode() instanceof MoveIR){
			ExpressionIR exp = (ExpressionIR) node.getIrnode().getChildren().get(0);
			if (exp instanceof TempIR){
				k.add((TempIR) exp);
			}
		}
		node.setKill(k);
	}

	private void setGen(CFGNode node) {
		HashSet<Copy> g = new HashSet<Copy>();
		if (node.getIrnode() instanceof MoveIR && !(node.getIrnode().getChildren().get(1) instanceof LabelIR)){
			ExpressionIR child1 = (ExpressionIR) node.getIrnode().getChildren().get(0);
			ExpressionIR child2 = (ExpressionIR) node.getIrnode().getChildren().get(1);
			if (child1 instanceof TempIR && child2 instanceof TempIR
					&& ((TempIR) child1).getId().equals(((TempIR) child2).getId())){
				Copy copy = new Copy((TempIR) child1,(TempIR) child2);
				g.add(copy);
			}
		}
		node.setGen(g);
	}
	
	public void copyPropagation(ArrayList<CFGNode> roots){
		setupGenKill(roots);
		for (HashSet<CFGNode> hh : allNodes){
			queue = new LinkedList<CFGNode>();	
			queue.addAll(hh);
			while(!queue.isEmpty()){
				CFGNode node = queue.pop();
				node.setCopyIn(setCopyIn(node));
				HashSet<Copy> out = setCopyOut(node);
				if (out.size() != node.getCopyOut().size()){
					if (node.getChild1() != null){
						queue.push(node.getChild1());
					}
					if (node.getChild2() != null){
						queue.push(node.getChild2());
					}
				}
				node.setCopyOut(out);
			}
			propagate(hh);
		}
	}

	private void propagate(HashSet<CFGNode> hh) {
		for (CFGNode node: hh){
			for (Copy c: node.getCopyIn()){
				TempIR src = c.getSrc();
				TempIR dest = c.getDest();
				replace(node.getIrnode(),dest,src);
			}
		}
	}

	private void replace(SyntaxIR irnode, TempIR dest, TempIR src) {
		if (irnode instanceof TempIR){
			TempIR temp = (TempIR) irnode;
			if (temp.getId().equals(dest.getId())){
				temp.setId(src.getId());
			}
		}
		else{
			for (VisualizableIRNode node: irnode.getChildren()){
				replace((SyntaxIR) node,dest,src);
			}
		}
		
	}

	private HashSet<Copy> setCopyOut(CFGNode node) {
		HashSet<Copy> copy = new HashSet<Copy>();
		//copy.addAll(node.getCopyIn());
		for (Copy c: node.getCopyIn()){
			boolean f = true;
			for (TempIR temp: node.getKill()){
				if (c.cequals(temp)){
					f = false;
				}
			}
			if (f){
				copy.add(c);
			}
		}
		copy.addAll(node.getGen());
		return copy;
	}

	private HashSet<Copy> setCopyIn(CFGNode node) {
		HashSet<Copy> copy = new HashSet<Copy>();
		for (CFGNode parent: node.getParents()){
			copy.addAll(parent.getCopyOut());
		}
		return copy;
	}
	
//=======================================================================================
//  Constant Propagation
//=======================================================================================	
	public void constPropagation(ArrayList<CFGNode> roots){
		allNodes = new ArrayList<HashSet<CFGNode>>();
		//setup defs
		for(CFGNode root: roots) {
			HashSet<CFGNode> hs = new HashSet<CFGNode>();
			getAllNodes(root, hs);
			allNodes.add(hs);
			for (CFGNode node: hs){
				setupdefndefc(node);
			}
		}
		//setup ins and outs
		for (HashSet<CFGNode> hh : allNodes){
			queue = new LinkedList<CFGNode>();	
			queue.addAll(hh);
			while(!queue.isEmpty()){
				CFGNode node = queue.pop();
				node.setConstIn(setConstIn(node));
				HashSet<Const> out = setConstOut(node);
				if (out.size() != node.getConstOut().size()){
					if (node.getChild1() != null){
						queue.push(node.getChild1());
					}
					if (node.getChild2() != null){
						queue.push(node.getChild2());
					}
				}
				node.setConstOut(out);
			}
			Constpropagate(hh);
		}
		
	}
	


	private void Constpropagate(HashSet<CFGNode> hh) {
		for (CFGNode node: hh){
			for (Const cons: node.getConstOut()){
				node.setIrnode(replaceConst(node.getIrnode(),cons));
			}
		}
		
	}

	private SyntaxIR replaceConst(SyntaxIR irnode, Const cons) {
		if (irnode instanceof TempIR && cons.getTemp().getId().equals(((TempIR) irnode).getId()) ){
				return cons.getCons();
		}
		else if (irnode instanceof MoveIR && irnode.getChildren().get(0) instanceof TempIR){
			VisualizableIRNode child =  irnode.getChildren().get(1);
			irnode.getChildren().set(1, replaceConst((SyntaxIR) child,cons));
			return irnode;
		}
		else{
			ArrayList<VisualizableIRNode> children = new ArrayList<VisualizableIRNode>();
			for (VisualizableIRNode child: irnode.getChildren()){
				children.add(replaceConst((SyntaxIR) child,cons));
			}
			irnode.setChildren(children);
			return irnode;
		}
	}

	private HashSet<Const> setConstOut(CFGNode node) {
		HashSet<Const> ret = new HashSet<Const>();
		//ret.addAll(node.getConstIn());
		for (TempIR temp: node.getDefn()){
			for(Const def: node.getConstIn()){
				if (!temp.getId().equals(def.getTemp().getId()) && !ret.contains(def)){
					ret.add(def);
				}
			}
		}
		ret.addAll(node.getDefc());
		return ret;
	}

	private HashSet<Const> setConstIn(CFGNode node) {
		HashSet<Const> ret = new HashSet<Const>();
		for (CFGNode parent: node.getParents()){
			ret.addAll(parent.getConstOut());
		}
		return ret;
	}

	private void setupdefndefc(CFGNode node) {
		SyntaxIR n = node.getIrnode();
		if (n instanceof MoveIR){
			if (n.getChildren().get(0) instanceof TempIR){
				if (n.getChildren().get(1) instanceof ConstIR){
					node.getDefc().add(new Const((TempIR) n.getChildren().get(0),(ConstIR) n.getChildren().get(1)));
				}
				else{
					node.getDefn().add((TempIR) n.getChildren().get(0));
				}
			}
		}
	}


//=======================================================================================
//  Common Subexpression Elimination
//=======================================================================================
	
	private static void removeAllCJump(LinkedList<CFGNode> queue2) {
		for(int i = 0; i < queue2.size(); i ++) {
			if(queue2.get(i).getIrnode() instanceof CJumpIR) {
				queue2.remove(i);
				i++;
			}
		}
	}
	
	public void commonSubexpressionElimination(ArrayList<CFGNode> roots) {
		for (HashSet<CFGNode> hh : allNodes){
			queue = new LinkedList<CFGNode>();	
			queue.addAll(hh);
			removeAllCJump(queue);
			while (!queue.isEmpty()){
				CFGNode node = queue.pop();
				HashSet<OpIR> outs = setCSEOut(node);
				node.setCSEOut(outs);
				HashSet<OpIR> ins = setCSEIn(node);
				if (ins.size() != node.getCSEIn().size()){
					for (CFGNode n: node.getParents()){
						if (!queue.contains(n)){
							queue.push(n);
						}
					}
					if(!queue.contains(node)) {
						queue.push(node);
					}
				}
				node.setCSEIn(ins);
			}
		}
		for(CFGNode root : roots) {
			HashMap<String, TempIR> csemap = new HashMap<String, TempIR>();
			HashSet<CFGNode> doneList = new HashSet<CFGNode>();
			hoistCSE(root, csemap, doneList);
			doneList = new HashSet<CFGNode>();
			replaceCSE(root, csemap, doneList);
		}
	}
	
	private HashSet<OpIR> setCSEOut(CFGNode node) {
		HashSet<OpIR> out = new HashSet<OpIR>();
		out.addAll(node.getCSEIn());
		HashSet<OpIR> subexps = findSubExpressions(node.getIrnode());
		if(out.size() == 0) {
			out.addAll(subexps);
		} else {
			for(OpIR ops : subexps) {
				boolean exit = false;
				for(int i = 0; i < out.size() && !exit; i ++) {
					OpIR outOps = (OpIR) out.toArray()[i];
					if(ops.prettyPrint().equals(outOps.prettyPrint())) {
						exit = true;
					}
					else if(!ops.prettyPrint().equals(outOps.prettyPrint()) && !exit) {
						out.add(ops);
						i++;
					}
				}
			}
		}
		//out.addAll(subexps);
		HashSet<OpIR> killList = new HashSet<OpIR>();
		//find all expressions containing x and remove them from out
		for(OpIR op : out) {
			if((node.getIrnode() instanceof MoveIR) && (node.getIrnode().getChildren().get(0) instanceof TempIR)) {
				//System.out.println("checking for temp:"+((SyntaxIR) node.getIrnode().getChildren().get(0)).prettyPrint());
				//System.out.println("in ir node:"+((SyntaxIR) node.getIrnode().getChildren().get(1)).prettyPrint());
				killList.addAll(findSubExpressionsContaining(op, (TempIR) node.getIrnode().getChildren().get(0)));
			}
		}
		//System.out.println("killlist:"+killList);
		//System.out.println("out before:"+out);
		out.removeAll(killList);
		//System.out.println("out after:"+out);
		return out;
	}
	
	private HashSet<OpIR> setCSEIn(CFGNode node) {
		HashSet<OpIR> parent1 = new HashSet<OpIR>();
		HashSet<OpIR> parent2 = new HashSet<OpIR>();
		HashSet<OpIR> ret = new HashSet<OpIR>();
		if(node.getParents().size() > 0 && node.getParents().get(0) != null) {
			parent1 = node.getParents().get(0).getCSEOut();
		}
		if(node.getParents().size() > 1 && node.getParents().get(1) != null) {
			parent2 = node.getParents().get(1).getCSEOut();
		}
		for(OpIR p1Op: parent1) {
			if(parent2.size() > 0) {
				for(OpIR p2Op: parent2) {
					//System.out.println("p1op:"+p1Op.prettyPrint());
					//System.out.println("p2op:"+p2Op.prettyPrint());
					if(p1Op.equals(p2Op)) {
						ret.add(p1Op);
					}
				}
			} else {
				ret.addAll(parent1);
			}
		}
		return ret;
	}
	
	private static HashSet<OpIR> findSubExpressions(SyntaxIR node) {
		HashSet<OpIR> ret = new HashSet<OpIR>();
		if(node instanceof OpIR) {
			ret.add((OpIR) node);
		}
		for(VisualizableIRNode child : node.getChildren()) {
			ret.addAll(findSubExpressions((SyntaxIR) child));
		}
		return ret;
	}
	
	private static HashSet<OpIR> findSubExpressionsContaining(SyntaxIR op, TempIR remove) {
		HashSet<OpIR> removal = new HashSet<OpIR>();
		if(op instanceof OpIR && ((ExpressionIR) op).contains(remove))
			removal.add((OpIR) op);
		for(VisualizableIRNode child : op.getChildren()) {
			removal.addAll(findSubExpressionsContaining((SyntaxIR) child, remove));
		}
		return removal;
	}
	
	private static void hoistCSE(CFGNode root, HashMap<String, TempIR> csemap, HashSet<CFGNode> doneList) {
		if(doneList.contains(root))
			return;
		doneList.add(root);
		if(root.getCSEOut().size() > 0) {
			for(OpIR op : root.getCSEOut()) {
				//System.out.println("hoisting:"+op);
				if(!csemap.containsKey(op.prettyPrint())) {
					TempIR newTemp = new TempIR("_cseHoist"+SyntaxIR.getFreshLabel());
					MoveIR newMove = new MoveIR(newTemp, op);
					root.addCSEIn(op);
					csemap.put(op.prettyPrint(), newTemp);
					CFGNode newCFG = new CFGNode();
					newCFG.addCSEOut(op);
					newCFG.setIrnode(newMove);
					//assumption: root has 1 parent because it wont be the head node and the only thing that have 2 parents are labels
					newCFG.addParent(root.getParents().get(0));
					root.getParents().get(0).setChild1(newCFG);
					newCFG.setChild1(root);
					ArrayList<CFGNode> np = new ArrayList<CFGNode>();
					np.add(newCFG);
					root.setParents(np);
				} 
			}
		}
		if(root.getChild1() != null) 
			hoistCSE(root.getChild1(), csemap, doneList);
		if(root.getChild2() != null) 
			hoistCSE(root.getChild2(), csemap, doneList);
	}
	
	private static void replaceCSE(CFGNode root, HashMap<String, TempIR> csemap, HashSet<CFGNode> doneList) {
		if(doneList.contains(root))
			return;
		doneList.add(root);
		TreeSet<OpIR> sorted = new TreeSet<OpIR>();
		for(int i = 0; i < root.getCSEIn().size(); i ++) {
			sorted.add((OpIR) root.getCSEIn().toArray()[i]);
		}
		Iterator<OpIR> iter = sorted.iterator();
		while(iter.hasNext()){
			OpIR op = iter.next();
			OpIR tempOP = null;
			if(op.getChildren().size() > 1) {
				tempOP = new OpIR(op.getOp(), (ExpressionIR) op.getChildren().get(0), ((SyntaxIR)op.getChildren().get(1))); }
			else {
				tempOP = new OpIR(op.getOp(), (ExpressionIR) op.getChildren().get(0));}
			TempIR temps = new TempIR();
			temps.setId(csemap.get(op.prettyPrint()).getId());
			cseReplace(root.getIrnode(), tempOP, temps);
		}
		if(root.getChild1() != null)
			replaceCSE(root.getChild1(), csemap, doneList);
		if(root.getChild2() != null)
			replaceCSE(root.getChild2(), csemap, doneList);
	}
	
	private static void cseReplace(SyntaxIR root, OpIR op, TempIR temp) {
		if(root == null)
			return;
		for(int i = 0; i < root.getChildren().size(); i ++) {
			//System.out.println("root:"+root);
			SyntaxIR child = (SyntaxIR) root.getChildren().get(i);
			//System.out.println("old child:"+child);
			
			//System.out.println("new child:"+child);
			if(((SyntaxIR) child).prettyPrint().equals(op.prettyPrint())) {
				root.getChildren().set(i, temp);
			}
			cseReplace(child, op, temp);
			
		}
		//System.out.println("new root:"+root);
	}

//=======================================================================================
//  Dead Code Elimination
//=======================================================================================	
	public void deadCodeElimination(){
		for (HashSet<CFGNode> hh : allNodes){
			queue = new LinkedList<CFGNode>();	
			queue.addAll(hh);
			while(!queue.isEmpty()){
				CFGNode node = queue.pop();
				eliminate(node);
			}
		}
	}

	private void eliminate(CFGNode node) {
		
		for (TempIR temp: node.getDef()){
			if (!(node.getChild1() != null && node.getChild1().getIn().contains(temp) || 
					node.getChild2() != null && node.getChild2().getIn().contains(temp) )){
				//remove this dead node
				if (node.getChild2() == null){
					for (CFGNode parent: node.getParents()){
						if (parent.getChild1() == node){
							parent.setChild1(node.getChild1());
							node.getChild1().addParent(parent);
							node.getChild1().removeParent(node);
						}
						else if (parent.getChild2() == node){
							parent.setChild2(node.getChild2());
							node.getChild2().addParent(parent);
							node.getChild2().removeParent(node);
						}
						queue.push(parent);
					}
				}
			}
		}	
	}
	
	public ArrayList<HashSet<CFGNode>> getAllNodes(){
		return allNodes;
	}

	
}
