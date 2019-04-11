class Mover {
  PVector location;
  PVector velocity;
  PVector acceleration;
  float mass;
  float g;
  Mover() {
    location = new PVector(random(width/2), random(height/2));
    velocity = new PVector(1, 0);
    acceleration = new PVector(0, 0);
    mass = random(.1, 3);
    g = 1;
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
  
  PVector attract (Mover m) {
    
    //Direction of the force
    PVector force = PVector.sub(location, m.location);
    float d = force.mag();
    d = constrain(d, 5.0, 25.0);
    force.normalize();
    //Magnitude of the force
    float strength = (g * mass * m.mass) / (d*d);
    
    //Putting magnitude and direction together
    force.mult(strength);
    
    return force;
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
    ellipse(location.x, location.y, mass*20, mass*20);
  }
}
