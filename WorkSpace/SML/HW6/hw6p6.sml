fun scale([], n) = []
	| scale(h::nil, n) = [h*n]
	| scale(h::t, n) = h*n::scale(t, n);

scale([2,4,6,8], 2); 