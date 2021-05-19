package restaurant;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * This class represents the menu of the restaurant
 * The menu is read from a given file and can be saved to the same file
 * It is memorised via a HashMap with item names as keys and prices as values
 * Once the menu is open from a file, it can be edited
 */

public class Menu implements Quantifiable {
	
	// stores the set of menu items
	private HashMap<String, PricedItem> menu;
	// stores the path to menu file
	private String path;
	
	/**
	 * Constructs a new Menu object and reads menu from a file
	 * @param path the path is a valid path to a correctly formatted menu file
	 */
	public Menu(String path) {
		this.menu = new HashMap<String, PricedItem>();
		this.path = path;
		this.readFromFile();
	}
	
	/**
	 * Reads from the file given by the path if path is valid 
	 * Saves data in the menu if the file is formatted correctly
	 * Catches formatting error and disregards incorrectly formatted rows
	 * Catches invalid file path
	 */
	public void readFromFile() {
		// declaring auxiliary variables
		String line;
		String[] menuItem;
		Double itemPrice;
		File file = new File(this.path);
		// trying to open file
		try {
			// in case file exits
			Scanner stream = new Scanner(file);
			while (stream.hasNext()) {
				line = stream.next();
				menuItem = line.split(",");
				// trying to access data
				try {
					// in case two CS values found
					if (this.menu.containsKey(menuItem[0])) {
						// in case of repeating item names
						throw new RuntimeException("Repeating value " + menuItem[0]);
					}
					// trying to insert data into map
					try {
						// in case price is in valid format
						itemPrice = Double.parseDouble(menuItem[1]);
						this.menu.put(menuItem[0], new PricedItem(itemPrice));
					}
					catch (NumberFormatException e) {
						// in case price is not in valid format
						e.printStackTrace();
					}
				}
				catch (ArrayIndexOutOfBoundsException e) {
					// in case  two CS values not found
					e.printStackTrace();
				}
			}
			stream.close();
		} 
		catch (FileNotFoundException e) {
			// in case file does not exist
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes data from the menu to the file given by the path if path is valid 
	 * Saves data in a valid format for reopening with readFromFile method
	 * Catches invalid file path
	 */
	public void WriteToFile() {
		// declaring auxiliary variables
		String itemPrice;
		File file = new File(this.path);
		// trying to open file
		try {
			// in case file exits
			PrintWriter writer = new PrintWriter(file);
			for (String item : this.menu.keySet()) {
				// converting data to text and writing to file
				itemPrice = String.valueOf(this.getItemPrice(item));
				writer.write(item + "," + itemPrice + "\n");
			}
			writer.close();
		}
		catch (FileNotFoundException e) {
			// in case does not exist
		    e.printStackTrace();
		}
	}
	
	/**
	 * Gets all menu items with prices and returns them in a matrix of object 
	 * @return menu items and prices as matrix of objects
	 */
	public Object[][] getItems() {
		// setting variables
		Object[][] items = new Object[this.getQuantity()][2];
		int counter = 0;
		// looping for item and tracking array index by counter
		for (String item : this.menu.keySet()) {
			items[counter][0] = item;
			items[counter][1] = this.getItemPrice(item);
			counter ++;
		}
		return items;
	}
	
	/**
	 * Gets price of an item given its name
	 * @param name the name of the item
	 */
	public double getItemPrice(String name) {
		if (!this.menu.containsKey(name)) {
			throw new IllegalArgumentException("Item not in menu");
		}
		return (this.menu.get(name).getPrice());
	}
	
	/**
	 * Gets the number of items on the menu
	 * @return the number of items on the menu
	 */
	public int getQuantity() {
		return this.menu.size();
	}
	
	/**
	 * Adds a new item to the menu
	 * Replaces item price if existing item name is given
	 * @param name the name of the item
	 * @param price the price of the item is a non negative amount
	 */
	public void addItem(String name, double price) {
		this.menu.put(name, new PricedItem(price));
	}
	
	/**
	 * Removes an item from the menu
	 * Does not affect menu if non existing item name is given
	 * @param name the name of the item contained in the menu
	 */
	public void removeItem(String name) {
		this.menu.remove(name);
	}

}
