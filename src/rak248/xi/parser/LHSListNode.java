package rak248.xi.parser;

import java.util.ArrayList;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;
import rak248.xi.typeChecker.Type.typeEnum;


public class LHSListNode extends SyntaxNode{

	public LHSListNode(String id, Type t, Position position, Position position2) {
		addLHS(id,t,position,position2);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		//setLabel("LHS:"+id);
		setLabel("LHS List");
	}

	public LHSListNode(Position position, Position position2) {
		this(position, position2, true);
	}
	
	public LHSListNode(Position position, Position position2, boolean addUnderscore) {
		if(addUnderscore) {
			addLHS(position,position2);
		}
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		//setLabel("LHS:Underscore");
		setLabel("LHS List");
	}

	public LHSListNode(String id, Position idleft, Position idright) {
		addLHS(id,idleft,idright);
		Position newPos = new Location(idleft.unit(),idleft.lineStart(),idright.lineEnd(),idleft.columnStart(),idright.columnEnd());
		setPosition(newPos);
		//setLabel("LHS:"+id);
		setLabel("LHS List");
	}

	public LHSListNode(ArrayLookUpNode a, Position aleft, Position aright) {
		addLHS(a,aleft,aright);
		Position newPos = new Location(aleft.unit(),aleft.lineStart(),aright.lineEnd(),aleft.columnStart(),aright.columnEnd());
		setPosition(newPos);
		//setLabel("LHS: array assignment");
		setLabel("LHS List");
	}

	public LHSListNode addLHS(String id, Position position, Position position2) {
		addChild(new LHSNode(id,position,position2));
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		return this;
	}

	public LHSListNode addLHS(String id, Type t, Position position, Position position2) {
		addChild(new LHSNode(id,t,position,position2));
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		return this;
	}

/*	public LHSListNode addLHS() {
		addChild(new LHSNode());
		return this;
	}*/

	public LHSListNode addLHS(ArrayLookUpNode b, Position position, Position position2) {
		addChild(new LHSNode(b,position,position2));
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		return this;
	}
	
	public LHSListNode addLHS(Position position, Position position2) {
		addChild(new LHSNode(position,position2));
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
		return this;
	}
	
	public Type typeCheck(SymbolTable table) throws UndeclaredIdentifierException, TypeError {
		setSymTable(table);
		ArrayList<VisualizableTreeNode> children = getChildren();
		Type[] typeList = new Type[children.size()];
		if(children.size() == 1) {
			VisualizableTreeNode c = children.get(0);
			if(c instanceof AccessorNode) {
				return ((AccessorNode) c).typeCheck(table);
			}
			Type temp = ((LHSNode) c).typeCheck(table);
			return temp;
		}
		else {
			for(int i = 0; i < children.size(); i++) {
				//System.out.println(children.get(i));
				VisualizableTreeNode c = children.get(i);
				if(c instanceof AccessorNode) {
					typeList[i] = ((AccessorNode) c).typeCheck(table);
				} else {
					LHSNode child = (LHSNode) c;
					typeList[i] = child.typeCheck(table);
				}
			}
			return new Type(typeEnum.TUPLE, typeList);
		}
	}
	


}