float phase = 0;
float zoff = 0;

void setup() {
  size (600, 600);
}

void draw() {
  background(0);
  translate(width/2, height/2);
  // stroke(random(160, 200), random(0, 10), 60)
  stroke(255);
  strokeWeight(2);
   noFill();
  //fill(20, 10);
  beginShape();
  float noiseMax = 5;
  //adjusting angle 'a' changes oscillation frequency
  for(float a = 0; a < TWO_PI; a+=0.0003) {
    // let r = 100 //radius
    // let r = random(50,100) //radius
    //Increment angle while keeping radius constant

    float xoff = map(cos(a+phase), -1, 1, 0, noiseMax);
    float yoff = map(sin(a+phase), -1, 1, 0, noiseMax);
    float r = map(noise(xoff, yoff, zoff), 0, 1, width/4, noiseMax);
    float x = r * cos(a);
    float y = r * sin(a);
    vertex(x,y);

  }
  endShape(CLOSE);

  phase += 0.01;
  zoff += .01;
  
}
