class Drop {
  float x = random(width);
  float y = random(-200, 0);
  float zDepth = random(0, 20);
  float ySpeed = map(zDepth, 0, 20, 4, 10);
  float len = map(zDepth, 0, 20, 10, 20);

  
  void fall() {
    ySpeed += 0.05;//Slowly build up speed as drop falls
    y += ySpeed; //Add speed to y position
    if(y > height) { //If the drop ever falls below the screen
      y = random(-200, 0); //Reset y position to random above screen
      ySpeed = map(zDepth, 0, 20, 4, 10);
    }
    
  }
  
  void show() {
    float strokeA = map(zDepth, 0, 20, 10, 255);
    stroke(138, 43, 226, strokeA);
    line(x, y, x, y+len);
    //if(length > 8) {
    //  circle(x, y, length);
    //}
    
  }
  
}
