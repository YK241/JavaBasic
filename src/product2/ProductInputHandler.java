package product2;

import java.util.Scanner;

public class ProductInputHandler {
	private Scanner sc;

	public ProductInputHandler(Scanner sc) {
		this.sc = sc;
	}

	public Product inputProduct() throws Exception {
		System.out.println("商品IDを入力してください: ");
		int id = sc.nextInt();
		System.out.println("入力された商品ID：" + id);
		sc.nextLine();
		if (id < 0)
			throw new Exception("商品IDがマイナスです");

		System.out.println("商品名を入力してください: ");
		String name = sc.nextLine();
		System.out.println("入力された商品名：" + name);
		if (name.isEmpty())
			throw new Exception("商品名を正しく入力してください。");

		System.out.println("価格を入力してください: ");
		int price = sc.nextInt();
		System.out.println("入力された価格：" + price);
		if (price < 0)
			throw new Exception("価格を正しく入力してください。");

		System.out.println("在庫数を入力してください: ");
		int stock = sc.nextInt();
		System.out.println("入力された在庫：" + stock);
		if (stock < 0)
			throw new Exception("在庫を正しく入力してください。");

		return new Product(id, name, price, stock);
	}
}
