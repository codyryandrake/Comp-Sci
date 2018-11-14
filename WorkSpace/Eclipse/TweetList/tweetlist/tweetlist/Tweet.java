package tweetlist;

import java.util.Scanner;

public class Tweet {
	private double lat, lon;
	int year, month, day;
	String time, text;
	
	public Tweet(String s)
	{
		Scanner scan = new Scanner(s);
		lat = scan.nextDouble();
		System.out.println("Latitude: " + lat);
		lon = scan.nextDouble();
		System.out.println("Longitude: " + lon);
		if(scan.nextInt() == 6) {
			String date = scan.next();
			String[] d = date.split("-");
			year = Integer.parseInt(d[0]);
			month = Integer.parseInt(d[1]);
			day = Integer.parseInt(d[2]);
			System.out.printf("Date: " + year + "." + month + "." + day);
			String time = scan.next();
			System.out.println("Time: " + time);
			String text = scan.next();
			System.out.println("Text: " + text);
		}
		scan.close();
	}
	
	public void print() //Print a formatted representation of the entire Tweet
	{
		System.out.println(
				"Latitude: " + lat
			+ "Longitude: " + lon
			+ "Date: " + year + "." + month + "." + day
			+ "Time: " + time
			+ "Text: " + text);
	}
	
	public boolean textContains(String searchTerm)
	{
		return !(text.indexOf(searchTerm) == -1);
	}
}
