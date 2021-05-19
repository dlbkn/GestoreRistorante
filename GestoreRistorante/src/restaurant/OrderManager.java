package restaurant;

import java.util.HashMap;

/**
 * This class is a tool for storing and managing all unfinished orders
 * Each table has an Order object to track and interact with the order
 */

public class OrderManager implements Quantifiable {
	
	// stores the set of completed and unpaid orders
	private HashMap<Integer, Order> orders;
	private PaymentManager pendingPayments;
	/**
	 * Constructs a new OrderManager object
	 * @param pendingPayments the PaymentManager for the current restaurant session
	 */
	public OrderManager(PaymentManager pendingPayments) {
		this.orders = new HashMap<Integer, Order>();
		this.pendingPayments = pendingPayments;
	}
	
	/**
	 * Gets all incomplete orders represented by a table number and their cost
	 * @return incomplete orders and their prices as matrix of objects
	 */
	public Object[][] getMapItems() {
		// setting variables
		Object[][] items = new Object[this.getQuantity()][2];
		int counter = 0;
		// looping for item and tracking array index by counter
		for (Integer item : this.orders.keySet()) {
			items[counter][0] = item;
			items[counter][1] = this.getOrderPrice(item);
			counter ++;
		}
		return items;
	}
	
	/**
	 * Gets the items and quantities of a given order
	 * @return items and quantities of a given order
	 */
	public Object[][] geOrderMapItems(int table) {
		return this.orders.get(table).getItems();
	}
	
	/**
	 * Gets the price of the order for a given table
	 * @return the price of the order for a given table
	 */
	public double getOrderPrice(int table) {
		if (!this.orders.containsKey(table)) {
			throw new IllegalArgumentException("Non exitstent table");
		}
		return this.orders.get(table).getPrice();
	}
	
	/**
	 * Gets the number of tables with finished and unpaid orders
	 * @return the number of tables with unpaid orders
	 */
	public int getQuantity() {
		return this.orders.size();
	}
	
	/**
	 * Adds a new empty order to the set
	 * @param table the id number of the table
	 */
	public void addOrder(int table) {
		if (!this.orders.containsKey(table)) {
			this.orders.put(table, new Order());
		}
	}
	
	/**
	 * Adds a given quantity of an item to a given order
	 * @param table the id number of the table in the payments set 
	 * @param name the name of an item in the given order
	 * @param quantity the quantity to add is at least 1
	 * @param price the price of an item is a non negative amount
	 */
	public void addItem(int table, String name, int quantity, double price) {
		this.orders.get(table).addItem(name, quantity, price);
	}
	
	/**
	 * Removes a given quantity of an item from a given order (NOT FOR SERVING)
	 * @param table the id number of the table in the payments set 
	 * @param name the name of an item in the given order
	 * @param quantity the quantity to add is at least 1
	 */
	public void removeItem(int table, String name, int quantity) {
		double priceChange = this.orders.get(table).removeItem(name, quantity);
		this.orders.get(table).setPrice(this.getOrderPrice(table) - priceChange);
		// remove the order if it is empty
		this.removeOrderCheck(table);
		}
	
	/**
	 * Serves and ordered item to the given table
	 * @param table the id number of the table in the payments set 
	 * @param name the name of an item in the given order
	 */
	public void serveItem(int table, String name) {
		this.orders.get(table).removeItem(name, 1);
		this.removeOrderCheck(table);
	}
	
	private void removeOrderCheck(int table) {
		if (this.orders.get(table).getQuantity() == 0) {
			this.pendingPayments.addPayment(table, this.getOrderPrice(table));
			this.orders.remove(table);
		}
	}

}
