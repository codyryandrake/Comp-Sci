/*
                         preprocessor.c 

	Preprocessor directives (the lines that begin with "#") tell the 
	C "preprocessor" how to alter the source code before sending it 
	to the C compiler. Preprocessor directives are discussed in the 
	"C Pocket Reference" book (by Prinz and Kirsh-Prinz), beginning 
	on page 65. 

	#include means to include some other source code, typically from 
	a "header (.h) file". System header files are enclosed in angle 
	brackets, and other included files are enclosed in double quotes.

	#define introduces a preprocessor constant, and might assign a 
	text value to it. In this case, whenever the constant is later 
	encountered, its text value in substituted for it in the altered 
	source code (what goes to the compiler). 

	#define, #ifdef, #ifndef, #else and #endif are also used to process 
	the source code in some conditional way, before the compiler gets it.
	They decide which blocks of source code the compiler will and won't 
	see. Here a preprocessor constant (like DEBUG) is either defined or 
	it isn't (could comment out the defining line). No value is assigned 
	to the constant. Instead, it is simply defined or not defined, 
	and as such is essentially just used as a Boolean value. 
	#ifdef ... #endif can be used to signal that a block of source 
	code should be used if and only if a preprocessor constant is 
	defined. Likewise, #ifndef ... #endif means to include a block 
	of source code if and only if a preprocessor constant is not 
	defined. Can also combine a #else directive with either an 
	#ifdef or #ifndef directive, to select between one of two possible 
	blocks of source code to include in the altered source code. 
*/

#include <stdio.h>
#include <stdlib.h>

#define ARRAY_SIZE 10     // preprocessor constants (no "=" here)
#define RANGE      101
#define SEED_VALUE 13
#define DEBUG             // comment out when you don't want to debug
// #define BE_POLITE         // comment out when you want to be rude 

int main() 
{
	// a terrible random number generator
	int x = SEED_VALUE, y, i, j; 
	int array[ARRAY_SIZE];                  // an integer array
	for (i=0; i < ARRAY_SIZE; i++) {        // for loops as in Java 
		array[i] = x;
		x = x * x % RANGE;
#ifdef DEBUG
		printf("%d ", x);
#endif
	}
#ifdef DEBUG
	printf("\n");
#endif
	// Bubble Sort the array 
	for (i=0; i < ARRAY_SIZE - 1; i++) { 
		for (j=0; j<ARRAY_SIZE - 1 - i; j++) {
			x = array[j];
			y = array[j+1];
			if (x > y) {
				array[j] = y;
				array[j+1] = x; 
#ifdef DEBUG
				printf("Swapped %d and %d.\n", x, y);
#endif 
			}
		}
	}
#ifdef DEBUG 
	for(i=0; i < ARRAY_SIZE; i++) {
		printf("%d ", array[i]);
	}
	printf("\n");
#endif
#ifndef BE_POLITE
	printf("\nAll right - I'm leaving now!!!\n\n");
#else
	printf("\nThis has fun. Goodbye for now. Have a great day!\n\n");
#endif
	return 0;
}
