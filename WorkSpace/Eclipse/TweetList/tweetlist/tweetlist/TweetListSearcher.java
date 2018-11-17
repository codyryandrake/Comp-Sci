/*
Name: Cody Ryan
Date: 11.15.18
Description: This class contains a main method for searching through a precompiled TweetList database.
			Throughout all searches the original database remains intact and a filtered list is returned to the user.
Sources Cited: Homework instructions, class slides
*/

package tweetlist;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Random;
import java.util.Scanner;

public class TweetListSearcher {
	static Scanner keyboard = new Scanner(System.in); //Our User input Scanner
	public static void main(String[] args) throws IOException {
		
		//String fName = "smalltweetdata.txt";
		String fName = "tweetdata.txt"; //BIG DATA FILE! Takes 5+ min to read in.
		String[] searchList = new String[100]; //create list of searched terms
		searchList[0] = "";
		int index = 0;
		Tweet t;
		String line = null;
		TweetList tList = new TweetList(); //Our list for holding all valid matching Tweets
		
		/*
		 * Initial read-in of matching Tweets to the database. 
		 * The initial read-in can take 5+ min for large files.
		 * We return to this location whenever the user would like
		 * to refresh the database.
		 */
		do 
		{
			try 
			{
				if(Prompt("Welcome to TweetSearcher-2011! Would you like to run the program?  "))
				{
					if (Prompt("Rebuild database?  ")) 
					{
						if(Prompt("Would you like to specify an inital query?  ")) 
						{
							System.out.print("INITIAL QUERY:  ");
							searchList[0] = keyboard.nextLine(); //Grab User input for initial query
						}
							FileReader file = new FileReader(fName);
							BufferedReader read = new BufferedReader(file);
							System.out.println("Performing intial query [" + searchList[0] + "]\nPlease wait...");

							while ((line = read.readLine()) != null ) 
								{
									t = new Tweet(line); //Take in the current line and organize it into a tweet.
									if ((t.textContains(searchList[0]))) //If the tweet contains the keyword in it's text field
										tList.prepend(t); //Prepend the tweet to our list
								}
							tList.print(); //Print our initial list
							System.out.println("Initial database search with keyword [" + searchList[0] + "]\n"
											 + "returned " + tList.size() + " results.\n");
							System.out.println("Processing Options...");
							file.close(); //This will force a Scanner reset in the case we want to rebuild the database.
							read.close();
							searchList[0] = ""; //Reset our initial query

						
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("The file " + fName + " has not been found.");
			} 
		} while(!Prompt("Are you sure you want to quit?  ")); //Prompt for confirmation before terminating program.
		
		keyboard.close();
		System.exit(0);	
}
	
	public static boolean Prompt(String str) {
		System.out.print(str);
		String s = keyboard.nextLine();
		if (s.contains("y"))
			return true;
		else
			return false;
	}
	

	public static boolean refineList(TweetList tList, String[] searchList, int index, int mode) 
	{ 
		index = 1; 										//Index comes into loop and is immediately set to 1
		
		
	}

}

