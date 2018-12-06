
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner; 
public class TweetListSearcher 
{
	public static void main(String[] args)
	{ 
		TweetList list = new TweetList();
		System.out.println("Please enter a file name");
		Scanner filename = new Scanner(System.in);
		String fname = "smalltweetdata.txt";
		try {
				FileReader file = new FileReader(fname);
				BufferedReader read = new BufferedReader(file); 
				System.out.println("Enter a word or phrase to search for:  ");
				Scanner keyboard = new Scanner(System.in);
				String search = keyboard.nextLine();
				String line;
				while((line = read.readLine()) != null)
				{
					Tweet t = new Tweet(line);
					//t.print();
					if (t.textContains(search))
					{
						list.prepend(t); 
						//System.out.println(list);
					}
					
					

				}
				read.close();
				keyboard.close();
				list.print();
				
			}
		catch (FileNotFoundException e) 
		{
			System.out.println("The file " + fname + " has not been found.");
		}
		catch (IOException e) 
		{
			System.out.println("An error occurred while reading " + fname + ".");
		}
		
		filename.close();
	}
} 