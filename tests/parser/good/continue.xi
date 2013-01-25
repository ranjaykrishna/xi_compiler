use io
use conv

main(args: int[][]) {
	a:int = 5
	while(a > 0) {
	  if(a == 1) {
	    println("continuing!")
		continue
		println("should not occur. test failed")
	  }
	  a = a - 1
	  println(unparseInt(a))
	}
}
