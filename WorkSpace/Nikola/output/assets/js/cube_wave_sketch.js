let angle = 0;
let w = 12; //x width increment
let q = 12; //z width increment
let ma = 0; //measured angle for ortho
let maxD = 0; //


function setup() {
	createCanvas(400, 400, WEBGL);
	ma = asin(1 / sqrt(2));
	maxD = dist(0, 0, width/2, height/2);

}

function draw() {
	background(0, 100);
	ortho(-320, 300, 300, -310, 0, 1000);

	rotateX(QUARTER_PI); //Rotate x -45 degrees
	rotateY(ma);
	// rotateX	(angle/16);
	// rotateY(angle/16);
	// rotateZ(angle/16);
	rectMode(CENTER);

	for (let z = 0; z < height; z+= q) {
		for (let x = 0; x < width; x+= w) {
			push();
			let d = dist(x, z, width/2, height/2);
			let offset = map(d, 0, maxD, -PI, PI);
			let a = angle + offset;
			let h = map(sin(a), -1, 1, 100, 300);
			translate(x - width/2, 0, z - height/2);
			normalMaterial();
			//fill(0, 100)
			box	(w-2, h, w-2);
			pop();
		}
	}
	
	angle -= 0.05; //Radians


	
}