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
    		strBuilder = strBuilder + curr.value.toString() + " \n";
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
    
    public void filter(String keyword)
    {
 
    }
}
