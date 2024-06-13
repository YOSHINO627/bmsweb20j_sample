package bean;

public class User {
	private String userid;
	private String password;
	private String email;
	private String authority;

	public User() {
		this.userid = null;
		this.password = null;
		this.email = null;
		this.authority = null;
	}

	public User(String userid, String password, String email, String authority) {
		this.userid = userid;
		this.password = password;
		this.email = email;
		this.authority = authority;
	}

	public String getUserid() {
		return this.userid;
	}

	public String getPassword() {
		return this.password;
	}

	public String getEmail() {
		return this.email;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
