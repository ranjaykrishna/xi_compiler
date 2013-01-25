main(args:int[][]):int{
	a:int[2]
	a[0] = 1;
	a[1] = 2;
	y:int = f(a);
	return y
}

f(a:int[]):int{
	return a[1];
}