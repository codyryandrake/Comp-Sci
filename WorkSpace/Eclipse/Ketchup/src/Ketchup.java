/*
Name: Cody Ryan
Date: September 16, 2018
Description: This program scans a text document using the Scanner() class.
			 After skipping the first line of the document, every subsequent
			 line is scanned. The manufacturer name on each line directs the
			 switch statement to award that lines profit price to the specified 
			 manufacturer.
Sources Cited: Class slides
			   https://stackoverflow.com/questions/11871520/how-can-i-read-input-from-the-console-using-the-scanner-class-in-java/31241089
			   https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
*/

import java.util.Scanner;
import java.io.*;

public class Ketchup {

	public static void main(String[] args) throws IOException
	{
		System.out.println("Welcome to the Ketchup Program."); //Greet the user.
		System.out.println("Scanning for \'ketchupData.txt\' in common folder...\n");
		
		String file = ("ketchupData.txt"); //Initialize the file variable with the specified file name.	
		File dataFile = new File(file); //Pass the file variable to the File class as the dataFile var.
		Scanner input = new Scanner(dataFile); //Initialize a new Scanner object as the input.
		
		System.out.println("File found! Initializing Ketchup Program...\n"); 
		
		String[] name = new String[4]; //String array for storing manufacturer names. 
		double[][] productArray = new double[3][4];

		
		input.nextLine(); //This line moves the cursor to the next line of the document.
		  				  //This allows us to skip the unique first line of the data file. 
		
		while (input.hasNext()) //If the document still has content, the loop continues.
		{
			
			String manufacturer = input.next(); //Assigns the current line's string val to manufacturer.
			for (int i = 0; i < productArray[2].length; i++) {
			productArray[0][i]= input.nextDouble(); /* Initialize 'price' array with the current line's prices. */
			}
			switch (manufacturer) 			/*This switch statement handles awarding
																profit to the named manufacturer once per loop. */
			{
			case "heinz": 							/*If the line starts with "heinz"... */
				name[0] = manufacturer;
				productArray[1][0] += productArray[0][0]; 		/* Increment "heinz" profits. */
				productArray[2][0] += 1;										/* increment "heinz" bottle sold. */
				break;
			case "hunts":
				name[1] = manufacturer;
				productArray[1][1] += productArray[0][1]; 		/* Increment "hunts" profits. */
				productArray[2][1] += 1;										/* increment "hunts" bottle sold. */
				break;
			case "delmonte":
				name[2] = manufacturer;
				productArray[1][2] += productArray[0][2]; 		/* Increment "delmonte" profits. */
				productArray[2][2] += 1;										/* increment "delmonte" bottle sold. */
				break;
			case "stb":
				name[3] = manufacturer;
				productArray[1][3] += productArray[0][3]; 		/* Increment "stb" profits. */
				productArray[2][3] += 1;										/* increment "stb" bottle sold. */
				break;
			}
			
		}
		
		input.close(); //Close the scanner.
		
		System.out.println("Ketchup Program Complete!\n");
		
		System.out.println("Results:\n");
		
		for (int i = 0; i < productArray[2].length; i++) {
		System.out.println(name[i].toUpperCase() //Relay each companies profits and total bottles sold to the user.
						 + "\nProfits: $" + productArray[1][i]
						 + "\nBottles Sold: " + productArray[2][i] + "\n"); 
		}
		

	}

}
