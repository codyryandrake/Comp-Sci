class Boundary {
  constructor(x1, y1, x2, y2, s) {
    this.a = createVector(x1, y1);
    this.b = createVector(x2, y2);
    this.s = s;
  }

  show() {
    push();
    stroke(this.s);
    line(this.a.x, this.a.y, this.b.x, this.b.y);
    pop();
  }
}
