
public class TwoDimArray {

	public static void main(String[] args) {
		int rows = 12;
		int cols = 20;
		char [] [] arr = new char [rows] [cols];
		for (int i = 0; i < 12; i++ )
		{
			if ( (i%2) == 0) 
			{
				for (int i1 = 0; i1 < 20; i1++ )
				{
					if (i1%2 == 0)
						{
						arr [i][i1] = 'x';
						System.out.print("x");
						}
					else
					{
						arr [i][i1] = '0';
						System.out.print("0");
					}
					
					
				}
				System.out.println();
			}
			else
			{
				for (int i2 = 0; i2 < 20; i2++ )
				{
					if (i2%2 == 0)
					{
					arr [i][i2] = '0';
					System.out.print("0");
					}
				else
				{
					arr [i][i2] = 'x';
					System.out.print("x");
				}
					
					
				}
				System.out.println();
				
			}
			
		}
		System.out.println();
		for (int r = 0; r < 12; r++)
		{
			for (int c = 0; c < 20; c++)
			{
				System.out.print(arr[r][c]);
			}
		System.out.println();
		}
	}
	

}
