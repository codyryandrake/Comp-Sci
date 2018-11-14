/*
                               pointers.c 

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

int main()
{
	printf("\nLet's have a little fun with pointers.\n");
	int x = 3, y = 7, z = 2;   // three int variables x, y, z
	int *p, *q, *r;            // three "pointer to int" variables p, q, r
	p = &x;           // set p to point to x (p will hold the address of x)
	q = &y;           // set q to point to y
	r = &z;           // set r to point to z
	*r = *p + *q;     // add numbers pointed to by p and q, and store 
	                  // in memory location that r is pointing to
	printf("%d %d\n", *r, z);   // print number r is pointing to, 
	                            // and also print z (same thing!)
	*p = 4;           // basically, change the value of x to -4 
	p = q;            // basically, make p point to y (like q does)
	*p = 8;           // basically, change the value of y to 8
	printf("%d %d %d\n", x, y, z);

	printf("\nHere is an example of an array of integers.\n");
	int primes[] = {2, 3, 5, 7, 11, 13};   // integer array of length 6
	for (int i=0; i < 6; i++) 
	   printf("%d ", primes[i]);           // display the array 
	printf("\n");

	// Technically though, primes is really a constant pointer that
	// points to the first entry in the array, as seen next. If we add
	// an integer to this pointer, then we'll be pointing somewhere else.
	// In fact, primes[i] means the same thing as *(primes+i), that is, 
	// take the pointer primes, add the integer i to it to get another 
	// pointer, and then dereference that to get the thing it points to! 
	printf("\nNotice how square brackets are equivalent to other stuff.");
	printf("%d %d\n", primes[0], *primes); 
	printf("%d %d\n", primes[1], *(primes+1)); 
	printf("%d %d\n", primes[2], *(primes+2)); 
	printf("%d %d\n", primes[3], *(primes+3)); 
	printf("%d %d\n", primes[4], *(primes+4)); 
	printf("%d %d\n", primes[5], *(primes+5));
	printf("I wonder what garbage is in the -123 position of primes!\n");
	printf("%d %d\n", primes[-123], *(primes-123));   // causes a warning

    // After setting p and q, so that p points to the start of the array, 
    // and q points just after the array, we can subtract these pointers
    // to get an integer. Guess what integer it will be. 
    p = primes; 
    q = primes + 6; 
    printf("\nq-p equals %d\n", (int)(q-p)); 
    // Although q - p equals 6, this doesn't mean that the main memory 
    // address stored in q, minus the main memory address stored in 
    // p equals 6. If fact, these two main memory addresses differ by
    // 6 times the number of bytes required to store an integer. 
    // Here is how to let C tell you what this equals:
    printf("Integers require %d bytes each.\n", (int) sizeof(int));
    printf("The difference between the two main memory addresses is %d.\n", 
    	(int) ((q-p)*sizeof(int)));

    // Now let a pointer p traverse the array
    // - Notice that pointers are being compared using < 
    // - Notice that pointer is being incremented using ++
    // - Remember that *p dereferences p, and means whatever p points to 
    printf("\nHere is the array again (reading via a pointer): ");
	for ( ; p < q ; p++ ) 
	   printf("%d ", *p);   // display array again, using pointer p
	printf("\n");
}
