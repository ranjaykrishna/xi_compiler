// SHOULD THROW OUT OF BOUNDS EXCEPTION
main() : int {
    a:int[1][2][3]
    a[0][1][3] = 5
    return a[0][1][3]
}
