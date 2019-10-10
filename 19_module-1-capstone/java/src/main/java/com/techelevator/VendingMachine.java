package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
	
	private int currentSpendingMoney;
	private int totalMoneySpent;
	private int currentInventory;
	
	public VendingMachine() {
		currentSpendingMoney = 0;
		totalMoneySpent = 0;
	}
	
	public void takeMoney(int money) {
	currentSpendingMoney += money;
	// send something to the log
	}
	
	public void dispenseMoney() {
		// send something to the log
		currentSpendingMoney = 0;
		
	}
		
		
		
		Scanner inventoryReader = new Scanner(path);
		List<Product> inventoryList = new ArrayList<Product>();
		while (inventoryReader.hasNextLine()) {
			String[] nextItem = inventoryReader.nextLine().split("\\|");
			String currentNewTitle = nextItem[0];
			inventoryList.add(new Product(nextItem[0], nextItem[1], nextItem[2], nextItem[3]));
			inventoryList.add(currentNewTitle);
			
			
			
	}


