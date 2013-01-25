package rak248.xi.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import java_cup.runtime.Symbol;

import edu.cornell.cs.cs4120.xi.Position;
import rak248.xi.Location;
import rak248.xi.SyntaxNode;
import rak248.xi.lexer.JFlexLexer;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.SymbolTableCreator;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.Type.typeEnum;

public class ExtendsNode extends SyntaxNode {
	
	public ArrayList<String> classes;
	
	public ExtendsNode(ArrayList<String> d, Position position) {
		Position newPos = new Location(position.unit(),position.lineStart(),position.lineEnd(),position.columnStart(),position.columnEnd());
		setPosition(newPos);
		classes = d;
		String s = "";
		for (String n: d){
			s += n + ", ";
		}
		setLabel("Extends: "+s);
	}
	
	public String getClassName() {
		if(classes == null || classes.size() == 0) {
			return null;
		}
		if(classes.size() > 1) {
			System.err.println("Multiple inheritance not yet supported; only considering first class");
		}
		return classes.get(0);
	}

	public ExtendsNode() {
		classes = new ArrayList<String>();
		setLabel("Extends Nothing");
	}
	
	public ArrayList<String> getClasses() {
		return classes;
	}

}
