class Particle {
  constructor() {
    this.pos = p5.Vector.random2D();
    this.rays = [];
    this.color = random(360);
    for (let a = 0; a < 360; a +=.3) {
      this.rays.push(new Ray(this.pos, radians(a)));
    }
  }

  applyForce(force) {
    this.pos.set(force);
  }

  look(boundaries) {
    let rayColor = this.color;
    let rayBrightness;
    for (let ray of this.rays) {
      let closest = null;
      let record = Infinity;
      for(let boundary of boundaries) {
        const pt = ray.cast(boundary);
        if (pt) {
          const d = p5.Vector.dist(this.pos, pt);
          if(d < record) {
            record = d;
            closest = pt;
            rayBrightness = map(d, 0, record, 0, 5);
          }
        }
      }
      if(closest) {
        push();
        colorMode(HSB, 360, 255, 255, 100);
        stroke(rayColor, 255, 255, 3);
        strokeWeight(2);
        line(this.pos.x, this.pos.y, closest.x, closest.y)
        //rayColor = (rayColor + .01) %50;
        pop();
      }
      //When particle crosses a boundary
      if(record < 1) {
        //this.color = random(360);
        //this.pos.set(closest)
      }
    }
  }

  show() {
    // fill(255);
    // ellipse(this.pos.x, this.pos.y, 4, 4);
    //for (let ray of this.rays) {
      //ray.show();
    //}
  }
}
