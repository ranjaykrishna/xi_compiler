use assert

f() : int { return -3 }
g() : int { return 2 }

// Make sure div and mod behave consistently between constant folding and assembly generation.
main(args: int[][]) {
   assert(f() / g() == (-3) / 2)
   assert(f() % g() == (-3) % 2)
}
