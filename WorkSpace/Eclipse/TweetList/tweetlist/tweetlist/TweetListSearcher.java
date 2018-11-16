package tweetlist;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TweetListSearcher {

	public static void main(String[] args) {
		
		String fName = "smalltweetdata.txt";
		//String fName = "tweetdata.txt";
		Scanner keyboard = new Scanner(System.in);
		String[] searchList = new String[100]; //create list of searched terms
//		String prompt = keyboard.nextLine();
		String line;
		Tweet t;
		TweetList tList = new TweetList();
		TweetList filteredList;

		int index = 0;
		try {
			FileReader file = new FileReader(fName);
			BufferedReader read = new BufferedReader(file);

			while ((line = read.readLine()) != null) {
				t = new Tweet(line);
				tList.prepend(t);
			}
			System.out.println("List created!\n");
			System.out.println("The Tweet database contains " + tList.size() + " tweets.");
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file " + fName + " has not been found.");
		} catch(IOException e) {
			System.out.println("An error occurred while reading " + fName + ".");
		}
		

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
