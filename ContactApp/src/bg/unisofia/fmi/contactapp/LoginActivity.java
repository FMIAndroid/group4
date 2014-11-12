package bg.unisofia.fmi.contactapp;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private Button mLoginButton;
	private Button mRegisterButton;
	private SharedPreferences mPreferences;
	private EditText mUsernameEditText;
	private EditText mPasswordEditText;
	private User mCurrentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mLoginButton = (Button) findViewById(R.id.loginButton);
		mRegisterButton = (Button) findViewById(R.id.registerButton);
		mUsernameEditText = (EditText) findViewById(R.id.usernameEditText);
		mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);
		mCurrentUser = readUser();
	}
	
	private void updateUI() {
		if(isRegistered()) {
			onRegistered();
		} else {
			onNotRegistered();
		}
	}
	
	private User readUser() {
		final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		final String userString = preferences.getString(User.KEY, null);
		if(userString == null) {
			return null;
		} else {
			final Parcel parcel = Parcel.obtain();
			parcel.unmarshall(userString.getBytes(), 0, userString.getBytes().length);
			final User user = new User(parcel);
			parcel.recycle();
			return user;
		}
	}
	
	private void writeUser(User user) {
		final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		final Parcel parcel = Parcel.obtain();
		user.writeToParcel(parcel, 0);
		final String userString = Arrays.toString(parcel.marshall());
		preferences.edit().putString(User.KEY, userString).commit();
		parcel.recycle();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		updateUI();
	}

	public void login(View view) {
		if(mCurrentUser.getUsername().equals(mUsernameEditText.getText().toString()) &&
			 mCurrentUser.getPassword().equals(mPasswordEditText.getText().toString())) {
			final Intent intent = new Intent(this, ContactActivity.class);
			intent.putExtra(User.KEY, mCurrentUser);
			startActivity(intent);
		}
	}
	
	public void register(View view) {
		final User user = new User();
		user.setUsername(mUsernameEditText.getText().toString());
		user.setPassword(mPasswordEditText.getText().toString());
		mCurrentUser = user;
		writeUser(user);
		onRegistered();
	}
	
	private boolean isRegistered() {
		return mCurrentUser != null;
	}
	
	private void onNotRegistered() {
		mRegisterButton.setVisibility(View.VISIBLE);
		mLoginButton.setVisibility(View.GONE);
	}
	
	private void onRegistered() {
		mRegisterButton.setVisibility(View.GONE);
		mLoginButton.setVisibility(View.VISIBLE);
	}
}
