main(args:int[][]):int{
	a:int
	b:int
	a,_,b = f(2);
	return b
}

f(a:int):int,int,int{
	return a,(a*2),3
}