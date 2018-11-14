

public class Student {
	private String name;
	private double gpa;
	
	public Student(String n, double g)
	{
		setName(n);
		setGPA(g);
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setGPA(double g)
	{
		gpa = g;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getGPA()
	{
		return gpa;
	}
	
	public void study()
	{
		if (gpa < 4.0)
		{
			gpa += 0.1;
			System.out.println("GPA increased for " + getName() + " by 0.1.");
		}
		else
		{
			gpa = 4.0;
			System.out.println("Max GPA of " + gpa +  " for " + getName() + " already reached!");
		}
	}
	
	public void display()
	{
		System.out.println(getName());
		System.out.println(getGPA());
	}
}