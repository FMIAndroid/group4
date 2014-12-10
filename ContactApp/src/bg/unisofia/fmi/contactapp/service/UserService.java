package bg.unisofia.fmi.contactapp.service;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import bg.unisofia.fmi.contactapp.R;
import bg.unisofia.fmi.contactapp.model.User;

public class UserService {
	private ArrayList<User> users;
	
	public UserService(Context context) {
		final TypedArray userRes = context.getResources().obtainTypedArray(R.array.users);
		final int count = userRes.length();
		users = new ArrayList<User>(count);
		for(int i = 0; i < count; ++i) {
			final int userResId = userRes.getResourceId(i, 0);
			final TypedArray userData = context.getResources().obtainTypedArray(userResId);
			final User user = new User();
			user.setUsername(userData.getString(0));
			user.setPassword(userData.getString(1));
			user.setFullName(userData.getString(2));
			user.setImageResource(userData.getResourceId(3,0));
			user.setEmail(userData.getString(4));
			user.setPhone(userData.getString(5));
			users.add(user);
			userData.recycle();
		}
		userRes.recycle();
	}
	
	public User login(String username, String password) {
		for(User user: users) {
			if(user.getUsername().equals(username)) {
				if(user.getPassword().equals(password)) {
					return user;
				}
			}
		}
		return null;
	}
	
	public User getUser(int index) {
		return users.get(index);
	}
	
	public List<User> getAllUsers() {
		return users;
	}
}
