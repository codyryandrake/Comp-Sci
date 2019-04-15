function Firework() {

  this.firework = new Particle(
                                mouseIsPressed ? mouseX :  random(width),
                                height,
                                createVector(0, random(-5,-20))
                              );
  this.exploded = false;
  this.done = false;
  this.particles = [];

  this.update = function() {
    if(!this.exploded) {
      this.firework.applyForce(gravity);
      this.firework.update();
      if(this.firework.vel.y >= 0)
        this.explode();
    }

    for(var i = this.particles.length-1; i >= 0 ; i--) {
      this.particles[i].applyForce(gravity);
      this.particles[i].update();
      if(this.particles[i].lifespan <= 0) this.particles.splice(i, 1);
      else this.particles[i].lifespan -= random(.02);
    }

    if(this.exploded && this.particles.length == 0)
      this.done = true;
  }

  this.explode = function() {
    for(let i = 0; i < 300; i++) {
      var p = new Particle(this.firework.pos.x, this.firework.pos.y, p5.Vector.random2D(), 1);
      p.vel.mult(random(1,50));
      //Color the particles based off the initial firework color, with a bit of randomness
      p.color = this.firework.color + random(-30, 30);
      this.particles.push(p);
    }
    this.exploded = true;
  }

  this.show = function() {
    if(!this.exploded)
      this.firework.show();
    else
      for(var i = 0; i < this.particles.length; i++) {
        //Add an additional fall amount to particles after they explode
        this.particles[i].vel.mult(.85);
        this.particles[i].show();
      }
  }
}
