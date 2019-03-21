var cells = [];
const cellSize = 60; //Fully-grown cell radius
const cellQuantity = 10;


function setup() {
	createCanvas(700, 700);
	for(var i = 0; i < cellQuantity; i++) {
		cells.push(new Cell());
	}

}


function draw() {
	background(255);


	for(var i = 0; i < cells.length; i++) {
		cells[i].move();
		cells[i].grow();
		cells[i].show();
		if(cells[i].died()) { //Life span
			cells.splice(i, 1, new Cell(null, null, null));
		}
	}

}

function mousePressed() {
	for(var i = cells.length-1; i >= 0; i--) { //Reverse loop
		if(cells[i].clicked(mouseX, mouseY)) {
			cellA = cells[i].mitosis();
			cellB = cells[i].mitosis();
			cells.splice(i, 1, cellA, cellB);
		}
	}
}

