//CC_134-1_Heart_Curve
//Original Code By Dan Shiffman of The Coding Train
//https://www.youtube.com/watch?v=oUBAi9xQ2X4
float a = 0;

ArrayList<PVector> heart = new ArrayList<PVector>(); 
void setup() {
  size(400, 400);
}

void draw() {
  background(0);
  //move origin to the center of the window
  translate(width/2, height/2);
  noFill();
  stroke(255);
  beginShape();
  for(PVector v : heart) {
    vertex(v.x,v.y);
  }
  endShape();
  
  float r = 8;
  float x = r*(16 * pow(sin(a), 3));
  float y = -r*(13 * cos(a) - 5 * cos(2*a) - 2 * cos(3*a) - cos(4*a));
  heart.add(new PVector(x,y));
  a += 0.01;
}
