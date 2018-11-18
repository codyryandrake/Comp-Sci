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
    	Node newNode = new Node(t, head);
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
    
    @Override
    public String toString()
    {
    	String strBuilder = "";
    	Node curr = head;
    	while(curr != null)
    	{
    		strBuilder = strBuilder + curr.value;
    		curr = curr.next;
    	}
    	return strBuilder;
    }
    
   
    // Deletes (circumvents) any Tweet lacking the given keyword from the Tweet List.

	public void filterText(String keyword) 
	{ 
		Node prev = null;
		Node curr = head;
	
		while (!isEmpty())
		{ //While the Tweet List is not empty			
			if (!(head.value.textContains(keyword))) 	
			{ 
				//If the head doesn't contain the keyword, advance it
				head = head.next;
			}
			else if (!(curr.value.textContains(keyword))) 
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
	
	public void filterLocation(int lx, int ly, double maxDist)
	{
		Node prev = null;
		Node curr = head;
	
		while (!isEmpty())
		{ //While the Tweet List is not empty			
			if (!(head.value.locationContains(lx, ly, maxDist))) 	
			{ 
				//If the head doesn't contain the keyword, advance it
				head = head.next;
			}
			else if (!(curr.value.locationContains(lx, ly, maxDist))) 
			{   //If the keyword is not found in the 'value' data of the 'current' Node
				prev = curr.next; //skip around the node
			}
				
			
			prev = curr;
			curr = curr.next;
			if(curr == null) //If we reach the end of the list
				break;
		}
		return; //If we try to filter an empty list, return			
	}
	
	
	
}

