package rak248.testing;

import java.io.IOException;
import java.io.Reader;

import edu.cornell.cs.cs4120.xi.parser.Parser;
import edu.cornell.cs.cs4120.xi.testing.ParserFactory;

import rak248.xi.lexer.JFlexLexer;
import rak248.xi.parser.XiParser;


public class XiParserFactory implements ParserFactory{

	@Override
	public Parser newParser(Reader reader) {
		JFlexLexer lex = null;
		try {
			lex = new JFlexLexer("factory not given source name",reader);
		} catch (IOException e) {
			System.out.println("IOException during factory creation");
			e.printStackTrace();
		}
    	XiParser parser = new XiParser(lex);
		return parser;
	}

}
