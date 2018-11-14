import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileNotFound {
	public static void main (String [] args) {
		try { //Code you will attempt to run. This code might generate an error.
			File file = new File ("MyFile.txt");
			Scanner inputFile = new Scanner(file);
			System.out.println("File found!");
		}
		catch (FileNotFoundException e) { //This block will run if an error is passed to it.
			System.out.println("File not found."); //This will output INSTEAD of crashing the program.
		}
	}

}
