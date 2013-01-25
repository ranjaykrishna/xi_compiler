/* The following code was generated by JFlex 1.4.3 on 12/14/11 1:35 PM */

  package rak248.xi.lexer;
  import rak248.xi.lexer.*;
  import rak248.xi.parser.*;
  import java_cup.runtime.*;
  import java.io.*;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 12/14/11 1:35 PM from the specification file
 * <tt>MyLexer.flex</tt>
 */
public class JFlexLexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRING = 2;
  public static final int YYINITIAL = 0;
  public static final int CHARACTER = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\1\1\3\1\0\1\1\1\2\22\0\1\1\1\44\1\12"+
    "\2\0\1\43\1\50\1\7\1\54\1\55\1\41\1\37\1\61\1\40"+
    "\1\36\1\42\1\5\11\10\1\60\1\62\1\45\1\46\1\47\2\0"+
    "\32\4\1\52\1\11\1\53\1\0\1\6\1\0\1\24\1\22\1\33"+
    "\1\35\1\15\1\14\1\31\1\21\1\13\1\4\1\25\1\16\1\4"+
    "\1\30\1\32\2\4\1\23\1\17\1\26\1\27\1\4\1\20\1\34"+
    "\2\4\1\56\1\51\1\57\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\2\1\3\1\4\1\5\1\3\1\6"+
    "\13\2\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\1\31\1\32\1\33\1\34\2\35"+
    "\1\0\1\36\1\0\2\37\1\0\1\40\20\2\1\0"+
    "\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50"+
    "\1\51\1\52\1\53\1\54\1\55\1\56\10\45\1\57"+
    "\12\2\1\60\1\61\3\2\2\62\1\63\1\64\1\65"+
    "\1\66\1\67\1\70\1\71\1\72\1\2\1\73\4\2"+
    "\1\74\1\2\1\75\1\76\1\77\2\2\1\100\2\2"+
    "\1\101\1\102\1\2\1\103\2\2\1\104\1\105\1\2"+
    "\1\106\1\2\1\107";

  private static int [] zzUnpackAction() {
    int [] result = new int[145];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\63\0\146\0\231\0\314\0\231\0\231\0\231"+
    "\0\377\0\231\0\u0132\0\u0165\0\u0198\0\u01cb\0\u01fe\0\u0231"+
    "\0\u0264\0\u0297\0\u02ca\0\u02fd\0\u0330\0\231\0\231\0\231"+
    "\0\231\0\u0363\0\231\0\u0396\0\u03c9\0\u03fc\0\u042f\0\231"+
    "\0\231\0\231\0\231\0\231\0\231\0\231\0\231\0\231"+
    "\0\231\0\231\0\u0462\0\u0495\0\231\0\u04c8\0\231\0\u04fb"+
    "\0\u052e\0\231\0\u0561\0\314\0\u0594\0\u05c7\0\u05fa\0\u062d"+
    "\0\u0660\0\u0693\0\u06c6\0\u06f9\0\u072c\0\u075f\0\u0792\0\u07c5"+
    "\0\u07f8\0\u082b\0\u085e\0\u0891\0\u08c4\0\231\0\231\0\231"+
    "\0\231\0\231\0\231\0\231\0\231\0\231\0\231\0\231"+
    "\0\231\0\231\0\231\0\u08f7\0\u092a\0\u095d\0\u0990\0\u09c3"+
    "\0\u09f6\0\u0a29\0\u0a5c\0\314\0\u0a8f\0\u0ac2\0\u0af5\0\u0b28"+
    "\0\u0b5b\0\u0b8e\0\u0bc1\0\u0bf4\0\u0c27\0\u0c5a\0\314\0\314"+
    "\0\u0c8d\0\u0cc0\0\u0cf3\0\u0d26\0\231\0\231\0\231\0\231"+
    "\0\231\0\231\0\231\0\231\0\231\0\u0d59\0\314\0\u0d8c"+
    "\0\u0dbf\0\u0df2\0\u0e25\0\314\0\u0e58\0\314\0\314\0\314"+
    "\0\u0e8b\0\u0ebe\0\314\0\u0ef1\0\u0f24\0\314\0\314\0\u0f57"+
    "\0\314\0\u0f8a\0\u0fbd\0\314\0\314\0\u0ff0\0\314\0\u1023"+
    "\0\314";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[145];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\0\3\4\1\5\1\6\1\7\1\10\1\11\1\0"+
    "\1\12\1\13\1\14\1\15\1\16\1\5\1\17\1\5"+
    "\1\20\1\21\2\5\1\22\1\23\1\24\2\5\1\25"+
    "\2\5\1\26\1\27\1\30\1\31\1\32\1\33\1\34"+
    "\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\44"+
    "\1\45\1\46\1\47\1\50\1\51\1\52\2\53\1\54"+
    "\1\55\5\53\1\56\1\57\50\53\2\60\1\61\1\62"+
    "\3\60\1\0\1\60\1\63\51\60\67\0\5\5\2\0"+
    "\23\5\32\0\1\11\2\0\1\11\56\0\5\5\2\0"+
    "\1\5\1\64\13\5\1\65\5\5\31\0\5\5\2\0"+
    "\11\5\1\66\11\5\31\0\5\5\2\0\3\5\1\67"+
    "\15\5\1\70\1\5\31\0\5\5\2\0\2\5\1\71"+
    "\20\5\31\0\5\5\2\0\6\5\1\72\14\5\31\0"+
    "\5\5\2\0\10\5\1\73\6\5\1\74\3\5\31\0"+
    "\5\5\2\0\2\5\1\75\20\5\31\0\5\5\2\0"+
    "\6\5\1\76\1\5\1\77\12\5\31\0\5\5\2\0"+
    "\4\5\1\100\16\5\31\0\5\5\2\0\2\5\1\101"+
    "\11\5\1\102\6\5\31\0\5\5\2\0\3\5\1\103"+
    "\13\5\1\104\3\5\67\0\1\105\66\0\1\106\62\0"+
    "\1\107\62\0\1\110\62\0\1\111\14\0\2\53\2\0"+
    "\5\53\2\0\50\53\3\0\1\55\57\0\3\112\1\0"+
    "\3\112\1\113\1\112\1\114\1\115\1\112\1\116\5\112"+
    "\1\117\1\120\2\112\1\121\1\112\1\122\32\112\7\0"+
    "\1\123\56\0\1\62\57\0\3\112\1\0\3\112\1\124"+
    "\1\112\1\125\1\126\1\112\1\127\5\112\1\130\1\131"+
    "\2\112\1\132\1\112\1\133\32\112\4\0\5\5\2\0"+
    "\13\5\1\134\7\5\31\0\5\5\2\0\3\5\1\135"+
    "\17\5\31\0\5\5\2\0\4\5\1\136\16\5\31\0"+
    "\5\5\2\0\13\5\1\137\7\5\31\0\5\5\2\0"+
    "\15\5\1\140\5\5\31\0\5\5\2\0\1\141\22\5"+
    "\31\0\5\5\2\0\2\5\1\142\20\5\31\0\5\5"+
    "\2\0\17\5\1\143\3\5\31\0\5\5\2\0\13\5"+
    "\1\144\7\5\31\0\5\5\2\0\1\145\22\5\31\0"+
    "\5\5\2\0\14\5\1\146\6\5\31\0\5\5\2\0"+
    "\2\5\1\147\20\5\31\0\5\5\2\0\5\5\1\150"+
    "\15\5\31\0\5\5\2\0\3\5\1\151\17\5\31\0"+
    "\5\5\2\0\11\5\1\152\11\5\31\0\5\5\2\0"+
    "\15\5\1\153\5\5\25\0\2\105\1\154\1\155\57\105"+
    "\7\0\1\156\62\0\1\157\62\0\1\160\62\0\1\161"+
    "\62\0\1\162\62\0\1\163\62\0\1\164\62\0\1\165"+
    "\57\0\5\5\2\0\4\5\1\166\16\5\31\0\5\5"+
    "\2\0\2\5\1\167\20\5\31\0\5\5\2\0\2\5"+
    "\1\170\20\5\31\0\5\5\2\0\16\5\1\171\4\5"+
    "\31\0\5\5\2\0\3\5\1\172\17\5\31\0\5\5"+
    "\2\0\11\5\1\173\11\5\31\0\5\5\2\0\3\5"+
    "\1\174\17\5\31\0\5\5\2\0\14\5\1\175\6\5"+
    "\31\0\5\5\2\0\4\5\1\176\16\5\31\0\5\5"+
    "\2\0\2\5\1\177\20\5\31\0\5\5\2\0\3\5"+
    "\1\200\17\5\31\0\5\5\2\0\4\5\1\201\16\5"+
    "\31\0\5\5\2\0\13\5\1\202\7\5\30\0\1\155"+
    "\63\0\5\5\2\0\2\5\1\203\20\5\31\0\5\5"+
    "\2\0\15\5\1\204\5\5\31\0\5\5\2\0\13\5"+
    "\1\205\7\5\31\0\5\5\2\0\2\5\1\206\20\5"+
    "\31\0\5\5\2\0\12\5\1\207\10\5\31\0\5\5"+
    "\2\0\10\5\1\210\12\5\31\0\5\5\2\0\4\5"+
    "\1\211\16\5\31\0\5\5\2\0\1\212\22\5\31\0"+
    "\5\5\2\0\22\5\1\213\31\0\5\5\2\0\6\5"+
    "\1\214\14\5\31\0\5\5\2\0\15\5\1\215\5\5"+
    "\31\0\5\5\2\0\15\5\1\216\5\5\31\0\5\5"+
    "\2\0\4\5\1\217\16\5\31\0\5\5\2\0\14\5"+
    "\1\220\6\5\31\0\5\5\2\0\2\5\1\221\20\5"+
    "\25\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4182];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\11\1\1\3\11\1\1\1\11\13\1\4\11"+
    "\1\1\1\11\4\1\13\11\2\1\1\11\1\0\1\11"+
    "\1\0\1\1\1\11\1\0\21\1\1\0\16\11\31\1"+
    "\11\11\34\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[145];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
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



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public JFlexLexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public JFlexLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 130) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public XiSymbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 1: 
          { /* ignore */
          }
        case 72: break;
        case 19: 
          { return xiSymbol(Sym.OPEN_BRACKET,yytext());
          }
        case 73: break;
        case 29: 
          { throw new RuntimeException("Unterminated string at end of line:"+yyline);
          }
        case 74: break;
        case 68: 
          { return xiSymbol(Sym.LENGTH,yytext());
          }
        case 75: break;
        case 16: 
          { return xiSymbol(Sym.GT,yytext());
          }
        case 76: break;
        case 41: 
          { string.append( '\f' );
          }
        case 77: break;
        case 49: 
          { return xiSymbol(Sym.NEW,yytext());
          }
        case 78: break;
        case 65: 
          { return xiSymbol(Sym.WHILE,yytext());
          }
        case 79: break;
        case 35: 
          { return xiSymbol(Sym.EQUAL,yytext());
          }
        case 80: break;
        case 11: 
          { return xiSymbol(Sym.DIVIDE,yytext());
          }
        case 81: break;
        case 47: 
          { return xiSymbol(Sym.INT,yytext());
          }
        case 82: break;
        case 42: 
          { string.append( '\b' );
          }
        case 83: break;
        case 60: 
          { return xiSymbol(Sym.BOOL,yytext());
          }
        case 84: break;
        case 15: 
          { return xiSymbol(Sym.GETS,yytext());
          }
        case 85: break;
        case 3: 
          { return xiSymbol(Sym.INTEGER_LITERAL, Long.parseLong(yytext()));
          }
        case 86: break;
        case 46: 
          { yybegin(YYINITIAL); 
                                     return xiSymbol(Sym.CHAR_LITERAL, new Character(yytext().charAt(0)),1);
          }
        case 87: break;
        case 2: 
          { return xiSymbol(Sym.IDENTIFIER, yytext());
          }
        case 88: break;
        case 44: 
          { string.append( '\t' );
          }
        case 89: break;
        case 5: 
          { yybegin(CHARACTER);
          }
        case 90: break;
        case 25: 
          { return xiSymbol(Sym.COLON,yytext());
          }
        case 91: break;
        case 38: 
          { string.append( '\'' );
          }
        case 92: break;
        case 48: 
          { return xiSymbol(Sym.USE,yytext());
          }
        case 93: break;
        case 20: 
          { return xiSymbol(Sym.CLOSE_BRACKET,yytext());
          }
        case 94: break;
        case 10: 
          { return xiSymbol(Sym.TIMES,yytext());
          }
        case 95: break;
        case 34: 
          { return xiSymbol(Sym.LEQ,yytext());
          }
        case 96: break;
        case 22: 
          { return xiSymbol(Sym.CLOSE_PAREN,yytext());
          }
        case 97: break;
        case 7: 
          { return xiSymbol(Sym.PERIOD,yytext());
          }
        case 98: break;
        case 69: 
          { return xiSymbol(Sym.RETURN,yytext());
          }
        case 99: break;
        case 13: 
          { return xiSymbol(Sym.NOT,yytext());
          }
        case 100: break;
        case 71: 
          { return xiSymbol(Sym.CONTINUE,yytext());
          }
        case 101: break;
        case 33: 
          { return xiSymbol(Sym.NOT_EQUAL,yytext());
          }
        case 102: break;
        case 8: 
          { return xiSymbol(Sym.PLUS,yytext());
          }
        case 103: break;
        case 70: 
          { return xiSymbol(Sym.EXTENDS,yytext());
          }
        case 104: break;
        case 14: 
          { return xiSymbol(Sym.LT,yytext());
          }
        case 105: break;
        case 64: 
          { return xiSymbol(Sym.FALSE,yytext());
          }
        case 106: break;
        case 4: 
          { return xiSymbol(Sym.UNDERSCORE,yytext());
          }
        case 107: break;
        case 9: 
          { return xiSymbol(Sym.MINUS,yytext());
          }
        case 108: break;
        case 18: 
          { return xiSymbol(Sym.OR,yytext());
          }
        case 109: break;
        case 30: 
          { yybegin(YYINITIAL); 
	       return xiSymbol(Sym.STRING_LITERAL, string.toString(),string.length());
          }
        case 110: break;
        case 21: 
          { return xiSymbol(Sym.OPEN_PAREN,yytext());
          }
        case 111: break;
        case 26: 
          { return xiSymbol(Sym.COMMA,yytext());
          }
        case 112: break;
        case 61: 
          { return xiSymbol(Sym.THIS,yytext());
          }
        case 113: break;
        case 66: 
          { return xiSymbol(Sym.BREAK,yytext());
          }
        case 114: break;
        case 40: 
          { string.append( '\"' );
          }
        case 115: break;
        case 17: 
          { return xiSymbol(Sym.AND,yytext());
          }
        case 116: break;
        case 24: 
          { return xiSymbol(Sym.CLOSE_BRACE,yytext());
          }
        case 117: break;
        case 12: 
          { return xiSymbol(Sym.MODULO,yytext());
          }
        case 118: break;
        case 31: 
          { throw new RuntimeException("Unterminated character literal at end of line");
          }
        case 119: break;
        case 53: 
          { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\"'),1);
          }
        case 120: break;
        case 28: 
          { string.append(yytext());
          }
        case 121: break;
        case 51: 
          { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\''),1);
          }
        case 122: break;
        case 36: 
          { return xiSymbol(Sym.GEQ,yytext());
          }
        case 123: break;
        case 63: 
          { return xiSymbol(Sym.NULL,yytext());
          }
        case 124: break;
        case 43: 
          { string.append( '\r' );
          }
        case 125: break;
        case 62: 
          { return xiSymbol(Sym.TRUE,yytext());
          }
        case 126: break;
        case 39: 
          { string.append( '\\' );
          }
        case 127: break;
        case 6: 
          { yybegin(STRING); string.setLength(0);
          }
        case 128: break;
        case 59: 
          { return xiSymbol(Sym.ELSE,yytext());
          }
        case 129: break;
        case 45: 
          { string.append( '\n' );
          }
        case 130: break;
        case 52: 
          { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\\'),1);
          }
        case 131: break;
        case 23: 
          { return xiSymbol(Sym.OPEN_BRACE,yytext());
          }
        case 132: break;
        case 54: 
          { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\f'),1);
          }
        case 133: break;
        case 32: 
          { return xiSymbol(Sym.IF,yytext());
          }
        case 134: break;
        case 67: 
          { return xiSymbol(Sym.CLASS,yytext());
          }
        case 135: break;
        case 55: 
          { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\b'),1);
          }
        case 136: break;
        case 50: 
          { /* IGNORE */
          }
        case 137: break;
        case 27: 
          { return xiSymbol(Sym.SEMICOLON,yytext());
          }
        case 138: break;
        case 56: 
          { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\r'),1);
          }
        case 139: break;
        case 58: 
          { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\n'),1);
          }
        case 140: break;
        case 57: 
          { yybegin(YYINITIAL); return xiSymbol(Sym.CHAR_LITERAL, new Character('\t'),1);
          }
        case 141: break;
        case 37: 
          { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\"");
          }
        case 142: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new XiSymbol(Sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}