var rows, cols;
var w = 40; //width of cell
var grid = [];

var current;

var stack = [];

function setup() {
	createCanvas(400, 400, WEBGL);
	frameRate(10);
	rows = floor(height/w);
	cols = floor(width/w);

	for(var i = 0; i < rows; i++) {
		for(var j = 0; j < cols; j++) {
			//Make a new cell at every position in the grid
			//And store it in the grid array
			grid.push(new Cell(i, j));
			// console.log(i + " " + j);
		}
	}

	current = grid[0]; //Initial cell


}

function draw() {
	background(51);
	translate(-width/2, -height/2);
	// grid.forEach( cell => {
	// 	cell.show(); 
	// 	// console.log("shown");
	// })
	for(var i = 0; i < grid.length; i++) {
		grid[i].show();
	}

	current.visited = true;
	current.highlight();
	//STEP 1
	var next = current.checkNeighbors();
	if(next) {
		next.visited = true;

		//STEP 2
		stack.push(current);
		//STEP 3 - Remove wall between current cell and chosen cell
		removeWalls(current, next);
		//STEP 4
		current = next;
	} else if(stack.length > 0) {
		current = stack.pop();
	}

}

function index(i, j) {
	if(i < 0 || j < 0 || i > rows-1 || j > cols-1) {
		return -1;
	}
	return j + i * cols; //psuedo-2D indexing formula
}


function removeWalls(curr, nxt) {
	var x = curr.i - nxt.i;
	if(x == 1) { 
		curr.walls[3] = false; //Remove wall left of curr
		nxt.walls[1] = false; //Remove wall right of nxt
	} else {
		if(x == -1) {
			curr.walls[1] = false; //Remove wall right of curr
			nxt.walls[3] = false; //Remove wall left of nxt
		}
	}

	var y = curr.j - nxt.j;
	if(y == 1) {
		curr.walls[0] = false; //Remove wall top of curr
		nxt.walls[2] = false; //Remove wall bottom of nxt	
	} else {
		if(y == -1) {
			curr.walls[2] = false; //Remove wall bottom of curr
			nxt.walls[0] = false; //Remove wall top of nxt
		}
	}



}