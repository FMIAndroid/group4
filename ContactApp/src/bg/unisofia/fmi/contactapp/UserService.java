package bg.unisofia.fmi.contactapp;

import java.util.ArrayList;

import android.content.res.Resources;
import android.content.res.TypedArray;

public class UserService {
	private ArrayList<User> users;
	
	public UserService(Resources res) {
		final TypedArray userRes = res.obtainTypedArray(R.array.users);
		final int count = userRes.length();
		users = new ArrayList<User>(count);
		for(int i = 0; i < count; ++i) {
			final TypedArray userData = res.obtainTypedArray(userRes.getResourceId(i, 0));
			final User user = new User();
			user.setUsername(userData.getString(0));
			user.setPassword(userData.getString(1));
			users.add(user);
			userData.recycle();
		}
		userRes.recycle();
	}
	
	public static class LoginResult {
		public User user;
		public String error;
		public boolean successful;
		
		public LoginResult(User user) {
			successful = true;
			error = null;
			this.user = user;
		}
		
		public LoginResult(String error) {
			successful = false;
			this.error = error;
		}
	}
	
	public LoginResult login(String username, String password) {
		for(User user: users) {
			if(user.getUsername().equals(username)) {
				if(user.getPassword().equals(password)) {
					return new LoginResult(user);
				} else {
					return new LoginResult("Wrong password");
				}
			}
		}
		return new LoginResult("Not registered");
	}
}
