package bean;

public class Order {
	private int orderno;
	private String userid;
	private String isbn;
	private int quantity;
	private String date;

	public Order() {
		this.orderno = 0;
		this.userid = null;
		this.isbn = null;
		this.quantity = 0;
		this.date = null;
	}

	public Order(int orderno, String userid, String isbn, int quantity, String date) {
		this.orderno = orderno;
		this.userid = userid;
		this.isbn = isbn;
		this.quantity = quantity;
		this.date = date;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
