
Walker w;

void setup() {
  size(640,360);

  w = new Walker(); //create the walker

  background(255); //Make the canvas white
}

void draw() {

  w.step(); //Have the walker move randomly
  w.display(); //and reveal itself by drawing a black dot at its location
}

class Walker {

  float x; //x-coordinate of walker
  float y; //y-coordinate of walker

  Walker () {
    x = width/2; //Initialize walker in the center of the screen
    y = height/2;
  }

  void display() {
    stroke(0);
    point(x,y);
  }
  
  //  void step() {
  //    float stepx = random(-1,1);
  //    float stepy = random(-1,1);
  //    x += stepx + random(0,.04);
  //    y += stepy + random(0,.04);
  //}
  
  void step() {
    
    float r = random(1);
    if(r < .4)
      x++;
    else if(r < 0.6)
      x--;
    else if(r < 0.8)
      y++;
    else
      y--;
  }
     
}
