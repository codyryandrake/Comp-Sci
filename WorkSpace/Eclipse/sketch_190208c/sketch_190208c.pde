
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

  int x; //x-coordinate of walker
  int y; //y-coordinate of walker

  Walker () {
    x = width/2; //Initialize walker in the center of the screen
    y = height/2;
  }

  void display() {
    stroke(0);
    point(x,y);
  }

  //void step() {
  //  int choice = int(random(4)); //0, 1, 2, or 3

  //  if(choice == 0)
  //    x++;
  //  else if(choice == 1)
  //    x--;
  //  else if(choice == 2)
  //    y++;
  //  else
  //    y--;
  //}
  
    void step() {
      float stepx = random(-1,1);
      float stepy = random(1,-1);
      x += stepx;
      y += stepy;
  }
}
