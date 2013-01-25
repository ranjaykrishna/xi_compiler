use assert

valueOf(i:int) : int { return i }

main(args:int[][]) {
	a:int = valueOf(-2)
	i:int = valueOf(-6)
	b:int = valueOf(6)
	assert((b/a) == -3)
	assert((i/2) == -3)
	assert((i/a) == 3)
}
