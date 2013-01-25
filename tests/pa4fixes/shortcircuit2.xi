// TEST CASE SHOULD RETURN 0
use io

main() : int { 
	a : int[1]
	a[0] = 0
	if(incTrue(a, 10) | incFalse(a, 20)) { // = 10
		a[0] = a[0] + 5 // = 15
	}
	if(incFalse(a, 50) | incFalse(a, 50)) { // = 115
		a[0] = a[0] + 5 // doesn't read this
	}
	if(incFalse(a, 3) | incTrue(a, 7)) { // = 125
		a[0] = a[0] + 1 // = 126
	}
	if(incTrue(a, 3) | incTrue(a, 7)) { // = 129
		a[0] = a[0] + 8 // = 137
	}
	b:int[1]
	b[0] = a[0]-137+65
	if(a[0] == 137) { 
		a[0] = 0 // = 0
		return 0
	} else {
		println(b)
		return a[0] // shouldn't happen
	}
}

incTrue(a:int[], b:int):bool {
	a[0] = a[0] + b
	return true
}

incFalse(a:int[], b:int):bool {
	a[0] = a[0] + b
	return false
}

