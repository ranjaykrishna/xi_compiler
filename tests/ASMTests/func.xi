use io
use conv

main(args:int[][]) {
    a:int = 0
    b:bool = true
    c:bool = a<5 & b
	if(true)
	  a = 3;
	if(false)
	  a = 4;
	println(unparseInt(a));
    if(!c){
      q:int = func()
      println("c is false");    
    } else
	{
	  println("c is true");
	}
	if(false)
	{
	  println("shouldn't get here");
	}
    
    if(a<5 & b){
      r:int = func()
      println("a<5 & b is true");
    }
    println(unparseInt(a));
}

func():int {
  a:int[1]
  a[0] = 3
  return 4
}