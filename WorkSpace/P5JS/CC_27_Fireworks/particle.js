

function Particle(x,y, velocity, lifespan) {

  this.pos = createVector(x,y);
  this.vel = velocity || createVector(0, 0);
  this.acc = createVector(0,0);
  this.lifespan = lifespan == null ? null : lifespan
  this.color = mouseIsPressed ? map(mouseY, 0, height, 0, 360) : random(360);


  this.applyForce = function(force) {
    this.acc.add(force);
  }

  this.update = function() {
    this.vel.add(this.acc);
    this.pos.add(this.vel);
    this.acc.mult(0);
  }

  this.show = function() {
    //If a particle is created with lifespan and that lifespan still has value
    stroke(this.color,100,100, this.lifespan || 1);
    strokeWeight(this.lifespan != null ? lifespan + random(0, particleSize) : random(rocketSize));
    point(this.pos.x, this.pos.y);
  }

}
