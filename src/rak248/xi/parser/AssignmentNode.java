package rak248.xi.parser;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;

import rak248.util.VisualizableIRNode;
import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.CallIR;
import rak248.xi.ir.ConstIR;
import rak248.xi.ir.EseqIR;
import rak248.xi.ir.ExpIR;
import rak248.xi.ir.ExpressionIR;
import rak248.xi.ir.MemIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.OpIR;
import rak248.xi.ir.SeqIR;
import rak248.xi.ir.OpIR.opEnum;
import rak248.xi.ir.TempIR;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;

public class AssignmentNode extends StatementNode{

	private LHSListNode a;
	private ExpressionNode e;
	private Type LHStype;
	private Type Exprtype;
	private Type[] LHSListTypes;
	private Type[] ExprListTypes;
	
	//normal assignments
	public AssignmentNode(LHSListNode a, ExpressionNode e, Position position, Position position2) {
		addChild(a);
		addChild(e);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		setLabel("Assignment");
		this.a = a;
		this.e = e;
	}
	
	//Class Assignments
	public AssignmentNode(ArrayList<String> id, Type t, ExpressionNode a2,
			Position position) {
		if (id.size() != 1){
			System.err.println("Compiler doesnt support multiple assignments");
		}
		String child = id.get(0);
		a = new LHSListNode(child, t, position, position);
		e = a2;
		Position newPos = new Location(position.unit(),position.lineStart(),position.lineEnd(),position.columnStart(),position.columnEnd());
		setPosition(newPos);
		setLabel("Class Assignment");
		addChild(a);
		addChild(a2);
	}

