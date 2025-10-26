package product;

import java.util.ArrayList;
import java.util.List;

public class ProductManager implements Searchable {

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
