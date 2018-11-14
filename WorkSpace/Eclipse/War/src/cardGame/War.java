/*
 * Name: Cody Ryan
 * Date: 10.18.18
 * Description: The War class handles playing the game of war, utilizing a Deck object.
 * Sources Cited: <Professor Case, Spencer Poole, Class Slides,
 * 								 Course Textbook: "Starting Out With Java", 
 * 								 https://www.compart.com/en/unicode/search?q=number#characters
 * 								 stackoverflow.com>
 */

package cardGame;

import javax.swing.JOptionPane; //For pop-up interactive windows
import java.io.IOException; //For possible exception handling

public class War 
{
//	static String S = "♥♦♠♣"; //The game of WAR has these 4 suits
//	static int[] R = {2, 3, 4, 5, 6, 7, 8, 9, 10,11, 12, 13, 14}; //The game of WAR has these 13 ranks
	static int pAPoints = 0; //Score keeper variable for Player A
	static int pBPoints = 0; //Score keeper variable for Player B
	static int round = 1; //The round always starts as one.
	static boolean enableAnimation = true; //boolean control for pause() method.
	static boolean enablePrompts = true; //boolean control for promptEnterKey() method.
	static int dVar; //Variable for controlling the output of the displayDeck() method.
	static boolean three = false; //third round detector.

	
	public static void main(String[] args) throws Exception {

		Deck d = new Deck(); //Create a new Deck object with default ranks and suits
												//Do it now so we can access the getRanks() and getSuits() methods
		
		Object[] possibilities = {"DEMO", "CLASSIC", "ANIMATED", "INSTANT"}; //Game mode options
		String gameType = (String)JOptionPane.showInputDialog(
		                    null,
		                    "Which version of WAR would you like to play?\n\n"
		                    
		                    + "DEMO \t\t\t\t\t\t\t\t[The game will run itself with animation enabled and prompts disabled.]\n"
		                    + "CLASSIC \t\t\t\t[Play the game with animation disabled and prompts enabled.]\n"
		                    + "ANIMATED [Play the game with animation and prompts enabled.]\n"
		                    + "INSTANT \t\t\t[The deck will be displayed. The game will be played instantly.]",
		                    (d.getSuits()+"Game Options"+d.getSuits()),
		                    JOptionPane.QUESTION_MESSAGE,
		                    null,
		                    possibilities,
		                    "INSTANT");

		if (gameType == "DEMO") { //Runs the game automatically without prompts.
			enableAnimation = true;
			enablePrompts = false;
			dVar = 30; //The contents of the deck will be fully displayed
		}
		else if (gameType == "CLASSIC") { //Runs the game without animation.
			enableAnimation = false;
			enablePrompts = true;
			dVar = 91; //After shuffling card ranks are hidden from view 
		}
		else if (gameType == "ANIMATED") { //Runs the game with animation.
			enableAnimation = true;
			enablePrompts = true;
			dVar = 92; //After shuffling the cards are replaced with 🂠
		}
		else if (gameType == "INSTANT") { //Plays the game instantly with no interaction
			enableAnimation = false;	
			enablePrompts = false;
			dVar = 0; //The contents of the deck will be fully displayed instantly
		}
		else if (gameType == null) { //If the user closes the window or clicks <cancel>
			System.out.println("PROGRAM TERMINATED BY USER"); //Alert the user:
			System.exit(0); //the program has ended.
		}
		System.out.println("You have selected " + gameType + " mode."); //Reaffirms the User's choice
		pause(500);
		
		loadingEffect("Loading...‎0███████████████████100%", 60); //Simulates a loading bar
		pause(10);
		System.out.println();
		
		loadingEffect("Welcome to the game of WAR!\n", 50);
		loadingEffect("Creating a new digital deck.....", 50);
		pause(20);
		displayDeck(d, 30); //Display the newly created deck
		System.out.println();

		loadingEffect("Shuffling the deck.....", 50);
		d.shuffle(); //Randomly shuffle the cards in Deck <d>
		pause(20);
		displayDeck(d, dVar); //Display the newly shuffled deck
		System.out.println();
		
		System.out.println("Deck setup complete.");
		pause(20);
		
		while (!d.isEmpty()) {
			loadingEffect("--------------------------------------------\n"
					+ "\t♢♠♢♠♢♠♢♠ROUND ONE♠♢♠♢♠♢♠♢\n"
					+ "--------------------------------------------\n", 10);
			round = 1;
			if (roundActions(d)) { //Perform all of the current round's actions and return the result as a boolean
												 //Ties lead to subsequent rounds
				loadingEffect("--------------------------------------------\n"
						+ "\t♥♣♥♣♥♣♥♣ROUND TWO♣♥♣♥♣♥♣♥\n"
						+ "--------------------------------------------\n", 10);
				round = 2;
				if (roundActions(d)) {
					
					loadingEffect("--------------------------------------------\n"
							+ "\t♢♥♢♥♢♥♢ROUND THREE♢♥♢♥♢♥♢\n"
							+ "--------------------------------------------\n", 10);
					three = true; //Flags the boolean variable if a third round occurs
					round = 3;
					roundActions(d);
				}
			}
		}

		pause(400);
		System.out.print("!DECK EMPTY!\n\n"); //Inform the user when the loop breaks
		pause(800);
		System.out.println("USER Points: " + pAPoints); //Display final score
		System.out.println("COMPUTER Points: " + pBPoints);
		if (pAPoints > pBPoints) 
			System.out.println("\nUSER WINS!!!");
		else if (pAPoints < pBPoints) 
			System.out.println("\nCOMPUTER WINS!!!");
		else if (pAPoints == pBPoints)
			System.out.println("\nTIE GAME!!!");
				
//		if (three)
//			System.out.println("Round 3 occurred."); //For detecting round 3 occurrence
	}
	
//-----------------------------------------------------------------------------------------------------------------------------//
//---------------------------------------------CUSTOM METHODS---------------------------------------------------//
//-----------------------------------------------------------------------------------------------------------------------------//
	

