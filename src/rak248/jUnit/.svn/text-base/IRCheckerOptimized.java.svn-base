package rak248.jUnit;

import static org.junit.Assert.*;
import java.io.*;
import java_cup.runtime.Symbol;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import rak248.Driver;
import rak248.testing.Interpreter;
import rak248.testing.InterpreterException;
import rak248.testing.JumpException;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.Optimize;
import rak248.xi.lexer.JFlexLexer;
import rak248.xi.parser.CompUnitNode;
import rak248.xi.parser.CupParser;
import rak248.xi.typeChecker.SymbolTableCreator;
import rak248.xi.typeChecker.TypeError;

public class IRCheckerOptimized {
	
	@Test
	public void testBasic(){
		assertEquals(new Long(0),testPass("tests"+File.separator+"IRtests"+File.separator+"basic.xi"));
	}
	
	@Test
	public void testFunc(){
		assertEquals(new Long(11),testPass("tests"+File.separator+"IRtests"+File.separator+"func.xi"));
	}
	
	@Test
	public void testFactorial(){
		assertEquals(new Long(3628800),testPass("tests"+File.separator+"IRtests"+File.separator+"factorial.xi"));
	}
	
	@Test
	public void testFibonacci(){
		assertEquals(new Long(610),testPass("tests"+File.separator+"IRtests"+File.separator+"fibonacci.xi"));
	}
	
	@Test
	public void testWhile(){
		assertEquals(new Long(100),testPass("tests"+File.separator+"IRtests"+File.separator+"while.xi"));
	}
	
	@Test
	public void testConditional(){
		assertEquals(new Long(1),testPass("tests"+File.separator+"IRtests"+File.separator+"trueConditional.xi"));
		assertEquals(new Long(0),testPass("tests"+File.separator+"IRtests"+File.separator+"falseConditional.xi"));
		assertEquals(new Long(99),testPass("tests"+File.separator+"IRtests"+File.separator+"halfConditional.xi"));
	}
	
	@Test
	public void testConst(){
		assertEquals(new Long(362880),testPass("tests"+File.separator+"IRtests"+File.separator+"const.xi"));
	}
	
	@Test
	public void testShortCircuit(){
		assertEquals(new Long(1),testPass("tests"+File.separator+"pa4fixes"+File.separator+"shortcircuit.xi"));
		assertEquals(new Long(0),testPass("tests"+File.separator+"pa4fixes"+File.separator+"shortcircuit2.xi"));
		assertEquals(new Long(0),testPass("tests"+File.separator+"pa4fixes"+File.separator+"shortcircuit3.xi"));
	}
	
	@Test
	public void testIndexOutOfBounds(){
		assertEquals(new Long(700),testPass("tests"+File.separator+"pa4fixes"+File.separator+"outOfBounds"+File.separator+"outOfBounds.xi"));
		assertEquals(new Long(700),testPass("tests"+File.separator+"pa4fixes"+File.separator+"outOfBounds"+File.separator+"outOfBounds2.xi"));
		assertEquals(new Long(700),testPass("tests"+File.separator+"pa4fixes"+File.separator+"outOfBounds"+File.separator+"outOfBounds3.xi"));
	}
	
	@Test
	public void testAppendingArrays(){
		assertEquals(new Long(8),testPass("tests"+File.separator+"pa4fixes"+File.separator+"appendingArrays.xi"));
	}
	
	@Test
	public void testAppendingStrings(){
		assertEquals(new Long(227),testPass("tests"+File.separator+"pa4fixes"+File.separator+"appendingStrings.xi"));
	}
	
	public Long testPass(String input){
		Driver.initForJUnit();
		try {
			return Driver.helper(input,"",true);
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
			return 70l;
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 700l;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 800l;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 9000l;
		}
	}
}

