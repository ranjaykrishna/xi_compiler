package rak248.xi.typeChecker;

import java.util.ArrayList;
import java.util.HashMap;

import rak248.xi.ir.Mangler;
import rak248.xi.parser.ClassNode;
import rak248.xi.parser.ExtendsNode;
import rak248.xi.parser.VarNode;
import rak248.xi.parser.Wrapper;
import rak248.xi.typeChecker.Type.typeEnum;


public class SymbolTable {
	private SymbolTable parent;
	private HashMap<VarNode, Type> table;
	private String currentFunction;
	private String currentClass;
	
	private static int uid = 0;
	private static int lid = 0;
	private HashMap<String,String> associations;
	private ArrayList<String> listLocs;
	
	private static HashMap<String, Integer> funcRetSizes = new HashMap<String, Integer>();
	private static HashMap<String,HashMap<String,Type>> methods = new HashMap<String,HashMap<String,Type>>();
	private static HashMap<String, String> inheritanceTree = new HashMap<String, String>();
	public static ArrayList<ClassNode> classList = new ArrayList<ClassNode>();
	private static HashMap<String, ClassNode> classNameToNode = new HashMap<String, ClassNode>();
	public static HashMap<String, ArrayList<Wrapper>> methodsWrapper = new HashMap<String, ArrayList<Wrapper>>();
	public static HashMap<String, ArrayList<String>> methodsNonHashed = new HashMap<String, ArrayList<String>>();
	public static HashMap<String, String> globals = new HashMap<String, String>();
	
	public SymbolTable(){
		table = new HashMap<VarNode,Type>();
		parent = null;
		associations = new HashMap<String,String>();
		listLocs = new ArrayList<String>();
		classNameToNode = new HashMap<String, ClassNode>();
	}
	
	/** creates a new SymbolTable with p set as the parent */
	public SymbolTable(SymbolTable p){
		table = new HashMap<VarNode,Type>();
		associations = new HashMap<String,String>();
		listLocs = new ArrayList<String>();
		parent = p;
		setCurrentFunction(p.getCurrentFunction());
		setCurrentClass(p.getCurrentClass());
	}
	
	/** looks for id in the SymbolTable recursing through its parent tables
	 *  if necessary */
	public Type lookup(VarNode id) throws UndeclaredIdentifierException{
		Type t = table.get(id);
		if(t!=null) {
			return t;
		} else if(methods.get(currentClass) != null) {
			
			HashMap<String, Type> curMethods = methods.get(currentClass);
			if(curMethods.get(id.getLabel()) != null) {
				return curMethods.get(id.getLabel());
			}
			if(lookUpMethod(currentClass, id.getID()) != null) {
				return lookUpMethod(currentClass, id.getID());
			}
		}
		if(parent==null) {
			throw new UndeclaredIdentifierException(id);
		}
		return parent.lookup(id);
	}
	
	public Type lookup2(VarNode id) throws UndeclaredIdentifierException{
		Type t = table.get(id);
		if(t!=null) return t;
		else if(parent==null)
			return null;
		return parent.lookup2(id);
	}
	
	public void add(SymbolTable s) throws TypeError {
		HashMap<VarNode, Type> sTable = s.getSymTable();
		for(VarNode v : sTable.keySet()) {
			Type t = sTable.get(v);
			if(this.table.put(v, t) != null) {
				throw new TypeError("Trying to override same function");
			}
		}
	}
	
	/** Adds mapping id->t to calling SymbolTable */
	public void add(VarNode id, Type t){
		table.put(id, t);
	}
	
	public String toString(){
		String ret = table.toString();
		if (parent == null) return ret;
		else {
			String par = parent.toString();
			String total = ret.substring(0,ret.length()-1) + "; " +  par.substring(1);
			return total;
		}
	}
	
	public void combineSymTable(SymbolTable other){
		HashMap<VarNode, Type> b = other.getSymTable();
		this.table.putAll(b);
	}

	private HashMap<VarNode, Type> getSymTable() {
		return table;
	}

