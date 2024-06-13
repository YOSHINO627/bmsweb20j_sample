package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Order;

public class OrderDAO {
	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/mybookdb";
	private static String USER = "root";
	private static String PASSWD = "root123";
	
	public static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public void insert(Order order) {
		Connection con = null;
		Statement smt = null;
		try {
			String sql = "INSERT INTO "
					+ "orderinfo VALUES(NULL,'"
					+ order.getUserid() + "','"
					+ order.getIsbn() + "',"
					+ order.getQuantity() + ",CURDATE())";
			con = getConnection();
			smt = con.createStatement();
			smt.executeUpdate(sql);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {smt.close();}
				if (con != null) {con.close();}
			} catch (SQLException ignore) {
			}
		}
	}

}
