package rak248.xi.parser;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;
import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.CallIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.EseqIR;
import rak248.xi.ir.ExpIR;
import rak248.xi.ir.ExpressionIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.Mangler;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.ir.SeqIR;
import rak248.xi.ir.TempIR;
import rak248.xi.ir.OpIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;

public class AccessorNode extends StatementsNode{
	
	private HashMap<SyntaxNode, Type> typeMap;
	
	public AccessorNode(Position position){
		setLabel("Accessor");
		setPosition(position);
		typeMap = new HashMap<SyntaxNode, Type>();
	}
	
	public void add(VarNode varNode) {
		Position newPos = new Location(position().unit(), position().lineStart(), varNode.position().lineEnd(), position().columnStart(), varNode.position().columnEnd());
		setPosition(newPos);
		addChild(varNode);
	}
	
	public void add(ThisNode thisNode) {
		Position newPos = new Location(position().unit(), position().lineStart(), thisNode.position().lineEnd(), position().columnStart(), thisNode.position().columnEnd());
		setPosition(newPos);
		addChild(thisNode);
	}
	
	public void add(ObjectNode objectNode) {
		Position newPos = new Location(position().unit(), position().lineStart(), objectNode.position().lineEnd(), position().columnStart(), objectNode.position().columnEnd());
		setPosition(newPos);
		addChild(objectNode);
	}

	public void add(FunctionCallNode f) {
		Position newPos = new Location(position().unit(), position().lineStart(), f.position().lineEnd(), position().columnStart(), f.position().columnEnd());
		setPosition(newPos);
		addChild(f);
	}

	public void addInFront(VarNode varNode) {
		getChildren().add(0, varNode);
		Position newPos = new Location(position().unit(), varNode.position().lineStart(), position().lineEnd(), varNode.position().columnStart(), position().columnEnd());
		setPosition(newPos);
	}

	public void addInFront(FunctionCallNode id,Position useless) {
		getChildren().add(0,id);
		Position newPos = new Location(position().unit(), id.position().lineStart(), position().lineEnd(), id.position().columnStart(), position().columnEnd());
		setPosition(newPos);
	}

	public void addInFront(ThisNode thisNode) {
		getChildren().add(0,thisNode);
		Position newPos = new Location(position().unit(), thisNode.position().lineStart(), position().lineEnd(), thisNode.position().columnStart(), position().columnEnd());
		setPosition(newPos);
	}

	public void add(ArrayLookUpNode f) {
		Position newPos = new Location(position().unit(), position().lineStart(), f.position().lineEnd(), position().columnStart(), f.position().columnEnd());
		setPosition(newPos);
		addChild(f);
	}

	public void addInFront(ArrayLookUpNode id, Position useless) {
		getChildren().add(0,id);
		Position newPos = new Location(position().unit(), id.position().lineStart(), position().lineEnd(), id.position().columnStart(), position().columnEnd());
		setPosition(newPos);	
	}
	
	
	public Type typeCheck(SymbolTable table) throws TypeError, UndeclaredIdentifierException {
		setSymTable(table);
		Type last = ((SyntaxNode) ((ArrayList<VisualizableTreeNode>) children()).get(0)).typeCheck(table);

		typeMap.put((SyntaxNode) getChildren().get(0), last);
		for(int i = 1; i < ((ArrayList<VisualizableTreeNode>) children()).size(); i++) {
			SymbolTable childTable = new SymbolTable(table);
			childTable.add(new VarNode("this", this.position(), this.position()), last);
			
			SyntaxNode child = (SyntaxNode) ((ArrayList<VisualizableTreeNode>) children()).get(i);

			if(child instanceof VarNode) {
				last = ((VarNode) child).typeCheck(childTable, last.getObject());
				//last = ((VarNode) child).typeCheck(childTable);
			} else if (child instanceof FunctionCallNode) {
				last = ((FunctionCallNode) child).typeCheck(childTable, last.getObject());
			} else if (child instanceof ProcedureCallNode) {
				last = ((ProcedureCallNode) child).typeCheck(childTable, last.getObject());
			}else {
				System.err.println("Unimplemented: Grant doesn't understand AccessorNode");
				System.err.println(child.getClass());
				return null;
			}
			typeMap.put(child, last);
		}
		return last;
	}

