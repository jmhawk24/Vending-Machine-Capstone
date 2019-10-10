package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT
													    };
	
	
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT_PRODUCT,
			PURCHASE_MENU_OPTION_FINISH_TRANSACTION
		    };
	
	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	
	VendingMachine myMachine = new VendingMachine(); // instance of our vending machine class

	public VendingMachineCLI(Menu menu) {  // Constructor - user will pas a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
	}
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	 * @throws IOException 
	***************************************************************************************************************************/

	public void run() throws IOException {
		
		File inputFile = new File("vendingmachine.csv");
		Scanner inventoryReader = new Scanner(inputFile);
		
		List<Product> inventoryList = new ArrayList<Product>();
//		Map<String, Double> slotPriceMap = new TreeMap<String, Double>();
		Map<String, Product> slotProductMap = new TreeMap<String, Product>();
		Map<String, String> noiseMap = new TreeMap<String, String>();
		noiseMap.put("Chips", "Crunch Crunch, Yum!");
		noiseMap.put("Candy", "Munch Munch, Yum!");
		noiseMap.put("Drink", "Glug Glug, Yum!");
		noiseMap.put("Gum", "Chew Chew, Yum!");
		VendingMachine theMachine = new VendingMachine();
		
		File logFile = new File("VendingMachineLog.txt");
		logFile.createNewFile();

		
		
		while (inventoryReader.hasNextLine()) {
			
			String[] nextItem = inventoryReader.nextLine().split("\\|"); // slot, name, price, type
			Product currentProduct = new Product(nextItem[1], Double.parseDouble(nextItem[2]), nextItem[3]);
			inventoryList.add(currentProduct);
			slotProductMap.put(nextItem[0], currentProduct); // slot and price
		}
		inventoryReader.close();

		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			

			
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems(inventoryList);           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					PrintWriter logWriter = new PrintWriter(logFile);
					Purchase newPurchase = new Purchase();
					boolean inPurchase = true;
					while (inPurchase) {
					String secondChoice = (String)vendingMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					
					switch(secondChoice) {
					case PURCHASE_MENU_OPTION_FEED_MONEY:
						newPurchase.feedMoney(logWriter);
						//this case will put money into machine, list amount, send to log
						break;
						
					case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
						// this is where they will purchase, money reduces, send info to log
						newPurchase.makePurchase(logWriter, slotProductMap, noiseMap);
						break;
						
					case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
						//remaining money returns to the customer, send to log
						newPurchase.returnMoney(logWriter);
						inPurchase = false;
						logWriter.close();
						break;
					}
				}// Exit switch statement
					
					break;
			
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false; 
												// Set variable to end loop
					break;                    // Exit switch statement
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public static void displayItems(List<Product> inventoryList) {      // static attribute used as method is not associated with specific object instance
		for(Product entry : inventoryList) {
			System.out.println(entry.toString());
		}
		
	}
	
	public static void purchaseItems() {	 // static attribute used as method is not associated with specific object instance
		
		//method to add money to vendingmachine.current
		
	//	double change = dollarAmount
		
	}
	
	public static void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
		
		
		
	}
}
