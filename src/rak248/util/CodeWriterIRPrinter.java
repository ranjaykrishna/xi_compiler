package rak248.util;

import java.io.IOException;
import java.io.OutputStream;

import edu.cornell.cs.cs4120.util.TreePrinter;

import polyglot.util.CodeWriter;
import polyglot.util.OptimalCodeWriter;

/**
 * An {@linkplain TreePrinter} implementation designed to print IRs through a
 * provided {@link CodeWriter}.
 */
public class CodeWriterIRPrinter implements IRPrinter{
	private final CodeWriter writer;
	
	  /**
	   * Constructs a new {@linkplain TreePrinter} instance that prints programs
	   * using the given {@code CodeWriter}.
	   *
	   * @param writer
	   *          the {@code CodeWriter} to print to
	   */
	  public CodeWriterIRPrinter(CodeWriter writer) {
	    this.writer = writer;
	  }
	  
	  /**
	   * Constructs a new {@linkplain TreePrinter} instance that prints programs
	   * using to the given stream. Output is kept to 80 columns.
	   *
	   * @param o
	   *          the output stream to print to
	   */
	 public CodeWriterIRPrinter(OutputStream o) {
	    this(new OptimalCodeWriter(o, 80));
	  }
	 
	  
	@Override
	public void print(VisualizableIRNode node) {
		   printHelper(node);
		    try {
		      writer.flush();
		    } catch (IOException e) {
		      throw new RuntimeException(e);
		    }
		
	}

	private void printHelper(VisualizableIRNode node) {
		writer.write("(");
	    writer.write(node.label());
	    for (VisualizableIRNode child : node.children()) {
	      writer.allowBreak(4);
	      writer.begin(4);
	      printHelper(child);
	      writer.end();
	    }
	    writer.write(")");
	    //writer.write("\n");
	    writer.allowBreak(0);
	}

}
