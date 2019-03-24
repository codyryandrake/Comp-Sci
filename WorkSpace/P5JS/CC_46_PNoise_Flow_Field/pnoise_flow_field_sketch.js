

var inc = 0.1;
var scl = 10;
var cols, rows;

var zoff = 0;

var fr; 

var particles = [];

var flowField = [];

function setup() {
	// createCanvas(400, 400);
	colorMode(HSB, 255);
	createCanvas(window.innerWidth, window.innerHeight);
	
	cols = floor(width/scl);
	rows = floor(height/scl);
	fr = createP('');

	flowField = new Array(cols * rows);

	for(var i = 0; i < 2000; i++) {
		particles[i] = new Particle();
	}
// background(255, 2);
	

	
}

function draw() {
	background(0, 10);
	var yoff = 0;
	for(var y = 0; y < rows; y++) {
		var xoff = 0;
		for(var x = 0; x < cols; x++) {
			var index = (x + y * cols);
			var angle = noise(xoff, yoff, zoff) * TWO_PI;
			var v = p5.Vector.fromAngle(angle);
			v.setMag(1);
			flowField[index] = v;
			xoff += inc;
			//stroke(0, 110);
			// push();
			// 	stroke(0, 50);
			// 	translate(x * scl, y * scl);
			// 	rotate(v.heading());
			// 	//line(0, 0, scl, 0);
			// pop();
		}
		yoff += inc;
		
	}
	zoff += .01
	// if (zoff > .1) {
	// 	zoff = -zoff
	// }
	// if(zoff < -.1) {
	// 	zoff = -zoff;
	// }


		for(var i = 0; i < particles.length; i++) {
				particles[i].follow(flowField);
				particles[i].edges();
				particles[i].show();
				particles[i].update();
				
	}
	// particles.forEach(particle => {
	// 	particle.follow(flowField);
	// 	particle.show();
	// 	particle.update();
	// })
	
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