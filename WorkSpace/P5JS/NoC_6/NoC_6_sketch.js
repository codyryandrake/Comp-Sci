var cars = [];
var field;


function setup() {
	createCanvas(400, 400);
	noCursor();
	field = new FlowField(20, width, height);
	for(var i = 0; i < 10; i++) {
		cars.push(car = new Vehicle(random(width), random(height)));
	}
	
}

function draw() {
	background(0);
	var mouseLoc = createVector(mouseX, mouseY);
	circle(mouseLoc.x, mouseLoc.y, 10);
	cars.forEach(car => {
		//car.seek(mouseLoc)
		car.followField(field);
		car.update();
		car.display();
	})

}