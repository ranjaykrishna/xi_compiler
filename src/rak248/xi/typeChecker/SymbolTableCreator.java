package rak248.xi.typeChecker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import java_cup.runtime.Symbol;
import rak248.xi.SyntaxNode;
import rak248.xi.lexer.JFlexLexer;
import rak248.xi.parser.AssignmentNode;
import rak248.xi.parser.ClassNode;
import rak248.xi.parser.CompUnitNode;
import rak248.xi.parser.DeclarationNode;
import rak248.xi.parser.FunctionDecNode;
import rak248.xi.parser.FunctionsNode;
import rak248.xi.parser.InterfaceParser;
import rak248.xi.parser.LHSNode;
import rak248.xi.parser.UseNode;
import rak248.xi.parser.VarNode;
import rak248.xi.parser.Wrapper;
import edu.cornell.cs.cs4120.util.VisualizableTreeNode;


public class SymbolTableCreator {
	
	public SymbolTableCreator(){
		super();
	}
	
	public static HashMap<String,HashMap<String,Type>> classPass(CompUnitNode comp) {
		return classPass(comp, true);
	}
	
	public static HashMap<String,HashMap<String,Type>> classPass(CompUnitNode comp, boolean addFields){
		HashMap<String,HashMap<String,Type>> ret = new HashMap<String, HashMap<String,Type>>();
		HashMap<String, String> inheritanceTree = new HashMap<String, String>();
		for (VisualizableTreeNode node: comp.getChildren()){
			if (node instanceof ClassNode){
				ClassNode cnode = (ClassNode) node;
				String parent = cnode.getExt().getClassName();
				inheritanceTree.put(cnode.getType().getObject(), parent);
				SymbolTable.classList.add((ClassNode) node);
				HashMap<String,Type> map = new HashMap<String, Type>();
				ArrayList<Wrapper> wrapperList = new ArrayList<Wrapper>();
				if(!((ClassNode) node).getExt().toString().equals("Extends Nothing")) {
					wrapperList.addAll(SymbolTable.methodsWrapper.get(((ClassNode) node).getExt().getClassName()));
					for(Wrapper wrap : wrapperList) {
						map.put(wrap.methodName, wrap.methodType);
					}
				}
				for (VisualizableTreeNode n: ((SyntaxNode) ((ClassNode) node).getChildren().get(0)).getChildren()){
					//System.out.println("n stc:"+n);
					if (n instanceof FunctionsNode){
						for(VisualizableTreeNode fNode : ((SyntaxNode) n).getChildren()) {
							wrapperList.add(new Wrapper(((SyntaxNode) fNode).getLabel(), ((SyntaxNode) fNode).getType()));
							map.put(((FunctionDecNode) fNode).getLabel(), ((FunctionDecNode)fNode).getType());
						}
					}
					else if (n instanceof AssignmentNode && addFields){
						LHSNode lhs = (LHSNode) ((SyntaxNode) ((AssignmentNode) n).getChildren().get(0)).getChildren().get(0);
						wrapperList.add(new Wrapper(lhs.getId(), lhs.getType()));
						map.put(lhs.getId(),lhs.getType());
					}
					else if (n instanceof DeclarationNode && addFields){
						if(((DeclarationNode) n).getAllIds() != null){
							for(String id : ((DeclarationNode) n).getAllIds()) {
								wrapperList.add(new Wrapper(id, ((DeclarationNode) n).getDeclarationType()));
								map.put(id, ((DeclarationNode) n).getDeclarationType());
							}
						}
						else{
							for (DeclarationNode dd: ((DeclarationNode) n).getDecList()){
								wrapperList.add(new Wrapper(((DeclarationNode) n).getId(), ((SyntaxNode) n).getType()));
								map.put(((DeclarationNode) n).getId(), ((SyntaxNode) n).getType());
							}
						}
					}
				}
				SymbolTable.methodsWrapper.put(((ClassNode) node).getName(), wrapperList);
				if(map.containsKey(null))
					map.remove(null);
				ret.put(((ClassNode) node).getName(),map);
			}
		}
		SymbolTable.addToInheritanceTree(inheritanceTree);
		return ret;
	}
	
