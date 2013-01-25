package rak248.jUnit;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java_cup.runtime.Symbol;

import org.junit.Test;

import rak248.Driver;
import rak248.asm.Generate;
import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.opt.CFG;
import rak248.opt.CFGNode;
import rak248.opt.IRGen;
import rak248.opt.RegAllocation;
import rak248.opt.StaticStack;
import rak248.opt.Worklist;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.Optimize;
import rak248.xi.lexer.JFlexLexer;
import rak248.xi.parser.CompUnitNode;
import rak248.xi.parser.CupParser;
import rak248.xi.typeChecker.SymbolTableCreator;

public class AsmChecker {
	
	//private static String runtimeLocation = "/home/ranjay/Documents/CS4120/runtime";
	
	
	//private static String runtimeLocation = "/home/grant/Desktop/runtime";
	

	private static String runtimeLocation = "/home/colin/Desktop/runtime";

	@Test
	public void testHelloWorld(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"helloworld.xi");
		assertEquals("hello world!\n", res);
	}
	
	@Test
	public void testIfElse(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"IfElse.xi");
		assertEquals("true is true\n1 is: 1\n0 is: 0\n", res);
	}
	
	@Test
	public void testsimpleprog(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"basicprog.xi");
		assertEquals("YEA\n", res);
	}
	
	@Test
	public void testWhile(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"while.xi");
		assertEquals("a:100\n", res);
	}

	@Test
	public void testFactorial(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"factorial.xi");
		assertEquals("120\n", res);
	}

	@Test
	public void testFibonacci(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"fibonacci.xi");
		assertEquals("610\n", res);
	}
	
	@Test
	public void testArray(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"array.xi");
		assertEquals("ABC\nBCD\n67\n", res);
	}
	
	@Test
	public void testMod(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"mod_test.xi");
		assertEquals("great success\n", res);
	}
	
	@Test
	public void testBasic(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"basic.xi");
		assertEquals("b:0\n", res);
	}
	
	@Test
	public void testFunc(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"func.xi");
		assertEquals("3\nc is true\na<5 & b is true\n3\n", res);
	}
	
	@Test
	public void testLong(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"long.xi");
		assertEquals("5294967296 is :\n5294967296\n10589934592 is :\n10589934592\n11589934592 is :\n11589934592\n", res);
	}
	
	@Test
	public void testSimpleMem(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"simpleMem.xi");
		assertEquals("c:2\nwhich is also a[0][1]:2\nd:4\n", res);
	}

	@Test
	public void testMultiDim(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"multiDim.xi");
		assertEquals("100==100\n", res);
	}
	
	@Test
	public void testArguments(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator+"multiargstuple.xi");
		assertEquals("", res);
	}
	
	//Start test cases from previous year's contest
	@Test
	public void testContestArray(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-contest"+File.separator+"array.xi");
		assertEquals("", res);
	}
	
	@Test
	public void testContestBubbleSort(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-contest"+File.separator+"bubbleSort.xi");
		assertEquals("3.\n4.\n6.\n7.\n", res);
	}
	
	@Test
	public void testContestDivMod(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-contest"+File.separator+"divmod.xi");
		assertEquals("", res);
	}
	
	@Test
	public void testContestFib(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-contest"+File.separator+"fib.xi");
		assertEquals("", res);
	}
	
	@Test
	public void testContestGCD(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-contest"+File.separator+"gcd.xi");
		assertEquals("", res);
	}

	@Test
	public void testContestITOA(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-contest"+File.separator+"itoa.xi");
		assertEquals("", res);
	}

	@Test
	public void testContestMerge_Short(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-contest"+File.separator+"merge_short.xi");
		assertEquals("", res);
	}
	
	@Test
	public void testContestNegDiv(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-contest"+File.separator+"negdiv.xi");
		assertEquals("", res);
	}
	
	//Begin 2009-testcases 
	@Test
	public void testTestCaseArrayInvalid0(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
			+"testcases"+File.separator+"2009-testcases"+File.separator+"array-invalid-0.xi");
		assertEquals("Segmentation fault(probably):Array index out of bounds\n", res);
	}

	@Test
	public void testTestCaseArrayInvalid1(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
			+"testcases"+File.separator+"2009-testcases"+File.separator+"array-invalid-1.xi");
		assertEquals("Segmentation fault(probably):Array index out of bounds\n", res);
	}

	@Test
	public void testTestCaseArrayInvalid2(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
			+"testcases"+File.separator+"2009-testcases"+File.separator+"array-invalid-2.xi");
		assertEquals("Segmentation fault(probably):Array index out of bounds\n", res);
	}

	@Test
	public void testTestCaseArrayValid0(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
			+"testcases"+File.separator+"2009-testcases"+File.separator+"array-valid-0.xi");
		assertEquals("No crash\n", res);
	}

	@Test
	public void testTestCaseCompareSigned(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
			+"testcases"+File.separator+"2009-testcases"+File.separator+"compare-signed.xi");
		assertEquals("Testing:<0a\nTesting:<0b\nTesting:<1a\nTesting:<1b\nTesting:<2a\nTesting:<2b\n"+
				     "Testing:<=0a\nTesting:<=0b\nTesting:<=1a\nTesting:<=1b\nTesting:<=2a\nTesting:<=2b\n"+
				     "Testing:>0a\nTesting:>0b\nTesting:>1a\nTesting:>1b\nTesting:>2a\nTesting:>2b\n"+
				     "Testing:>=0a\nTesting:>=0b\nTesting:>=1a\nTesting:>=1b\nTesting:>=2a\nTesting:>=2b\n", res);
	}

	@Test
	public void testTestCaseDividedSignedDef(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"divide-signed-def.xi");
		assertEquals("", res);
	}

	@Test
	public void testTestCaseDividedSigned(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"divide-signed.xi");
		assertEquals("-2\n", res);
	}

	@Test
	public void testTestCaseHello(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"hello.xi");
		assertEquals("Hello, World!\n", res);
	}

	@Test
	public void testTestCaseIntPythag(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"int-pythag.xi");
		assertEquals("Testing:#1\nTesting:#2\n", res);
	}

	@Test
	public void testTestCaseNonMain(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"nonmain.xi");
		assertEquals("IOException", res);
	}

	@Test
	public void testTestCaseSort(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"sort.xi");
		assertEquals("                        ,-.AITT[]aaaaaaaaaabccddeeeeeeeeeeeffffgghhiiiiiillllmnnnnnnnnoopqrrrrrssssstttttuuxyyy\n"
				+"Testing:length\n"+"Testing:0\n"+"Testing:1\n"+"Testing:2\n"+"Testing:3\n"+"Testing:4\n"+"Testing:5\n"+"Testing:6\n"
				+"Testing:7\n"+"Testing:8\n"+"Testing:9\n"+"Testing:10\n"+"Testing:11\n"+"Testing:12\n"+"Testing:13\n"+"Testing:14\n"
				+"Testing:15\n"+"Testing:16\n"+"Testing:17\n"+"Testing:18\n"+"Testing:19\n"+"Testing:20\n"+"Testing:21\n"+"Testing:22\n"
				+"Testing:23\n"+"Testing:24\n"+"Testing:25\n"+"Testing:26\n"+"Testing:27\n"+"Testing:28\n"+"Testing:29\n"+"Testing:30\n"
				+"Testing:31\n"+"Testing:32\n"+"Testing:33\n"+"Testing:34\n"+"Testing:35\n"+"Testing:36\n"+"Testing:37\n"+"Testing:38\n"
				+"Testing:39\n"+"Testing:40\n"+"Testing:41\n"+"Testing:42\n"+"Testing:43\n"+"Testing:44\n"+"Testing:45\n"+"Testing:46\n"
				+"Testing:47\n"+"Testing:48\n"+"Testing:49\n"+"Testing:50\n"+"Testing:51\n"+"Testing:52\n"+"Testing:53\n"+"Testing:54\n"
				+"Testing:55\n"+"Testing:56\n"+"Testing:57\n"+"Testing:58\n"+"Testing:59\n"+"Testing:60\n"+"Testing:61\n"+"Testing:62\n"
				+"Testing:63\n"+"Testing:64\n"+"Testing:65\n"+"Testing:66\n"+"Testing:67\n"+"Testing:68\n"+"Testing:69\n"+"Testing:70\n"
				+"Testing:71\n"+"Testing:72\n"+"Testing:73\n"+"Testing:74\n"+"Testing:75\n"+"Testing:76\n"+"Testing:77\n"+"Testing:78\n"
				+"Testing:79\n"+"Testing:80\n"+"Testing:81\n"+"Testing:82\n"+"Testing:83\n"+"Testing:84\n"+"Testing:85\n"+"Testing:86\n"
				+"Testing:87\n"+"Testing:88\n"+"Testing:89\n"+"Testing:90\n"+"Testing:91\n"+"Testing:92\n"+"Testing:93\n"+"Testing:94\n"
				+"Testing:95\n"+"Testing:96\n"+"Testing:97\n"+"Testing:98\n"+"Testing:99\n"+"Testing:100\n"+"Testing:101\n"+"Testing:102\n"
				+"Testing:103\n"+"Testing:104\n"+"Testing:105\n"+"Testing:106\n"+"Testing:107\n"+"Testing:108\n"+"Testing:109\n"
				+"Testing:110\n",res);
	}

	@Test
	public void testTestCaseStatementBreak(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"statement-break.xi");
		assertEquals("Testing:#1\nTesting:#2\nTesting:#3\n", res);
	}

	@Test
	public void testTestCaseStatementIf(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"statement-if.xi");
		assertEquals("", res);
	}

	@Test
	public void testTestCaseTypeArray0(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"type-array-0.xi");
		assertEquals("Testing:#1\nTesting:#2\nTesting:#3\nTesting:#4\nTesting:#5\nTesting:#6\n", res);
	}

	@Test
	public void testTestCaseTypeArray1(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"type-array-1.xi");
		assertEquals("Testing:#1\nTesting:#2\nTesting:#3\n", res);
	}

	@Test
	public void testTestCaseTypeArrayInit(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"type-array-init.xi");
		assertEquals("Testing:#0\nTesting:#1\nTesting:#2\nTesting:#3\nTesting:#4\nTesting:#5\n", res);
	}

	@Test
	public void testTestCaseTypeBoolShortCircuit(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"type-bool-shortcircuit.xi");
		assertEquals("Testing:#1\nTesting:#2\n", res);
	}

	@Test
	public void testTestCaseTypeInt0(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"type-int-0.xi");
		assertEquals("Testing:+\nTesting:-\nTesting:*\nTesting:/\nTesting:/2\nTesting:%\nTesting:%2\n"
				    +"Testing:+\nTesting:-\nTesting:*\nTesting:/\nTesting:/2\nTesting:%\nTesting:%2\n", res);
	}

	@Test
	public void testTestCaseTypeInt1(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"type-int-1.xi");
		assertEquals("Testing:<\nTesting:<=\nTesting:>\nTesting:>=\nTesting:==\nTesting:!=\n"
				    +"Testing:<\nTesting:<=\nTesting:>\nTesting:>=\nTesting:==\nTesting:!=\n", res);
	}

	@Test
	public void testTestCaseVars0(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"vars-0.xi");
		assertEquals("", res);
	}

	@Test
	public void testTestCaseVars1(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"vars-1.xi");
		assertEquals("", res);
	}

	@Test
	public void testTestCaseVars2(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"vars-2.xi");
		assertEquals("", res);
	}

	@Test
	public void testTestCaseVars3(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"testcases"+File.separator+"2009-testcases"+File.separator+"vars-3.xi");
		assertEquals("", res);
	}
	
	/**********************************
	 * New PA5 tests
	 */
	@Test
	public void test2011AddArray(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"2011-contest"+File.separator+"addArray.xi");
		assertEquals("hello ABCFGHIJK\n", res);
	}
	
	@Test
	public void test2011Contest1(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"2011-contest"+File.separator+"contest1.xi");
		assertEquals("72\nHelloworld\n", res);
	}
	
	@Test
	public void test2011Contest2(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"2011-contest"+File.separator+"contest2.xi");
		assertEquals("7\n", res);
	}

	@Test
	public void test2011GHard(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"2011-contest"+File.separator+"GHard.xi");
		assertEquals("hello \n", res);
	}

	@Test
	public void test2011inlineArray(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"2011-contest"+File.separator+"inlineArray.xi");
		assertEquals("", res);
	}

	@Test
	public void test2011T1(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"2011-contest"+File.separator+"t1.xi");
		assertEquals("	\n", res);
	}

	@Test
	public void test2011T2(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"2011-contest"+File.separator+"t2.xi");
		assertEquals("", res);
	}

	@Test
	public void test2011T3(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"2011-contest"+File.separator+"t3.xi");
		assertEquals("7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n"+
					"7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n"+
					"7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n"+
					"7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n"+
					"7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n"+
					"7Hello\n7Hello\n7Hello\n7Hello\n7Hello\n", res);
	}

	@Test
	public void test2011T4(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"2011-contest"+File.separator+"t4.xi");
		assertEquals("120\n", res);
	}

	@Test
	public void test2011T5(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"2011-contest"+File.separator+"t5.xi");
		assertEquals("", res);
	}

	@Test
	public void test2011Testlongs(){
		String res = runProgram("tests"+File.separator+"ASMTests"+File.separator
				+"2011-contest"+File.separator+"testlongs.xi");
		assertEquals("", res);
	}
	/*
	 * Start of pa6 benchmark tests
	 */
	@Test
	public void testBench_3np1(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"3np1.xi");
		assertEquals("230:1\n",res);
	}
	
	@Test
	public void testBench_ack(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"ack.xi");
		assertEquals("Ack(3,11): 16381\n",res);
	}
	
	@Test
	public void testBench_contrast(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"contrast.xi");
		assertEquals("@0\n0\n0\n0\n@3\n0\n0\n0\n@7\n0\n0\n0\n@12\n0\n0\n0\n@19\n0\n0\n0\n" +
				     "@28\n0\n0\n1\n@40\n5\n7\n3\n@56\n17\n7\n13\n@77\n36\n13\n27\n@105\n" +
				     "25\n51\n76\n@143\n161\n55\n110\n@193\n153\n230\n78\n@260\n2\n104\n209\n" +
				     "@349\n255\n101\n127\n@468\n182\n66\n250\n@627\n247\n191\n136\n@839\n" +
				     "30\n11\n22\n@1121\n104\n128\n255\n@1497\n255\n242\n228\n@1999\n61\n" +
				     "244\n183\n@2668\n255\n113\n127\n@3560\n255\n178\n77\n@4749\n54\n107\n" +
				     "161\n@6335\n227\n77\n153\n@8449\n0\n0\n0\n@11268\n206\n105\n5\n@15027\n" +
				     "174\n40\n215\n@20039\n30\n11\n22\n@26721\n242\n179\n116\n@35631\n121\n" +
				     "241\n56\n@47511\n255\n215\n174\n@63351\n128\n255\n121\n@84471\n255\n252\n" +
				     "249\n@112631\n255\n171\n82\n@150177\n166\n26\n191\n@200239\n140\n55\n225\n" +
				     "@266988\n178\n78\n255\n@355987\n14\n168\n157\n@474652\n0\n0\n1\n@632872\n" +
				     "5\n7\n3\n@843832\n146\n66\n228\n@1125112\n252\n249\n255\n@1500152\n" +
				     "255\n85\n170\n@2000205\n127\n255\n88\n@2666943\n178\n50\n229\n@3555927\n" +
				     "17\n33\n50\n@4741239\n128\n255\n121\n@6321655\n170\n255\n85\n@8428876\n24\n" +
				     "36\n13\n@11238504\n127\n255\n110\n@14984675\n255\n85\n170\n",res);
	}
	
	@Test
	public void testBench_dot(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"dot.xi");
		assertEquals("-737211646186078208\n",res);
	}
	
	@Test
	public void testBench_enigma(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"enigma.xi");
		assertEquals("MATCH At rotor pos:EGG first comes in from:E\n",res);
	}
	
	@Test
	public void testBench_fannkuch(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"fannkuch.xi");
		assertEquals("556355\nPfannkuchen(11) = 51\n",res);
	}

	@Test
	public void testBench_fib(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"fib.xi");
		assertEquals("",res);
	}

	@Test
	public void testBench_loop(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"loop.xi");
		assertEquals("",res);
	}

	@Test
	public void testBench_mat_mult(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"mat-mult.xi");
		assertEquals("41541750\n43016250\n40911000\n",res);
	}

	@Test
	public void testBench_sieve(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"sieve.xi");
		assertEquals("Count: 8713\n",res);
	}

	@Test
	public void testBench_strings(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"strings.xi");
		assertEquals("",res);
	}

	@Test
	public void testBench_student_gs(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"student-gs.xi");
		assertEquals("26354\n",res);
	}

	@Test
	public void testBench_student_primes(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"student-primes.xi");
		assertEquals("",res);
	}

	@Test
	public void testBench_student_sort(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"student-sort.xi");
		assertEquals("",res);
	}

	@Test
	public void testBench_student_tsp(){
		String res = runProgram("tests"+File.separator+"pa6-bench"+File.separator
				+"student-tsp.xi");
		assertEquals("25\n",res);
	}
		
	public String runProgram(String input){
		try {
			String output = runtimeLocation + "/" + 
				input.substring(input.lastIndexOf(File.separator)+1,input.lastIndexOf(".")) + ".s";
			Driver.initForJUnit();
			Driver.helper(input, output, false);

			Runtime rt = Runtime.getRuntime();
			File runtimeFile = new File(runtimeLocation);
			Process p = rt.exec(runtimeLocation + "/linkxi.sh -o " + 
					input.substring(input.lastIndexOf(File.separator)+1,input.lastIndexOf("."))
					+ " "
					+ output, null, runtimeFile);
			if(p.waitFor()!=0){
				InputStream in = p.getErrorStream();
				int c = in.read();
				StringBuilder str = new StringBuilder();
				while(c!=-1){
					str.append((char)c);
					c = in.read();
				}
				in = p.getInputStream();
				c = in.read();
				while(c!= -1){
					str.append((char) c);
					c = in.read();
				}
				p.destroy();
				return "linking failed:"+str.toString();
			}
			p.destroy();
			for(int i =0;i<100000;i++){i++;}
			p = rt.exec(runtimeLocation + "/"
					+ input.substring(input.lastIndexOf(File.separator)+1,input.lastIndexOf("."))
					, null, runtimeFile);

			if(p.waitFor()!=0){
				InputStream in = p.getErrorStream();
				int c = in.read();
				StringBuilder str = new StringBuilder();
				while(c!=-1){
					str.append((char)c);
					c = in.read();
				}
				p.destroy();
				return "Segmentation fault(probably):"+str.toString();
			}

			StringBuilder str = new StringBuilder();
			InputStream in = p.getInputStream();

			int c = in.read();

			while(c!= -1){
				str.append((char) c);
				c = in.read();
			}
			p.destroy();
			return str.toString();
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
			return "FileNotFoundException";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "IOException";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Exception";
		}
	}
	
	public String old_runProgram(String input){
		try {
			String output = runtimeLocation + "/" + 
				input.substring(input.lastIndexOf(File.separator)+1,input.lastIndexOf(".")) + ".s";
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
				seq = seq.foldCALL();
				seq = seq.foldESEQ();
				Optimize.foldConst(seq);
				Optimize.foldSEQ(seq);
				//StaticStack.staticStackFrame(seq, null);
				CFG cfg = new CFG(seq.getChildren());
				ArrayList<CFGNode> roots = cfg.getHeadList();
				Worklist worklist = new Worklist();
				worklist.copyPropagation(roots);
				worklist.setupDefUse(roots);
				
				worklist.liveVariableAnalysis();
				worklist.deadCodeElimination();
				
				for(int i = 0; i<roots.size();i++){
					HashSet<CFGNode> doneList = new HashSet<CFGNode>();
					roots.get(i).resetSpecialTemps();
					roots.get(i).fixMultipleDefs(doneList);
				}
				worklist.setupDefUse(roots);
				for(int i = 0; i < roots.size(); i ++) {
					HashSet<CFGNode> doneList = new HashSet<CFGNode>();
					roots.get(i).dominate(new HashSet<CFGNode>());
					roots.get(i).calculateDomList(new HashSet<CFGNode>());
					doneList = new HashSet<CFGNode>();
					roots.get(i).computeDomFront(doneList);
					//roots.get(i).printDomList();
					doneList = new HashSet<CFGNode>();
					//roots.get(i).printDomFront(doneList);
					//doneList = new HashSet<CFGNode>();
					roots.get(i).fixUses(doneList);
				}
				
				worklist.setupDefUse(roots);
				worklist.commonSubexpressionElimination(roots);
				worklist.copyPropagation(roots);
				worklist.setupDefUse(roots);
				worklist.liveVariableAnalysis();
				worklist.deadCodeElimination();
				//RegAllocation.printCheck(roots);
				/*reg = this is a hashmap that tells you what registers to allocate 
				 * to temps each string corresponds to the mangled name of 
				 * the function and their corresponding hashmap of temps
				 */
				worklist.setupDefUse(roots);
				worklist.liveVariableAnalysis();
				HashMap<String,HashMap<String,String>> reg = RegAllocation.registerAllocate(roots);
				IRGen irgen = new IRGen(roots);
				seq = irgen.getIR();
				
				seq = seq.foldCALL();
				seq = seq.foldESEQ();
				Optimize.foldConst(seq);
				Optimize.foldSEQ(seq);
				TempTable tTable = new TempTable();
				FileWriter f = new FileWriter(output);
				BufferedWriter b = new BufferedWriter(f);
				StaticStack.staticStackFrame(seq, reg);
				for(Tile tile : Generate.generateTile(seq,tTable).getTiles()) {
					b.write(tile.toString());
					b.newLine();
					//System.out.println(tile);
				}
				b.close();
				
				Runtime rt = Runtime.getRuntime();
				File runtimeFile = new File(runtimeLocation);
				Process p = rt.exec(runtimeLocation + "/linkxi.sh -o " + 
						input.substring(input.lastIndexOf(File.separator)+1,input.lastIndexOf("."))
						+ " "
						+ output, null, runtimeFile);
				if(p.waitFor()!=0){
					InputStream in = p.getErrorStream();
					int c = in.read();
					StringBuilder str = new StringBuilder();
					while(c!=-1){
						str.append((char)c);
						c = in.read();
					}
					in = p.getInputStream();
					c = in.read();
					while(c!= -1){
						str.append((char) c);
						c = in.read();
					}
					p.destroy();
					return "linking failed:"+str.toString();
				}
				p.destroy();
				p = rt.exec(runtimeLocation + "/"
						+ input.substring(input.lastIndexOf(File.separator)+1,input.lastIndexOf("."))
						, null, runtimeFile);
				
				if(p.waitFor()!=0){
					InputStream in = p.getErrorStream();
					int c = in.read();
					StringBuilder str = new StringBuilder();
					while(c!=-1){
						str.append((char)c);
						c = in.read();
					}
					p.destroy();
					return "Segmentation fault(probably):"+str.toString();
				}
				
				StringBuilder str = new StringBuilder();
				InputStream in = p.getInputStream();
				
				int c = in.read();
				
				while(c!= -1){
					str.append((char) c);
					c = in.read();
				}
				p.destroy();
				return str.toString();

			}
			else{
				throw new RuntimeException("interface check failed");
			}
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
			return "FileNotFoundException";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "IOException";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Exception";
		}
	}
	
	public static String getRuntimeLocation() {
		return runtimeLocation;
	}
}
