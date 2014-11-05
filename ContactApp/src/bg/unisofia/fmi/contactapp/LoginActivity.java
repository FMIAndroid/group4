package bg.unisofia.fmi.contactapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void login(View view) {
		final Intent intent = new Intent(this, ContactActivity.class);
		startActivity(intent);
	}
}
