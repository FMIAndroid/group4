package bg.unisofia.fmi.contactapp.service.android;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.SmsManager;

public class SmsService extends Service {

	public class LocalBinder extends Binder {
		public SmsService getService() {
			return SmsService.this;
		}
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_NOT_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return new LocalBinder();
	}
	
	public void sendMessage(String phone, String message) {
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phone, null, message, null, null);
	}

}
