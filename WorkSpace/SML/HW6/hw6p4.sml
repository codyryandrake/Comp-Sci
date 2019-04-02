fun swapFirstTwo([]) = []
	| swapFirstTwo([h]) = [h]
	| swapFirstTwo(h::s::t) = s::h::t;

swapFirstTwo([2,1,3,4,5]);