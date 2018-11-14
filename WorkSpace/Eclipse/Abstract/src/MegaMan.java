
public class MegaMan extends videoGame{
	
	String genre;
	
	public MegaMan (String c, String v)
	{
		super(c, v);
		genre = "Side-Scroller";
	}
	
	public void display()
	{
		System.out.println("Game Company: " + companyName);
		System.out.println("Game Company: " + videoGameName);
		System.out.println("Game Company: " + genre);
	}


	public String returnGenre()
	{
		return genre;
	}
	
	

}
