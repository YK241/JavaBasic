package product2;

import java.util.ArrayList;
import java.util.List;

public class ProductManager implements Searchable {

	private ArrayList<Product> products = new ArrayList<>();

	public void addProduct(Product p) {
		products.add(p);
	}

	public boolean removeProduct(int id) {
		for (Product p : products) {
			if (p.getId() == id) {
				products.remove(p);
				return true;
			}
		}
		return false;
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

	@Override
	public List<Product> search(String keyword) {
		List<Product> result = new ArrayList<>();

		for (Product p : products) {
			if (p.getName().contains(keyword)) {
				result.add(p);
			}
		}
		
		return result;
	}
}
