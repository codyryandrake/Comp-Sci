package tweetlist;

import java.util.Scanner;

public class Tweet {
	private double lat, lon;
	int year, month, day;
	String time, text;
	
	public Tweet(String s)
	{
		Scanner scan = new Scanner(s);
		lat = scan.nextDouble(); //Scan in Latitude
		//System.out.println("Latitude: " + lat);
		lon = scan.nextDouble(); //Scan in Longitude
		//System.out.println("Longitude: " + lon);
		scan.next(); //Scan in the '6' 
		
		String date = scan.next(); //Scan in the date string
		String[] d = date.split("-");
		year = Integer.parseInt(d[0]);
		month = Integer.parseInt(d[1]);
		day = Integer.parseInt(d[2]);		
		//System.out.printf("Date: " + year + "." + month + "." + day);
		
		time = scan.next(); //Scan in the time string
		//System.out.println("Time: " + time);
		
		text = scan.nextLine();
		//System.out.println("Text: " + text);
		
		scan.close();
	}
	
	public void print() //Print a formatted representation of the entire Tweet
	{
		System.out.println(
			  "\nLatitude: " + lat
			+ "\nLongitude: " + lon
			+ "\nDate: " + day + "/" + month + "/" + year
			+ "\nTime: " + time
			+ "\nText: " + text);
	}
	
	public boolean textContains(String searchTerm)
	{
		return !(text.indexOf(searchTerm) == -1);
	}
}
