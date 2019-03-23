var grid;
var next;


var dA = 1.0;
var dB = .5;
var feed = 0.0545;
var k = 0.062;
var t = 1.0;

function setup() {
	// createCanvas(window.innerWidth, window.innerHeight);
	createCanvas(400, 400);
	//frameRate(1);
	pixelDensity(1);



	grid = [];
	next = [];
	for(var x = 0; x < width; x++) {
		grid[x] = [];
		next[x] = [];
		for(var y = 0; y < height; y++) {
			grid[x][y] = { a: 1, b: 0 }
			next[x][y] = { a: 1, b: 0 }
		}
	}

	
}

function draw() {
	background(51);



	for(var x = 1; x < width-1; x++) {
		for(var y = 1; y < height-1; y++) {
			var a = grid[x][y].a;
			var b = grid[x][y].b;
			next[x][y].a = (a + 
						   (dA * laPlaceA(x, y)) - 
						   (a * b * b) + 
						   feed*(1-a))*t;

			next[x][y].b = (b + 
						   (dB * laPlaceB(x, y)) + 
						   (a * b * b) - 
						   (k+feed)*b)*t;

			next[x][y].a = constrain(next[x][y].a, 0, 1);
			next[x][y].b = constrain(next[x][y].b, 0, 1);

		}
	}

	loadPixels();
	for(var x = 0; x < width; x++) {
		for(var y = 0; y < height; y++) {
			var pix = (x + y * width)*4;
			var a = next[x][y].a;
			var b = next[x][y].b;
			var c = floor((a-b)*255);
			c = constrain(c, 0 ,255);
			pixels[pix + 0] = c; 
			pixels[pix + 1] = c; 
			pixels[pix + 2] = c; 
			pixels[pix + 3] = 255; 
		}
	}
	updatePixels();

	swap();


}

function swap() {
	var temp = grid;
	grid = next;
	next = temp;
}

function laPlaceA(x, y) {
	var sumA = 0;

	sumA += grid[x][y].a * -1;
	sumA += grid[x-1][y].a * 0.2;
	sumA += grid[x+1][y].a * 0.2;
	sumA += grid[x][y-1].a * 0.2;
	sumA += grid[x][y+1].a * 0.2;
	sumA += grid[x-1][y-1].a * 0.05;
	sumA += grid[x-1][y+1].a * 0.05;
	sumA += grid[x+1][y+1].a * 0.05;
	sumA += grid[x+1][y-1].a * 0.05;
	return sumA;
}

function laPlaceB(x, y) {
	var sumB = 0;

	sumB += grid[x][y].b * -1;
	sumB += grid[x-1][y].b * 0.2;
	sumB += grid[x+1][y].b * 0.2;
	sumB += grid[x][y-1].b * 0.2;
	sumB += grid[x][y+1].b * 0.2;
	sumB += grid[x-1][y-1].b * 0.05;
	sumB += grid[x-1][y+1].b * 0.05;
	sumB += grid[x+1][y+1].b * 0.05;
	sumB += grid[x+1][y-1].b * 0.05;
	return sumB;
}

function mousePressed() {
	// grid[floor(mouseX)][floor(mouseY)].a = .1;
	// grid[floor(mouseX)][floor(mouseY)].b = 1;
	console.log(mouseX, mouseY);

	for(var i = floor(random(mouseY)); i < mouseX; i++) {
		for(var j = mouseY-100; j < mouseY; j++) {
			grid[i][j].b = 1;
		}
	}
}