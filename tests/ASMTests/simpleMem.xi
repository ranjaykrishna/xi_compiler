use io
use conv

main(args:int[][]){
	a:int[][] = ((1,2,30),(2,3,4));
	c:int = a[0][1];
	d:int = a[1][c];
	print("c:")
	println(unparseInt(c))
	print("which is also a[0][1]:")
	println(unparseInt(a[0][1]))
	print("d:")
	println(unparseInt(d))
}