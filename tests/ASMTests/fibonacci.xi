use io
use conv

main(args:int[][]){
	println(unparseInt(fibonacci(15)))//610
}

fibonacci(i:int):int{
	if (i <= 2){
		return 1;
	}
	else{
		return fibonacci(i-1) + fibonacci(i-2)
	}
}