package com.techelevator;

public class OverallSalesLog {

	public static double runningTotal;
	
	public OverallSalesLog() {
		runningTotal = 0;
	}
	
	
	public static void addToTotal(double lastSale) {
		runningTotal += lastSale;
	}


	public static double getRunningTotal() {
		return runningTotal;
	}
	
}
