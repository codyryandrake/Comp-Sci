/*
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
#define DEBUG


int main() {

	float floats;
	float min;
	float max;
	float average;
	float sum;
	int loopCount;
	int i;
	printf("How many numbers would you like to calculate?\n");
	scanf("%d", &loopCount);
	printf("The program will now ask for %d numbers, one at a time...\n", loopCount);

	for (i = 0; i < loopCount; i++) {
		printf("Please enter a number...\n");
		scanf("%f", &floats);
		if (floats > max) max = floats;
		if (floats < min) min = floats;
		sum += floats;

#ifdef DEBUG
		printf("\nAfter iteration %d, sum = %f, min = %f, max = %f\n", loopCount, sum, min, max);
#endif

	}
	average = (sum/loopCount);





	return 0;

}



