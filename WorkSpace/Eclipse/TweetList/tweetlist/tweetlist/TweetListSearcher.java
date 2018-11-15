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
		
		System.out.println("List created!\n");
		System.out.println("Enter a word or phrase to search for:   ");
		String search = keyboard.nextLine();
		tList.filter(search);
		tList.print();
		
		keyboard.close();

	}

}
