package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.User;

public class UserDAO {
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

	public User selectByUser(String userid) {
		Connection con = null;
		Statement smt = null;
		User user = new User();
		try {
			String sql = "SELECT * FROM userinfo WHERE user ='" + userid + "'";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) {
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {smt.close();}
				if (con != null) {con.close();}
			} catch (SQLException ignore) {}
		}
		return user;
	}

	public User selectByUser(String userid, String password) {
		Connection con = null;
		Statement smt = null;
		User user = new User();
		
		try {
			String sql = "SELECT * FROM userinfo "
					+ "WHERE user ='"+userid+"' "
					+ "AND password='"+password+"'";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {smt.close();}
				if (con != null) {con.close();}
			} catch (SQLException ignore) {}
		}
		return user;
	}
}
