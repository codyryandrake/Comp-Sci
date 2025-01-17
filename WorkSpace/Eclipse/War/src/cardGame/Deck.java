/*
 * Name: Cody Ryan
 * Date: 10.18.18
 * Description: The Deck class handles creating a new deck, returning cards in the deck
 * 				and shuffling the deck upon command. It It also contains a displayDeck() method.
 * Sources Cited: <Professor Case, Spencer Poole, Class Slides,
 * 								 Course Textbook: "Starting Out With Java", 
 * 								 https://www.compart.com/en/unicode/search?q=number#characters
 * 								 stackoverflow.com>
 */


package cardGame;

import java.util.Random; //Allows us to create a Random object.

public class Deck {
	private Card[] deck = new Card[52]; //Initialize a new array (length 52) of <Card> objects.
	private int top = 0; //<top> serves as the marker for our position in the deck.
	private String S= "♥♦♠♣"; //Default suits.
	private int[] R = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}; //Default ranks.
	private Random rand = new Random(); //Generate a new random object <rand> for future use.

	
	public Deck() { //Construct the deck with default suits and ranks
		for (int s = 0; s < S.length(); s++) { //The outer loop will increment through the String <S> suits.		
			for (int r = 0; r < R.length; r++) { //The inner loop will increment through the int[] <R> ranks.
				deck[top] = new Card(R[r], S.charAt(s));//Every new Card object is assigned the current 
																					  //rank and suit.
				top++; //Use <top> to cycle through the deck array.
			}
		}
		top = -1; //Reset <top> to -1 in preparation for a draw() call.
	}
	
	
	public Deck(int[] ranks, String suits) { //Construct the deck with specified suits and ranks
		setRanks(ranks); //Custom ranks
		setSuits(suits); //Custom suits
		for (int s = 0; s < S.length(); s++) { //The outer loop will increment through the String <S> suits.		
			for (int r = 0; r < R.length; r++) { //The inner loop will increment through the int[] <R> ranks.
				deck[top] = new Card(R[r], S.charAt(s));//Every new Card object is assigned the current 
											  										  //rank and suit.
				top++; //Use <top> to cycle through the deck array.
			}
		}
		top = -1; //Reset <top> to -1 in preparation for a draw() call.
	}
	
	
	public void shuffle() { //Randomly rearrange the deck
		for (int c = 0; c < deck.length; c++) { //Loop through every card in the deck.
			swap(c, rand.nextInt(deck.length)); //Swap the current card with another random
		}																	  //card from the deck.
	}
	
	
	public Card draw() {  //Return the next top card.
		if(!isEmpty()) {  //If the deck contains at least one card
			top++; //Increment the top of the deck
			return deck[top];	//Return the drawn card
		}
		return null; //When the deck runs out
	}
	
	
	public boolean isEmpty() {
		if (top == deck.length-1) { //If all the cards have been drawn,
			return true; //the deck is empty.
		}	
		return false; //Otherwise the deck isn't empty!
	}
	
	
	private void swap(int i, int j)
	{
		Card temp = deck[i]; //Create a place-holder card <temp> and copy deck[i] to it.
		deck[i] = deck[j]; //Copy deck[j] to deck[i];
		deck[j] = temp; //Copy the <temp> card (a copy of deck[i]) to deck[j] 
	}
	
	
	public String getSuits () { //Returns the contents of the <S> suits string
		return S;
	}
	
	
	public void setSuits(String s) { //Pass a custom string containing "suit" characters to this method
		S = s;
	}
	
	
	public int[] getRanks() { //Returns the contents of the <R> ranks int[]
		return R;
	}

	
	public void setRanks(int[] r) { //Pass a custom string containing "rank" integers in an array to this method
		R = r;
	}
	
	
	public int getTop() { //Getter method for <top>
		return top;
	}
	
	
	public void setTop(int t) { //Setter method for <top>
		top = t;
	}
	
}