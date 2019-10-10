package com.techelevator;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Purchase extends VendingMachine{
	
	public double currentFundsAvailable;
	
	
	public Purchase() {
		super();
	}
	
	
	
	
	
	
	public void feedMoney() {
		Scanner feedMoney = new Scanner(System.in);
		System.out.print("Please insert the amount ($1, $2, $5, $10 only): ");
		String dollarAmountString = feedMoney.nextLine();
		double dollarAmount = Double.parseDouble(dollarAmountString);
		if (dollarAmount == 1 || dollarAmount == 2 || dollarAmount == 5 || dollarAmount == 10) {
			currentFundsAvailable += dollarAmount;
			System.out.println(currentFundsAvailable);
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
	
	public void makePurchase(Map<String, Product> slotPriceMap, Map<String, String> noiseMap) {	 // static attribute used as method is not associated with specific object instance
		Scanner choiceInput = new Scanner(System.in);
		System.out.println("Please enter the two-digit code for the item you want: ");
		String theirChoice = choiceInput.nextLine();
		if (slotPriceMap.containsKey(theirChoice)) {
			double itemPrice = slotPriceMap.get(theirChoice).getPrice();
			currentFundsAvailable -= itemPrice;
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
	public void returnMoney() {		// this method is to finalize sale and return money
		System.out.println("Your change is: $" + currentFundsAvailable);
		currentFundsAvailable = 0;
	}

	
	
}
