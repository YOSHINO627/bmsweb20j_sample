package bean;

public class Book {
	private String isbn;
	private String title;
	private int price;

	public Book() {
		this.isbn = null;
		this.title = null;
		this.price = 0;
	}

	public Book(String isbn, String title, int price) {
		this.isbn = isbn;
		this.title = title;
		this.price = price;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public String getTitle() {
		return this.title;
	}

	public int getPrice() {
		return this.price;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}