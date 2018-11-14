public class Rectangle {
	private double length;
	private double width;
		
	public Rectangle(double len, double w)
	{
		setLength(len);
		setWidth(w);
	}
		 
	public void setLength(double len)
	{
	   if(len >= 0)
		   length = len;
	   else
		   System.out.println("You can't have a rectangle with negative length.");
	}
		
	public void setWidth(double w)
	{
		if(w >= 0)
			width = w;
		else
			System.out.println("You can't have a rectangle with negative width.");
	}
	
	public double getLength()
	{
		return length;
	}
	
	public double getWidth()
	{
		return width;
	}
	
	public double getArea()
	{
		return length * width;
	}
	
	public Rectangle copy() 
	{
		return new Rectangle(length, width);
	}
	
	public boolean equals(Rectangle r)
	{
		if (r.length == length && r.width == width)
		{
			return true;
		}
		else
			return false;
	}
}
