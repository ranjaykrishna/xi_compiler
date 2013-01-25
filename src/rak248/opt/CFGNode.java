package rak248.opt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import rak248.xi.SyntaxIR;
import rak248.xi.ir.JumpIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.TempIR;

public class CFGNode {
	private CFGNode child1;
	private CFGNode child2;
	private ArrayList<CFGNode> parents;
	
	//the list of all nodes that dominate this node;
	private HashSet<CFGNode> domList;
	
	//the list of all nodes on the dominance frontier of this node
	private HashSet<CFGNode> domFront;
	private SyntaxIR irnode;
	private CFGNode dominatedBy;
	private TreeSet<TempIR> in;
	private TreeSet<TempIR> out;
	private TreeSet<TempIR> def;
	private TreeSet<TempIR> use;
	
	private HashSet<Copy> copyOut;
	private HashSet<Copy> copyIn;
	private HashSet<Copy> gen;
	private TreeSet<TempIR> kill;
	
	private HashSet<OpIR> inCSE;
	private HashSet<OpIR> outCSE;
	
	private HashSet<Const> constIn;
	private HashSet<Const> constOut;
	private TreeSet<TempIR> defn;
	private HashSet<Const> defc;
	
	private static HashMap<String, String> specialTemps;
	private ArrayList<Phi> phi;
	
	public CFGNode() {
		child1 = null;
		child2 = null;
		irnode = null;
		parents = new ArrayList<CFGNode>();
		domFront = new HashSet<CFGNode>();
		domList = new HashSet<CFGNode>();
		in  = new TreeSet<TempIR>();
		out = new TreeSet<TempIR>();
		use = new TreeSet<TempIR>();
		def = new TreeSet<TempIR>();
		gen = new HashSet<Copy>();
		kill = new TreeSet<TempIR>();
		copyIn = new HashSet<Copy>();
		copyOut = new HashSet<Copy>();
		inCSE = new HashSet<OpIR>();
		outCSE = new HashSet<OpIR>();
		defn = new TreeSet<TempIR>();
		defc = new HashSet<Const>();
		constIn = new HashSet<Const>();
		constOut = new HashSet<Const>();
		specialTemps = new HashMap<String, String>();
		phi = new ArrayList<Phi>();
	}

	public void setChild1(CFGNode child1) {
		this.child1 = child1;
	}

	public CFGNode getChild1() {
		return child1;
	}

	public void setChild2(CFGNode child2) {
		this.child2 = child2;
	}

	public CFGNode getChild2() {
		return child2;
	}

	public void setIrnode(SyntaxIR irnode) {
		this.irnode = irnode;
	}

	public SyntaxIR getIrnode() {
		return irnode;
	}
	
	public void addParent(CFGNode p) {
		if(p == null) {
			parents.add(p);
			return;
		}
		for(CFGNode par : parents) {
			if(par.equals(p)) {
				return;
			}
		}
		parents.add(p);
	}
	
	public ArrayList<CFGNode> getParents() {
		return parents;
	}
	
	public void setParents(ArrayList<CFGNode> p) {
		parents = p;
	}
	
	public String toString(HashSet<CFGNode> processed) {
		if(processed.contains(this))
			return "";
		processed.add(this);
		String ret = "";
		ret += irnode.toString();
		for(int i = 0; i < parents.size(); i++) {
			if(parents.get(i) != null) {
				ret += "\n\t\tparent"+i+":"+parents.get(i).getIrnode();
			}
		}
		if(child1 != null) {
			ret += "\n\tChild1:"+child1.toString(processed);
		}
		if(child2 != null) {
			ret += "\n\tChild2:"+child2.toString(processed);
		}
		return ret;
	}

