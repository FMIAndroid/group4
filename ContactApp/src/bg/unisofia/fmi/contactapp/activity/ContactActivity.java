package bg.unisofia.fmi.contactapp.activity;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import bg.unisofia.fmi.contactapp.R;

public class ContactActivity extends Activity implements OnLongClickListener {

	private static final int PICK_PHOTO = 1;

	private static final int PICK_PHONE = 2;
	
	private static final String TAG = ContactActivity.class.getSimpleName();

	private ImageView photoImageView;

	private TextView phoneTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		photoImageView = (ImageView) findViewById(R.id.photoImageView);
		photoImageView.setOnLongClickListener(this);
		phoneTextView = (TextView) findViewById(R.id.phoneTextView);
		phoneTextView.setOnLongClickListener(this);
	}

	@Override
	public boolean onLongClick(View view) {
		switch (view.getId()) {
		case R.id.photoImageView:
			final Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			startActivityForResult(photoPickerIntent, PICK_PHOTO);
			break;
		case R.id.phoneTextView:
			final Intent phonePickerIntent = new Intent(
					Intent.ACTION_GET_CONTENT);
			phonePickerIntent.setType("vnd.android.cursor.item/phone");
			startActivityForResult(phonePickerIntent, PICK_PHONE);
			break;
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != Activity.RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case PICK_PHOTO:
			Uri photo = data.getData();
			photoImageView.setImageURI(photo);
			break;
		case PICK_PHONE:
			Uri phoneUri = data.getData();
			Cursor cursor = getContentResolver().query(phoneUri, null, null,
					null, null);
			String phoneNumber;
			if (cursor.moveToFirst()) {
				while (cursor.moveToNext()) {
					phoneNumber = cursor.getString(cursor
							.getColumnIndex(Phone.NUMBER));
					Log.i(TAG, "phone number is: " + phoneNumber);
				}
			}
			break;
		}
	}
}
