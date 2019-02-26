

let CRYSTAL_SIZE = 500
let SIDES = 6
const PALETTE = [
[
	'#6b5b95', //Palette 0
	'#feb236',
	'#d64161',
	],
	[
	'#eeac99',
	'#e06377',
	'#c83349',
	],
	[
	'#5b9aa0',
	'#d6d4e0',
	'#b8a9c9',
	],
	[
	'#e3eaa7',
	'#86af49',
	'#eca1a6',
	],
	[
	'#92a8d1',
	'#034f84',
	'#f7cac9',
	],
	[
	'#000000', //Palette 0
	'#7F95D1',
	'#FF82A9',
	],
	[
	'#C8ADC0',
	'#7765E3',
	'#3B60E4',
	],
	[
	'#D00000',
	'#FFBA08',
	'#3F88C5',
	],
	[
	'#E40066',
	'#03CEA4',
	'#FFBA08',
	],
	[
	'#45462A',
	'#7E5920',
	'#DC851F',
	],
	[
	'#635C51',
	'#0F110C',
	'#1E1E24',
	],
	]
let paletteButton = 0
// const SELECT_PALETTE = random(1)
const NUM_PALETTES = PALETTE.length
const NUM_COLORS = PALETTE[0].length
let ALL_CRYSTALS = new Array(10)

const PADDING = CRYSTAL_SIZE * 0.1
const MARGIN = CRYSTAL_SIZE/2
const GRIDBOX = CRYSTAL_SIZE + PADDING
const COLS = 1
const ROWS = 1
const START = MARGIN * 2

var x = 1
var y = 1
let easing = 0.2
let count = 0
let counter = 0
let scaleFactor = 0
let dx = 0
let xy = 0
let mouseClickX = 0
let mouseClickY = 0
let mousePrev = 0

function setup() {

	const totalX = START + GRIDBOX * COLS
	const totalY = START + GRIDBOX * ROWS
	createCanvas(500, 500, SVG)
	background(0)


console.log("Palettes: ", NUM_PALETTES)
console.log("Colors per Palette: ", NUM_COLORS)
	

	//noLoop()
	frameRate(10)
	angleMode(DEGREES)
	rectMode(CENTER)
	//noCursor()
	

}

function draw() {
	//background(getRandomFromPalette())
	//Draw transparent rectangle over canvas every draw()
	//to slowly fade out crystals
background(0, 10)
	var targetX = mouseX
	dx = targetX - x
	x += dx * easing + random(-.1, .1)

	var targetY = mouseY
	dy = targetY - y
	y += dy * easing + random(-.1, .1)

	if(scaleFactor >= .5)
		scaleFactor = -scaleFactor
	else if (scaleFactor < .5)
		scaleFactor = -scaleFactor


	if(ALL_CRYSTALS.length > 10) {
		ALL_CRYSTALS.length = 0
		
	}

	// for(let x = 0; x < COLS; x++) {
	// 	for(let y = 0; y < ROWS; y++) {
			// const posX = START + x * GRIDBOX
			// const posY = START + y * GRIDBOX
			// const posX = mouseX //+ random(20, 50)
			// const posY = mouseY //+ random(30, 70)


			ALL_CRYSTALS.push(new Crystal(x, y))
			//crystal = new Crystal(x, y)
	// 	}
	// }
		ALL_CRYSTALS.forEach(crystal => {
			crystal.render()
		})

		//count = (count + 1) % 36
}

function mousePressed() {
	console.log("Mouse Pressed")
	// save("mySVG.svg");      // give file name
 //  	print ("saved svg");
 //  	noLoop();								// we just want to export once
	paletteButton = (paletteButton + 1) % NUM_PALETTES
	// redraw()
	mouseClickX = mouseX
	mouseClickY = mouseY
}

function mouseMoved() {
	counter++
	if(counter > 10) {
		counter = 0
		// SIDES = floor(random(6, 12))
		// CRYSTAL_SIZE = (CRYSTAL_SIZE +10 + noise(0)*100) % 200
		//redraw()
	}
}

function keyPressed() {
  if (keyCode === RIGHT_ARROW) {
    //redraw()
    return true
  } else if (keyCode === LEFT_ARROW) {
    //value = 0;
  }
}