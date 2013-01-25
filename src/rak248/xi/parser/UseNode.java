package rak248.xi.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import edu.cornell.cs.cs4120.xi.Position;

import java_cup.runtime.Symbol;

import rak248.xi.Location;
import rak248.xi.SyntaxIR;
import rak248.xi.SyntaxNode;
import rak248.xi.ir.SeqIR;
import rak248.xi.lexer.JFlexLexer;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.SymbolTableCreator;
import rak248.xi.typeChecker.Type;

public class UseNode extends SyntaxNode {
	ArrayList<String> imports;
	
	public UseNode(){
		//super();
		setPosition(new Location("EmptyUseCompUnit",0,0,0,0));
		imports = new ArrayList<String>();
	}
	
	//needs to update the SymbolTable. fix this.
	public UseNode addUse(String id, Position use_opt, Position use, Position idpos) {
		imports.add(id);
		if(use_opt.columnStart()==0){
			setPosition(new Location(use.unit(),use.lineStart(),idpos.lineEnd(),use.columnStart(),idpos.columnEnd()));
		}else{
			setPosition(new Location(use_opt.unit(),use_opt.lineStart(),idpos.lineEnd(),use_opt.columnStart(),idpos.columnEnd()));
		}
		return this;
	}
	
	public String label() {
		//return Arrays.toString(imports.toArray());
		return imports.toString();
	}
	
	public String toString(){
		String ret = getLabel();
		return ret;
	}
	
	public Type typeCheck(SymbolTable table) {
		return new Type(Type.typeEnum.UNIT);
	}
	
	public SymbolTable SymbolTableSetterGetter(String head) throws Exception {
		setSymbolTable(head);
		return getSymTable();
	}
	
	public void setSymbolTable(String head) throws Exception{
		setSymTable(new SymbolTable());
		for (String source: imports){
			SymbolTableCreator stc = new SymbolTableCreator();
			FileReader fil = new FileReader(head+source + ".ixi");
			BufferedReader bff = new BufferedReader(fil);
			JFlexLexer lexie = new JFlexLexer(source + ".ixi",bff);
			InterfaceParser parserObj = new InterfaceParser(lexie);
			Symbol parTree = parserObj.parse();
			CompUnitNode com = (CompUnitNode) parTree.value;
			//System.out.println("source + head in usenode:"+source+head);
			SymbolTable sym = stc.firstPass(head+source+".xi",com);
			//System.out.println("NEW: " + SymbolTableCreator.classPass(com));
			HashMap<String, HashMap<String, Type>> methodsToAdd = SymbolTableCreator.classPass(com);
			sym.addMethods(SymbolTableCreator.classPass(com));
			getSymTable().add(sym);
		}
	}

}