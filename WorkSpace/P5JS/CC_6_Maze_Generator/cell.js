function Cell(i, j) { //Cell constructor
	this.i = i; //Column #
	this.j = j; //Row #
	this.walls = [true, true, true, true]; //T, R, B, L
	this.visited = false;
	this.checkNeighbors = function() {
		var neighbors = [];

		var top = grid[index(i, j-1)];
		var right = grid[index(i+1, j)];
		var bottom = grid[index(i, j+1)];
		var left = grid[index(i-1, j)];

		if(top && !top.visited) {
			neighbors.push(top);
		}
		if(right && !right.visited) {
			neighbors.push(right);
		}
		if(bottom && !bottom.visited) {
			neighbors.push(bottom);
		}
		if(left && !left.visited) {
			neighbors.push(left);
		}

		if(neighbors.length > 0) {
			var r = floor(random(0, neighbors.length));
			return neighbors[r];
		}
		else {
			return undefined;
		}

	}

	this.highlight = function() {
		var x = this.i*w;
		var y = this.j*w;
		push();
		fill(0, 255, 100, 100);
		noStroke();
		ellipseMode(CORNER);
		ellipse(x, y, w, w);
		pop();
	}

	this.show = function() {
		var x = this.i*w;
		var y = this.j*w;

	if(this.walls[0]) box(x,   y,   x+w, y); //TL >> TR Line;
	if(this.walls[1]) line(x+w, y,   x+w, y+w); //TR >> BR Line
	if(this.walls[2]) line(x+w, y+w, x,   y+w); //BR >> BL Line
	if(this.walls[3]) line(x,   y+w, x,   y); //BR >> TR Line

	push();
	if(this.visited) {
		fill(255, 0, 100, 50);
		noStroke();
		rect(x, y, w, w);
	}
	pop();
	}

}