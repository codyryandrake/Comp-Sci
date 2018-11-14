

public class Rectangle

{
	private double length;
	private double width;
	

	public Rectangle (double len, double wid) //Constructor
	{
		setLength(len);
		setWidth(wid);
			
	}
	
	public void setLength(double len)
	{
		if (len >= 0)
			length = len;
		else
		{
			System.out.println("You can't have a rectangle with negative length...");
			System.out.println("Length defaulted to 0.");
		}

	}
	
	public void setWidth(double wid)
	{
		if (wid >= 0)
			width = wid;
		else
		{
			System.out.println("You can't have a rectangle with negative width...");
			System.out.println("Width defaulted to 0.");
		}

	}
	
	public double getLength()

	{
		return length;
	}
	

	public double getWidth()//Accessor method
	{
		return width;
	}

	/**
	 * The getWidth method returns a value
	 * in the width field.
	 * @param return The value in the width field.
	 */
	
	

	public double getArea() //Accessor method
	{
		return length * width;
	}


	/**
	 * The getArea method returns the product
	 * of the values in the length and width fields.
	 * @param return the product of the length and 
	 * width values.
	 */
	
	

}
