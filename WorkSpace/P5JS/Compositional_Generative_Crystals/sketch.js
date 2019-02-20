

const CRYSTAL_SIZE = 350
const SIDES = 17
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
const COLS = 4
const ROWS = 4
const START = MARGIN * 2
let palettePlusPlus = 0

function setup() {

	const totalX = START + GRIDBOX * COLS
	const totalY = START + GRIDBOX * ROWS
	createCanvas(totalX, totalY, SVG)


console.log("Palettes: ", NUM_PALETTES)
console.log("Colors per Palette: ", NUM_COLORS)

	noLoop()
	//frameRate(5)
	angleMode(DEGREES)
	rectMode(CENTER)

	
	

}

function draw() {
	// background(getRandomFromPalette())
	
	for(let x = 0; x < COLS; x++) {
		for(let y = 0; y < ROWS; y++) {
			const posX = START + x * GRIDBOX
			const posY = START + y * GRIDBOX
			const crystal = makeCrystal(posX, posY)
			console.log(crystal)
			// ALL_CRYSTALS.push(crystal)
		}
	}

	// ALL_CRYSTALS.forEach(crystal => {
	// 	crystal.render()
	// })

	// console.log(circles({test: 'hello world'}))
	// console.log(simpleLines({test: 'hello world'}))
	// console.log(squareDots({test: 'hello world'}))
	// console.log(centeredShape({test: 'hello world'}))
	// console.log(outlineShape({test: 'hello world'}))
	// console.log(ringOfShapes({test: 'hello world'}))
	// console.log(steppedHexagons({test: 'hello world'}))
	// console.log(testLines({test: 'hello world'}))
}

// function mousePressed() {
// 	console.log("Mouse Pressed")
// 	// save("mySVG.svg");      // give file name
//  //  	print ("saved svg");
//  //  	noLoop();								// we just want to export once
// 	//redraw()
// }

// function keyPressed() {
//   if (keyCode === BACKSPACE) {
//     redraw()
//   } else if (keyCode === RIGHT_ARROW) {
//     palettePlusPlus = (palettePlusPlus + 1) % NUM_PALETTES
//     redraw()
//     console.log("Current Palette: ", palettePlusPlus)
//   } else if (keyCode === LEFT_ARROW) {
//   	if(palettePlusPlus <= 0) 
//   		palettePlusPlus = NUM_PALETTES
//     palettePlusPlus = (palettePlusPlus - 1) % NUM_PALETTES
//     redraw()
//     console.log("Current Palette: ", palettePlusPlus)
//   } else if (keyCode = SHIFT) {
//   	palettePlusPlus = floor(random(0, NUM_PALETTES))
//   	redraw()
//   	console.log("Current Palette: ", palettePlusPlus)
//   }
//   else{}
// }