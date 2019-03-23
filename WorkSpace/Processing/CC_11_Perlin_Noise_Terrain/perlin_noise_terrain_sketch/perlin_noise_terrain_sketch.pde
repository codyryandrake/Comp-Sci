int cols, rows;
int scl = 20;
int w, h;
float flying = 0;

float[][] terrain;



void setup() {
 //size(600, 600, P3D); 
 fullScreen(P3D);
 //frameRate(1);
  w = int(1.1*width);
  h = int(1.1*height);
  cols = w/scl;
  rows = h/scl;
  terrain = new float[cols][rows];
  
}

void draw() {
  flying -= .1;
  float yoff = flying;
  for(int y = 0; y < rows; y++) {
    float xoff = 0;
    for(int x = 0; x < cols; x++) {
    terrain[x][y] = map(noise(xoff, yoff), 0, 1, -150, 150);
      xoff += .1;
    }
    yoff += .1;
  }
  
  background(0, 100);
  stroke(255);
  noFill();
  
  translate(width/2, height/2);
  rotateX(PI/3);
  
  translate(-w/2, -h/2);
  for(int y = 0; y < rows-1; y++) {
    beginShape(TRIANGLE_STRIP);
    for(int x = 0; x < cols; x++) {
      vertex(x*scl, y*scl, terrain[x][y]);
      vertex(x*scl, (y+1)*scl, terrain[x][y+1]);
    }
    endShape();
  }
}
