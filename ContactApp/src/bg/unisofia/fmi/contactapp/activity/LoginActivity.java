package bg.unisofia.fmi.contactapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bg.unisofia.fmi.contactapp.R;
import bg.unisofia.fmi.contactapp.model.User;

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
