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
	static int time = 10;
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
				if(Prompt("\n\nWould you like to run the program?\n"))
				{
					Animate.enable = true;
					Animate.loadingEffect("---------------------------------------", time);
					Animate.loadingEffect("Welcome to TweetSearcher-2011 Edition!", time);
					Animate.loadingEffect("---------------------------------------", time);
					
					if(!Prompt("Keep animation on?")) //Determine Animation class preferences
						Animate.enable = false;
					
					if (Prompt("Rebuild database?\n")) 
					{
						searchHistory[index] = "";
						tList= new TweetList();

						if(Prompt("Specify an inital keyword query?\n"))
						{
							Animate.loadingEffect("Please enter an initial search term...", time);
							searchHistory[index] = keyboard.nextLine(); //Grab User input for initial query
						}
						Animate.loadingEffect("----------------------------------", time);
						Animate.loadingEffect("INITIAL QUERY:", time);
							FileReader file = new FileReader(fName);
							BufferedReader read = new BufferedReader(file);
							Animate.loadingEffect("Performing intial query [" + searchHistory[index] + "]"
												+ "\nPlease wait...", time);

							while ((line = read.readLine()) != null ) 
								{
									t = new Tweet(line); //Take in the current line and organize it into a tweet.
									if(t.textContains(searchHistory[index])) //If the tweet contains the keyword in it's text field
										tList.prepend(t); //append the tweet to our list and loop
								}
							file.close(); //This will force a Scanner reset in the case we want to rebuild the database.
							read.close();

							Animate.loadingEffect("\nInitial database search with keyword [" + searchHistory[index] + "]\n"
											 + "returned " + tList.size() + " results.\n", time);
							Animate.loadingEffect("----------------------------------", time);
							if (Prompt("\nPrint results?")) //The first list could potentially be huge
							{
								tList.print(); //Print our initial list
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
		Animate.loadingEffect(str, time);
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
		while(Prompt("Refine query?\n"))
		{
			Animate.loadingEffect("Please select a query refinement option:\n"
					+ "\t[0] Additional keyword refinement.\n"
					+ "\t[1] Location-based refinement.\n"
					+ "\t[2] Date-based refinement.", time);
			int queryType = keyboard.nextInt();
			keyboard.nextLine();
			if (queryType == 0)
			{
				Animate.loadingEffect("Please specify a search term or phrase:\n", time);
				searchHistory[index] = keyboard.nextLine();
				Animate.loadingEffect("\n\t\t\tUSER QUERY: " + searchHistory[index], time);
				tList.filterText(searchHistory[index]);
			}
			if (queryType == 1)
			{			
				Animate.loadingEffect("Please specify a Latitude Coordinate:  ", time);
				int lx = keyboard.nextInt();
				keyboard.nextLine();
				Animate.loadingEffect("Please specify a Longitude Coordinate:  ", time);
				int ly = keyboard.nextInt();
				keyboard.nextLine();
				Animate.loadingEffect("Please specify a maximum search distance:  ", time);
				double maxDist = keyboard.nextDouble();
				keyboard.nextLine();
				searchHistory[index] = ("\nLocation Search:"
								   + "\nLAT: " + lx
								   + "\nLON: " + ly
								   + "\nSEARCH RADIUS: " + maxDist + "\n\t\t\t");
				tList.filterLocation(lx, ly, maxDist);
			}
			if (queryType == 2)
			{
				int searchMonth = -1, searchDay = -1, searchYear = -1;
				if (Prompt("Specify by month?\n"))
				{
					Animate.loadingEffect("Please enter the desired month:", time);
					searchMonth = keyboard.nextInt();
					keyboard.nextLine();
					if (Prompt("Also specify by day?\n"))
					{
						Animate.loadingEffect("Please enter the desired day:", time);
						searchDay = keyboard.nextInt();
						keyboard.nextLine();
						if (Prompt("Also specify by year?\n"))
						{
							Animate.loadingEffect("Please enter the desired year:", time);
							searchYear = keyboard.nextInt();
							keyboard.nextLine();
							tList.filterDate(searchDay, searchMonth, searchYear);
						}
						else
							tList.filterDate(searchMonth, searchMonth);
					}
					else
						tList.filterDate(searchMonth);
				}
				searchHistory[index] = ("Date Query: " + searchMonth + "/" + searchDay + "/" + searchYear);			
			}

			index++;
			
			Animate.loadingEffect("\n--------------------------------------------------------------------", time);
			Animate.loadingEffect("Query History:\n", time);
			for (int i = 0; i < index; i++)
			{
				System.out.print("[" + searchHistory[i] + "]==>");
			}
			Animate.loadingEffect("\nTweets found: " + tList.size(), time);
			Animate.loadingEffect("--------------------------------------------------------------------", time);
			if(tList.size() == 0) //If we ever encounter an empty list
				return;
			if(Prompt("Print list?"))
				tList.print();
		}
		index = 0;
		return; //If the user declines refining, exit the method
	}

}

