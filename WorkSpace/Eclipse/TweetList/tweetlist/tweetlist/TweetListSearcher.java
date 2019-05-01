/*
Name: Cody Ryan
Date: 11.28.18
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
	static Scanner keyboard = new Scanner(System.in);				//Our User input Scanner
	static String[] searchHistory = new String[100];				//create list of searched terms
	static int index = 0;											//index at 0 indicates an initial 
																	//search (rebuilding the database)
	static int queryType;											//Determines what action the program will perform
	static int time = 15;											//Animation.TextDraw speed
	static double searchLon, searchLat, searchRadius;				//User search coordinate vars
	static int searchYear = -1, searchMonth = -1, searchDay = -1,	//User search date and timestamp vars
			   searchHour = -1, searchMin = -1, searchSec = -1;
	static String fileName;											//Holds data file name
	
	static TweetList fullList;										//Our full tweet list for quick rebuilding
	static TweetList filteredList;									//Our list for holding filtered tweets
	
	public static void main(String[] args)
	{
		//Animate.enable = true;									//Keep animation off for grading purposes
		Animate.TextDraw("--------------------------------------------", time);
		Animate.TextDraw("\nWelcome to TweetSearcher! #L33tTw33t Edition", time);
		Animate.TextDraw("\n--------------------------------------------", time);
		
//		if(!Prompt("\nKeep animation on?")) //Determine Animation class preferences
//			Animate.enable = false;
		
		//Main Menu
		Options(); 
	}
	
	/*
	 * A method for handling program options. On first run, or database rebuild, User may specify the data file to be 
	 * read from. User may choose to search by words, location, date, or timestamp. After an 
	 * initial search the User may choose to print results, rebuild the database, or exit the program. 
	 */
	public static void Options()
	{
		while(true)													//Exiting this loop closes the program
		{
			while(true)												//Exiting this loop re-confirms 
			{														//program termination
				if(fileName == null)								//True on first loop of program
				{													//and database reset
					fullList = new TweetList();						//Clear our original list
					//A new file is being read																	
					Animate.TextDraw("\n\nPlease enter the data file name with file-type extension.\n"
							+ "Example: tweetdata.txt   \n", time);
					fileName = keyboard.nextLine();
					//Animate.TextDraw("\nData file: " + fileName + ".\n\n", time);
				}				

				Animate.TextDraw(
						"\nPlease select from the following:\n"
						+ "\t[1] Search by Word or Phrase?\n"
						+ "\t[2] Search by Date?\n"
						+ "\t[3] Search by Location?\n"
						+ "\t[4] Search by Timestamp?\n"
						+ "\t[5] Reboot System?\n", time);
				if (index > 0) {										//Hide print option until a search has been made
				Animate.TextDraw(
						  "\t[6] Print Results?\n", time); }
				Animate.TextDraw(
						"\t[7] Enable Animation?\n"
						+ "\t[8] Disable Animation?\n"
						+ "\t[0] Exit Program?\n", time); 

						
				
				queryType = keyboard.nextInt();						//Get queryType
				keyboard.nextLine();
				
				if (queryType > 0 && queryType < 5) 
				{													//If the user has selected a search option
					SearchDatabase();
					continue;
				}				
				if(queryType == 5)
				{
					if(Prompt("Reboot system? All search history will be lost. Type 'y' to continue.\n\n"))
							if(Prompt("WARNING This action cannot be undone. Type 'y' again to confirm database reset..."))
							{
								fileName = null;					//Reset our file name variable to 
																	//confirm file name on next loop
								index = 0;							//Reset the search history index	
							}
					continue;
				}
				if (queryType == 6)
				{													//Print the refined list
					if (index == 0)
						continue;
					filteredList.print(); 
																	//Append our query history below the printed tweets
					PrintSearchHistory(); 
					continue;
				}
				if (queryType == 7)
					Animate.enable = true;
				if(queryType == 8)
					Animate.enable = false;
				if (queryType == 0)
					break;											//Exit inner-loop
			}
			if(Prompt("Are you sure you want to quit the program?"))
			{
				Animate.TextDraw("Program terminated by User. Goodbye.", time);
				keyboard.close();									//Close our keyboard scanner
				System.exit(0);										//Cleanly exit the program
			}
				
		}
	}
	
	public static void SearchDatabase()
	{
		switch(queryType)
		{
			case 1:
				Animate.TextDraw("Please specify a search term or phrase:", time);
				searchHistory[index] = keyboard.nextLine();	
				if(fullList.isEmpty())								//If the fullList is empty, the first query 
					BuildDatabase();								//(of any type) will initialize the database
				else
					filteredList.filterText(searchHistory[index]);	//Print out formatted search term
				break;
			case 2:
				searchYear = -1; searchMonth = -1; searchDay = -1;	//Default parameter values
				Animate.TextDraw("Please enter the desired year, or enter <-1> to skip:  ", time);
				searchYear = keyboard.nextInt();
				keyboard.nextLine();
				
				Animate.TextDraw("Please enter the desired month, or enter <-1> to skip:  ", time);
				searchMonth = keyboard.nextInt();
				keyboard.nextLine();
				
				Animate.TextDraw("Please enter the desired day, or enter <-1> to skip:  ", time);
				searchDay = keyboard.nextInt();
				keyboard.nextLine();
				
				searchHistory[index] = (searchYear + "/" + searchMonth + "/" + searchDay);
				if(fullList.isEmpty())
					BuildDatabase();
				else
					filteredList.filterDate(searchYear, searchMonth, searchDay);
				break;
			case 3:
				Animate.TextDraw("Please specify a Latitude Coordinate {double}:  ", time);
				searchLat = keyboard.nextDouble();
				keyboard.nextLine();
				Animate.TextDraw("Please specify a Longitude Coordinate {double}:  ", time);
				searchLon = keyboard.nextDouble();
				keyboard.nextLine();
				Animate.TextDraw("Please specify a maximum search distance {double}:  ", time);
				searchRadius = keyboard.nextDouble();
				keyboard.nextLine();
				searchHistory[index] = ("(" + searchLat + ", " + searchLon + ") Radius: " + searchRadius);
				if(fullList.isEmpty())
					BuildDatabase();
				else
					filteredList.filterLocation(searchLat, searchLon, searchRadius);
				break;
			case 4:
				searchHour = -1; searchMin = -1; searchSec = -1;	//Default parameter values
				Animate.TextDraw("Please enter the desired hour, or enter <-1> to skip:  ", time);
				searchHour = keyboard.nextInt();
				keyboard.nextLine();
				
				Animate.TextDraw("Please enter the desired minute, or enter <-1> to skip:  ", time);
				searchMin = keyboard.nextInt();
				keyboard.nextLine();
				
				Animate.TextDraw("Please enter the desired second, or enter <-1> to skip:  ", time);
				searchSec = keyboard.nextInt();
				keyboard.nextLine();
				
				searchHistory[index] = (searchHour + ":" + searchMin + ":" + searchSec);
				if(fullList.isEmpty())
					BuildDatabase();
				else
					filteredList.filterTime(searchHour, searchMin, searchSec);
				break;
		}
		
		index++; 													//Increment index after a successful search
		PrintSearchHistory();										//Print out the search history thus far
		//If we ever encounter an empty list
		if(filteredList.size() == 0) 
		{															
			Animate.TextDraw("\nLIST EMPTY! Rebuilding complete database...\n\n", time);
			//fileName = null;										//Reset our filename preferences
			index = 0;												//Reset our search history 
			filteredList = fullList.clone();						//Clone our complete list 
		}
	
	}
	/*
	 * Initial read-in of matching Tweets to the database. 
	 * The initial read-in can take 5+ min for large files.
	 * We return to this location whenever the user would like
	 * to rebuild the database.
	 */	
	public static void BuildDatabase()
	{
		if (fullList.isEmpty()) 
		{
			try {
				Animate.TextDraw("\nBuilding database...", time);
				Tweet t;
				filteredList = new TweetList();							//Reset our filteredList
				FileReader file = new FileReader(fileName);
				BufferedReader read = new BufferedReader(file);
				String line;
				while ((line = read.readLine()) != null) {
					t = new Tweet(line);								//Take in the current line as a tweet.
					fullList.prepend(t);								//Prepend ALL tweets to the full list
					switch (queryType) {
					case 1:												//Keyword/Phrase search
						if (t.textContains(searchHistory[index]) == true)//If the keyword is found
							filteredList.prepend(t);					//Append the tweet to our list and loop
						break;
					case 2:												//Date search
						if (t.dateContains(searchYear, searchMonth, searchDay) == true)
							filteredList.prepend(t);
						break;
					case 3:												//Location search
						if (t.locationContains(searchLat, searchLon, searchRadius) == true)
							filteredList.prepend(t);
						break;
					case 4:												//Timestamp search
						if (t.timeContains(searchHour, searchMin, searchSec) == true)
							filteredList.prepend(t);
						break;
					}
				}
				file.close();											//This will force a Scanner reset in case 
																		//we want to rebuild the database.
				read.close();
			} 
			catch (FileNotFoundException e) {System.out.println("The file " + fileName + " could not be found.");} 
			catch (IOException e) {System.out.println("An error occurred while reading " + fileName + ".");} 
		}
		else
			filteredList = fullList.clone();							//Clone our complete list
	}
	
	
	//Show our current search history, data file being read-in, and tweet count
	public static void PrintSearchHistory()
	{
		Animate.TextDraw("\n---------------------------------------------------------------------------", time);
		Animate.TextDraw("\n" + filteredList.size() + " Tweet(s) found in " + fileName + "."
				+ "\nINITIAL QUERY: [" + searchHistory[0] + "]" //Print initial query on its own line
				+ "\nQuery History: -1 represents a User-omitted parameter.\n\n", time);
		for (int i = 1; i < index; i++)
		{
			Animate.TextDraw("[" + searchHistory[i] + "]", time);												
			Animate.TextDraw("➣➣➣", time); 
			if(i%3 == 0)										//Every 3 tweets
				System.out.println();							//Move the cursor down a row				
		}
		Animate.TextDraw("\n---------------------------------------------------------------------------", time);
		Prompt("\n\nPress <enter> to continue...");
	}
	
	//A simple method for prompting the user
	public static boolean Prompt(String str) 
	{
		Animate.TextDraw(str, time);
		String s = keyboard.nextLine();
		System.out.println();
		if (s.toLowerCase().startsWith("y"))
			return true;
		return false;
	}
}

