package bg.unisofia.fmi.contactapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import bg.unisofia.fmi.contactapp.model.User;
import bg.unisofia.fmi.contactapp.service.SessionService;
import bg.unisofia.fmi.contactapp.service.UserService;

public class UserInfoFragment extends Fragment {
	
	private SessionService sessionService;
	
	private User user;

	private UserService userService;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		userService = new UserService(activity);
		sessionService = new SessionService(activity);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		update();
		sessionService.registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener() {
			
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
					String key) {
				if(key == SessionService.SELECTED_USER_INDEX) {
					update();
				}
			}
		});
	}
	
	private void update() {
		user = userService.getUser(sessionService.getSelectedUserIndex());
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		sessionService = null;
	}
}
