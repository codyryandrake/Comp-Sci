package tweetlist;

public class TweetList {
    private class Node
    {
        Tweet value;   
        Node next;      
        
        // Constructor for a node.
        Node(Tweet val, Node n)
        {
            value = val;
            next = n;
        } 
        
    }	
	 
    private Node head;
    
    public TweetList()
    {
    	head = null;
    }
    
    public void print()
    {
    	String strBuilder = "";
    	Node curr = head;
    	while(curr != null) {
    		curr.value.print();
    		curr = curr.next;
    	}
    	System.out.println(strBuilder);
    }
    
    public void prepend(Tweet newValue)
    {
    	Node newNode = new Node(newValue, head);
    	head = newNode;
    }
    

    public int size()    // This method returns the number of nodes in the Linked List.
    {
    	int count = 0;
    	Node p = head; //'p' now points to the same address as 'head'.
    	while (p != null) { //While there is an element in 'p'
    		count++;
    		p = p.next;
    	}
    	return count;
    }
    
    // Checks if the Tweet List is empty.
    public boolean isEmpty()
    {        
        return (head == null); //Does head point to an element in the list or just to null?       
    }
    
   
    // Removes any Tweet lacking the given keyword from the Tweet List.

	public void filter(String keyword)
    {
		try {
			Node c = head; //Initialize our 'current' Node to point to the same Node as 'head'
			Node p = null; //Our 'previous' Node
			
			while (!isEmpty()) { //While the Tweet List is not empty
				if (head.value.textContains(keyword))
					head = head.next;
				
				if ((c.value.textContains(keyword))) {   //If the keyword is not found in the 'value' data of the 'current' Node
					p.next = c.next;
					System.out.println("KEEP");
				}
				else
					System.out.println("DONT KEEP");
				
				p = c;
				c = c.next;
			}
		} catch (NullPointerException e) {

		}
    }
}






//
//Node curr = head;
//Node prev = null;
//
//if(!(head.value.textContains(keyword)))
//	System.out.println("head.next");
//	head = head.next;
//	
//
//while (curr != null) {
//	System.out.println("Curr is valid!");
//	if(curr.value.textContains(keyword)) 
//		System.out.println("Tweet kept.");
//	else 
//			try {	    			
//				prev = curr.next; //Skip around the invalid Node
//				System.out.println("Tweet deleted.");
//			} catch (NullPointerException e) {
//				System.out.println("NULL");
//			}
//		
//	System.out.println("Prev = Curr");	
//	prev = curr;
//	System.out.println("Curr.next");
//	curr = curr.next;
//
//}
//

