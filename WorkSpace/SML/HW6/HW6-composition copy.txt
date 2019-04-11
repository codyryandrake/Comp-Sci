(*)Problem 1(*)
fun gcd(x, y) = 
	if (x = 0) then y
	else if (y = 0) then x
	else if (x > y) then gcd(y, x)
	else gcd(y mod x, x);

gcd(18, 15);

(*)Problem 2(*)
fun gcd(0, 0) = 0
	| gcd(0, y) = y
	| gcd(x, 0) = x
	| gcd(x, y) = gcd(y mod x, x);

gcd(18, 15);

(*)Problem 3a(*)
fun insertAtEnd([], n) = n::nil
	| insertAtEnd(lst, n) = hd(lst)::insertAtEnd(tl(lst), n);

insertAtEnd([1,2,3], 4);
insertAtEnd(nil, 4);

(*)Problem 3b(*)
fun insertAtEnd([], n) = n::nil
	| insertAtEnd(lst, n) = hd(lst)::insertAtEnd(tl(lst), n);

fun rotate([]) = []
	| rotate(h:nil) = [h]
	| rotate(h::t) = insertAtEnd(t, h);

fun rotate([5, 7, 3, 2]);

(*)Problem 4(*)
fun swapFirstTwo([]) = []
	| swapFirstTwo([h]) = [h]
	| swapFirstTwo(h::s::t) = s::h::t;

swapFirstTwo([2,1,3,4,5]);

(*)Problem 5(*)
fun repFun (f, 0) = (fn x => x)
	| repFun(f, 1) = (fn x => f(x))
	| repFun(f, n) = fn x =>  => f(x) => f(repFun(f, n-1)(x);

val square = fn (x) => x * x;

square(3);
repFun(square, 3)(2);

(*)Problem 6(*)
fun scale([], n) = []
	| scale(h::nil, n) = [h*n]
	| scale(h::t, n) = h*n::scale(t, n);

scale([2,4,6,8], 2); 