
import java.util.Scanner;
public class Tweet {
	private double lat, lon;
	int year, month, day;
	String time, text;
	public Tweet(String s) 
	{
		Scanner scan = new Scanner(s); 
		lat = scan.nextDouble(); 
		lon = scan.nextDouble();
		scan.next();
		String date = scan.next(); 
		String[] d = date.split("-"); 
		year = Integer.parseInt(d[0]); 
		month = Integer.parseInt(d[1]); 
		day = Integer.parseInt(d[2]); 
		time = scan.next(); 
		text = scan.nextLine(); 
		scan.close(); 
	}
	
	public void print()
	{
		System.out.println("Text: " + text);
		System.out.println("Location: " + lon + "," + lat);
		System.out.println("Date: " + month + "/" + day + "/" + year);
		System.out.println("Time: " + time);
	}
	
	public boolean textContains(String searchTerm)
	{
		return !(text.indexOf(searchTerm) == -1);
	}
	

}
