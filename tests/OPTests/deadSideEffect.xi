main(args:int[][]) : int {
  a:int[] = (1,2,3)
  d:int = foo(a)
  return a[0]
}

foo(x:int[]) : int {
  x[0] = x[0] + 1
  return 1
}
