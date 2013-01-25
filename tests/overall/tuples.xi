use io

main(args:int[][]) {
	a:int, b:bool = foo()
	if(b & a == 4) {
		println("Win")
	}
}

foo() : int, bool {
    // returns 4, true
	return (1,2)[0] + ((6,7),(9,3))[1][1], true
}
