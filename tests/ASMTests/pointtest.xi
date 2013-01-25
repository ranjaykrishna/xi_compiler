use io
use assert

class Point {
	x, y : int

	init(x': int, y': int) : Point {
		x = x'
		y = y'
		return this
	}

	coords() : int, int {
		return x, y
	}

	move(x': int, y': int) : Point {
		return createPoint(x+x', y+y')
	}

	clone() : Point {
		return createPoint(x, y)
	}
}

createPoint(x:int, y:int) : Point {
	return new Point.init(x, y)
}

main(args:int[][]) {
	x : int, y : int = createPoint(5, 10).move(2,3).clone().coords()
	assert(x == 7 & y == 13)

	out : OutputStream = stdout()
    out.puts("Test passed.\n");
}