	//ranjay is a noob
	public void addInFront(ObjectNode id) {
		getChildren().add(0,id);
		Position newPos = new Location(position().unit(), id.position().lineStart(), position().lineEnd(), id.position().columnStart(), position().columnEnd());
		setPosition(newPos);
		
	}

	public void add(ProcedureCallNode f) {
		Position newPos = new Location(position().unit(), position().lineStart(), f.position().lineEnd(), position().columnStart(), f.position().columnEnd());
		setPosition(newPos);
		addChild(f);
	}
	
	/*public SyntaxIR translate(HashMap<String, String> map){
		HashMap<String,String> initialMap = (HashMap<String, String>) map.clone();
		SyntaxIR syn = new SyntaxIR();
		SyntaxIR caller = null;
		Type t = null;
		for(int i = 0; i < getChildren().size()-1; i++) {
			SyntaxNode child = (SyntaxNode) getChildren().get(i);
			SyntaxIR childIR = (SyntaxIR) child.translate(map);
			if (caller == null){
				caller = childIR;
				t = child.getType();
			}
			else{
				ClassNode cNode = null;
				for(ClassNode node : getSymTable().getClassList()) {
					if(node.getType().equals(typeMap.get(caller))) {
						cNode = node;
						break;
					}
				}
				if(child instanceof VarNode) {
					HashMap<TempIR, Integer> offsetMap = cNode.getFieldOffset();
					child.setSymTable(getSymTable());
					TempIR field = (TempIR) child.translate(map);
					
					for(TempIR offIR : offsetMap.keySet()) {;
						if(offIR.getActual().equals(((VarNode) child).getID())) {
							field = offIR;
						}
					}
					Integer offset = offsetMap.get(field);
					t = child.getType();
					caller = new MemIR(new OpIR(opEnum.PLUS, (ExpressionIR) childIR, new ConstIR(offset)));
				}
				else if (child instanceof FunctionCallNode){
					EseqIR result = new EseqIR();
					HashMap<NameIR, Integer> methodOffset = cNode.getMethodOffset();
					String funcNameString = Mangler.mangle(((FunctionCallNode) child).getId(), getSymTable().getMethods().get(t.toString()).get(((FunctionCallNode) child).getId()));
					NameIR funcName = new NameIR(funcNameString);
					Integer offset = methodOffset.get(funcName);
					TempIR t0 = new TempIR("_acc"+SyntaxIR.getFreshLabel());
					MoveIR move = new MoveIR(t0, childIR);
					result.addChild(move);
					MemIR t0Mem = new MemIR(t0);
					for(NameIR check : methodOffset.keySet()){
						if(check.equals(funcName)) {
							offset = methodOffset.get(check);
						}
					}
					MemIR name = new MemIR(new OpIR(opEnum.PLUS, t0Mem, new ConstIR(offset)));
					CallIR call = new CallIR(name);
					call.addChild(t0);
					ArrayList<Type> argType = new ArrayList<Type>();
					for(int j = 0; j < child.getChildren().size(); j++) {
						SyntaxNode arg = (SyntaxNode) child.getChildren().get(j);
						call.addChild((SyntaxIR) arg.translate(initialMap));
						argType.add(((SyntaxNode) arg).getType());
					}
					result.addChild(call);
					syn.addChild(result);
					t = child.getType();
					caller = result;
				}
				else if (child instanceof ProcedureCallNode) {
					HashMap<NameIR, Integer> methodOffset = cNode.getMethodOffset();
					String funcNameString;
					SeqIR result = new SeqIR();
					funcNameString = Mangler.mangle(((ProcedureCallNode) child).getId(), getSymTable().getMethods().get(t.toString()).get(((ProcedureCallNode) child).getId()));
					NameIR funcName = new NameIR(funcNameString);
					Integer offset = methodOffset.get(funcName);
					TempIR t0 = new TempIR("_acc"+SyntaxIR.getFreshLabel());
					MoveIR move = new MoveIR(t0, childIR);
					result.addChild(move);
					MemIR t0Mem = new MemIR(t0);
					for(NameIR check : methodOffset.keySet()){
						if(check.equals(funcName)) {
							offset = methodOffset.get(check);
						}
					}
					MemIR name = new MemIR(new OpIR(opEnum.PLUS, t0Mem, new ConstIR(offset)));
					CallIR call = new CallIR(name);
					call.addChild(t0);
					ArrayList<Type> argType = new ArrayList<Type>();
					for(int j = 0; j < child.getChildren().size(); j++) {
						SyntaxNode arg = (SyntaxNode) child.getChildren().get(j);
						if(!(j == 0 && arg instanceof VarNode)) {
							call.addChild((SyntaxIR) arg.translate(initialMap));
							argType.add(((SyntaxNode) arg).getType());
						}
					}
					result.addChild(new ExpIR(call));
					syn.addChild(result);
					return syn;
				}
			}
		}
		return syn;
	}*/
	
