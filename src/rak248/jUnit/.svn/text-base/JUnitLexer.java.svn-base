package rak248.jUnit;

import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;

import org.junit.Test;

import rak248.xi.lexer.JFlexLexer;
import rak248.xi.lexer.XiSymbol;
import rak248.xi.parser.CupParser;
import rak248.xi.parser.Sym;

       
public class JUnitLexer {
	protected CupParser parser;
	protected JFlexLexer lexer;
	protected FileReader file;
	protected BufferedReader bf;
	protected FileReader fileAnswer;
	protected BufferedReader bfAnswer;
	String nextTokenAnswer;
	XiSymbol nextTokenSource;
	
	@Test
	public void oneTokenTest() {
		//Actual Result
		String testSource = "tests"+File.separator+"lexer"+File.separator+"test1.txt";
		try {
			file = new FileReader(testSource);
			bf = new BufferedReader(file);
			lexer = new JFlexLexer(testSource, bf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		XiSymbol token1 = new XiSymbol(Sym.IDENTIFIER, "func", 2, 1, 5, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token2 = new XiSymbol(Sym.OPEN_PAREN, "(", 2, 5, 6, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token3 = new XiSymbol(Sym.IDENTIFIER, "a", 2, 6, 7, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token4 = new XiSymbol(Sym.COLON, ":", 2, 7, 8, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token5 = new XiSymbol(Sym.INT, "int", 2, 8, 11, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token6 = new XiSymbol(Sym.CLOSE_PAREN, ")", 2, 11, 12, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token7 = new XiSymbol(Sym.COLON, ":", 2, 12, 13, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token8 = new XiSymbol(Sym.INT, "int", 2, 13, 16, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token9 = new XiSymbol(Sym.OPEN_BRACE, "{", 2, 17, 18, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token10 = new XiSymbol(Sym.IDENTIFIER, "b", 3, 2, 3, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token11 = new XiSymbol(Sym.COLON, ":", 3, 3, 4, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token12 = new XiSymbol(Sym.INT, "int", 3, 4, 7, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token13 = new XiSymbol(Sym.GETS, "=", 3, 8, 9, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token14 = new XiSymbol(Sym.IDENTIFIER, "a", 3, 10, 11, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token15 = new XiSymbol(Sym.RETURN, "return", 4, 2, 8, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token16 = new XiSymbol(Sym.OPEN_PAREN, "(", 4, 8, 9, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token17 = new XiSymbol(Sym.IDENTIFIER, "b", 4, 9, 10, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token18 = new XiSymbol(Sym.CLOSE_PAREN, ")", 4, 10, 11, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		XiSymbol token19 = new XiSymbol(Sym.CLOSE_BRACE, "}", 5, 1, 2, "tests"+File.separator+"lexer"+File.separator+"test1.txt");
		//assertEquals
		nextTokenSource = lexer.next();
		assertEquals(token1.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token2.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token3.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token4.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token5.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token6.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token7.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token8.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token9.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token10.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token11.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token12.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token13.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token14.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token15.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token16.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token17.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token18.position(), nextTokenSource.position());
		nextTokenSource = lexer.next();
		assertEquals(token19.position(), nextTokenSource.position());
	}
	
	@Test
	public void testGCD() {
		//Actual Result
		String testSource = "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt";
		try {
			file = new FileReader(testSource);
			bf = new BufferedReader(file);
			lexer = new JFlexLexer(testSource, bf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<XiSymbol> tokenList = new ArrayList<XiSymbol>();
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "gcd", 2, 1, 4, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_PAREN, "(", 2, 4, 5, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "a", 2, 5, 6, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 2, 6, 7, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 2, 7, 10, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.COMMA, ",", 2, 10, 11, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "b", 2, 12, 13, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 2, 13, 14, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 2, 14, 17, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_PAREN, ")", 2, 17, 18, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 2, 18, 19, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 2, 19, 22, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACE, "{", 2, 23, 24, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.WHILE, "while", 3, 2, 7, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_PAREN, "(", 3, 8, 9, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "a", 3, 9, 10, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.NOT_EQUAL, "!=", 3, 11, 13, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.INTEGER_LITERAL, "0", 3, 14, 15, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_PAREN, ")", 3, 15, 16, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACE, "{", 3, 17, 18, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.IF, "if", 4, 3, 5, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_PAREN, "(", 4, 6, 7, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "a", 4, 7, 8, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.LT, "<", 4, 8, 9, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "b", 4, 9, 10, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_PAREN, ")", 4, 10, 11, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "b", 4, 12, 13, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.GETS, "=", 4, 14, 15, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "b", 4, 16, 17, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.MINUS, "-", 4, 18, 19, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "a", 4, 20, 21, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.ELSE, "else", 5, 3, 7, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "a", 5, 8, 9, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.GETS, "=", 5, 10, 11, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "a", 5, 12, 13, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.MINUS, "-", 5, 14, 15, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "b", 5, 16, 17, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACE, "}", 6, 2, 3, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.RETURN, "return", 7, 2, 8, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_PAREN, "(", 7, 8, 9, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "b", 7, 9, 10, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_PAREN, ")", 7, 10, 11, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACE, "}", 8, 1, 2, "tests"+File.separator+"lexer"+File.separator+"gcd_test.txt"));
		for(XiSymbol sym : tokenList){
			assertEquals("gcd test", sym, lexer.next());
		}
	}
	
	@Test
	public void testEmptyFile() {
		String testSource = "tests"+File.separator+"lexer"+File.separator+"empty_file_test.txt";
		try {
			file = new FileReader(testSource);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bf = new BufferedReader(file);
		try {
			lexer = new JFlexLexer(testSource, bf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lexer.next();
		}
		catch (Exception EmptyFileException)
		{
			assertEquals("Test passed", true, true);
		}
	}
	
	@Test
	public void testArray() {
		String testSource = "tests"+File.separator+"lexer"+File.separator+"array_tester.txt";
		try {
			file = new FileReader(testSource);
			bf = new BufferedReader(file);
			lexer = new JFlexLexer(testSource, bf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<XiSymbol> tokenList = new ArrayList<XiSymbol>();
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "a", 1, 1, 2, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 1, 2, 3, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 1, 4, 7, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 1, 7, 8, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 1, 8, 9, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 1, 9, 10, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 1, 10, 11, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "b", 2, 1, 2, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 2, 2, 3, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 2, 4, 7, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 2, 7, 8, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INTEGER_LITERAL, "3", 2, 8, 9, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 2, 9, 10, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 2, 10, 11, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INTEGER_LITERAL, "4", 2, 11, 12, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 2, 12, 13, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "a", 3, 1, 2, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.GETS, "=", 3, 3, 4, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "b", 3, 5, 6, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "c", 4, 1, 2, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 4, 2, 3, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 4, 4, 7, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 4, 7, 8, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INTEGER_LITERAL, "3", 4, 8, 9, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 4, 9, 10, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 4, 10, 11, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 4, 11, 12, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "c", 5, 1, 2, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 5, 2, 3, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INTEGER_LITERAL, "0", 5, 3, 4, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 5, 4, 5, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.GETS, "=", 5, 6, 7, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "b", 5, 8, 9, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 5, 9, 10, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INTEGER_LITERAL, "0", 5, 10, 11, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 5, 11, 12, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.SEMICOLON, ";", 5, 12, 13, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "c", 5, 14, 15, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 5, 15, 16, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INTEGER_LITERAL, "1", 5, 16, 17, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 5, 17, 18, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.GETS, "=", 5, 19, 20, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "b", 5, 21, 22, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 5, 22, 23, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INTEGER_LITERAL, "1", 5, 23, 24, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 5, 24, 25, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.SEMICOLON, ";", 5, 25, 26, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "d", 6, 1, 2, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 6, 2, 3, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 6, 4, 7, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 6, 7, 8, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INTEGER_LITERAL, "1", 6, 8, 9, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 6, 9, 10, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 6, 10, 11, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INTEGER_LITERAL, "2", 6, 11, 12, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 6, 12, 13, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 6, 13, 14, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.INTEGER_LITERAL, "3", 6, 14, 15, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 6, 15, 16, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 6, 16, 17, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 6, 17, 18, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACKET, "[", 6, 18, 19, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACKET, "]", 6, 19, 20, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		tokenList.add(new XiSymbol(Sym.SEMICOLON, ";", 6, 20, 21, "tests"+File.separator+"lexer"+File.separator+"array_tester.txt"));
		for(int i = 0; i < tokenList.size(); i ++) {
			assertEquals("array checker", tokenList.get(i), lexer.next());
		}
	}
	
	@Test
	public void testBasicOO(){
		String testSource = "tests"+File.separator+"lexer"+File.separator+"oo.xi";
		try {
			file = new FileReader(testSource);
			bf = new BufferedReader(file);
			lexer = new JFlexLexer(testSource, bf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<XiSymbol> tokenList = new ArrayList<XiSymbol>();
		tokenList.add(new XiSymbol(Sym.CLASS, "class", 1, 1, 6, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "Point", 1, 7, 12, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACE, "{", 1, 13, 14, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "x", 2, 1, 2, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COMMA, ",", 2, 2, 3, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "y", 2, 3, 4, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 2, 4, 5, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 2, 6, 9, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "square", 3, 1, 7, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 3, 8, 9, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.BOOL, "bool", 3, 10, 14, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "coords", 4, 1, 7, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.OPEN_PAREN, "(", 4, 7, 8, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.CLOSE_PAREN, ")", 4, 8, 9, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 4, 9, 10, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 4, 11, 14, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COMMA, ",", 4, 14, 15, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 4, 16, 19, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACE, "{", 4, 20, 21, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.RETURN, "return", 5, 1, 7, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "x", 5, 8, 9, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COMMA, ",", 5, 9, 10, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "y", 5, 10, 11, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACE, "}", 6, 1, 2, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "init", 7, 1, 5, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.OPEN_PAREN, "(", 7, 5, 6, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));	
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "x0", 7, 6, 8, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 7, 8, 9, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 7, 9, 12, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COMMA, ",", 7, 12, 13, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "y0", 7, 13, 15, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 7, 15, 16, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 7, 16, 19, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.CLOSE_PAREN, ")", 7, 19, 20, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 7, 20, 21, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "Point", 7, 21, 26, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACE, "{", 7, 26, 27, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "x", 8, 1, 2, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.GETS, "=", 8, 2, 3, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "x0", 8, 3, 5, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "y", 9, 1, 2, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.GETS, "=", 9, 2, 3, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "y0", 9, 3, 5, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.RETURN, "return", 10, 1, 7, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.THIS, "this", 10, 8, 12, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACE, "}", 11, 1, 2, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACE, "}", 12, 1, 2, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "createPoint", 14, 1, 12, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.OPEN_PAREN, "(", 14, 12, 13, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "x", 14, 13, 14, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 14, 14, 15, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 14, 15, 18, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COMMA, ",", 14, 18, 19, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "y", 14, 20, 21, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 14, 21, 22, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.INT, "int", 14, 22, 25, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.CLOSE_PAREN, ")", 14, 25, 26, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COLON, ":", 14, 26, 27, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "Point", 14, 28, 33, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.OPEN_BRACE, "{", 14, 34, 35, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.RETURN, "return", 15, 1, 7, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.NEW, "new", 15, 8, 11, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "Point", 15, 12, 17, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.PERIOD, ".", 15, 17, 18, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "init", 15, 18, 22, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.OPEN_PAREN, "(", 15, 22, 23, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "x", 15, 23, 24, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.COMMA, ",", 15, 24, 25, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.IDENTIFIER, "y", 15, 25, 26, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.CLOSE_PAREN, ")", 15, 26, 27, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		tokenList.add(new XiSymbol(Sym.CLOSE_BRACE, "}", 16, 1, 2, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		for(int i = 0; i < tokenList.size(); i ++) {
			assertEquals("basic OO checker", tokenList.get(i), lexer.next());
		}
		//printLexerTree("tests"+File.separator+"lexer"+File.separator+"oo.xi");
		//assertEquals(true,true);
	}
	/*
	@Test
	public void testInterfaceOO(){
		String testSource = "tests"+File.separator+"lexer"+File.separator+"oo.xi";
		try {
			file = new FileReader(testSource);
			bf = new BufferedReader(file);
			lexer = new JFlexLexer(testSource, bf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<XiSymbol> tokenList = new ArrayList<XiSymbol>();
		//tokenList.add(new XiSymbol(Sym.CLASS, "class", 1, 1, 6, "tests"+File.separator+"lexer"+File.separator+"oo.xi"));
		
	}
	*/
	private void printLexerTree(String input) {
		String testSource = input;
		try {
			file = new FileReader(testSource);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bf = new BufferedReader(file);
		try {
			lexer = new JFlexLexer(testSource, bf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(lexer.hasNext())
		{
			System.out.println(lexer.next());
		}
	}
}