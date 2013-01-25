  package rak248.xi.lexer;
  import rak248.xi.lexer.*;
  import rak248.xi.parser.*;
  import java_cup.runtime.*;
  import java.io.*;
%%

%public
%class JFlexLexer
%type XiSymbol
%function next_token

%unicode
%pack
%line
%char
%column

%cupsym Sym
%cup

%{
  StringBuffer string = new StringBuffer();

  private XiSymbol next;
  private String compUnit;
  
  public JFlexLexer(String compUnit, Reader reader) throws IOException {
	  this(reader);
	  this.compUnit = compUnit;
	  next = next_token();
  }
  
  public JFlexLexer(String compUnit, InputStream reader) throws IOException {
	  this(reader);
	  this.compUnit = compUnit;
	  next = next_token();
  }
  
  public XiSymbol next() {
	  XiSymbol old_next = next;
	  try {
		  next = next_token();
	  } catch (IOException e) {
		  System.out.println("Shits going down!!!");
		  e.printStackTrace();
	  }
	  return old_next;
  }
  
  public XiSymbol getNext(){
	  return next;
  }
  
  public boolean hasNext() {
	  return next.getType() != Sym.EOF;
  }
  
  public void remove() {
	  throw new UnsupportedOperationException();
  }

  private XiSymbol xiSymbol(int sym, Object value) {
          return new XiSymbol(sym,value,yyline+1,yycolumn+1,yycolumn+1+yylength(),compUnit);
  }

  private XiSymbol xiSymbol(int sym, Object value,int length) {
          return new XiSymbol(sym,value,yyline+1,yycolumn+1,yycolumn+1+length,compUnit);
  }

%}

Whitespace = [ \t\f\r\n]
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Letter = [a-zA-Z]
Digit = [0-9]
Identifier = {Letter}({Digit}|{Letter}|_|')*
Integer = [1-9]{Digit}*|0
SingleCharacter = [^\r\n\'\\]
StringCharacter = [^\r\n\"\\]

%state STRING,CHARACTER

%%
  
<YYINITIAL> {
  
  {Whitespace}  { /* ignore */ }
  "if"          { return xiSymbol(Sym.IF,yytext()); }
  "else"        { return xiSymbol(Sym.ELSE,yytext()); }
  "while"       { return xiSymbol(Sym.WHILE,yytext()); }
  "break"       { return xiSymbol(Sym.BREAK,yytext()); }
  "return"       { return xiSymbol(Sym.RETURN,yytext()); }
  "use"       { return xiSymbol(Sym.USE,yytext()); }
  "length"       { return xiSymbol(Sym.LENGTH,yytext()); }
  "int"       { return xiSymbol(Sym.INT,yytext()); }
  "bool"       { return xiSymbol(Sym.BOOL,yytext()); }
  "true"       { return xiSymbol(Sym.TRUE,yytext()); }
  "false"       { return xiSymbol(Sym.FALSE,yytext()); }
  "class"       { return xiSymbol(Sym.CLASS,yytext()); }
  "extends"     { return xiSymbol(Sym.EXTENDS,yytext()); }
  "new"         { return xiSymbol(Sym.NEW,yytext()); }
  "this"        { return xiSymbol(Sym.THIS,yytext()); }
  "null"        { return xiSymbol(Sym.NULL,yytext()); }
  "continue"    { return xiSymbol(Sym.CONTINUE,yytext()); }
  "."           { return xiSymbol(Sym.PERIOD,yytext()); }
  "+"       { return xiSymbol(Sym.PLUS,yytext()); }
  "-"       { return xiSymbol(Sym.MINUS,yytext()); }
  "*"       { return xiSymbol(Sym.TIMES,yytext()); }
  "/"       { return xiSymbol(Sym.DIVIDE,yytext()); }
  "%"       { return xiSymbol(Sym.MODULO,yytext()); }
  "!"       { return xiSymbol(Sym.NOT,yytext()); }
  "<"       { return xiSymbol(Sym.LT,yytext()); }
  "<="       { return xiSymbol(Sym.LEQ,yytext()); }
  ">"       { return xiSymbol(Sym.GT,yytext()); }
  ">="       { return xiSymbol(Sym.GEQ,yytext()); }
  "&"       { return xiSymbol(Sym.AND,yytext()); }
  "|"       { return xiSymbol(Sym.OR,yytext()); }
  "=="       { return xiSymbol(Sym.EQUAL,yytext()); }
  "!="       { return xiSymbol(Sym.NOT_EQUAL,yytext()); }
  "="       { return xiSymbol(Sym.GETS,yytext()); }
  "["       { return xiSymbol(Sym.OPEN_BRACKET,yytext()); }
  "]"       { return xiSymbol(Sym.CLOSE_BRACKET,yytext()); }
  "("       { return xiSymbol(Sym.OPEN_PAREN,yytext()); }
  ")"       { return xiSymbol(Sym.CLOSE_PAREN,yytext()); }
  "{"       { return xiSymbol(Sym.OPEN_BRACE,yytext()); }
  "}"       { return xiSymbol(Sym.CLOSE_BRACE,yytext()); }
  ":"       { return xiSymbol(Sym.COLON,yytext()); }
  ","       { return xiSymbol(Sym.COMMA,yytext()); }
  ";"       { return xiSymbol(Sym.SEMICOLON,yytext()); }
  "_"       { return xiSymbol(Sym.UNDERSCORE,yytext()); }
  \"        { yybegin(STRING); string.setLength(0); }
  \'        { yybegin(CHARACTER); }
  "//"{InputCharacter}*{LineTerminator}      { /* IGNORE */}
  {Identifier}  { return xiSymbol(Sym.IDENTIFIER, yytext()); }
  {Integer}     { return xiSymbol(Sym.INTEGER_LITERAL, Long.parseLong(yytext())); }
}

<STRING> {
  \"       { yybegin(YYINITIAL); 
	       return xiSymbol(Sym.STRING_LITERAL, string.toString(),string.length());
	   }
  {StringCharacter}+ {string.append(yytext());}

  /*escape sequences */
  "\\b"		{ string.append( '\b' ); }
  "\\t"		{ string.append( '\t' ); }
  "\\n"		{ string.append( '\n' ); }
  "\\f"		{ string.append( '\f' ); }
  "\\r"		{ string.append( '\r' ); }
  "\\\""	{ string.append( '\"' ); }
  "\\'"		{ string.append( '\'' ); }
  "\\\\"	{ string.append( '\\' ); }

  /* error cases */
  \\.		{ throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator} { throw new RuntimeException("Unterminated string at end of line:"+yyline); }
}

<CHARACTER> {
  {SingleCharacter}\'            { yybegin(YYINITIAL); 
                                     return xiSymbol(Sym.CHAR_LITERAL, new Character(yytext().charAt(0)),1);
				 }
  
  /* escape sequences */
  "\\b"\'                        { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\b'),1);}
  "\\t"\'                        { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\t'),1);}
  "\\n"\'                        { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\n'),1);}
  "\\f"\'                        { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\f'),1);}
  "\\r"\'                        { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\r'),1);}
  "\\\""\'                       { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\"'),1);}
  "\\'"\'                        { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\''),1);}
  "\\\\"\'                       { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\\'),1);}
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated character literal at end of line"); }
}
