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

    public void print() {

    	Node curr = head;

    		while(!isEmpty())
			{
				curr.value.print(); //Print the current Tweet
				curr = curr.next; //Advance curr
				if (curr == null)
					break;
			} 
//    	System.out.println("LIST EMPTY");
    } 
    
    public void prepend(Tweet t)
    {
    	Node newTweet = new Node(t, head);
    	head = newTweet;
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
        return head == null; //Does head point to an element in the list or just to null?       
    }
    
   
    // Deletes (circumvents around) any Tweet lacking the given keyword from the Tweet List.

	public void filter(String keyword) { 
		Node prev = null;
		Node curr = head;
		
//		if(isEmpty()) {
//			return;
//		}
		
		try
		{
			while (!isEmpty())
			{ //While the Tweet List is not empty			
					if (!(head.value.textContains(keyword))) { //If the head doesn't contain the keyword, advance it
						head = head.next;
					}
					else if (!(curr.value.textContains(keyword))) {   //If the keyword is not found in the 'value' data of the 'current' Node
						prev = curr.next;
					}
						
					prev.next = curr;
					curr = curr.next;
			} 
		} catch(NullPointerException e) {}

		return; //If we try to filter an empty list, return
			
    }
	
}

