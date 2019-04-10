function Particle() {
	this.pos = createVector(random(width), random(height));
	this.vel = createVector(0,0);
	this.acc = createVector(0,0);
	this.maxSpeed = random(2, 3);
	this.hueSpeed = 0;
	this.h = 0;
	this.force = createVector(0,0);

	this.prevPos = this.pos.copy();

	this.updatePrev = function() {
		this.prevPos = this.pos.copy();
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
		this.force = vectors[index];
		this.applyForce(this.force);
	}

	this.seek = function(target) {
		this.force = target.sub(this.pos);
		this.force.setMag(.5)
		this.force.limit(this.maxSpeed);
		this.applyForce(this.force);
	}

	this.applyForce = function(force) {
		this.acc.add(force);
		this.updatePrev();
		// push();
		// rotate(this.vel.heading());
		// pop();
	}

	this.show = function() {
		colorMode(RGB);
		var c = color(strokeColor);
		var f = color(fillColor);
		if(rainbowTrails) {
			colorMode(HSB, 360, 255, 255, 255); //need to specify each parameter value
			fill(this.h, 255, 255, strokeAlpha);
			stroke(this.h, 255, 255, strokeAlpha); 
		} else {
			fill(red(f), green(f), blue(f), fillAlpha);
			stroke(red(c), green(c), blue(c), strokeAlpha);
		}

		this.h = (this.h + this.hueSpeed) % 255;

		if(true) {
			line(this.pos.x, this.pos.y, this.prevPos.x, this.prevPos.y);
			//triangle( this.pos.x, this.pos.y-dotSize, this.pos.x-dotSize, this.pos.y, this.pos.x+dotSize, this.pos.y);
			// point(this.pos.x, this.pos.y);
			//rect(this.pos.x, this.pos.y, dotSize, dotSize);

		}
		
		
	}

	this.edges = function() {
		if (this.pos.x > width) {
			this.pos.x = 0;
			this.updatePrev();
			// this.maxSpeed = random(.1, 2);
			this.hueSpeed = map(this.vel.x, 0, width, .1, 10);
		}
		if (this.pos.x < 0) {
			this.pos.x = width;
			this.updatePrev();
			// this.maxSpeed = random(.1, 2);
			this.hueSpeed = map(this.vel.x, 0, width, .1, 10);
		}
		if (this.pos.y > height) {
			this.pos.y = 0;
			this.updatePrev();
			// this.maxSpeed = random(.1, 2);
			this.hueSpeed = map(this.vel.x, 0, width, .1, 10);
		}
		if (this.pos.y < 0) {
			this.pos.y = height;
			this.updatePrev();
			// this.maxSpeed = random(.1, 2);
			this.hueSpeed = map(this.vel.x, 0, width, .1, 10);
		}
	}


}