//https://en.wikipedia.org/wiki/Lorenz_system
//https://www.youtube.com/watch?v=f0lkz2gSsIk&list=PLRqwX-V7Uu6ZiZxtDDRCi6uhfTH4FilpH&index=15

import peasy.*;

float x = 0.01;
float y = 0;
float z = 0;


float a = 10; //constant
float b = 28;
float c = 8/3.0;

ArrayList<PVector> points = new ArrayList<PVector>();
PeasyCam cam;

void setup() {
  size(800, 600, P3D);
  colorMode(HSB);
  cam = new PeasyCam(this, 500);
  
}


void draw() {
  background(0, 10);
  float dt = 0.01;
  float dx = (a * (y - x)) * dt;
  float dy = (x * (b-z) - y) * dt;
  float dz = (x * y - c * z) * dt;
  x+= dx;
  y += dy;
  z += dz;
  
  //translate(width/2, height/2);
  //rotateZ(angle);
  //rotateX(angle);
  //rotateY(angle);
  scale(5);
  noFill();
  points.add(new PVector(x,y,z));
  float hu = 0;
  beginShape();
  for(PVector v : points) {
    stroke(hu, 255, 255);
    vertex(v.x,v.y,v.z);
    hu = (hu+1) %255;
    PVector offset = PVector(noise(dt), noise(dt));
    v.add(offset);
  }
  endShape();
  println(x,y,z);
}
