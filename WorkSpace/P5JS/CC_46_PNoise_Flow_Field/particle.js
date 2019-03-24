function Particle() {
	this.pos = createVector(random(width), random(height));
	this.vel = createVector(0,0);
	this.acc = createVector(0,0);
	this.maxSpeed = random(.1, 2);
	this.hueSpeed = random(.01, .1);
	this.h = 0;

	this.prevPos = this.pos.copy();

	this.updatePrev = function() {
		this.prevPos.x = this.pos.x;
		this.prevPos.y = this.pos.y;
	}

	this.update = function() {
		this.vel.add(this.acc);
		this.vel.limit(this.maxSpeed);
		this.pos.add(this.vel);
		this.acc.mult(0);
	}

	this.follow = function(vectors) {
		var x = floor(this.pos.x / scl);
		var y = floor(this.pos.y / scl);
		var index = x + y * cols;
		var force = vectors[index];
		this.applyForce(force);
	}

	this.applyForce = function(force) {
		this.acc.add(force);

	}

	this.show = function() {
		stroke(this.h, 255, 255, 255);
		this.h += this.hueSpeed;
		if (this.h > 255) {
			this.h = -this.h
		}
		strokeWeight(1);
		line(this.pos.x, this.pos.y, this.prevPos.x, this.prevPos.y);
		// point(this.pos.x, this.pos.y);
		this.updatePrev();
	}

	this.edges = function() {
		if (this.pos.x > width) {
			this.pos.x = 0;
			this.updatePrev();
			this.maxSpeed = random(.1, 2);
			this.hueSpeed = random(.01, .1);
		}
		if (this.pos.x < 0) {
			this.pos.x = width;
			this.updatePrev();
			this.maxSpeed = random(.1, 2);
			this.hueSpeed = random(.01, .1);
		}
		if (this.pos.y > height) {
			this.pos.y = 0;
			this.updatePrev();
			this.maxSpeed = random(.1, 2);
			this.hueSpeed = random(.01, .1);
		}
		if (this.pos.y < 0) {
			this.pos.y = height;
			this.updatePrev();
			this.maxSpeed = random(.1, 2);
			this.hueSpeed = random(.01, .1);
		}
	}


}