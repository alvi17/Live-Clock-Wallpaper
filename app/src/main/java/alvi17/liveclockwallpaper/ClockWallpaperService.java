package alvi17.liveclockwallpaper;

import java.util.Date;
import java.util.Random;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Clock wallpaper service
 * 
 * @author sylsau - sylvain.saurel@gmail.com - http://www.ssaurel.com
 *
 */
public class ClockWallpaperService extends WallpaperService {

	@Override
	public Engine onCreateEngine() {
		return new ClockWallpaperEngine();
	}

	private class ClockWallpaperEngine extends Engine implements
			OnSharedPreferenceChangeListener {
		private final Handler handler = new Handler();
		private final Runnable drawRunner = new Runnable() {
			@Override
			public void run() {
				draw();
			}

		};

		private Paint paint;
		/** hands colors for hour, min, sec */
		private int[] colors = { 0xFF000000, 0xFF000000, 0xFFFF0000 };
		private int bgColor;
		private int width;
		private int height;
		private boolean visible = true;
		private boolean displayHandSec;
		private AnalogClock clock;
		private SharedPreferences prefs;
		boolean displayCenter,displaytopleft,displaytopright,displaybottomleft,displaybottomright,width40,wifth50,width65;
		Integer[] backs={R.drawable.wall1,R.drawable.wall2,R.drawable.wall3,R.drawable.wall4,R.drawable.wall5,R.drawable.wall6};

		int back=0;

		float size_factor;

		public ClockWallpaperEngine() {

			prefs = PreferenceManager
					.getDefaultSharedPreferences(ClockWallpaperService.this);
			prefs.registerOnSharedPreferenceChangeListener(this);
			displayHandSec = prefs.getBoolean(SettingsActivity.DISPLAY_HAND_SEC_KEY, true);
			displayCenter=prefs.getBoolean("center",true);
			displaybottomright=prefs.getBoolean("bottomright",false);
			displaybottomleft=prefs.getBoolean("bottomleft",false);
			displaytopleft=prefs.getBoolean("topleft",false);
			displaytopright=prefs.getBoolean("topright",false);
			width40=prefs.getBoolean("clockwidth40",false);
			wifth50=prefs.getBoolean("clockwidth50",true);
			width65=prefs.getBoolean("clockwidth65",false);

			size_factor=(float)0.5;
			back= new Random().nextInt(backs.length);

			paint = new Paint();
			paint.setAntiAlias(true);
			paint.setStyle(Paint.Style.FILL_AND_STROKE);

			bgColor = Color.parseColor("#3F51B5");
			clock = new AnalogClock(getApplicationContext());
			handler.post(drawRunner);
		}

		@Override
		public void onVisibilityChanged(boolean visible) {
			this.visible = visible;
			if (visible) {
				handler.post(drawRunner);
			} else {
				handler.removeCallbacks(drawRunner);
			}
		}

		@Override
		public void onSurfaceDestroyed(SurfaceHolder holder) {
			super.onSurfaceDestroyed(holder);
			this.visible = false;
			handler.removeCallbacks(drawRunner);
			prefs.unregisterOnSharedPreferenceChangeListener(this);
		}

		@Override
		public void onSurfaceChanged(SurfaceHolder holder, int format,
				int width, int height) {
			this.width = width;
			this.height = height;
			super.onSurfaceChanged(holder, format, width, height);
		}

		private void draw() {
			SurfaceHolder holder = getSurfaceHolder();
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas();
				if (canvas != null) {
					draw(canvas);
				}
			} finally {
				if (canvas != null)
					holder.unlockCanvasAndPost(canvas);
			}

			handler.removeCallbacks(drawRunner);

			if (visible) {
				handler.postDelayed(drawRunner, 200);
			}
		}
		int i=0;

		private void draw(Canvas canvas) {
//			canvas.drawColor(Color.argb(100,230,12,153));

			Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),
						backs[back]), width, height, false);
			canvas.drawBitmap(bitmap, 0, 0, null);

			if(wifth50)
			{
				size_factor=(float) 0.5;
				paint.setStrokeWidth(4);
			}
			else if(width40)
			{
				size_factor=(float).40;
				paint.setStrokeWidth(3);
			}
			else if(width65)
			{
				size_factor=(float).65;
				paint.setStrokeWidth(5);
			}
			else
			{
				size_factor=(float) 0.5;
				paint.setStrokeWidth(4);
			}


			if(displayCenter)
			{
				clock.config(width/2,height/2, (int) (width * size_factor),
						new Date(), paint, colors, displayHandSec);
			}
			else if(displaytopright) {
				clock.config(width - width *size_factor/2, width *size_factor/2 + 40, (int) (width * size_factor),
						new Date(), paint, colors, displayHandSec);
			}
			else if (displaytopleft)
			{
				clock.config(width *size_factor/2, width *size_factor/2 + 40, (int) (width * size_factor),
						new Date(), paint, colors, displayHandSec);
			}
			else if(displaybottomright)
			{
				clock.config(width-width *size_factor/2, height- width *size_factor/2 - 30, (int) (width * size_factor),
						new Date(), paint, colors, displayHandSec);

			}
			else if(displaybottomleft)
			{
				clock.config(width *size_factor/2, height- width *size_factor/2 - 30, (int) (width * size_factor),
						new Date(), paint, colors, displayHandSec);
			}
			else
			{
				clock.config(width*size_factor/2,height*size_factor/2, (int) (width * size_factor),
						new Date(), paint, colors, displayHandSec);
			}

			clock.draw(canvas);
		}

		@Override
		public void onSharedPreferenceChanged(
				SharedPreferences sharedPreferences, String key) {
			if (SettingsActivity.DISPLAY_HAND_SEC_KEY.equals(key)) {
				displayHandSec = sharedPreferences.getBoolean(
						SettingsActivity.DISPLAY_HAND_SEC_KEY, true);
			}

			if(key.equals("center"))
			{
				displayCenter=sharedPreferences.getBoolean("center",true);
			}
			else if(key.equals("topleft"))
			{
				displaytopleft=sharedPreferences.getBoolean("topleft",false);

			}
			else if(key.equals("topright"))
			{
				displaytopright=sharedPreferences.getBoolean("topright",false);
			}
			else if(key.equals("bottomleft"))
			{
				displaybottomleft=sharedPreferences.getBoolean("bottomleft",false);
			}
			else if(key.equals("bottomright"))
			{
				displaybottomright=sharedPreferences.getBoolean("bottomright",false);
			}
			else if(key.equals("clockwidth40"))
			{
				width40=sharedPreferences.getBoolean("clockwidth40",false);
			}
			else if(key.equals("clockwidth50"))
			{
				wifth50=sharedPreferences.getBoolean("clockwidth50",true);
			}
			else if(key.equals("clockwidth65"))
			{
				width65=sharedPreferences.getBoolean("clockwidth65",true);
			}


		}

	}

}
