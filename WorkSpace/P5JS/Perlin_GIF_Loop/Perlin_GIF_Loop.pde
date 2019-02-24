float a = 0;

void setup() {
  size (600, 600);
}

void draw() {
  background(0);
  float noiseMax = 5;
  //adjusting angle 'a' changes oscillation frequency
    //Increment angle while keeping radius constant
    float xoff = map(cos(a), -1, 1, 0, noiseMax);
    float yoff = map(sin(a), -1, 1, 0, noiseMax);
    float r = noise(xoff, yoff);
    float x = map(r, 0, 1, 0 , width);
    circle(x, height/2, 100);
    println(r);
    a += radians(1);
}
