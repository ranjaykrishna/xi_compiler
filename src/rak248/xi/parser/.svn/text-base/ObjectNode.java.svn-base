package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.CallIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.EseqIR;
import rak248.xi.ir.ExpIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.NameIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.TempIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.Type.typeEnum;
import rak248.xi.typeChecker.TypeError;
import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;

public class ObjectNode extends ExpressionNode {
	
	private String name;
	private ClassNode thisClass;
	private MemIR objLoc;
	
	public ObjectNode(String string, AccessorNode e,
			Position position) {
		name = string;
		setLabel("New "+name+" Declaration");
		Position newPos = new Location(position.unit(),position.lineStart(),position.lineEnd(),position.columnStart(),position.columnEnd());
		setPosition(newPos);
		addChild(e);
	}

	public ObjectNode(String value, Position position) {
		name = value;
		setLabel("New "+name+" Declaration");
		Position newPos = new Location(position.unit(),position.lineStart(),position.lineEnd(),position.columnStart(),position.columnEnd());
		setPosition(newPos);
	}
	
	public Type typeCheck(SymbolTable tTable) throws TypeError {
		boolean flag = false;
		for(ClassNode cNode : SymbolTable.classList) {
			if(cNode.getName().equals(name)) {
				flag = true;
				thisClass = cNode;
			}
		}
		if(!flag) {
			String message = "Type error at position " + position() + ". No class of " +
					"this type found.";
			throw new TypeError(message);
		}
		this.setType(new Type(typeEnum.ABSTRACT, name));
		return new Type(typeEnum.ABSTRACT, name);
	}
	
	/*
	 * ObjectNode's translate initializes the declaration, allocates memory for everything, and gets
	 * the appropriate DV for this type of object.
	 */
	public SyntaxIR translate(HashMap<String, String> map) {
		HashMap<DeclarationNode, Integer> fieldOffset = thisClass.getDecOffset();
		HashMap<TempIR, Integer> extendedFieldOffset = thisClass.getFieldOffset();
		ArrayList<MemIR> thisList = thisClass.getThisIR();
		ExtendsNode ext = thisClass.getExt();
		EseqIR seq = new EseqIR();
		TempIR obj = new TempIR("obj"+SyntaxIR.getFreshLabel());
		if(ext.toString().equals("Extends Nothing")) {
			ConstIR size = new ConstIR(fieldOffset.size()+1);
			LabelIR label = new LabelIR(thisClass.getSizeString());
			//allocate memory for each of the fields;
			/*CallIR call = new CallIR(new NameIR("_I_alloc_i"), new OpIR(
                    opEnum.LSHIFT,size,new ConstIR(3)));*/
			CallIR call = new CallIR(new NameIR("_I_alloc_i"), label);
			MoveIR move = new MoveIR(obj, call);
			objLoc = new MemIR(obj);
			seq.addChild(move);
			//seq.addChild(new MoveIR(new TempIR("thisNode"), obj));
			//generate declarations for each of the fields
			for(DeclarationNode child : fieldOffset.keySet()) {
				seq.addChild(child.translate(map));
				TempIR decTemp = child.getEndTemp();
				MoveIR movField = new MoveIR(new MemIR(new OpIR(opEnum.PLUS, obj, new ConstIR(fieldOffset.get(child)))), decTemp);
				seq.addChild(movField);
			}
			//move the DV into the first slot of the memory allocated
			//to the Class
			SyntaxIR dispatchVector = thisClass.getDV(map);
			seq.addChild(new MoveIR(new MemIR(obj), dispatchVector));
		} else {
			ConstIR size = new ConstIR(fieldOffset.size()+1);
			LabelIR label = new LabelIR(thisClass.getSizeString());
			//allocate memory for each of the fields;
			/*CallIR call = new CallIR(new NameIR("_I_alloc_i"), new OpIR(
                    opEnum.LSHIFT,size,new ConstIR(3)));*/
			CallIR call = new CallIR(new NameIR("_I_alloc_i"), label);
			MoveIR move = new MoveIR(obj, call);
			objLoc = new MemIR(obj);
			seq.addChild(move);
			//seq.addChild(new MoveIR(new TempIR("thisNode"), obj));
			//generate declarations for each of the fields
			int[] offsetMeasure = new int[extendedFieldOffset.size() + 1];
			int i = 0;
			int offsetCount = 0;
			for(TempIR child : extendedFieldOffset.keySet()) {
				seq.addChild(new MoveIR(child, new ConstIR(0)));
				seq.addChild(new MoveIR(new MemIR(new OpIR(opEnum.PLUS, obj, new ConstIR(extendedFieldOffset.get(child)))), child));
				offsetMeasure[i] = offsetCount;
				offsetCount += extendedFieldOffset.get(child);
				i++;
			}
			/*for(DeclarationNode child : fieldOffset.keySet()) {
				seq.addChild(child.translate(map));
				TempIR decTemp = child.getEndTemp();
				MoveIR movField = new MoveIR(new MemIR(new OpIR(opEnum.PLUS, obj, new ConstIR(fieldOffset.get(child)))), decTemp);
				seq.addChild(movField);
				offsetMeasure[i] = offsetCount;
				offsetCount += fieldOffset.get(child);
				i++;
			}*/
			//move the DV into the first slot of the memory allocated
			//to the Class
			SyntaxIR dispatchVector = thisClass.getDV(map);
			/*ArrayList<SyntaxIR> extraDVs = thisClass.getParentDV();
			i = 0;
			for(SyntaxIR extraDV : extraDVs) {
				seq.addChild(new MoveIR(new MemIR(new OpIR(opEnum.PLUS,obj,new ConstIR(offsetMeasure[i]))), extraDV));
				i++;
			}
			HashMap<NameIR, Integer> extendedMethodOffset = thisClass.getMethodOffset();
			*/
			seq.addChild(new MoveIR(new MemIR(obj), dispatchVector));
		}
		for(MemIR thisNode : thisList) {
			seq.addChild(new MoveIR(thisNode, obj));
		}
		seq.addChild(new ExpIR(obj));
		return seq;
	}

	public void setThisClass(ClassNode thisClass) {
		this.thisClass = thisClass;
	}

	public ClassNode getThisClass() {
		return thisClass;
	}

}
