package rak248;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import rak248.asm.Generate;
import rak248.asm.GenerateWindows;
import rak248.asm.TempTable;
import rak248.asm.Tile;
import rak248.jUnit.AsmChecker;
import rak248.opt.CFG;
import rak248.opt.CFGNode;
import rak248.opt.IRGen;
import rak248.opt.RegAllocation;
import rak248.opt.StaticStack;
import rak248.opt.Worklist;
import rak248.testing.Interpreter;
import rak248.testing.XiParserFactory;
import rak248.util.CodeWriterIRPrinter;
import rak248.util.VisualizableIRNode;
import rak248.xi.SyntaxIR;
import rak248.xi.ir.CompUnitIR;
import rak248.xi.ir.Optimize;
import rak248.xi.parser.CompUnitNode;
import rak248.xi.parser.ElseNode;
import rak248.xi.parser.XiParser;
import rak248.xi.typeChecker.SymbolTable;
import rak248.xi.typeChecker.SymbolTableCreator;
import edu.cornell.cs.cs4120.util.CodeWriterTreePrinter;
import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.AbstractSyntaxNode;

public class Driver {
	
	public static enum dumpsEnum {AST_INITIAL, AST_FINAL, IR_INITIAL, IR_FINAL, CFG_INITIAL, CFG_FINAL, INTERPRETER_INITIAL, INTERPRETER_FINAL, EXECUTE, WINDOWS};
	static HashSet<String> options;
	static HashSet<dumpsEnum> dumps;

