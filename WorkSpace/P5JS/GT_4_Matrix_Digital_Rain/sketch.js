var streams = [];
var symbolSize = 26;

function setup() {
	createCanvas(window.innerWidth, window.innerHeight);
	background(0);
	var x = 0;
	for(var i = 0; i < width/symbolSize; i++) {
		var stream = new Stream();
		stream.generateSymbols(x, random(-1000, 0));
		streams.push(stream);
		x += symbolSize;
		
	}
	textSize(symbolSize);
}

function draw() {
	background(0, 100);
	streams.forEach(stream => {
		stream.render();
	})
	console.log(mouseX);
}

function Symbol(x, y, speed, first) {
	this.x = x; 
	this.y = y;
	this.value; //Holds symbol
	this.speed = speed;
	this.count = 0;
	this.switchInterval = round(random(2, 20));
	this.first = first;

	this.setToRandomSymbol = function(x, y) {
		if(frameCount % this.switchInterval == 0) {
			this.value = String.fromCharCode(0x30A0 + round(random(0, 96)));
		}
		
	}

	this.rain = function() {
		this.y = (this.y >= height) ? 0 : this.y += this.speed;

		// this.count++;
		// if(this.count > 20) {
		// 	this.count = 0;
		// 	this.value = String.fromCharCode(0x30A0 + round(random(0, 96)));
		// }
		
	}

}


function Stream() {
	this.symbols = []; 
	this.totalSymbols = round(random(5, 20)); //Randomize length of stream
	this.speed = random(5, 20);

	this.generateSymbols = function(x, y) {
		var first = round(random(0, 4)) == 1;
		for(var i = 0; i < this.totalSymbols; i++) {
			symbol = new Symbol(x, y, this.speed, first);
			symbol.setToRandomSymbol();
			this.symbols.push(symbol);
			y -= symbolSize;
			first = false;
		}
	}

	this.render = function() {
		this.symbols.forEach(function(symbol) {
			if(symbol.first) {
				stroke(210, 255, 230);
				strokeWeight(2);
				fill(255, 255, 255);
			} else if (symbol.x <= mouseX+symbolSize/2 && symbol.x >= mouseX-symbolSize/2){
				noStroke();
				fill(random(255), random(255), random(255));
			} else {
				noStroke();
				fill(0, 255, 70);
			}
			text(symbol.value, symbol.x, symbol.y);
			symbol.rain();
			symbol.setToRandomSymbol();
		}); 
	}


}