package restaurant;
/**
 * This class represents a given quantity of an abstract item which has a price
 * The price given in is per one single item
 * The price given out is for the given quantity of items
 */

public class QuantifiedPricedItem extends PricedItem implements Quantifiable, Priceable {
	
	// stores the quantity of an item
	private int quantity;
	
	/**
	 * Constructs a new QuantifiedPricedItem object
	 * @param quantity the quantity of an item is at least 1
	 * @param price the price per single item is a non negative amount
	 */
	public QuantifiedPricedItem(int quantity, double price) {
		super(price);
		this.setQuantity(quantity);
		
	}
	
	/**
	 * Gets the the quantity of an item
	 * @return the quantity of an item
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	/**
	 * Overrides PricableItem
	 * Gets the price of the given quantity of items
	 * @return the price of the given quantity of items
	 */
	public double getPrice() {
		return super.getPrice() * this.quantity;
	}
	
	/**
	 * Resets the quantity of an item
	 * @param quantity the quantity of an item is at least 1
	 */
	public void setQuantity(int quantity) {
		if (quantity < 1) {
			throw new IllegalArgumentException("Min order size is 1");
		}
		this.quantity = quantity;
	}


}