	public SyntaxIR translate(HashMap<String, String> map) {
		if(getChildren().get(getChildren().size()-1) instanceof ProcedureCallNode) {
			return translateStatement(map);
		} else {
			return translateExpression(map);
		}
		
	}
	
	public SyntaxIR translateStatement(HashMap<String, String> map) {
		MemIR mem;
		@SuppressWarnings("unchecked")
		HashMap<String,String> initialMap = (HashMap<String, String>) map.clone();
		SeqIR seq = new SeqIR();
		SyntaxNode child = (SyntaxNode) getChildren().get(0);
		if(child instanceof VarNode)
			child.setType(getSymTable().lookup((VarNode) child));
		SyntaxIR childIR = child.translate(map);
		for(int i = 1; i < getChildren().size(); i++) {
			SyntaxNode second = (SyntaxNode) getChildren().get(i);
			ClassNode cNode = null;
			for(ClassNode node : SymbolTable.classList) {
				if(node.getType().equals(typeMap.get(child))) {
					cNode = node;
					break;
				}
			}
			if(second instanceof VarNode) {
				HashMap<TempIR, Integer> offsetMap = cNode.getFieldOffset();
				second.setSymTable(getSymTable());
				TempIR field = (TempIR) second.translate(map);
				
				for(TempIR offIR : offsetMap.keySet()) {;
					if(offIR.getActual().equals(((VarNode) second).getID())) {
						field = offIR;
					}
				}
				Integer offset = offsetMap.get(field);
				mem = new MemIR(new OpIR(opEnum.PLUS, (ExpressionIR) childIR, new ConstIR(offset)));
				child = second;
				childIR = mem;
			}
			if(second instanceof FunctionCallNode) {
				EseqIR result = new EseqIR();
				HashMap<NameIR, Integer> methodOffset = cNode.getMethodOffset();
				String funcNameString;
				funcNameString = Mangler.mangle(((FunctionCallNode) second).getId(), getSymTable().getMethods().get(child.getType().toString()).get(((FunctionCallNode) second).getId()));
				NameIR funcName = new NameIR(funcNameString);
				Integer offset = methodOffset.get(funcName);
				TempIR t0 = new TempIR("acc"+SyntaxIR.getFreshLabel());
				MoveIR move = new MoveIR(t0, childIR);
				result.addChild(move);
				MemIR t0Mem = new MemIR(t0);
				for(NameIR check : methodOffset.keySet()){
					if(check.equals(funcName)) {
						offset = methodOffset.get(check);
					}
				}
				if(offset == null) {
					System.out.println("offset null");
					offset = 8;
					for(Wrapper kanye : SymbolTable.methodsWrapper.get(cNode.getName())) {
						//System.out.println("func name:"+funcName);
						String mangled = Mangler.mangle(kanye.methodName, kanye.methodType);
						//System.out.println("mangled:"+mangled);
						//System.out.println("wrapper method:"+kanye.methodName);
						if(mangled.equals(funcName.getLabel())) {
							//System.out.println("found right offset an");
							break;
						}
						offset += 8;
					}
				}
				MemIR name = new MemIR(new OpIR(opEnum.PLUS, t0Mem, new ConstIR(offset)));
				CallIR call = new CallIR(name, (FunctionCallNode) second);
				call.addChild(t0);
				ArrayList<Type> argType = new ArrayList<Type>();
				for(int j = 0; j < second.getChildren().size(); j++) {
					SyntaxNode arg = (SyntaxNode) second.getChildren().get(j);
					call.addChild((SyntaxIR) arg.translate(initialMap));
					argType.add(((SyntaxNode) arg).getType());
				}
				result.addChild(call);
				child = second;
				childIR = result;
			}
			if(second instanceof ProcedureCallNode) {
				SeqIR result = new SeqIR();
				if(getSymTable().getMethods().get(cNode.getName()).size() > 0) {
					if(cNode.getMethodOffset().size() == 0) {
						cNode.setSymTable(getSymTable());
					}
				}
				HashMap<NameIR, Integer> methodOffset = cNode.getMethodOffset();
				String funcNameString;
				funcNameString = Mangler.mangle(((ProcedureCallNode) second).getId(), getSymTable().getMethods().get(child.getType().toString()).get(((ProcedureCallNode) second).getId()));
				NameIR funcName = new NameIR(funcNameString);
				Integer offset = methodOffset.get(funcName);
				TempIR t0 = new TempIR("acc"+SyntaxIR.getFreshLabel());
				MoveIR move = new MoveIR(t0, childIR);
				result.addChild(move);
				MemIR t0Mem = new MemIR(t0);
				for(NameIR check : methodOffset.keySet()){
					if(check != null && check.equals(funcName)) {
						offset = methodOffset.get(check);
					}
				}
				//TODO: implement kanye hack in all other cases
				if(offset == null) {
					System.out.println("offset null");
					offset = 8;
					for(Wrapper kanye : SymbolTable.methodsWrapper.get(cNode.getName())) {
						//System.out.println("func name:"+funcName);
						String mangled = Mangler.mangle(kanye.methodName, kanye.methodType);
						//System.out.println("mangled:"+mangled);
						//System.out.println("wrapper method:"+kanye.methodName);
						if(mangled.equals(funcName.getLabel())) {
							//System.out.println("found right offset an");
							break;
						}
						offset += 8;
					}
				}
				MemIR name = new MemIR(new OpIR(opEnum.PLUS, t0Mem, new ConstIR(offset)));
				CallIR call = new CallIR(name, (ProcedureCallNode) second);
				call.addChild(t0);
				ArrayList<Type> argType = new ArrayList<Type>();
				for(int j = 0; j < second.getChildren().size(); j++) {
					SyntaxNode arg = (SyntaxNode) second.getChildren().get(j);
					if(!(j == 0 && arg instanceof VarNode)) {
						call.addChild((SyntaxIR) arg.translate(initialMap));
						argType.add(((SyntaxNode) arg).getType());
					}
				}
				result.addChild(new ExpIR(call));
				return result;
			}
		}
		System.out.println("statement - oh shit!");
		return null;
	}
	
