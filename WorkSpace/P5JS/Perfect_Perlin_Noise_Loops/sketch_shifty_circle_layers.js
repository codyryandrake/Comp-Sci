
let slider
let phase = 0
let zoff = 0
let color = 0
var mic
let pointArray = []
let Palette = ['#73628A', '#313D5A']

function setup() {
	createCanvas(windowWidth, windowHeight)
	//slider(startVal, endVal, initVal, increment)
	slider = createSlider(0, 10, 5, 0.1)
	noCursor()
	
}

function draw() {
	// HSL color is also supported and can be specified
// by value




	background(10,10)
	translate(width/2, height/2)

	stroke(random(160, 200), random(0, 10), 60)

	
	strokeWeight(2)
	noFill()
	let t = 0
		
	let noiseMax = slider.value()
	beginShape();
	//adjusting angle 'a' changes oscillation frequency
	for(let a = 0; a < TWO_PI; a+= 0.03) {
		// let r = 100 //radius
		// let r = random(50,100) //radius
		//Increment angle while keeping radius constant

		let xoff = map(cos(a+phase), -1, 1, 0, noiseMax)
		let yoff = map(sin(a+phase), -1, 1, 0, noiseMax)
		let r = map(noise(xoff, yoff, zoff), 0, 1, windowWidth/8, windowHeight/1.5)
		let x = (r * cos(a)/1)*sin(a*1000)
		let y = r * sin(a)/2
		vertex(x,y)

	}
	endShape()


	
	
	zoff += .01
	phase += 0.00001
}