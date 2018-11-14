/*
Name: Cody Ryan
Date: 11.5.18
Description: This JUnit tester tests crucial VendingMachine methods.
Sources Cited: <Class slides>
*/

package Vending;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VendingMachineTester {
	
	
	@Test(expected = ImproperCoinsException.class) //TEST 1
	public void ImproperCoinsExceptionTest() {
		System.out.println("\t\t\t\t\t\t\t\tTEST 1");
		VendingMachine iVend = new VendingMachine(5, 5, 5);
		iVend.insertCents(12);  													/* Try depositing a non-multiple of 5
		 																							  into the machine */
	}
	
	@Test(expected = ImproperSelectionException.class) //TEST 2
	public void ImproperSelectionExceptionTest1() {
		System.out.println("\t\t\t\t\t\t\t\tTEST 2");
		VendingMachine iVend = new VendingMachine(5, 5, 5);
		iVend.makeSelection(-1); 												/* Try to make an invalid selection. */
	}
	
	@Test(expected = ImproperSelectionException.class) //TEST 3
	public void ImproperSelectionExceptionTest2() {
		System.out.println("\t\t\t\t\t\t\t\tTEST 3");
		VendingMachine iVend = new VendingMachine(5, 0, 5);
		iVend.makeSelection(1);  												/* Try to select an out-of-stock item */ 
	}
	
	@Test(expected = ImproperPurchaseException.class) //TEST 4
	public void ImproperPurchaseExceptionTest1() {
		System.out.println("\t\t\t\t\t\t\t\tTEST 4");
		VendingMachine iVend = new VendingMachine(5, 5, 5);
		iVend.purchaseSelection();  											/* Try to purchase before making a selection */
	}
	
	@Test(expected = ImproperPurchaseException.class) //TEST 5
	public void ImproperPurchaseException2() {
		System.out.println("\t\t\t\t\t\t\t\tTEST 5");
		VendingMachine iVend = new VendingMachine(5, 5, 5);
		iVend.makeSelection(1);  												/* Select Twix */
		iVend.purchaseSelection(); 											/* Try to purchase Twix before depositing any change */
	}
	
	@Test //TEST 6
	public void changeReturnTest1() {
		System.out.println("\t\t\t\t\t\t\t\tTEST 6");
		VendingMachine iVend = new VendingMachine(5, 5, 5);
		iVend.insertCents(200); 													/* Deposit 200 cents into the machine */
		iVend.makeSelection(0); 												/* Snickers costs 100 cents */
		assertEquals(100, iVend.purchaseSelection());			/* Original deposit of 200 minus cost of Snickers (100)
																									  should return 100 cents */
	}
	
	@Test //TEST 7
	public void changeReturnTest2() {
		System.out.println("\t\t\t\t\t\t\t\tTEST 7");
		VendingMachine iVend = new VendingMachine(5, 5, 5);
		iVend.insertCents(150); 													/* Original deposit of 150 cents */
		assertEquals(150, iVend.returnUnspentCents()); 		/* Original deposit should be equal
		 																							  to returned amount if no purchase
		 																							  was made. */
	}
	
	@Test //TEST 8
	public void getProfitsTest() {
		System.out.println("\t\t\t\t\t\t\t\tTEST 8");
		VendingMachine iVend = new VendingMachine(5, 5, 5);
		iVend.insertCents(1000); 												/* Deposit of 1000 cents */
		iVend.makeSelection(0); 												/* Select Snickers */
		iVend.purchaseSelection(); 											/* Purchase Snickers for 100 cents */
		
		iVend.insertCents(500); 													/* Deposit of 500 cents */
		iVend.makeSelection(1); 												/* Select Twix */
		iVend.purchaseSelection(); 											/* Purchase Twix for 115 cents */
		
		iVend.insertCents(200); 													/* Deposit of 200 cents */
		iVend.makeSelection(2); 												/* Select Reeses */
		iVend.purchaseSelection(); 											/* Purchase Reeses for 130 cents */
		
		assertEquals(345, iVend.getProfits()); 							/* Profit should equal 345 cents */
	}
	
	

}
