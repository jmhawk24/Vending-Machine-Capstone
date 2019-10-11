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
	
	
	
	private static void writeToLog(PrintWriter logWriter, double dollarAmount, double currentFunds) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		Date dateInstance = new Date();
		logWriter.println(dateInstance + " FEED MONEY: $" + dollarAmount + " $" + currentFunds);
	}
	
	
	private static void writeToLog(PrintWriter logWriter, String itemName, String slot, double dollarAmount, double currentFunds) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		Date dateInstance = new Date();
		logWriter.println(dateInstance + itemName + slot + " $" + dollarAmount + " $" + currentFunds);
	}
	
	public static void salesReport(Map<String, Product> slotPriceMap, double runningTotal) throws IOException {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		Date dateInstance = new Date();
		File salesLog = new File("SalesReport " + dateInstance);
		salesLog.createNewFile();
		PrintWriter salesReportWriter = new PrintWriter(salesLog);
		
		Set<String> productSets = new TreeSet<String>();
		productSets = (slotPriceMap.keySet());
		for (String entry : productSets) {
			salesReportWriter.println(
			slotPriceMap.get(entry).getName() + " | " + 
			(5 - slotPriceMap.get(entry).getInventory()));
		}
		salesReportWriter.println("Total Sales: $" + runningTotal);
		salesReportWriter.close();
//		getRunningTotal();
//		int inventoryReport = slotPriceMap.get);
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
			
			slotPriceMap.get(theirChoice).setInventory(slotPriceMap.get(theirChoice).getInventory() - 1);
			
	
			
			writeToLog(logWriter, slotPriceMap.get(theirChoice).getName(), theirChoice, 
					fundsBeforeSubtract, currentFundsAvailable);
			OverallSalesLog.addToTotal(itemPrice);
			// method that sends this transaction to the reports and final counter
			if (theirChoice.contains("A")) {
				System.out.println(noiseMap.get("Chips"));
				System.out.printf("\n You just bought " + slotPriceMap.get(theirChoice).getName() 
						+ "! It cost $ %.2f.\n ", slotPriceMap.get(theirChoice).getPrice());

			}
			if (theirChoice.toUpperCase().contains("B")) {
				System.out.println(noiseMap.get("Candy"));
				System.out.printf("You just bought " + slotPriceMap.get(theirChoice).getName() 
						+ "! It cost $ %.2f.\n", slotPriceMap.get(theirChoice).getPrice());
			}
			if (theirChoice.toUpperCase().contains("C")) {
				System.out.println(noiseMap.get("Drink"));
				System.out.printf("\n You just bought " + slotPriceMap.get(theirChoice).getName() 
						+ "! It cost $ %.2f.\n ", slotPriceMap.get(theirChoice).getPrice());
			}
			if (theirChoice.toUpperCase().contains("D")) {
				System.out.println(noiseMap.get("Gum"));
				System.out.printf("\n You just bought " + slotPriceMap.get(theirChoice).getName() 
						+ "! It cost $ %.2f.\n ", slotPriceMap.get(theirChoice).getPrice());
			}
		}System.out.printf("Your remaining balance is $%.2f.", currentFundsAvailable);
	}
	
	//REFACTOR this to spit out specific coins!
	public void returnMoney(PrintWriter logWriter) {		// this method is to finalize sale and return money
		
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
		writeToLog(logWriter, fundsBeforeDispensing, currentFundsAvailable);
	}

	
	
}
