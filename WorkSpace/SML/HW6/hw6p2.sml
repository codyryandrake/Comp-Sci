fun gcd(0, 0) = 0
	| gcd(0, y) = y
	| gcd(x, 0) = x
	| gcd(x, y) = gcd(y mod x, x);

gcd(18, 15);