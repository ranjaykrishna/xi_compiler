package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;
import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.CallIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.EseqIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.Mangler;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.SeqIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.DeclaredIdentifierException;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;

public class ClassNode extends SyntaxNode{
	
	private HashMap<DeclarationNode, Integer> decOffset;
	private String name;
	private ExtendsNode ext;
	private MemIR classLoc;
	private HashMap<TempIR, Integer> fieldOffset;
	private MemIR DV;
	private HashMap<NameIR, Integer> methodOffset;
	private HashMap<NameIR, SyntaxIR> methodMap;
	private ArrayList<MemIR> thisIR;
	private HashMap<String, String> translateMap;
	private ArrayList<String> listOfMethods;
	private String setupString;
	private String sizeString;
	private HashMap<FunctionDecNode, SyntaxIR> nodeToIRMap;
	private static HashMap<String, ClassNode> nameToClassNode = new HashMap<String, ClassNode>();
	private static ArrayList<String> listOfTranslated = new ArrayList<String>();
	private int sizeOfClass;
	private HashMap<String, HashMap<LabelIR, Integer>> generatedDV;
	private ArrayList<SyntaxIR> parentDV;
	
	public ClassNode(String string, ExtendsNode ext, ClassBodyNode b,
			Position position, Position position2) {
		name = string;
		this.setExt(ext);
		
		addChild(b);
		setLabel("CLASS: "+name);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		fieldOffset = new HashMap<TempIR, Integer>();
		methodOffset = new HashMap<NameIR, Integer>();
		decOffset = new HashMap<DeclarationNode, Integer>();
		methodMap = new HashMap<NameIR, SyntaxIR>();
		listOfMethods = new ArrayList<String>();
		thisIR = new ArrayList<MemIR>();
		nodeToIRMap = new HashMap<FunctionDecNode, SyntaxIR>();
		nameToClassNode.put(name, this);
		generatedDV = new HashMap<String, HashMap<LabelIR, Integer>>();
	}

	public Type getType() {
		return new Type(typeEnum.ABSTRACT, name);
	}
	
	public void setExt(ExtendsNode ext) {
		this.ext = ext;
	}

