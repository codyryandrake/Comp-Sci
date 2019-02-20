class Layer {
	constructor() {
		this.sides = SIDES 
		this.numShapes = randomSelect2() ? this.sides : this.sides * 2
		this.angle = 360/this.numShapes
		this.stepsOut = 8
		this.singleStep = (CRYSTAL_SIZE/2) / this.stepsOut
		this.thinStroke = 1
		this.thickStroke = 3
		this.weight = randomSelect2() ? this.thinStroke : this.thickStroke
		this.layerColor = getRandomFromPalette()
		this.fillColor = getRandomFromPalette()
	}
}

class Circles extends Layer {
	constructor() {
		super() //Apply all vals from parent to child
		this.shapeSize = random(0,(CRYSTAL_SIZE/2) * 0.93)
		this.position = (CRYSTAL_SIZE/2) - (this.shapeSize/2)
		console.log(this.sides)
	}

	render() {
		stroke(this.layerColor)
		strokeWeight(this.weight)
		noFill()
		push()
			//translate(width/2, height/2)
			for(let i = 0; i <= this.numShapes; i++) {
				ellipse(this.position, 0, this.shapeSize, this.shapeSize)
				rotate(this.angle)
			}
		pop()
	}

}

// class Spheres extends Layer {
// 	constructor() {
// 		super() //Apply all vals from parent to child
// 		this.shapeSize = random(0,(CRYSTAL_SIZE/2) * 0.93)
// 		this.position = (CRYSTAL_SIZE/2) - (this.shapeSize/2)
// 		console.log(this.sides)
// 	}

// 	render() {
// 		push()
// 			//translate(width/2, height/2)
// 			for(let i = 0; i <= this.numShapes; i++) {
// 				ambientLight(100)
// 				ambientMaterial(250)
// 				sphere(20)
// 				rotate(this.angle)
// 			}
// 		pop()
// 	}

// }

class SimpleLines extends Layer {
	constructor() {
		super()
		this.numSteps = randomSelect2() ? this.stepsOut : floor(this.stepsOut * 1.25) //8 or 10
		this.step = (CRYSTAL_SIZE/2) / this.numSteps //step = radius of crystal
		//Pick a start point (floored) between 0 and the # of steps
		this.start = floor(random(0, this.numSteps)) 
		//Pick an end point (floored) between our start point and one more than the # of steps		
		this.stop = floor(random(this.start, this.numSteps+1)) 
	}

	render() {
		noFill()
		stroke(this.layerColor)
		strokeWeight(this.weight)
		push() //wrap fancy translate in a push/pop
			//translate(width/2, height/2) //Move origin (0,0) to center of screen
			for(let i = 0; i < this.numShapes; i++) { //For every shape
				//Create a line from start to stop along only the x-axis
				line(this.start * this.step, 0, this.stop * this.step, 0)
				//Rotate each line an additional fraction of the circle
				//These calls to rotate() stack and thus each line is 
				//transformed further around the circle
				rotate(this.angle)
			}
		pop()
	}
}

class SquareDots extends Layer {
	constructor() {
		super()
		this.shapeSize = randomSelect2() ? 3 : 5
		this.centerOffset = this.singleStep
	}

	render() {
		// noFill()
		fill(this.layerColor)
		strokeWeight(this.strokeWeight)
		push()
			//translate(width/2, height/2)
			for(let i = 0; i < this.numShapes; i++) {
				for(let j = this.centerOffset; j < CRYSTAL_SIZE/2; j+= this.singleStep)
					rect(j, 0, this.shapeSize, this.shapeSize)
					rotate(this.angle)
			}
		pop()
	}
}

class CenteredShape extends Layer {
	constructor() {
		super()
		this.randomShape = random(1)
		//Pick a random shapeSize between 5 and 9 steps
		this.shapeSize = floor(random(this.stepsOut / 2, this.stepsOut-2)) * this.singleStep
	}

	render() {
		fill(this.layerColor)
		push()
			//translate(width/2, height/2)
			if(this.randomShape > .7)
				ellipse(0,0, this.shapeSize * 2, this.shapeSize * 2)
			else if(this.randomShape > .5)
				rect(0,0, this.shapeSize * 1.5, this.shapeSize * 1.5)
			else {
				rotate((this.angle*this.sides)/2) //Oddly complicated rotation
				hexagon(0, 0, this.shapeSize)				
			}
		pop()

	}
}

class OutlineShape extends Layer{
	constructor() {
		super()
		this.hexagonTrue = randomSelect2() ? true : false
	}

	render() {
		noFill()
		stroke(this.layerColor)
		push()
			//translate(width/2, height/2)
			strokeWeight(this.weight)
			if(this.hexagonTrue)
				hexagon(0, 0, CRYSTAL_SIZE/2)
			else
				ellipse(0, 0, CRYSTAL_SIZE, CRYSTAL_SIZE)
		pop()
	}
}

class RingOfShapes extends Layer {
	constructor() {
		super()
		this.steps = floor(random(1, this.stepsOut))
		this.center = this.steps * this.singleStep
		this.randomShape = random(1)
		this.direction = randomSelect2()
		this.fillColor = randomSelect2() ? this.layerColor : color(0, 1)

	if(this.steps < this.stepsOut / 2) 
		this.radius = floor(random(1, this.steps)) * this.singleStep
	else if(this.steps > this.stepsOut / 2) 
		this.radius = floor(random(1, this.stepsOut - this.steps)) * this.singleStep
	else
		this.radius = floor(random(1, this.stepsOut / 2 + 1)) * this.singleStep
}

	render() {
		stroke(this.layerColor)
		fill(this.fillColor)
		strokeWeight(this.weight)
		push()
			//translate(width/2, height/2)
			for(let i = 0; i < this.numShapes; i++) {
				if(this.randomShape < .33)
					ellipse(0, this.center, this.radius, this.radius)
				else if(this.randomShape >= .33 && this.randomShape < .66)
					rect(0, this.center, this.radius, this.radius)
				else
					myTriangle(this.center, this.radius, this.direction)
				rotate(this.angle)
			}
		pop()
	}

}

class SteppedHexagons extends Layer {
	constructor() {
		super()
		this.numSteps = randomSelect2() ? this.stepsOut : this.stepsOut * 1.25
		this.centerOffset = (CRYSTAL_SIZE/2) * .15
		this.singleStep = ((CRYSTAL_SIZE/2) - this.centerOffset) / this.numSteps
	}

	render() {
		stroke(this.layerColor)
		noFill()
		strokeWeight(this.weight)
		push()
			//translate(width/2, height/2)
			rotate((this.angle*this.sides)/2)
			for(let i = 0; i < this.numSteps; i++) {
				hexagon(0, 0, this.centerOffset + (i * this.singleStep))
			}
		pop()
	}
}

class TestLines extends Layer {
	constructor() {
		super()
	}

	render() {
		push() //wrap fancy translate in a push/pop
			//translate(width/2, height/2)
			stroke(this.layerColor)
			ellipse(0, 0, CRYSTAL_SIZE, CRYSTAL_SIZE) //Outer limits of crystal
			const angle = 360 / this.numShapes
			for(let i = 0; i < this.numShapes; i++) {
				line(0,0,CRYSTAL_SIZE/2,0)
				rotate(this.angle)
			}
		pop()	
	}
}