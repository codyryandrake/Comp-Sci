/*
                               minMaxPointers.c

      The ins and outs of pointers and pointer arithmetic

      (Pointers are discussed on pages 52-55 of "C Pocket Reference"
      by Prinz and Kirch-Prinz)

      (There will be an interesting warning here, but don't worry.)

	  C has several data types, many of which will be familiar
	  from Java, and these can easily be found in "C Pocket Reference."
	  I am only listing here some of the most common:

	  int            // a "plain old" signed int
	  unsigned int   // obvious from name (stores using unsigned binary)
	  short          // might be a shorter version if int (depending on system)
	  long           // might be a longer version if int (depending on system)
	  char           // character data (via ASCII)
	  float          // floating point data
	  double         // double precision floating point data
*/

#include <stdio.h>
#include <stdlib.h>

//#define DEBUG
#define ArrayLength 6



int main()
{
	float nums[] = {23.43, 50.97, 2.32, 98.534, -5.2423, 10.11};
	float sumNums;
	float *minP = nums, *maxP = nums, aveP;
	int *arrStart = nums, *arrEnd = nums+ArrayLength;

	for (; arrStart < arrEnd; arrStart++)
	{
		if (*arrStart < *minP)
		{
			minP = *arrStart;
		}
		if (*arrStart > *maxP)
		{
			maxP = *arrStart;
		}
		sumNums += *arrStart;
	}
	aveP = sumNums/ArrayLength;
	printf("The maximum number is: %f.\n", *maxP);
	printf("The minimum number is: %f.\n", *minP);
	printf("The average of all numbers is: %f", aveP);

	Printf("Program terminated. Goodbye.");



}