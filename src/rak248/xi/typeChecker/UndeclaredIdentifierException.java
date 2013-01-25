package rak248.xi.typeChecker;

import edu.cornell.cs.cs4120.xi.CompilationException;
import rak248.xi.parser.VarNode;

public class UndeclaredIdentifierException extends CompilationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UndeclaredIdentifierException(VarNode id){
		super("Identifer:"+id.getLabel()+" not found in scope at position:"+id.position().toString(),id.position());
		if(id.position()==null){
			System.err.println("ther is no position");
		}
		
	}
	
}
