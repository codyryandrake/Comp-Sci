/*
                               bit_twiddling.c 

	The goal here is to get a practical sense of what can be accomplished 
	using bitwise operations, whether it is done in C code on assembly code.
	This tends to be useful when interfacing with hardware devices, such as 
	a disk drive. The so-called "driver" code that controls such a device 
	tends to involve a good deal of interaction at the bit level, reading 
	"status bits" that are the electronics way of letting the program know 
	whether or not a certain condition is currently true. It might also 
	be possible for the program to change a bit, and thereby signal to the 
	hardware that something should be turned on or off. 

	Recall the "bit positions" in a word:

	         +-----+-----+--          --+-----+-----+-----+-----+
	         | bit | bit |              | bit | bit | bit | bit |
	         |  31 |  30 |      ...     |  3  |  2  |  1  |  0  |
	         +-----+-----+--          --+-----+-----+-----+-----+

	The following is typical of what takes place, although it is admittedly 
	for an extraordinary "device". The device here is a nuclear power plant.
	Let's imagine that there is a word (int variable) called status, whose 
	bits have the following puposes:

	       bit 0   -   radiation level too high?      (0=no, 1=yes)
	       bit 1   -   turn off/on motor to lower contol rods
	                   (on to decrease radiation)     (0=off, 1=on)
	       bit 2   -   radiation level too low?       (0=no, 1=yes)
	       bit 3   -   turn off/on motor to raise contol rods
	                   (on to increase radiation)     (0=off, 1=on)
	       bit 4   -   cooling water level too high?  (0=no, 1=yes)
	       bit 5   -   cooling water level too low?   (0=no, 1=yes)
	       bit 6   -   pumping water in or out?       (0=in, 1=out)
	                   (can test this bit to see which way the water is flowing;
	                    can also change it to reverse the direction; assume the 
	                    water is always being pumped in or out, which is weird)
*/

#include <stdio.h>
#include <stdlib.h>

// Bits for checking the sensors: 
#define RAD_TOO_HIGH             0x01     // in binary: 00000001
#define RAD_TOO_LOW              0x04     // in binary: 00000100
#define WATER_TOO_HIGH           0x10     // in binary: 00010000
#define WATER_TOO_LOW            0x20     // in binary: 00100000

// Bits to control the control rod motors: 
#define LOWER_CONTROL_RODS       0x02     // in binary: 00000010
#define RAISE_CONTROL_RODS       0x08     // in binary: 00001000

// Bit for testing which way the cooling water is being pumped,
// and also to cause the pump to change this direction:
#define WHICH_WAY_WATER_FLOW     0x40     // in binary: 01000000


int main()
{
  int status = 0, x; 

// Setting up an infinite loop to continuously monitor sensors and react accordingly.
// Can use control-C (hold down "control" key and press "C" key) to terminate.

  while(1) {

// Will have the user specify the values of four sensors, each time through the loop. 
// This section of code will simulate the behavior of hardware devices, in particular, 
// how the reactor's various sensors would automatically (via electronic circuitry) 
// change some status bits (0,2,4,5), setting these according to the sensor readings.
// Also, the same circuitry would automatically read the values of certain bits (1,3,6)
// and turn control rods motors on or off, and set the direction of the water pump
// according to these bits, but this functionality is not in evidence here. 

// This section of code simulates electronics directly connected to the sensors and 
// motors/pump. It is NOT intended to be part of the software system that also reads 
// and writes status bits, and makes decisions (see below). It is merely simulation
// of hardware behavior. 

	printf("Radiation too high (0=no, 1=yes)? ");
	scanf("%d", &x);
	if (x) 
		status = status | RAD_TOO_HIGH;    // only affects bit 0, setting it to 1  
	else 
		status = status & ~RAD_TOO_HIGH;   // only affects bit 0, setting it to 0
	printf("Radiation too low (0=no, 1=yes)? ");
	scanf("%d", &x);
	if (x) 
		status = status | RAD_TOO_LOW;     // only affects bit 2, setting it to 1  
	else 
		status = status & ~RAD_TOO_LOW;    // only affects bit 2, setting it to 0
	printf("Water too high (0=no, 1=yes)? ");
	scanf("%d", &x);
	if (x) 
		status = status | WATER_TOO_HIGH;  // only affects bit 4, setting it to 1  
	else 
		status = status & ~WATER_TOO_HIGH; // only affects bit 4, setting it to 0
	printf("Water too low (0=no, 1=yes)? ");
	scanf("%d", &x);
	if (x) 
		status = status | WATER_TOO_LOW;   // only affects bit 5, setting it to 1  
	else 
		status = status & ~WATER_TOO_LOW;  // only affects bit 5, setting it to 0


// Display the status now, before testing and reacting
	printf("Status before testing and reacting: %x (hex) \n", status); 	


// This section of code IS the software that interacts with the hardware (sensors 
// and motors/pump) via the status bits. It routinely checks the status bits that 
// are automatically set by the sensors, makes decisions based on this info, and  
// sets the status bits that cause the hardware to turn control rod motors on or 
// off, and that decide which direction the water pump will cause water to flow. 

// If radiation is too high, turn on the motor that lowers the control rods, and 
// turn off the motor that raises the control rods. 
	if (status & RAD_TOO_HIGH) {
		status = status | LOWER_CONTROL_RODS; 
		status = status & ~RAISE_CONTROL_RODS; 
	} else
// Otherwise, if radiation is too low, turn on the motor that raises the control rods,
// and turn off the motor that lowers the control rods. 
	if (status & RAD_TOO_LOW) {
		status = status | RAISE_CONTROL_RODS; 
		status = status & ~LOWER_CONTROL_RODS; 
	}; 
// If water level is too low and water is being pumped out, or if water lever is 
// too high and water is being pumped in, then reverse the pump. 
	if (((status & WATER_TOO_LOW)  &&  (status & WHICH_WAY_WATER_FLOW)) || 
		((status & WATER_TOO_HIGH) && !(status & WHICH_WAY_WATER_FLOW))) {
		status = status ^ WHICH_WAY_WATER_FLOW;   // XOR used to toggle bit
	}
// Display status now, after testing and reacting
	printf("Status after testing and reacting: %x (hex) \n", status); 	
  }
}
