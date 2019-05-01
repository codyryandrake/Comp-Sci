//@D Visibility
//Ray Casting
//Cody's Implementation of The Coding Train

let boundaries = [];
let particles = [];
let xoff = 0;
let yoff = 10000;

let xoff2 = 1000;
let yoff2 = 100000;

function setup() {
  createCanvas(400, 400);
  for (let i = 0; i < 5; i++) {
    let x1 = random(width);
    let y1 = random(height);
    let x2 = random(width);
    let y2 = random(height);
    boundaries.push(new Boundary(x1, y1, x2, y2));
  }
  boundaries.push(new Boundary(0, 0, width, 0));
  boundaries.push(new Boundary(0, height, width, height));
  boundaries.push(new Boundary(0, 0, 0, height));
  boundaries.push(new Boundary(width, 0, width, height));
  for (let i = 0; i < 2; i++) {
    particles.push(new Particle(random(width), random(height), random(360)));
  }
}

function draw() {
  background(0, 20, 55, 10);
  for (let boundary of boundaries) {
    boundary.show();
  }




  for(let i = 0; i < particles.length; i++) {
    particles[i].update(noise(xoff+i)*width, noise(yoff+i)*height);
    if(mouseIsPressed)
      particles[0].update(mouseX, mouseY);
    particles[i].show();
    particles[i].look(boundaries)
  }
  // particles[0].update(noise(xoff)*width, noise(yoff)*height);
  // particles[0].show();
  // particles[0].look(boundaries)
  xoff += 0.01;
  yoff += 0.01;
  //
  // particles[1].update(noise(xoff2)*width, noise(yoff2)*height);
  // particles[1].show();
  // particles[1].look(boundaries)
  // xoff2 += 0.01;
  // yoff2 += 0.01;


}

  // ray.show();
  // ray.lookAt(mouseX, mouseY);
  //
  //
  // let pt = ray.cast(boundary);
  // // console.log(pt);
  // if(pt) {
  //   fill(255);
  //   ellipse(pt.x, pt.y, 8, 8);
  // }
