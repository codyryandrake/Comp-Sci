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
	private Double dist = null;
	int year, month, day;
	String time, text;
	static Scanner keyboard = new Scanner(System.in);
	
	public Tweet(String s)
	{
		Scanner scan = new Scanner(s);
		lat = scan.nextDouble(); //Scan in Latitude

		lon = scan.nextDouble(); //Scan in Longitude

		scan.next(); //Scan in the '6' 
		
		String date = scan.next(); //Scan in the date string
			String[] d = date.split("-");
			year =  Integer.parseInt(d[0]);
			month = Integer.parseInt(d[1]);
			day =   Integer.parseInt(d[2]);				
		time = scan.next(); //Scan in the time string		
		text = scan.nextLine();
		
		scan.close();
	}
	
	public void print() //Print a formatted representation of the entire Tweet
	{
		System.out.print(
			  "\n\t\t\tLatitude: " + lat
			+ "\n\t\t\tLongitude: " + lon);
		if(dist != null)
			System.out.print(
			  "\n\t\t\tDistance From Query: " + dist);
		System.out.println(
			  "\n\t\t\tDate: " + day + "/" + month + "/" + year
			+ "\n\t\t\tTime: " + time
			+ "\n\t\t\tText: " + text);

	}
	
	public boolean textContains(String s)
	{
		if (text.contains(s)) //If the phrase is found within
		{
			System.out.println("\t\t\t\t\tTERM FOUND WITHIN");
			return true;
		}
		
		return false; //Otherwise return false
			
	}
	
	
	public boolean locationContains(int lx, int ly, double maxDist)
	{		
		dist = Point.distance(lat, lon, lx, ly);
		return (dist <= maxDist); //Returns true for all tweets within a distance from a specified location.
	}
	
	public boolean dateContains(int m)
	{
		System.out.println(day);
		return (m == month);
	}
	
	public boolean dateContains(int m, int d)
	{
		return (m== month && d == day);
	}
	
	public boolean dateContains(int m, int d, int y)
	{
		return (m== day && d == month && y == year);
	}
	

	
	
	
	

}