	public String print(HashSet<CFGNode> done) {
		if(done.contains(this)){
			return "";
		}
		String p = "";
		if(phi != null) {
			p = phi.toString() + "\\n";
		}
		String u = prettyPrint(use);
		String d = prettyPrint(def);
		String i = prettyPrint(in);
		String o = prettyPrint(out);
		String g = Copy.prettyPrint(gen);
		String ic = Copy.prettyPrint(copyIn);
		String oc = Copy.prettyPrint(copyOut);
		String k = prettyPrint(kill);
		String ci = prettyPrintOps(inCSE);
		String co = prettyPrintOps(outCSE);
		String dc = Const.prettyPrint(defc);
		String dn = prettyPrint(defn);
		String ret="a"+this.hashCode() + " [label=\""+p+this.getIrnode().prettyPrint()+"\\n"+
															"USE:"+u+"\\n"+
															"DEF:"+d+"\\n"+
															"GEN:"+g+"\\n"+
															"KILL:"+k+"\\n"+
															"C-IN:"+ic+"\\n"+
															"C-OUT:"+oc+"\\n"+
															"IN:"+i+"\\n"+
															"OUT:"+o+"\\n"+
															"CSEIN:"+ci+"\\n"+
															"CSEOUT:"+co+"\\n"+
															"DEF-N:"+dn+"\\n"+
															"DEF-C:"+dc+"\\n"+
                                                            dominatedBy+"\" shape=box];\n";;
		if(dominatedBy!=null){
			ret = "a"+this.hashCode() + " [label=\""+p+this.getIrnode().prettyPrint()+"\\n"+
														"USE:"+u+"\\n"+
														"DEF:"+d+"\\n"+
														"GEN:"+g+"\\n"+
														"KILL:"+k+"\\n"+
														"C-IN:"+ic+"\\n"+
														"C-OUT:"+oc+"\\n"+
														"IN:"+i+"\\n"+
														"OUT:"+o+"\\n"+
														"CSEIN:"+ci+"\\n"+
														"CSEOUT:"+co+"\\n"+
														"DEF-N:"+dn+"\\n"+
														"DEF-C:"+dc+"\\n"+
		                                               dominatedBy.getIrnode().prettyPrint()+"\" shape=box];\n";
		}
		done.add(this);
		if(this.getChild1()!=null){
			ret += "a"+this.hashCode() + "->" + "a" + this.getChild1().hashCode()+"\n";
			ret += this.getChild1().print(done);
		}
		if(this.getChild2()!=null){
			ret += "a"+this.hashCode() + "->" + "a" + this.getChild2().hashCode()+"\n";
			ret += this.getChild2().print(done);
	    }
		return ret;
	}

	private String prettyPrint(TreeSet<TempIR> tt) {
		String ret = "[";
		for (TempIR temp: tt){
			ret += temp.prettyPrint() + ", ";
		}
		if (tt.size() > 0){
			ret = ret.substring(0,ret.length()-2);
		}
		ret += "]";
		return ret;
	}
	
	private String prettyPrintOps(HashSet<OpIR> ops) {
		String ret = "[";
		for (OpIR temp: ops){
			ret += temp.prettyPrint() + ", ";
		}
		if (ops.size() > 0){
			ret = ret.substring(0,ret.length()-2);
		}
		ret += "]";
		return ret;
	}

	public void dominate(HashSet<CFGNode> donelist) {
		if(donelist.contains(this)) {
			return;
		}
		donelist.add(this);
		//System.out.println("dominating:"+this);
		if(parents == null || parents.size() == 0) {
			dominatedBy = null;
			dominateChildren(donelist);
			return;
		} else if(parents.size() == 1) {
			if(parents.get(0).dominatedBy == null) {
				//for the special case of the start node.
				dominatedBy = parents.get(0);
			}
			else
				dominatedBy = parents.get(0);
			dominateChildren(donelist);
			return;
		} else if(parents.size() == 2 && parents.get(0).dominatedBy == null) {
			dominatedBy = parents.get(0);
			dominateChildren(donelist);
			return;
		} else {
			//assuming we have 2 parents
			CFGNode p1dom = parents.get(0).dominatedBy;
			CFGNode p2dom = parents.get(1).dominatedBy;
			
			if(p1dom == null) {
				if(!(donelist.contains(parents.get(0)) && donelist.contains(parents.get(1)))) {
					donelist.remove(this);
					return;
				}
				dominatedBy = parents.get(0);
				dominateChildren(donelist);
				return;
				
			} else if(p2dom == null) {
				
				dominatedBy = parents.get(0);
				dominateChildren(donelist);
				return;
			}
			else {
				//parents are dominated by different nodes
				//use recursion
				if(!(donelist.contains(parents.get(0)) && donelist.contains(parents.get(1)))) {
					donelist.remove(this);
					return;
				}
				HashSet<CFGNode> epic = new HashSet<CFGNode>();
				dominatedBy = recurseDom(parents.get(0), parents.get(1), epic);
				dominateChildren(donelist);
				return;
			}
		}
	}
	
