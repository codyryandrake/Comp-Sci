
let i = 0;
let w = 10;

let states = [];

function setup() {
	createCanvas(1600, 200);
	values = new Array(floor(width / w));
	for (let i = 0; i < values.length; i++) {
		values[i] = random(height);
		states[i] = -1;
	}
	//frameRate(5);
	quickSort(values, 0, values.length-1);
}

async function quickSort(arr, start, end) {
	if (start >= end) return;

	let index = await partition(arr, start, end);
	states[index] = -1;

	await Promise.all([
		quickSort(arr, start, index -1),
		quickSort(arr, index + 1, end)
		]);
}

async function partition(arr, start, end) {

	for (let i = start; i < end; i++) {
		states[i] = 1;
	}

	let pivotIndex = start;
	states[pivotIndex] = 0;
	let pivotValue = arr[end];
	for (let i = start; i < end; i++) {
		if(arr[i] < pivotValue) {
			await swap(arr, i, pivotIndex);
			states[pivotIndex] = -1;
			pivotIndex++;
			states[pivotIndex] = 0;
		}
	}
	await swap(arr, pivotIndex, end);

	for (let i = start; i < end; i++) {
		if(i != pivotIndex)
			states[i] = -1;
	}

	return pivotIndex;
}

async function swap(arr, a, b) {
	await sleep(30);

	let temp = arr[a];
	arr[a] = arr[b];
	arr[b] = temp;
}

function sleep(ms) {
	return new Promise(resolve => setTimeout(resolve, ms));
}

function draw() {
	background(200, 50);

	for (let i = 0; i < values.length; i++) {
		noStroke();
		fill(255);
		if(states[i] == 0) {
			fill(255, 0, 0, 40);
		} else if (states[i] == 1) {
			fill(0, 0, 255, 40);
		} else {
			fill(0, 255, 0, 40);
		}
		rect(i * w, height - values[i], w, values[i]);
	}
}