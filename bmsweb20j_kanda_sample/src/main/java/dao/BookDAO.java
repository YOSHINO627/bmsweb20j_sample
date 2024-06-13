package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Book;

public class BookDAO {
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

	public ArrayList<Book> selectAll() {
		Connection con = null;
		Statement smt = null;
		ArrayList<Book> bookList = new ArrayList<>();
		try {
			String sql = "SELECT isbn,title,price FROM bookinfo ORDER BY isbn";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {smt.close();}
				if (con != null) {con.close();}
			} catch (SQLException ignore) {}
		}
		return bookList;
	}

	public void insert(Book book) {
		Connection con = null;
		Statement smt = null;
		try {
			String sql = "INSERT INTO bookinfo(isbn,title,price) "
					+ "VALUES('" + book.getIsbn() + "','" 
								+ book.getTitle()+ "', " 
								+ book.getPrice() + ")";
			con = getConnection();
			smt = con.createStatement();
			smt.executeUpdate(sql);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {smt.close();}
				if (con != null) {con.close();}
			} catch (SQLException ignore) {}
		}
	}

	public Book selectByIsbn(String isbn) {
		Connection con = null;
		Statement smt = null;
		Book book = new Book();
		try {
			String sql = "SELECT isbn,title,price FROM bookinfo WHERE isbn='" + isbn + "'";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) {
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {smt.close();}
				if (con != null) {con.close();}
			} catch (SQLException ignore) {}
		}
		return book;
	}

	public void delete(String isbn) {
		Connection con = null;
		Statement smt = null;
		try {
			String sql = "DELETE FROM bookinfo WHERE isbn = '" + isbn + "'";
			con = getConnection();
			smt = con.createStatement();
			smt.executeUpdate(sql);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {smt.close();}
				if (con != null) {con.close();}
			} catch (SQLException ignore) {}
		}
	}
	public void update(Book book) {
		Connection con = null;
		Statement smt = null;
		try {
			String sql = "UPDATE bookinfo SET "
					+ "title='" + book.getTitle()
					+ "',price=" + book.getPrice()
					+ " WHERE isbn='" + book.getIsbn() + "'";
			con = getConnection();
			smt = con.createStatement();
			smt.executeUpdate(sql);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {smt.close();}
				if (con != null) {con.close();}
			} catch (SQLException ignore) {}
		}
	}

	public ArrayList<Book> search(String isbn, String title, String price) {
		Connection con = null;
		Statement smt = null;
		ArrayList<Book> bookList = new ArrayList<>();
		try {
			String sql = "SELECT isbn,title,price FROM bookinfo " +
					"WHERE isbn LIKE '%" + isbn + "%'"
					+ " AND title LIKE '%" + title + "%'"
					+ " AND price LIKE '%" + price + "%'";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if (smt != null) {smt.close();}
				if (con != null) {con.close();}
			} catch (SQLException ignore) {}
		}
		return bookList;
	}
}
