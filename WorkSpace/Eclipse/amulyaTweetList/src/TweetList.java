

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
    	Node curr = head; 
    	while (curr != null)
    	{ 
    		curr.value.print(); 
    		curr = curr.next; 
    	}
    }
    
    public void prepend(Tweet newValue)
    {
    	Node n = new Node(newValue, head); 
    	head = n; 
    }
    
    public int size()
    {
    	int length = 0; 
    	Node curr = head; 
    	while (curr != null) 
    	{ 
    		curr = curr.next; 
    		length++; 
    	}
    	
    	System.out.println(length);
    	return length; 
    }
    
    public void filter(String keyword)
    {
	  while(head != null)
	  {
		  if(head.value.textContains(keyword) == false)
			  head = head.next;
		  else
			  break;
	  }
    	
    	Node curr = head; 
    	Node prev = head;
    	while (curr != null) 
    	{ 
    		if (curr.value.textContains(keyword))
    			prev = curr; 
    		else 
    			prev.next = curr.next; 
    		curr = curr.next; 
    		
    	}
    }
}
