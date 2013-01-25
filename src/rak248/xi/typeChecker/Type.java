package rak248.xi.typeChecker;

import java.util.ArrayList;

import edu.cornell.cs.cs4120.xi.Position;

import rak248.xi.parser.ExpressionNode;
import rak248.xi.parser.IntegerLiteralNode;
import rak248.xi.typeChecker.Type.typeEnum;


public class Type {
	
	public static enum typeEnum {UNSPECIFIED, UNIT, INT, BOOL, INT_ARRAY, BOOL_ARRAY, FUNCTION, TUPLE, UNDERSCORE, VOID, ABSTRACT, ABSTRACT_ARRAY, NULL};
	
	public typeEnum type;
	// This is an array that represents the dimensionality and size of each
	// dimension of the array if it is not null.  In this case the size of the
	// array is the dimensionality and each value is the width of that dimension
	private int[] arraySize;
	private Type[] parameters;
	private Type[] returnType;
	private Type[] tupleList;
	private ArrayList<ExpressionNode> sizeList;
	private String object;
	
	public Type(typeEnum type){
		switch(type) {
		case NULL:
		case ABSTRACT:
			System.err.println("Error:abstract type using wrong constructor");
			break;
		case FUNCTION:
			System.err.println("Error:Function type using wrong constructor");
			break;
		case INT_ARRAY:
		case BOOL_ARRAY:
		case ABSTRACT_ARRAY:
			System.err.println("Error:Array type using wrong constructor");
			break;
		default:
			this.type  = type;
			arraySize  = null;
			parameters = null;
			returnType = null;
		}
	}
	
	//constructor for abstract and null
	public Type(typeEnum type, String value) {
		switch(type) {
		case FUNCTION:
			System.err.println("Error:Function type using wrong constructor");
			break;
		case INT_ARRAY:
		case BOOL_ARRAY:
			System.err.println("Error:Array type using wrong constructor");
			break;
		case INT:
		case BOOL:
			System.out.println("Error:int or bool type using the wrong constructor");
			break;
		default:
			this.type  = type;
			setObject(value);
			arraySize  = null;
			parameters = null;
			returnType = null;
		}
	}
	
	//Array constructor for parameters/return types where there will not be widths
	public Type(typeEnum type, int dimensionality, Position p) {
		switch(type) {
		case FUNCTION:
			System.err.println("Error:Function type using array constructor");
			break;

		case NULL:
		case ABSTRACT_ARRAY:
		case ABSTRACT:
			System.err.println("Error:Abstract type using wrong constructor");
			break;
		case INT_ARRAY:
		case BOOL_ARRAY:
			this.type = type;
			ArrayList<ExpressionNode> ar = new ArrayList<ExpressionNode>();
			int[] sizeArray = new int[dimensionality];
			for(int i = 0; i < dimensionality; i ++) {
				ar.add(new IntegerLiteralNode(0,p,p));
				sizeArray[i] = 0;
			}
			arraySize = sizeArray;
			break;
		default:
			System.err.println("Error: Primative type using array constructor");
		}
	}
	
	public Type(typeEnum type, int dimensionality, Position p, String name) {
		if(type == typeEnum.ABSTRACT_ARRAY) {
			this.type = type;
			ArrayList<ExpressionNode> ar = new ArrayList<ExpressionNode>();
			int[] sizeArray = new int[dimensionality];
			for(int i = 0; i < dimensionality; i ++) {
				ar.add(new IntegerLiteralNode(0,p,p));
				sizeArray[i] = 0;
			}
			object = name;
			arraySize = sizeArray;
			return;
		}
		
		System.err.println("Error: constructor should only be called on abstract arrays");
	}
	
	public Type(typeEnum type, int indices) {
		switch(type) {
		case FUNCTION:
			System.err.println("Error:Function type using array constructor");
			break;
		case INT_ARRAY:
		case BOOL_ARRAY:
			this.type = type;
			int[] sizeArray = new int[indices];
			for(int i = 0; i < indices; i ++) {
				sizeArray[i] = 0;
			}
			arraySize = sizeArray;
			break;
		default:
			System.err.println("Error: Primative type using array constructor");
		}
	}
	
