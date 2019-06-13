//Rachel Buse Inflatable Project June 2019 w/Code by CODYRYANDESIGN.com

//This code is designed to automatically implement an inflation/deflation 
//mechanic, with X minutes of inflation followed by X minutes of deflation,
//before resetting to an OFF state and triggering a momentary delay.

//System Process:
//When a patron activates the system by stepping on a floor pad, the system 
//will turn on INFLATION fans.
//After the desired inflationTime the system will turn INFLATION fans off
//and DEFLATION fans on. 
//After the total inflationTime + deflationTime has passed, the system will
//turn ALL fans off, the system will RESET, and DELAY for systemDelayTime 
//before repeating the process.   

//Time-Management Vars
unsigned long padTriggerTimeStamp; //Global var for holding starting time of pad trigger
unsigned long currentTimeStamp; //Global var for holding current time stamp
const unsigned long inflationTime = 10 * 1000; //Operations will run for this amount of time (in millis) ~10 seconds
const unsigned long deflationTime = 10 * 1000; //Operations will run for this amount of time (in millis) ~10 seconds
const unsigned long systemDelayTime = 10 * 1000; //The system will halt for this amount of time after completion

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

}

void loop() {
  // put your main code here, to run repeatedly:
  padFlag = digitalRead(dPin8); //At the start of each loop, check the status of the floor pad
  if(padFlag && !systemRunningFlag) //If the pad is active and the system is currently off
  {
    Startup();
    Inflate();
  }
  if(systemRunningFlag) //If the system is currently running
  {
    currentTimeStamp = millis(); //Update current time at the beginning of every loop
    if(currentTimeStamp - padTriggerTimeStamp > inflationTime) //After desired INFLATION time has passed
      Deflate();
    if(currentTimeStamp - padTriggerTimeStamp >= inflationTime + deflationTime) //After ALL desired time has passed
      Shutdown();
    else if(!padFlag) //If the patron leaves the floor pad area early, initiate shutdown procedures
      Shutdown();
  }
}

//Handle turning on INFLATION pins and turning off DEFLATION pins
void Inflate()
{
  digitalWrite(dPin1, HIGH); //Turn the inflation pin(relay) ON
  digitalWrite(dPin2, LOW); //Turn the deflation pin(relay) OFF
}

//Handle turning on DEFLATION pins and turning off INFLATION pins
void Deflate()
{
  digitalWrite(dPin1, LOW); //Turn the inflation pin(relay) OFF
  digitalWrite(dPin2, HIGH); //Turn the deflation pin(relay) ON
}

//Handle system startup procedures
void Startup()
{
  padTriggerTimeStamp = millis(); //Record the starting time of the system
  systemRunningFlag = true; //Set the system flag to true since the system is now running  
}

//Handle system shutdown procedures
void Shutdown()
{
  digitalWrite(dPin1, LOW); //Turn the inflation pin(relay) OFF
  digitalWrite(dPin2, LOW); //Turn the deflation pin(relay) OFF
  delay(systemDelayTime); //Insert a delay before turning the system off to prevent an instant reset
  systemRunningFlag = false; //Set the system flag to false since the system is now off
}
