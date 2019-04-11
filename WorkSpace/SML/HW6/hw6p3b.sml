fun insertAtEnd([], n) = n::nil
	| insertAtEnd(lst, n) = hd(lst)::insertAtEnd(tl(lst), n);

fun rotate([]) = []
	| rotate(h:nil) = [h]
	| rotate(h::t) = insertAtEnd(t, h);

fun rotate([5, 7, 3, 2]);