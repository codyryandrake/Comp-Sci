/*
Name: Cody Ryan
Date: 11.5.18
Description: This class handles a GUI implementation of the VendingMachine class.
					   The iVend machine inventory is randomly stocked on creation.
Sources Cited: <Class slides>
							https://docs.oracle.com/javase/tutorial/uiswing/
*/

package Vending;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.util.Random;

public class iVend extends JFrame {
	
	private static Random rand = new Random();
	private static VendingMachine iVend = new VendingMachine /* Create a vending machine "iVend" */
			(rand.nextInt(5)+1, rand.nextInt(5)+1, rand.nextInt(5)+1);    /* and populate inventory randomly */
	
	
	/* Initialize all our JFrame, panel and component vars. */
	private static JLabel dLabel = new JLabel();
	private static JLabel[] snackInventoryLabel= new JLabel[iVend.getInventory().length];
	private static JLabel[] snackCostLabel= new JLabel[iVend.getInventory().length];
	private static JLabel snackPanelLabel;
	private static JLabel purchaseLabel;
	private static JLabel profits = new JLabel("");
	private static boolean profitsVisible = false;
	
	private static JButton purchase;
	private static JButton depositButton;
	private static JButton quickDeposit;
	private static JButton returnButton;
	private static JRadioButton[] Selections;
	private static ButtonGroup S;
	
	private static JPanel snackSelectionPanel;
	private static JPanel purchasePanel;
	private static JPanel depositPanel;
	private static JPanel returnPanel;

	private static GridLayout g;
	private static Dimension dim = new Dimension(100, 100);
	private static JFrame menuFrame;

	/*Call our GUI iVendMenu within our iVend constructor. */
	public iVend () {
		iVendMenu();
	}

	/*Create a new instance of our graphical iVend machine! */
	public static void main(String[] args) {
		new iVend();
	}

	/*A class for initializing GUI elements and their actions. */
	public static void iVendMenu() {
		snackInventoryLabel = new JLabel[iVend.getInventory().length];		
		snackPanelLabel = new JLabel("SNACK SELECT"); 
		Selections = new JRadioButton[iVend.getSnackNames().length]; //Create an array of snack buttons
		snackSelectionPanel = new JPanel(); //Create the panel for holding snack selections
		snackSelectionPanel.add(snackPanelLabel);
		/* Create a dynamically-sized group of selection buttons. */
		S = new ButtonGroup();
		for (int i = 0; i < iVend.getSnackNames().length; i++) { //Itterate through the array
			Selections[i] = new JRadioButton(iVend.getSnackNames()[i]); 									  //And create the correct # of buttons
			Selections[i].setActionCommand(i + "");
			Selections[i].addActionListener(new selectSnack());
			Selections[i].setFont(new Font("Helvetica", Font.PLAIN, 18));
			S.add(Selections[i]);
			snackCostLabel[i] = new JLabel(iVend.getCost()[i] + "¢");
			snackInventoryLabel[i] = new JLabel("[" + iVend.getInventory()[i] + "]");
			snackSelectionPanel.add(Selections[i], BorderLayout.NORTH); //Append buttons to panel
			snackSelectionPanel.add(snackCostLabel[i], BorderLayout.CENTER);
			snackSelectionPanel.add(snackInventoryLabel[i], BorderLayout.SOUTH);
		}
		/*Create purchase panel components. */
		purchaseLabel = new JLabel(""); 
		purchasePanel = new JPanel();
		purchase = new JButton("PURCHASE");
		purchase.addActionListener(new purchaseSnack());
		purchasePanel.add(purchase, BorderLayout.NORTH);
		purchasePanel.add(purchaseLabel, BorderLayout.SOUTH);
		/*Create deposit panel components. */
		depositButton = new JButton("INSERT DEPOSIT");
		depositButton.addActionListener(new depositCents()); //Custom deposit action
		quickDeposit = new JButton("QUICK INSERT 100¢"); //Quick default deposit of 100 cents
		quickDeposit.addActionListener(new quickDeposit());
		depositPanel = new JPanel();
		depositPanel.add(depositButton, BorderLayout.NORTH);
		depositPanel.add(quickDeposit, BorderLayout.SOUTH);
		/*Create return panel components. */
		returnPanel = new JPanel();
		returnPanel.add(dLabel, BorderLayout.SOUTH);
		dLabel.setText("DEPOSIT: " + iVend.getDeposit() + "¢");	
		returnButton = new JButton("RETURN");
		returnPanel.add(returnButton, BorderLayout.NORTH);
		returnPanel.add(profits,BorderLayout.SOUTH);
		returnButton.addActionListener(new returnCents());
		/*Establish a grid layout and append all panels to the menuFrame. */
		g = new GridLayout(2, 2);
		menuFrame = new JFrame("iVend-by CODYRYANDESIGN"); //Create menu frame
		menuFrame.setLayout(g);
		menuFrame.setVisible(true);
		menuFrame.setSize(250, 275); //width x height
		menuFrame.setLocation(500, 500);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.add(snackSelectionPanel, BorderLayout.NORTH);
		menuFrame.add(purchasePanel, BorderLayout.SOUTH);
		menuFrame.add(depositPanel, BorderLayout.WEST);
		menuFrame.add(returnPanel, BorderLayout.EAST);
	}
	
