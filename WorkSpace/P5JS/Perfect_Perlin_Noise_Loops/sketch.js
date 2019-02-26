
let slider
let phase = 0
let zoff = 0
let color = 0
let pointArray = []
let Palette = ['#73628A', '#313D5A']



function setup() {
	createCanvas(windowWidth, windowHeight)
	//slider(startVal, endVal, initVal, increment)
	slider = createSlider(0, 5, .1, 0.1)
	//noCursor()
	const button = createButton('render')
	button.mousePressed(() => console.log('render clicked'))


}

function draw() {
	// HSL color is also supported and can be specified
// by value




	background(200, 10)


	translate(width/2, height/2)
	// stroke(random(160, 200), random(0, 10), 60)
	stroke('pink')
	strokeWeight(2)
	fill(0, 70)
	beginShape();
	let noiseMax = 1
	//adjusting angle 'a' changes oscillation frequency
	for(let a = 0; a < TWO_PI; a+=radians(1)) {
		// let r = 100 //radius
		// let r = random(50,100) //radius
		//Increment angle while keeping radius constant
		let xoff = map(cos(a+phase), -1, 1, 0, -noiseMax)
		let yoff = map(sin(a+phase), -1, 1, 0, noiseMax)
		let r = map(noise(xoff, yoff, zoff), 0, 1, width/4, noiseMax)
		let x = r * cos(a)
		let y = r * sin(a)
		vertex(x,y)

	}
	endShape(CLOSE)


	
	phase += 0.01
	zoff += .006
	
}