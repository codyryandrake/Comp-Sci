import java.io.*;
import java.util.*;

public class MultiCatch {
   public static void main(String[] args) {
      int number;
      
      try {
         File file = new File("Numbers.txt");
         Scanner inputFile = new Scanner(file);
         while (inputFile.hasNext()) {
            number = inputFile.nextInt();
            System.out.println(number);
            inputFile.close();
         }
      }
         catch (FileNotFoundException | InputMismatchException e) {
        	 System.out.println("File not found or invalid content found in file.");
         }
//         catch (InputMismatchException e) {
//        	 System.out.println("Cannot convert from object type to int");
//         }
   }
}