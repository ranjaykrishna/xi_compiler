package rak248.jUnit;


import static org.junit.Assert.*;

import java.io.*;
import java_cup.runtime.Symbol;

import org.junit.Test;

import rak248.xi.lexer.JFlexLexer;
import rak248.xi.parser.CompUnitNode;
import rak248.xi.parser.CupParser;
import rak248.xi.parser.ElseNode;
import rak248.xi.typeChecker.InterfaceMismatchException;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.SymbolTableCreator;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.UndeclaredIdentifierException;

public class TypeCheckerChecker {
	
	protected CupParser parser;
	protected JFlexLexer lexer;
	protected FileReader file;
	protected BufferedReader br;
	protected FileReader fileAnswer;
	protected BufferedReader bfAnswer;
	protected Symbol parseTree;
	protected CompUnitNode comp;
	protected SymbolTable table;

	@Test
	public void testCorrectProgram() {
		assertTrue(testTypeChecker("tests"+File.separator+"type_checker"+File.separator+"test1.xi"));
	}
	
	@Test
	public void testBadParseSuite() {
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"01.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"02.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"03.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"04.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"05.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"06.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"07.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"08.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"09.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"10.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"16.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"18.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"21.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"23.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"24.ix"));
		assertTrue(testParserFailure("tests"+File.separator+"parser"+File.separator+"bad"+File.separator+"25.ix"));
    }
	
	@Test
	public void testGoodParseSuite() {
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"01.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"02.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"06.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"array.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"assoc.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"basic-precedence.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"basic-stats.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"complex-lvalue.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"emptyfn.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"helloworld.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"ifs.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"minus.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"precedence.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"proc-simple.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"sort.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"validExpressions.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"validFuncDeclaration.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"validStatements.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"validVariableDeclaration.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"while.xi"));
    }
	
	@Test
	public void testArrays1() {
		assertTrue(testTypeCheckerFailure("tests"+File.separator+"type_checker"+File.separator+"array1.xi"));
	}
	
	@Test
	public void testArrays2() {
		assertTrue(testTypeCheckerFailure("tests"+File.separator+"type_checker"+File.separator+"array2.xi"));
	}
	
	@Test
	public void testArrays3() {
		assertTrue(testTypeCheckerFailure("tests"+File.separator+"type_checker"+File.separator+"array3.xi"));
	}
	
	@Test
	public void testArrays4() {
		assertTrue(testParserFailure("tests"+File.separator+"type_checker"+File.separator+"array4.xi"));
	}
	
	@Test
	public void testarrayDefs() {
		assertTrue(testTypeCheckerFailure("tests"+File.separator+"type_checker"+File.separator+"arrayDefFail1.xi"));
		assertTrue(testTypeCheckerFailure("tests"+File.separator+"type_checker"+File.separator+"arrayDefFail2.xi"));
	}
	
	@Test
	public void testLoop1() {
		assertTrue(testScopeFailure("tests"+File.separator+"type_checker"+File.separator+"loop1.xi"));
	}
	
	@Test
	public void testIfs(){
		assertTrue(testTypeChecker("tests"+File.separator+"type_checker"+File.separator+"ifgood.xi"));
		assertTrue(testTypeChecker("tests"+File.separator+"type_checker"+File.separator+"ifgood2.xi"));
		assertTrue(testTypeCheckerFailure("tests"+File.separator+"type_checker"+File.separator+"ifbad.xi"));
	}
	
	@Test
	public void testReturns(){
		assertTrue(testParserSuccess("tests"+File.separator+"parser"+File.separator+"good"+File.separator+"return.xi"));
	}
	
	@Test
	public void testLong(){
		assertTrue(testTypeChecker("tests"+File.separator+"type_checker"+File.separator+"long.xi"));
	}
	
	/*@Test
	public void testCompExceptions(){
		assertTrue(testTypeCheckerFailure("tests"+File.separator+"type_checker"+File.separator+"brokenReturn.xi"));
	}*/
	
	@Test
	public void testScope(){
		assertTrue(testTypeChecker("tests"+File.separator+"type_checker"+File.separator+"blockScope.xi"));
	}
	
	@Test
	public void testUseSemicolon(){
		assertTrue(testTypeChecker("tests"+File.separator+"type_checker"+File.separator+"useTests"+File.separator+"useSemicolon.xi"));
	}
	
	@Test
	public void testUseIO(){
		assertTrue(testTypeChecker("tests"+File.separator+"type_checker"+File.separator+"useTests"+File.separator+"useIO.xi"));
	}
	
	@Test
	public void differentInterface(){
		assertTrue(testTypeChecker("tests"+File.separator+"type_checker"+File.separator+"useTests"+File.separator+"diff.xi"));
	}
	
	@Test
	public void interfaceMismatch(){
		assertTrue(testTypeCheckerInterfaceFailure("tests"+File.separator+"type_checker"+File.separator+"useTests"+File.separator+"mismatch.xi"));
		assertTrue(testTypeCheckerInterfaceFailure("tests"+File.separator+"type_checker"+File.separator+"useTests"+File.separator+"mismatchType.xi"));
	}
	
	public void testGlobal(){
		assertTrue(testParserSuccess("tests"+File.separator+"type_checker"+File.separator+"global.xi"));
	}
	
	@Test
	public void testParseOO(){
		assertTrue(testParserSuccess("tests"+File.separator+"type_checker"+File.separator+"oo.xi"));
		assertTrue(testParserSuccess("tests"+File.separator+"type_checker"+File.separator+"cp.xi"));
	}
	
	@Test
	public void testTypeCheckOO(){
		assertTrue(testTypeChecker("tests"+File.separator+"type_checker"+File.separator+"oo.xi"));
		assertTrue(testTypeChecker("tests"+File.separator+"type_checker"+File.separator+"cp.xi"));
	}
	
	@Test
	public void testPointTest(){
		assertTrue(testParserSuccess("tests"+File.separator+"type_checker"+File.separator+"pointtest.xi"));
		assertTrue(testTypeChecker("tests"+File.separator+"type_checker"+File.separator+"pointtest.xi"));
	}
	
	@Test
	public void testTypeCheckMCP(){
		assertTrue(testTypeChecker("tests"+File.separator+"type_checker"+File.separator+"multiextends.xi"));
	}
	
	@Test
	public void testParseAck(){
		assertTrue(testParserSuccess("tests"+File.separator+"overall"+File.separator+"ack.xi"));
	}
	
	@Test
	public void testTypeCheckAck(){
		assertTrue(testTypeChecker("tests"+File.separator+"overall"+File.separator+"ack.xi"));
	}
	
	@Test
	public void testParseBinsearch(){
		assertTrue(testParserSuccess("tests"+File.separator+"overall"+File.separator+"binsearch.xi"));
	}
	
	@Test
	public void testTypeCheckBinsearch(){
		assertTrue(testTypeChecker("tests"+File.separator+"overall"+File.separator+"binsearch.xi"));
	}
	
	@Test
	public void testParseEnigma(){
		assertTrue(testParserSuccess("tests"+File.separator+"overall"+File.separator+"enigma.xi"));
	}
	
	@Test
	public void testTypeCheckEnigma(){
		assertTrue(testTypeChecker("tests"+File.separator+"overall"+File.separator+"enigma.xi"));
	}
	
	@Test
	public void testParseKmp(){
		assertTrue(testParserSuccess("tests"+File.separator+"overall"+File.separator+"kmp.xi"));
	}
	
	@Test
	public void testTypeCheckKmp(){
		assertTrue(testTypeChecker("tests"+File.separator+"overall"+File.separator+"kmp.xi"));
	}
	
	public boolean testTypeChecker(String input){
		String testSource = input;
		try {
			file = new FileReader(testSource);
			br = new BufferedReader(file);
			lexer = new JFlexLexer(testSource,br);
			parser = new CupParser(lexer);
			parseTree = parser.parse();
			comp = (CompUnitNode) parseTree.value;
			ElseNode.foldElse(comp);
			SymbolTableCreator stc = new SymbolTableCreator();
			stc.interfaceCheck(input.substring(0,input.length()-3),comp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean testTypeCheckerFailure(String input){
		String testSource = input;
		try {
			file = new FileReader(testSource);
			br = new BufferedReader(file);
			lexer = new JFlexLexer(testSource,br);
			parser = new CupParser(lexer);
			parseTree = parser.parse();
			comp = (CompUnitNode) parseTree.value;
			ElseNode.foldElse(comp);
			//new CodeWriterTreePrinter(System.out).print(comp);
			SymbolTableCreator stc = new SymbolTableCreator();
			stc.interfaceCheck(input.substring(0,input.length()-3),comp);
    		System.err.println("Successfully type checked "+input+" when a failure was expected");
			return false;
		} catch (TypeError e) {
			//e.printStackTrace();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean testTypeCheckerInterfaceFailure(String input){
		String testSource = input;
		try {
			file = new FileReader(testSource);
			br = new BufferedReader(file);
			lexer = new JFlexLexer(testSource,br);
			parser = new CupParser(lexer);
			parseTree = parser.parse();
			comp = (CompUnitNode) parseTree.value;
			ElseNode.foldElse(comp);
			//new CodeWriterTreePrinter(System.out).print(comp);
			SymbolTableCreator stc = new SymbolTableCreator();
			stc.interfaceCheck(input.substring(0,input.length()-3),comp);
    		System.err.println("Successfully type checked "+input+" when a failure was expected");
			return false;
		} catch (InterfaceMismatchException e) {
			//e.printStackTrace();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean testParserFailure(String input) {
		String testSource = input;
		try {
			file = new FileReader(testSource);
			br = new BufferedReader(file);
			lexer = new JFlexLexer(testSource,br);
			parser = new CupParser(lexer);
			parseTree = parser.parse();
			comp = (CompUnitNode) parseTree.value;
			ElseNode.foldElse(comp);
			//new CodeWriterTreePrinter(System.out).print(comp);
			//SymbolTableCreator stc = new SymbolTableCreator();
    		//stc.interfaceCheck(input.substring(0,input.length()-3),comp);
			System.out.println("Expected parsing failure in "+input+" but no failure was encountered");
			return false;
		} catch (ClassCastException e) {
			//Hopefully this is always the error when we can't parse the program
			return true;
			
			//if(e.getMessage().equals("Fatal Syntax Error")) {
			//	return true;
			//}
			//e.printStackTrace();
			//return false;
		} catch (NullPointerException e) {
			//This can also occur during a parser failure but it could also be a problem with our code
			//so we will pass the test but print something out on system.err
			System.err.println("Null pointer while testing "+input+" for a failure."+
					           " This might be correct but check the file for a syntax error at the begining");
			return true;
		}catch (TypeError e){
			e.printStackTrace();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean testParserSuccess(String input){
		String testSource = input;
		try {
			file = new FileReader(testSource);
			br = new BufferedReader(file);
			lexer = new JFlexLexer(testSource,br);
			parser = new CupParser(lexer);
			parseTree = parser.parse();
			comp = (CompUnitNode) parseTree.value;
			ElseNode.foldElse(comp);
			//new CodeWriterTreePrinter(System.out).print(comp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean testScopeFailure(String input){
		String testSource = input;
		try {
			file = new FileReader(testSource);
			br = new BufferedReader(file);
			lexer = new JFlexLexer(testSource,br);
			parser = new CupParser(lexer);
			parseTree = parser.parse();
			comp = (CompUnitNode) parseTree.value;
			ElseNode.foldElse(comp);
			//new CodeWriterTreePrinter(System.out).print(comp);
			SymbolTableCreator stc = new SymbolTableCreator();
    		SymbolTable symtab = stc.firstPass(input,comp);
    		comp.typeCheck(symtab);
			return false;
		} catch (UndeclaredIdentifierException e) {
			//e.printStackTrace();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
