public class SuperHero 
{
	private String name;
	private int numLivesSaved;
	private String sideKickName;
	
	public SuperHero (String n, int num, String sk)
	{
		setName(n);
		setNumLivesSaved(num);
		setSideKickName(sk);
	}
	
	public void setName(String n) 
	{
		name = n;
	}
	
	public void setNumLivesSaved(int n) 
	{
		numLivesSaved = n;
	}
	
	public void setSideKickName(String sk) 
	{
		sideKickName = sk;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public int getNumLivesSaved() 
	{
		return numLivesSaved;
	}
	
	public String getSideKickName() 
	{
		return sideKickName;
	}
	
	public void printInfo()
	{
		System.out.println(getName());
		System.out.println(getNumLivesSaved());
		System.out.println(getSideKickName());
	}
}