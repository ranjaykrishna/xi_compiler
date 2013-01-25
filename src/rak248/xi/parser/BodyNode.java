package rak248.xi.parser;

import edu.cornell.cs.cs4120.xi.Position;
import rak248.xi.Location;
import rak248.xi.SyntaxNode;

public class BodyNode extends SyntaxNode{

	public BodyNode(ClassNode c) {
		super();
		addChild(c);
		this.setPosition(c.position());
		setLabel("BODY");
	}

	public BodyNode(FunctionsNode c) {
		super();
		addChild(c);
		this.setPosition(c.position());
		setLabel("BODY");
	}

	public BodyNode(StatementNode f) {
		addChild(f);
		//update end position
		//Position newPos = new Location(position().unit(), position().lineStart(), f.position().lineEnd(), position().columnStart(), f.position().columnEnd());
		this.setPosition(f.position());
	}

	public void add(FunctionsNode f) {
		addChild(f);
		//update end position
		Position newPos = new Location(position().unit(), position().lineStart(), f.position().lineEnd(), position().columnStart(), f.position().columnEnd());
		this.setPosition(newPos);
	}

	public void add(ClassNode c) {
		addChild(c);
		//update end position
		Position newPos = new Location(position().unit(), position().lineStart(), c.position().lineEnd(), position().columnStart(), c.position().columnEnd());
		this.setPosition(newPos);
	}

	public void add(StatementNode c) {
		addChild(c);
		//update end position
		Position newPos = new Location(position().unit(), position().lineStart(), c.position().lineEnd(), position().columnStart(), c.position().columnEnd());
		this.setPosition(newPos);
	}

}
