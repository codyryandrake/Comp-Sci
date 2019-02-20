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
		push()
			translate(this.x, this.y)
			//scale(millis()*.000025)
			console.log(this.layers)
			this.layers.forEach(layer => {
			layer.render()
			})
		pop()
		this.layers = []
	}
}

