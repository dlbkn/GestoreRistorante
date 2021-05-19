package restaurant;

import java.util.HashMap;

/**
 * This class represents an abstract restaurant order
 * The order stores all ordered items with their price and quantity
 * The inherited price attribute stores the total price of the order
 */

public class Order extends PricedItem implements Quantifiable, Priceable {
	
	// stores the set ordered items
	private HashMap<String, QuantifiedPricedItem> order;
	
	/**
	 * Constructs a new Order object
	 * @param price the price of the order is a non negative amount
	 */
	public Order() {
		super(0.0);
		this.order = new HashMap<String, QuantifiedPricedItem>();
	}
	
	/**
	 * Gets all order items with prices and quantities and returns them in a matrix of object 
	 * @return order items and their quantities as matrix of objects
	 */
	public Object[][] getItems() {
		// setting variables
		Object[][] items = new Object[this.getQuantity()][2];
		int counter = 0;
		// looping for item and tracking array index by counter
		for (String item : this.order.keySet()) {
			items[counter][0] = item;
			items[counter][1] = this.getItemQuantity(item);
			counter ++;
		}
		return items;
	}
	
	/**
	 * Gets the quantity ordered of a given item
	 * @param name the name of an item in the order
	 * @return the quantity ordered of a given item
	 */
	public int getItemQuantity(String name) {
		if (!this.order.containsKey(name)) {
			throw new IllegalArgumentException("Item not in order");
		}
		return this.order.get(name).getQuantity();
	}
	
	/**
	 * Gets the price for a given ordered item in its quantity
	 * @param name the name of an item in the order
	 * @return the price for a given ordered item in its quantity
	 */
	public double getItemPrice(String name) {
		if (!this.order.containsKey(name)) {
			throw new IllegalArgumentException("Item not in order");
		}
		return this.order.get(name).getPrice();
	}
	
	/**
	 * Gets the number of different items in the order
	 * @return the total number of items in the order
	 */
	public int getQuantity() {
		return this.order.size();
	}
	
	/**
	 * Adds a given quantity of an item to the order
	 * @param name the name of an item 
	 * @param quantity the quantity of an item is at least 1
	 * @param price the price of an item is a non negative amount
	 */
	public void addItem(String name, int quantity, double  price) {
		if (this.order.containsKey(name)) {
			this.order.get(name).setQuantity(this.getItemQuantity(name) + quantity);
		}
		else {
			this.order.put(name, new QuantifiedPricedItem(quantity, price));
		}
		this.setPrice(this.getPrice() + this.getItemPrice(name));
	}
	
	/**
	 * Removes a given quantity of an item from the order 
	 * @param name the name of an item 
	 * @param quantity the quantity to remove is greater than the quantity of given item
	 * @param price the price of an item is a non negative amount
	 * @return the price difference from before and after the operation
	 */
	public double removeItem(String name, int quantity) {
		if (this.order.containsKey(name)) {
			double priceChange = this.getItemPrice(name);
			if (this.getItemQuantity(name) > quantity) {
				this.order.get(name).setQuantity(this.getItemQuantity(name) - quantity);
				priceChange -= this.getItemPrice(name);
			}
			else {
				this.order.remove(name);
			}
			return priceChange;
		}
		return 0.0;
	}
	

}
