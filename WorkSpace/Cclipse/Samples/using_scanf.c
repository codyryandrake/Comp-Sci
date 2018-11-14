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

int main()
{
	char name[100];      // array of 100 characters to capture keyboard input 
	                     // (name is really a pointer pointing to the start)
	int age;
	float fave_num;
	char fave_char; 

	printf("What is your name? ");
	scanf("%s", name);
	printf("Hello %s, happy to meet you!\n", name);
	printf("How old are you? ");
	scanf("%d", &age);    // Don't forget the & here!
	printf("You know, %d is a wonderful age!\n", age);
	printf("What is your favorite floating point number? ");
	scanf("%f", &fave_num);
	printf("Wow! %f is my favorite floating point number too!\n", fave_num); 
	printf("What is your favorite keyboard character? ");
	scanf(" %c", &fave_char);
	printf("Well, %c is my favorite keyboard character!\n", fave_char + 1); 

	// %s, %d, %f, %c have the same meanings in scanf that they do in printf.
	// That leading space in " %c" takes care of a dumb technical issue, usually.
	// Notice: No & before name because name is a pointer (we'll discuss by and by).

	return 0;
}
