fun multiply([],[]) = []
	| multiply(h::t,[]) = [h::t]
	| multiply(h::t, h2::t2) = 