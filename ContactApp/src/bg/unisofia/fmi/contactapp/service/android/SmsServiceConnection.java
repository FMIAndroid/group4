package bg.unisofia.fmi.contactapp.service.android;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

public class SmsServiceConnection implements ServiceConnection{

	private SmsService.LocalBinder binder;
	
	public SmsService getService() {
		return binder.getService();
	}
	
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		binder = (SmsService.LocalBinder) service;
		getService().sendMessage("123", "hi");
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		binder = null;
	}
	
}
