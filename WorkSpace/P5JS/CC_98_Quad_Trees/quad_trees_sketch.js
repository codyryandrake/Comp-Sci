let tree;

let count = 0;

function setup() {
	createCanvas(400, 400);
	background(255);
	let boundary = new Rectangle(200, 200, 200, 200);
	tree = new QuadTree(boundary, 4);
	// console.log(tree);

	for (let i = 0; i < 300; i++) {
		let x = randomGaussian(width / 2, width / 8);
		let y = randomGaussian(height / 2, height / 8);
		let p = new Point(x,y);
		tree.insert(p);
	}
	

}

function draw() {
	count = 0;
	background(0);
	tree.show();

	stroke(0, 255, 0);

	rectMode(CENTER);
	let range = new Rectangle(mouseX, mouseY, 25, 25);
	rect(range.x, range.y, range.w*2, range.h*2);
	let points = tree.query(range)
	points.forEach(p => {
		push();
		strokeWeight(3);
		point(p.x, p.y);
		pop();
	})
	if(frameCount % 60 == 0)
		console.log(count);
}