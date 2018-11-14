
/*
 * CODY RYAN
                                 using_scanf.c

       To compile:       gcc using_scanf.c -o using_scanf
          or             gcc using_scanf.c -o using_scanf.exe    (Cygwin users, maybe)
          or             cl using_scan.c                         (Developer Command Prompt)
       To execute:       ./using_scan
          or             ./using_scan.exe                        (Cygwin users, maybe)
          or             using_scan                              (Developer Command Prompt)
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DEBUG //Comment out to disable DEBUG output
#define systemBit 0X01
#define sysLisBit 0x02
#define heat 	  0x04
#define AC 		  0x08
#define panicBit1 0x10
#define panicBit2 0x20
#define panicBit3 0x40




int main() {

	int status = 1, temperature = 40, natTempChange = 1, count = 0;

	if (status & systemBit)
		status = status | sysLisBit; //Set the "listen" bit to the "system" bit
	else
		status = status & ~sysLisBit; //Otherwise set it to the opposite

	while ((status & sysLisBit) && (count < 100)) { //While the system is on
													//and we haven't looped 100 times
		printf("\nThe temperature is %d.\n", temperature);

		if ((status & sysLisBit) && (temperature > 85)) {
			status = status | AC;
#ifdef DEBUG
			printf("Heat: off.\n");
#endif
			status = status & ~heat;
#ifdef DEBUG
			printf("AC: on.\n");
#endif
		}
		if ((status & sysLisBit) && (temperature < 65)) {
			status = status | heat;
#ifdef DEBUG
			printf("Heat: on.\n");
#endif
			status = status & ~AC;
#ifdef DEBUG
			printf("AC: off.\n");
#endif
		}
		if ((status & heat) && (status & AC) ) {
			status = status & ~heat;
#ifdef DEBUG
			printf("Heat: off.\n");
#endif
			status = status & ~AC;
#ifdef DEBUG
			printf("AC: off.\n");
#endif
		}
		temperature = temperature + natTempChange;
#ifdef DEBUG
			printf("The temperature of the room naturally rises by %d.\n", natTempChange);
#endif
		if (status & heat) { //If furnace is on
			temperature = temperature + 2;
#ifdef DEBUG
			printf("Heat increased temperature by 2.\n");
#endif
		}
		if (status & AC) { //If AC is on
			temperature = temperature - 1;
#ifdef DEBUG
			printf("AC decreases temperature by -1.\n");
#endif
		}
		if((status & panicBit1) && (status & panicBit2) && (status & panicBit3)){
			status = status ^ systemBit;
			status = status & ~panicBit1;
			status = status & ~panicBit2;
			status = status & ~panicBit3;
		}


		count = count + 1; //Increment our counter
	}
}
