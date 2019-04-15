
//A list of the walkers in the pattern
var tree = [];
var walkers = [];
//var r = 2;
var maxWalkers = 50;
var iterations = 1000;
var radius = 8;
var radiusMax = radius;
var radiusMin = radius*.01

function setup() {
	createCanvas(displayWidth, displayHeight);
	colorMode(HSB, 255, 255, 100);

	// for(var i = 0; i <= 10; i++) {
	// 	tree[i] = new Walker((i*width/10), height);
	// }
	tree[0] = new Walker(width/2, height/2);

	for(var i = 0; i < maxWalkers; i++) {
		walkers[i] = new Walker();
	}
}

function draw() {
	background(0,0, 0, 100);

	for(var i = 0; i < tree.length; i++) {
		tree[i].show();
	}

	for(var i = 0; i < walkers.length; i++) {
		walkers[i].show();
	}

	for(var n = 0; n < iterations; n++) {
		for(var i = 0; i < walkers.length; i++) {
			walkers[i].walk();
			// walkers[i].show();
			if(walkers[i].checkStuck(tree)) {
				radius *= .9992;
				if(radius < radiusMin)
					noLoop();
				walkers[i].r = radius;
				tree.push(walkers[i]);
				walkers.splice(i, 1);
			}
		}
	}



	while(walkers.length < maxWalkers) {
		walkers.push(new Walker());
	}
}

function mousePressed() {
  if (mouseX > 0 && mouseX < 100 && mouseY > 0 && mouseY < 100) {
    let fs = fullscreen();
    fullscreen(!fs);
  }
}