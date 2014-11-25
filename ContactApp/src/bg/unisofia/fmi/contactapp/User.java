package bg.unisofia.fmi.contactapp;


public class User {
	
	public static final String KEY = User.class.getSimpleName();
	
	private String username;
	
	private String password;
	
	private String phone;
	
	private String email;
	
	private boolean isAdmin;
	
	public User() {
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {		
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
