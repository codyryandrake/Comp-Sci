let x;
let t = 0;
let t2 = 10;
let noiseArray;

function setup() {
	createCanvas(600, 400);
	noiseArray = [width][height];
}
function draw() {
	background(0, 10);
	fill(255);
	x = map(noise(t), 0, 1, 0, width);
	y = map(noise(t2), 0, 1, 0, height);
	// ellipse(x, height/2, 30, 30);
	// t += .01;

	for(i = 0; i < width; i++) {
		t += .01
		for(j = 0; j < height; j++) {
			t2 += .01
			ellipse(i, j, x, y)
		}
	}
	// noiseArray.forEach()
}