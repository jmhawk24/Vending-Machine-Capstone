package com.techelevator;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Purchase extends VendingMachine{
	
	public double currentFundsAvailable;
	
	
	public Purchase() {
		super();
	}
	
	
	
	private static void writeToLog(PrintWriter logWriter, double dollarAmount, double currentFunds) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		Date dateInstance = new Date();
		logWriter.println(dateInstance + "FEED MONEY: $" + dollarAmount + " $" + currentFunds);
	}
	
	
	private static void writeToLog(PrintWriter logWriter, String itemName, String slot, double dollarAmount, double currentFunds) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		Date dateInstance = new Date();
		logWriter.println(dateInstance + itemName + slot + " $" + dollarAmount + " $" + currentFunds);
	}
	
	
	public void feedMoney(PrintWriter logWriter) {
		Scanner feedMoney = new Scanner(System.in);
		System.out.print("Please insert the amount ($1, $2, $5, $10 only): ");
		String dollarAmountString = feedMoney.nextLine();
		double dollarAmount = Double.parseDouble(dollarAmountString);
		if (dollarAmount == 1 || dollarAmount == 2 || dollarAmount == 5 || dollarAmount == 10) {
			currentFundsAvailable += dollarAmount;
			writeToLog(logWriter, dollarAmount, currentFundsAvailable);
			
			logWriter.println("FEED MONEY: $" + dollarAmount + " $" + currentFundsAvailable);
		}
		else {
			System.out.println("This is not a valid dollar amount.");
		}
	}
	Map<String, Double> slotPrice = new TreeMap<String, Double>();
	public void initializeSlotPriceMap(List<Product> inventory) {
		for(Product productEntries : inventory) {
			slotPrice.put(productEntries.getSlot(), productEntries.getPrice());
		}
	}
	
	public void makePurchase(PrintWriter logWriter, Map<String, Product> slotPriceMap, Map<String, String> noiseMap) {	 // static attribute used as method is not associated with specific object instance
		Scanner choiceInput = new Scanner(System.in);
		System.out.println("Please enter the two-digit code for the item you want: ");
		String theirChoice = choiceInput.nextLine();
		if (slotPriceMap.containsKey(theirChoice)) {
			double itemPrice = slotPriceMap.get(theirChoice).getPrice();
			double fundsBeforeSubtract = currentFundsAvailable;
			currentFundsAvailable -= itemPrice;
			writeToLog(logWriter, slotPriceMap.get(theirChoice).getName(), theirChoice, 
					fundsBeforeSubtract, currentFundsAvailable);
			OverallSalesLog.addToTotal(itemPrice);
			// method that sends this transaction to the reports and final counter
			if (theirChoice.contains("A")) {
				System.out.println(noiseMap.get("Chips"));
				System.out.println("You just bought " + slotPriceMap.get(theirChoice).getName() 
						+ "! It cost $" + slotPriceMap.get(theirChoice).getPrice() + ".");

			}
			if (theirChoice.toUpperCase().contains("B")) {
				System.out.println(noiseMap.get("Candy"));
				System.out.println("You just bought " + slotPriceMap.get(theirChoice).getName() 
						+ "! It cost $" + slotPriceMap.get(theirChoice).getPrice() + ".");
			}
			if (theirChoice.toUpperCase().contains("C")) {
				System.out.println(noiseMap.get("Drink"));
				System.out.println("You just bought " + slotPriceMap.get(theirChoice).getName() 
						+ "! It cost $" + slotPriceMap.get(theirChoice).getPrice() + ".");
			}
			if (theirChoice.toUpperCase().contains("D")) {
				System.out.println(noiseMap.get("Gum"));
				System.out.println("You just bought " + slotPriceMap.get(theirChoice).getName() 
						+ "! It cost $" + slotPriceMap.get(theirChoice).getPrice() + ".");
			}
		}System.out.printf("Your remaining balance is $%.2f.", currentFundsAvailable);
	}
	
	//REFACTOR this to spit out specific coins!
	public void returnMoney(PrintWriter logWriter) {		// this method is to finalize sale and return money
		System.out.println("Your change is: $" + currentFundsAvailable);
		double fundsBeforeDispensing = currentFundsAvailable;
		currentFundsAvailable = 0;
		writeToLog(logWriter, fundsBeforeDispensing, currentFundsAvailable);
	}

	
	
}
