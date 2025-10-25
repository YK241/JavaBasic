package product;

import java.util.ArrayList;

public class ProductManager {

	private ArrayList<Product> products = new ArrayList<>();

	public void addProduct(Product p) {
		products.add(p);
	}

	public void removeProduct(int id) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id) {
				products.remove(i);
				break;
			}
		}
	}

	public Product getProductByName(String name) {
		for (Product p : products) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<Product> getProducts() {
	    return products;
	}
}
