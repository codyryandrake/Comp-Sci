const hexagon = (posX, posY, radius) => {
	const rotAngle = 360 / 6
	beginShape()
		for(let i = 0; i < 6; i++) {
			const thisVertex = pointOnCircle(posX, posY, radius, i * rotAngle)
			vertex(thisVertex.x, thisVertex.y)
		}
	endShape(CLOSE)
}

const pointOnCircle = (posX, posY, radius, angle) => {
	const x = posX + radius * cos(angle)
	const y = posY + radius * sin(angle)
	return createVector(x, y)
}

//Return true 50% of the time
const randomSelect2 = () => {
	const rand = (random(1) > 0.5) ? true : false
	return rand
}

//Returns a random palette from the PALETTE[]
const getRandomFromPalette = () => {
	//Return a random palette and color upon request
	// return PALETTE[floor(random(0, NUM_PALETTES))][floor(random(0, NUM_COLORS))]
	return PALETTE[palettePlusPlus][floor(random(0, NUM_COLORS))]
}

const myTriangle = (center, radius, direction) => {
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

const layerConstructors = [
	{
		name: 'Outline Shape',
		init: (props) => outlineShape({
			...props,
			...setState(state),
		}),
		weight: 0.3
	},
	{
		name: 'Centered Shape',
		init: (props) => centeredShape({
			...props,
			...setState(state),
		}),
		weight: 0.3
	},
	{
		name: 'Stepped Hexagons',
		init: (props) => steppedHexagons({
			...props,
			...setState(state),
		}),
		weight: 0.3
	},
	{
		name: 'Circles',
		init: (props) => circles({
			...props,
			...setState(state),
		}),
		weight: 0.3
	},
	{
		name: 'Simple Lines',
		init: (props) => simpleLines({
			...props,
			...setState(state),
		}),
		weight: 0.3
	},
	{
		name: 'Square Dots',
		init: (props) => squareDots({
			...props,
			...setState(state),
		}),
		weight: 0.3
	},
	{
		name: 'Ring Of Shapes',
		init: (props) => ringOfShapes({
			...props,
			...setState(state),
		}),
		weight: 0.3
	},
	{
		name: 'Test Lines',
		init: (props) => testLines({
			...props,
			...setState(state),
		}),
		weight: 1
	},
]

const makeCrystal = (pos) => {
	const layers = layerConstructors.map(lcon => {
		let picker = random(1)
		const draw = picker > lcon.weight
		return lcon.init({
				pos,
				draw //true or false bool
		})
	})

	//console.log(layers)
	return layers
}

const drawCrystal = (crystal) => {
	fill(0, 127) //2.22 EDIT
	rect(width/2, height/2, totalX, totalY) //2.22 EDIT
	crystal.forEach(layer => {
	if (layer.state.draw) {
		push()
		translate(layer.state.pos.x, layer.state.pos.y)
		layer.render()
		pop()
		}
	})

	this.layers = []
}