	public static CFGNode recurseDom(CFGNode parent1, CFGNode parent2, HashSet<CFGNode> epic) {
		CFGNode p1dom = parent1.dominatedBy;
		CFGNode p2dom = parent2.dominatedBy;
		if(epic.contains(p1dom))
			return p1dom;
		if(epic.contains(p2dom))
			return p2dom;
		if(p1dom == null) {
			return recurseDom(parent1, parent2.dominatedBy, epic);
		} else if(p2dom == null) {
			return recurseDom(parent1.dominatedBy, parent2, epic);
		}
		epic.add(p1dom);
		epic.add(p2dom);
		if(p1dom.equals(p2dom)) {
			return p1dom;
		} else if(p1dom.equals(parent2)) {
			return p1dom;
		} else if(p2dom.equals(parent1)) {
			return p2dom;
		} else {
			
			return recurseDom(parent1.dominatedBy, parent2.dominatedBy, epic);
		}
	}
	
	public void dominateChildren(HashSet<CFGNode> donelist) {
		if(child1 != null)
			child1.dominate(donelist);
		if(child2 != null)
			child2.dominate(donelist);
	}

	public void computeDomFront(HashSet<CFGNode> doneList) {
		if(doneList.contains(this))
			return;
		doneList.add(this);
		if(parents.size() > 1) {
			HashSet<CFGNode> p1list = parents.get(0).domList;
			HashSet<CFGNode> p2list = parents.get(1).domList;
			//System.out.println("irnode:"+irnode);
			//System.out.println("p1 list:"+p1list);
			//System.out.println("p2 list:"+p2list);
			//System.out.println("domList:"+domList);
			for(CFGNode p1dom : p1list) {
				//if(!domList.contains(p1dom)) {
					domFront.add(p1dom);
				//}
			}
			for(CFGNode p2dom : p2list) {
				//if(!domList.contains(p2dom)) {
					domFront.add(p2dom);
				//}
			}
		}
		//System.out.println(irnode + " domfront:" + domFront);
		if(child1 != null)
			child1.computeDomFront(doneList);
		if(child2 != null)
			child2.computeDomFront(doneList);
	}
	
	public void fixMultipleDefs(HashSet<CFGNode> done){
		if(done.contains(this))
			return;		
		//Try renaming all variables
		if(def.size()>0){
			MoveIR m = (MoveIR)this.getIrnode();
			String id = "DEADBEEF";
			if(def.first().getId().startsWith("_arg") || def.first().getId().startsWith("_ret")) {
				if(specialTemps.containsKey(def.first().getId())) {
					id = ((TempIR) dominatedBy.goUpDomList(def.first()).getIrnode().getChildren().get(0)).getId();
				} else {
					id = def.first().getId() + "@" + SyntaxIR.getFreshLabel();
					specialTemps.put(def.first().getId(), "_"+id);
				}
			} else {
				id = def.first().getId() + "@" + SyntaxIR.getFreshLabel();
			}
			TempIR t;
			t = new TempIR("_" + id);
			m.getChildren().set(0, t);
			TreeSet<TempIR> newDef = new TreeSet<TempIR>();
			newDef.add(t);
			this.setDef(newDef);
			//def.first().setId("_"+def.first().getId()+"@"+SyntaxIR.getFreshLabel());
		}
		done.add(this);
		//}
		//Make sure to recurse
		if(child1 != null)
			child1.fixMultipleDefs(done);
		if(child2 != null)
			child2.fixMultipleDefs(done);
	}

	public TreeSet<TempIR> getOut() {
		return out;
	}

	public void setOut(TreeSet<TempIR> out) {
		this.out = out;
	}

	public TreeSet<TempIR> getIn() {
		return in;
	}