	public SymbolTable firstPass(String source, CompUnitNode p) throws Exception{
		ArrayList<VisualizableTreeNode> children = p.getChildren();
		FunctionsNode f = null;
		UseNode un = null;
		ClassNode cl = null;
		ArrayList<ClassNode> cList = new ArrayList<ClassNode>();
		for (VisualizableTreeNode child: children){
			if (child instanceof FunctionsNode){
				f = (FunctionsNode) child;
			}
			else if (child instanceof UseNode){
				un = (UseNode) child;
			}
			else if (child instanceof ClassNode){
				cl = (ClassNode) child;
				cList.add(cl);
			}
		}
		if (f != null && un != null){
			SymbolTable ht = headerTable(un,source);
			SymbolTable ft = funcTable(f,ht);
			//ft.setClassList(cList);
			return ft;
		}
		else if (f != null){
			SymbolTable result = funcTable(f,null);
			//result.setClassList(cList);
			return result;
		}
		else if(un != null) {
			SymbolTable result = headerTable(un,source);
			//result.setClassList(cList);
			return result;
		}
		else if(cl != null) {
			SymbolTable result = funcTable(null, null);
			//result.setClassList(cList);
			return result;
		}
		else{
			throw new EmptyFileException("The file doesn't contain any functions or classes");
		}
	}

	private SymbolTable headerTable(UseNode un,String fileName) throws Exception {
		int x = fileName.lastIndexOf(File.separator);
		if (x<0){
			return un.SymbolTableSetterGetter("");
		}
		return un.SymbolTableSetterGetter(fileName.substring(0,x+1));	
	}

	private SymbolTable funcTable(FunctionsNode f, SymbolTable ht) throws UndeclaredIdentifierException, TypeError {
		ArrayList<VisualizableTreeNode> children = f.getChildren();
		SymbolTable symtab;
		if (ht == null){
			symtab = new SymbolTable();
		}
		else{
			symtab = new SymbolTable(ht);
		}
		for (VisualizableTreeNode child: children){
			FunctionDecNode func = (FunctionDecNode) child;
			VarNode name = new VarNode(func.label(), func.position(), func.position());
			Type type = func.getType();
			SymbolTable.addFunctionRet(func.label(), type);
			if(null == symtab.lookup2(name)) {
				symtab.add(name, type);
			} 
			else{
				String message = "Function '" + func.label() + "' already declared in this scope. ";
				message += "Occurred at position: " + func.position();
				throw new TypeError(message);
			}
		}
		return symtab;
	}
	
	/**
	 * This method type checks the interface of this file. 
	 * It also compares these files to their ixi files 
	 * to make sure that all the types are right.
	 * @throws Exception
	 */
	public boolean interfaceCheck(String source, CompUnitNode compUnit) throws Exception{
			File interfaceFile = new File(source + ".ixi");
			if (interfaceFile.exists()){
				FileReader fil = new FileReader(source + ".ixi");
				BufferedReader bff = new BufferedReader(fil);
				JFlexLexer lexie = new JFlexLexer(source + ".ixi",bff);
				InterfaceParser parserObj = new InterfaceParser(lexie);
				Symbol parTree = parserObj.parse();
				CompUnitNode com = (CompUnitNode) parTree.value;
				SymbolTable sym = firstPass(source,com);
				
				SymbolTable symtab = firstPass(source,compUnit);
				symtab.addMethods(classPass(compUnit));
				//System.out.println("sym tab:"+symtab);
				if ( compUnit.typeCheck(symtab).isUnit() && typeEquals(symtab,sym)){
					return true;
				}
				else{
					throw new InterfaceMismatchException(source+".ixi and "+source+".xi files dont match");
				}
				
			}
			else{
				SymbolTable symtab = firstPass(source,compUnit);
				symtab.addMethods(classPass(compUnit));
				if ( compUnit.typeCheck(symtab).isUnit()){
					return true;
				}	
				else{
					throw new TypeError(source + " doesn't type check.");
				}
			}
	}
	
	/**
	 * Checks to see if all the elements in both the symbol tables have the same type
	 * 
	 * @param symtab
	 * @param sym
	 * @return true if the functions declared in the two symbol tables have the same type
	 */
	private boolean typeEquals(SymbolTable symtab, SymbolTable symInterface) {
		HashMap<VarNode,Type> table1 = symtab.getTable();
		HashMap<VarNode,Type> table2 = symInterface.getTable();
		Set<VarNode> keys = table2.keySet();
		for (VarNode vn: keys){
			if (table1.containsKey(vn)){
				Type type1 = table1.get(vn);
				Type type2 = table2.get(vn);
				if (!type1.equals(type2)){
					return false;
				}
			}
			else{
				return false;
			}
		}
		return true;
	}
	
}
