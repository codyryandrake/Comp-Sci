class Crystal {
	constructor(posX, posY) {
		this.x = posX
		this.y = posY
		this.layers = []

	layerConstructors.forEach(lCon => {
		let picker = random(1)
		if(picker > lCon.weight)
			this.layers.push(lCon.init())
	})
	}

	render() {
		fill(0, 127)
		rect(width/2, height/2, 500, 500) //Trailing fade effect
		push()
			translate(this.x, this.y)
			console.log(this.layers)
			this.layers.forEach(layer => {

			scaleFactor = map(noise(x, y), -1, 1, 1, -1)
			scale(scaleFactor)
			rotate(count)
			layer.render()
			})
		pop()

	}


	// clean() {
	// 	this.layers.forEach(layer => {
	// 		layer = []
	// 	})
	// }
}

