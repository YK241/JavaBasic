package product;

import java.util.List;

public class Main {

	public static void showProduct(Product p) {
		if (p == null) {
			System.out.println("商品が存在しません");
		} else {
			System.out.print(
					"Product: id=" + p.getId() +
							", name=" + p.getName() +
							", price=" + p.getPrice() +
							", stock=" + p.getStock());
		}
	}

	public static void showAllProduct(ProductManager manager) {
		for (Product p : manager.getProducts()) {
			System.out.println(
					"Product: id=" + p.getId() +
							", name=" + p.getName() +
							", price=" + p.getPrice() +
							", stock=" + p.getStock());
		}
	}

	public static void showDiscountProduct(DiscountProduct dp) {
		if (dp == null) {
			System.out.println("商品が存在しません");
		} else {
			System.out.print(
					"Product: id=" + dp.getId() +
							", name=" + dp.getName() +
							", price=" + dp.getPrice() +
							", stock=" + dp.getStock() +
							", 割引率=" + dp.getDiscountRate());
		}
	}

	public static void main(String[] args) {
		//System.out.println("---商品を5つ追加して全て表示---");
		ProductManager manager = new ProductManager();

		Product p1 = new Product(1, "冷蔵庫", 50000, 10);
		Product p2 = new Product(2, "ソファ", 30000, 5);
		Product p3 = new Product(3, "米", 2000, 3);
		Product p4 = new Product(4, "小説", 1500, 4);
		Product p5 = new Product(5, "Tシャツ", 1500, 5);

		manager.addProduct(p1);
		manager.addProduct(p2);
		manager.addProduct(p3);
		manager.addProduct(p4);
		manager.addProduct(p5);

		/*showAllProduct(manager);
		System.out.println();
		
		System.out.println("---商品を一つ削除して全て表示---");
		
		manager.removeProduct(1);
		
		showAllProduct(manager);
		System.out.println();
		
		System.out.println("---商品名「米」の情報を表示する---");
		showProduct(manager.getProductByName("米"));
		*/

		DiscountProduct dp = new DiscountProduct(
				p2.getId(),
				p2.getName(),
				p2.getPrice(),
				p2.getStock(),
				0.3);

		int dicountedPrice = dp.calculateDiscountedPrice();

		System.out.println("---商品名「ソファ」の情報と割引率30%の情報を表示する---");
		showDiscountProduct(dp);
		System.out.println(",割引後価格=" + dicountedPrice);

		
		System.out.println("---商品名「Tシャツ」を検索して表示する---");
		List<Product> results = manager.search("Tシャツ");
		if (results.isEmpty()) {
			System.out.println("該当する商品がありません。");
		} else {
			for (Product p : results) {
				showProduct(p);
			}
		}
	}
}
