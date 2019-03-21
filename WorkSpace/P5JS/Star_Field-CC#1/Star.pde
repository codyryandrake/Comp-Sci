class Star {
  float x, y, z; //x and y coordinates, z offset
  float pz; //Previous z
  float speed; //How fast should our stars move?
  
  Star() {
    x = random(-width, width); //Pick a random x somewhere on the screen
    y = random(-height, height); //Pick a random y somewhere on the screen
    z = random(width); //Pick a random z somewhere on the screen
    pz = z; //pz begins at z
    
  }
  
  void update() {
    speed = map(mouseX, 0, width, 5, 50); //Comment out to set constant speed
    z -= speed; 
    if (z < 1) { //If z approaches 0
      z = width; //Reset z
      x = random(-width, width); //Recalculate x
      y = random(-height, height); //Recalculate y
      pz = z; //store last z position in pz
    }
  }
  
  void show() {

    float sx = map(x/z, 0, 1, 0, width); //Map x over z travel
    float sy = map(y/z, 0, 1, 0, height); //Map y over z travel
    float px = map(x/pz, 0, 1, 0, width); //Map px off pz
    float py = map(y/pz, 0, 1, 0, height); //Map py off pz
    
    float s = map(speed, 0, width, 0, 255); //Line stroke transparency mapped to speed
    
    fill(255, 0, 0, s);
    stroke(255,255,0);
    line(px, py, sx, sy);
    
    fill(255);
    noStroke();
    float r = map(z, 0, width, 200/speed, 0);
    ellipse(sx, sy, r, r);
    pz = z;
    
  }
}
