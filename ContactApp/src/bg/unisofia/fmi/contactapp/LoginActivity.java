package bg.unisofia.fmi.contactapp;

import bg.unisofia.fmi.contactapp.UserService.LoginResult;
import android.app.Activity;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		userService = new UserService(getResources());
	}

	public void login(View view) {
		final LoginResult loginResult = userService.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
		if(loginResult.successful) {
			Toast.makeText(this, "OK", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, loginResult.error, Toast.LENGTH_LONG).show();
		}
	}
}
