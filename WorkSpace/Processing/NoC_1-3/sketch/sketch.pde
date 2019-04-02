//Mover[] movers;
//Attractor[] attractors; 

Mover m;
Attractor a;


void setup() {
  size(640, 360);
  //movers = new Mover[10];
  //for(int i = 0; i < movers.length; i ++) {
  //  movers[i] = new Mover();
  //}
  
  //attractors = new Attractor[1];
  //for(int i = 0; i < attractors.length; i++) {
  //  attractors[i] = new Attractor();
  //}
  m = new Mover();
  a = new Attractor();
  
  
}

void draw() {
  background(51);
  
  //For every 'b' Ball object in the array of balls
  //for(Mover m : movers) {
    //PVector gravity = new PVector(0, 0.3);
    //gravity.mult(m.mass);
    //m.applyForce(gravity);

    //PVector wind = new PVector(.02, 0);
    //m.applyForce(wind);

    
    //Friction resistance
    //if(mousePressed) {
    //PVector friction = b.velocity.copy();
    //friction.normalize();    
    ////Friction coefficient
    //float c = -0.1;
    //friction.mult(c);
    //b.applyForce(friction);
    //}
    
    //Drag resistance
    //if(mousePressed) {
    //  PVector drag = b.velocity.copy();
    //  float c2 = -0.01;
    //  drag.normalize();
    //  float speed = b.velocity.magSq();
    //  drag.mult(c2 * speed);
    //  b.applyForce(drag);
    //}
    
    //Gravitational attraction
    //if(mousePressed) {
    //  PVector direction = PVector.sub(loc2, loc1);
    //  float d = direction.magSq();
    //  direction.normalize();
    //  float gravityCo = 1;
    //  direction.mult((gravityCo * loc1.mass * loc2.mass))
    //}
    
    PVector f = a.attract(m);
    m.applyForce(f);
    m.update();
    
    a.drag();
    a.hover(mouseX, mouseY);
    
    a.display();
    m.display();
  //}
  

} 

void mousePressed() {
  a.clicked(mouseX,mouseY); 
}

void mouseReleased() {
  a.stopDragging(); 
}
