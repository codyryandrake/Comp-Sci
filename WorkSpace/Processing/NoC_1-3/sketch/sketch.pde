Ball b;

void setup() {
  size(640, 360);
  b = new Ball();
  
}

void draw() {
  background(51);
  b.update();
  b.edges();
  b.display();
} 
