package People;
public class PersonStudentTester 
{

	public static void main(String[] args) 
	{
		Person p1 = new Person("Bob", 30);
		p1.display();
		p1.getAge();
		p1.getName();
		
		Student s1 = new Student("Mary", 23, 2.3);
		s1.display();
		s1.getAge();
		s1.getName();
		s1.getGPA();
	}

}