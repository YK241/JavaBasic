package product2;

import java.util.Scanner;

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
		if (manager.getProducts().isEmpty()) {
			System.out.println("商品が存在しません");
			return;
		} else {
			for (Product p : manager.getProducts()) {
				showProduct(p);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ProductManager manager = new ProductManager();
		ProductInputHandler inputHandler = new ProductInputHandler(sc);

		while (true) {
			System.out.println("--メニュー--\n"
					+ "1:商品追加\n"
					+ "2:商品情報取得\n"
					+ "3:商品検索\n"
					+ "4:商品全て表示\n"
					+ "5:商品削除\n"
					+ "0:終了\n");
			System.out.println("メニューから操作を選択してください");

			int choice = sc.nextInt();
			sc.nextLine();

			if (choice == 1) {
				try {
					Product p = inputHandler.inputProduct();
					manager.addProduct(p);
					showProduct(p);
					System.out.println("を登録しました");
				} catch (Exception e) {
					System.out.println("無効な入力です。");
					e.printStackTrace();
				}

			} else if (choice == 2) {
				System.out.println("商品情報を取得する商品名を入力してください：");
				String name = sc.nextLine();
				Product found = manager.getProductByName(name);
				if (found == null) {
					System.out.print("該当する商品は存在しません");
					System.out.println("");
				} else {
					System.out.print("取得した商品は、");
					showProduct(found);
					System.out.println("");
				}

			} else if (choice == 3) {
				System.out.println("検索する商品名を入力してください：");
				String keyword = sc.nextLine();
				var results = manager.search(keyword);
				if (results.isEmpty()) {
					System.out.println("該当する商品は見つかりませんでした");
				} else {
					for (Product p : results) {
						showProduct(p);
						System.out.println("");
					}
				}
			} else if (choice == 4) {
				System.out.println("商品を全て表示します");
				showAllProduct(manager);
				System.out.println("");

			} else if (choice == 5) {
				System.out.println("削除する商品のIDを入力してください");
				int id = sc.nextInt();
				sc.nextLine();
				boolean removed = manager.removeProduct(id);
				if (removed) {
					System.out.println("商品IDが" + id + "の商品を削除しました");
				} else {
					System.out.println("指定されたIDは存在しません");
				}

			} else if (choice == 0) {
				System.out.println("終了します");
				sc.close();
				break;

			} else {
				System.out.println("無効な入力です");
			}
		}
	}

}
