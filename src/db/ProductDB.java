package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductDB {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/product_management?serverTimezone=UTC";
		String user = "root";
		String pass = "pass"; // mysqlのpassの為、仮名

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection conn = DriverManager.getConnection(url, user, pass);
					Scanner sc = new Scanner(System.in)) {

				while (true) {

					System.out.println("--メニュー--\n"
							+ "1:商品追加\n"
							+ "2:商品情報更新\n"
							+ "3:商品削除\n"
							+ "0:終了\n");
					System.out.println("メニューから操作を選択してください");

					int choice = sc.nextInt();
					sc.nextLine();

					if (choice == 1) {
						insertProduct(conn, sc);
					} else if (choice == 2) {
						updateProduct(conn, sc);
					} else if (choice == 3) {
						deleteProduct(conn, sc);
					} else if (choice == 0) {
						System.out.println("終了します");
						break;
					} else {
						System.out.println("無効な入力です");
					}
				}

			} catch (SQLException e) {
				System.out.println("DB接続エラー");
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません");
			e.printStackTrace();
		}
	}

	private static void insertProduct(Connection conn, Scanner sc) {

		System.out.println("商品名を入力してください");
		String name = sc.nextLine();
		System.out.println("価格を入力してください");
		int price = sc.nextInt();
		System.out.println("在庫数を入力してください");
		int stock = sc.nextInt();
		System.out.println("カテゴリーIDを入力してください");
		int categoryId = sc.nextInt();

		if (!validateProductInput(name, price, stock)) {
			System.out.println("不正な入力の為、商品追加を中止します");
			return;
		}

		String sql = "INSERT INTO products (name, price, stock, category_id) VALUES (?,?,?,?)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setInt(2, price);
			pstmt.setInt(3, stock);
			pstmt.setInt(4, categoryId);

			int rows = pstmt.executeUpdate();
			System.out.println(rows + "件の商品を追加しました");
			sc.nextLine();

		} catch (SQLException e) {
			System.out.println("商品追加中にエラーが発生しました");
			e.printStackTrace();
		}
	}

	private static void updateProduct(Connection conn, Scanner sc) {

		System.out.println("更新する商品IDを入力してください");
		int id = sc.nextInt();
		System.out.println("新しい価格を入力してください");
		int newPrice = sc.nextInt();
		System.out.println("新しい在庫数を入力してください");
		int newStock = sc.nextInt();

		if (newPrice <= 0 || newStock < 0) {
			System.out.println("価格、または在庫数の入力が不正です");
			return;
		}

		String sql = "UPDATE products SET price = ?, stock = ? WHERE id = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, newPrice);
			pstmt.setInt(2, newStock);
			pstmt.setInt(3, id);

			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				System.out.println("商品情報を更新しました");
			} else {
				System.out.println("指定されたIDの商品が見つかりません");
			}

		} catch (SQLException e) {
			System.out.println("商品更新中にエラーが発生しました");
			e.printStackTrace();
		}
	}

	private static void deleteProduct(Connection conn, Scanner sc) {

		System.out.println("削除する商品カテゴリーIDを入力してください");
		int categoryId = sc.nextInt();
		sc.nextLine();

		String sql = "DELETE FROM products WHERE category_id = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, categoryId);

			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				System.out.println(rows + "件の商品を削除しました");
			} else {
				System.out.println("指定されたカテゴリーIDの商品が見つかりません");
			}

		} catch (SQLException e) {
			System.out.println("商品削除中にエラーが発生しました");
			e.printStackTrace();
		}
	}

	private static boolean validateProductInput(String name, int price, int stock) {
		if (name == null || name.trim().isEmpty()) {
			System.out.println("商品名は空白にできません");
			return false;
		}
		if (price <= 0) {
			System.out.println("価格は1以上を入力してください");
			return false;
		}
		if (stock < 0) {
			System.out.println("在庫数は0以上を入力してください");
			return false;
		}
		return true;
	}
}
