use io
use conv

main(args:int[][]){
  //z is bigger than 32bits
  z:int = 5294967296
  x:int = z*2
  y:int = z+6294967296
  println("5294967296 is :"+unparseInt(z));
  println("10589934592 is :"+unparseInt(x));
  println("11589934592 is :"+unparseInt(y));
}