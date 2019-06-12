//Cody Ryan + Rachel Buse Inflatable Project June 2019
//This code is designed to automatically implement a inflation/deflation 
//mechanic, with 5 minutes of inflation followed by 5 minutes of deflation,
//before resetting to an OFF state.
//When a patron activates the process by stepping on a floor pad, the system state
//will turn ON and process will begin.  

//Time-Management Vars
unsigned long padTriggerTimeStamp; //Global var for holding starting time of pad trigger
unsigned long currentTimeStamp; //Global var for holding current time stamp
const unsigned long desiredRunTime = 10 * 1000; //Operations will run for this amount of time (in millis) ~10 seconds

//Pin Control Vars
const byte led1 = 2; //Built-in debug LED
const byte dPin1 = 16; //D0
const byte dPin2 = 5; //D1
const byte dPin3 = 4; //D2
const byte dPin4 = 0; //D3
const byte dPin5 = 14; //D5
const byte dPin6 = 12; //D6 
const byte dPin7 = 13; //D7
const byte dPin8 = 15; //D8

//Bool Control Flags
bool padFlag = false; //Identifies if the floor pad is being stood on or not
bool systemRunningFlag = false; //Identifies if the system is currently running


void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200); //Begin serial communication at speified Baud rate

  //Initialize All Pin Control Vars
  pinMode(led1, OUTPUT);
  pinMode(dPin1, OUTPUT);
  pinMode(dPin2, OUTPUT);
  pinMode(dPin3, OUTPUT);
  pinMode(dPin4, OUTPUT);
  pinMode(dPin5, OUTPUT);
  pinMode(dPin6, OUTPUT);
  pinMode(dPin7, OUTPUT);
  pinMode(dPin8, INPUT); //The final pin is used for floor pad input

//  padTriggerTimeStamp = millis(); //Start keeping track of elapsed time
}

void loop() {
  // put your main code here, to run repeatedly:
  padFlag = digitalRead(dPin8); //At the start of each loop, check the status of the floor pad
  if(padFlag && !systemRunningFlag) //If the pad is active and the system is currently off
  {
    digitalWrite(dPin1, HIGH); //Turn the inflation pin(relay) ON
    digitalWrite(dPin2, LOW); //Turn the deflation pin(relay) OFF
    padTriggerTimeStamp = millis(); //Record the starting time of the system
    systemRunningFlag = true; //Set the system flag to true since the system is now running
  }
  else if(systemRunningFlag) //If the system is currently running
  {
    currentTimeStamp = millis(); //Keep track of elapsed time since the system started
    
    if(
      currentTimeStamp - padTriggerTimeStamp >= desiredRunTime/2 &&
      currentTimeStamp - padTriggerTimeStamp < desiredRunTime) //After half of the desired time has passed
    {
      digitalWrite(dPin1, LOW); //Turn the inflation pin(relay) OFF
      digitalWrite(dPin2, HIGH); //Turn the deflation pin(relay) ON
    }
    if(currentTimeStamp - padTriggerTimeStamp >= desiredRunTime) //If all desired time has passed
    {
      digitalWrite(dPin1, LOW); //Turn the inflation pin(relay) OFF
      digitalWrite(dPin2, LOW); //Turn the deflation pin(relay) OFF
      systemRunningFlag = false; //Set the system flag to false since the system is now off
    }
  }

}
