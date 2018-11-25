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
import java.util.Scanner;

public class TweetListSearcher 
{
	static Scanner keyboard = new Scanner(System.in); //Our User input Scanner
	static String[] searchHistory = new String[100]; //create list of searched terms
	static int index = 0;
	static int queryType;
	static int time = 15;
	static int searchLon, searchLat;
	static double maxDist;
	static int searchYear = -1, searchMonth = -1, searchDay = -1,
	searchHour = -1, searchMin = -1, searchSec = -1;
	//static String fName = "smalltweetdata.txt";
	static String fName = "tweetdata.txt"; //BIG DATA FILE! Takes 5+ min to read in.
	static TweetList tList = new TweetList(); //Our list for holding all valid matching Tweets
	
	public static void main(String[] args)
	{
		Animate.enable = false; //Keep animation off for grading purposes
		Animate.loadingEffect("--------------------------------------------", time);
		Animate.loadingEffect("\nWelcome to TweetSearcher! #L33tTw33t Edition", time);
		Animate.loadingEffect("\n--------------------------------------------", time);
		
//		if(!Prompt("\nKeep animation on?")) //Determine Animation class preferences
//			Animate.enable = false;
		if(Prompt("\nSpecify data file?"))
		{
			if(Prompt("Please enter the data file name with file type."))
			fName = keyboard.nextLine();
		}
		else
			Animate.loadingEffect("\nData file defaulted to: " + fName + ".\n\n", time);
		queryOptions();
	}

	public static void queryOptions()
	{
		while(true) //Exiting this loop closes the program
		{
			while(true) //Exiting this loop re-confirms program termination
			{
				Animate.loadingEffect(
						"\nPlease select from the following:\n\n"
						+ "\t[1] Search by Word or Phrase?\n\n"
						+ "\t[2] Search by Date?\n\n"
						+ "\t[3] Search by Location?\n\n"
						+ "\t[4] Search by Timestamp?\n\n", time);					
				if (index > 0) //These options hidden until a list is created
					Animate.loadingEffect(
						  "\t[5] Print Results?\n\n"
						+ "\t[6] Rebuild Database?\n\n"
						+ "\t[0] Exit Program?\n\n", time);
				queryType = keyboard.nextInt(); //Get queryType
				keyboard.nextLine();
				
				if (queryType > 0 && queryType < 5) 
				{ //If the user has selected a search option
					SearchDatabase();
					continue;
				}
				if (queryType == 5)
				{ //Print the refined list
					tList.print(); 
					//Append our query history below the printed tweets
					PrintSearchHistory(); 
					continue;
				}
				if(queryType == 6)
				{
					if(index > 0 && Prompt("Reintialize database? All search history will be lost.\n\n"))
							if(Prompt("WARNING This action cannot be undone. Type 'y' again to continue..."))
								index = 0;			
					continue;
				}
				if (queryType == 0)
					break; //Exit inner-loop
			}
			if(Prompt("Are you sure you want to quit the program?"))
			{
				Animate.loadingEffect("Program terminated by User. Goodbye.", time);
				keyboard.close();
				System.exit(0);	
			}
				
		}
	}
	
