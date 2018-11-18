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

public class TweetListSearcher 
{
	static Scanner keyboard = new Scanner(System.in); //Our User input Scanner
	static Tweet t;
	static String[] searchHistory = new String[100]; //create list of searched terms
	static int index = 0;
	public static void main(String[] args) throws IOException {
		
		String fName = "smalltweetdata.txt";
		//String fName = "tweetdata.txt"; //BIG DATA FILE! Takes 5+ min to read in.
		String line = null;
		TweetList tList; //Our list for holding all valid matching Tweets
		
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
				if(Prompt("\t\t\tWelcome to TweetSearcher-2011 Edition!\n\nWould you like to run the program?\n"))
				{
					if (Prompt("Rebuild database?\n")) 
					{
						searchHistory[index] = "";
						tList= new TweetList();
						if(Prompt("Would you like to specify an inital query?\n")) 
						{
							System.out.print("\t\t\tINITIAL QUERY:  ");
							searchHistory[index] = keyboard.nextLine(); //Grab User input for initial query
						}
							FileReader file = new FileReader(fName);
							BufferedReader read = new BufferedReader(file);
							System.out.println("\t\t\tPerforming intial query [" + searchHistory[index] + "]\n\t\t\tPlease wait...");

							while ((line = read.readLine()) != null ) 
								{
									t = new Tweet(line); //Take in the current line and organize it into a tweet.
									if(t.textContains(searchHistory[index])) //If the tweet contains the keyword in it's text field
										tList.prepend(t); //append the tweet to our list and loop
								}
							file.close(); //This will force a Scanner reset in the case we want to rebuild the database.
							read.close();
							tList.print(); //Print our initial list
							System.out.println("\t\t\t----------------------------------");
							System.out.println("\n\t\t\tInitial database search with keyword [" + searchHistory[index] + "]\n"
											 + "\t\t\treturned " + tList.size() + " results.\n");
							System.out.println("\t\t\t----------------------------------");
							if(tList.size() == 0)
							{
								System.out.println("\n\t\t\tSearch returned 0 results.");
								continue;
							}
							
							queryOptions(tList); //Run further queries on our initial list until the list results 
												//are empty or the user declines further refinement.
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("The file " + fName + " has not been found.");
			} 
		} while(!Prompt("Would you like to quit the program?\n")); //Prompt for confirmation before terminating program.

		System.out.println("Program terminated by user.");
		keyboard.close();
		System.exit(0);	
}
	
	public static boolean Prompt(String str) {
		System.out.print(str);
		String s = keyboard.nextLine();
		System.out.println();
		if (s.contains("y"))
			return true;
		else
			return false;
	}
	

	public static void queryOptions(TweetList tList)
	{
		index = 1; //Move our index past the first search
		while(Prompt("Refine query further?\n"))
		{
			System.out.println("Please select a query refinement option:\n"
					+ "[0] Additional keyword refinement.\n"
					+ "[1] Location-based refinement.");
			int queryType = keyboard.nextInt();
			keyboard.nextLine();
			if (queryType == 0)
			{
				System.out.println("Please specify a search term or phrase:\n");
				searchHistory[index] = keyboard.nextLine();
				System.out.println("\n\t\t\tUSER INPUT: " + searchHistory[index]);
				tList.filterText(searchHistory[index]);
			}
			if (queryType == 1)
			{			
				System.out.println("Please specify a Latitude Coordinate:  ");
				int lx = keyboard.nextInt();
				keyboard.nextLine();
				System.out.println("Please specify a Longitude Coordinate:  ");
				int ly = keyboard.nextInt();
				keyboard.nextLine();
				System.out.println("Please specify a maximum search distance:  ");
				double maxDist = keyboard.nextDouble();
				keyboard.nextLine();
				searchHistory[index] = ("\n\t\t\tLocation Search:"
								   + "\n\t\t\tLAT: " + lx
								   + "\n\t\t\tLON: " + ly
								   + "\n\t\t\tSEARCH RADIUS: " + maxDist + "\n\t\t\t");
				tList.filterLocation(lx, ly, maxDist);
			}
			if(tList.size() == 0)
			{
				System.out.println("\n\t\t\tSearch returned 0 results.");
				return;
			}
			index++;
			tList.print();
			System.out.println("\t\t\t----------------------------------");
			System.out.print("\t\t\tQuery History:\n\t\t\t");
			for (int i = 0; i < index; i++)
			{
				System.out.print("[" + searchHistory[i] + "]==>");
			}
			System.out.println("\n\t\t\tTweets found: " + tList.size());
			System.out.println("\t\t\t----------------------------------");
			System.out.println();
		}
		index = 0;
		return; //If the user declines refining, exit the method
	}

}

