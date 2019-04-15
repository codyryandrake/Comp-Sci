var fireworks = [];
var gravity;

function setup() {
  createCanvas(400, 300);
  // colorMode(HSB, 360, 100, 100, 1)
  colorMode(HSB);
  gravity = createVector(0, .2);

  stroke(255);
  strokeWeight(4);
}

function draw() {
  background(0, 0, 0, .3);
  if(random(1) < .1)
    fireworks.push(new Firework());

  for(var i = fireworks.length - 1; i >= 0; i--) {
    fireworks[i].update();
    fireworks[i].show();
    if(fireworks[i].done)
      fireworks.splice(i, 1);
  }
  console.log(fireworks.length);

}
