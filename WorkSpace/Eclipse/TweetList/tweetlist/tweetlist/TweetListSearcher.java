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
		String line;
		Tweet t;
		TweetList tList = new TweetList();
		try {
			FileReader file = new FileReader(fName);
			BufferedReader read = new BufferedReader(file);

			while ((line = read.readLine()) != null) {
				t = new Tweet(line);
				tList.prepend(t);
			}
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file " + fName + " has not been found.");
		} catch(IOException e) {
			System.out.println("An error occurred while reading " + fName + ".");
		}
		
		while(true) {
			System.out.println("List created!\n");
			System.out.println("Enter a word or phrase to search for:   \r");
			String search = keyboard.nextLine();
			TweetList filteredList = tList.filter(search);
			filteredList.print();
			System.out.println("\n\nAll Tweets containing [" + search + "]");
	
			
			System.out.println("\n\nPlease enter a word or phrase to further refine your search:    \r");
			String search2 = keyboard.nextLine();
			filteredList = filteredList.filter(search2);
			filteredList.print();
			System.out.println("\n\nAll Tweets containing [" + search + "] [" + search2 + "]");

			System.out.println("Would you like to start a new search?\r");
			String choice = keyboard.nextLine(); 			
			if(choice.toLowerCase().contains("y"))
				System.out.println("\n\nRebuilding original list...");
			else {
				System.out.println("Program terminated by User.");
				break;
			}
		}
		keyboard.close();
	}


}
