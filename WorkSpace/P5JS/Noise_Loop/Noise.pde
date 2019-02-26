class NoiseLoop {
  float diameter;
  float min;
  float max;
  float xOff = random(100000000);
  float yOff = random(100000000);
  
  NoiseLoop(float diameter, float min, float max) {
   this.diameter = diameter; 
   this.min = min;
   this.max = max;
  }
  
  float value(float a) {
    float xoff = map(cos(a), -1, 1, xOff, xOff + diameter);
    float yoff = map(sin(a), -1, 1, yOff, yOff + diameter);
    float r = noise(xoff, yoff);
    return map(r, 0, 1, min, max);
  }
  
  
  
  
  
  
  
}
