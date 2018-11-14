/*
Name: Cody Ryan
Date: 11.5.18
Description: This class handles the mechanical working of a vending machine including inventory, transactions, and sales reporting.
Sources Cited: <Class slides>
*/
package Vending;

import java.util.Random;

/** This class handles the mechanical working of a vending machine including inventory, transactions, and sales reporting.
 * @param snackNames A String [] for holding snack names. 
 * @param cost An int [] for holding each snack's cost. 
 * @param inventory An int [] for holding the quantity of each snack. Sized by the snackNames[]. 				    
 * @param selection An int var for holding valid selection values.  		
 * @param deposit An int var for holding non-negative change put into the machine.  		
 * @param profits An int var for holding accumulating profits. 		    		    
 */
public class VendingMachine implements VendingMachineInterface {

	Random rand = new Random();
	private String[] randSnackList = {"🍧", "🍫", "🍩", "🍭", "🥝", "🍪", "🍨", "🍰", "🍇", "🍉", "🍊", "🍌", "🍎", "🍑", "🍒", "🍓"};
	private String[] snackNames = {randSnack(), randSnack(), randSnack()};
	private int[] cost = {100, 115, 130};
	private int[] inventory = new int[snackNames.length]; 
	private int selection; 
	private int deposit;
	private int profits;
	private static boolean enableAnimation = false; /*Flag for enabling loadingEffect() animations. Set to 'false' for JUnit testing. */
	
/**Allocate the passed inventory of each candy bar to the designated index within the 
 * 'inventory' array.
 * @param s The passed inventory count 's'.
 * @param t The passed inventory count 't'.
 * @param r The passed inventory count 'r'.
 * @see setSelection(-1)  initializes 'selection' to -1 in preparation for a makeSelection() call.
 */
	public VendingMachine(int s, int t, int r) { 
		setInventory(0, s); 
		setInventory(1, t);
		setInventory(2, r);
		setSelection(-1); 
		setDeposit(0); 											
		setProfits(0); 												
	}
	

/* Setter/Getter methods */

/** Assigns a quantity of a snack to that snack's location in inventory. 
 * @param s The specified location in inventory.
 * @param q The quantity of a snack to be put in inventory.
 */
	private void setInventory (int s, int q) {
		inventory[s] = q;
	}
	
	private void setSelection(int sel) {
		selection = sel;
	}
	
	private void setProfits(int p) {
		profits = p;
	}

	private void setDeposit(int d) {
		deposit = d;
	}
	
	public int getDeposit() {
		return deposit;
	}
	
	public String[] getSnackNames() {
		return snackNames;
	}
	
	public int[] getCost() {
		return cost;
	}
	
	public int[] getInventory() {
		return inventory;
	}
		public int getSelection() {
		return selection;
	}
		
	public String randSnack() {
		return randSnackList[rand.nextInt(randSnackList.length-1)];
	}
	

	/** Stores a valid value into 'deposit' and rejects invalid inserts.
	 * @param c contains the value of the attempted deposit.
	 * @exception ImproperCoinsException An ImproperCoinsException is thrown if c is not a multiple of 5 or is a negative insert.
	 */
	@Override
	public void insertCents(int c) {
		loadingEffect("You attempt to insert " + c + "¢ into the machine.", 40);
		if (c > 0) {
			deposit += c; 
			if (deposit%5 == 0) { 
				loadingEffect(c + "¢ insert accepted! \t\t\tCurrent deposit: " + deposit + "¢\n"
											+ "Please make a selection...", 40);
				displayInventory();
				}
			else {
				loadingEffect("Sorry, this machine only accepts inserts in multiples of 5¢.", 40);
				throw new ImproperCoinsException();
			}
		}
		else { 
			loadingEffect("Sorry, this machine only accepts positive inserts.", 40);
			throw new ImproperCoinsException();
		}	
	}

	/** Stores a valid value in 'selection'.
	 * @param s An int var for holding the passed selection 's'.
	 * @exception ImproperSelectionException An ImproperSelectionException is thrown if the value is outside the valid range.
	 * @exception ImproperSelectionException(String snack) An ImproperSelectionException(String snack) is thrown if the selected 'snack' is sold out.
	 * @see validSelection(int s) validates the passed 's' value if it is within range of 'selection'.
	 */
	@Override
	public void makeSelection(int s) {
		if (validSelection(s)) { 
			setSelection(s);
			loadingEffect("\n\t\t\t\t\t*" + snackNames[selection] + " button pressed*\n\t\t\t\t\t", 40);
			if (inventory[selection] > 0)
				loadingEffect("\nThe " + snackNames[selection] + " button is glowing.", 40);
			else {
				loadingEffect("\nSorry, " + snackNames[selection] +" is currently sold out.", 40); 
				selection = -1; //Invalidate selection
				System.out.println(selection);
				throw new ImproperSelectionException(snackNames[s]); 
			}
		}
		else { 
			selection = -1; //Invalidate selection
			loadingEffect("\n\t\t\t\t\t*unmarked button pressed*\n\n"
					+ "Selection invalid. Valid selections: ", 40); 
			displayInventory();
			throw new ImproperSelectionException(); 
		}	
	}