	public void setIn(TreeSet<TempIR> in) {
		this.in = in;
	}

	public TreeSet<TempIR> getDef() {
		return def;
	}

	public void setDef(TreeSet<TempIR> def) {
		this.def = def;
	}

	public TreeSet<TempIR> getUse() {
		return use;
	}

	public void setUse(TreeSet<TempIR> use) {
		this.use = use;
	}
	
	public void printDomFront(HashSet<CFGNode> doneList) {
		if(doneList.contains(this))
			return;
		doneList.add(this);
		for(CFGNode dom : domFront) {
			//System.out.println("\tDominance frontier:"+dom.getIrnode().prettyPrint());
		}
		if(child1 != null) 
			child1.printDomFront(doneList);
		if(child2 != null)
			child2.printDomFront(doneList);
	}
	
	public void calculateDomList(HashSet<CFGNode> doneList) {
		if(doneList.contains(this))
			return;
		doneList.add(this);
		domList.add(dominatedBy);
		if(dominatedBy != null)
			domList.addAll(dominatedBy.getDomList());
		domList.remove(null);
		//System.out.println(irnode + " domlist: " + domList);
		if(child1 != null)
			child1.calculateDomList(doneList);
		if(child2 != null)
			child2.calculateDomList(doneList);
	}
	
	public HashSet<CFGNode> getDomList() {
		return domList;
	}
	
	public void printDomList() {
		for(int i = 0; i < domList.size(); i++) {
			CFGNode dom = (CFGNode) domList.toArray()[i];
			if(dom == null) {
				domList.remove(dom);
				i--;
			}
		}
		for(CFGNode dom : domList) {
			//System.out.println("\t" + dom.getIrnode().prettyPrint());
		}
		if(child1 != null)
			child1.printDomList();
		if(child2 != null)
			child2.printDomList();
	}
	
