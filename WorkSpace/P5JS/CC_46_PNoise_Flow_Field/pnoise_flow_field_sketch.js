var scl = 10;
var cols, rows;


var fr; 

var particles = [];

var flowField = [];

//gui params
// var backgroundHue = 0;
// var backgroundSat = 255;
// var backgroundBal = 20;

var bgColor = '#751c8e' 
var backgroundAlpha = 20;
var sColor = '#ffc0a2';
var dotSize = 3;
var strokeAlpha = 100;


var angleVal = -5;
var pathMagnitude = 1;
var zoom = 1.0;
var inc = .1;
var incAdjust = .1;
var zoff = .01;
var zoffAdjust = .01; 
var rainbowTrails = false;

//'magic' slider range vals
//////////////////////////////////////////////////

var backgroundAlphaMin = 0;
var backgroundAlphaMax = 255;

var dotSizetMin = 0.01;
var dotSizeMax = 30;
var dotSizeStep = 0.01;

var strokeAlphaMin = 0;
var strokeAlphaMax = 255;

var pathMagnitudeMin = .001;
var pathMagnitudeMax = 5;
var pathMagnitudeStep = .01;


var zoomMin = 1.0;
var zoomMax = 4.0;
var zoomStep = .01;

var zoffAdjustMin = -1;
var zoffAdjustMax = 1;
var zoffAdjustStep = .001;

var incAdjustMin = -1;
var incAdjustMax = 1;
var incAdjustStep = .001;

//////////////////////////////////////////////////

//gui
var visible = true;
var gui, gui2;

function setup() {
	var angleValMin = -TWO_PI*4;
	var angleValMax = TWO_PI*4;
	// createCanvas(400, 400);
	angleMode(DEGREES)
	createCanvas(window.innerWidth, window.innerHeight);

	gui = createGui('HSB+ GUI');
	gui.addGlobals(
		'bgColor',
		'sColor',
		'backgroundAlpha',
		'dotSize',
		'strokeAlpha',
		'angleVal',
		'pathMagnitude',
		'zoom',
		'zoffAdjust',
		'incAdjust',
		'rainbowTrails');
	
	cols = floor(width/scl);
	rows = floor(height/scl);
	fr = createP('');

	flowField = new Array(cols * rows);

	for(var i = 0; i < 2000; i++) {
		particles[i] = new Particle();
	}

}

function draw() {
	colorMode(RGB);
	var cb = color(bgColor);
	var c = color(sColor);
	colorMode(RGB);
	background(red(cb), green(cb), blue(cb), backgroundAlpha);
	stroke(red(c), green(c), blue(c), strokeAlpha);
	strokeWeight(dotSize);
	
	
	scale(zoom);

	inc = incAdjust;
	var yoff = 0;
	for(var y = 0; y < rows; y++) {
		var xoff = 0;
		for(var x = 0; x < cols; x++) {
			var index = (x + y * cols);
			var angle = noise(xoff, yoff, zoff) * angleVal;
			var v = p5.Vector.fromAngle(angle);
			v.setMag(pathMagnitude);
			flowField[index] = v;
			xoff += inc;

		}
		yoff += inc;
		
	}
	zoff += zoffAdjust;


	particles.forEach(particle => {
		particle.follow(flowField);
		particle.edges();
		particle.show();
		particle.update();
	})
	
	fr.html(floor(frameRate()));
}


















// function draw() {
// 	background(51);
// 	stroke(255);
// 	noFill();
// 	yoff = start; //start yoff at 0
// 	for(var x = 0; x < width; x++) { //Increment through every x position
		
// 		var y = map(noise(yoff), 0, 1, 0, height); //and pick a y from noise(yoff)
// 		point(x, y); //draw a point using these two coordinate
		
// 		yoff += 0.001; //Increment our noise value along the x axis (time)
// 	}
	
// 	start += inc; //Bump up where we start picking noise from once after every loop
// 	//This creates a motion effect because we repeat the noise values every loop.
// 	//Try commenting out the line above to see how it causes motion. 




// 	// var x = map(noise(xoff), 0, 1, 0, width);
// 	// var y = map(noise(yoff), 0, 1, 0, height);
// 	// xFill = map(x, y ,width, 0 ,255);
// 	// fill(0, xFill)
// 	// ellipse(x, y, 24, 24);
// 	// xoff += 0.01;
// 	// yoff += 0.01;
// }