main():int{
	return factorial(10);
}

factorial(a:int):int{
	x:int = a;
	if (x<1) {
		return 1;
	}
	else{
		return x*factorial(x-1)
	}
}