	public ExtendsNode getExt() {
		return ext;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean canBeTranslated() {
		/*if(ext.toString().equals("Extends Nothing"))
			return true;
		ArrayList<String> parents = new ArrayList<String>();
		String child = name;
		String parent = SymbolTable.getInheritanceTree().get(child);
		while(parent != null) {
			parents.add(parent);
			child = parent;
			parent = SymbolTable.getInheritanceTree().get(child);
		}
		boolean isTrue = true;
		for(String parentString : parents) {
			if(!listOfTranslated.contains(parentString))
				isTrue = false;
		}
		return isTrue;*/
		return true;
	}
	
	public Type typeCheck(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		table.setCurrentClass(name);
		setSymTable(table);
		SymbolTable childTable = new SymbolTable(table);
		Type type = new Type(typeEnum.ABSTRACT, name);
		Type unit = new Type(typeEnum.UNIT);
		childTable.add(new VarNode("this", this.position(), this.position()), type);
		for(VisualizableTreeNode c : children()) {
			SyntaxNode child = (SyntaxNode) c;
			if(child instanceof FunctionsNode) {
				for(VisualizableTreeNode vtn : child.getChildren()) {
					FunctionDecNode childvtn = (FunctionDecNode) vtn;
					if(!childvtn.typeCheck(table).isUnit()) {
						String message = "TypeError: function declaration must be of type unit at " + child.position();
						throw new TypeError(message);
					}
				}
			}
			if(!child.typeCheck(childTable).isUnit()) {
				String message = "TypeError: ClassNode must have children of type unit at " + this.position();
				throw new TypeError(message);
			}
		}
		return unit;
	}
	
	/*
	 * A class node sets up the declaration map and also generates IR for all of the methods
	 */
	public SyntaxIR translate(HashMap<String, String> map) {
		SeqIR seq = new SeqIR();
		if(ext.toString().equals("Extends Nothing")) {
			ClassBodyNode body = (ClassBodyNode) getChildren().get(0);
			int decCount = 0;
			int fieldIndex = 0;
			ConstIR fieldConst = new ConstIR(fieldIndex);
			/*
			 * This counts all the declaration nodes and 
			 */
			for(VisualizableTreeNode child : body.getChildren()) {
				if(child instanceof DeclarationNode) {
					if(((DeclarationNode) child).getDecList() == null)
						decCount++;
					else {
						for(DeclarationNode temp : ((DeclarationNode) child).getDecList()) {
							decCount++;
						}
					}
				}
			}
			sizeOfClass = (decCount + 1)*8;
			for(VisualizableTreeNode childUn : body.getChildren()) {
				/*
				 * What the declaration stuff does:
				 * Generate's the declaration node
				 * gets the end tempIR that its moved in to.
				 * move that tempIR into the appropriate MemIR location of the stack.
				 */
				if(childUn instanceof DeclarationNode) {
					DeclarationNode child = (DeclarationNode) childUn;
					/*
					 * for declarations like
					 * a,b,c:int
					 */
					if(child.getDecList() != null) {
						for(DeclarationNode decPart : child.getDecList()) {
							decPart.translate(map);
							TempIR lastDec = decPart.getEndTemp();
							MoveIR movField = new MoveIR(new MemIR(new OpIR(opEnum.PLUS, classLoc, fieldConst)), lastDec);
							fieldOffset.put(lastDec, new Integer(fieldIndex));
							fieldIndex = fieldIndex + 8;
							fieldConst = new ConstIR(fieldIndex);
							decOffset.put(decPart, fieldIndex);
							
						}
					}
					/*
					 * For declarations like
					 * d:bool
					 */
					else {
						TempIR lastDec = child.getEndTemp();
						MoveIR movField = new MoveIR(new MemIR(new OpIR(opEnum.PLUS, classLoc, fieldConst)), lastDec);
						fieldOffset.put(lastDec, new Integer(fieldIndex));
						fieldIndex = fieldIndex + 8;
						fieldConst = new ConstIR(fieldIndex);
						decOffset.put(child, fieldIndex);
					}
				}
			}
			for(VisualizableTreeNode child : body.getChildren()) {
				if(child instanceof FunctionsNode) {
					/*
					 * Set up the dispatch vector with the appropriate size.
					 * the size is the amount of children in the FunctionsNode
					 */
					translateMap = map;
					for(VisualizableTreeNode temp : child.children()) {
						FunctionDecNode fDec = (FunctionDecNode) temp;
						
						//Generate IR for all the methods and stick them at the top of the class
						SyntaxIR fDecIR = fDec.translateClass(fieldOffset, new TempIR("thisNode"), map, name);
						nodeToIRMap.put(fDec, fDecIR);
						listOfMethods.add(fDec.getMangled());
						seq.addChild(fDecIR);
					}
				}
			}
			listOfTranslated.add(name);
		} else {
			String childClass = name;
			String parent = SymbolTable.getInheritanceTree().get(childClass);
			ArrayList<ClassNode> parentClasses = new ArrayList<ClassNode>();
			while(parent != null) {
				childClass = parent;
				parentClasses.add(nameToClassNode.get(parent));
				parent = SymbolTable.getInheritanceTree().get(childClass);
			}
			generatedDV = new HashMap<String, HashMap<LabelIR, Integer>>();
			HashMap<String, Integer> generatedFieldOffset = new HashMap<String, Integer>();
			HashMap<String, Type> generatedFieldType = new HashMap<String, Type>();
			int methodOffset = 8;
			int fieldOffset = 8;
			for(int i = parentClasses.size()-1; i >= 0; i--) {
				ClassNode cNode = parentClasses.get(i);
				ArrayList<Wrapper> classWrap = SymbolTable.methodsWrapper.get(cNode.getName());
				HashMap<LabelIR, Integer> classMap = new HashMap<LabelIR, Integer>();
				for(Wrapper wrap : classWrap) {
					if(wrap.methodType.isFunction()) {
						String mangled = Mangler.mangle(wrap.methodName, wrap.methodType);
						LabelIR classLabel = new LabelIR(mangled);
						classMap.put(classLabel, new Integer(methodOffset));
						methodOffset = methodOffset+8;
					}
				}
				for(Wrapper wrap : classWrap) {
					if(!(wrap.methodType.getTypeEnum().equals(typeEnum.FUNCTION))) {
						generatedFieldOffset.put(wrap.methodName, fieldOffset);
						generatedFieldType.put(wrap.methodName, wrap.methodType);
						fieldOffset = fieldOffset+8;
					}
				}
				generatedDV.put(cNode.getName(), classMap);
			}
			ClassBodyNode body = (ClassBodyNode) getChildren().get(0);
			int decCount = 0;
			Integer fieldIndex = fieldOffset;
			ConstIR fieldConst = new ConstIR(fieldIndex);
			/*
			 * This counts all the declaration nodes and 
			 */
			for(VisualizableTreeNode child : body.getChildren()) {
				if(child instanceof DeclarationNode) {
					if(((DeclarationNode) child).getDecList() == null)
						decCount++;
					else {
						for(DeclarationNode temp : ((DeclarationNode) child).getDecList()) {
							decCount++;
						}
					}
				}
			}
			sizeOfClass = 0;
			for(ClassNode pClass : parentClasses) {
				sizeOfClass += (pClass.getSizeOfClass()-8);
			}
			sizeOfClass += (decCount + 1)*8;
			for(String fieldName : generatedFieldOffset.keySet()) {
				DeclarationNode decNode = new DeclarationNode(fieldName, generatedFieldType.get(fieldName),position(), position());
				decNode.setSymTable(getSymTable());
				decNode.translate(map);
				TempIR lastDec = decNode.getEndTemp();
				this.fieldOffset.put(lastDec, generatedFieldOffset.get(fieldName));
			}
			for(VisualizableTreeNode childUn : body.getChildren()) {
				/*
				 * What the declaration stuff does:
				 * Generate's the declaration node
				 * gets the end tempIR that its moved in to.
				 * move that tempIR into the appropriate MemIR location of the stack.
				 */
				if(childUn instanceof DeclarationNode) {
					DeclarationNode child = (DeclarationNode) childUn;
					/*
					 * for declarations like
					 * a,b,c:int
					 */
					if(child.getDecList() != null) {
						for(DeclarationNode decPart : child.getDecList()) {
							decPart.translate(map);
							TempIR lastDec = decPart.getEndTemp();
							MoveIR movField = new MoveIR(new MemIR(new OpIR(opEnum.PLUS, classLoc, fieldConst)), lastDec);
							this.fieldOffset.put(lastDec, new Integer(fieldIndex));
							fieldIndex = fieldIndex + 8;
							fieldConst = new ConstIR(fieldIndex);
							decOffset.put(decPart, fieldIndex);
							
						}
					}
					/*
					 * For declarations like
					 * d:bool
					 */
					else {
						TempIR lastDec = child.getEndTemp();
						MoveIR movField = new MoveIR(new MemIR(new OpIR(opEnum.PLUS, classLoc, fieldConst)), lastDec);
						this.fieldOffset.put(lastDec, new Integer(fieldIndex));
						fieldIndex = fieldIndex + 8;
						fieldConst = new ConstIR(fieldIndex);
						decOffset.put(child, fieldIndex);
					}
				}
			}
			for(VisualizableTreeNode child : body.getChildren()) {
				if(child instanceof FunctionsNode) {
					/*
					 * Set up the dispatch vector with the appropriate size.
					 * the size is the amount of children in the FunctionsNode
					 */
					translateMap = map;
					for(VisualizableTreeNode temp : child.children()) {
						FunctionDecNode fDec = (FunctionDecNode) temp;
						
						//Generate IR for all the methods and stick them at the top of the class
						SyntaxIR fDecIR = fDec.translateClass(this.fieldOffset, new TempIR("thisNode"), map, name);
						nodeToIRMap.put(fDec, fDecIR);
						listOfMethods.add(fDec.getMangled());
						seq.addChild(fDecIR);
					}
				}
			}
			listOfTranslated.add(name);
		}
		return seq;	
	}
	
	public MemIR getClassLoc() {
		return classLoc;
	}

	public void setFieldOffset(HashMap<TempIR, Integer> fieldOffset) {
		this.fieldOffset = fieldOffset;
	}

	public HashMap<TempIR, Integer> getFieldOffset() {
		return fieldOffset;
	}

	public HashMap<NameIR, Integer> getMethodOffset() {
		return methodOffset;
	}
	
	public HashMap<NameIR, SyntaxIR> getMethodMap() {
		return methodMap;
	}

	public void setDV(MemIR dV) {
		DV = dV;
	}

	/*
	 * Returns an Eseq that builds the DV and returns the MemIR corresponding to the DV
	 */
	public SyntaxIR getDV(HashMap<String,String> map) {
		ClassBodyNode body = (ClassBodyNode) getChildren().get(0);
		ConstIR size;
		CallIR call;
		MoveIR move;
		EseqIR seq = new EseqIR();
		TempIR DV = new TempIR("_DV"+SyntaxIR.getFreshLabel());
		if(ext.toString().equals("Extends Nothing")) {
			for(VisualizableTreeNode child : body.getChildren()) {
				if(child instanceof FunctionsNode) {
					/*
					 * Set up the dispatch vector with the appropriate size.
					 * the size is the amount of children in the FunctionsNode
					 */
					size = new ConstIR(((FunctionsNode) child).getChildren().size()+1);
					call = new CallIR(new NameIR("_I_alloc_i"), new OpIR(
		                    opEnum.LSHIFT,size,new ConstIR(3)));
					//move into a temp, the memory address returned by CallIR
					move = new MoveIR(DV, call);
					seq.addChild(move);
					int methodInt = 8;
					//cmIR is the offset for the method.
					ConstIR cmIR = new ConstIR(methodInt);
					//want the same map for every method
					translateMap = map;
					for(VisualizableTreeNode temp : child.children()) {
						FunctionDecNode fDec = (FunctionDecNode) temp;
						//Add, as an argument, the tempIR for "thisNode"
						SyntaxIR fDecIR = nodeToIRMap.get(fDec);
						NameIR fDecLabel = new NameIR(fDec.getMangled());
						LabelIR labelDec = new LabelIR(fDecLabel.getLabel());
						//generate the IR for the function
						methodMap.put(fDecLabel, fDecIR);
						//put the label and offset for the function into a map
						methodOffset.put(fDecLabel, methodInt);
						MemIR memOff = new MemIR(new OpIR(opEnum.PLUS, DV, cmIR));
						MoveIR movOff = new MoveIR(memOff, labelDec);
						//generate a move that moves the label into the DV
						//seq.addChild(movOff);
						//update the offsets
						methodInt = methodInt+8;
						cmIR = new ConstIR(methodInt);
					}
					getDVASMSetup();
					getSizeSetup();
					seq.addChild(new MoveIR(DV, new LabelIR(setupString)));
				}
			}
		} else {
			for(VisualizableTreeNode child : body.getChildren()) {
				if(child instanceof FunctionsNode) {
					/*
					 * Set up the dispatch vector with the appropriate size.
					 * the size is the amount of children in the FunctionsNode
					 */
					size = new ConstIR(((FunctionsNode) child).getChildren().size()+1+generatedDV.size());
					call = new CallIR(new NameIR("_I_alloc_i"), new OpIR(
		                    opEnum.LSHIFT,size,new ConstIR(3)));
					//move into a temp, the memory address returned by CallIR
					move = new MoveIR(DV, call);
					seq.addChild(move);
					int methodInt = 0;
					for(String iter : generatedDV.keySet()) {
						for(LabelIR iter2 : generatedDV.get(iter).keySet())
						{
							if(generatedDV.get(iter).get(iter2) > methodInt) {
								methodInt = generatedDV.get(iter).get(iter2);
							}
							methodOffset.put(new NameIR(iter2.getName()), generatedDV.get(iter).get(iter2));
						}
						methodInt += 8;
						methodOffset.put(null, methodInt);
					}
					methodInt += 8;
					//cmIR is the offset for the method.
					ConstIR cmIR = new ConstIR(methodInt);
					//want the same map for every method
					translateMap = map;
					for(VisualizableTreeNode temp : child.children()) {
						FunctionDecNode fDec = (FunctionDecNode) temp;
						//Add, as an argument, the tempIR for "thisNode"
						SyntaxIR fDecIR = nodeToIRMap.get(fDec);
						NameIR fDecLabel = new NameIR(fDec.getMangled());
						LabelIR labelDec = new LabelIR(fDecLabel.getLabel());
						//generate the IR for the function
						methodMap.put(fDecLabel, fDecIR);
						//put the label and offset for the function into a map
						methodOffset.put(fDecLabel, methodInt);
						MemIR memOff = new MemIR(new OpIR(opEnum.PLUS, DV, cmIR));
						MoveIR movOff = new MoveIR(memOff, labelDec);
						//generate a move that moves the label into the DV
						//seq.addChild(movOff);
						//update the offsets
						methodInt = methodInt+8;
						cmIR = new ConstIR(methodInt);
					}
					getDVASMSetup();
					getSizeSetup();
					seq.addChild(new MoveIR(DV, new LabelIR(setupString)));
				}
			}
			parentDV = new ArrayList<SyntaxIR>();
			String child = name;
			String parent = SymbolTable.getInheritanceTree().get(child);
			while(parent != null) {
				parentDV.add(nameToClassNode.get(parent).getDV(map));
				child = parent;
				parent = SymbolTable.getInheritanceTree().get(child);
			}
		}
		seq.addChild(DV);
		return seq;
	}

	public HashMap<DeclarationNode, Integer> getDecOffset() {
		return decOffset;
	}

	public void setThisIR(ArrayList<MemIR> thisIR) {
		this.thisIR = thisIR;
	}

	public ArrayList<MemIR> getThisIR() {
		return thisIR;
	}
	
	public String getDVASMSetup() {
		if(generatedDV.size() == 0) {
			ArrayList<String> mangledNames = new ArrayList<String>();
			for(String name : listOfMethods) {
				mangledNames.add(name);
			}
			String result = "\t.section .rodata\n";
			result += "\t.align 8\n";
			result += ".globl _I_vt_"+name+"\n";
			result += "_I_vt_"+name+":\n";
			result += "\t.quad 0\n";
			for(String methodName : mangledNames) {
				result += "\t.quad "+methodName+"\n";
			}
			result += "\t.text\n";
			setupString = "_I_vt_"+name;
			return result;
		} else {
			ArrayList<String> mangledNames = new ArrayList<String>();
			for(String name : listOfMethods) {
				mangledNames.add(name);
			}
			String result = "\t.section .rodata\n";
			result += "\t.align 8\n";
			result += ".globl _I_vt_"+name+"\n";
			result += "_I_vt_"+name+":\n";
			int cur = 8;
			for(int i = generatedDV.keySet().size()-1; i >=0; i --) {
				String cNode = (String) generatedDV.keySet().toArray()[i];
				result += "\t.quad 0\n";
				LinkedList<LabelIR> queue = new LinkedList<LabelIR>();
				queue.addAll(generatedDV.get(cNode).keySet());
				while(queue.size() > 0) {
					//TODO: infinite loop
					LabelIR func = queue.pop();
					if(generatedDV.get(cNode).get(func) == cur) {
						String shortened = func.getName();
						result += "\t.quad " + shortened +"\n";
						cur = cur + 8;
					} else {
						queue.addLast(func);
					}
				}
			}
			result += "\t.quad 0\n";
			for(String methodName : mangledNames) {
				result += "\t.quad "+methodName+"\n";
			}
			result += "\t.text\n";
			setupString = "_I_vt_"+name;
			return result;
		}
	}
	
	public String getSizeSetup() {
		String result = "\t.section .rodata\n";
		result += "\t.align 8\n";
		result += ".globl _I_size_"+name+"\n";
		result += "_I_size_"+name+":\n";
		result += "\t.quad "+sizeOfClass+"\n";
		result += "\t.text\n";
		
		setSizeString("_I_size_"+name);
		return result;
	}

	public void setListOfMethods(ArrayList<String> listOfMethods) {
		this.listOfMethods = listOfMethods;
	}

	public ArrayList<String> getListOfMethods() {
		return listOfMethods;
	}

	public void setNodeToIRMap(HashMap<FunctionDecNode, SyntaxIR> nodeToIRMap) {
		this.nodeToIRMap = nodeToIRMap;
	}

	public HashMap<FunctionDecNode, SyntaxIR> getNodeToIRMap() {
		return nodeToIRMap;
	}

	public static void setNameToClassNode(HashMap<String, ClassNode> nameToClassNode) {
		ClassNode.nameToClassNode = nameToClassNode;
	}

	public static HashMap<String, ClassNode> getNameToClassNode() {
		return nameToClassNode;
	}

	public void setSizeString(String sizeString) {
		this.sizeString = sizeString;
	}

	public String getSizeString() {
		return sizeString;
	}
	
	public int getSizeOfClass() {
		return sizeOfClass;
	}
	
	public ArrayList<SyntaxIR> getParentDV() {
		return parentDV;
	}
}
