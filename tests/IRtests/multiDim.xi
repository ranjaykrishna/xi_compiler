main():int{
	a:int[3][3] = ((1,2,3),(4,5,6,),(7,8,9))
	a[1][2] = 100;
	b:int[1][3][3];
	b[0] = a;
	return a[1][2];
}