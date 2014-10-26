package bg.unisofia.fmi.imagegallery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class StarryNightPaintingActivity extends Activity {

	private ImageView starryNightView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starry_night_painting);
		starryNightView = (ImageView) findViewById(R.id.starry_night);
	}

	public void showName(View view) {
		Toast.makeText(this, "Starry Night", Toast.LENGTH_LONG).show();
	}
}