	/*
	 * This method is used to update uses based on the dominance frontier
	 * to convert a CFG to SSA
	 */
	public void fixUses(HashSet<CFGNode> doneList) {
		if(doneList.contains(this))
			return;
		doneList.add(this);
		if(use.size() > 0) {
			if(domFront.size() == 0) {
				TreeSet<TempIR> newUse = new TreeSet<TempIR>();
				for(TempIR useIR : use) {
					if(!useIR.getId().contains("@")) {
						TempIR oldUseIR = useIR;
						CFGNode mostRecDef;
						if(useIR.getId().startsWith("_arg") || useIR.getId().startsWith("_ret")) {
							mostRecDef = this;
							if(specialTemps.get(useIR.getId()) != null) {
								useIR.setId(specialTemps.get(useIR.getId()));
							}
							else {
								String id = "_"+useIR.getId() + "@" + SyntaxIR.getFreshLabel();
								specialTemps.put(useIR.getId(), id);
								useIR.setId(id);
							}
						} else {
							mostRecDef = dominatedBy.goUpDomList(useIR);
							useIR = mostRecDef.def.first();
						}
						newUse.add(useIR);
						TempIR newUseIR = useIR;
						//System.out.println("irnode before:"+irnode);
						TempIR tempOldUse = new TempIR();
						tempOldUse.setId(oldUseIR.getId());
						irnode.updateTemp(tempOldUse, newUseIR);
						//System.out.println("irnode after:"+irnode);
					}
				}
				setUse(newUse);
			}
		}
		if(child1 != null) 
			child1.fixUses(doneList);
		if(child2 != null)
			child2.fixUses(doneList);
		//if parents size is more than 1, we're going to insert a phi.
		//this is the inefficient way to do it, but fuck.  I want my code to work.
		if(parents.size() > 1) {
			HashSet<CFGNode> doneListPhi = new HashSet<CFGNode>();
			//This is a map from the real name to the mangled name.
			HashMap<String, String> tempMap = new HashMap<String, String>();
			HashMap<String, String> defTemps1 =  parents.get(0).goUpGetDef(doneListPhi, tempMap);
			doneListPhi = new HashSet<CFGNode>();
			tempMap = new HashMap<String, String>();
			HashMap<String, String> defTemps2 =  parents.get(1).goUpGetDef(doneListPhi, tempMap);
			//MatchingDefs is the set of unmangled variable names that are defined in both parent branches.
			//Also, you only add something if their mangled names are different but their unmangled names are the same.
			HashSet<String> matchingDefs = new HashSet<String>();
			for(String dT1 : defTemps1.keySet()) {
				for(String dT2 : defTemps2.keySet()) {
					if(dT1.equals(dT2) && !defTemps1.get(dT1).equals(defTemps2.get(dT2))) {
						matchingDefs.add(dT1);
					}
				}
			}
			//These next maps are a map from the unmangled name (e.g. x0) to the most recent mangled definition (e.g. _x0@49)
			//There's 1 map for each parent.  The two maps should have the same amount of elements because its only for temps
			//that are defined in both parents.
			for(String normName : matchingDefs) {
				TempIR left = new TempIR(defTemps1.get(normName));
				TempIR right = new TempIR(defTemps2.get(normName));
				Phi newPhi = new Phi(left, right);
				phi.add(newPhi);
				TempIR temp = newPhi.getOut();
				MoveIR moveL = new MoveIR(temp, newPhi.getIn1());
				MoveIR moveR = new MoveIR(temp, newPhi.getIn2());
				CFGNode CFGL = new CFGNode();
				CFGL.setIrnode(moveL);
				CFGNode CFGR = new CFGNode();
				CFGR.setIrnode(moveR);
				if(parents.get(0).getIrnode() instanceof JumpIR) {
					CFGNode jumpPar = parents.get(0).parents.get(0);
					CFGL.dominatedBy = jumpPar;
					CFGL.addParent(jumpPar);
					CFGL.setChild1(parents.get(0));
					jumpPar.setChild1(CFGL);
					ArrayList<CFGNode> p = new ArrayList<CFGNode>();
					p.add(CFGL);
					parents.get(0).setParents(p);
					parents.get(0).dominatedBy = CFGL;
				} else {
					CFGL.dominatedBy = parents.get(0);
					CFGL.addParent(parents.get(0));
					parents.get(0).setChild1(CFGL);
					CFGL.setChild1(this);
					ArrayList<CFGNode> p = new ArrayList<CFGNode>();
					p.add(CFGL);
					p.add(parents.get(1));
					this.setParents(p);
				}
				if(parents.get(1).getIrnode() instanceof JumpIR) {
					CFGNode jumpPar = parents.get(1).parents.get(0);
					CFGR.dominatedBy = jumpPar;
					CFGR.addParent(jumpPar);
					CFGR.setChild1(parents.get(1));
					jumpPar.setChild1(CFGR);
					ArrayList<CFGNode> p = new ArrayList<CFGNode>();
					p.add(CFGR);
					parents.get(1).setParents(p);
					parents.get(0).dominatedBy = CFGR;
				} else {
					CFGR.dominatedBy = parents.get(1);
					CFGR.addParent(parents.get(1));
					CFGR.setChild1(this);
					parents.get(1).setChild1(CFGR);
					ArrayList<CFGNode> p = new ArrayList<CFGNode>();
					p.add(parents.get(0));
					p.add(CFGR);
					this.setParents(p);
				}
				if(child1 != null)
					child1.fixUsePhi(newPhi, new HashSet<CFGNode>());
				if(child2 != null)
					child2.fixUsePhi(newPhi, new HashSet<CFGNode>());
			}
		}
		/*if(domFront.size() > 0) {
			/*
			 * map from shortened temp names to the actual temps.
			 * Let's say we have a which goes to x0
			 * x0 gets renamed to things like _x0@6 and _x0@9
			 * This map is a map from "x0" to the temps.
			 
			HashMap<String, TempIR> defList = new HashMap<String, TempIR>();
			System.out.println("checking ir:"+this.getIrnode());
			for(CFGNode dom : domFront) {
				System.out.println("domfront:"+dom);
				for(TempIR defIR : dom.def) {
					String name = defIR.getId();
					name = defIR.getId().substring(defIR.getId().indexOf("_"), defIR.getId().indexOf("@"));
					//if we're going to add something to the map again, that means we need a phi
					System.out.println("name:"+name);
					System.out.println("defList:"+defList);
					if(!name.startsWith("_arg") && defList.keySet().contains(name)){
						//System.out.println("var 2:"+defList.get(name));
						//System.out.println("var 1:"+defIR);
						phi = new Phi(defIR, defList.get(name));
						//use.add(phi.getIn1());
						//use.add(phi.getIn2());
						//def.add(phi.getOut());
						// parent 0
						CFGNode papa = new CFGNode();
						//parent 1
						CFGNode mama = new CFGNode();
						papa.addParent(parents.get(0));
						papa.dominatedBy = parents.get(0);
						parents.get(0).setChild1(papa);
						MoveIR papaMove = new MoveIR(phi.getOut(), phi.getIn1());
						papa.setIrnode(papaMove);
						papa.setChild1(this);
						this.dominatedBy = papa;
						this.addParent(papa);
						//papa.domList.addAll(parents.get(0).domList);
						//this.domList.addAll(papa.domList);
						//System.out.println(irnode +" now dominated by papa");
						//papa.dominate(new HashSet<CFGNode>());
						MoveIR mamaMove = new MoveIR(phi.getOut(), phi.getIn2());
						mama.setIrnode(mamaMove);
						//System.out.println("parents.get1:"+parents.get(1).getIrnode());
						if(parents.get(1).getIrnode() instanceof JumpIR) {
							CFGNode jump = parents.get(1);
							CFGNode jumpParent = parents.get(1).getParents().get(0);
							jumpParent.setChild1(mama);
							mama.addParent(jumpParent);
							mama.dominatedBy = jumpParent;
							//mama.domList.addAll(jumpParent.domList);
							mama.setChild1(jump);
							jump.setChild1(this);
							ArrayList<CFGNode> newP = new ArrayList<CFGNode>();
							newP.add(mama);
							jump.setParents(newP);
							jump.dominatedBy = mama;
							this.addParent(jump);
							//jump.domList.addAll(mama.domList);
							mama.dominate(new HashSet<CFGNode>());
							//this.domList.addAll(jump.domList);
						} else {
							mama.dominatedBy = parents.get(1);
							//mama.domList.addAll(parents.get(1).domList);
							mama.addParent(parents.get(1));
							parents.get(1).setChild1(mama);
							this.dominatedBy = mama;
							mama.setChild1(this);
							this.addParent(mama);
							//this.domList.addAll(mama.domList);
							//mama.dominate(new HashSet<CFGNode>());
						}
						HashSet<CFGNode> fixPhi = new HashSet<CFGNode>();
						if(child1 != null)
							child1.fixUsePhi(phi, fixPhi);
						if(child2 != null)
							child2.fixUsePhi(phi, fixPhi);
					}
					else {
						if(!name.startsWith("_arg"))
							defList.put(name, defIR);
					}
				}
			}
			//System.out.println("phi:"+phi);
			if(phi != null) {
				for(CFGNode dom : domFront) {
					if(!dom.equals(this)) {
					TreeSet<TempIR> newDom = new TreeSet<TempIR>();
					for(TempIR use : dom.use) {
						if(use.equals(phi.getIn1())) {
							use = new TempIR((phi.getOut().getId()));
							newDom.add(use);
						} else if(use.equals(phi.getIn2())) {
							use = new TempIR((phi.getOut().getId()));
							newDom.add(use);
						}
						else
						{
							newDom.add(use);
						}
					}
					dom.setUse(newDom);
					dom.getIrnode().updateTemp(phi.getIn1(), phi.getOut());
					dom.getIrnode().updateTemp(phi.getIn2(), phi.getOut());
					}
				}
			}
		}*/
	}
	
