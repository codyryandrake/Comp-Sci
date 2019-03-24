// This is a fork of Sparks, 
//  - modified to slow down the sparks
//  - gave them a longer lifetime 
//  - allowed them to loop from one ned to the other

sparkHue = 0; //Variable for gradually shifting through hue value
sparkSaturation = 1; //Variable for setting saturation
numSparks = 1 + (pixelCount); //How many sparks we will generate
decay = 0.99; //The decay rate to be applied to each spark
maxSpeed = 0.07 //The maximum travel speed of a spark
threshold = .010000;

export var light;
export var lightAverage;
export var lightMap;
export var lightPercent;
export var lightMin;
export var lightMax;
export var lightAverageDelta;
export var adjustmentTrigger
export var sparkHue;
export var maxBtn
export var calibratedMax
export var newThreshold




sparks = array(numSparks);
sparkX = array(numSparks);
pixels = array(pixelCount);

export function beforeRender(delta) {

  delta = .01; //Limit our FPS to slow things down a bit
  lightEngine(delta, numLoops);
  sparkHue += (random(.002) *triangle(wave(.32)))%1.0 //Slowly rotate through the color spectrum
  
  for (i = 0; i < pixelCount; i++)
    pixels[i] = pixels[i]*.99;
  
  
  for (i = 0; i < numSparks; i++) { //Increment through the sparks
    if (sparks[i] >= -newThreshold && sparks[i] <= newThreshold) { 
      sparks[i] = (maxSpeed/2) - random(maxSpeed); //Update maxSpeed randomly
      sparkX[i] = random(pixelCount); //Assign SparkX randomly to a pixel
    }
    
    sparks[i] *= decay; //Update the current sparks value to be 99% of itself
    sparkX[i] += sparks[i] *delta; //Boost the SparkX sparks' value by the product of the current spark and delta 
    
    if (sparkX[i] >= pixelCount) { //If the SparkX sparks' value ever exceeds 277
      sparkX[i] = 0; //Reset it to 0
    }
    
    if (sparkX[i] < 0) { //If the SparkX sparks' value ever drops below 0
      sparkX[i] = pixelCount - 1; //Update the spark's value to 276
    }
    
    pixels[floor(sparkX[i])] += sparks[i]
  }
  
numLoops++
}

export function render(index) {
  v = pixels[index];
  hsv(sparkHue, sparkSaturation, v*v*v*v)
  
}

// //Map light readings between 0.0 and 1.0
// lightPercent = clamp(InputMap(lightAverage, lightMin, lightMax, 0.0, 1.0), 0.0, 1.0) 

function InputMap(input, in_min, in_max, out_min, out_max) 
{
return (input - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
}

function lightEngine(delta, numLoops) {
lightPercent = .1;
lightPercentPrev = 0.0;
lightSum = 0.0;
lightAverage = 0.0;

numLoops = 0;
timer = 0;
calibratedMax = false;
calibratedMin = false;
lightMax = 0.0;
lightMin = 0.0;
maxBtn = touchRead(0)
minBtn = 0
  
  lightSum = lightSum + light; //Store a sum of the last 800ms of light readings.
  timer += delta //Keep track of how many times we loop
  
  if(timer > 800) //so that every 400ms we:
  { 
    if(calibratedMax == 1.0) { //only record the previous light reading after the initial reading
      lightAveragePrev = lightAverage //Record the previous average
      lightAverageDelta = lightAverage - lightAveragePrev //Determine the difference between curr and prev readings
      lightPercentPrev = lightPercent
    }
    lightAverage = lightSum/numLoops //Average the lightSum across the total readings taken
      if(calibratedMax == 0.0 || maxBtn){ //On boot up or manual maxBtn push:
        lightMax = lightAverage; //set maximum threshold to current light level
        calibratedMax = true; //and set our Max calibration flag to True
      }
      if(!calibratedMin){ //On boot up:
        lightMin = clamp(lightMax * .01, 0.0001, 1.0); //set minimum light reading to 10% of maximum 
        calibratedMin = true; //and set our Min calibration flag to True
      }
      if(minBtn) { //If button is pressed by user:
        lightMin = lightAverage; //set the minimum threshold to current light level (lowest light levels)
      }
    
    lightSum = 0 //At the end of our condition, reset variables
    timer = 0
    numLoops = 0
  }
  //Reverse output values for light to create an inverse map
  lightMap = InputMap(lightAverage, lightMin, lightMax, 0.00, 1.00); //Comment out this line to set a fixed delta
  lightPercent = 1.0 - lightMap;
  // newThreshhold = InputMap(lightPercent, 1.0, 0.0, lightMax, -lightMax); //Allow the threshhold to range between 1.0 and .1 depending on ambient light levels
  newThreshold = InputMap(lightPercent, 1.0, 0.0, threshold, -threshold);
}