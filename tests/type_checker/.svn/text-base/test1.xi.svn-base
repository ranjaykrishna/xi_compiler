func(a:int) :int {
  //tests declaration of an int
  woof:int
  //tests basic assignment of literal to identifier
  x:int = 3
  //tests assignment with a different letter
  y:int = 1
  //assign an identifier to an identifier
  x = y
  //tests declaration of an int array
  w:int []
  //tests assigning an int to an element of an int array
  w[0] = x
  //tests assigning an int to an element of an int array where the index is specified by an identifier that is an int
  w[x] = y
  //another int array declaration
  z:int []
  //assigning an int array to another int array
  w = z
  //testing unary minus with int assignments
  y = -x
  //test unsary minus used in an expression
  y = -3+4
  //testing adding something to itself
  a = x + a
  //another test
  a = y + 2
  //testing math expressions involving parentheses
  a = 4 + (-1+3)
  //math expressions wiht parentheses and literals
  x = (a + 2)
  //math expressions wiht parentheses, literals, and unary minus
  x = -4 * (x+y)
  //bool asignment
  m:bool = true
  //bool declaration
  r:bool 
  //bool assignment with a previously declared variable
  r = false
  //assigning a string to an int array
  w = "booger"
  //declaring a 2d int array
  q:int[][]
  //assigning an int array to the 2nd element of the first dimension of the previously defined array
  q[1] = z
  //assigning an int to the 2nd dimension of the 2d int array
  q[1][2] = 3
  //the next few lines test odd identfier names
  fishy:int = 3
  Fishy:int = 3
  masjr:bool[]
  F'Sif'f__f:int = 4
  o__o:int = 3
  O_o:bool = true
  Q''Q:bool = false
  //testing procedure
  apple(x)
  //testing length
  x = length("boooooooo")
  //testing a function returning multiple elements
  A:int[][], B: bool[], C: int[], D: bool, E:int = important(x, m, z, masjr, q)
  //testing a function returning multiple elements and underscore
  F:int[][], _, _, G: bool, H:int = important(x, m, z, masjr, q)
  //procedure with expression as param
  apple(3+3)
  //procedure with expression using identifiers as a param
  apple(x+y)
  //procedure with array lookup as parameter
  apple(q[1][2])
  //procedure with array lookup using identifiers as parameter
  apple(q[x][y])
  //the next few are testing boolean comparisons
  m = m|m
  m = m&m
  m = !m
  m = !!!!!m
  m = m == m
  //the next few are testing mathematical comparisons
  m = x > y
  m = x < y
  m = x >= y
  m = x <= y
  m = x == y
  x = ------x
  //a simple if statement
  if(m)
  {
    x = y
  }
  //an if-else statement
  if(m)
  {
    x = y
  }
  else
  {
    // a while statement in an if statement
    while(m)
	{
	  x = y
	}
  }
  while(m)
  {
    // an if statement in a while statement.  the if statement only has 1 line so it uses brackets
    if(m)
	  apple(x)
	//a break in a while
	break
  }
  return a
}

//a procedure
apple(y:int) {
  x:int
  y = x+y
}

//a function that takes in many things and returns many things
important(a:int, b:bool, c:int[], d:bool[], e:int[][]): int[][], bool[], int[], bool, int {	
  return e, d, c, b, a
}

foobar(a:int): int{
  x:bool = true
  while(x){
    return a;
  }
  return 8
}

functio(): int {
  x:bool = true
  if(x){
    return 7
  }
  else{
    return 9
  }
}
