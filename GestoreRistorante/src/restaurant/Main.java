package restaurant;

import java.io.IOException;
import GUI.GUIRestaurant;

/**
 * This class acts as the entry point into GestoreRistorante
 * It creates an instance of Restaurant and GUIRestaurant to begin the program
 * The paths for MENU and SCONTRINI can be edited by the user
 */

public class Main {
	
	// Set MENU to the path of the menu file
	private static String MENU = "GestoreRistorante\\resources\\menu";
	
	// Set SCONTRINI to the path of the receipts folder
	private static String SCONTRINI = "GestoreRistorante\\resources\\";
	
	/**
	 * Starts running GestoreRistorante
	 * @param args not used
	 */
	public static void main(String[] args) throws IOException {
		
		Restaurant r = new Restaurant(MENU, SCONTRINI);
		GUIRestaurant rGUI = new GUIRestaurant(r);
        rGUI.setVisible(true);

	}

}
