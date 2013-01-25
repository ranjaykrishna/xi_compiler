package rak248.xi.parser;

import java.util.List;
import java.util.Stack;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.Position;
import rak248.Driver;
import rak248.xi.Location;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.LabelIR;
import rak248.xi.typeChecker.TypeError;

public class ElseNode extends ConditionNode {

	private StatementNode s;
	
	public ElseNode(StatementNode c, Position position, Position position2) {
		s = c;
		addChild(c);
		Position newPos = new Location(position.unit(),position.lineStart(),position2.lineEnd(),position.columnStart(),position2.columnEnd());
		setPosition(newPos);
	}
	
	public static void old_rep_foldElse(SyntaxNode node) throws TypeError {
		for(int i = ((List<VisualizableTreeNode>) node.getChildren()).size() - 1; i >= 0; i--) {
			SyntaxNode child = (SyntaxNode) ((List<VisualizableTreeNode>) node.getChildren()).get(i);
			foldElse(child);
			if(child instanceof ElseNode) {
				SyntaxNode ifNode = (SyntaxNode) ((List<VisualizableTreeNode>) node.getChildren()).get(i - 1);
				if(ifNode instanceof ElseNode) {
					SyntaxNode lastChild = (SyntaxNode) ifNode.getChildren().get(ifNode.getChildren().size() - 1);
					if(lastChild instanceof ConditionNode) {
						node.getChildren().remove(i);
						lastChild.addChild(((ElseNode) child).getStatementNode());
					} else {
						String message = "TypeError: unmatched else at " + ifNode.position();
						throw new TypeError(message);
					}
				}
				else if(ifNode instanceof ConditionNode) {
					node.getChildren().remove(i);
					ifNode.addChild(((ElseNode) child).getStatementNode());
				} else {
					String message = "TypeError: unmatched else at " + ifNode.position();
					throw new TypeError(message);
				}
			}
		}
	}
	
	public static void RfoldElse(SyntaxNode node) throws TypeError {
		Stack<ElseNode> es = new Stack<ElseNode>();
		for(int i = ((List<VisualizableTreeNode>) node.getChildren()).size() - 1; i >= 0; i--) {
			SyntaxNode child = (SyntaxNode) ((List<VisualizableTreeNode>) node.getChildren()).get(i);
			foldElse(child);
			if(child instanceof ElseNode) {
				node.getChildren().remove(i);
				es.push((ElseNode) child);
			}
			else if(child instanceof ConditionNode) {
				if(!es.empty()) {
					node.addChild(es.pop().getStatementNode()); 
				} else {
					
				}
			}
		}
		if(!es.empty()) {
			String message = "TypeError: unmatched else";
			throw new TypeError(message);
		}
	}
	
	
	public static void foldElse(SyntaxNode node) throws TypeError {
		for(int i = ((List<VisualizableTreeNode>) node.getChildren()).size() - 1; i >= 0; i--) {
			SyntaxNode child = (SyntaxNode) ((List<VisualizableTreeNode>) node.getChildren()).get(i);
			foldElse(child);
			if(child instanceof ElseNode) {
				SyntaxNode prevSibling = (SyntaxNode) ((List<VisualizableTreeNode>) node.getChildren()).get(i - 1);
				if(prevSibling instanceof ElseNode) {
					prevSibling = (SyntaxNode) prevSibling.getChildren().get(0);
				}
				try {
					addElse((ConditionNode) prevSibling, (ElseNode) child);
					node.getChildren().remove(i);
				} catch(Exception e) {
					String message = "TypeError: unmatched else at " + child.position();
					throw new TypeError(message);
				}
			}
		}
	}
	
	public static void addElse(ConditionNode parent, ElseNode e) {
		if(parent.getFirstStatement() instanceof ConditionNode) {
			addElse((ConditionNode) parent.getFirstStatement(), e);
		} else {
			parent.addChild(e.getStatementNode());
		}
	}
	
	public StatementNode getStatementNode() {
		return s;
	}

}
