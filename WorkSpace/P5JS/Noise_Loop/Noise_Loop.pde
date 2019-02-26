// GIF Loop
// Daniel Shiffman
// https://thecodingtrain.com/CodingChallenges/135-gif-loop.html
// https://youtu.be/nBKwCCtWlUg

int totalFrames = 240;
int counter = 0;
boolean record = false;

NoiseLoop xNoise;
NoiseLoop yNoise;

void setup() {
  size(600, 600);
  xNoise = new NoiseLoop(5, 0, width);
  yNoise = new NoiseLoop(5, 0, height);
  
  
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
  background(0, 10);
  float a = percent * TWO_PI;
  stroke(255);
  //beginShape();
  float x = xNoise.value(a);
  float y = yNoise.value(a);
  circle(x, y, 100);
  //vertex(x, y);
  //endShape(CLOSE);
  
}
