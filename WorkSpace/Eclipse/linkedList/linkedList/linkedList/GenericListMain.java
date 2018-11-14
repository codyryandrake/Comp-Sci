package linkedList;
//Using a linked list

public class GenericListMain {

	public static void main(String[] args) {
		GenericList list = new GenericList();
		
		list.prepend("Bob");
		list.prepend("Jim");
		list.prepend("is");
		list.prepend("name");
		list.prepend("my");
		list.prepend("Hello");
		
		System.out.println(list.toString());
		
		list.remove("is");
		
		System.out.println(list.toString());
		
		System.out.println("Does 'my' exist in the list: " + list.search("my"));
		System.out.println("Does 'Jim' exist in the list: " + list.search("Jim"));
		System.out.println("Does 'Coolio' exist in the list: " + list.search("Coolio"));
		
		GenericList anotherList = new GenericList();
		anotherList.prepend("Hiya!");
		System.out.println(anotherList.toString());
		anotherList.remove("Hiya!");
		System.out.println(anotherList.toString());
		
		System.out.println("Does 'Neato!' exist in the list: " + anotherList.search("Neato!"));
		
		System.out.println(list.get(2));
		//System.out.println(anotherList.get(2));
		

	}

}
