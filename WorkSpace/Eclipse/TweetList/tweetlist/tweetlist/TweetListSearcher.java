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

public class TweetListSearcher {

	public static void main(String[] args) throws IOException {
		
		String fName = "smalltweetdata.txt";
		//String fName = "tweetdata.txt"; //BIG DATA FILE! Takes 5+ min to read in.
		Scanner keyboard = new Scanner(System.in);
		FileReader file = null;
		BufferedReader read = null;
		String initialSearch = null;
		String[] searchList = new String[100]; //create list of searched terms
		String line;
		Tweet t;
		TweetList tList = new TweetList();
		TweetList filteredList;

		int index = 0;
		do {
		try {
			file = new FileReader(fName);
			read = new BufferedReader(file);
		} catch (FileNotFoundException e) {
			System.out.println("The file " + fName + " has not been found.");
		} 

				
				System.out.println("Would you like to specify an initial search term?");
				if(promptContinue(keyboard.nextLine())) {
					System.out.println("Please enter a word or phrase to perform an intial search...");
					initialSearch = keyboard.nextLine();
				}
				else
					initialSearch = null;
				
					System.out.println("Please wait. Building list...");	
					while ((line = read.readLine()) != null ) {
						t = new Tweet(line);
						if(initialSearch != null) { //If the user has specified an initial search term 					
							if (t.textContains(initialSearch)) 
								tList.prepend(t); //Only prepend MATCHING tweets to the main list
						}
						else
							tList.prepend(t); //Else, prepend ALL tweets to the main list
						}
					if(tList.size() == 0)
						System.out.println("Sorry, the initial search returned 0 results. Resetting database...");
					
					read.close(); 
			} while (tList.size() == 0); //If the initial search returns 0 hits, ask for a new initial search term.
			
			System.out.println("List created!\n");
			System.out.println("The Tweet database contains " + tList.size() + " tweets. ");
			System.out.println("Initial search term: [" + initialSearch + "]");
			
			

		do {
			
			do {	
													//get search term
				System.out.println("Please enter a word or phrase to search for:   ");
													//Add search term to search list
				searchList[index] = keyboard.nextLine();
													//create new list from filter(search term)
				tList.filter("" + searchList[index] + "");
													//Throw filtered list into temp var
				filteredList = tList.getFList();
													//print filtered list
				filteredList.print();
				System.out.println("All " + filteredList.size() + " Tweets containing:");
					for (int i = 0; i < index+1; i++) {
						System.out.print("[" + initialSearch + "] ==>");
						System.out.println("[" + searchList[i] + "]\n");
					}
				System.out.println("Would you like to REFINE the search with an additional word or phrase?\n");
													//refine search or revert search
				while (promptContinue(keyboard.nextLine())) {
													//increment index		
					index++; 
					System.out.println("Please enter a REFINED word or phrase to search for:   ");
													//Add search term to search list
					searchList[index] = keyboard.nextLine();
					String refinedSearch = searchList[index];
					filteredList.filter("" + refinedSearch + "");			
					filteredList = filteredList.getFList();
					System.out.println();
					filteredList.print();
					if (filteredList.size() == 0) {
						System.out.println("0 Tweets match search criteria.\nResetting search...\n");
													//Return to the start of the current loop
						index = 0;
						break; 			
					}
					System.out.println("All " + filteredList.size() + " Tweets containing:");
					System.out.print("[" + initialSearch + "] ");
					for (int i = 0; i < index+1; i++) {
						System.out.print(" ==>[" + searchList[i] + "]");
					}
													//If we've filtered out every item in the list

					System.out.println();
					System.out.println("Would you like to REFINE the search?\n");
					continue;
				}
				System.out.println("Would you like to perform a new search?\n");
				index = 0; //If the User declines refining the search, index should reset	
		} while(promptContinue(keyboard.nextLine()));
			System.out.println("Would you like to quit the program?\n");
	} while (!promptContinue(keyboard.nextLine()));
		keyboard.close();
		System.out.println("Program terminated by User");
		System.exit(0); //Gracefully close the program
	}
	
	public static boolean promptContinue(String s) {
		if (s.contains("y"))
			return true;
		return false;
	}

}
