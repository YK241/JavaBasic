package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDB {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/product_management?serverTimezone=UTC";
		String user = "root";
		String pass = "pass"; // mysqlのpassの為、仮名

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("DB接続成功");
			System.out.println(	"--productsテーブルのすべての商品情報を表示--");

			stmt = conn.createStatement();

			String sql = "SELECT * FROM products";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				int categoryId = rs.getInt("category_id");

				System.out.println(
								"id:" + id + "\n" +
								"name:" + name + "\n" +
								"price:" + price + "\n" +
								"stock:" + stock + "\n" +
								"category_id:" + categoryId + "\n");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("DB接続失敗");
			System.out.println("JDBCドライバが見つかりません");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB接続失敗");
			System.out.println("DB接続エラー");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("リソースクローズエラー: " + e.getMessage());
			}
		}
	}
}
