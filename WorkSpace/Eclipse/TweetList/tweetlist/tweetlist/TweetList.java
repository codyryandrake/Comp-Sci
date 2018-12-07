/*
Name: Cody Ryan
Date: 11.28.18
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
				curr.value.print(); 										//Print the current Tweet
				curr = curr.next;											//Advance curr
    	}
    } 
    
    public void prepend(Tweet t)
    {
    	Node n = new Node(t, head);
    	head = n;															//Reassign head to point to our new Node
    }
    
    //This method iterates through a TweetList
    //and prepends all tweet nodes to a new list
    public TweetList clone()
    {
    	Node curr = head;
    	TweetList newList = new TweetList();
    	while(curr != null)
    	{
    		newList.prepend(curr.value);
    		curr = curr.next;
    	}
    	return newList;
    }
    
    // This method returns the number of nodes in the Linked List.
    public int size()    
    {
    	int count = 0;
    	Node p = head; 														//'p' now points to the same address as 'head'.
    	while (p != null) { 												//While there is an element in 'p'
    		count++;
    		p = p.next;
    	}
    	return count;
    }
    
    // Checks if the Tweet List is empty. 
    public boolean isEmpty()
    { //Does head point to an element in the list or just to null?              
        return (head == null); 
    }
    
   

    // Deletes (circumvents) any Tweet lacking the given filter from the Tweet List.
    public void filterText(String keyword) 
  { 
      	Node prev = head;
      	Node curr = head;
	while(!isEmpty())
	{	//If we find the keyword in head's text field
	  	if (head.value.textContains(keyword) == true)
	  		break;
	  	else
			head = head.next;
	}	
	//Pick up where head leaves off
	while(curr != null)
	{
  		if (curr.value.textContains(keyword) == true) 					//If we find the keyword in curr's text field	
  			prev = curr; 												//Shift our prev pointer up to match curr  				
  		else 															//If the keyword is NOT found in curr's text field
  			prev.next = curr.next; 										//Point our prev node past the curr node
  		curr = curr.next;												//Advance our curr to the next node
	}  		
  	 
  }
    
    
	public void filterDate(int y, int m, int d) 
	{ 
      	Node prev = head;
      	Node curr = head;
	while(!isEmpty())
	{	//If we find the keyword in head's text field
	  	if (head.value.dateContains(y, m, d) == true)
	  		break;
	  	else
			head = head.next;
	}	
	//Pick up where head leaves off
	while(curr != null)
	{
  		if (curr.value.dateContains(y, m, d) == true) 					//If we find the date in curr's date field	
  			prev = curr; 												//Shift our prev pointer up to match curr  				
  		else 															//If the date is NOT found in curr's date field
  			prev.next = curr.next; 										//Point our prev node around the curr node
  		curr = curr.next;												//Advance our curr to the next node
	}  		
  	 
  }
	
	
	public void filterLocation(double uLon, double uLat, double maxDist)
	{ 
      	Node prev = head;
      	Node curr = head;
	while(!isEmpty())
	{	//If we find the keyword in head's text field
	  	if (head.value.locationContains(uLon, uLat, maxDist) == true)
	  		break;
	  	else
			head = head.next;
	}	
	//Pick up where head leaves off
	while(curr != null)
	{
  		if (curr.value.locationContains(uLon, uLat, maxDist) == true) 	//If the tweet's location is within the search radius	
  			prev = curr; 												//Shift our prev pointer up to match curr  				
  		else 															//If the tweet's location is outside the search radius
  			prev.next = curr.next; 										//Point our prev node past the curr node
  		curr = curr.next;												//Advance our curr to the next node
	}  		
  	 
  }
	
	
	//Filters by hour, minute, second
	public void filterTime(int hr, int min, int sec) 
	{ 
      	Node prev = head;
      	Node curr = head;
		while(!isEmpty())
		{//If we find the time in head's time field
		  	if (head.value.timeContains(hr, min, sec) == true) 
		  		break;
		  	else
				head = head.next;
		}	
		//Pick up where head leaves off
		while(curr != null)
		{
	  		if (curr.value.timeContains(hr, min, sec) == true) 			//If we find the time in curr's time field	
	  			prev = curr; 											//Shift our prev pointer up to match curr  				
	  		else 														//If the time is NOT found in curr's time field
	  			prev.next = curr.next; 									//Point our prev node past the curr node	  																	
	  		curr = curr.next;											//Advance our curr to the next node
		}  		
  	 
  }
	
    
	

}