	//Array constructor for actual declarations of arrays
/*	public Type(typeEnum type, ArrayList<Integer> a) {
		switch(type) {
		case FUNCTION:
			System.err.println("Error:Function type using array constructor");
			break;
		case INT_ARRAY:
		case BOOL_ARRAY:
			this.type = type;
			arraySize = new int[a.size()];
			for(int i = 0; i<a.size();i++){
				arraySize[i] = a.get(i);
			}
			break;
		default:
			System.err.println("Error: Primative type using array constructor");
		}
	}*/
	
	public Type(typeEnum type, Type[] parameters, Type[] returnType){
		switch(type) {
		case FUNCTION:
			this.type = type;
			this.parameters = parameters;
			this.returnType = returnType;
			break;
		case INT_ARRAY:
		case BOOL_ARRAY:
			System.err.println("Error: Array type using function constructor");
			break;
		default:
			System.err.println("Error: Primative type using function constructor");
		}
	}
	
	/*
	 * This constructor is used for assignments.  It is of type unit
	 * and stores a list of Type parameters
	 */
	public Type(typeEnum type, Type[] types) {
		this.type = type;
		this.tupleList = types;
	}

	public Type(typeEnum type, ArrayList<ExpressionNode> b) {
		switch(type) {
		case FUNCTION:
		case NULL:
		case ABSTRACT:
		case ABSTRACT_ARRAY:
			System.err.println("function or abstract type using array constructor");
			break;
		case INT_ARRAY:
		case BOOL_ARRAY:
			this.type = type;
			int dimensionality = b.size();
			int[] sizeArray = new int[dimensionality];
			for(int i = 0; i < dimensionality; i ++) {
				sizeArray[i] = 0;
			}
			arraySize = sizeArray;
			sizeList = b;
			break;
		default:
			System.err.println("Error: Primative type using array constructor");
		}		
	}

	public Type(typeEnum type, ArrayList<ExpressionNode> b,String object2) {
		switch(type) {
		case FUNCTION:
		case INT_ARRAY:
		case BOOL_ARRAY:
		case ABSTRACT:
		case NULL:
			System.err.println("abstract array constructor used by non-abstract array");
			break;
		case ABSTRACT_ARRAY:
			this.type = type;
			object = object2;
			int dimensionality = b.size();
			int[] sizeArray = new int[dimensionality];
			for(int i = 0; i < dimensionality; i ++) {
				sizeArray[i] = 0;
			}
			arraySize = sizeArray;
			sizeList = b;
			break;
		default:
			System.err.println("abstract array constructor used by non-abstract array");
		}		
	}

	public Type(typeEnum type, Integer indices, String object2) {
		switch(type) {
		case FUNCTION:
			System.err.println("Error:Function type using array constructor");
			break;
		case INT_ARRAY:
		case BOOL_ARRAY:
			System.err.println("Error:primitive array type using abstract array constructor");
			break;
		case ABSTRACT_ARRAY:
			this.type = type;
			object = object2;
			int[] sizeArray = new int[indices];
			for(int i = 0; i < indices; i ++) {
				sizeArray[i] = 0;
			}
			arraySize = sizeArray;
			break;
		default:
			System.err.println("Error: Primative type using array constructor");
		}
	}

	public int[] getArraySize() {
		return arraySize;
	}
	
	public ArrayList<ExpressionNode> getSizeList() {
		return sizeList;
	}
	
	public Type[] getParameters() {
		return parameters;
	}
	
	public Type[] getReturnTypes() {
		return returnType;
	}
	
	public Type[] getTupleList() {
		return tupleList;
	}
	
	public boolean isBool() {
		return this.type == typeEnum.BOOL;
	}
	
