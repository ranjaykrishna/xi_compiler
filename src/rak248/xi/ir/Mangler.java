package rak248.xi.ir;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rak248.xi.typeChecker.Type;
import rak248.xi.typeChecker.TypeError;
import rak248.xi.typeChecker.Type.typeEnum;

public class Mangler {
	
	public static String mangle(String id, Type t) {
		List<Type> returnTypes = Arrays.asList(t.getReturnTypes());
		List<Type> arrayParams =  Arrays.asList(t.getParameters());
		return mangle(id,returnTypes,arrayParams);
	}	
	
	public static String mangle(String label,List<Type> returnTypes, List<Type> arrayParams){
		//add the beginning before name
		String name = "_I";
		//add the name
		for (char s: label.toCharArray()){
			if (s == '_'){
				name = name + "__";
			}
			else{
				name = name + s;
			}
		}
		//add end after the name
		name = name + "_";
		//add the return type
		if (returnTypes.size() == 0){
			name = name + "p";
		}
		else{
			name = name + manglerets(label,returnTypes);
		}
		//add the parameters
		name = name + mangleAll(label,arrayParams);
		//return
		return name;
	}
	
	public static String mangleAll(String label,List<Type> arrayParams){
		String name = "";
		for (Type type: arrayParams){
			try {
				name = name + mangleType(label,type);
			} catch (TypeError e) {
				e.printStackTrace();
			}
		}
		return name;
	}
	
	public static String manglerets(String label,List<Type> returnTypes){
		String name = "";
		for (Type type: returnTypes){
			try {
				name = name + mangleType(label,type);
			} catch (TypeError e) {
				e.printStackTrace();
			}
		}
		if (returnTypes.size()==1){
			return name;
		}
		else{
			String out = "t"+name.length()+name;
			return out;
		}
	}
	
	public static String mangleType(String label,Type type) throws TypeError{
		//System.err.println(type);
		switch(type.getTypeEnum()) {
		case UNSPECIFIED:
			throw new TypeError(label + "has unspecified type");
		case UNIT:
			return "p";
		case INT:
			return "i";
		case VOID:
			return "p";
		case BOOL:
			return "b";
		case INT_ARRAY:
			String output ="";
			for (int i = 0; i < type.getDimensionality(); i ++){
				output = output+"a";
			}
			return output+"i";
		case BOOL_ARRAY:
			String output2 ="";
			for (int i = 0; i < type.getDimensionality(); i ++){
				output2 = output2+"a";
			}
			return output2+"b";
		case ABSTRACT_ARRAY:
			String output3="";
			for(int i = 0; i < type.getDimensionality(); i++){
				output3= output3+"a";
			}
			return output3+mangleAbstract(type);
		case ABSTRACT:
			return mangleAbstract(type);
		case FUNCTION:
			throw new TypeError(label + "has function type");
		case TUPLE:
			String tups = "t"+type.getTupleSize();
			for (Type t: type.getTupleList()){
				mangleType(label,t);
			}
			return tups;
		}
		return null;
	}
	
	public static String mangleAbstract(Type t){
		String ret = "o";
		String obj = t.getObject();
		obj.replaceAll("_", "__");
		ret += obj.length();
		ret += obj;
		return ret;
	}
	
	public static Type Demangler(LabelIR label){
		ArrayList<Type> ret = new ArrayList<Type>();
		ArrayList<Type> params = new ArrayList<Type>();
		Type t;
		if (label.getName().startsWith("_I")){
			int underscore = label.getName().lastIndexOf('_');
			String type = label.getName().substring(underscore+1);
			if (type.charAt(0)=='t'){
				Type[] tArray = null;
				t = new Type(typeEnum.TUPLE,tArray);
				return t;
			}
			else if (type.charAt(0)=='p'){
				ret.add(new Type(typeEnum.UNIT));
				type = type.substring(1);
			}
			else if (type.charAt(0)=='i'){
				ret.add(new Type(typeEnum.INT));
				type = type.substring(1);
			}
			else if (type.charAt(0)=='b'){
				ret.add(new Type(typeEnum.BOOL));
				type = type.substring(1);
			}
			
			
			return null;
		}
		else{
			return null;
		}
	}
	
}
