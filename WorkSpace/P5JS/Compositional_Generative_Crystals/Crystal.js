class Crystal {
	constructor(posX, posY) {
		this.x = posX
		this.y = posY
		this.layers = []

	layerConstructors.forEach(lcon => {
		let picker = random(1)
		if(picker > lcon.weight)
			this.layers.push(lcon.init())
		})
	}

	render() {
		push()
			translate(this.x, this.y)
			//scale(millis()*.000025)
			//console.log(this.layers)
			this.layers.forEach(layer => {
			layer.render()
			})
		pop()
		//this.layers = []
	}
}

