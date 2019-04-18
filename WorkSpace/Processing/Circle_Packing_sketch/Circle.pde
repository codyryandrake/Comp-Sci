class Circle {
  float x, y;
  float r = 1;
  
  boolean growing = true;
  
  Circle(float x_, float y_) {
    x = x_;
    y = y_; 
  }
  
  void grow() {
    if(growing)
      r = r + .5;
  }
  
  boolean edges() {
    return (x + r > width || x - r < 0 || y + r > height || y - r < 0);
  }
  
  void show() {
    stroke(255);
    strokeWeight(2);
    noFill();
    ellipse(x, y, r*2, r*2);
  }
  
  
  
}