	public SyntaxIR translateExpression(HashMap<String, String> map) {
		MemIR mem;
		@SuppressWarnings("unchecked")
		HashMap<String,String> initialMap = (HashMap<String, String>) map.clone();
		SyntaxNode child = (SyntaxNode) getChildren().get(0);
		if(child instanceof VarNode)
			child.setType(getSymTable().lookup((VarNode) child));
		SyntaxIR childIR = child.translate(map);
		for(int i = 1; i < getChildren().size(); i++) {
			SyntaxNode second = (SyntaxNode) getChildren().get(i);
			ClassNode cNode = null;
			for(ClassNode node : SymbolTable.classList) {
				if(node.getType().equals(typeMap.get(child))) {
					cNode = node;
					break;
				}
			}
			if(second instanceof VarNode) {
				HashMap<TempIR, Integer> offsetMap = cNode.getFieldOffset();
				second.setSymTable(getSymTable());
				TempIR field = (TempIR) second.translate(map);
				
				for(TempIR offIR : offsetMap.keySet()) {;
					if(offIR.getActual().equals(((VarNode) second).getID())) {
						field = offIR;
					}
				}
				Integer offset = offsetMap.get(field)+8;
				mem = new MemIR(new OpIR(opEnum.PLUS, (ExpressionIR) childIR, new ConstIR(offset)));
				child = second;
				childIR = mem;
			}
			if(second instanceof FunctionCallNode) {
				EseqIR result = new EseqIR();
				HashMap<NameIR, Integer> methodOffset = cNode.getMethodOffset();
				String funcNameString;
				String rightClass = "";
				String childString = child.getType().toString();
				String parentString = SymbolTable.getInheritanceTree().get(childString);
				while(childString != null) {
					if(getSymTable().getMethods().get(childString).get(((FunctionCallNode) second).getId()) == null) {
						childString = parentString;
						parentString = SymbolTable.getInheritanceTree().get(childString);
					} else {
						rightClass = childString;
						break;
					}
				}
				funcNameString = Mangler.mangle(((FunctionCallNode) second).getId(), getSymTable().getMethods().get(rightClass).get(((FunctionCallNode) second).getId()));
				NameIR funcName = new NameIR(funcNameString);
				Integer offset = methodOffset.get(funcName);
				TempIR t0 = new TempIR("acc"+SyntaxIR.getFreshLabel());
				MoveIR move = new MoveIR(t0, childIR);
				result.addChild(move);
				MemIR t0Mem = new MemIR(t0);
				for(NameIR check : methodOffset.keySet()){
					if(check != null && check.equals(funcName)) {
						offset = methodOffset.get(check);
					}
				}
				if(offset == null) {
					System.out.println("offset null");
					offset = 8;
					for(Wrapper kanye : SymbolTable.methodsWrapper.get(cNode.getName())) {
						//System.out.println("func name:"+funcName);
						String mangled = Mangler.mangle(kanye.methodName, kanye.methodType);
						//System.out.println("mangled:"+mangled);
						//System.out.println("wrapper method:"+kanye.methodName);
						if(mangled.equals(funcName.getLabel())) {
							//System.out.println("found right offset an");
							break;
						}
						offset += 8;
					}
				}
				MemIR name = new MemIR(new OpIR(opEnum.PLUS, t0Mem, new ConstIR(offset)));
				CallIR call = new CallIR(name, (FunctionCallNode) second);
				call.addChild(t0);
				ArrayList<Type> argType = new ArrayList<Type>();
				for(int j = 0; j < second.getChildren().size(); j++) {
					SyntaxNode arg = (SyntaxNode) second.getChildren().get(j);
					call.addChild((SyntaxIR) arg.translate(initialMap));
					argType.add(((SyntaxNode) arg).getType());
				}
				result.addChild(call);
				child = second;
				childIR = result;
			}
		}
		return childIR;
	}

	public void add(SyntaxNode child) {
		if(child instanceof VarNode) {
			add((VarNode)child);
			return;
		}
		if(child instanceof FunctionCallNode) {
			add((FunctionCallNode)child);
			return;
		}
		if(child instanceof ThisNode) {
			add((ThisNode)child);
			return;
		}
		if(child instanceof ArrayLookUpNode) {
			add((ArrayLookUpNode)child);
			return;
		}
		if(child instanceof ObjectNode){
			add((ObjectNode)child);
			return;
		}
		System.out.println("We didn't ask for this!");
	}
}
