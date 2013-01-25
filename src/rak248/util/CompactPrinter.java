package rak248.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import edu.cornell.cs.cs4120.util.TreePrinter;

import polyglot.util.CodeWriter;
import polyglot.util.OptimalCodeWriter;


/**
 * An {@linkplain TreePrinter} implementation designed to print IRs through a
 * provided {@link CodeWriter}.
 */
public class CompactPrinter implements IRPrinter{
	private final Writer writer;
	
	  /**
	   * Constructs a new {@linkplain TreePrinter} instance that prints programs
	   * using the given {@code CodeWriter}.
	   *
	   * @param writer
	   *          the {@code CodeWriter} to print to
	   */
	  public CompactPrinter(Writer writer) {
	    this.writer = writer;
	  }
	  
	  /**
	   * Constructs a new {@linkplain TreePrinter} instance that prints programs
	   * using to the given stream. Output is kept to 80 columns.
	   *
	   * @param o
	   *          the output stream to print to
	   */
	 /*public CodeWriterIRPrinter(OutputStream o) {
	    this(new OptimalCodeWriter(o, 80));
	  }*/
	 
	  
	@Override
	public void print(VisualizableIRNode node) {
		   try {
			printMaster(node);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		    try {
		      writer.flush();
		    } catch (IOException e) {
		      throw new RuntimeException(e);
		    }
		
	}
	
	private void printMaster(VisualizableIRNode node) throws IOException {
		writer.write("(");
	    writer.write(node.label());
	    writer.write("\n");
	    for (VisualizableIRNode child : node.children()) {
		    writer.write("\t");
		    printHelper(child);
		    writer.write("\n");
		}
	    writer.write(")");
	    writer.write("\n");
	}

	private void printHelper(VisualizableIRNode node) throws IOException {
		writer.write("(");
	    writer.write(node.label());
	    for (VisualizableIRNode child : node.children()) {
	    writer.write("\t");
	      printHelper(child);
	    }
	    writer.write(")");
	}

}