	/** Purchases the current selection,
	 * removes the item from inventory, and gives it to the customer. 
	 * Increments 'profits' by the cost of the purchased item.
	 * Resets 'selection' to an invalid value of -1.
	 * @exception ImproperSelectionException The customer tries to purchase before selecting an item.
	 * @exception ImproperSelectionException The customer tries to purchase an item more costly than the 'deposit' balance.
	 */
	@Override
	public int purchaseSelection() { 
		loadingEffect("\n\t\t\t\t\t*purchase button pressed*\n\n", 40);
		if (validSelection(selection)) { /* If 'selection' has been set to a valid value (0, 1, 2) */
			if (selectionInStock(selection) && deposit >= cost[selection]) { 
				deposit -= cost[selection]; 
				profits += cost[selection]; 
				inventory[selection] -= 1; 
				loadingEffect("A " + snackNames[selection] + " item falls to the retrieval slot.\n"
									 + "The " + snackNames[selection] + " button is no longer glowing.", 40);
				selection =  -1; //Invalidate selection					  
				return returnUnspentCents(); 
			}
			else { 
				loadingEffect("Insufficient funds to purchase a " + snackNames[selection] + " item.", 40);
				int remainder = cost[selection] - deposit;
				throw new ImproperPurchaseException(remainder); 
			}
		}
		else 
		{
			loadingEffect("Please make a selection before attempting to purchase an item.", 40);
			throw new ImproperPurchaseException(); 
		}

	}

/** Returns any available change to the customer upon request. */
	@Override
	public int returnUnspentCents() { 
		int unspentCents = deposit; 						/* Transfer funds to 'unspentCents' so 'deposit' can be reset. */
		deposit = 0; 													
		loadingEffect("\n\t\t\t\t\t*coin return pressed*\n\n", 40);
		if (unspentCents > 0) { 
				loadingEffect("You hear your change fall into the return slot.\n"
				+ "\t\t\t\t\tReturned: " + unspentCents + "¢", 40); 
		}
		else {
			loadingEffect("\t\t\t\t\tCoin deposit empty.\n", 40);
		}
		return unspentCents; 
	}

	/** @param getProfits returns the current profits. */
	@Override
	public int getProfits() { 
		loadingEffect("Current iVend profit:" + profits + "¢", 40);
		return profits;
	}
	
	
	/** A simple method for validating a passed selection argument.
	 * @param s A possible selection to be passed as an int.
	 * @return Returns 'true' if the passed value in within the valid range. Returns 'false' if not.
	 */
	public boolean validSelection(int s) {
		if (-1 < s && s < inventory.length) { /* If 's' is within valid 'selection' range. */
			return true;
		}
		return false;
	}
	
	/** A simple method for validating if a selected item is in stock.
	 * @param s A possible selection to be passed as an int.
	 * @return Returns 'true' if the passed value represents and in-stock item.
	 *  Returns 'false' if not.
	 */
	public boolean selectionInStock(int s) {
			if (inventory[s] != 0) { /* In stock */
				return true;
			}			
		return false;
	}
	
	
/**Displays each item in the inventory, it's cost and inventory count. */
	public void displayInventory() {
		for (int index = 0; index < inventory.length; index++)
				loadingEffect("\t\t\t\t\t[" + index + "] " +  snackNames[index] 
									+ ":\t" + cost[index] + "¢ (" + inventory[index] + ")\n", 40);
	}
	
	
/**A simple method for pausing the thread for a specified 'time'.
 * @param time An int var for holding the specified time value.
 */
	private static void pause(int time) { 
		if (enableAnimation) 
		try {
			Thread.sleep(time); //1000ms = 1s
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**Outputs the characters of the passed 'string' object
	 * with a pause(time) between each character. 
	 * @param string The passed string.
	 * @param time The amount of time to pause between each character output. 
	 */
	private static void loadingEffect(String string, int time) { 
		for (int i = 0; i < string.length(); i++) { 					 
			System.out.print(string.charAt(i)); 
			pause(time); 	 								   
		}
		System.out.println(); 
	}


}
