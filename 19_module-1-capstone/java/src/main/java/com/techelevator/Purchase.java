package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Purchase extends VendingMachine{
	
	public double currentFundsAvailable;
	
	
	public Purchase() {
		super();
	}

	public static void salesReport(Map<String, Product> slotProductMap, double runningTotal) throws IOException {
		File salesLog = new File("SalesReport " + getDate().toString());
		salesLog.createNewFile();
		PrintWriter salesReportWriter = new PrintWriter(salesLog);
		
		Set<String> productSets = new TreeSet<String>();
		productSets = (slotProductMap.keySet());
		for (String entry : productSets) {
			salesReportWriter.println(
			slotProductMap.get(entry).getName() + " | " + 
			(5 - slotProductMap.get(entry).getInventory()));
		}
		salesReportWriter.printf("Total Sales: $%.2f", runningTotal);
		salesReportWriter.close();
	}
	
	public void feedMoney() {
		Scanner feedMoney = new Scanner(System.in);
		System.out.print("Please insert the amount ($1, $2, $5, $10 only): ");
		String dollarAmountString = feedMoney.nextLine();
		double dollarAmount = Double.parseDouble(dollarAmountString);
		if (dollarAmount == 1 || dollarAmount == 2 || dollarAmount == 5 || dollarAmount == 10) {
			currentFundsAvailable += dollarAmount;
			String whatToWrite = (getDate().toString() + " FEED MONEY: $" + Double.toString(dollarAmount) + " $" + Double.toString(currentFundsAvailable));
			OverallSalesLog.writeToLog(whatToWrite);
		}
		else {
			System.out.println("This is not a valid dollar amount.");
		}
	}
	
	public void makePurchase( Map<String, Product> slotProductMap, Map<String, String> noiseMap) {	 // static attribute used as method is not associated with specific object instance
		Scanner choiceInput = new Scanner(System.in);
		System.out.println("Please enter the two-digit code for the item you want: ");
		String theirChoice = choiceInput.nextLine();
		if (slotProductMap.containsKey(theirChoice)) {
			if (slotProductMap.get(theirChoice).getInventory() > 0) {
				double itemPrice = slotProductMap.get(theirChoice).getPrice();
				
					if (currentFundsAvailable >= itemPrice) {
						
						double fundsBeforeSubtract = currentFundsAvailable;
					currentFundsAvailable -= itemPrice;
					
					slotProductMap.get(theirChoice).setInventory(slotProductMap.get(theirChoice).getInventory() - 1);
					
					OverallSalesLog.writeToLog(getDate().toString() + slotProductMap.get(theirChoice).getName() + " " +  slotProductMap.get(theirChoice).getSlot() 
							+ " $" + Double.toString(fundsBeforeSubtract) + " $" + Double.toString(currentFundsAvailable));
					OverallSalesLog.addToTotal(itemPrice);
					// method that sends this transaction to the reports and final counter
					System.out.println(getNoise(theirChoice, noiseMap));
					System.out.printf("\n You just bought " + slotProductMap.get(theirChoice).getName() 
								+ "! It cost $ %.2f.\n ", slotProductMap.get(theirChoice).getPrice()); 
					}
					else {
						System.out.println("Sorry, you have insufficient funds.");
					}
				}
			
			else {
				System.out.println("Sorry, your choice is SOLD OUT.");
			}
			
			System.out.printf("Your remaining balance is $%.2f.", currentFundsAvailable);
		}
		else {
			System.out.println("You did not enter a valid item code. Please try again.");
		}
	}
	
	private String getNoise(String theirChoice, Map<String, String> noiseMap) {
		if (theirChoice.toUpperCase().contains("A")) {
			return noiseMap.get("Chips");
		}
		if (theirChoice.toUpperCase().contains("B")) {
			return noiseMap.get("Candy");
		}
		if (theirChoice.toUpperCase().contains("C")) {
			return noiseMap.get("Drink");
		}
		if (theirChoice.toUpperCase().contains("D")) {
			return noiseMap.get("Gum");
		}
		else {
			return "";
		}
	}
	
	public void returnMoney() {		// this method is to finalize sale and return money
		
		System.out.printf("\n Your change is: $ %.2f. \n", currentFundsAvailable);
		int change = (int)(Math.ceil(currentFundsAvailable*100));
		int quarters = Math.round((int)change/25);
		change = change%25;
		int dimes = Math.round((int)change/10);
		change = change%10;
		int nickels = Math.round(change/5);
		System.out.println("You will receive " + quarters + " quarters, " + dimes + " dimes, and "
				+ nickels + " nickels.\n");
		double fundsBeforeDispensing = currentFundsAvailable;
		currentFundsAvailable = 0;
		OverallSalesLog.writeToLog(getDate() + " DISPENSE CHANGE: $" + fundsBeforeDispensing + " $" + currentFundsAvailable);
	}
	
	
	private static Date getDate() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		Date dateInstance = new Date();
		return dateInstance;
	}


	
	
}
