
public class MegaMan extends abstraction{
	
	String genre;
	
	public MegaMan (String c, String v, String g)
	{
		super(c, v);
		g = genre;
	}
	
	public void display()
	{
		System.out.println("Game Company: " + genre);
	}


	public String returnGenre()
	{
		return genre;
	}
	
	

}
