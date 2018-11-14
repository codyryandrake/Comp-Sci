

public class lengthDemo 
{

	public static void main(String[] args) 
	{
		Rectangle box = new Rectangle(10.0, 12.43);
		
		//Indicate what we are doing.
		System.out.println("Sending the value 10.0  "
						 + "to the setLength method.");
		
		//Call the box object's  setLength method.
		System.out.println(box.getArea());
		
		//Indicate we are done.
		System.out.println("Done.");
	}

}
