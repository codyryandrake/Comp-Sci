
public class ketchupCalc 
{
	private String manufacturer;
	
	private double priceHeinz;
	private double priceHunts;
	private double priceDelmonte;
	private double priceSTB;
	
	private double profitHeinz = 0.0;
	private double profitHunts = 0.0;
	private double profitDelmonte = 0.0;
	private double profitSTB = 0.0;
	
	public ketchupCalc (String man, double pHeinz, double pHunts,
						double pDelmonte, double pSTB)
	{
		man = manufacturer;
		pHeinz = priceHeinz;
		pHunts = priceHunts;
		pDelmonte = priceDelmonte;
		pSTB = priceSTB;
		
		switch (manufacturer)
		{
		case "heinz":
			profitHeinz += priceHeinz;
			break;
		case "hunts":
			profitHunts += priceHunts;
			break;
		case "delmonte":
			profitDelmonte += priceDelmonte;
			break;
		case "stb":
			profitSTB += priceSTB;
			break;
		}
	}
	
	public double getHeinzP()
	{
		return profitHeinz;
	}
	
	public double getHuntsP()
	{
		return profitHunts;
	}
	
	public double getDelmonteP()
	{
		return profitDelmonte;
	}
	
	public double getSTBP()
	{
		return profitSTB;
	}
}
