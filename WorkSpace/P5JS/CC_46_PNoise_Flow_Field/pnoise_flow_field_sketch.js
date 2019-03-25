var scl = 30;
var cols, rows;


var fr; 

var particles = [];

var flowField = [];

//gui params
var backgroundColor = '#000080' 
var backgroundAlpha = 223;
var strokeColor = '#ff0000';
// var colorChangeSpeed = 0.01;
var dotSize = 24.31;
var strokeAlpha = 37;


var angleVal = 4;
var angleMult = -1.2;
var pathMagnitude = 1;
var zoom = 1.0;
var inc = .1;
var incAdjust = .1;
var zoff = .01;
var zoffAdjust = .01; 


//'magic' slider range vals
//////////////////////////////////////////////////
var sclMin = 5;
var sclMax = 20;
var sclStep = 1;


var backgroundAlphaMin = 0;
var backgroundAlphaMax = 255;

// var colorChangeSpeedMin = 0.01;
// var colorChangeSpeedMax = 1;
// var colorChangeSpeedStep = .01;

var dotSizetMin = 0.01;
var dotSizeMax = 30;
var dotSizeStep = 0.01;

var strokeAlphaMin = 0;
var strokeAlphaMax = 255;

var pathMagnitudeMin = -2;
var pathMagnitudeMax = 2;
var pathMagnitudeStep = .001;

var angleValMin = -3;
var angleValMax = 3;
var angleValStep = .1;

var angleMultMin = -10;
var angleMultMax = 10;
var angleMultStep = .1;

var zoomMin = 1.0;
var zoomMax = 10;
var zoomStep = 1;

var zoffAdjustMin = -10;
var zoffAdjustMax = 10;
var zoffAdjustStep = .001;

var incAdjustMin = -10;
var incAdjustMax = 10;
var incAdjustStep = .001;

//////////////////////////////////////////////////

//gui
var visible = true;
var rainbowTrails = false;
var showFlowField = false;
var gui, gui2;

function setup() {

	// createCanvas(400, 400);
	angleMode(DEGREES);
	createCanvas(windowWidth, windowHeight);

	gui = createGui('HSB+ GUI');
	gui.addGlobals(
		'backgroundColor',
		'strokeColor',
		// 'colorChangeSpeed',
		'backgroundAlpha',
		'dotSize',
		'strokeAlpha',
		'angleVal',
		'angleMult',
		'pathMagnitude',
		'zoom',
		'zoffAdjust',
		'incAdjust',
		'rainbowTrails',
		'showFlowField');
	
	cols = floor(width/scl);
	rows = floor(height/scl);
	fr = createP('');

	flowField = new Array(cols * rows);

	for(var i = 0; i < 2500; i++) {
		particles[i] = new Particle();
	}

}

function draw() {
	// if(keyIsPressed) {
	// 	if(keyCode = '`') {
	// 		// saveFrames('out', 'png', 1, 25, data => {
	// 		// 	print(data);
	// 		// });
	// 		save('myCanvas.jpg');
	// 		noLoop();
	// 	}
	// }


	colorMode(RGB);
	var cb = color(backgroundColor);
	var c = color(strokeColor);
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
			var angle = ((noise(xoff, yoff, zoff) * angleVal)) * angleMult;
			var v = p5.Vector.fromAngle(angle);
			v.setMag(pathMagnitude);
			// if(mouseIsPressed) {
			// 	// var vec = createVector(mouseX-width/2, mouseY-height/2);
			// 	var vec = p5.Vector.lerp(v, createVector(sin(mouseY), cos(mouseX)), 5);
			// 	v.add(vec);
			// }
			flowField[index] = v;
			xoff += inc;
			

			if(showFlowField) {
				push();
					translate(x * scl, y * scl);
					rotate(v.heading());
					strokeWeight(1);
					line(0, 0, scl, 0);
				pop();
			}
			
			
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