	/*
	 * This method recursively crawls backwards up the tree finding the most recent definition for all of the variables.
	 */
	private HashMap<String, String> goUpGetDef(HashSet<CFGNode> doneListPhi,
			HashMap<String, String> tempMap) {
		if(doneListPhi.contains(this))
			return tempMap;
		doneListPhi.add(this);
		//System.out.println("def:"+def);
		if(def.size() == 0) {
			//do noothing
		} else {
			String mangled = def.first().getId();
			String unMangled = mangled.substring(mangled.indexOf("_")+1, mangled.indexOf("@"));
			if(!tempMap.containsKey(unMangled)) {
				tempMap.put(unMangled,mangled);
			}
		}
		//HashMap<String, String> p1Map = new HashMap<String, String>();
		//HashMap<String, String> p2Map = new HashMap<String, String>();
		if(parents.size() == 0)
			return tempMap;
		if(parents.get(0) != null)
			tempMap = parents.get(0).goUpGetDef(doneListPhi, tempMap);
		if(parents.size() == 1)
			return tempMap;
		if(parents.get(1) != null)
			tempMap = parents.get(1).goUpGetDef(doneListPhi, tempMap);
		return tempMap;
	}

	private void fixUsePhi(Phi phi, HashSet<CFGNode> doneList) {
		if(doneList.contains(this))
			return;
		doneList.add(this);
		if(getUse() != null && getUse().contains(phi.getIn1())) {
			irnode.updateTemp(phi.getIn1(), phi.getOut());
		}
		if(getUse() != null && getUse().contains(phi.getIn2())) {
			irnode.updateTemp(phi.getIn2(), phi.getOut());
		}
		if(child1 != null)
			child1.fixUsePhi(phi, doneList);
		if(child2 != null)
			child2.fixUsePhi(phi, doneList);
	}

