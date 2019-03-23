//Langton's Ant Coding Challenge 
int[][] grid;
int x;
int y;

final int ANT_UP = 0;
final int ANT_RIGHT = 1;
final int ANT_DOWN = 2;
final int ANT_LEFT = 3;

PImage ant;

int dir;

void setup() {
  //size(400, 400);
  fullScreen(P2D);
  grid = new int[width][height];
  ant = createImage(width, height, RGB);
  ant.loadPixels();
  for(int i = 0; i < ant.pixels.length; i++) {
    ant.pixels[i] = color(255);
  }
  ant.updatePixels();
  x = width/2;
  y = height/2;
  dir = ANT_UP;
}

void turnRight() {
  dir++;
  if(dir > ANT_LEFT)
    dir = ANT_UP;
}

void turnLeft() {
    dir--;
  if(dir < ANT_UP)
    dir = ANT_LEFT;
}

void moveForward() {
  switch(dir) {
    case ANT_UP: y--; if(y < 0) y = height -1; break; 
    case ANT_RIGHT: x++; if(x > width-1) x = 0; break;
    case ANT_DOWN: y++; if(y > height-1) y = 0; break;
    case ANT_LEFT: x--; if(x < 0) x = width -1; break;
  }

}

void draw() {
  background(255);
  

  ant.loadPixels();
  for(int n = 0; n < 11000; n++) {
   if(grid[x][y] == 1) {
      turnRight();
      grid[x][y] = 0;
  } else {
      turnLeft();
      grid[x][y] = 1;
    }
    int pix = x + y * ant.width;
    ant.pixels[pix] = (grid[x][y] == 0) ? color(0) : color(255);
    moveForward();
  }
  ant.updatePixels();
  image(ant, 0 , 0);
  //noLoop();


}
