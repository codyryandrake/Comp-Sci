var fireworks = [];
var gravity;

function setup() {
  createCanvas(window.innerWidth, window.innerHeight);
  // colorMode(HSB, 360, 100, 100, 1)
  colorMode(HSB);
  gravity = createVector(0, .2);

  stroke(255);
  strokeWeight(4);
}

function draw() {
  background(0, 0, 0, .1);
  if(random(1) < .05)
    fireworks.push(new Firework());

  for(var i = fireworks.length - 1; i >= 0; i--) {
    fireworks[i].update();
    fireworks[i].show();
    if(fireworks[i].done)
      fireworks.splice(i, 1);
  }

}
