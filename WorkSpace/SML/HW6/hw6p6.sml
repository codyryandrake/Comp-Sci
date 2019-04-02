fun scale([], n) = []
	| scale([h], n) = [h*n]
	| scale([h::t], n) = h*n::scale(h::t::t, n);

scale([2,4,6,8], 2); 