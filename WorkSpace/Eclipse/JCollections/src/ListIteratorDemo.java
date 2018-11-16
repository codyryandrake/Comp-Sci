import java.util.ArrayList;
import java.util.ListIterator;

public class ListIteratorDemo {

	public static void main(String[] args) {
		String [] names = {"Anna", "Bob", "Jim", "Debby"};
		
		ArrayList<String> nameList = new ArrayList<String>();
		
		for(int i = 0; i < names.length; i++)
			nameList.add(names[i]);
		
		ListIterator<String> listIter = nameList.listIterator();
		
		System.out.println(listIter.next());
		System.out.println(listIter.next());
		System.out.println(listIter.next());
		
		listIter.add("Sarah");
		System.out.println(listIter.next());
		//System.out.println(listIter.next());
		System.out.println();
		
		while(listIter.hasPrevious())
		{
			System.out.println(listIter.previous());
		}
		//listIter.previous();
	}

}