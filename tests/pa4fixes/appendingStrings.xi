// SHOULD RETURN 227
use io

main() : int {
	a:int[] = "hello "
	b:int[] = "world!"
	c:int[] = a + b
	println(c); 
	return c[2] + c[6]
}
