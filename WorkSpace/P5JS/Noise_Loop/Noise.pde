class NoiseLoop {
  float diameter;
  float min;
  float max;
  float cx;
  float cy;
  
  NoiseLoop(float diameter, float min, float max) {
   this.diameter = diameter; 
   this.min = min;
   this.max = max;
    cx = random(diameter)+mouseX;
    cy = random(diameter)+mouseY;
  }
  
  float value(float a) {
    float xoff = map(cos(a), -1, 1, cx, cx + diameter);
    float yoff = map(sin(a), -1, 1, cy, cy + diameter);
    float r = noise(xoff, yoff);
    return map(r, 0, 1, min, max);
  }
  
  
  
  
  
  
  
}
