class Curve {
  
  constructor() {
    this.path = [];
    this.current = createVector();
    
  }
  
  setX(x) {
    this.current.x = x;
  }
  
  setY(y) {
    this.current.y = y;
  }
  
  addPoint() {
    this.path.push(this.current);
    //current = new PVector();
  }
  
  show() {
    stroke(255,255,200,150);
    strokeWeight(1);
    //noFill();
    //fill(0, 0, 255, 50);
    // beginShape();
    // for(let v of this.path) {
    //   vertex(v.x, v.y);
    // }
    // endShape();
    
    strokeWeight(18);
    point(this.current.x, this.current.y);
    this.current = createVector();
  }
  
  reset() {
    this.path = [];
  }
}