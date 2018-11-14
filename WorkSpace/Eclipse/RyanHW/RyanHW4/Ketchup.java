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
		System.out.println("Scanning for \'ketchupData.txt\' in common folder...");
		System.out.println();
		
		String file = ("ketchupData.txt"); //Initialize the file variable with the specified file name.	
		File dataFile = new File(file); //Pass the file variable to the File class as the dataFile var.
		Scanner input = new Scanner(dataFile); //Initialize a new Scanner object as the input.
		
		System.out.println("File found! Initializing Ketchup Program..."); 
		System.out.println();
		
		double profitHeinz = 0; //All variables for storing accumulating profits.
		double profitHunts = 0;
		double profitDelmonte = 0;
		double profitSTB = 0;
		
		int bottlesHeinz = 0;
		int bottlesHunts = 0;
		int bottlesDelmonte = 0;
		int bottlesSTB = 0;
		
		input.nextLine(); //This line moves the cursor to the next line of the document.
		  				  //This allows us to skip the unique first line of the data file. 
		
		while (input.hasNext()) //If the document still has content, the loop continues.
		{
			String manufacturer = input.next(); //Assigns the current line's string val to manufacturer.
			double pHeinz = input.nextDouble(); //Assigns the next available double val to pHeinz.
			double pHunts = input.nextDouble(); //Assigns the next available double val to pHunts.
			double pDelmonte = input.nextDouble(); //Assigns the next available double val to pDelmonte.
			double pSTB = input.nextDouble(); //Assigns the next available double val to pSTB.
			
			switch (manufacturer) //This switch statement handles awarding profit to the named
								  //manufacturer once per loop.
			{
			case "heinz": //If the line starts with "heinz" 
				profitHeinz += pHeinz; //award Heinz the profit.
				bottlesHeinz += 1;
				break;
			case "hunts":
				profitHunts += pHunts;
				bottlesHunts += 1;
				break;
			case "delmonte":
				profitDelmonte += pDelmonte;
				bottlesDelmonte += 1;
				break;
			case "stb":
				profitSTB += pSTB;
				bottlesSTB += 1;
				break;
			}
		}
		
		input.close(); //Close the scanner.
		
		System.out.println("Ketchup Program Complete!");
		System.out.println();
		
		System.out.println("Results:");
		System.out.println();
		
		System.out.println("Heinz:" //Relay each companies profits and total bottles sold to the user.
						 + "\nProfits: $" + profitHeinz
						 + "\nBottles Sold: " + bottlesHeinz); 
		System.out.println();
		System.out.println("Hunts:" //Relay each companies profits and total bottles sold to the user.
				 + "\nProfits: $" + profitHunts
				 + "\nBottles Sold: " + bottlesHunts); 
		System.out.println();
		System.out.println("Delmonte:" //Relay each companies profits and total bottles sold to the user.
				 + "\nProfits: $" + profitDelmonte
				 + "\nBottles Sold: " + bottlesDelmonte); 
		System.out.println();
		System.out.println("STB:" //Relay each companies profits and total bottles sold to the user.
				 + "\nProfits: $" + profitSTB
				 + "\nBottles Sold: " + bottlesSTB); 
		System.out.println();
		

	}

}
