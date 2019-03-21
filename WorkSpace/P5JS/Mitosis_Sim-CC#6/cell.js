function Cell(pos, rad, col) {
	this.decayCounter = 0;

	if(pos) {
		this.pos = pos.copy();
	}
	else {
		this.pos = createVector(random(width/2), random(height/2));
	}
	this.r = rad || random(cellSize, cellSize/4);
	this.c = col || color(random(100+this.r, 255), 0, random(100, 255), 100);

	this.clicked = function(mx, my) {
		if(dist(this.pos.x, this.pos.y, mx, my) <= this.r) {
			return true;
		}
		else
			return false;
	}

	this.mitosis = function() {
		offsetPos = createVector(random(-100, 100), random(-100, 100));
		var cell = new Cell(this.pos.add(offsetPos), this.r/2, this.c);
		return cell;
	}

	this.grow = function() {
		if(this.r < cellSize) {
			this.r += .1
		}
		else {
			this.decayCounter += 1;
		}
	}

	this.died = function() { //TODO: Have cell die after time limit and create new cell after double time limit
			if(this.decayCounter == cellSize) {
				return true
			}
	}

	this.move = function() {
		var vel = p5.Vector.random2D();
		this.pos.add(vel);
	}

	this.show = function() {
		fill(this.c);
		circle(this.pos.x, this.pos.y, this.r, this.r);
	}




}