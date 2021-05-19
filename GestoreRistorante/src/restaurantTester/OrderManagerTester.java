package restaurantTester;

import java.util.Arrays;

import restaurant.PaymentManager;
import restaurant.Order;
import restaurant.OrderManager;

public class OrderManagerTester {
	
	public static void main(String[] args) {
		
		// creating new OrderManager object
		PaymentManager paymentTest = new PaymentManager();
		OrderManager orderTest = new OrderManager(paymentTest);
		orderTest.addOrder(1);
		orderTest.addOrder(2);
		
		// testing insertion of items
		System.out.println("Expecting:");
		System.out.println("fish, 2");
		System.out.println("water, 1");
		System.out.println("---------");
		System.out.println("meat, 1");
		System.out.println();
		System.out.println("Result:");
		orderTest.addItem(1,"fish", 2, 10.00);
		orderTest.addItem(1,"water", 1, 1.00);
		orderTest.addItem(2,"meat", 1, 8.00);
		for (Object[] item : orderTest.geOrderMapItems(1)) {
			System.out.println(Arrays.toString(item));
		}
		System.out.println("---------");
		for (Object[] item : orderTest.geOrderMapItems(2)) {
			System.out.println(Arrays.toString(item));
		}
		System.out.println();
		
		// testing removal of items
		System.out.println("Expecting:");
		System.out.println("water, 1");
		System.out.println("---------");
		System.out.println("meat, 1");
		System.out.println();
		System.out.println("Result:");
		orderTest.removeItem(1, "fish", 2);
		for (Object[] item : orderTest.geOrderMapItems(1)) {
			System.out.println(Arrays.toString(item));
		}
		System.out.println("---------");
		for (Object[] item : orderTest.geOrderMapItems(2)) {
			System.out.println(Arrays.toString(item));
		}
		System.out.println();
		
		// testing price 
		System.out.println("Expecting: 1.00");
		System.out.println("Results: " + orderTest.getOrderPrice(1));
		System.out.println();
		
		// testing finished order
		orderTest.serveItem(1, "water");
		System.out.println("Expecting: 1");
		System.out.println("Results: " + orderTest.getQuantity());
		System.out.println("Expecting:");
		System.out.println("1, 1.0");
		System.out.println("Results: ");
		for (Object[] item : paymentTest.getItems()) {
			System.out.println(Arrays.toString(item));
		}
		System.out.println();
		System.out.println();
		
		
	}

}
