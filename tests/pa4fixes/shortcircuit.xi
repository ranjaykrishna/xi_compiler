// TEST CASE SHOULD RETURN 1
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
	if(a[0] == 126) {
		a[0] = 0 // = 0
	} else {
		return 0 // shouldn't happen
	}
	
	if(incTrue(a, 10) & incFalse(a, 20)) { // = 30
		a[0] = a[0] + 5 // doesn't read
	}
	if(incFalse(a, 50) & incFalse(a, 50)) { // = 80
		a[0] = a[0] + 5 // doesn't read this
	}
	if(incFalse(a, 3) & incTrue(a, 7)) { // = 83
		a[0] = a[0] + 1 // doesn't read this
	}
	if(incTrue(a, 11) & incTrue(a, 22)) { // = 116
		a[0] = a[0] + 5 // = 121
	}
	if(a[0] == 121) {
		return 1 // should happen
	}
	return 0 // shouldn't happen
}

incTrue(a:int[], b:int):bool {
	a[0] = a[0] + b
	return true
}

incFalse(a:int[], b:int):bool {
	a[0] = a[0] + b
	return false
}

