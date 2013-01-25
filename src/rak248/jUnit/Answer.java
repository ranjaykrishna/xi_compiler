package rak248.jUnit;

public class Answer {
	public boolean bool;
	public int integer;
	private boolean isBool;
	
	public Answer(int i){
		integer = i;
		isBool = false;
	}
	
	public Answer(boolean b){
		bool = b;
		isBool = true;
	}
	
	public boolean equals(Answer ans){
		if (!isBool){
			return ans.integer == integer;
		}
		else {
			return ans.bool == bool;
		}
	}
}
