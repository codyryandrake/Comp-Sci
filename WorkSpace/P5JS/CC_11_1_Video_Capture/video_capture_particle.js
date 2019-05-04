

function Particle(x,y, velocity) {

  this.pos = createVector(x,y);
  this.vel = velocity || createVector(0,0);
  this.acc = createVector(0,0);
  this.lifespan;
  this.size;

  this.applyForce = function(force) {
    this.acc.add(force);
  }

  this.edgeConstrain = function() {
    if(this.pos.x > width || this.pos.x < 0 || this.pos.y > height || this.pos.y < 0) {
      this.vel.mult(-.5);
    }

  }

  this.update = function() {
    this.applyForce(createVector(random(-.1,.1), random(-.1,.1)));
    this.vel.add(this.acc);
    this.pos.add(this.vel);
    this.edgeConstrain();
    this.acc.mult(0);
  }

  this.show = function() {
    stroke(255);
    strokeWeight(8);
    point(this.pos.x, this.pos.y);
  }



}
