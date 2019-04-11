final int N = 128;
final int iter = 16;
final int SCALE = 5;
float t = 0;

Fluid fluid;

void settings() {
  size(N*SCALE, N*SCALE);
}

void setup() {
  fluid = new Fluid(5, 0, .00000001);
  
}

void mouseDragged() {
  fluid.addDensity(mouseX/SCALE, mouseY/SCALE, 500);
  float amtX = mouseX - pmouseX;
  float amtY = mouseY - pmouseY;
  fluid.addVelocity(mouseX/SCALE, mouseY/SCALE, amtX, amtY);

}

void draw() {
  background(0,10);
  
  int cx = int(.5*width/SCALE);
  int cy = int(.5*height/SCALE);
  
  for(int i = -1; i <= 1; i++) {
    for (int j = -1; j <= 1; j++) {
      fluid.addDensity(cx + i, cy + j, random(100, 5000));
    }
  }
  
  float angle = noise(t) * TWO_PI * 2;
  PVector v = PVector.fromAngle(angle);
  v.mult(0.1);
  t += 0.01;
  fluid.addVelocity(cx, cy, v.x, v.y);
  
  fluid.step();
  fluid.renderD();
  fluid.renderV();
  fluid.fadeD();
  push();
  fill(0,200);
  circle(width/2, height/2, 50);
  pop();
  
}
