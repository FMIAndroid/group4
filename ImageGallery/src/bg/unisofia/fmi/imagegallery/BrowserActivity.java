package bg.unisofia.fmi.imagegallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class BrowserActivity extends Activity implements OnClickListener {

	private static final String TAG = BrowserActivity.class.getSimpleName();

	private static final int[] IMAGE_RES = new int[] { R.drawable.mona_lisa,
			R.drawable.starry_night };

	private static final Class<?>[] ACTIVITY_CLASS = new Class<?>[] {
			MonaLisaPaintingActivity.class, StarryNightPaintingActivity.class };

	private ImageView imageView;

	private Button previousButton;

	private Button nextButton;

	private int currentImageId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		setContentView(R.layout.activity_browser);
		currentImageId = 0;
		imageView = (ImageView) findViewById(R.id.image);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent intent = new Intent(BrowserActivity.this,
						ACTIVITY_CLASS[currentImageId]);
				startActivity(intent);
			}
		});
		previousButton = (Button) findViewById(R.id.previous);
		previousButton.setOnClickListener(this);
		nextButton = (Button) findViewById(R.id.next);
		nextButton.setOnClickListener(this);
		show(currentImageId);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy");
	}

	public void show(int imageId) {
		currentImageId = imageId;
		imageView.setImageResource(IMAGE_RES[imageId]);
		previousButton.setEnabled(imageId > 0);
		nextButton.setEnabled(imageId < IMAGE_RES.length - 1);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.previous:
			show(currentImageId - 1);
			break;
		case R.id.next:
			show(currentImageId + 1);
			break;
		}
	}
}
