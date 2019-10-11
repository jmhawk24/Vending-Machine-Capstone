package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OverallSalesLog {

	public static File logFile;
	public static PrintWriter logWriter;
	public static double runningTotal;
	
	public OverallSalesLog() throws IOException {
		logFile = new File("VendingMachineLog.txt");
		logFile.createNewFile();
		logWriter = new PrintWriter(logFile);
		runningTotal = 0;
	}
	
	public static void writeToLog(String whatToWrite ) {
		logWriter.println(whatToWrite);
	}
	
	public static void addToTotal(double lastSale) {
		runningTotal += lastSale;
	}


	public static double getRunningTotal() {
		return runningTotal;
	}
	
	public static void closePrintWriter() {
		logWriter.close();
	}
	
}