	public boolean isInt() {
		return this.type == typeEnum.INT;
	}
	
	public boolean isArray() {
		return this.type == typeEnum.INT_ARRAY || this.type == typeEnum.BOOL_ARRAY || this.type == typeEnum.ABSTRACT_ARRAY || this.type == typeEnum.NULL;
	}
	
	public boolean isIntArray() {
		return this.type == typeEnum.INT_ARRAY || this.type == typeEnum.NULL;
	}
	
	public boolean isBoolArray() {
		return this.type == typeEnum.BOOL_ARRAY || this.type == typeEnum.NULL;
	}
	
	public boolean isUnit() {
		return this.type == typeEnum.UNIT;
	}
	
	public boolean isAbstract() {
		return this.type == typeEnum.ABSTRACT || this.type == typeEnum.NULL;
	}
	
	public boolean isTuple () {
		return this.type == typeEnum.TUPLE;
	}
	
	public boolean isFunction() {
		return this.type == typeEnum.FUNCTION;
	}
	
	public boolean isUnderscore() {
		return this.type == typeEnum.FUNCTION;
	}
	
	public boolean isVoid() {
		return this.type == typeEnum.VOID;
	}
	
	public boolean isNull() {
		return this.type == typeEnum.NULL;
	}
	
	public String dimToBrackets() {
		String out = "";
		for(int i = 0; i < arraySize.length; i++) {
			out += "[]";
		}
		return out;
	}
	
	public String funcToString() {
		String out = "(";
		for(Type p : parameters) {
			out += p.toString() + ", ";
		}
		
		if(out.length() != 1) {
			out = out.substring(0, out.length() - 2);
		}else{
			out+="unit";
		}
		
		out += ")->(";
		for(Type r : returnType) {
			out += r.toString() + ", ";
		}
		
		if(returnType.length != 0) {
			out = out.substring(0, out.length() - 2);
		} else {
			out += "unit";
		}
		return out+")";
	}
	
	public String tupleToString() {
		String out = "(";
		if(tupleList.length != 0) {
			for(Type t : tupleList) {
				out += t.toString() + ", ";
			}
			out = out.substring(0, out.length() - 2);
		}
		return out + ")";
	}
	
	public String toString() {
		switch(type) {
		case UNSPECIFIED:
			return "unspecified";
		case UNIT:
			return "unit";
		case INT:
			return "int";
		case ABSTRACT:
			return object;
		case VOID:
			return "void";
		case BOOL:
			return "bool";
		case INT_ARRAY:
			return "int"+dimToBrackets();
		case BOOL_ARRAY:
			return "bool"+dimToBrackets();
		case ABSTRACT_ARRAY:
			return object+dimToBrackets();
		case FUNCTION:
			return funcToString();
		case TUPLE:
			return tupleToString();
		case NULL:
			return "null";
		}
		return "Didn't match switch in Type toString";
	}
	
