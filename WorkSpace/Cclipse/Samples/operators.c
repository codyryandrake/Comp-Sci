/*
                               operators.c 

        Most of the operators discussed here will be familiar to you 
        from Java, and to a lesser extent, Python.

        C, or at least the original C, does not have a Boolean data type. 
        It's comparison operators (==, !=, <, >, <=, >=) and its Boolean 
        operators (&&, ||, !) must instead return integer values. As you 
        might guess, 0 is returned for "false", and 1 is returned for "true".
        When using a Boolean operator, a 0 operand is treated as "false", 
        and anything else is treated as "true".

        The "bitwise" operators & , | , ^ , ~ , >> and << will probably be new. 
        Table 8 on page 19 of "C Pocket Reference" (by Prinz and Kirsh-Prinz)
        give the precedences (order of application) for the operators in C. 
        Bitwise operators are discussed on pages 24-25. There operators are 
        also discussed in Section 2.6 of Patterson & Hennessy. These operands 
        have corresponding machine instructions in most CPUs, including MIPS 
        (the instructions are on your purple sheet, and you can probably guess).
        These instruction work with data at the bit level. Here is what they do: 

               &    -  bitwise and - corresponding bits are "and-ed" together 
               |    -  bitwise or - corresponding bits are "or-ed" together 
               ^    -  bitwise exclusive or - corresponding bits are "exclusive 
                       or-ed" together 
               ~    -  bitwise not - "flip" (change) all the bits
               <<   -  shift left - shift bits left by some number of positions
                       (can provide a fast way to multiply by a power of two)
               >>   -  shift right - shift bits right by some number of positions
                       (can provide a fast way to divide by a power of two)

        Note that hexadecimal notation can be used in the source code to represent 
        integers, but to do so, preface the hex notation with "0x". Recall the 
        "bit positions" in a word:

                   +-----+-----+--          --+-----+-----+-----+-----+
                   | bit | bit |              | bit | bit | bit | bit |
                   |  31 |  30 |      ...     |  3  |  2  |  1  |  0  |
                   +-----+-----+--          --+-----+-----+-----+-----+

*/

#include <stdio.h>
#include <stdlib.h>

int main()
{
	if ((2 * 3 < 4 || 1 + 2 * 3 != 9) && !(5 <= 5))
		printf("Sounds good.\n");
	else 
		printf("Sounds bad.\n");
	printf("%d\n", 5 << 2);       // Use left shift to multiply 5 by 4
	printf("%d\n", 20 >> 2);      // Use right shift to divide 20 by 4
	printf("%d\n", 0x3c);         // Hex 3c equals decimal 60 
	printf("%x\n", 60);           // Decimal 60 equals hex 3c
	printf("%x\n", 0x3c & 0x27);  // Bitwise-and 00111100 with 00100111
	                              // to get 00100100 (in binary)
	printf("%x\n", 0x3c | 0x27);  // Bitwise-or  00111100 with 00100111
	                              // to get 00111111 (in binary)
	printf("%x\n", 0x3c ^ 0x27);  // Bitwise-xor 00111100 with 00100111
	                              // to get 00011011 (in binary)
	printf("%x\n", ~0x3c);        // Bitwise-not 0...000111100 
	                              // to get 1...111000011 (in binary)
	                              // (a whole word, i.e. 32 bits)
	printf("%x\n", 0x3c << 3);    // Shift  0...0000111100 left three spots
	                              // to get 0...0111100000 (in binary)
	printf("%x\n", 0x3c >> 3);    // Shift  0...0000111100 right three spots
	                              // to get 0...0000000111 (in binary)
	return 0; 
}
