
public class FuncArg 
{

	public static void main(String[] args) 
	{
		
		
		int[] arr1 = {1, 2};
		int[] arr2 = {3, -4, 52, 70};
		int[] arr3 = {-42, 67, 0, 32, 198};
		
		System.out.println("First Max: " + findMax (arr1));
		System.out.println("Second Max: " + findMax (arr2));
		System.out.println("Third Max: " + findMax (arr3));
	}
	
	public static int findMax (int[] arr)
	{
		int max = arr[0];
		for (int i = 0; i < arr.length; i++) 
		{
			if (arr[i] > max)
			{
				max = arr[i];
			}
		}
		return max;
	}


	

}
