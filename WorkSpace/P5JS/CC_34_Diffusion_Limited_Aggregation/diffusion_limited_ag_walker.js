function Walker(x, y) {
	if(x != undefined && y != undefined) {
		this.pos = createVector(x, y);
		this.stuck = true;
	} else {
		this.pos = randomPoint();
		this.stuck = false;
	}
	this.r = radius;
	

	this.walk = function() {
		var vel = p5.Vector.random2D();
		// var vel = createVector(random(-1,1), random(0, 30));
		this.pos.add(vel);
		this.pos.x = constrain(this.pos.x, 0, width);
		this.pos.y = constrain(this.pos.y, 0, height);
	}

	this.checkStuck = function(others) {
		for(var i = 0; i < others.length; i++) {	
			var d = distSq(this.pos, others[i].pos);
			if(d < (this.r * others[i].r * 4)) {
				this.stuck = true;
				return true;
			}
		}
		return false;
	}

	this.show = function() {
		h = map(this.r, radiusMax, radiusMin, 0, 360);
		s = map(this.r, radiusMax, radiusMin, 255, 0);
		b = map(this.r, radiusMax, radiusMin, 0, 255);
		a = map(this.r, radiusMax, radiusMin, 0, 5);
		if(this.stuck)
			fill(h, s, s, a);
		else
			fill(h, s, s, a);
		circle(this.pos.x, this.pos.y, this.r*2);
	}

}

function randomPoint() {
	var i = floor(random(4));

	switch(i) {
		case 0:
			var x = random(width);
			return createVector(x, 0);
			break;
		case 1:
			var x = random(width);
			return createVector(x, height);
			break;
		case 2:
			var y = random(height);
			return createVector(0, y);
			break;
		case 3:
			var y = random(height);
			return createVector(width, y);
			break;
	}
}

function distSq(a, b) {
	var dx = b.x - a.x;
	var dy = b.y - a.y;
	return (dx * dx +dy * dy);
}