
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


int main() {
	int multiplicand, multiplier, product = 0;
	while (1) {
		printf("\n");
		printf("Please enter the multiplicand(8-bit)..."); //Get first unisigned int
		scanf("%d", &multiplicand); //8-bit
		printf("Please enter the multiplier(4-bit)..."); //Get second unsigned int
		scanf("%d", &multiplier); //4-bit
		int num1 = multiplicand;
		int num2 = multiplier;
		for (int i = 0; i < 4; i++) { //Simulates limiting the register to 4-bit integers
			if(multiplier & 1) {
				product += multiplicand;
				multiplicand = multiplicand << 1;
				multiplier = multiplier >> 1;
#ifdef DEBUG
				printf("Multiplicand: %d, Multiplier: %d, Product: %d\n",
						multiplicand, multiplier, product);
#endif
			}
			else {
				multiplicand = multiplicand << 1;
				multiplier = multiplier >> 1;
#ifdef DEBUG
				printf("Multiplicand: %d, Multiplier: %d, Product: %d\n",
						multiplicand, multiplier, product);
#endif
			}
		}
		printf("%d times %d equals %d\n", num1, num2, product);
		product = 0;



	}

}

/*
 * Problem 2. Recall the integer multiplication algorithm you used in HW #6, Problem 3.
 * Write a C program to simulate this algorithm. You must use C bit shifting operators to do the bit shifting.
 * When needed, you should also test bits using the C bitwise and operator. If you want to set a particular bit to be 1, then use bitwise or-ing.
 * When you worked with the algorithm previously, you assumed 4-bit and 8-bit registers.
 * Do that here too, but use unsigned integer variables (which are actually 32 bits) to play the roles of the registers, using only the
 *  least significant bits of these (4 or 8 bits, depending on the register being simulated).
 * Set up a loop for repeated testing, and each time through the loop, let the user specify the values of the multiplicand and the multiplier.

Add a preprocessor constant DEBUG similar to what we've done before.
When DEBUG is defined, your program should display lots of appropriate diagnostic messages.
When DEBUG is not defined, your program should just display prompt messages and a message that looks like this, but with the appropriate numbers instead:

7 times 8 equals 56

Test your software thoroughly. Show me one non-trivial sample running of the program and your program listing.
 */