	public static void SearchDatabase()
	{
		switch(queryType)
		{
			case 1:
				Animate.loadingEffect("Please specify a search term or phrase:", time);
				searchHistory[index] = keyboard.nextLine();	
				if(index == 0)
					BuildDatabase();
				else
					tList.filterText(searchHistory[index]);
				break;
			case 2:
				searchYear = -1; searchMonth = -1; searchDay = -1;
				Animate.loadingEffect("Please enter the desired year, or enter <-1> to skip:  ", time);
				searchYear = keyboard.nextInt();
				keyboard.nextLine();
				if(searchYear != -1) //User entered year
				{
					Animate.loadingEffect("Please enter the desired month, or enter <-1> to skip:  ", time);
					searchMonth = keyboard.nextInt();
					keyboard.nextLine();
					if(searchMonth != -1) //User entered month
					{
						Animate.loadingEffect("Please enter the desired day, or enter <-1> to skip:  ", time);
						searchDay = keyboard.nextInt();
						keyboard.nextLine();
					}
				}
				searchHistory[index] = (searchYear + "/" + searchMonth + "/" + searchDay);
					if(index == 0)
						BuildDatabase();
					else
						tList.filterDate(searchYear, searchMonth, searchDay);
				break;
			case 3:
				Animate.loadingEffect("Please specify a Latitude Coordinate:  ", time);
				int searchLat = keyboard.nextInt();
				keyboard.nextLine();
				Animate.loadingEffect("Please specify a Longitude Coordinate:  ", time);
				int searchLon = keyboard.nextInt();
				keyboard.nextLine();
				Animate.loadingEffect("Please specify a maximum search distance:  ", time);
				maxDist = keyboard.nextDouble();
				keyboard.nextLine();
				searchHistory[index] = ("(" + searchLat + ", " + searchLon + ") Area: " + maxDist);
				if(index == 0)
					BuildDatabase();
				else
					tList.filterLocation(searchLat, searchLon, maxDist);
				break;
			case 4:
				searchHour = -1; searchMin = -1; searchSec = -1;
				Animate.loadingEffect("Please enter the desired hour, or enter <-1> to skip:  ", time);
				searchHour = keyboard.nextInt();
				keyboard.nextLine();
				if(searchHour != -1)
				{
					Animate.loadingEffect("Please enter the desired minute, or enter <-1> to skip:  ", time);
					searchMin = keyboard.nextInt();
					keyboard.nextLine();
					if(searchMin != -1)
					{
						Animate.loadingEffect("Please enter the desired second, or enter <-1> to skip:  ", time);
						searchSec = keyboard.nextInt();
						keyboard.nextLine();
					}
				}
				searchHistory[index] = (searchHour + ":" + searchMin + ":" + searchSec);
					if(index == 0)
						BuildDatabase();
					else
						tList.filterTime(searchHour, searchMin, searchSec);
				break;
		}
		index++; //Increment index after a search
		PrintSearchHistory();
		if(tList.size() == 0) 
		{ //If we ever encounter an empty list
			Animate.loadingEffect("\nLIST EMPTY! Rebuilding database...\n\n", time);
			index = 0;
		}
	
	}
	/*
	 * Initial read-in of matching Tweets to the database. 
	 * The initial read-in can take 5+ min for large files.
	 * We return to this location whenever the user would like
	 * to refresh the database.
	 */	
	public static void BuildDatabase()
	{
		try {
			Animate.loadingEffect("\nBuilding database...", time);
			Tweet t;
			tList = new TweetList(); //Reset our tList
			FileReader file = new FileReader(fName);
			BufferedReader read  = new BufferedReader(file);
			String line;
			while ((line = read.readLine()) != null ) 
			{	
				t = new Tweet(line); //Take in the current line and organize it into a tweet.
				switch(queryType)
				{//Keyword/Phrase search
				case 1:
					if(t.textContains(searchHistory[index]) == true)
					{
						//Animate.loadingEffect("\nKeyword match found and appended.\n", time);
						tList.prepend(t); //append the tweet to our list and loop
					}
					break;
				case 2:
					if(t.dateContains(searchYear, searchMonth, searchDay) == true)
					{
						//Animate.loadingEffect("\nDate match found and appended.\n", time);
						tList.prepend(t); //append the tweet to our list and loop
					}
					break;		
				case 3:
					if(t.locationContains(searchLat, searchLon, maxDist) == true)
					{
						//Animate.loadingEffect("\nLocation match found and appended.\n", time);
						tList.prepend(t); //append the tweet to our list and loop
					}
					break;
				case 4:
					if(t.timeContains(searchHour, searchMin, searchSec) == true)
					{
						//Animate.loadingEffect("\nTimestamp match found and appended.\n", time);
						tList.prepend(t); //append the tweet to our list and loop
					}
					break;
				}			
			}
			file.close(); //This will force a Scanner reset in the case we want to rebuild the database.
			read.close();
		} 
		catch (FileNotFoundException e) {System.out.println("The file " + fName + " could not be found.");} 
		catch (IOException e) {System.out.println("An error occurred while reading " + fName + ".");}
	}
	
	public static void PrintSearchHistory()
	{
		Animate.loadingEffect("\n--------------------------------------------------------------------", time);
		Animate.loadingEffect("\nQuery History: -1 represents a User-omitted parameter.\n\n"
				+ "INITIAL QUERY:\n", time);
		for (int i = 0; i < index; i++)
		{
			Animate.loadingEffect("[" + searchHistory[i] + "]", time);
			if(i != 0) //Every 6 Tweets
			{
				Animate.loadingEffect("➣➣➣", time); //Move the cursor down a row
				if(i%5 == 0)
					System.out.println();
			}
			else
				System.out.println("\n");
		}
		Animate.loadingEffect("\n\nTweets found: " + tList.size(), time);
		Animate.loadingEffect("\n--------------------------------------------------------------------", time);
		Prompt("\n\nContinue?");
	}
	public static boolean Prompt(String str) 
	{
		Animate.loadingEffect(str, time);
		String s = keyboard.nextLine();
		System.out.println();
		if (s.startsWith("y"))
			return true;
		return false;
	}
}

