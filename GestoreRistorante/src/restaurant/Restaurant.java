package restaurant;

public class Restaurant {
	
	private Menu menu;
	
	private OrderManager orders;
	
	private PaymentManager payments;
	
	public Restaurant(String pathToMenu) {
		this.menu = new Menu(pathToMenu);
		this.payments = new PaymentManager();
		this.orders = new OrderManager(this.payments);
	}
	
	public Menu getMenu() {
		return this.menu;
	}
	
	public OrderManager getOrderManager() {
		return this.orders;
	}
	
	public PaymentManager getPaymentManager() {
		return this.payments;
	}

}
