package restaurant;

/**
 * This class represents an abstract item which has a price
 * The price is validated to be a non negative number
 * The price is store with two decimal point precision
 */

public class PricedItem implements Priceable {
	
	// stores the price of the item
	private double price; 
	
	/**
	 * Constructs a new PricedItem object
	 * @param price the price of the item is a non negative amount
	 */
	public PricedItem(double price) {
		this.setPrice(price);
	}
	
	/**
	 * Gets the price of the item
	 * @return the price of the item
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * Resets the price of the item
	 * @param price the price of the item is a non negative amount
	 */
	public void setPrice(double price) {
		if (price < 0.0) {
			throw new IllegalArgumentException("Negative price");
		}
		this.price = this.roundPrice(price);
	}
	
	/**
	 * Rounds the price to two decimal places 
	 * @param price the price of the item
	 * @return the price of the item rounded to two decimal places
	 */
	private double roundPrice(double price) {
		return ((int) (price * 100)) / 100.0;
	}  

}
