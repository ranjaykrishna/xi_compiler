use io
use conv

main(args:int[][]){
     r:int = factorial(5);
	 println(unparseInt(r));
}

factorial(a:int):int{
    //print("call to fact:");
    //println(unparseInt(a));
	x:int = a;
	if (x<=1) {
		return 1;
	}
	else{
		return x*factorial(x-1)
	}
}