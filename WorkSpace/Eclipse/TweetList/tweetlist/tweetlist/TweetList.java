/*
Name: Cody Ryan
Date: 11.15.18
Description: This class specifies the properties of a TweetList object, a linked list of Tweets. 
Sources Cited: Homework instructions, class slides
*/

package tweetlist;



public class TweetList {

    private class Node
    {
        Tweet value;   
        Node next = null;      
        
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
				curr.value.print(); //Print the current Tweet
				curr = curr.next; //Advance curr
    	}
    } 
    
    public void prepend(Tweet t)
    {
    	Node n = new Node(t, head);
    	head = n;//Reassign head to point to our new Node
    }
    
    // This method returns the number of nodes in the Linked List.
    public int size()    
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
    
   
    // Deletes (circumvents) any Tweet lacking the given filter from the Tweet List.
	public void filterDate(int y) //Filters by year								  
	{							  

		Node prev = null;
		Node curr = head;
	
		while (!isEmpty())
		{ //While the Tweet List is not empty			
			if (!(head.value.dateContains(y)))	
			{ 
				//If the head doesn't contain the keyword, advance it
				head = head.next;
			}
			else if (!(curr.value.dateContains(y))) 
			{   //If the keyword is not found in the 'value' data of the 'current' Node
				prev.next = curr.next; //skip around the node
			}

			prev = curr;
			curr = curr.next;
			if(curr == null) //If we reach the end of the list
				break;
		}
		return; //If we try to filter an empty list, return			
	}
    
	public void filterDate(int y, int m) //Filters by year, month
	{ 
		if (isEmpty())
			return;
	  	while (head.value.dateContains(y, m) == false)
	  		head = head.next;
	  		if (head == null)
	  			return;
	  	
	  	Node prev = null;
	  	Node curr = head;

	  	while(curr != null)
	  	{	
	  		if (curr.value.dateContains(y, m) == true)
	  		{	
	  			prev = curr;

	  		}
	  		else
	  		{
	  			prev.next = curr.next;
	  		}
			curr = curr.next;
	 			
	  	}
	  }
    
	public void filterDate(int y, int m, int d) //Filters by year, month, date
	  { 
		if (isEmpty())
			return;
	  	while (head.value.dateContains(y, m, d) == false)
	  		head = head.next;
	  		if (head == null)
	  			return;
	  	
	  	Node prev = null;
	  	Node curr = head;

	  	while(curr != null)
	  	{	
	  		if (curr.value.dateContains(y, m, d) == true)
	  		{	
	  			prev = curr;

	  		}
	  		else
	  		{
	  			prev.next = curr.next;
	  		}
			curr = curr.next;
	 			
	  	}
	  }
    
    public void filterLocation(int uLon, int uLat, double maxDist)
    { 
		if (isEmpty())
			return;
		
	  	while (head.value.locationContains(uLon, uLat, maxDist) == false)
	  		head = head.next;
	  	
	  	Node prev = null;
	  	Node curr = head;
	
	  	while(curr != null)
	  	{	
	  		if (curr.value.locationContains(uLon, uLat, maxDist) == true)
	  		{	
	  			prev = curr;
	
	  		}
	  		else
	  		{
	  			prev.next = curr.next;
	  		}
			curr = curr.next;
		 			
		  }	
	}
	
    public void filterText(String keyword) 
  { 
	if (isEmpty())
		return;
  	while (head.value.textContains(keyword) == false)
  		head = head.next;
  		if (head == null)
  			return;
  	
  	Node prev = null;
  	Node curr = head;

  	while(curr != null)
  	{	
  		if (curr.value.textContains(keyword) == true)
  		{	
  			prev = curr;

  		}
  		else
  		{
  			prev.next = curr.next;
  		}
		curr = curr.next;
 			
  	}
  }
}

