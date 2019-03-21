float angle = -PI/2;
float oAngle = -PI/2;
int w = 200;
int cols;
int rows;
Curve[][] curves;

void setup() {
  fullScreen(P2D);
  //frameRate(5);
  cols = width/w - 1;
  rows = height/w - 1;
  curves = new Curve[rows][cols];
  
  for(int j = 0; j < rows; j++) {
    for(int i = 0; i < cols; i++) {
      curves[j][i] = new Curve();
    }
  }
    
}

void draw() {
  background(0,10);
  stroke(255);
  noFill();
  float d = w - .1*w;
  float r = d/2;
  for(int i = 0; i < cols; i++) {
    float cx = w + i * w + w/2;
    float cy = w/2;
    stroke(255);
    strokeWeight(1);
    ellipse(cx,cy, d, d);
    float x = r * cos(angle * (i+1));
    float y = r * sin(angle * (i+1));
    stroke(255);
    strokeWeight(8);
    point(cx + x, cy + y);
    
    stroke(255, 50);
    strokeWeight(1);
    line(cx + x, 0 , cx + x, height);
    
    for(int j = 0; j < rows; j++) {
      curves[j][i].setX(cx + x);
    }
  }
  
   for(int j = 0; j < rows; j++) {
    float cy = w + j * w + w/2;
    float cx = w/2;
    stroke(255);
    strokeWeight(1);
    ellipse(cx,cy, d, d);
    float x = r * cos(angle * (j+1));
    float y = r * sin(angle * (j+1));
    stroke(255);
    strokeWeight(8);
    point(cx + x, cy + y);
    
    stroke(255);
    strokeWeight(1);
    line(0, cy + y, width, cy + y);
    
    for(int i = 0; i < cols; i++) {
      curves[j][i].setY(cy + y);
    }
  }
     for(int j = 0; j < rows; j++) {
    for(int i = 0; i < cols; i++) {
      curves[j][i].addPoint();
      curves[j][i].show();
    }
  }
  
  angle -= 0.005;
  if(angle < -TWO_PI - HALF_PI) {
    for(int j = 0; j < rows; j++) {
      for(int i = 0; i < cols; i++) {
        curves[j][i].reset();
        //println("curves reset");
      }
    } 
    angle = -HALF_PI;
  }
  
  
}
