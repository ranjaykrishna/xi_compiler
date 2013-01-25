package rak248.jUnit;

import static org.junit.Assert.*;
import java.io.*;
import java_cup.runtime.Symbol;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


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

public class IRChecker {
	
	@Test
	public void testBasic(){
		assertEquals(0,testPass("tests"+File.separator+"IRtests"+File.separator+"basic.xi"));
	}
	
	@Test
	public void testFunc(){
		assertEquals(11,testPass("tests"+File.separator+"IRtests"+File.separator+"func.xi"));
	}
	
	@Test
	public void testFactorial(){
		assertEquals(3628800,testPass("tests"+File.separator+"IRtests"+File.separator+"factorial.xi"));
	}
	
	@Test
	public void testFibonacci(){
		assertEquals(610,testPass("tests"+File.separator+"IRtests"+File.separator+"fibonacci.xi"));
	}
	
	@Test
	public void testWhile(){
		assertEquals(100,testPass("tests"+File.separator+"IRtests"+File.separator+"while.xi"));
	}
	
	@Test
	public void testConditional(){
		assertEquals(1,testPass("tests"+File.separator+"IRtests"+File.separator+"trueConditional.xi"));
		assertEquals(0,testPass("tests"+File.separator+"IRtests"+File.separator+"falseConditional.xi"));
		assertEquals(99,testPass("tests"+File.separator+"IRtests"+File.separator+"halfConditional.xi"));
	}
	
	@Test
	public void testConst(){
		assertEquals(362880,testPass("tests"+File.separator+"IRtests"+File.separator+"const.xi"));
	}
	
	@Test
	public void testShortCircuit(){
		assertEquals(1,testPass("tests"+File.separator+"pa4fixes"+File.separator+"shortcircuit.xi"));
		assertEquals(0,testPass("tests"+File.separator+"pa4fixes"+File.separator+"shortcircuit2.xi"));
		assertEquals(0,testPass("tests"+File.separator+"pa4fixes"+File.separator+"shortcircuit3.xi"));
	}
	
	@Test
	public void testIndexOutOfBounds(){
		assertEquals(700,testPass("tests"+File.separator+"pa4fixes"+File.separator+"outOfBounds"+File.separator+"outOfBounds.xi"));
		assertEquals(700,testPass("tests"+File.separator+"pa4fixes"+File.separator+"outOfBounds"+File.separator+"outOfBounds2.xi"));
		assertEquals(700,testPass("tests"+File.separator+"pa4fixes"+File.separator+"outOfBounds"+File.separator+"outOfBounds3.xi"));
	}
	
	@Test
	public void testAppendingArrays(){
		assertEquals(8,testPass("tests"+File.separator+"pa4fixes"+File.separator+"appendingArrays.xi"));
	}
	
	@Test
	public void testAppendingStrings(){
		assertEquals(227,testPass("tests"+File.separator+"pa4fixes"+File.separator+"appendingStrings.xi"));
	}
	
	public int testPass(String input){
		try {
			FileReader file = new FileReader(input);
			BufferedReader br = new BufferedReader(file);
			JFlexLexer lexer = new JFlexLexer(input,br);
			CupParser parser = new CupParser(lexer);
			Symbol parseTree = parser.parse();
			//System.out.println(parseTree);
			CompUnitNode comp = (CompUnitNode) parseTree.value;
			SymbolTableCreator stc = new SymbolTableCreator();
			if (stc.interfaceCheck(input.substring(0,input.length()-3),comp)){
				SyntaxIR seq = comp.translate(null);
				//seq = seq.foldCALL();
				seq = seq.foldESEQ();
				Optimize.foldConst(seq);
				Optimize.foldSEQ(seq);
				//Optimize.foldCJump(seq);
				Interpreter lir = new Interpreter(seq);
				Long returnVariable = lir.runProgram();
				return returnVariable.intValue();
			}
			else{
				throw new RuntimeException("interface check failed");
			}
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
			return 70;
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 700;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 800;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 9000;
		}
	}
}
