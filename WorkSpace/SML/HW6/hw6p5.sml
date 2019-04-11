fun repFun (f, 0) = (fn x => x)
	| repFun(f, 1) = (fn x => f(x))
	| repFun(f, n) = fn x =>  => f(x) => f(repFun(f, n-1)(x);

val square = fn (x) => x * x;

square(3);
repFun(square, 3)(2);