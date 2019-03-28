class Vehicle{
	constructor(x, y) {
		this.loc = createVector(x, y);
		this.vel = createVector(0, 0);
		this.acc = createVector(0, 0); 
		this.r = random(8, 20);
		this.maxForce = random(.1, 2);
		this.maxSpeed = random(2, 4);
		this.perceptionRadius = 50; //How much of the system does this boid see?
	}

	update() {
		this.vel.add(this.acc); //Update velocity
		this.vel.limit(this.maxSpeed); //Limit how fast the vehicle can travel
		this.loc.add(this.vel);
		this.acc.mult(0); //Reset acceleration to 0
	}

	applyForce(force) {
		this.acc.add(force);
	}

	seek(target) {
		var desiredVel = p5.Vector.sub(target, this.loc);
		
		//Arrival check
		if(desiredVel.mag() < this.r*5) {
			var m = map(desiredVel.mag(), 0, 100, 0, this.maxSpeed);
			desiredVel.setMag(m);
		} else {
			desiredVel.setMag(this.maxSpeed); //Normalize and multiply by maxSpeed
		}

		var steer = p5.Vector.sub(desiredVel, this.vel);
			steer.limit(this.maxForce);
		this.vel.add(steer);
	}

	followField(field) {
		var fieldVel = lookUp(field);
		var desiredVel = p5.Vector.sub(fieldVel, this.loc);
		var steer = p5.Vector.sub(desiredVel, this.vel);
			steer.limit(this.maxForce);
		this.vel.add(steer);

	}



	display() {
		//Draw a triangle rotated in the direction of the velocity
		var theta = this.vel.heading() + PI/2;
		fill(175);
		stroke(0);
		push();
		translate(this.loc.x, this.loc.y);
		rotate(theta);
		beginShape();
			vertex(0, -this.r*2);
			vertex(-this.r, this.r*2);
			vertex(this.r, this.r*2);
		endShape(CLOSE);
		pop();
	}







}