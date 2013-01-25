// TEST CASE SHOULD RETURN 0
main() : int { 
	a : int[1]
	a[0] = 0
	if(incTrue(a, 3) | incTrue(a, 7)) { // = 3
		a[0] = a[0] + 8 // = 11
	}
	if(a[0] == 11) {
		a[0] = 0 // = 0
		return 0
	} else {
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

