package com.techelevator;

import java.util.List;

public class Product {
	//Attributes
	private String slot;
	private String name;
	private double price;
	private String type;
	private int inventory;
	private String noise;
	

	//Constructor
	
	public Product(String slot, String name, double price, String type) {
		super();
		this.slot = slot;
		this.name = name;
		this.price = price;
		this.type = type;
		this.inventory = 5;
	}
	
	//Getters and Setters
	public String getSlot() {
		return slot;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public String getType() {
		return type;
	}
	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	
	//Methods
	public void printProductList (List<Product> inventoryList) {
		for(Product entry : inventoryList) {
			System.out.println(entry.toString());
		}
		
	}

	@Override
	public String toString() {
		if (this.getInventory() == 0) {
			return(slot + ": " + name + ": \t$" + price + " | SOLD OUT");
		}
		else {
			return(slot + ": " + name + ": \t$" + price + " | " + inventory + " remaining");
		}
	}
	
}
