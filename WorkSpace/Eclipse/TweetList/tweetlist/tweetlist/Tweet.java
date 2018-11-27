/*
Name: Cody Ryan
Date: 11.15.18
Description: This class specifies the properties of a Tweet object.
Sources Cited: Homework instructions, class slides
*/


package tweetlist;

import java.awt.Point;
import java.util.Scanner;

public class Tweet 
{
	private double lat, lon;
	private Double dist = null;
	private int year, month, day;
	private int hour, minute, second;
	private String date, time, text;
	
	public Tweet(String s)
	{
		Scanner scan = new Scanner(s);
		lat = scan.nextDouble(); //Scan in Latitude

		lon = scan.nextDouble(); //Scan in Longitude

		scan.next(); //Scan in the '6' 
		
		date = scan.next(); //Scan in the date string
			String[] d = date.split("-");
			year =  Integer.parseInt(d[0]);
			month = Integer.parseInt(d[1]);
			day =   Integer.parseInt(d[2]);				
		time = scan.next(); //Scan in the time string		
			String[] t = time.split(":");
			hour =   Integer.parseInt(t[0]);
			minute = Integer.parseInt(t[1]);
			second = Integer.parseInt(t[2]);
		text = scan.nextLine().trim(); //Scan in text field
		
		scan.close();
	}
	
	public void print() //Print a formatted representation of the entire Tweet
	{
		System.out.print("\n\t-----------------------------");
		System.out.print(
				"\n\t||Text: " + text
			  + "\n\t||Location: (" + lat + ", " + lon + ")");	if(dist != null)
				  											System.out.print("\n"
			  + "\t||Distance From Query: \t[" + dist + "]");
		System.out.println(
			  "\n\t||Date: " + date
			+ "\n\t||Time: " + time);
			
		System.out.print("\t-----------------------------\n\n");

	}
	
	public boolean textContains(String s)
	{
		//If a tweet's text value contains the User-searched keyword/phrase
		if (!(text.indexOf(s) == -1))
			return true; //return true	
		return false; //Otherwise return false			
	}
	
	
	public boolean dateContains(int y, int m, int d)
	{//Check if each section of 'date' matches the User's query
		if(y == year) //year matches
		{
			if(m == month) //month matches
			{
				if(d == day) 
					return true; //All parameters match
				else
					if(d == -1) //day was skipped
						return true;
				return false; //day mismatch
			}
			else
				if(m == -1) //month was skipped
					return true;
			return false; //month mismatch
		}
		else
			if(y == -1) //year was skipped
				return true;
		return false; //year mismatch
	}
	
	
	public boolean locationContains(double uLon, double uLat, double maxDist)
	{//Compare the distance between all tweets and the desired coordinates		
		dist = Point.distance(lat, lon, uLon, uLat); //Search-radius
		return (dist <= maxDist); //Returns true for all tweets within a 								  
	}							  //search-radius.
	
	
	public boolean timeContains(int hr, int min, int sec)
	{//Check if each section of 'time' matches the User's query
		if(hr == hour) //hour matches
		{
			if(min == minute) //minute matches
			{
				if(sec == second) 
					return true; //All parameters match
				else
					if(sec == -1) //sec was skipped
						return true;
				return false; //sec mismatch
			}
			else
				if(min == -1) //min was skipped
					return true;
			return false; //min mismatch
		}
		else
			if(hr == -1) //hour was skipped
				return true;
		return false; //hour mismatch
	}

}
