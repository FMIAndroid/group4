package bg.unisofia.fmi.imagegallery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MonaLisaPaintingActivity extends Activity {

	private ImageView monaLisaView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mona_lisa_painting);
		monaLisaView = (ImageView) findViewById(R.id.mona_lisa);
	}

	public void showName(View view) {
		Toast.makeText(this, "Mona Lisa", Toast.LENGTH_LONG).show();
	}

}
