package bg.unisofia.fmi.contactapp;

import bg.unisofia.fmi.contactapp.model.User;
import bg.unisofia.fmi.contactapp.service.SessionService;
import bg.unisofia.fmi.contactapp.service.UserService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	@SuppressWarnings("unused")
	private static final String TAG = LoginActivity.class.getSimpleName();

	private EditText usernameEditText;
	private EditText passwordEditText;

	private UserService userService;

	private SessionService sessionService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		userService = new UserService(this);
		sessionService = new SessionService(this);
	}

	public void login(View view) {
		final User user = userService.login(usernameEditText.getText()
				.toString(), passwordEditText.getText().toString());
		if (user != null) {
			Toast.makeText(this, "OK", Toast.LENGTH_LONG).show();
			sessionService.setCurrentUser(user);
			final Intent intent = new Intent(this, UserListActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show();
		}

	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		userService = null;
		sessionService = null;
	}

}
