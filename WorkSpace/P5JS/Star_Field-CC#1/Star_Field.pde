Star[] stars = new Star[800];

void setup() {
  size(1000, 200);
  for (int i = 0; i < stars.length; i++) {
    stars[i] = new Star();
  }
  
  
}

void draw() {
  background(0);
  translate(width/2, height/2);
  for (int i = 0; i < stars.length; i++) {
    stars[i].update();
    stars[i].show();
  }
  
  
}
