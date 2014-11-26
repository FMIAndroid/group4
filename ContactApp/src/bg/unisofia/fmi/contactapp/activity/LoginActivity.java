package bg.unisofia.fmi.contactapp.activity;

import bg.unisofia.fmi.contactapp.R;
import bg.unisofia.fmi.contactapp.R.id;
import bg.unisofia.fmi.contactapp.R.layout;
import bg.unisofia.fmi.contactapp.model.User;
import bg.unisofia.fmi.contactapp.service.SessionService;
import bg.unisofia.fmi.contactapp.service.UserService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

	@SuppressWarnings("unused")
	private static final String TAG = LoginActivity.class.getSimpleName();

	private EditText usernameEditText;
	private EditText passwordEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
	}

	public void login(View view) {
		final User user = getUserService().login(usernameEditText.getText()
				.toString(), passwordEditText.getText().toString());
		if (user != null) {
			Toast.makeText(this, "OK", Toast.LENGTH_LONG).show();
			getSessionService().setCurrentUser(user);
			final Intent intent = new Intent(this, UserListActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show();
		}

	}

}
