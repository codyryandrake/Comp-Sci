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



int main()
{
	float nums[] = {23.43, 50.97, 2.32, 98.534, -5.2423, 10.11};
	float sumNums;
	float *minP = nums, *maxP = nums, aveP;

	for (int i = 0; i<6; i++)
	{
		if (nums[i] < *minP)
		{
			minP = &nums[i];
		}
		if (nums[i] > *maxP)
		{
			maxP = &nums[i];
		}
		sumNums += nums[i];
	}
	aveP = sumNums/6.0;
	printf("The maximum number is: %f.", *maxP);
	printf("The minimum number is: %f.", *minP);
	printf("The average of all numbers is: %f", aveP);



}
