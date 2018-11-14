/*
Name: Cody Ryan
Date: September 13, 2018
Description: The program uses the minMax() function to take in data from a data.txt file and appends the min and max values to a results.txt file.
Sources Cited: Class slides
*/

package minMax;

import java.util.Scanner;
import java.io.*;

public class minMax 
{
 public static void main(String[] args) throws IOException
	{
	String file = ("data.txt");			
	File dataFile = new File(file);
	Scanner input = new Scanner(dataFile);
	
	double max = 0; //Initialize max variable and set to 0
	double min = 0; //Initialize min variable and set to 0
	boolean firstRun = true; //The firstRun variable guarantees
	 			 //that both if statements below are
	 			 //triggered during the first loop

	
		while (input.hasNext()) //Loop ends when file ends
		{
			double temp = input.nextDouble(); //Increment through the file every loop
			if ( (temp > max) || firstRun) 
			{			    
				max = temp; //Assign the current num to max if it is greater
			}
			if ( (temp < min) || firstRun) 
			{			    
				min = temp; //Assign the current num to min if it is lesser	       
			}
			firstRun = false; //Permanently flipped after the first cycle of the loop
		}
		
		FileWriter fwriter = new FileWriter("results.txt", true); //Initialize the results file
		PrintWriter outputFile = new PrintWriter(fwriter); //Prepare the file to be appended to
		outputFile.println(dataFile + " results:"); //indicate in the results file which data.txt 
		outputFile.println();			    //file the results came from
		outputFile.println("Max num: " + max);	    //Append max
		outputFile.println("Min num: " + min);	    //Append min
		outputFile.println();
	 f
	 	input.close();			    	    //Close the input file
		outputFile.close();			    //Close the output file	

	System.out.println("DONE! Please look for \'results.txt\' in the program folder.");
	System.out.println("Program Terminated");
	
	

	}
}
