fun insertAtEnd([], n) = n::nil
	| insertAtEnd(lst, n) = hd(lst)::insertAtEnd(tl(lst), n);

fun rotate([]) = []
	| rotate(h) = h
	| rotate(h::t) = t::insertAtEnd(t::t, h::t);

fun rotate([5, 7, 3, 2]);