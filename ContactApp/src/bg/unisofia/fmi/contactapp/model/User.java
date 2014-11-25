package bg.unisofia.fmi.contactapp.model;


public class User {
		
	private String username;
	
	private String password;
	
	private String fullName;
	
	private String phone;
	
	private String email;
	
	private int imageResource;
	
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getImageResource() {
		return imageResource;
	}

	public void setImageResource(int imageResource) {
		this.imageResource = imageResource;
	}
	
}
