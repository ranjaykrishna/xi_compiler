package rak248.xi.lexer;

import edu.cornell.cs.cs4120.xi.Position;
import edu.cornell.cs.cs4120.xi.lexer.Token;
import edu.cornell.cs.cs4120.xi.lexer.TokenType;
import rak248.xi.*;

public class JFlexToken implements Token{
	
	private TokenType myType;
	private Position myPosition;
	private String myValue;
	
	public JFlexToken(TokenType in_type)
	{
		myType = in_type;
	}
	
	public JFlexToken(TokenType in_type, Object in_value, int line, int cc, int cc1, String compUnit)
	{
		myPosition = new Location(compUnit,line,line,cc,cc1);
		myType = in_type;
		myValue = in_value + "";
	}
	
	public TokenType type(){
		return myType;
	}
	
	public Position position(){
		return myPosition;
	}
	
	public String value(){
		return myValue;
	}
	
	public boolean equals(Object otherToken)
	{
		if(otherToken instanceof JFlexToken){
			JFlexToken newToken = (JFlexToken)otherToken;
			return newToken.type() == myType && 
				   newToken.position().equals(myPosition) && 
				   newToken.value().equals(myValue);
		}
		else return false;
	}
	
	public String toString(){
		String ret = "Type: " + myType + ", value: " + myValue; 
		if (myPosition != null) ret += ", position: " + myPosition.toString(); 
		return ret;
	}
}
