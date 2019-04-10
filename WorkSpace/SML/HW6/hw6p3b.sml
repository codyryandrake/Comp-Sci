fun insertAtEnd([], n) = n::nil
	| insertAtEnd(lst, n) = hd(lst)::insertAtEnd(tl(lst), n);

fun rotate([]) = []
	| rotate(h) = h
	| rotate(lst) = insertAtEnd(lst, hd(lst));

fun rotate([5, 7, 3, 2]);