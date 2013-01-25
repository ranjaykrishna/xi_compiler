use io
use conv

// Requires: the array a of length n>0 is in ascending sorted order.
// Ensures: If x is in the array, return valid index i such that a[i]=x.
//          Otherwise, return an arbitrary index in 0..n-1.
//

main(args:int[][]){
  a:int[] = (1,4,6,8,10,16,17,18,19,101);
  b:int = 18;
  print("Found b@")
  println(unparseInt(binary_search(a,b)));
}

binary_search(a: int[], x: int) : int {
    n:int = length(a)
    l:int = 0
    r:int = n-1

    // loop invariant: l <= r & a[l] <= x & a[r] >= x &
    //                 forall i,j in 0..n-1: i<j => a[i] < a[j]
    // decrementing function: r-l
    while (l < r) {
        m:int = (l+r)/2
        if (a[m] < x) l = m+1
        else r = m
    }
    return l
}
