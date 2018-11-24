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
	int hour, minute, second;
	String date, time, text;
	static Scanner keyboard = new Scanner(System.in);
	
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
		text = scan.nextLine();
		
		scan.close();
	}
	
	public void print() //Print a formatted representation of the entire Tweet
	{
		System.out.print("\n\t-----------------------------");
		System.out.print(
			  "\n\t||Latitude: " + lat
			+ "\n\t||Longitude: " + lon);
		if(dist != null)
			System.out.print(
			  "\n\t||Distance From Query: " + dist);
		System.out.println(
			  "\n\t||Date: " + date
			+ "year" + year + "month" + month + "day" + day
			+ "\n\t||Time: " + time
			+ "hour" + hour + "minute" + minute + "second" + second
			+ "\n\t||Text: " + text);
		System.out.print("\t-----------------------------\n\n");

	}
	
	public boolean textContains(String s)
	{
		//if (text.contains(s)) //If the phrase is found within
		if (!(text.indexOf(s) == -1))
		{
			//System.out.println("\t\t\t\t\tTERM FOUND WITHIN");
			return true;
		}
		
		return false; //Otherwise return false
			
	}
	
	public boolean locationContains(int lx, int ly, double maxDist)
	{		
		dist = Point.distance(lat, lon, lx, ly);
		return (dist <= maxDist); //Returns true for all tweets within a distance from a specified location.
	}
	
	public boolean timeContains(int hr, int min, int sec)
	{
		if(hr == hour) //hour matches
		{
//			System.out.println("\nHour matches.  ");
			if(min == minute) //minute matches
			{
//				System.out.println("Minute matches.  ");
				if(sec == second) 
				{
//					System.out.println("Second matches.\n");
					return true; //All parameters match
				}
				else
					{
					if(sec == -1) //sec was skipped
						return true;
					}
				return false;
			}
			else
			{
				if(min == -1) //min was skipped
					return true;
			}
			return false;
		}
		else
		{
			if(hr == -1) //hour was skipped
				return true;
		}
		return false;
	}
	
	public boolean dateContains(int y, int m, int d)
	{
		if(y == year) //hour matches
		{
//			System.out.println("\nYear matches.  ");
			if(m == month) //minute matches
			{
//				System.out.println("Month matches.  ");
				if(d == day) 
				{
//					System.out.println("Day matches.\n");
					return true; //All parameters match
				}
				else
					{
					if(d == -1) //sec was skipped
						return true;
					}
				return false;
			}
			else
			{
				if(m == -1) //min was skipped
					return true;
			}
			return false;
		}
		else
		{
			if(y == -1) //hour was skipped
				return true;
		}
		return false;
	}
	

	
	
	
	

}
