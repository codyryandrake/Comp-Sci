/*
                               functions.c 

	A function is basically the same as a function in Python, and also 
	a public static method in Java. The syntax for the function header
	is pretty much the same as the header for a public static method in 
	Java, with "public static" removed. 

	UNLIKE Python and Java, to help make the compiler faster, it is 
	required that something about a particular function be known to the
	compiler BEFORE that function is called. If the function is part 
	of a "library" ("package") or some other external code, then an 
	#include directive should be used to include the a suitable header, 
	before using the function. If on the other hand, the function is 
	defined in the current source code, then either this definition 
	should occur before any call to the function, or at least, a 
	"(prototype) declaration" of the function should occur before any 
	call to the function. Such a declaration is just the function 
	header followed by a semicolor, and optionally, parameter names 
	can be skipped but not their data types. 

	Functions are discussed on pages 57-63 of "C Pocket Reference"
	by Prinz and Kirch-Prinz. 
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>                  // library of math functions 

double f(double x, double y) {     // defining function f
	return 3*x*y + 2*x - 5*y + 11; 
}

double g(double x, double y);      // declaring function g (prototype)

double h(double, double);          // declaring function h (prototype)

int main()
{
	printf("%f\n", cos(3.14));     // cosine of 3.14 (need math.h)
	printf("%f\n", pow(1.7, 3));   // raise 1.7 to power 3 (need math.h)
	printf("%f\n", f(1,10));       // okay - f is already defined 
	printf("%f\n", g(4,5));        // okay - g is already declared 
	printf("%f\n", h(7,2));        // okay - g is already declared 
	return 0;
}

double g(double x, double y) {
	return 2*x - 3*y + 5; 
}

double h(double x, double y) {
	return x*x + y*y - 1; 
}
