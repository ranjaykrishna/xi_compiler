use io
use conv

main(args:int[][]){
	a:int = -4
	b:int = 4;
	while(a<100){
		a = a + 1;
	}
	print("a:")
	println(unparseInt(a));
}