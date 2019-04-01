class Boid {
	constructor() {
		this.pos = createVector(random(width), random(height));
		this.prevPos = createVector();
		this.vel = p5.Vector.random2D();
		this.vel.setMag(random(2, 4));
		this.acc = createVector(); 
		this.maxForce = 0.2;
		this.maxSpeed = 4;
		this.r = 8;
		this.perceptionRadius = perception; //How much of the system does this boid see?
		
	}

	addOrRemove() {
		if(flockCount > flock.length) {
			flock.push(new Boid());
			console.log('greater');
		} else if (flockCount < flock.length) {
			flock.splice(-1, 1);
			console.log('fewer')
		}
	}

	edges() {
		if(this.pos.x < 0-this.r) {this.pos.x = width; this.addOrRemove();}
		if(this.pos.x > width+this.r) {this.pos.x = 0; this.addOrRemove();}
		if(this.pos.y < 0-this.r) {this.pos.y = height; this.addOrRemove();}
		if(this.pos.y > height+this.r) {this.pos.y = 0; this.addOrRemove();}
	}

	align(boids) {
		
		let steering = createVector();
		let total = 0;
		for(let other of boids) {
			let distance = dist(this.pos.x, this.pos.y, other.pos.x, other.pos.y)
			let distanceCheck =  distance < this.perceptionRadius;
			if(distanceCheck) {
				
			}

			if(other != this && distanceCheck) {
				steering.add(other.vel); //
				total++;
			} 
		}
		if(total > 0) {
			steering.div(total);
			steering.setMag(this.maxSpeed);
			steering.sub(this.vel);
			steering.limit(this.maxForce);
		}
		return steering;
	}

	separation(boids) {
		let steering = createVector();
		let total = 0;
		for(let other of boids) {
			let distance = dist(this.pos.x, this.pos.y, other.pos.x, other.pos.y)
			let distanceCheck =  distance < this.perceptionRadius;

			if(other != this && distanceCheck) {
				let difference = p5.Vector.sub(this.pos, other.pos);
				difference.div(distance); 	//Make the resulting vector inversely 
											//proportional to the distance
				steering.add(difference); //
				total++;
			} 
		}
		if(total > 0) {
			steering.div(total);
			steering.setMag(this.maxSpeed);
			steering.sub(this.vel);
			steering.limit(this.maxForce);
		}
		return steering;
	}

	cohesion(boids) {
	let steering = createVector();
	let total = 0;
	for(let other of boids) {
		let distance = dist(this.pos.x, this.pos.y, other.pos.x, other.pos.y)
		let distanceCheck =  distance < this.perceptionRadius;

		if(other != this && distanceCheck) {
			steering.add(other.pos); //
			total++;
		} 
	}
	if(total > 0) {
		steering.div(total);
		steering.sub(this.pos);
		steering.setMag(this.maxSpeed);
		steering.sub(this.vel);
		steering.limit(this.maxForce);
	}
		return steering;
	}

	flock(boids) {

		let alignment = this.align(boids); //A vector created by align()
		alignment.mult(alignmentMult);
		let separation = this.separation(boids);
		separation.mult(separationMult);
		let cohesion = this.cohesion(boids);
		cohesion.mult(cohesionMult);
		this.acc.add(alignment);
		this.acc.add(separation);
		this.acc.add(cohesion);
	}

	update() {
		this.r = radius;
		this.prevPos = this.pos;
		this.pos.add(this.vel);
		this.edges(); //Edge checks
		this.vel.add(this.acc);
		this.vel.limit(this.maxSpeed);
		this.acc.mult(0);
	}

	show() {
		strokeWeight(this.r);
		line(this.prevPos.x, this.prevPos.y, this.pos.x, this.pos.y);
		// point(this.pos.x, this.pos.y);

	}


}