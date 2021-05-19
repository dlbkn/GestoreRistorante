package restaurantTester;

import java.util.Arrays;

import restaurant.PaymentManager;

public class PaymentManagerTester {
	
	public static void main(String[] args) {
		
		// creating new PaymentManager object
		PaymentManager paymentTest = new PaymentManager();
		
		// testing insertion of payments
		System.out.println("Expecting:");
		System.out.println("1,105.00");
		System.out.println("2,80.00");
		System.out.println("Result:");
		paymentTest.addPayment(1, 100.00);
		paymentTest.addPayment(2, 80.00);
		paymentTest.addPayment(1, 5.00);
		for (Object[] item : paymentTest.getItems()) {
			System.out.println(Arrays.toString(item));
		}
		System.out.println();
		
		// testing item price getter
		System.out.println("Expecting: 80.00");
		System.out.println("result: " + paymentTest.getItemPrice(2));
		System.out.println();
		
		// testing length getter
		System.out.println("Expecting: 2");
		System.out.println("Result: " + paymentTest.getQuantity());
		System.out.println();
		
		// testing discount
		System.out.println("Expecting: 60.00");
		paymentTest.discountPayment(2, 75.0);
		System.out.println("Result: " + paymentTest.getItemPrice(2));
		System.out.println();
		
		// testing removal of payments
		System.out.println("Expecting:");
		System.out.println("1,105.00");
		System.out.println("Result:");
		paymentTest.removePayment(2);
		for (Object[] item : paymentTest.getItems()) {
			System.out.println(Arrays.toString(item));
		}
		System.out.println();
		
	}

}
