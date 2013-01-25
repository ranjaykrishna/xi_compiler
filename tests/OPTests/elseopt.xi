use assert

main(args:int[][]){
	a:int = 3;
	if (false) a = 1
	else a = 0
	assert(a==0)
}