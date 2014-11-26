package bg.unisofia.fmi.contactapp.fragment;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import bg.unisofia.fmi.contactapp.R;
import bg.unisofia.fmi.contactapp.model.User;
import bg.unisofia.fmi.contactapp.service.SessionService;

public class UserInfoFragment extends BaseFragment implements OnSharedPreferenceChangeListener{
	
	@SuppressWarnings("unused")
	private static final String TAG = UserInfoFragment.class.getSimpleName();
	
	private User user;

	private TextView fullNameView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_info, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fullNameView = (TextView) getView().findViewById(R.id.fullNameValue);
		update();
		getBaseActivity().getSessionService().registerOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if(key == SessionService.SELECTED_USER_INDEX) {
			update();
		}
	}
	
	private void update() {
		user = getBaseActivity().getUserService().getUser(getBaseActivity().getSessionService().getSelectedUserIndex());
		fullNameView.setText(user.getFullName());
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		getBaseActivity().getSessionService().unregisterOnSharedPreferenceChangeListener(this);
	}
}