	/* read this as ____ is a _____
	 * i.e. if A extends B:
	 * 			A.equals(B) returns true
	 * 			B.equals(A) returns false
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if(o instanceof Type) {
			Type t = (Type) o;
			if(this.type == typeEnum.UNDERSCORE || t.type == typeEnum.UNDERSCORE) {
				return true;
			}
			
			if(this.type != t.type) {
				return false;
			}
			
			if(this.type == typeEnum.ABSTRACT) {
				if(this.object.equals(t.getObject())) {
					return true;
				} else {
					if(t.isNull()){
						return true;
					}
					String parent = SymbolTable.getInheritanceTree().get(this.object);
					if(parent == null) {
						return false;
					} else {
						return new Type(typeEnum.ABSTRACT, parent).equals(o);
					}
					
				}
			}
			
			if(this.isNull()){
				if(t.isAbstract()||t.isArray()){
					return true;
				}
				return false;
			}
			
			if(t.isNull()){
				if(this.isAbstract()||this.isArray()){
					return true;
				}
				return false;
			}
			
			if(this.type == typeEnum.FUNCTION) {
				if(this.parameters.length != t.parameters.length || this.returnType.length != t.returnType.length)
					return false;
				
				for(int i = 0; i < this.parameters.length; i++) {
					// next line needs to change if functions aren't allowed
					if(!this.parameters[i].equals(t.parameters[i])) {
						return false;
					}
				}
				for(int i = 0; i < this.returnType.length; i++) {
					// next line needs to change if functions aren't allowed
					if(!this.returnType[i].equals(t.returnType[i])) {
						return false;
					}
				}
				return true;
			}
			
			// Doesn't check to see if each dimension is same size but that's the correct
			// behavior in my opinion.  Considering
			// String[] s1 = new String[5];
			// String[] s2 = new String[4];
			// s1 = s2; // doesn't throw a type error
			if(this.type == typeEnum.INT_ARRAY || this.type == typeEnum.BOOL_ARRAY || this.type == typeEnum.ABSTRACT_ARRAY) {
				if(t.isNull()){
					return true;
				}
				//if(this.type == typeEnum.ABSTRACT_ARRAY && !(new Type(typeEnum.ABSTRACT, this.getObject())).equals(new Type(typeEnum.ABSTRACT, t.getObject()))) {
				// MY CURRENT UNDERSTANDING IS THAT ARRAY TYPES ARE ONLY CONSIDERED EQUALLY IF THEY ARE THE SAME CLASS
				// SO ESSENTIALLY IGNORE SUBTYPING
				if(this.type == typeEnum.ABSTRACT_ARRAY && !this.getObject().equals(t.getObject())) {
					return false;
				}
				return this.arraySize.length == t.arraySize.length;
			}
			
			if(this.type == typeEnum.TUPLE) {
				if(this.tupleList.length != t.tupleList.length) {
					return false;
				}
				for(int i = 0; i < this.tupleList.length; i++) {
					if(!this.tupleList[i].equals(t.tupleList[i])) {
						return false;
					}
				}
				return true;
			}
			return true;
		}
		return false;
	}

	public Type subtractDimensions(int i, Position p) {
		int outputDimensions = arraySize.length - i;
		if(this.type == typeEnum.INT_ARRAY) {
			if(outputDimensions == 0){
				return new Type(typeEnum.INT);
			} else {
				return new Type(typeEnum.INT_ARRAY, outputDimensions, p);
			}
		} else if(this.type == typeEnum.BOOL_ARRAY) {
			if(outputDimensions == 0){
				return new Type(typeEnum.BOOL);
			} else {
				return new Type(typeEnum.BOOL_ARRAY, outputDimensions, p);
			}
		} else if(this.type == typeEnum.ABSTRACT_ARRAY) {
			if(outputDimensions == 0){
				return new Type(typeEnum.ABSTRACT, object);
			} else {
				return new Type(typeEnum.ABSTRACT_ARRAY, outputDimensions, p, object);
			}
		}
		System.err.println("Calling subtractDimensions on a non array");
		return null;
	}

	public Type addDimension(Position p) {
		if(isArray()) {
			if(this.type == typeEnum.ABSTRACT_ARRAY) {
				return new Type(type, arraySize.length+1, p, object);
			}
			return new Type(type, arraySize.length+1, p);
		} else {
			if(isInt()) {
				return new Type(typeEnum.INT_ARRAY, 1, p);
			} else if(isBool()) {
				return new Type(typeEnum.BOOL_ARRAY, 1, p);
			}else if(isAbstract()) {
				return new Type(typeEnum.ABSTRACT_ARRAY, 1, p, object);
			} else {
				System.err.println("Trying to add a dimension/create an array of a non int/bool/abstract");
				return null;
			}
		}
	}
	
	public typeEnum getTypeEnum(){
		return type;
	}
	
	public int getDimensionality(){
		return arraySize.length;
	}
	
	public int getTupleSize(){
		return tupleList.length;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getObject() {
		return object;
	}
	
}
