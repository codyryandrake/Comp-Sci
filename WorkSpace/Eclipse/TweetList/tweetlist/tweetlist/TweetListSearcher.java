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
	static int time = 15;
	//static String fName = "smalltweetdata.txt";
	static String fName = "tweetdata.txt"; //BIG DATA FILE! Takes 5+ min to read in.
	static String line = null;
	static TweetList tList; //Our list for holding all valid matching Tweets
	
	public static void main(String[] args) throws IOException 
	{
		

		
		/*
		 * Initial read-in of matching Tweets to the database. 
		 * The initial read-in can take 5+ min for large files.
		 * We return to this location whenever the user would like
		 * to refresh the database.
		 */
		//Animate.enable = true;
		Animate.loadingEffect("-------------------------", time);
		Animate.loadingEffect("Welcome to TweetSearcher!", time);
		Animate.loadingEffect("-------------------------", time);
		
//		if(!Prompt("Keep animation on?")) //Determine Animation class preferences
//			Animate.enable = false;
		
		queryOptions();

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
	

	public static void queryOptions()
	{
		while(true)
		{
			try {
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
					index++;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			

			while(true)
			{
				Animate.loadingEffect(
						"Please select from the following:\n"
						+ "\t[0] Reinitialize Database\n\n"
						+ "\t[1] Print Results\n\n"
						+ "\t[2] Search By Word or Phrase\n\n"
						+ "\t[3] Search By Location\n\n"
						+ "\t[4] Search by Date\n\n"
						+ "\t[5] Exit Program", time);
				int queryType = keyboard.nextInt();
				keyboard.nextLine();
				if(queryType == 0)
				{
					Animate.loadingEffect("Database reinitialized", time);
					continue;
				}
				if (queryType == 1)
				{
					tList.print();
					continue;
				}
				if (queryType == 2)
				{
					Animate.loadingEffect("Please specify a search term or phrase:\n", time);
					searchHistory[index] = keyboard.nextLine();
					Animate.loadingEffect("\n\t\t\tUSER QUERY: " + searchHistory[index], time);
					tList.filterText(searchHistory[index]);
				}
				if (queryType == 3)
				{			
					Animate.loadingEffect("Please specify a Latitude Coordinate:  ", time);
					int uLat = keyboard.nextInt();
					keyboard.nextLine();
					Animate.loadingEffect("Please specify a Longitude Coordinate:  ", time);
					int uLon = keyboard.nextInt();
					keyboard.nextLine();
					Animate.loadingEffect("Please specify a maximum search distance:  ", time);
					double maxDist = keyboard.nextDouble();
					keyboard.nextLine();
					searchHistory[index] = ("Location Search:"
									   + "\nLAT: " + uLat
									   + "\nLON: " + uLon
									   + "\nSEARCH RADIUS: " + maxDist);
					tList.filterLocation(uLat, uLon, maxDist);
				}
				if (queryType == 4)
				{
					int searchYear = -1, searchMonth = -1, searchDay = -1;
					if (Prompt("Specify by year?\n"))
					{
						Animate.loadingEffect("Please enter the desired year:", time);
						searchYear = keyboard.nextInt();
						keyboard.nextLine();
						if (Prompt("Also specify by month?\n"))
						{
							Animate.loadingEffect("Please enter the desired month:", time);
							searchDay = keyboard.nextInt();
							keyboard.nextLine();
							if (Prompt("Also specify by day?\n"))
							{
								Animate.loadingEffect("Please enter the desired day:", time);
								searchDay = keyboard.nextInt();
								keyboard.nextLine();
							}
						}
					}
					searchHistory[index] = ("Date Query: " + searchMonth + "/" + searchDay + "/" + searchYear);
					tList.filterDate(searchYear, searchMonth, searchDay);
				}
				if (queryType == 5)
					break;
				
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
	
			}
			if(Prompt("Are you sure you want to quit the program?"))
			{
				Animate.loadingEffect("Program terminated by User. Goodbye.", time);
				keyboard.close();
				System.exit(0);	
				break;
			}
				
		}
	}
}