	/**
	 * @param argsvalue
	 * @throws Exception 
	 * @throws Exception 
	 */
	public static void main(String[] args){
		try{
		if (args.length == 0){
			printHelp();
		}else{
			boolean opt = true;
			options = new HashSet<String>();
			dumps = new HashSet<dumpsEnum>();
			String output = "";
			String source ="";
			boolean plusOSeen = false;
			initOptionsSet();
			for(int i =0; i < args.length; i++){
				String arg = args[i];
				if(arg.startsWith("-O")){
					if(arg.length() == 2) {
						opt = false;
					} else {
						options.remove(arg.substring(2));
					}
				} else if(arg.startsWith("+O")) {
					if(plusOSeen == false) {
						options.clear();
						plusOSeen = true;
					}
					options.add(arg.substring(2));
				} else if(arg.equals("-o")){
					if(args.length<i+1){
						System.out.println("Please specify an output file with the -o option");
						printHelp();
						return;
					}else{
						output = args[i+1];
					}
				}else if(arg.equals("-target")) {
					String os = args[++i];
					if(!os.equals("linux")) {
						System.out.println("Sorry " + os + " is not a supported target");
						printHelp();
						return;
					}
				}else if(arg.equals("--dump_ast")) {
					if(args[i+1].equals("initial")) {
						dumps.add(dumpsEnum.AST_INITIAL);
						i++;
					} else if(args[i+1].equals("final")) {
						dumps.add(dumpsEnum.AST_FINAL);
						i++;
					} else {
						dumps.add(dumpsEnum.AST_FINAL);
					}
				}else if(arg.equals("--dump_ir")) {
					if(args[i+1].equals("initial")) {
						dumps.add(dumpsEnum.IR_INITIAL);
						i++;
					} else if(args[i+1].equals("final")) {
						dumps.add(dumpsEnum.IR_FINAL);
						i++;
					} else {
						dumps.add(dumpsEnum.IR_FINAL);
					}
				}else if(arg.equals("--dump_cfg")) {
					if(args[i+1].equals("initial")) {
						dumps.add(dumpsEnum.CFG_INITIAL);
						i++;
					} else if(args[i+1].equals("final")) {
						dumps.add(dumpsEnum.CFG_FINAL);
						i++;
					} else {
						dumps.add(dumpsEnum.CFG_FINAL);
					}
				}else if(arg.equals("--interpret")) {
					if(args[i+1].equals("initial")) {
						dumps.add(dumpsEnum.INTERPRETER_INITIAL);
						i++;
					} else if(args[i+1].equals("final")) {
						dumps.add(dumpsEnum.INTERPRETER_FINAL);
						i++;
					} else {
						dumps.add(dumpsEnum.INTERPRETER_FINAL);
					}
				}else if(arg.equals("--execute")) {
					dumps.add(dumpsEnum.EXECUTE);
				}else if(arg.equals("--windows")) {
					dumps.add(dumpsEnum.WINDOWS);
				}else{//Must be the input file
					source = arg;
					if(output.isEmpty())
						output = source.substring(0, source.lastIndexOf('.'))+".s";
				}
			}
			if(opt == false) {
				options.clear();
			}
			helper(source,output,false);
		}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void printHelp(){
		System.out.println("Incorrect inputs.");
		System.out.println("Add +O<opt> to enable optimization <opt> and set default other options disabled");
		System.out.println("Add -O to diable all optimizations");
		System.out.println("Add -O<opt> to disable optimization <opt>");
		System.out.println("Add --dump_ast <phase> to output an abstract syntax tree. Argument phase can be 'initial' or 'final' and 'final' is the default");
		System.out.println("Add --dump_ir <phase> to output the intermediate representation.");
		System.out.println("Add --dump_cfg <phase> to output the control-flow graph in dot format");
		System.out.println("Add -o output_file to output assembly to output_file");
		System.out.println("Add -target <OS> to specify the operating system for which to generate code");
		System.out.println("Make sure to add the source xi file.");
	}
	
	public static Long helper(String source, String output, boolean interpretReturn) throws Exception{
		FileReader file = new FileReader(source);
		BufferedReader bf = new BufferedReader(file);
		XiParser parser_obj = (XiParser) (new XiParserFactory()).newParser(bf);
		AbstractSyntaxNode parseTree = null;
		parseTree = parser_obj.parse();
		CompUnitNode comp = (CompUnitNode) parseTree;
		ElseNode.foldElse(comp);
		SymbolTableCreator stc = new SymbolTableCreator();
		if (dumps.contains(dumpsEnum.AST_INITIAL) || dumps.contains(dumpsEnum.AST_FINAL)){ // THESE SHOULD BE DIFFERENT CASES
			printTreeToStdOut(comp);
		}
		if (stc.interfaceCheck(source.substring(0,source.length()-3),comp)){

			SymbolTable.initMethodsInRetSizeMap();
			
			SyntaxIR seq = comp.translate(new HashMap<String, String>());
			ArrayList<String> listOfDVS = ((CompUnitIR) seq).getListOfDVS();
			ArrayList<String> sizeSetup = ((CompUnitIR) seq).getSizeSetup();
			//optimizations
			seq = seq.foldCALL();
			seq = seq.foldESEQ();
			Optimize.foldConst(seq);
			Optimize.foldSEQ(seq);
			
			//Fold MEM
			Optimize.foldMem(seq);
			seq = seq.foldESEQ();
			Optimize.foldSEQ(seq);
			
			/*Optimize.foldOp(seq);
			seq = seq.foldESEQ();
			Optimize.foldSEQ(seq);
			*/
			//StaticStack.staticStackFrame(seq);
			//Optimize.foldCJump(seq);
			
			if (dumps.contains(dumpsEnum.IR_INITIAL)){
				printTreeToStdOut(seq);
			}

			
			if(dumps.contains(dumpsEnum.INTERPRETER_INITIAL)) {
				printTreeToStdOut(seq);
				interpret(seq);
			}
			
			CFG cfg = new CFG(seq.getChildren());
			ArrayList<CFGNode> roots = cfg.getHeadList();
			if (dumps.contains(dumpsEnum.CFG_INITIAL)) {
				for(int i = 0; i < roots.size(); i ++) {
					CFG.printDotToStdOut(roots.get(i), roots.get(i).getIrnode().label());
				}
			}
			Worklist worklist = new Worklist();
			if(isEnabled("copy"))
				worklist.copyPropagation(roots);
			worklist.setupDefUse(roots);
			worklist.liveVariableAnalysis();
			worklist.constPropagation(roots);
			if(isEnabled("dce"))
				worklist.deadCodeElimination();
			/*
			for(int i = 0; i<roots.size();i++){
				HashSet<CFGNode> doneList = new HashSet<CFGNode>();
				roots.get(i).resetSpecialTemps();
				roots.get(i).fixMultipleDefs(doneList);
			}
			worklist.setupDefUse(roots);
			for(int i = 0; i<roots.size();i++){
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
			}*/
			worklist.setupDefUse(roots);
			if(isEnabled("cse"))
				worklist.commonSubexpressionElimination(roots);
			if(isEnabled("copy"))
				worklist.copyPropagation(roots);
			worklist.setupDefUse(roots);
			worklist.liveVariableAnalysis();
			worklist.constPropagation(roots);
			if(isEnabled("dce"))
				worklist.deadCodeElimination();
			HashMap<String, HashMap<String, String>> reg = null;
			if(isEnabled("reg")) {
				//RegAllocation.printCheck(roots);
				/*reg = this is a hashmap that tells you what registers to allocate 
				 * to temps each string corresponds to the mangled name of 
				 * the function and their corresponding hashmap of temps
				 */
				worklist.setupDefUse(roots);
				worklist.liveVariableAnalysis();
				reg = RegAllocation.registerAllocate(roots);
				//System.out.println("registers:"+reg);
				for(int i = 0; i < roots.size(); i ++) {
					CFG.printDot(roots.get(i), "cfg"+i+".dot");
				}
			} else {
				// TODO: WHAT DOES REG NEED SET TO?
			}
			IRGen irgen = new IRGen(roots);
			seq = irgen.getIR();
			((CompUnitIR) seq).setListOfDVS(listOfDVS);
			((CompUnitIR) seq).setSizeSetup(sizeSetup);
			/*if (dumps.contains(dumpsEnum.AST_FINAL)){
				printTreeToStdOut(seq);
			} */
			
			seq = seq.foldCALL();
			seq = seq.foldESEQ();
			Optimize.foldConst(seq);
			Optimize.foldSEQ(seq);
			if (dumps.contains(dumpsEnum.IR_FINAL)){
				printTreeToStdOut(seq);
			}
			if(dumps.contains(dumpsEnum.INTERPRETER_FINAL) || interpretReturn) {
				Long retVal = interpret(seq);
				if(interpretReturn) {
					return retVal;
				}
			}
			if (dumps.contains(dumpsEnum.CFG_FINAL)){
				cfg = new CFG(seq.getChildren());
				roots = cfg.getHeadList();
				for(int i = 0; i < roots.size(); i ++) {
					CFG.printDotToStdOut(roots.get(i), roots.get(i).getIrnode().label());
				}
			}
			TempTable tTable = new TempTable();
			FileWriter f = new FileWriter(output);
			BufferedWriter b = new BufferedWriter(f);
			StaticStack.staticStackFrame(seq,reg);
			ArrayList<Tile> tiles = null;
			if(dumps.contains(dumpsEnum.WINDOWS)) {
				tiles = GenerateWindows.generateTile(seq,tTable).getTiles();
			} else {
				tiles = Generate.generateTile(seq,tTable).getTiles();
			}
			//Optimize.foldPushPop(tiles);
			//Optimize.foldMov(tiles);
			for(Tile tile : tiles) {
				b.write(tile.toString());
				b.newLine();
				//System.out.println(tile);
			}
			b.close();
			if(dumps.contains(dumpsEnum.EXECUTE)) {
				runProgram(output);
			}
			//There are a lot of print statements 
		}else{
			System.out.println("You dun goofed");
			printHelp();
		}
		return -1l;
	}
	
	public static long helperWindows(String source, String output, boolean interpretReturn) throws Exception{
		FileReader file = new FileReader(source);
		BufferedReader bf = new BufferedReader(file);
		XiParser parser_obj = (XiParser) (new XiParserFactory()).newParser(bf);
		AbstractSyntaxNode parseTree = null;
		parseTree = parser_obj.parse();
		CompUnitNode comp = (CompUnitNode) parseTree;
		SymbolTableCreator stc = new SymbolTableCreator();
		if (stc.interfaceCheck(source.substring(0,source.length()-3),comp)){
			if (dumps.contains(dumpsEnum.AST_INITIAL) || dumps.contains(dumpsEnum.AST_FINAL)){ // THESE SHOULD BE DIFFERENT CASES
				printTreeToStdOut(comp);
			}

			SyntaxIR seq = comp.translate(null);
			ArrayList<String> listOfDVS = ((CompUnitIR) seq).getListOfDVS();
			if (dumps.contains(dumpsEnum.IR_INITIAL)){
				printTreeToStdOut(seq);
			}
			//optimizations
			seq = seq.foldCALL();
			seq = seq.foldESEQ();
			Optimize.foldConst(seq);
			Optimize.foldSEQ(seq);
			//StaticStack.staticStackFrame(seq);
			//Optimize.foldCJump(seq);
			
			/*    PERHAPS --DUMP_IR INITIAL SHOULD PRINT THIS INSTEAD?
			if(tree.equals("--dump_ir")) {
				printTreeToStdOut(seq);
			}
			*/

			//UnComment the following two lines to run the interpreter
			int[][] s = new int[2][2];
			s[1][1] = 34;
			s[1][0] = 2;
			s[0][0] = 0;
			s[0][1] = 1;
			//InputInterpreter lir = new InputInterpreter(seq);
			//lir.runProgram(s);
			
			if(dumps.contains(dumpsEnum.INTERPRETER_INITIAL)) {
				printTreeToStdOut(seq);
				interpret(seq);
			}
			
			CFG cfg = new CFG(seq.getChildren());
			ArrayList<CFGNode> roots = cfg.getHeadList();
			if (dumps.contains(dumpsEnum.CFG_INITIAL)) {
				// TODO: PRINT CFG WHEREVER IT IS SUPPOSED TO BE PRINTED
			}
			Worklist worklist = new Worklist();
			if(isEnabled("copy"))
				worklist.copyPropagation(roots);
			worklist.setupDefUse(roots);
			worklist.liveVariableAnalysis();
			if(isEnabled("dce"))
				worklist.deadCodeElimination();
			/*
			for(int i = 0; i<roots.size();i++){
				HashSet<CFGNode> doneList = new HashSet<CFGNode>();
				roots.get(i).resetSpecialTemps();
				roots.get(i).fixMultipleDefs(doneList);
			}
			worklist.setupDefUse(roots);
			for(int i = 0; i<roots.size();i++){
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
			}*/
			worklist.setupDefUse(roots);
			if(isEnabled("cse"))
				worklist.commonSubexpressionElimination(roots);
			if(isEnabled("copy"))
				worklist.copyPropagation(roots);
				worklist.setupDefUse(roots);
				worklist.liveVariableAnalysis();
			if(isEnabled("dce"))
				worklist.deadCodeElimination();
			HashMap<String, HashMap<String, String>> reg = null;
			if(isEnabled("reg")) {
				//RegAllocation.printCheck(roots);
				/*reg = this is a hashmap that tells you what registers to allocate 
				 * to temps each string corresponds to the mangled name of 
				 * the function and their corresponding hashmap of temps
				 */
				worklist.setupDefUse(roots);
				worklist.liveVariableAnalysis();
				reg = RegAllocation.registerAllocate(roots);
				for(int i = 0; i < roots.size(); i ++) {
					CFG.printDot(roots.get(i), "cfg"+i+".dot");
				}
			} else {
				// TODO: WHAT DOES REG NEED SET TO?
			}
			IRGen irgen = new IRGen(roots);
			seq = irgen.getIR();
			((CompUnitIR) seq).setListOfDVS(listOfDVS);
			/*if (dumps.contains(dumpsEnum.AST_FINAL)){
				printTreeToStdOut(seq);
			} */
			
			seq = seq.foldCALL();
			seq = seq.foldESEQ();
			Optimize.foldConst(seq);
			Optimize.foldSEQ(seq);
			if (dumps.contains(dumpsEnum.IR_FINAL)){
				printTreeToStdOut(seq);
			}
			if(dumps.contains(dumpsEnum.INTERPRETER_FINAL) || interpretReturn) {
				long retVal = interpret(seq);
				if(interpretReturn) {
					return retVal;
				}
			}
			if (dumps.contains(dumpsEnum.CFG_FINAL)){
				cfg = new CFG(seq.getChildren());
				roots = cfg.getHeadList();
				
				// TODO: PRINT CFG
			}
			TempTable tTable = new TempTable();
			FileWriter f = new FileWriter(output);
			BufferedWriter b = new BufferedWriter(f);
			StaticStack.staticStackFrame(seq,reg);
			ArrayList<Tile> tiles = null;
			tiles = GenerateWindows.generateTile(seq,tTable).getTiles();
			//Optimize.foldPushPop(tiles);
			//Optimize.foldMov(tiles);
			for(Tile tile : tiles) {
				b.write(tile.toString());
				b.newLine();
				//System.out.println(tile);
			}
			b.close();
			if(dumps.contains(dumpsEnum.EXECUTE)) {
				runProgram(output);
			}
			//There are a lot of print statements 
		}else{
			System.out.println("You dun goofed");
			printHelp();
		}
		return -1;
	}

	public static void initOptionsSet() {
		options.add("reg");
		options.add("cse");
		options.add("dce");
		options.add("uce");
		options.add("cf");
		options.add("copy");
		options.add("alg");
		options.add("vn");
		options.add("licm");
		options.add("mc");
		options.add("pre");
		options.add("lu");
		options.add("inl");
	}
	
	public static boolean isEnabled(String s) {
		return options.contains(s);
	}
	
	public static void printTreeToStdOut(Object node) {
		if(node instanceof VisualizableTreeNode) {
			CodeWriterTreePrinter printer = new CodeWriterTreePrinter(System.out);
			printer.print((VisualizableTreeNode) node);
		} else if(node instanceof VisualizableIRNode) {
			CodeWriterIRPrinter printer = new CodeWriterIRPrinter(System.out);
			printer.print((VisualizableIRNode) node);
		} else {
			System.err.println("Couldn't print this type of tree " + node);
		}
	}
	
	private static long interpret(SyntaxIR seq) {
		Interpreter lir = new Interpreter(seq);
		return lir.runProgram();
		
	}
	
	public static void runProgram(String sFile) {
		try {
		Runtime rt = Runtime.getRuntime();
		String runtimeLocation = AsmChecker.getRuntimeLocation();
		File f = new File(sFile.substring(sFile.lastIndexOf(File.separator)+1,sFile.lastIndexOf(".")));
		if(f.exists()){
			f.delete();
		}
		File runtimeFile = new File(runtimeLocation);
		Process p = rt.exec(runtimeLocation + "/linkxi.sh -o " + 
				sFile.substring(sFile.lastIndexOf(File.separator)+1,sFile.lastIndexOf("."))
				+ " "
				+ sFile, null, runtimeFile);
		System.out.println("AAA");
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
			System.out.println("linking failed:"+str.toString());
			return;// "linking failed:"+str.toString();
		}
		System.out.println("BBB");
		p.destroy();
		p = rt.exec(runtimeLocation + "/"
				+ sFile.substring(sFile.lastIndexOf(File.separator)+1,sFile.lastIndexOf("."))
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
			System.out.println("Segmentation fault(probably):"+str.toString());
			return;
		}
		System.out.println("CCC");
		StringBuilder str = new StringBuilder();
		InputStream in = p.getInputStream();
		
		int c = in.read();
		
		while(c!= -1){
			str.append((char) c);
			c = in.read();
		}
		p.destroy();
		System.out.println(str.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void initForJUnit() {
		options = new HashSet<String>();
		dumps = new HashSet<dumpsEnum>();
		initOptionsSet();
	}
}
