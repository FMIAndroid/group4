package bg.unisofia.fmi.contactapp;

import android.content.res.Configuration;
import android.os.Bundle;

public class UserInfoActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			return;
		}
		setContentView(R.layout.activity_user_info);
	}
}
