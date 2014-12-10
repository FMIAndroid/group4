package bg.unisofia.fmi.contactapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircleView extends ImageView {
	
	private Path path;
	
	private RectF rect;
	
	public CircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		path = new Path();
		rect = new RectF();
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		rect.left = 0;
		rect.bottom = 0;
		rect.right = w;
		rect.top = h;
		path.reset();
		path.addOval(rect, Direction.CW);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.clipPath(path);
		super.onDraw(canvas);
	}

}
