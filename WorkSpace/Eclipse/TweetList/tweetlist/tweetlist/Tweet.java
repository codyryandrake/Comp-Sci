/*
Name: Cody Ryan
Date: 11.15.18
Description: This class specifies the properties of a Tweet object.
Sources Cited: Homework instructions, class slides
*/


package tweetlist;

import java.awt.Point;
import java.util.Scanner;

public class Tweet {
	private double lat, lon;
	int year, month, day;
	String time, text;
	static Scanner keyboard = new Scanner(System.in);
	
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
	
	public boolean textContains(String s)
	{
		return !(text.indexOf(s) == -1);
	}
	
	public boolean locationContains(int lx, int ly, double maxDist)
	{		
		double dist = Point.distance(lat, lon, lx, ly);
		return (dist <= maxDist); //Returns true for all tweets within a distance from a specified location.
	}
	
	
	
	
	

}
