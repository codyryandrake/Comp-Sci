class Mover {
  PVector location;
  PVector velocity;
  PVector acceleration;
  float mass;
  
  Mover() {
    location = new PVector(400, 50);
    velocity = new PVector(1, 0);
    acceleration = new PVector(0, 0);
    mass = 1;
  }
  
  void update() {
    //PVector mouse = new PVector(mouseX, mouseY);
    //mouse.sub(location);
    //mouse.setMag(0.0001);
    //acceleration = mouse;
    
    velocity.add(acceleration);
    location.add(velocity);
    edges();
    //Wipe acceleration so it can accumulate the next frame
    acceleration.mult(0);
    //velocity.limit(5);
  }
  
  //Newton's 2nd Law of Motion with mass
  void applyForce(PVector force) {
    //F = M * A
    //A = F / M
    PVector f = PVector.div(force, mass);
    acceleration.add(f);
  }
  
  void edges() {
    if (location.x > width) {
      velocity.x *= -1; location.x = width;
    }
    if (location.x < 0) {
      velocity.x *= -1; location.x = 0;
    }
    if (location.y > height) {
      velocity.y *= -1; location.y = height;
    }
    if (location.y < 0) {
      velocity.y *= -1; location.y = 0;
    }
  }
  
  void display() {
    stroke(0);
    strokeWeight(2);
    fill(127);
    ellipse(location.x, location.y, 16, 16);
  }
}
