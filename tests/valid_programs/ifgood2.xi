main() : int {
  foo(3)
  return 0
}

foo(x:int):int {
  if(true) {
    return 1
  } else {
    x = 5;
  }
  return 3
}
