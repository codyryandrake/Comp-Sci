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
#include <string.h>
#define MAX_WORD_SIZE 12
//#define DEBUG //Comment out to disable DEBUG output
#define PRESET //Comment out to enable user input
 //The maximum number of accepted words.
 


int main() {



	char word[MAX_WORD_SIZE], //The temp string for holding a requested word

		 noun1[MAX_WORD_SIZE], //All word storage vars
		 noun2[MAX_WORD_SIZE],
		 noun3[MAX_WORD_SIZE],
		 verb1[MAX_WORD_SIZE],
		 verb2[MAX_WORD_SIZE],
		 verb3[MAX_WORD_SIZE],
		 adverb1[MAX_WORD_SIZE],
		 adverb2[MAX_WORD_SIZE],
		 adverb3[MAX_WORD_SIZE],
		 adjective1[MAX_WORD_SIZE],
		 adjective2[MAX_WORD_SIZE],
		 adjective3[MAX_WORD_SIZE];


#ifndef PRESET //If PRESET is not defined...
	printf("Please enter a noun..."); //NOUNS
	scanf("%s", noun1);
#ifdef DEBUG
	printf("%s\n", noun1);
#endif

	printf("Please enter a noun...");
	scanf("%s", noun2);
#ifdef DEBUG
	printf("%s\n", noun2);
#endif

	printf("Please enter a noun...");
	scanf("%s", noun3);
#ifdef DEBUG
	printf("%s\n", noun3);
#endif

	printf("Please enter a verb..."); //VERBS
	scanf("%s", verb1);
#ifdef DEBUG
	printf("%s\n", verb1);
#endif

	printf("Please enter a verb...");
	scanf("%s", verb2);
#ifdef DEBUG
	printf("%s\n", verb2);
#endif

	printf("Please enter a verb...");
	scanf("%s", verb3);
#ifdef DEBUG
	printf("%s\n", verb3);
#endif

	printf("Please enter an adverb..."); //ADVERBS
	scanf("%s", adverb1);
#ifdef DEBUG
	printf("%s\n", adverb1);
#endif

	printf("Please enter an adverb...");
	scanf("%s", adverb2);
#ifdef DEBUG
	printf("%s\n", adverb2);
#endif

	printf("Please enter an adverb...");
	scanf("%s", adverb3);
#ifdef DEBUG
	printf("%s\n", adverb3);
#endif

	printf("Please enter an adjective..."); //ADJECTIVES
	scanf("%s", adjective1);
#ifdef DEBUG
	printf("%s\n", adjective1);
#endif

	printf("Please enter an adjective...");
	scanf("%s", adjective2);
#ifdef DEBUG
	printf("%s\n", adjective2);
#endif

	printf("Please enter an adjective...");
	scanf("%s", adjective3);
#ifdef DEBUG
	printf("%s\n", adjective3);
#endif
#else
	_strcopy(noun1, "cat", MAX_WORD_SIZE);
//	noun1 = "cat";
//	noun2 = "dog";
//	noun3 = "squirrel";
//	adverb1 = "around";
//	adverb2 = "through";
//	adverb3 = "beneath";
//	verb1 = "run";
//	verb2 = "jump";
//	verb3 = "ski";
//	adjective1 = "hairy";
//	adjective2 = "bald";
//	adjective3 = "purposely";
#endif
	printf("Word collection complete!\n");
	printf("Here is your madLib:\n");

//	printf("One day %s decided to %s %s the %s world with the %s.\n", noun1, adverb1, verb1, adjective1, noun2);
//	printf("One day %s decided to %s %s the %s basketball court with the %s.\n", noun2, adverb2, verb2, adjective2, noun3);
//	printf("One day %s decided to %s %s the %s iceberg with the %s.\n", noun3, adverb3, verb3, adjective3, noun1);
//
//	printf("\n PROGRAM COMPLETE! Exiting...");


	return 0;

}
