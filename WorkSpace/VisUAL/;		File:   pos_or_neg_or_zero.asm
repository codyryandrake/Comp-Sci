		;		File:   pos_or_neg_or_zero.asm
		;		Author: M. Q. Rieck
		;		Date:   8/4/2018
		
		;		Tests an integer number to see it it is positive, negative or zero.
		;		Reports the result of the test as 1, -1 or 0, accordingly.
		
		;		The following assembly instructions correspond to
		;		machine instructions stored in the "code area" of
		;		main memory for this computer program.
		;		"ENTRY" marks the beginning of this area.
		
ENTRY
		LDR		r0, =number			; put memory address of number to be tested into r0
		LDR		r1, [r0]			; load this number from main memory 								and put it in r1
		LDR		r0, =number2
		LDR		r3, [r0]
		CMP		r1, #0				; compare this number to zero, to determine status bits
		BLT		negative			; branch if N ("negative") status bit is 1
		BGT		positive			; branch if N status bit and Z 	("zero") status bit are both 0
		BEQ		zero
		
zero		MOV		r2, #0			; no branching so must be zero, so put 0 into r2
		BMI		bothNeg
		BPL		bothPos
		
		
negative	CMP		r2, #0			; if here, then number is negative, so put 0 into r2
		BEQ		bothNeg
		BMI		bothPos
		BPL		bothNeg
		
		
positive	CMP		r2, #0			; if here, then number is positive, so out 0 into r2
		BEQ		bothPos
		BMI		bothPos
		BPL		bothNeg
		
		
		
		
bothNeg	MOV		r3, #-1
		B		finish
		
bothPos	MOV		r3, #1
		B		finish
		
finish	END		; terminate program cleanly
		
		;		Before running, set number (see below) to any integer,
		;		positive or negative or zero. This represents data
		;		stored somewhere in the main memory unit of the imaginary
		;		computer being emulated here. It is stored in
		;		the "data area" for this computer program.
		;		Initially the memory location labeled answer contains
		;		garbage, but when the program finishes, it will hold
		;		the value 1, -1 or 0, depending on whether the
		;		tested number is positive, negative or zero.
		
number	DCD		77				; value to be tested - change it before running
number2	DCD		40
answer	DCD		12345		; initially garbage (no reason)
junk		DCB		'A','B','C'		; more garbage (ASCII codes stored)
