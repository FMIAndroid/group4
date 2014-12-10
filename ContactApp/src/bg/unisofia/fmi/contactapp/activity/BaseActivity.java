package bg.unisofia.fmi.contactapp.activity;

import android.app.Activity;

import bg.unisofia.fmi.contactapp.service.SessionService;
import bg.unisofia.fmi.contactapp.service.UserService;

public class BaseActivity extends Activity {
	
	private UserService userService;
	private SessionService sessionService;

	public UserService getUserService() {
		if(userService == null) {
			userService = new UserService(this);
		}
		return userService;
	}
	
	public SessionService getSessionService() {
		if(sessionService == null) {
			sessionService = new SessionService(this);
		}
		return sessionService;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		userService = null;
		sessionService = null;
	}
}
