fun insertAtEnd([], n) = n::nil
	| insertAtEnd(lst, n) = hd(lst)::insertAtEnd(tl(lst), n);

insertAtEnd([1,2,3], 4);
insertAtEnd(nil, 4);