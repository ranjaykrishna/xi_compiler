package rak248.jUnit;


import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;

import java_cup.runtime.Symbol;

import org.junit.Test;

import edu.cornell.cs.cs4120.util.CodeWriterTreePrinter;

import rak248.xi.Location;
import rak248.xi.lexer.JFlexLexer;
import rak248.xi.lexer.XiSymbol;
import rak248.xi.parser.AssignmentNode;
import rak248.xi.parser.CompUnitNode;
import rak248.xi.parser.CupParser;
import rak248.xi.parser.ExpressionNode;
import rak248.xi.parser.IntegerLiteralNode;
import rak248.xi.parser.LHSListNode;
import rak248.xi.parser.StatementsNode;
import rak248.xi.parser.Sym;
import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.Type.typeEnum;


import junit.framework.TestCase;

public class StatementTests {
	
	public String readFile(String s){
		FileReader file;
		String output = "";
		try {
			file = new FileReader(s);
			BufferedReader br = new BufferedReader(file);
			String strLine;
			while ((strLine = br.readLine()) != null){
				output += " " + strLine;
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	public void createFile(String s, String fileName){
		FileWriter f;
		try {
			f = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(f);
			String header = "func():int { ";
			String tail = " return(1) }";
			String toWrite = header + s + tail;
			bw.write(toWrite,0,toWrite.length());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void oneTest(String s) {
		//Actual Result
		String testSource = s;
		String statement = readFile(testSource);
		createFile(statement,"tests/parser/temporary.txt");
		try {
			FileReader file = new FileReader("tests/parser/temporary.txt");
			BufferedReader br = new BufferedReader(file);
			JFlexLexer lexer = new JFlexLexer(testSource,br);
			CupParser parser = new CupParser(lexer);
			Symbol parseTree = parser.parse();
			//this is the expected compUnitNode
			CompUnitNode comp = (CompUnitNode) parseTree.value;
			//new CodeWriterTreePrinter(System.out).print(comp);
			//now to find the statements node
			StatementsNode stateNode = withdraw(comp);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private StatementsNode withdraw(CompUnitNode comp) {
		
		return null;
	}

	@Test
	public void testCaller(){
		oneTest("tests/parser/test3.txt");
	}
	
	public StatementsNode createTest3(){
		StatementsNode ret = new StatementsNode();
		AssignmentNode as = new AssignmentNode(
				new LHSListNode("b",new Type(typeEnum.INT),
						new Location("tests/parser/temporary.tx", 1, 1, 14, 15),
						new Location("tests/parser/temporary.txt", 1, 1, 14, 15)),
				new ExpressionNode(new IntegerLiteralNode(1,
							new Location("tests/parser/temporary.txt", 1, 1, 22, 23),
							new Location("tests/parser/temporary.txt", 1, 1, 22, 23)),
						new Location("tests/parser/temporary.txt", 1, 1, 22, 23),
						new Location("tests/parser/temporary.txt", 1, 1, 22, 23)),
				new Location("tests/parser/temporary.txt", 1, 1, 14, 23), 
				new Location("tests/parser/temporary.txt", 1, 1, 14, 23));
		ret.addStatement(as,
				new Location("tests/parser/temporary.txt", 1, 1, 12, 23),
				new Location("tests/parser/temporary.txt", 1, 1, 12, 23));
		return ret;
	}
}
