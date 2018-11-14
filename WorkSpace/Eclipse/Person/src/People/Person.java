package People;
public class Person {
	protected String name;
	protected int age;
	
	public Person(String n, int a)
	{
		setName(n);
		setAge(a);
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setAge(int a)
	{
		age = a;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void display()
	{
		System.out.println("PERSON: ");
		System.out.println(" name: " + getName());
		System.out.println(" age: " + getAge());
	}
}