	public static boolean cardCompare (Card a, Card b) { //A method for comparing the ranks of 2 cards
		int numCards = round*2; //The number of cards on the table is equal to the current round times two
		if (a.getRank() > b.getRank()) { //If Card A is higher than Card B
			loadingEffect("USER Wins!!! + " + numCards + " cards", 50);  //inform the players who won
			pAPoints += numCards; //Player A is awarded the current round's points
		}
		else if (a.getRank() < b.getRank()) { //If Card A is lower than Card B
			loadingEffect("COMPUTER Wins!!! + " + numCards + " cards", 50);  //inform the players who won
			pBPoints += numCards; //Player B is awarded the current round's points
		}
		else if (a.getRank() == b.getRank()) { //If the ranks are equal
			System.out.println("!STALEMATE! No points awarded."); //Inform the players
			return false; //The method returns FALSE and all code below is skipped
		}
		loadingEffect(
				"\t\t\t\t\tSCORE:\n" //Display current scores
			+ "\t\t\t\t\tUSER: " + pAPoints + "\n"
			+ "\t\t\t\t\tCOMPUTER: " + pBPoints + "\n", 50);
	return true; //The method returns TRUE
	}
	
	
	public static void displayDeck(Deck d, int time) { //A method for displaying the contents of any deck in multiple ways
		Card temp; //Temp card for holding info for each card in the deck
		for (int s = 0; s < d.getSuits().length(); s++) { //The outer loop is based on the number of suits
			for (int r = 0; r < d.getRanks().length; r++) { //The inner loop is based on the number of ranks
				temp = d.draw(); //Draw the next card
				if (time != 91 && time != 92) { //Flag for openly displaying the cards
					System.out.print("[" + temp.getRank() + temp.getSuit() + "]");
				}
				else if (time == 91) { //Flag for displaying the cards with their ranks hidden
					System.out.print("[?" + temp.getSuit() + "]");
				}
				else if (time == 92) {//Flag for displaying each card as a symbol
					System.out.print("🂠");
				}
				pause(time); //Wait <t> time between each card
			}
			System.out.println(); //Prints a new line after each iteration of the outer loop
		}
		d.setTop(-1); //Reset the top of the deck in preparation for a draw() call.
	}
	
	public static boolean roundActions(Deck d) { //A method for performing the standard actions of a round.
		loadingEffect("\tUser, please press <enter> to pick a card...", 50);
		promptEnterKey();
		Card a = d.draw(); //Draw first card
		loadingEffect("\t\t\t\t\t" + ("CARD: " + (d.getTop()+1)), 50); //Inform the user of which card will be drawn
		loadingEffect(d.getSuits(), 100);
		loadingEffect("USER: [" + a.getRank() + a.getSuit() + "]\n", 100);
		pause(300);
		loadingEffect("\tNow the COMPUTER will draw a card.", 50);
		Card b = d.draw(); //Draw second card
		loadingEffect("\t\t\t\t\t" + ("CARD: " + (d.getTop()+1)), 50); //Inform the user of which card will be drawn
		loadingEffect(d.getSuits(), 100);
		loadingEffect("COMP: [" + b.getRank() + b.getSuit() + "]\n", 100);
		pause(300);
		return !cardCompare(a, b) && !d.isEmpty();
	}
	

	
	public static void promptEnterKey() { //Prompt method provided by Professor Case.
	if (enablePrompts) { //Will only run if prompts are enabled
			try {
				System.in.read(new byte[2]);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void pause(int time) { //A method to pause the thread for <t> time.
		if (enableAnimation) //Will only run if animations are enabled
		try {
			Thread.sleep(time); //1000ms = 1s
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void loadingEffect(String string, int time) { //Method for animating strings
		for (int i = 0; i < string.length(); i++) { 					 
			System.out.print(string.charAt(i)); //Outputs the contents of any string one character
			pause(time);  //at a time with a pause() in between
		}
		System.out.println(); //Includes a println() for convenience
	}
	
}
