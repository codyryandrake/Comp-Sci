const flock = [];
var fr; //Frame Rate

//GUI Vars/////////////////////
var radius = 8;
var perception = 50;
var alignmentMult = 1.0;
var separationMult = 1.0;
var cohesionMult = 1.0;
var flockCount = 100;

var backgroundColor = '#845f75'
var backgroundAlpha = 100;
var strokeColor = '#33bfc8'
var strokeAlpha = 255;


////////////////////////////////

//GUI Var sliderRanges
var radiusMin = .1;
var radiusMax = 50;
var radiusStep = .1;

var perceptionMin = 10;
var perceptionMax = 200;
var perceptionStep = .1;

var alignmentMultMin = .01;
var alignmentMultMax = 5.0;
var alignmentMultStep = .01;

var separationMultMin = .01;
var separationMultMax = 5.0;
var separationMultStep = .01;

var cohesionMultMin = .01;
var cohesionMultMax = 5.0;
var cohesionMultStep = .01;

var flockCountMin = 1;
var flockCountMax = 1000;
var flockCountStep = 1;

var backgroundAlphaMin = 0;
var backgroundAlphaMax = 255;
var backgroundAlphaStep = .1;

var strokeAlphaMin = 0;
var strokeAlphaMax = 255;
var strokeAlphaStep = .1;

//GUI Object
var gui;


function setup() {
	createCanvas(640, 360);
	colorMode(RGB);

	for(var i = 0; i < 150; i++) {
		flock.push(new Boid());
	}

	gui = createGui('Flock Settings');

	gui.addGlobals(
		'backgroundColor',
		'backgroundAlpha',
		'strokeColor',
		'strokeAlpha',
		'radius',
		'perception',
		'separationMult',
		'alignmentMult',
		'cohesionMult',
		'flockCount');

	fr = createP('');
	
}

function draw() {
	var bC = color(backgroundColor);
	background(red(bC), green(bC), blue(bC), backgroundAlpha);
	var sC = color(strokeColor);
	stroke(red(sC), green(sC), blue(sC), strokeAlpha);

	flock.forEach(boid => {
		boid.flock(flock);
		boid.update();
		boid.show();
	})

	fr.html(floor(frameRate())); //Display framerate
}