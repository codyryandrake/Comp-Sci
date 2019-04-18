Circle c;

ArrayList<Circle> circles;

void setup() {
  size(640, 360);
  circles = new ArrayList<Circle>();
  
}

void draw() {
  background(51);
  
  int total = 10;
  int count = 0;
  int attempts = 0;
  
  while(count < 10) {
    Circle circle = newCircle();
    if (circle != null) {
      circles.add(circle);
      count++;
    } else {
      attempts++;
    }
    if(attempts > 1000) {
      noLoop();
      print("FINISHED");
      break;
    }
  }
  


  for(Circle c : circles) {
    if(c.growing){
      if(c.edges()) {
        c.growing = false;
      } else {
        for( Circle other : circles) {
          if(c != other) {
            float d = dist(c.x, c.y, other.x, other.y);
            if( d - 2 < c.r + other.r){
              c.growing = false;
              break;
             }
          }
        }
      }
    }
    c.show();
    c.grow();
  }
  
}

Circle newCircle() {
  float x = random(width);
  float y = random(height);
  
  boolean valid = true;
  for(Circle c : circles) {
     float d = dist(x, y, c.x, c.y);
     if(d < c.r) {
       valid = false;
       break;
     }
  }
  
  if(valid) {
    Circle circle = new Circle(x, y);
    return circle;
  }
  else return null;
  
}
