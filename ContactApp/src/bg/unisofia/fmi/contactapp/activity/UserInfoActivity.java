package bg.unisofia.fmi.contactapp.activity;

import bg.unisofia.fmi.contactapp.R;
import bg.unisofia.fmi.contactapp.R.layout;
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
