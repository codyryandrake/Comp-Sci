function hexToRgb(hex) {
    var bigint = parseInt(hex, 16);
    var r = (bigint >> 16) & 255;
    var g = (bigint >> 8) & 255;
    var b = bigint & 255;

    return [r,g,b].join();
}


function hexagon(posX, posY, radius) {
	const rotAngle = 360 / 6
	beginShape()
		for(let i = 0; i < 6; i++) {
			const thisVertex = pointOnCircle(posX, posY, radius, i * rotAngle)
			vertex(thisVertex.x, thisVertex.y)
		}
	endShape(CLOSE)
}

function pointOnCircle(posX, posY, radius, angle) {
	const x = posX + radius * cos(angle)
	const y = posY + radius * sin(angle)
	return createVector(x, y)
}

//Return true 50% of the time
function randomSelect2 () {
	const rand = (random(1) > 0.5) ? true : false
	return rand
}

//Returns a random palette from the PALETTE[]
function getRandomFromPalette () {
	//Return a random palette and color upon request
	return PALETTE[paletteButton][floor(random(0, NUM_COLORS))]
}

function myTriangle (center, radius, direction) {
	if(direction) {
		beginShape()
			vertex(center + radius * cos(0), radius * sin(0))
			vertex(center + radius * cos(120), radius * sin(120))
			vertex(center + radius * cos(240), radius * sin(240))
		endShape(CLOSE)
	}
	else {
		beginShape()
			vertex(center + radius * -cos(0), radius * sin(0))
			vertex(center + radius * -cos(120), radius * sin(120))
			vertex(center + radius * -cos(240), radius * sin(240))
		endShape(CLOSE)
	}
}

function randomizeSides() {
	SIDES = floor(random(2,6))
}

const layerConstructors = [
	{
		name: 'Outline Shape',
		init: () => new OutlineShape(),
		weight: 0.3
	},
	{
		name: 'Centered Shape',
		init: () => new CenteredShape(),
		weight: 0.3
	},
	{
		name: 'Stepped Hexagons',
		init: () => new SteppedHexagons(),
		weight: 0.3
	},
	{
		name: 'Circles',
		init: () => new Circles(),
		weight: 0.3
	},
	// {
	// 	name: 'Spheres',
	// 	init: () => new Spheres(),
	// 	weight: 0.3
	// },
	{
		name: 'Simple Lines',
		init: () => new SimpleLines(),
		weight: 0.3
	},
	{
		name: 'Square Dots',
		init: () => new SquareDots(),
		weight: 0.3
	},
	{
		name: 'Ring of Shapes',
		init: () => new RingOfShapes(),
		weight: 0.3
	},
	// {
	// 	name: 'Test Lines',
	// 	init: () => new TestLines(),
	// 	weight: 1
	// },
]