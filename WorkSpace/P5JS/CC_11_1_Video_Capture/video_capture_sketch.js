var video;
var vScale = 16;

var particle;

function setup() {
  createCanvas(320, 240);
  pixelDensity(1);
  
  video = createCapture(VIDEO);
  video.size(width/vScale, height/vScale);
  particle = new Particle(width/2, height/2);
}


function draw() {
	background(51);
	video.loadPixels();
	particle.applyForce(createVector(random(-1,1), random(-1,1)));
	particle.update();
	particle.show();
}
