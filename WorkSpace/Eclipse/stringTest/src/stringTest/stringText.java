package stringTest;

public class stringText {

	public static void main(String[] args) {
//		try{
//		      System.out.print("Completed : 0  %");
//		      pause(100);
//		      for(int i=1; i<=100; i++){
//		        System.out.print("\b\b\b\b"+pad(i)+"%");
//		        pause(100);
//		      }
//		    }
//		    catch(Exception e){
//		    }
//		    System.out.print("\nDone!!!");
//		  }
//		  public static String pad(int i){
//		    String s = ""+i;
//		    if(s.length()==1){
//		      s += " ";
//		    }
//		    else if(s.length()==2){
//		      s += "  ";
//		    }
//		    return s;
		
		System.out.print("testing...");
		while (true){
		System.out.print("\b\b\b");
		System.out.print("xxx");
		pause(500);
		System.out.print("\b\b\b");
		System.out.print("...");
		}
		

		
		

	}
	public static void loadingEffect()
	{
		for (int i = 0; i < 3; i++)
		{
			pause(400);
			System.out.print(".");
		}
		pause(200);
		System.out.println();
	}
	
	public static void pause(int t)
	{
		try {
			Thread.sleep(t); //1000ms = 1s
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
