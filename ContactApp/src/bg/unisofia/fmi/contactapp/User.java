package bg.unisofia.fmi.contactapp;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User {
	
	public static final String KEY = User.class.getSimpleName();
	
	@DatabaseField(generatedId = true, columnName = "_id")
	private int id;
	
	@DatabaseField
	private String username;
	
	@DatabaseField
	private String password;
	
	@DatabaseField
	private String phone;
	
	@DatabaseField
	private String email;
	
	public User() {
	}
	
	public int getId() {
		return id;
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
	
}
