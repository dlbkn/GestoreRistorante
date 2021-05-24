package restaurant;

import java.util.HashMap;

/**
 * This class is a tool for storing and paying any completed order
 * Each table has an assigned price representing the cost of their order
 */

public class PaymentHolder implements Bidimensional {
	
		// stores the set of completed and unpaid orders
		private HashMap<Integer, Double> paymentMap;
		
		/**
		 * Constructs a new PaymentHolder object
		 */
		public PaymentHolder() {
			this.paymentMap = new HashMap<Integer, Double>();
		}
		
		/**
		 * Gets the number of tables with finished and unpaid orders
		 * @return the number of tables with unpaid orders
		 */
		public int getRows() {
			return this.paymentMap.size();
		}
		
		/**
		 * Gets all completed and unpaid orders represented by a table number and their cost
		 * @return unpaid orders and their prices as matrix of objects
		 */
		public Object[][] getItems() {
			return Formatter.getFormattedListFromMap(this.paymentMap);
		}
		
		/**
		 * Gets the price to be payed by a given table
		 * @param table the ID of a table that has a completed order
		 * @return the price to be payed by a given table
		 */
		public double getPayment(int table) {
			if (!this.paymentMap.containsKey(table)) {
				throw new IllegalArgumentException("This table has no outsanding payments");
			}
			return this.paymentMap.get(table);
		}
		
		/**
		 * Adds a new finished and unpaid order to the set
		 * @param table the id number of the table
		 * @param price the price of the order is not negative
		 */
		public void addPayment(int table, double price) {
			if (this.paymentMap.containsKey(table)) {
				double tmp = this.paymentMap.get(table);
				this.paymentMap.replace(table, Formatter.getFormattedPrice(tmp + price));
			}
			else {
				this.paymentMap.put(table, price);
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
			if (!this.paymentMap.containsKey(table)) {
				throw new IllegalArgumentException("This table has no outsanding payments");
			}
			double newPrice = this.paymentMap.get(table) * (percentage / 100.0);
			this.paymentMap.replace(table, Formatter.getFormattedPrice(newPrice));
		}
		
		/**
		 * Removes a payment from the payments set (FOR PAYING)
		 * @param table the id number of the table in the payments set 
		 */
		public void removePayment(int table) {
			if (!this.paymentMap.containsKey(table)) {
				throw new IllegalArgumentException("This table has no outsanding payments");
			}
			this.paymentMap.remove(table);
		}

}
