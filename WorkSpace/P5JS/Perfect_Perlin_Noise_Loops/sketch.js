
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



	let rB = 50;
	let gB = 10;
	let bB = 200;
	let alphaB = 1;
	background(rB, gB, bB, alphaB)


	translate(width/2, height/2)
	// stroke(random(160, 200), random(0, 10), 60)
	let rS = 200;
	let gS = 150;
	let bS = 20;
	let alphaS = 2;
	stroke(rS, gS, bS, alphaS)
	strokeWeight(2)
	let r = 200;
	let g = 10;
	let b = 50;
	let alpha = 6;
	fill(r, g, b, alpha)
	beginShape();
	let noiseMax = 1
	//adjusting angle 'a' changes oscillation frequency
	for(let a = 0; a < TWO_PI; a+=radians(1)) {
		// let r = 100 //radius
		// let r = random(50,100) //radius
		//Increment angle while keeping radius constant
		let xoff = map(cos(a), -1, 1, 0, noiseMax)
		let yoff = map(sin(a), -1, 1, 0, noiseMax)
		let r = map(noise(xoff, yoff, zoff), 0, 1, width/8, noiseMax)
		let x = r * cos(a)
		let y = r * sin(a)
		let z = r * tan(a)
		//Play around with rendering different shapes below
		vertex(x,y)
		// vertex(y,z)
		alpha = 1
		//circle(x,y, r)
		// circle(x, y, x)

	}
	endShape(CLOSE)


	
	phase += 0.0007
	zoff += .006
	
}