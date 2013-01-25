package rak248.util;

/**
 * Pretty-prints a tree to some provided output.
 */
public interface IRPrinter {
	  /**
	   * Prints a tree.
	   * 
	   * @param program
	   *          the program to print
	   */
	  void print(VisualizableIRNode tree);

}
