package restaurant;

import java.util.HashMap;

/**
 * This class is a tool for storing and managing order payments after the order is finished
 * Each table in the payments set has an outstanding sum which can be discounted and paid
 */

public class PaymentManager implements Quantifiable{
	
	// stores the set of completed and unpaid orders
	private HashMap<Integer, PricedItem> payments;
	
	/**
	 * Constructs a new Order object
	 * @param price the price of the order is a non negative amount
	 */
	public PaymentManager() {
		this.payments = new HashMap<Integer, PricedItem>();
	}
	
	/**
	 * Gets all completed and unpaid orders represented by a table number and their cost
	 * @return unpaid orders and their prices as matrix of objects
	 */
	public Object[][] getItems() {
		// setting variables
		Object[][] items = new Object[this.getQuantity()][2];
		int counter = 0;
		// looping for item and tracking array index by counter
		for (Integer item : this.payments.keySet()) {
			items[counter][0] = item;
			items[counter][1] = this.getItemPrice(item);
			counter ++;
		}
		return items;
	}
	
	/**
	 * Gets the price to be payed by a given table
	 * @return the price to be payed by a given table
	 */
	public double getItemPrice(int table) {
		if (!this.payments.containsKey(table)) {
			throw new IllegalArgumentException("Non exitstent table");
		}
		return this.payments.get(table).getPrice();
	}
	
	/**
	 * Gets the number of tables with finished and unpaid orders
	 * @return the number of tables with unpaid orders
	 */
	public int getQuantity() {
		return this.payments.size();
	}
	
	/**
	 * Adds a new finished and unpaid order to the set
	 * @param table the id number of the table
	 * @param price the price of the order is not negative
	 */
	public void addPayment(int table, double price) {
		if (this.payments.containsKey(table)) {
			this.payments.get(table).setPrice(this.getItemPrice(table) + price);
		}
		else {
			this.payments.put(table, new PricedItem(price));
		}
	}
	
	/**
	 * Discounts the order payment by a given percentage
	 * @param table the id number of the table in the payments set 
	 * @param percentage the percentage is strictly between 0 and 100
	 */
	public void discountPayment(int table, double percentage) {
		if (percentage <= 0.0 || percentage >= 100.0) {
			throw new IllegalArgumentException("Invalid percentage");
		}
		this.payments.get(table).setPrice(this.getItemPrice(table) * (percentage / 100.0));
	}
	
	/**
	 * Removes a payment from the payments set
	 * @param table the id number of the table in the payments set 
	 */
	public void removePayment(int table) {
		this.payments.remove(table);
	}

}
