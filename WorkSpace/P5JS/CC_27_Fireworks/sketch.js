var fireworks = [];
var gravity;

//GUI vars
var backgroundC = '#210163';
var backgroundA = 0.06;
var backgroundAMin = 0;
var backgroundAMax = 1;
var backgroundAStep = .01;

var rocketSize = 10;
var rocketSizeMin = 0.1;
var rocketSizeMax = 200;
var rocketSizeStep = 1;

var particleSize = 3;
var particleSizeMin = 0;
var particleSizeMax = 20;
var particleSizeStep = .1;

var particleDecay = .06;
var particleDecayMin = 0.01;
var particleDecayMax = .08;
var particleDecayStep = .001;

var gui;

function setup() {
  createCanvas(window.innerWidth, window.innerHeight);
  // colorMode(HSB, 360, 100, 100, 1)
  colorMode(HSB);
  gravity = createVector(0, .2);
  gui = createGui('HSV GUI');
  gui.addGlobals(
    'backgroundC',
    'backgroundA',
    'particleSize',
    'rocketSize',
    'particleDecay'
  )
  stroke(255);
  strokeWeight(4);
}

function draw() {
  background(hue(backgroundC), saturation(backgroundC), brightness(backgroundC), backgroundA);
  if(random(1) < .05) {
    fireworks.push(new Firework());
  }

  for(var i = fireworks.length - 1; i >= 0; i--) {
    fireworks[i].update();
    fireworks[i].show();
    if(fireworks[i].done)
      fireworks.splice(i, 1);
  }

}
