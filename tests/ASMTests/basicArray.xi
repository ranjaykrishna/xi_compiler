use io
use conv

main(args:int[][]) : int {
  a:int[1]
  a[0] = 1
  println(unparseInt(a[0]))
  return a[0]
}
