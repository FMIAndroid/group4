package bg.unisofia.fmi.contactapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactActivity extends Activity implements OnLongClickListener {

	private static final int PICK_PHOTO = 1;
	
	private static final int PICK_PHONE = 2;

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
		switch(view.getId()) {
		case R.id.photoImageView:
			final Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			startActivityForResult(photoPickerIntent, PICK_PHOTO);
			break;
		case R.id.phoneTextView:
			final Intent phonePickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
			phonePickerIntent.setType("vnd.android.cursor.item/phone");
			startActivityForResult(phonePickerIntent, PICK_PHONE);
			break;
		};
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode != Activity.RESULT_OK) {
			return;
		}
		switch(requestCode) {
		case PICK_PHOTO:
			Uri photo = data.getData();
			photoImageView.setImageURI(photo);
			break;
		case PICK_PHONE:
			Uri phone = data.getData();
			break;
		}
	}
}
