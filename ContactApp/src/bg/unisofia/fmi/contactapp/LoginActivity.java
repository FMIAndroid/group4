package bg.unisofia.fmi.contactapp;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.stmt.QueryBuilder;

public class LoginActivity extends Activity {

	private static final String TAG = LoginActivity.class.getSimpleName();
	
	private Button mLoginButton;
	private Button mRegisterButton;
	private EditText mUsernameEditText;
	private EditText mPasswordEditText;
	private User mCurrentUser;
	private DatabaseHelper mDatabaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mLoginButton = (Button) findViewById(R.id.loginButton);
		mRegisterButton = (Button) findViewById(R.id.registerButton);
		mUsernameEditText = (EditText) findViewById(R.id.usernameEditText);
		mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);
		mDatabaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
		// getPreferences(MODE_PRIVATE).edit().remove(User.KEY).commit();
		mCurrentUser = readUser(getPreferences(MODE_PRIVATE).getInt(User.KEY, -1));
	}
	
	private void updateUI() {
		if(isRegistered()) {
			onRegistered();
		} else {
			onNotRegistered();
		}
	}
	
	private User readUser(int id) {
		return mDatabaseHelper.getUserDao().queryForId(id);
	}
	
	private User findUser(String username) {
		QueryBuilder<User, Integer> queryBuilder =
		  mDatabaseHelper.getUserDao().queryBuilder();
		List<User> results = null;
		try {
			queryBuilder.where().eq("username",
				    username);
			results = queryBuilder.query();
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return results.isEmpty() ? null : results.get(0);
	}
	
	private boolean writeUser(User user) {
		return mDatabaseHelper.getUserDao().create(user) == 1;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		updateUI();
	}

	public void login(View view) {
		User user = findUser(mUsernameEditText.getText().toString());
		if(user != null && user.getPassword().equals(mPasswordEditText.getText().toString())) {
			setCurrentUser(user);
			final Intent intent = new Intent(this, ContactActivity.class);
			intent.putExtra(User.KEY, mCurrentUser.getId());
			startActivity(intent);
		}
	}
	
	public void register(View view) {
		final User user = new User();
		user.setUsername(mUsernameEditText.getText().toString());
		user.setPassword(mPasswordEditText.getText().toString());
		mCurrentUser = user;
		writeUser(user);
		setCurrentUser(user);
		onRegistered();
	}
	
	private void setCurrentUser(User user) {
		mCurrentUser = user;
		getPreferences(MODE_PRIVATE).edit().putInt(User.KEY, user.getId()).commit();
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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		OpenHelperManager.releaseHelper();
		mDatabaseHelper = null;
	}
}
