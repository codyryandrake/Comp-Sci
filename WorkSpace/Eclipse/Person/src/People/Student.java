package People;


public class Student extends Person
{
	private double gpa;
	
	public Student(String n, int a, double g) 
	{
		super(n, a);
		setGPA(g);
	}
	
	public void setName(String n)
	{
		super.setName(n);
	}
	
	public void setAge(int a)
	{
		super.setAge(a);
	}
	
	public void setGPA(double g)
	{
		gpa = g;
	}
	
//	public String getName()
//	{
//		return super.getName();
//	}
	
//	public int getAge()
//	{
//		return super.getAge();
//	}
	
	public double getGPA()
	{
		return gpa;
	}
	
	public void study()
	{
		if (gpa < 4.0)
		{
			gpa += 0.01;
			System.out.println("GPA increased for " + getName() + " by 0.01.");
		}
		else
		{
			gpa = 4.0;
			System.out.println("Max GPA of " + gpa +  " for " + getName() + " already reached!");
		}
	}
	
	@Override
	public void display()
	{
		System.out.println("STUDENT: ");
		super.display();
		System.out.println(" GPA: " + getGPA());
	}
}