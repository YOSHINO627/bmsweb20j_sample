package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.OrderedItem;

public class OrderedItemDAO {
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

	public ArrayList<OrderedItem> selectAll() {
		Connection con = null;
		Statement smt = null;
		ArrayList<OrderedItem> orderList = new ArrayList<>();
		try {
			String sql = "SELECT o.user,b.title,o.date"
					+ " FROM bookinfo b "
					+ "INNER JOIN orderinfo o ON b.isbn=o.isbn";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) {
				OrderedItem book = new OrderedItem();
				book.setUserid(rs.getString("user"));
				book.setTitle(rs.getString("title"));
				book.setDate(rs.getString("date"));
				orderList.add(book);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {smt.close();}
				if (con != null) {con.close();}
			} catch (SQLException ignore) {}
		}
		return orderList;
	}
}