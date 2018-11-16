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

	private static TweetList filteredList = new TweetList();	
	 
    private Node head;
    
    public TweetList()
    {
    	head = null;
    }

    public void print()
    {
    	Node curr = head;
    	while(!isEmpty()) {
    		curr.value.print(); //Print the current Tweet
    		curr = curr.next; //Advance curr
    	}
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
		TweetList fList = new TweetList();
		Tweet t;
		try {
			Node c = head; //Initialize our 'current' Node to point to the same Node as 'head'
			Node p = null; //Our 'previous' Node
			while (!isEmpty()) { //While the Tweet List is not empty
				if ((c.value.textContains(keyword))) {   //If the keyword is found in the 'value' data of the 'current' Node
					t = c.value; //Create a Tweet from the currenty Node's value
					fList.prepend(t); //Append the new Tweet to a filtered list
				}			
				p = c;
				c = c.next;
			}
		} catch (NullPointerException e) {
			//System.out.println("Null Pointer Exception occurred.");
		}
		setFList(fList); //Pass the filtered list to our pre-designated 'filteredList' 
    }
	
	/*Setter and getter for the filtered list */
	public void setFList(TweetList T) {
		filteredList = T;
	}
	
	public TweetList getFList() {
		return filteredList;
	}
}

