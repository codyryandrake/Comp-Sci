//@D Visibility
//Ray Casting
//Cody's Implementation of The Coding Train

let boundaries = [];
let particles = [];
let xoff;
let yoff;

let noiseVector;
let mouseVector;

//GUI VARS
var backgroundC = '#0099CC';
var particleColor = ' #FFFF00';


function setup() {
  createCanvas(1600, 600);
  background(0);
  xoff = random(1000);
  yoff = random(1000);
  xoff2 = random(2000);
  yoff2 = random(2000);
  for (let i = 0; i < 7; i++) {
    let x1 = random(width);
    let y1 = random(height);
    let x2 = random(width);
    let y2 = random(height);
    boundaries.push(new Boundary(x1, y1, x2, y2, 100));
  }
  boundaries.push(new Boundary(0, 0, width, 0, 255));
  boundaries.push(new Boundary(0, height, width, height, 255));
  boundaries.push(new Boundary(0, 0, 0, height, 255));
  boundaries.push(new Boundary(width, 0, width, height, 255));
  for (let i = 0; i < 2; i++) {
    particles.push(new Particle());
  }
}

function draw() {
  if(key == 's') {
    save('2DRay.png');
    //nullify key value to prevent multiple downloads on subsequent loops
    key = null;
  }

  background(0, 5);
  for (let boundary of boundaries) {
    boundary.show();
  }




  for(let i = 0; i < particles.length; i++) {
    noiseVector = createVector((noise(xoff+i*1000)*width), (noise(yoff+i*1000)*height))
    particles[i].applyForce(noiseVector);
    if(mouseIsPressed) {
      mouseVector = createVector(mouseX, mouseY);
      particles[0].applyForce(mouseVector);
    }
    particles[i].look(boundaries)
    particles[i].show();
  }

  xoff += random(0.01);
  yoff += random(0.01);
}
