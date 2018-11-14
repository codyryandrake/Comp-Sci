/*
                                 using_printf.c 

       To compile:       gcc using_printf.c -o using_printf
          or             gcc using_printf.c -o using_printf.exe   (Cygwin users, maybe)
          or             cl using_printf.c                        (Developer Command Prompt)
       To execute:       ./using_printf
          or             ./using_printf.exe                       (Cygwin users, maybe)
          or             using_printf                             (Developer Command Prompt)
*/

#include <stdio.h>
#include <stdlib.h>

int main()
{
	int n = 77;          // integer data

  float x = 17.29;     // floating point data

	char c = '?';        // character data via ASCII code (must use single quotes).
	                     // Each ASCII character is stored as a single byte.

	char s[] = "thing";  // C-style string, i.e. a null-terminated character
	                     // array (must use double quotes). "thing" is really 
	                     // an array of six characters: 't','h','i','n','g','\0'
	                     // where '\0' means the null character (ASCII code zero).
	                     // In reality s is a pointer pointing to the the start 
	                     // of this array (the letter 't'). We speak of the 
	                     // "string" s, but it really is a pointer to a character.

    printf("%d\n", n);   // print value of n using decimal integer format 
    printf("%x\n", n);   // print value of n using hexadecimal integer format 
    printf("%f\n", x);   // print value of x using decimal floating pt format
    printf("%c\n", c);   // print value of c as an ASCII character 
    printf("%s\n", s);   // print string pointed to by s, as ASCII characters

    // So % and \ are both metacharacters inside double quotes, at least when 
    // interpreted by printf and a few other functions. % is used in combination 
    // with a letter, and the only ones we'll need are %d, %x, %f, %c and %s.
    // Table 24 on pages 82-83 of "C Pocket Reference" (by Prinz and Kirch-Prinz) 
    // is a more complete list of such %-combinations. Backslash is used 
    // essentially as in Python and Java. 

    // So far we've seen printf used with one argument (a string), and with 
    // two arguments (first is a string). printf can be called with several 
    // arguments, but the first must be a string, and the rest are used to 
    // fill in values when a % metacharacter occurs in this string. Examples:

    printf("Did you know that %d plus %d equals %d? Am I a %s?\n", 
    	1, 1, 2, "genius");
    printf("The decimal number %d equals the hexadecimal number %x.\n", n, n);
    printf("The number %d is the ASCII value of the character '%c'.\n", c, c); 
    printf("Have %s ever played %s?\n", "you", "Mad Libs"); 

    return 0;
}
