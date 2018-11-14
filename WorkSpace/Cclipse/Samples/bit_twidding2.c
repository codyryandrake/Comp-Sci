#include <stdio.h>
#include <stdlib.h> 

#define DOPEY   0x01
#define SNEEZY  0x02
#define BASHFUL 0x04
#define SLEEPY  0x08
#define HAPPY   0x10
#define GRUMPY  0x20
#define DOC     0x40 

int main() 
{
	int status = 0x26;
	printf("%x\n", status);
	if (status & (HAPPY | SLEEPY)) {
		status = status | DOPEY;
	} else {
		status = status & ~SNEEZY;
	}
	printf("%x\n", status);
	status = status ^ DOC; 
	printf("%x\n", status);
	if ((status & GRUMPY) && !(status & HAPPY)) {
		status = status | BASHFUL | DOC | DOPEY; 
	} else {
		status = status & ~(BASHFUL | DOC | DOPEY);
	}
	printf("%x\n", status);
}
