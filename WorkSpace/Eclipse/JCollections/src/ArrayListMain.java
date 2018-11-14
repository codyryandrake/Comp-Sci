import java.util.ArrayList;

public class ArrayListMain {

	public static void main(String[] args) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		numbers.add(21);
		numbers.add(4);
		numbers.add(100);

		
//		System.out.println(numbers.get(0));
//		System.out.println(numbers.get(1));
//		System.out.println(numbers.get(2));
		

		for (int i = 0; i < numbers.size(); i++) {
			System.out.println(numbers.get(i));
		}
		System.out.println("\nSize of 'numbers' arrayList: " + numbers.size() + "\n");
		
		numbers.add(0, 53);
		for (int i = 0; i < numbers.size(); i++) {
			System.out.println(numbers.get(i));
		}
		System.out.println("\nSize of 'numbers' arrayList: " + numbers.size() + "\n");
		
		numbers.remove(0);
		System.out.println("\nIndex 0 removed.");
		for (int i = 0; i < numbers.size(); i++) {
			System.out.println(numbers.get(i));
		}
		
		System.out.println("\nIndex 0 now contains the value of 3.");
		numbers.set(0, 3);
		for (int i = 0; i < numbers.size(); i++) {
			System.out.println(numbers.get(i));
		}
		
		numbers.clear();
		System.out.println("\n'numbers' arrayList cleared...");
		for (int i = 0; i < numbers.size(); i++) {
			System.out.println(numbers.get(i));
		}
		
		
		
	}

}
