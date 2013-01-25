package rak248.xi;

import edu.cornell.cs.cs4120.xi.Position;

public class Location implements Position{

	private String myUnit;
	private int myLineStart;
	private int myLineEnd;
	private int myColumnStart;
	private int myColumnEnd;
	
	public Location(String in_unit, int in_lineStart, int in_lineEnd, int in_columnStart, int in_columnEnd)
	{
		myUnit = in_unit;
		myLineStart = in_lineStart;
		myLineEnd = in_lineEnd;
		myColumnStart = in_columnStart;
		myColumnEnd = in_columnEnd;
	}
	
	public String unit(){
		return myUnit;
	}
	
	public int lineStart(){
		return myLineStart;
	}
	
	public int lineEnd(){
		return myLineEnd;
	}
	
	public int columnStart(){
		return myColumnStart;
	}
	
	public int columnEnd(){
		return myColumnEnd;
	}
	
	public void setMyColumnStart(int myColumnStart) {
		this.myColumnStart = myColumnStart;
	}

	public void setMyColumnEnd(int myColumnEnd) {
		this.myColumnEnd = myColumnEnd;
	}

	
	public boolean equals(Object that){
		if(that instanceof Position){
			Position newPosition = (Position) that;
			return newPosition.lineStart() == myLineStart && 
				   newPosition.lineEnd() == myLineEnd && 
				   newPosition.columnStart() == myColumnStart && 
				   newPosition.columnEnd() == myColumnEnd &&
				   (newPosition.unit()).equals(myUnit);
		}
		return false;
	}
	
	public String toString(){
		String ret = "(compUnit,Line,columnStart,columnEnd): " +
				"(" + myUnit + "," + myLineStart + "," + myColumnStart + "," + myColumnEnd + ")";
		return ret;
	}
}

	
