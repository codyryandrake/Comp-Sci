/*
                                 long_ints.c 

       To compile:       gcc long_ints.c -o long_ints
          or             gcc long_ints.c -o long_ints.exe        (Cygwin users, maybe)
          or             cl long_ints.c                          (Developer Command Prompt)
       To execute:       ./long_ints
          or             ./long_ints.exe                         (Cygwin users, maybe)
          or             long_ints                               (Developer Command Prompt)
*/

#include <stdio.h>
#include <stdlib.h>

int main()
{
	unsigned long longint, power_of_two, low, high; 
	unsigned int i;
	power_of_two = 2;  
	for (i=1; i<=5; i++) power_of_two *= power_of_two; 
	printf("Two to the 32nd power equals %lu.\n", power_of_two); 
	printf("Enter a long integer: "); 
	scanf("%lu", &longint);
	high = longint / power_of_two; 
	low  = longint % power_of_two; 
	printf("Low half is %lu and high half is %lu.\n", low, high); 
	longint = low + high * power_of_two; 
	printf("Confirmation: %lu.\n", longint); 
	printf("Enter a new low half: "); 
	scanf("%lu", &low); 
	printf("Enter a new high half: "); 
	scanf("%lu", &high); 
	longint = low + high * power_of_two; 
	printf("Long integer is %lu.\n", longint); 
	printf("Confirmation: %lu, %lu.\n", low, high); 
	return 0;
}
