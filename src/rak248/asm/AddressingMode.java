package rak248.asm;

public class AddressingMode {
	
	//Modes
	// $k        Just a constant k can only be used in src
	// r         Register, use the register as src/dest as appropriate
	// n         Not sure what this does for now we will not handle it
	// (r)       Access the memory address pointed to by register r
	// k(r)      Add k to the value in register r and access that memory address
	// (r1,r2)   Adds the value in registers r1 and r2 and accesses that memory address
	// (r1,r2,m) Adds the value in register r1 with the value in register r2*m (m is a const of 2,4,8) and uses that memory address
	// k(r1,r2,m)Same as above but adding the constant k at the end and uses that memory address
	
	private Long displacement;
	private String base_reg;
	private String offset_reg;
	private Integer mult_const;
	private boolean memAccess;
	private String label;
	private boolean justLabel;
	private boolean check;
	private boolean multiply;
	public boolean lea;
	
	//Constructor for $k
	public AddressingMode(long value){
		memAccess = false;
		displacement = value;
	}
	
	//constructor for r
	public AddressingMode(String reg){
		memAccess = false;
		base_reg = reg;
	}
	
	//constructor for (r) you could also use it for r if you set the first argument to false
	public AddressingMode(boolean mem, String reg){
		memAccess = true;
		base_reg = reg;
	}
	//if you're jumping to label.
	//if the constructor is of this order, jmp will be true.
	public AddressingMode(String label, boolean jmp){
		this.label = label;
		justLabel = true;
		memAccess = false;
	}
	
	//constructor for k(r)
	public AddressingMode(long k, String reg){
		memAccess = true;
		displacement = k;
		base_reg = reg;
	}
	
	
	//constructor for *k(r)
	public AddressingMode(long k, String reg, boolean multiply){
		memAccess = true;
		displacement = k;
		base_reg = reg;
		this.multiply = true;
	}
	
	//constructor (r1,r2)
	public AddressingMode(String reg1, String reg2){
		memAccess = true;
		base_reg = reg1;
		offset_reg = reg2;
		check = false;
	}
	
	//constructor ripoffset(%rip)
	public AddressingMode(String reg1, String reg2, boolean check){
		memAccess = true;
		base_reg = reg1;
		offset_reg = reg2;
		this.check = true;
		this.lea = true;
	}
	
	//constructor for (r1,r2,m)
	public AddressingMode(String reg1, String reg2, int mult){
		memAccess = true;
		base_reg = reg1;
		offset_reg = reg2;
		mult_const = mult;
	}
	
	//constructor for k(r1,r2,m)
	public AddressingMode(long k, String reg1, String reg2, int mult){
		memAccess = true;
		displacement = k;
		base_reg = reg1;
		offset_reg = reg2;
		mult_const = mult;
	}
	
	public AddressingMode(String name, String string, boolean b, boolean c) {
		memAccess = true;
		base_reg = name;
		offset_reg = string;
		this.check = true;
		this.lea = false;
	}

	public String toString(){
		String ret = "AddressingMode's toString failed";
		if(justLabel == true) {
			ret = label;
		}else if(mult_const!=null){
			//either (r1,r2,m) or k(r1,r2,m)
			if(displacement == null){
				//(r1,r2,m)
				ret = "(%"+base_reg+",%"+offset_reg+","+mult_const+")";
			}else{
				ret = ""+displacement+"(%"+base_reg+",%"+offset_reg+","+mult_const+")";
			}
		}else if(offset_reg!=null){
			//(r1,r2)
			if(!check)
				ret = "(%"+base_reg+",%"+offset_reg+")";
			else
				ret = base_reg +"(%" + offset_reg +")";
		}else if(memAccess){
			//(r) or k(r)
			if(multiply) {
				ret = "*"+displacement+"(%"+base_reg+")";
			}
			else if(displacement == null){
				ret = "(%"+base_reg+")";
			}else{
				ret = ""+displacement+"(%"+base_reg+")";
			}
		}else if(displacement == null){
			//r
			ret = "%"+base_reg;
		}else{
			ret = "$"+displacement;
		}
		if(label!=null){
			ret = label;
		}
		return ret;
	}
	
	public boolean equals(AddressingMode other) {
		if((other.justLabel != this.justLabel)) {
			return false;
		}
		if(this.displacement == null && other.displacement != null) {
			return false;
		}
		if(this.displacement != null && other.displacement == null) {
			return false;
		}
		if(this.displacement != null && other.displacement != null) {
			if(!other.displacement.equals(this.displacement)) {
				return false;
			}
		}
		if(this.base_reg == null && other.base_reg != null) {
			return false;
		}
		if(this.base_reg != null && other.base_reg == null) {
			return false;
		}
		if(this.base_reg != null && other.base_reg != null) {
			if(!other.base_reg.equals(this.base_reg)) {
				return false;
			}
		}
		if(this.offset_reg == null && other.offset_reg != null) {
			return false;
		}
		if(this.offset_reg != null && other.offset_reg == null) {
			return false;
		}
		if(this.offset_reg != null && other.offset_reg != null) {
			if(!other.offset_reg.equals(this.offset_reg)) {
				return false;
			}
		}
		if(this.mult_const == null && other.mult_const != null) {
			return false;
		}
		if(this.mult_const != null && other.mult_const == null) {
			return false;
		}
		if(this.mult_const != null && other.mult_const != null) {
			if(!other.mult_const.equals(this.mult_const)) {
				return false;
			}
		}
		if(this.label == null && other.label != null) {
			return false;
		}
		if(this.label != null && other.label == null) {
			return false;
		}
		if(this.label != null && other.label != null) {
			if(!other.label.equals(this.label)) {
				return false;
			}
		}
		if((other.memAccess != this.memAccess)) {
			return false;
		}
		return true;
	}
	
	public boolean getMemAccess(){
		return memAccess;
	}
	
	public String getBaseReg(){
		return base_reg;
	}

	public AddressingMode makeMultiply(int i) {
		multiply = true;
		displacement = (long) i;
		return this;
	}
}
