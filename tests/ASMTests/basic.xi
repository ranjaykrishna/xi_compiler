use io
use conv

main(args:int[][]){
    a:int = -5*3/15*4   //-4
	b:int
	b = func(a,2)
	print("b:")
	println(unparseInt(b))
}

func(a:int,b:int):int{
	if (a<0){
	    y:int = junk(a/b)
	    return y
	}
	else{
	    y:int = a*b
		return y
	}
}

junk(a:int):int{
    while(a<0){
	    a = a + 1;
	}
	return a;
}