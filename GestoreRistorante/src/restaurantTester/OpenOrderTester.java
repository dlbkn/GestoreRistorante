package restaurantTester;

import restaurant.OrderHolder;
import restaurant.PaymentHolder;
import restaurant.Menu;
import restaurant.OpenOrder;

public class OpenOrderTester {
	
public static void main(String[] args) {
		
		// creation of new OpenOrder
		Menu menu = new Menu("resources/menu");
		OrderHolder holder = new OrderHolder(new PaymentHolder());
		OpenOrder creatorTest = new OpenOrder(menu, holder);
		
		// testing addition of items
		System.out.println("Expecting:");
		System.out.println("fish, 2");
		System.out.println("meat, 1");
		System.out.println("water, 1");
		System.out.println("Result:");
		creatorTest.addItem("fish", 2);
		creatorTest.addItem("meat", 1);
		creatorTest.addItem("water", 1);
		TesterTools.matrixPrinter(creatorTest.getItems());
		System.out.println();
		
		// testing removal
		System.out.println("Expecting:");
		System.out.println("meat, 1");
		System.out.println("water, 1");
		System.out.println("Result:");
		creatorTest.removeItem("fish",1);
		creatorTest.removeItem("fish",1);
		TesterTools.matrixPrinter(creatorTest.getItems());
		System.out.println();
		
		// testing item quantity
		System.out.println("Expecting: 2");
		System.out.println("Result: " + creatorTest.getRows());
		System.out.println();
		
		// testing order submission 
		System.out.println("Expecting:");
		System.out.println("meat, 1");
		System.out.println("water, 1");
		System.out.println("Result:");
		creatorTest.sendOrder(1);
		TesterTools.matrixPrinter(holder.getOrder(1).getItems());
		System.out.println();
		
		
		
	}

}
