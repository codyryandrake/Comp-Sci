/*
Name: Cody Ryan
Date: 09.05.2018
Description: This program welcomes the user with a prompt. If the user begins the program, the user is prompted to name the <fileName>.txt
		     where the output of the program will be stored. After, the program prompts the user to specify how many numbers they would
		     like to average. Using that number, the program prompts the user to enter integers, one at a time, until all numbers have been
		     chosen. As each integer is entered it is copied to the <fileName>.txt. The program outputs the average of all entered numbers as
		     a Double value into <fileName>.txt on a new line. The program then instructs the user to find their new file and terminates. 
		     
Sources Cited: https://stackoverflow.com/questions/2375649/converting-to-upper-and-lower-case-in-java
			   https://stackoverflow.com/questions/1938101/how-to-initialize-an-array-in-java
			   https://stackoverflow.com/questions/21329617/how-can-input-from-keyboard-in-array-in-java
			   https://stackoverflow.com/questions/2795350/how-to-put-a-scanner-input-into-an-array-for-example-a-couple-of-numbers
*/

import java.io.*;
import java.util.Scanner;



public class Average 
{
	//private static final String fileName = null;
	static Scanner keyboard = new Scanner(System.in); //Initialize the keyboard for user input.

	public static void main(String[] args) throws IOException //The main program runs in here.
	{	
		
		if (welcomePrompt()) //If welcomePrompt() returns <true> the rest of the program can run, otherwise the program ends.
		{
			System.out.println("\n Would you like to append to an existing file?");
			String input = keyboard.nextLine().toLowerCase(); //Captures an answer to the prompt and forces lower-case formatting.
			char firstLetter = input.charAt(0); //Grab the first character from the user input.
			if (firstLetter == 'y') //If the user character is 'Y' or 'y' the program begins.
			{
				System.out.println("\nPlease enter the name of an existing"
								 + " *.txt file.");
				String fileName = keyboard.nextLine(); //Grabs the user input for naming the file.
				FileWriter fWriter = new FileWriter(fileName, true);
				PrintWriter outputFile = new PrintWriter(fileName + ".txt"); //Opens the existing file and prepares to append to it.
				
				System.out.println("\nHow many integers would you like to average?");
				int size = keyboard.nextInt(); //Allows the user to specify how many numbers will be averaged.
				double[] doubleArray = new double[size]; //Makes an array to hold <size> integers.
				
				double sum = 0; //By making sum a Double, we guarantee the final average result will include decimal values.
				
				for (int i = 0; i < doubleArray.length; i++) //Iterate through the new array to fill it with user-specified integers.
				{
					System.out.println("\nPlease type an integer into the console...");
					doubleArray[i] = keyboard.nextDouble(); //Every loop a new double is added to the array.
					sum = sum + doubleArray[i]; //Every loop the most recent integer is added to the sum total.
					outputFile.print(doubleArray[i] + " "); //Every loop each user-specified double is output to the <fileName.txt>.
				}
				outputFile.println(); //Move the cursor down a line.
				outputFile.println("\nAverage: " + sum/doubleArray.length ); //Calculate and output the average to <fileName.txt>.
				outputFile.close(); //Close <fileName.txt> before terminating the program.
				
				System.out.println("Program Completed. Please look for " + fileName + " in the program folder."); //Prompts the user to look for the new
				  //<fileName.txt> and terminates.
			}
				
			else
			{
				
				System.out.println("\nPlease name the file the program will output to...");
				String fileName = keyboard.nextLine(); //Grabs the user input for naming the file.
				PrintWriter outputFile = new PrintWriter(fileName + ".txt"); //Creates a file named <fileName.txt>.
				
				System.out.println("\nHow many integers would you like to average?");
				int size = keyboard.nextInt(); //Allows the user to specify how many numbers will be averaged.
				double[] doubleArray = new double[size]; //Makes an array to hold <size> integers.
				
				double sum = 0; //By making sum a Double, we guarantee the final average result will include decimal values.
				
				for (int i = 0; i < doubleArray.length; i++) //Iterate through the new array to fill it with user-specified integers.
				{
					System.out.println("\nPlease type an integer into the console...");
					doubleArray[i] = keyboard.nextDouble(); //Every loop a new double is added to the array.
					sum = sum + doubleArray[i]; //Every loop the most recent integer is added to the sum total.
					outputFile.print(doubleArray[i] + " "); //Every loop each user-specified double is output to the <fileName.txt>.
				}
				outputFile.println(); //Move the cursor down a line.
				outputFile.println("\nAverage: " + sum/doubleArray.length ); //Calculate and output the average to <fileName.txt>.
				outputFile.close(); //Close <fileName.txt> before terminating the program.
				
				System.out.println("Program Completed. Please look for " + fileName + " in the program folder."); //Prompts the user to look for the new
				  //<fileName.txt> and terminates.
			}
			
			
			
			
			
		}

		
		
		

	}
	public static boolean welcomePrompt() //A quick method for initiating or terminating the program.
	{
		System.out.println("Welcome to the AVERAGING program.\n"
						 + "This program accepts a user-specified number of integers\n"
						 + "and outputs these values, along with their average,\n"
						 + "to a user-specified *.txt file.\n"
				 		 + "Type \'Yes\' to begin or \'No\' to quit. \n");
		String input = keyboard.nextLine().toLowerCase(); //Captures an answer to the prompt and forces lower-case formatting.
		char firstLetter = input.charAt(0); //Grab the first character from the user input.
		if (firstLetter == 'y') //If the user character is 'Y' or 'y' the program begins.
		{
			return true;
		}
			
		else
		{
			System.out.println("\nProgram Terminated"); //Alert the user that the program has ended.
			return false;
		}
		
	}


}
