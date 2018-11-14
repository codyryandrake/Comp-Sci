
public class ForLoop {

	public static void main(String[] args) {
		int sum = 0;
		for (int i = 2; i <=99; i++)
		{
			
			if (i%2 == 0)
			{
				System.out.println(i);
				sum += i;
			}
		
			
		}
		System.out.println("\nSUM: " + sum);

	}

}
