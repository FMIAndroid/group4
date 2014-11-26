package bg.unisofia.fmi.contactapp.activity;

import bg.unisofia.fmi.contactapp.R;
import bg.unisofia.fmi.contactapp.R.layout;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class UserListActivity extends BaseActivity implements OnItemClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		getSessionService().setSelectedUserIndex(position);
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			final Intent intent = new Intent(this,
					UserInfoActivity.class);
			startActivity(intent);
		}

	}
}
