

const CRYSTAL_SIZE = 200
const SIDES = 16
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
	]

// const SELECT_PALETTE = random(1)
const NUM_PALETTES = PALETTE.length
const NUM_COLORS = PALETTE[0].length
const ALL_CRYSTALS = []

const PADDING = CRYSTAL_SIZE * 0.1
const MARGIN = CRYSTAL_SIZE/2
const GRIDBOX = CRYSTAL_SIZE + PADDING
const COLS = 1
const ROWS = 1
const START = MARGIN * 2
const totalX = START + GRIDBOX * COLS
const totalY = START + GRIDBOX * ROWS
let palettePlusPlus = 0
let flag = false
let index = 0
let frameCount; 

function setup() {


	createCanvas(totalX, totalY, SVG)


console.log("Palettes: ", NUM_PALETTES)
console.log("Colors per Palette: ", NUM_COLORS)

	//noLoop()
	frameRate(10)
	angleMode(DEGREES)
	rectMode(CENTER)
	background(0) 

	
	

}

function draw() {
	push()
	translate(width/2, height/2)
	fill(255)
	text(frameRate())
	pop()
	if(flag) {
		// background(PALETTE[9][floor(random(0,2))])
		background(0)
		flag = false
	}

	
	// for(let x = 0; x < COLS; x++) {
	// 	for(let y = 0; y < ROWS; y++) {
	// 		const posX = START + (x * GRIDBOX)
	// 		const posY = START + (y * GRIDBOX)
			const crystal = makeCrystal({x: mouseX, y: mouseY})
			console.log(crystal)
			ALL_CRYSTALS.push(crystal)
			// ALL_CRYSTALS.splice(index, ALL_CRYSTALS.length, crystal)
			// index = (index + 1) % (COLS*ROWS)
	// 	}
	// }

	ALL_CRYSTALS.forEach(crystal => {
		fill(0, 127) //2.22 EDIT
		rect(0, 0, width*2, height*2)// EDIT
		drawCrystal(crystal)
		crystal.slice()
	})


	if(ALL_CRYSTALS.length > 5) {	
		ALL_CRYSTALS.length = 0
		flag = true		
	}
}

function mousePressed() {
	console.log("Mouse Pressed")
	// save("mySVG.svg");      // give file name
 //  	print ("saved svg");
 //  	noLoop();
 									// we just want to export once
	redraw()
}

function keyPressed() {
  if (keyCode === BACKSPACE) {
    redraw()
  } else if (keyCode === RIGHT_ARROW) {
    palettePlusPlus = (palettePlusPlus + 1) % NUM_PALETTES
    redraw()
    console.log("Current Palette: ", palettePlusPlus)
  } else if (keyCode === LEFT_ARROW) {
  	if(palettePlusPlus <= 0) 
  		palettePlusPlus = NUM_PALETTES
    palettePlusPlus = (palettePlusPlus - 1) % NUM_PALETTES
    redraw()
    console.log("Current Palette: ", palettePlusPlus)
  } else if (keyCode = SHIFT) {
  	palettePlusPlus = floor(random(0, NUM_PALETTES))
  	redraw()
  	console.log("Current Palette: ", palettePlusPlus)
  }
  else{}
}