	static class selectSnack implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	
			int selection = Integer.parseInt(e.getActionCommand()); //Interpret the pressed button as a temp 'selection' var
			try {
				iVend.makeSelection(selection); //Try to make a selection
				purchaseLabel.setText(iVend.getSnackNames()[selection]); //Add valid selection beneath button
			} catch (ImproperSelectionException s) { //Catch an improper selection and...

				if(!iVend.validSelection(iVend.getSelection())) { //If the exception is thrown for a valid selection
					JOptionPane.showMessageDialog(null, "Error: This vending machine is out of " + iVend.getSnackNames()[selection] + ".");
				} else {
					JOptionPane.showMessageDialog(null, "Error: The item number selected does not exist.");
				}
				S.clearSelection(); /* Resets the state of the button group to false. */
				purchaseLabel.setText(""); //Clear purchase selection label
			}
		}		
	}
	
	static class purchaseSnack implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {	
			int selection = iVend.getSelection(); /*Temp var for holding 'selection' state during the purchase action.*/
			int soldOut = 0;
			
						if (iVend.validSelection(selection)) { /*If 'selection' is valid...*/
							try {
								int change = iVend.purchaseSelection(); /*Attempt purchase of valid 'selection' and collect change. */
								
								JOptionPane.showMessageDialog(null, "A " + iVend.getSnackNames()[selection]
										+ " item falls to the retrieval slot. ");
								JOptionPane.showMessageDialog(null, 
										"Returned: " + change + "¢");
								
								dLabel.setText("DEPOSIT: " + iVend.getDeposit() + "¢");
								snackInventoryLabel[selection].setText("[" + iVend.getInventory()[selection] + "]");
								S.clearSelection(); //Reset the selected button after a successful purchase
								purchaseLabel.setText(""); //Clear purchase selection label
								if (!iVend.selectionInStock(selection)) { //Check to see if we sold the last of an item	
										JOptionPane.showMessageDialog(null, 
												"Last " + iVend.getSnackNames()[selection] + " sold.");
								}
								for (int i = 0; i < iVend.getInventory().length; i++) { //Check to see if all items are sold out.
									if(iVend.getInventory()[i] == 0) {
										soldOut += 1;
										if(soldOut == iVend.getInventory().length) {
												JOptionPane.showMessageDialog(null, 
																		"iVend inventory sold out!");
												profits.setText("Profits: " + iVend.getProfits() + "¢");
												profitsVisible = true;
										}
									} 
								}
							} catch (ImproperPurchaseException e1) {
								JOptionPane.showMessageDialog(null, "Error: Insufficient funds. The selected snack costs " 
																										+ (iVend.getCost()[iVend.getSelection()]-iVend.getDeposit()) //Selection cost minus current deposit
																										+ " more cents." ); 
							} 
						} else /* If the selection is invalid try to purchase anyway and catch the 2nd expected exception. */
							try {
								iVend.purchaseSelection();
							} catch (ImproperPurchaseException e2) { 
								JOptionPane.showMessageDialog(null, "Error: You have not selected a snack number yet."); //Purchase made before valid selection was chosen
							}
				}
	}
			
	
	static class depositCents implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {	 
				try {
					String d = JOptionPane.showInputDialog("Deposit Amount");
					int deposit = Integer.parseInt(d); //NumberFormatException thrown here
					if (deposit > 0) {																		
					iVend.insertCents(deposit); //ImproperCoinsException 'c'  thrown here
					dLabel.setText("DEPOSIT: " + iVend.getDeposit() + "¢");	
					}
					else { /* If the attempted deposit is negative */
						try {
							iVend.insertCents(deposit); /*try to deposit anyway and alert the
							 													user by handling the exception */
						} catch (ImproperCoinsException e1) { 
							JOptionPane.showMessageDialog(null, "Error: This machine only accepts positive inserts.");
						}
					}
				}catch (ImproperCoinsException c) { //If you insert a positive non-multiple of 5 integer 
					JOptionPane.showMessageDialog(null, "Error: The amount of cents you put into a machine must be a multiple of 5.");
					JOptionPane.showMessageDialog(null, "Returned: " + iVend.returnUnspentCents() + "¢");
					dLabel.setText("DEPOSIT: " + iVend.getDeposit() + "¢");	
				}
				catch (NumberFormatException e1) {
					if(e1.equals(null)) { //The user enters something other than an integer.
					JOptionPane.showMessageDialog(null, "Error: this machine only accepts integer inserts.");
					}
				}
		}
	
	}
	/*Our fast and easy solution for quickly depositing inserts. */
	static class quickDeposit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {	
			iVend.insertCents(100);	
			dLabel.setText("DEPOSIT: " + iVend.getDeposit() + "¢");	
		}
	
	}
	/*Call the standard returnUnspentCents() method and imform the user. */
	static class returnCents implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {	
				
				JOptionPane.showMessageDialog(null, "Returned: " + iVend.returnUnspentCents() + "¢");
				dLabel.setText("DEPOSIT: " + iVend.getDeposit() + "¢");	
		}
	
	}
}
