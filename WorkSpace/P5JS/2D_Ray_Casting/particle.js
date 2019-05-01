class Particle {
  constructor(x,y, color) {
    this.pos = createVector(random(x, y));
    this.rays = [];
    this.color = color;
    for (let a = 0; a < 360; a +=.5) {
      this.rays.push(new Ray(this.pos, radians(a)));
    }
  }

  update(x, y) {
    this.pos.set(x, y);
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
            rayBrightness = map(d, 0, width, 200, 255);
          }
        }
      }
      if(closest) {
        push();
        colorMode(HSB, 360, 255, 255, 100);
        stroke(rayColor, rayBrightness, 255, 10);
        line(this.pos.x, this.pos.y, closest.x, closest.y)
        //rayColor = (rayColor + .01) %50;
        pop();
      }
    }
  }

  show() {
    // fill(255);
    // ellipse(this.pos.x, this.pos.y, 4, 4);
    for (let ray of this.rays) {
      ray.show();
    }
  }
}
