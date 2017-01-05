package alvi17.liveclockwallpaper;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

/**
 * Analog Clock view
 * 
 * @author sylsau - sylvain.saurel@gmail.com - http://www.ssaurel.com
 *
 */
public class AnalogClock extends View {
	
	/** center X. */
	private float x;
	/** center Y. */
	private float y;
	private int radius;
	private Calendar cal;
	private Paint paint;
	private Bitmap clockDial = BitmapFactory.decodeResource(getResources(),
			R.drawable.clock5);
	private int sizeScaled = -1;
	private Bitmap clockDialScaled;
	/** Hands colors. */
	private int[] colors;
	private boolean displayHandSec;

	public AnalogClock(Context context) {
		super(context);
		cal = Calendar.getInstance();
	}
	
	public void config(float x, float y, int size, Date date, Paint paint, int[] colors, boolean displayHandSec) {
		this.x = x;
		this.y = y;
		this.paint = paint;
		this.colors = colors;
		this.displayHandSec = displayHandSec;
		
		cal.setTime(date);
		
		// scale bitmap if needed
		if (size != sizeScaled) {
			clockDialScaled = Bitmap.createScaledBitmap(clockDial, size, size, false);
			radius = size / 2;
		}
	}
	int i=0;
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if (paint != null) {
			// draw clock img

			canvas.drawBitmap(clockDialScaled, x - radius, y - radius, null);

			float sec = cal.get(Calendar.SECOND);
			float min = cal.get(Calendar.MINUTE);
			float hour = cal.get(Calendar.HOUR_OF_DAY);

			hour=Float.parseFloat((int)hour+"."+(int)min);
			//Log.e("AnalogClock","Hour: "+hour+" "+min);
			//draw hands
			paint.setColor(colors[0]);
			paint.setStrokeCap(Paint.Cap.ROUND);
			paint.setStrokeWidth((float) 8);
			canvas.drawLine(x, y, (float) (x + (radius * 0.5f) * Math.cos(Math.toRadians((hour / 12.0f * 360.0f) - 90f))),
					(float) (y + (radius * 0.5f) * Math.sin(Math.toRadians((hour / 12.0f * 360.0f) - 90f))), paint);
			RectF oval_hour = new RectF(x-12, y-12, x+12, y+12);
			canvas.drawOval(oval_hour, paint);
			canvas.save();

			paint.setColor(colors[1]);
			paint.setStrokeCap(Paint.Cap.ROUND);
			paint.setStrokeWidth((float) 6.5);
			canvas.drawLine(x, y, (float) (x + (radius * 0.6f) * Math.cos(Math.toRadians((min / 60.0f * 360.0f) - 90f))),
					(float) (y + (radius * 0.6f) * Math.sin(Math.toRadians((min / 60.0f * 360.0f) - 90f))), paint);
			RectF oval_min = new RectF(x-10, y-10, x+10, y+10);
			canvas.drawOval(oval_min, paint);
			canvas.save();
			
			if (displayHandSec) {
				paint.setColor(colors[2]);
				paint.setStrokeCap(Paint.Cap.ROUND);
				paint.setStrokeWidth((float) 5);
				canvas.drawLine((float)(x-(radius * 0.2f) * Math.cos(Math.toRadians((sec / 60.0f * 360.0f) - 90f))), (float) (y - (radius * 0.2f) * Math.sin(Math.toRadians((sec / 60.0f * 360.0f) - 90f))), (float) (x + (radius * 0.7f) * Math.cos(Math.toRadians((sec / 60.0f * 360.0f) - 90f))),
					(float) (y + (radius * 0.7f) * Math.sin(Math.toRadians((sec / 60.0f * 360.0f) - 90f))), paint);
				paint.setStyle(Paint.Style.FILL);
				RectF oval = new RectF(x-8, y-8, x+8, y+8);
				canvas.drawOval(oval, paint);
				canvas.save();
			}

			//paint.setColor(Color.BLACK);



//			paint.setColor(Color.BLACK);
//			paint.setStyle(Paint.Style.FILL);
//			RectF oval1 = new RectF(x-20, y-20, x+20, y+20);
//			canvas.drawRoundRect(oval1,x,y, paint);
//			canvas.save();


		}
	}
}