	public String getCurrentFunction() {
		return currentFunction;
	}
	
	public String getCurrentClass() {
		return currentClass;
	}

	public void setCurrentFunction(String currentFunction) {
		this.currentFunction = currentFunction;
	}
	
	public void setCurrentClass(String currentClass) {
		this.currentClass = currentClass;
	}
	
	public HashMap<String, String> getAssociations() {
		return associations;
	}
	
	
	public HashMap<VarNode, Type> getTable(){
		return table;
	}
	
	public boolean isInCurrentScope(VarNode id){
		return table.containsKey(id);
	}
	
	public String getFreshName(String s){
		String id = "x"+uid;
		uid++;
		associations.put(s,id);
		return id;
	}
	
	public String getFreshName() {
		String id = "l" + lid;
		lid++;
		listLocs.add(id);
		return id;
	}
	
	public String getFreshNameSpecial(String s, String t) {
		associations.put(s, t);
		return t;
	}

	public String lookUp(String s){
		String output = associations.get(s);
		if (output == null){
			if (parent != null)
				return parent.lookUp(s);
			else
				throw new RuntimeException("Tried to look up: " + s + ". Compiler implementation error, sorry.");
		}
		else{
			return output;
		}
	}
	
	public static Type lookUpMethod(String cName, String methodName) {
		if(cName == null) {
			return null;
		}
		HashMap<String, Type> map = methods.get(cName);
		Type t = map.get(methodName);
		if(t == null) {
			String parent = inheritanceTree.get(cName);
			if(parent != null) {
				return lookUpMethod(parent, methodName);
			} else {
				return null;
			}
		} else {
			return t;
		}
	}

	public void setMethods(HashMap<String,HashMap<String,Type>> methods) {
		System.err.println("setMethods has been deprecated should call addMethods!");
		this.methods = methods;
	}
	
	public void addMethods(HashMap<String, HashMap<String, Type>> map) {
		for(String s : map.keySet()) {
			if(!methods.containsKey(s)) {
				methods.put(s, map.get(s));;
			}
		}
		
	}

	public HashMap<String,HashMap<String,Type>> getMethods() {
		return methods;
	}

	public static HashMap<String, String> getInheritanceTree() {
		return inheritanceTree;
	}

	public static void setInheritanceTree(HashMap<String, String> inheritanceTree) {
		System.err.println("setInheritanceTree has been deprecated should call addToInheritanceTree!");
		SymbolTable.inheritanceTree = inheritanceTree;
	}


	public static void addToInheritanceTree(
			HashMap<String, String> map) {
		for(String s : map.keySet()) {
			if(!inheritanceTree.containsKey(s)) {
				inheritanceTree.put(s, map.get(s));
			}
		}
		
	}
	
	public void addClass(ClassNode add) {
		classList.add(add);
		classNameToNode.put(add.getName(), add);
	}

	public static void setClassNameToNode(HashMap<String, ClassNode> classNameToNode) {
		SymbolTable.classNameToNode = classNameToNode;
	}

	public static HashMap<String, ClassNode> getClassNameToNode() {
		return classNameToNode;
	}
	
	public static void addFunctionRet(String fname, Type t) {
		funcRetSizes.put(Mangler.mangle(fname, t), t.getReturnTypes().length);
	}
	
	public static int getFunctionRetSize(String fname) {
		if(fname.equals("_I_outOfBounds_p")) {
			return 0;
		} else if(fname.equals("_I_alloc_i")) {
			return 1;
		}

		Integer temp = funcRetSizes.get(fname);
		if(temp == null) {
			return -1;
		} else {
			return temp.intValue();
		}
	}

	public static void initMethodsInRetSizeMap() {
		for(String cName : methods.keySet()) {
			HashMap<String,Type> st = methods.get(cName);
			for(String mName : st.keySet()) {
				Type t = st.get(mName);
				if(t.isFunction()) {
					String mangledName = Mangler.mangle(mName, t);
					funcRetSizes.put(mangledName, t.getReturnTypes().length);
				}
			}
		}
	}
	
}
