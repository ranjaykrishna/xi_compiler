m:int = 5;

class Point {
  x,y: int
  square : bool
  
  move(dx:int, dy:int){
    x = x + dx;
    y = y + dy;
  }
  
  coords(): int, int {
    return x,y
  }
  	
  add(p : Point) : Point {
    return createPoint(x + p.x, y + p.y);
  }
  
  clone() : Point {
    return createPoint(x,y)
  }
  
  init(x0:int,y0:int):Point{
    p:Point = new Point
    x=x0
    y=y0
    return this
  }
}

createPoint(x:int, y:int): Point {
  return new Point.init(x,y)
}

main(args:int[][]){
  p: Point = createPoint(5,5)
  x:int, y:int = p.coords();
  polygon : Point[];
  square : Point[4];
  square[0] = createPoint(0,0)
  square[1] = createPoint(1,0)
  square[2] = createPoint(1,1)
  square[3] = createPoint(0,1)
  i:int = 0;
  while(i<length(square)){
    a : Point = square[i].add(createPoint(x,y))
    if(i==2){
      // Don't shift the last point
      break;
    }
  }
}