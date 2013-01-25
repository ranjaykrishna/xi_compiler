package rak248.xi.lexer;

import edu.cornell.cs.cs4120.xi.Position;
import rak248.xi.Location;

public class XiSymbol extends java_cup.runtime.Symbol{
	
	
	private int myType;
	private Position myPosition;
	private String myValue;
	public Object value;
	
	public int getType(){
		return myType;
	}
	
	public XiSymbol(int in_type, Object in_value, int line, int cc, int cc1, String compUnit)
	{
		super(in_type);
		myPosition = new Location(compUnit,line,line,cc,cc1);
	    super.left = myPosition.columnStart();
		super.right = myPosition.columnEnd();
		myType = in_type;
		myValue = in_value + "";
		super.value = this;
		this.value = this;
	}
	
	public XiSymbol(int in_type)
	{
		super(in_type);
		myType = in_type;
	}
	
	public int type(){
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
		if(otherToken instanceof XiSymbol){
			XiSymbol newToken = (XiSymbol)otherToken;
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
