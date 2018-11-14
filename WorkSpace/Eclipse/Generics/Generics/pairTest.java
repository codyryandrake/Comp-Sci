
public class pairTest {

	public static void main(String[] args) {
		Pair<String, String> sPair = new Pair<String, String>("43", "World!");
		
		String s1 = sPair.getFirst();
		String s2 = sPair.getSecond();
		
		System.out.println(s1 +  s2);
		
		//Pair<double, int> doubleIntPair = new Pair<double, int>(34.2, 5);
		Pair<Double, Integer> doubleIntPair = new Pair<Double, Integer>(34.2, 5);
		
		Pair<Character, Float> charFloPair = new Pair<Character, Float>('h', 54f);

	}

}
