/*
 * Name: Cody Ryan
 * Date: 09.23.18
 * Description: This class uses abs() method to define the absolute value of a double.
 * 				The dist() method calculates the abs() difference between two points.
 * 				The maxDistance() method compares matching indexes of arrays to return the 
 * 				highest pair value of each set of arrays. 
 * Sources Cited: Class slides, book
 */

public class Math {
	public static double abs(double x)
	{
		if (x < 0) x = -x; //Only in the case of negative doubles, x = negative x.
		return x; //Return the absolute value of x
	}
	
	public static double dist(double x, double y)
	{
		return abs(x-y); //Pass the difference of x and y to the abs() method
	}
	
	public static double maxDistance(double array1[], double array2[], int size)
	{
		double temp = dist(array1[0], array2[0]); //Let <temp> initialize as the smallest possible value
		for (int i = 0; i < size; i++) //Let <size> indicate how many times to loop
		{
			if (dist(array1[i], array2[i]) >= temp) //If a pair value is greater than <temp>
				temp = dist(array1[i], array2[i]); //<temp> becomes that pair value
		}
		return temp; //Return the final value update of <temp>
	}
}