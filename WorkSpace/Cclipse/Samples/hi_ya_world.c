/*
                               hi_ya_world.c 

       To compile:       gcc hi_ya_world.c -o hi_ya_world
          or             gcc hi_ya_world.c -o hi_ya_world.exe    (Cygwin users, maybe)
          or             cl hi_ya_world.c                        (Developer Command Prompt)
       To execute:       ./hi_ya_world
          or             ./hi_ya_world.exe                       (Cygwin users, maybe)
          or             hi_ya_world                             (Developer Command Prompt)
*/

#include <stdio.h>             // include C's "standard I/O" "header file" 
#include <stdlib.h>            // include C's "standard library" "header file"

int main()                     // program starts in main() function, but might 
{                              // return an integer to the op sys (don't worry)
    printf("Hi ya world!\n");  // "printf" means "formatted print"
    return 0;                  // return 0 to operating system (not really needed)   
}