	public Type typeCheck(SymbolTable table) throws UndeclaredIdentifierException, TypeError {
		setSymTable(table);
		Exprtype = ((ExpressionNode)getChildren().get(1)).typeCheck(table);
		LHStype = ((LHSListNode)getChildren().get(0)).typeCheck(table);
		if(LHStype.isTuple() && Exprtype.isTuple()) {
			LHSListTypes = LHStype.getTupleList();
			ExprListTypes = Exprtype.getTupleList();
			if(LHSListTypes.length != ExprListTypes.length) {
				String message = "Different number of arguments being assigned.";
				message += " Occurred at position: " + position();
				throw new TypeError(message);
			}
			else {
				for(int i = 0; i < LHSListTypes.length; i ++) {
					if(!LHSListTypes[i].equals(ExprListTypes[i])) {
						if(!LHSListTypes[i].isUnderscore()) {
							String message = LHSListTypes[i] + " expected when " + ExprListTypes + " was used.";
							message += "Occurred at position: " + position();
							throw new TypeError(message);
						}
					}
				}
				return new Type(typeEnum.UNIT);
			}
		}
		else if(!LHStype.equals(Exprtype) && LHStype.isArray() && (((ExpressionNode) getChildren().get(1)).getParFlag() == true)) {
			if(LHStype.isIntArray() && Exprtype.isBool()) {
				String message = LHStype + " expected when " + Exprtype + " was used.";
				message += " Occurred at position: " + position();
				throw new TypeError(message);
			}
			if(LHStype.isBoolArray() && Exprtype.isInt()) {
				String message = LHStype + " expected when " + Exprtype + " was used.";
				message += " Occurred at position: " + position();
				throw new TypeError(message);
			}
			ArrayList<ExpressionNode> el = new ArrayList<ExpressionNode>();
			el.add((ExpressionNode) getChildren().get(1));
			ListNode ln = new ListNode(el, ((SyntaxNode) getChildren().get(1)).position(),  ((SyntaxNode) getChildren().get(1)).position());
			getChildren().set(1, ln);
			Exprtype = ((SyntaxNode) getChildren().get(1)).typeCheck(table);
			return new Type(typeEnum.UNIT);
		}
		else if (!LHStype.equals(Exprtype)) {
			if(LHStype.isUnderscore()) {
				return new Type(typeEnum.UNIT);
			}
			String message = LHStype + " expected when " + Exprtype + " was used.";
			message += " Occurred at position: " + position();
			throw new TypeError(message);
		}
		else {
			return new Type(typeEnum.UNIT);
		}
	}

	
	public SyntaxIR translate(HashMap<String, String> map) {
		ArrayList<VisualizableTreeNode> LHSNodes = ((LHSListNode)getChildren().get(0)).getChildren();
		SyntaxIR e2 = ((ExpressionNode)getChildren().get(1)).translate(map);
		//if the lhs and rhs are the same, return an empty sequence
		if(LHSNodes.size() == 1) {
			SyntaxIR lhs = (SyntaxIR) (((SyntaxNode) LHSNodes.get(0)).translate(map));
			//System.out.println("lhs:"+lhs);
			//System.out.println(lhs.prettyPrint()+" and "+e2.prettyPrint());
			if(lhs.equals(e2)) {
				SeqIR ret = new SeqIR();
				return ret;
			}
		}
		for(VisualizableTreeNode nodeq : LHSNodes) {
			LHSNode node = (LHSNode) nodeq;
			//System.out.println(node.label());
			String name = node.label();
			int index = name.lastIndexOf(":");
			String shortName = name.substring(index+1,name.length());
			//System.out.println(shortName);
			if(shortName.equals("int") || shortName.equals("bool")) {
				
			}
		}
		if(e2 instanceof CallIR || e2 instanceof EseqIR) {
			if (LHSNodes.size() == 1){
				SyntaxNode out = (SyntaxNode)LHSNodes.get(0);
				if (out.getLabel().equals("underscore")){
					return new ExpIR((ExpressionIR) e2);
				}
				else{
					SeqIR result = new SeqIR();
					ExpressionIR LHS = (ExpressionIR) out.translate(map);
					//System.out.println("allocating space for LHS");
					//result.addChild(new MoveIR(LHS, new ConstIR(0)));
					for(VisualizableTreeNode nodeq : LHSNodes) {
						LHSNode node = (LHSNode) nodeq;
						String name = node.label();
						int index = name.lastIndexOf(":");
						String shortName = name.substring(index+1,name.length());
						if(shortName.equals("int") || shortName.equals("bool")) {
							//result.addChild(new MoveIR(LHS, new ConstIR(0)));
						}
						if(shortName.startsWith("int[") || shortName.startsWith("bool[")) {
							//result.addChild(new MoveIR(LHS, new ConstIR(0)));
						}
					}
					result.addChild(new MoveIR(LHS, e2));
					return result;
				}
			}
			else{
				SeqIR result = new SeqIR();
				result.addChild(new ExpIR((ExpressionIR) e2));
				for(int i = 0; i < LHSNodes.size(); i ++) {
					SyntaxNode out = (SyntaxNode)LHSNodes.get(i);
					if (out.getLabel().equals("underscore")){
						result.addIR(new ExpIR(new TempIR("_ret"+i,"_ret"+i)));
					}
					else{
						ExpressionIR LHS = (ExpressionIR) out.translate(map);
						if(LHS != null) {
							for(VisualizableTreeNode nodeq : LHSNodes) {
								LHSNode node = (LHSNode) nodeq;
								String name = node.label();
								int index = name.lastIndexOf(":");
								String shortName = name.substring(index+1,name.length());
								if(shortName.equals("int") || shortName.equals("bool")) {
									//result.addChild(new MoveIR(LHS, new ConstIR(0)));
								}
								if(shortName.startsWith("int[") || shortName.startsWith("bool[")) {
									//result.addChild(new MoveIR(LHS, new ConstIR(0)));
								}
							}
							MoveIR temp = new MoveIR(LHS, new TempIR("_ret"+i,"_ret"+i));
							result.addIR(temp);
						}
					}
				}
				return result;
			}
			
		}
		else if (getChildren().get(1) instanceof ListNode){
			SeqIR s = new SeqIR();
			s.addChild(new MoveIR(new MemIR(new OpIR(opEnum.MINUS, (ExpressionIR) e2,new ConstIR(8))), new ConstIR(((ListNode)getChildren().get(1)).subList.size())));
			SyntaxNode out = (SyntaxNode) LHSNodes.get(0);
			if (out.getLabel().equals("underscore")){
				s.addChild(new ExpIR((ExpressionIR) e2));
				return s;
			}
			else{
				for(VisualizableTreeNode nodeq : LHSNodes) {
					LHSNode node = (LHSNode) nodeq;
					String name = node.label();
					int index = name.lastIndexOf(":");
					String shortName = name.substring(index+1,name.length());
					if(shortName.equals("int") || shortName.equals("bool")) {
						//s.addChild(new MoveIR((ExpressionIR) out.translate(map), new ConstIR(0)));
					}
					if(shortName.startsWith("int[") || shortName.startsWith("bool[")) {
						//s.addChild(new MoveIR((ExpressionIR) out.translate(map), new ConstIR(0)));
					}
				}
				s.addChild(new MoveIR((ExpressionIR) ((SyntaxNode) out).translate(map) ,e2));
				return s;
			}
		}
		else {
			SyntaxNode out = (SyntaxNode)LHSNodes.get(0);
			if (out.getLabel().equals("underscore")){
				return new ExpIR((ExpressionIR) e2);
			}
			else{
				ExpressionIR LHS = (ExpressionIR)out.translate(map);
				SeqIR result = new SeqIR();
				for(VisualizableTreeNode nodeq : LHSNodes) {
					LHSNode node = (LHSNode) nodeq;
					String name = node.label();
					int index = name.lastIndexOf(":");
					String shortName = name.substring(index+1,name.length());
					if(shortName.equals("int") || shortName.equals("bool")) {
						//result.addChild(new MoveIR(LHS, new ConstIR(0)));
					}
					if(shortName.startsWith("int[") || shortName.startsWith("bool[")) {
						//result.addChild(new MoveIR(LHS, new ConstIR(0)));
					}
				}
				result.addChild(new MoveIR(LHS, (ExpressionIR)e2));
				return result;
			}
			
		}
	}
	
	public MoveIR addElementArray(ExpressionIR name, int index, ExpressionIR rhs) {
		OpIR lshift = new OpIR(opEnum.LSHIFT, new ConstIR(index), new ConstIR(3));
		OpIR add = new OpIR(opEnum.PLUS, name, lshift);
		MemIR memoryIR = new MemIR(add);
		return new MoveIR(memoryIR, rhs);
	}

}
