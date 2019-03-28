class FlowField {
	constructor(r, w, h) {
		this.resolution = r;
		this.rows = floor(w/this.resolution);
		this.cols = floor(h/this.resolution);	
		this.field = p5.Vector[this.rows][this.cols];
	}

	init() {
		var xoff = 0;
		for(var i = 0; i < cols; i++) {
			var yoff = 0;
			for(var j = 0; j < rows; j++) {
				var theta = 0;
				field[i][j] = map(noise(xoff, yoff), 0, 1, 0, TWO_PI);
			}
		}
	}

	display() {
		for(var i = 0; i < cols; i++) {
			for(var j = 0; j < rows; j++) {
				
			}
		}
	}

	lookUp(lookup) {
		var column = int(constrain(lookup.x/resolution, 0, cols-1));
		var row = int(constrain(lookup.y/resolution, 0, rows-1));
		return field[column][row].get();
	}
}