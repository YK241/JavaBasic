package product;

public class DiscountProduct extends Product {
	private double discountRate;

	public DiscountProduct(int id, String name, int price, int stock, double discountRate) {
		super(id, name, price, stock);
		this.discountRate = discountRate;
	}

	public int calculateDiscountedPrice() {
		return (int) (getPrice() * (1 - discountRate));
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}

}
