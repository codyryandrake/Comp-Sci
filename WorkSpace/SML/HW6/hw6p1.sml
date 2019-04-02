fun gcd(x, y) = 
	if (x = 0) then y
	else if (y = 0) then x
	else if (x > y) then gcd(y, x)
	else gcd(y mod x, x);

gcd(18, 15);