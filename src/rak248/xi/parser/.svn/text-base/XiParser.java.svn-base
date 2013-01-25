package rak248.xi.parser;

import edu.cornell.cs.cs4120.xi.AbstractSyntaxNode;
import edu.cornell.cs.cs4120.xi.CompilationException;
import edu.cornell.cs.cs4120.xi.parser.Parser;
import java_cup.runtime.Symbol;
import rak248.xi.lexer.JFlexLexer;

public class XiParser implements Parser{
	
	CupParser parser;
	
	public XiParser(JFlexLexer lexer){
		parser = new CupParser(lexer);
	}

	@Override
	public AbstractSyntaxNode parse() throws CompilationException {
		Symbol parseTree = null;
		try {
			parseTree = parser.parse();
		} catch (Exception e) {
			e.printStackTrace();
			//System.err.println("exception");
			//This will be a runtime Error for a fatal syntax error
			//It prints out the error message by itself
		}
		return (CompUnitNode) parseTree.value;
	}

}
