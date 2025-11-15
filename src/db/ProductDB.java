package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
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
							+ "1:商品の登録\n"
							+ "2:商品の価格と在庫を更新\n"
							+ "3:商品の削除（カテゴリーID指定）\n"
							+ "4:複数商品の在庫更新\n"
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
					} else if (choice == 4) {
						updateMultipleProduct(conn, sc);
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

		System.out.println("商品名を入力してください：");
		String name = sc.nextLine();
		System.out.println("価格を入力してください：");
		int price = sc.nextInt();
		System.out.println("在庫数を入力してください：");
		int stock = sc.nextInt();
		System.out.println("カテゴリーIDを入力してください：");
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
			System.out.println("登録成功件数" + rows + "件");
			System.out.println("登録内容：\n" +
					"商品名：" + name + ", 価格：" + price + "在庫数：" + stock + "カテゴリーID：" + categoryId);
			sc.nextLine();

		} catch (SQLException e) {
			System.out.println("商品追加中にエラーが発生しました");
			e.printStackTrace();
		}
	}

	private static void updateProduct(Connection conn, Scanner sc) {

		System.out.println("商品IDを入力してください");
		int id = sc.nextInt();
		System.out.println("価格を入力してください");
		int newPrice = sc.nextInt();
		System.out.println("在庫数を入力してください");
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
				System.out.println("更新成功件数：" + rows + "件");
			} else {
				System.out.println("更新失敗");
			}

		} catch (SQLException e) {
			System.out.println("商品更新中にエラーが発生しました");
			e.printStackTrace();
		}
	}

	private static void deleteProduct(Connection conn, Scanner sc) {

		System.out.println("削除するカテゴリーIDを入力してください");
		int categoryId = sc.nextInt();
		sc.nextLine();

		String sql = "DELETE FROM products WHERE category_id = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, categoryId);

			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				System.out.println("削除成功件数：" + rows + "件");
				System.out.println("カテゴリーID " + categoryId + " の商品を削除しました。");
			} else {
				System.out.println("指定されたカテゴリーIDの商品が見つかりません");
			}

		} catch (SQLException e) {
			System.out.println("商品削除中にエラーが発生しました");
			e.printStackTrace();
		}
	}

	private static void updateMultipleProduct(Connection conn, Scanner sc) {

		Map<Integer, int[]> productMap = new LinkedHashMap<>();

		System.out.println("複数の商品IDと新しい在庫数を入力してください");
		System.out.println("入力を終える際は0と入力してください");

		int count = 1;

		while (true) {
			System.out.println("--商品の価格と在庫を更新" + count + "--");

			System.out.println("商品IDを入力してください");
			int id = sc.nextInt();
			if (id == 0)
				break;

			System.out.println("価格を入力してください");
			int price = sc.nextInt();

			System.out.println("在庫数を入力してください");
			int stock = sc.nextInt();

			if (price <= 0 || stock < 0) {
				System.out.println("価格、または在庫数の入力が不正なため、再入力してください");
				continue;
			}

			productMap.put(id, new int[] { price, stock });
			count++;
		}

		if (productMap.isEmpty()) {
			System.out.println("更新対象がありません");
			return;
		}

		String sql = "UPDATE products SET price = ?, stock = ? WHERE id = ?";
		String selectSql = "SELECT id, price, stock FROM products WHERE id = ?";

		try {
			conn.setAutoCommit(false);

			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

				int successCount = 0;
				int totalCount = productMap.size();

				for (Map.Entry<Integer, int[]> entry : productMap.entrySet()) {
					int id = entry.getKey();
					int price = entry.getValue()[0];
					int stock = entry.getValue()[1];

					pstmt.setInt(1, price);
					pstmt.setInt(2, stock);
					pstmt.setInt(3, id);

					int rows = pstmt.executeUpdate();
					if (rows == 0) {
						System.out.println(totalCount + "件全ての更新に失敗しました。");
						System.out.println("更新成功件数： " + successCount + "件");
						System.out.println("ロールバックしました");
						conn.rollback();
						return;
					}
					successCount++;
				}

				conn.commit();
				System.out.println("コミット成功");
				sc.nextLine();
				System.out.println("更新成功件数" + successCount + "件\n");

				try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
					int updateCount = 1;
					for (int id : productMap.keySet()) {
						selectStmt.setInt(1, id);
						try (ResultSet rs = selectStmt.executeQuery()) {
							if (rs.next()) {
								System.out.println("更新内容" + updateCount);
								System.out.println(
										"商品ID： " + rs.getInt("id") +
												", 価格： " + rs.getInt("price") +
												", 在庫： " + rs.getInt("stock"));
							} else {
								System.out.println("商品ID：" + id + " は存在しません。");
							}
						}
						updateCount++;
					}
				}

			} catch (SQLException e) {
				System.out.println("商品更新中にエラーが発生しました。ロールバックします");
				conn.rollback();
				e.printStackTrace();
			} finally {
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					System.out.println("自動コミット中にエラーが発生しました");
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			System.out.println("トランザクション処理中にエラーが発生しました");
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
