/*
Name: Cody Ryan
Date: 11.28.18
Description: This class allows for printing animated strings to the console. 
				By animating prints to the console, the program may be perceived
				as more interactive and responsive. 
Sources Cited: Homework instructions, class slides
*/
package tweetlist;

public class Animate {
	
	static boolean enable = false;
	

	public static void pause(int time) { //A method to pause the thread for <t> time.
		if (enable)
		{
			try {
				Thread.sleep(time); //1000ms = 1s
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void TextDraw(String string, int time) { //Method for animating strings
		for (int i = 0; i < string.length(); i++) 
		{ 					 
			System.out.print(string.charAt(i)); //Outputs the contents of any string one character
			pause(time);  //at a time with a pause() in between
		}
	}

}
