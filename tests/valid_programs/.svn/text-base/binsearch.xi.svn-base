// Requires: the array a of length n>0 is in ascending sorted order.
// Ensures: If x is in the array, return valid index i such that a[i]=x.
//          Otherwise, return an arbitrary index in 0..n-1.
//
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

main() : int {
  array:int[] = (1,2,3,4,5,185,49814,6924,1485,350184501,384,581834,6,4,1,5,4,1,6,4)
  count: int = binary_search(array, 49814)
  return count
}