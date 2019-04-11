float angle;
float aVel = 0.0;
float aAcc = 0.01;

void setup() {
  size(640, 360);
}

void draw() {
  background(255);
  aAcc = map(mouseX, 0, width, -5, 5);
  angle+=aVel;
  aVel += aAcc;
  
  rectMode(CENTER);
  stroke(0);
  fill(127);
  translate(width/2, height/2);
  rotate(angle);
  rect(0,0,64,36);
}
