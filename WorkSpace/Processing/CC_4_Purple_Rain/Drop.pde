class Drop {
  PVector position = new PVector(random(width), random(-200, 0));
  float zDepth = random(0, 20);
  PVector acc = new PVector(0, map(zDepth, 0, 20, 4, 10));
  float len = map(zDepth, 0, 20, 10, 20);

  
  void fall() {
    //acc.add(;//Slowly build up speed as drop falls
    position.add(acc); //Add speed to y position
    if(position.y > height) { //If the drop ever falls below the screen
      position.y = random(-200, 0); //Reset y position to random above screen
      acc.y = map(zDepth, 0, 20, 4, 10);
    }
    
  }
  
  void show() {
    float strokeA = map(zDepth, 0, 20, 10, 200);
    stroke(138, 43, 226, strokeA);
    line(position.x, position.y, position.x, position.y+len);
    //if(len > 8) {
    //  circle(position.x, position.y+len, len);
    //}
    
  }
  
}
