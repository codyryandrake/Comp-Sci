//static vals
const state = {
	sides: 6, 
	stepsOut: 8,
	thinStroke: 1,
	thickStroke: 3,
}

//mods on static vals
const setState = (state) => {
	state.numShapes = randomSelect2() ? state.sides : state.sides * 2
	state.angle = 360/state.numShapes
	state.singleStep = (CRYSTAL_SIZE/2) / state.stepsOut
	state.weight = randomSelect2() ? state.thinStroke : state.thickStroke
	state.layerColor = getRandomFromPalette()
	state.fillColor = getRandomFromPalette()
}


const circles = (state) => {
	state.shapeSize = random(0,(CRYSTAL_SIZE/2) * 0.93)
	state.position = (CRYSTAL_SIZE/2) - (state.shapeSize/2)
	console.log(state.sides)

	return ({
		name: 'circles',
		state,
		render: () => {
			stroke(state.layerColor)
			strokeWeight(state.weight)
			noFill()
			push()
				//translate(width/2, height/2)
				for(let i = 0; i <= state.numShapes; i++) {
					ellipse(state.position, 0, state.shapeSize, state.shapeSize)
					rotate(state.angle)
				}
			pop()
		}	
	})


}

const simpleLines = (state) => {
	state.numSteps = randomSelect2() ? state.stepsOut : floor(state.stepsOut * 1.25) //8 or 10
	state.step = (CRYSTAL_SIZE/2) / state.numSteps //step = radius of crystal
	//Pick a start point (floored) between 0 and the # of steps
	state.start = floor(random(0, state.numSteps)) 
	//Pick an end point (floored) between our start point and one more than the # of steps		
	state.stop = floor(random(state.start, state.numSteps+1)) 

	return ({
		name: 'simpleLines',
		state,
		render: () => {
			noFill()
			stroke(state.layerColor)
			strokeWeight(state.weight)
			push() //wrap fancy translate in a push/pop
				//translate(width/2, height/2) //Move origin (0,0) to center of screen
				for(let i = 0; i < state.numShapes; i++) { //For every shape
					//Create a line from start to stop along only the x-axis
					line(state.start * state.step, 0, state.stop * state.step, 0)
					//Rotate each line an additional fraction of the circle
					//These calls to rotate() stack and thus each line is 
					//transformed further around the circle
					rotate(state.angle)
				}
			pop()
		}	
	})

}

const squareDots = (state) => {
	state.shapeSize = randomSelect2() ? 3 : 5
	state.centerOffset = state.singleStep

	return ({
		name: 'squareDots',
		state,
		render: () => {
			// noFill()
			fill(state.layerColor)
			strokeWeight(state.strokeWeight)
			push()
				//translate(width/2, height/2)
				for(let i = 0; i < state.numShapes; i++) {
					for(let j = state.centerOffset; j < CRYSTAL_SIZE/2; j+= state.singleStep)
						rect(j, 0, state.shapeSize, state.shapeSize)
						rotate(state.angle)
				}
			pop()
	}	
	})

}

const centeredShape = (state) => {
	state.randomShape = random(1)
	//Pick a random shapeSize between 5 and 9 steps
	state.shapeSize = floor(random(state.stepsOut / 2, state.stepsOut-2)) * state.singleStep

	return ({
		name: 'centeredShape',
		state,
		render: () => {
			fill(state.layerColor)
			push()
				//translate(width/2, height/2)
				if(state.randomShape > .7)
					ellipse(0,0, state.shapeSize * 2, state.shapeSize * 2)
				else if(state.randomShape > .5)
					rect(0,0, state.shapeSize * 1.5, state.shapeSize * 1.5)
				else {
					rotate((state.angle*state.sides)/2) //Oddly complicated rotation
					hexagon(0, 0, state.shapeSize)				
				}
			pop()
		}
	})

}

const outlineShape = (state) => {
	state.hexagonTrue = randomSelect2()

	return ({
		name: 'outlineShape',
		state,
		render: () => {
			noFill()
			stroke(state.layerColor)
			push()
				//translate(width/2, height/2)
				strokeWeight(state.weight)
				if(state.hexagonTrue)
					hexagon(0, 0, CRYSTAL_SIZE/2)
				else
					ellipse(0, 0, CRYSTAL_SIZE, CRYSTAL_SIZE)
			pop()
		}
	})

}

const ringOfShapes = (state) => {
	state.steps = floor(random(1, state.stepsOut))
	state.center = state.steps * state.singleStep
	state.randomShape = random(1)
	state.direction = randomSelect2()
	state.fillColor = randomSelect2() ? state.layerColor : color(0, 1)

	if(state.steps < state.stepsOut / 2) 
		state.radius = floor(random(1, state.steps)) * state.singleStep
	else if(state.steps > state.stepsOut / 2) 
		state.radius = floor(random(1, state.stepsOut - state.steps)) * state.singleStep
	else
		state.radius = floor(random(1, state.stepsOut / 2 + 1)) * state.singleStep

	return ({
		name: 'ringOfShapes',
		state,
		render: () => {
			stroke(state.layerColor)
			fill(state.fillColor)
			strokeWeight(state.weight)
			push()
				//translate(width/2, height/2)
				for(let i = 0; i < state.numShapes; i++) {
					if(state.randomShape < .33)
						ellipse(0, state.center, state.radius, state.radius)
					else if(state.randomShape >= .33 && state.randomShape < .66)
						rect(0, state.center, state.radius, state.radius)
					else
						myTriangle(state.center, state.radius, state.direction)
					rotate(state.angle)
				}
			pop()
		}
	})


}

const steppedHexagons = (state) => {
	state.numSteps = randomSelect2() ? state.stepsOut : state.stepsOut * 1.25
	state.centerOffset = (CRYSTAL_SIZE/2) * .15
	state.singleStep = ((CRYSTAL_SIZE/2) - state.centerOffset) / state.numSteps

	return ({
		name: 'steppedHexagon',
		state,
		render: () => {
			stroke(state.layerColor)
			noFill()
			strokeWeight(state.weight)
			push()
				//translate(width/2, height/2)
				rotate((state.angle*state.sides)/2)
				for(let i = 0; i < state.numSteps; i++) {
					hexagon(0, 0, state.centerOffset + (i * state.singleStep))
				}
			pop()
		}	
	})

}

const testLines = (state) => {
	return ({
		name: 'testLines',
		state,
		render: () => {
		stroke(state.layerColor)
		push() //wrap fancy translate in a push/pop
			ellipse(0, 0, CRYSTAL_SIZE, CRYSTAL_SIZE) //Outer limits of crystal
			const angle = 360 / state.numShapes
			for(let i = 0; i < state.numShapes; i++) {
				line(0,0,CRYSTAL_SIZE/2,0)
				rotate(state.angle)
			}
		pop()	
	}
	})


}