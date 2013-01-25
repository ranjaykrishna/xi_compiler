use io
use conv

class Point {
  x:int
  y:int
  
  getX() : int {
    return x
  }
  
  setX(a:int) {
    x = a
  }
  
  addPoint(a:int, b:int) {
    x = a + this.x
	y = y + b
  }
}
	
main(args:int[][]){
  p:Point = new Point
  p.setX(7)
  p.addPoint(4,5)
  c:int = p.getX()
  println(unparseInt(c))
}