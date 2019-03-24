

var inc = .1;
var scl = 10;
var cols, rows;

var zoff = 0;

var fr; 

var particles = [];

var flowField = [];

let bH, bS, bB;
let angleVal;

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
	//frameRate(10);


	bH = createSlider(0, 255, 0);
	bH.position(20, 20);
	bS = createSlider(0, 255, 255);
	bS.position(20, bH.y + 40);
	bB = createSlider(0, 255, 20);
	bB.position(20, bH.y + 80);
	bA = createSlider(0, 255, 10);
	bA.position(20, bH.y + 120);

	sH = createSlider(0, 255, 0);
	sH.position(20, bA.y + 120);
	sS = createSlider(0, 255, 255);
	sS.position(20, sH.y + 40);
	sB = createSlider(0, 255, 255);
	sB.position(20, sH.y + 80);
	sA = createSlider(0, 255, 100);
	sA.position(20, sH.y + 120);

	angleVal = createSlider(-TWO_PI*4, TWO_PI*4, TWO_PI);
	angleVal.position(20, bH.y + 160);
}

function draw() {
	background(bH.value(), bS.value(), bB.value(), bA.value());
	push();
	fill(150);
	rect(0, 0, bH.x*8, height/2);
	fill(0);
	noStroke();
	textSize(15);
	text('bHue', bH.x + 10, bH.y + 30);
	text('bSat', bS.x + 10, bS.y + 30);
	text('bBal', bB.x + 10, bB.y + 30);
	text('bAlph', bA.x + 10, bA.y + 30);
	text('field angle adjust', angleVal.x + 10, angleVal.y + 30)
	textSize(12);
	text('*keep sHue at 0 to let particles pick their own colors', sH.x, sA.y + 50, 150);
	textSize(15);
	text('sHue', sH.x + 10, sH.y + 30);
	text('sSat', sS.x + 10, sS.y + 30);
	text('sBal', sB.x + 10, sB.y + 30);
	text('sAlph', sA.x + 10, sA.y + 30);
	pop();
	
	var yoff = 0;
	for(var y = 0; y < rows; y++) {
		var xoff = 0;
		for(var x = 0; x < cols; x++) {
			var index = (x + y * cols);
			var angle = noise(xoff, yoff, zoff) * angleVal.value();
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
	zoff += .01;
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