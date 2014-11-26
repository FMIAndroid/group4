package bg.unisofia.fmi.contactapp.service;

import bg.unisofia.fmi.contactapp.model.User;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

public class SessionService {
	
	private static final String SESSION_PREFERENCES = "SessionPreferences";
	
	private static final String USERNAME_KEY = "username";
	
	private static final String EMAIL_KEY = "email";
	
	private static final String PHONE_KEY = "phone";
	
	private static final String FULL_NAME_KEY = "fullName";
	
	public static final String SELECTED_USER_INDEX = "selectedUserIndex";

	private SharedPreferences preferences;

	public SessionService(Context context) {
		preferences = context.getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE);
	}
	
	public void setCurrentUser(User user) {
		preferences.edit()
			.putString(USERNAME_KEY, user.getUsername())
			.putString(EMAIL_KEY, user.getEmail())
			.commit();
	}
	
	public User getCurrentUser() {
		final User user = new User();
		user.setUsername(preferences.getString(USERNAME_KEY, ""));
		user.setEmail(preferences.getString(EMAIL_KEY, ""));
		user.setPhone(preferences.getString(PHONE_KEY, ""));
		user.setFullName(preferences.getString(FULL_NAME_KEY, ""));
		return user;
	}
	
	public void setSelectedUserIndex(int index) {
		preferences.edit()
			.putInt(SELECTED_USER_INDEX, index)
			.commit();
	}
	
	public int getSelectedUserIndex() {
		return preferences.getInt(SELECTED_USER_INDEX, 0);
	}
	
	public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
		preferences.registerOnSharedPreferenceChangeListener(listener);
	}
	
	public void clear() {
		preferences.edit().clear().commit();
	}
}