	public CFGNode goUpDomList(TempIR useIR) {
		if(def.size() == 0) 
			return dominatedBy.goUpDomList(useIR);
		String id = def.first().getId();
		id = id.substring(id.indexOf("_")+1, id.indexOf("@"));
		if(id.equals(useIR.getId())) {
			return this;
		} else
			return dominatedBy.goUpDomList(useIR);
	}

	public void removeParent(CFGNode node) {
		parents.remove(node);
	}

	public HashSet<OpIR> getCSEIn() {
		return inCSE;
	}
	
	public HashSet<OpIR> getCSEOut() {
		return outCSE;
	}
	
	public void addCSEIn(OpIR op) {
		inCSE.add(op);
	}
	
	public void addCSEOut(OpIR op) {
		outCSE.add(op);
	}
	
	public void setCSEOut(HashSet<OpIR> outs) {
		outCSE = outs;
	}
	
	public void setCSEIn(HashSet<OpIR> ins) {
		inCSE = ins;
	}
	
	public String toString() {
		String ret = irnode.prettyPrint();
		/*if(child1 != null) 
			ret += "\n\t" + child1.getIrnode().prettyPrint();
		if(child2 != null) 
			ret += "\n\t" + child2.getIrnode().prettyPrint();
		for(CFGNode p : parents) {
			ret += "\n\t\t parent: " + p.getIrnode().prettyPrint();
		}*/
		return ret;
	}

	public void resetSpecialTemps() {
		specialTemps = new HashMap<String,String>();
		
	}

	public void setGen(HashSet<Copy> gen) {
		this.gen = gen;
	}

	public HashSet<Copy> getGen() {
		return gen;
	}

	public void setKill(TreeSet<TempIR> kill) {
		this.kill = kill;
	}

	public TreeSet<TempIR> getKill() {
		return kill;
	}

	public void setCopyOut(HashSet<Copy> copyOut) {
		this.copyOut = copyOut;
	}

	public HashSet<Copy> getCopyOut() {
		return copyOut;
	}

	public void setCopyIn(HashSet<Copy> copyIn) {
		this.copyIn = copyIn;
	}

	public HashSet<Copy> getCopyIn() {
		return copyIn;
	}

	public HashSet<Const> getConstIn() {
		return constIn;
	}

	public void setConstIn(HashSet<Const> constIn) {
		this.constIn = constIn;
	}

	public HashSet<Const> getConstOut() {
		return constOut;
	}

	public void setConstOut(HashSet<Const> constOut) {
		this.constOut = constOut;
	}

	public TreeSet<TempIR> getDefn() {
		return defn;
	}

	public void setDefn(TreeSet<TempIR> defn) {
		this.defn = defn;
	}

	public HashSet<Const> getDefc() {
		return defc;
	}

	public void setDefc(HashSet<Const> defc) {
		this.defc = defc;
	}
}
