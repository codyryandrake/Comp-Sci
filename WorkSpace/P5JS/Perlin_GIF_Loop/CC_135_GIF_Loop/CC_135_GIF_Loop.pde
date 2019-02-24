// GIF Loop
// Daniel Shiffman
// https://thecodingtrain.com/CodingChallenges/135-gif-loop.html
// https://youtu.be/nBKwCCtWlUg

int totalFrames = 120;
int counter = 0;
boolean record = false;
float a = 0;

void setup() {
  size(800, 400);
}

void draw() {
  float percent = 0;
  if (record) {
    percent = float(counter) / totalFrames;
  } else {
    percent = float(counter % totalFrames) / totalFrames;
  }
  render(percent);
  if (record) {
    saveFrame("output/gif-"+nf(counter, 3)+".png");
    if (counter == totalFrames-1) {
      exit();
    }
  }
  counter++;
}

void render(float percent) {
  background(0);
  a = percent * TWO_PI;
  float noiseMax = 1;
  //adjusting angle 'a' changes oscillation frequency
  //Increment angle while keeping radius constant
  float xoff = map(cos(a), -1, 1, 0, noiseMax);
  float yoff = map(sin(a), -1, 1, 0, noiseMax);
  float r = noise(xoff, yoff);
  float x = map(r, 0, 1, 0 , width);
  //float y = map(r, 0, 1, 0 , height);
  circle(x, height/2, 100);
  
  translate(width/2, height/2);
  stroke(255);
  float radius = 100;
  float x1 = radius  * cos(a);
  float y1 = radius * sin(a);
  line(0, 0, x1, y1);
  circle(x1, y1, 10);
}
