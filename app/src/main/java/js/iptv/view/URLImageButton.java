package js.iptv.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import js.iptv.activity.RootActivity;
import js.iptv.utils.Anims;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class URLImageButton extends ImageButton implements IUrlImage,
		AnimationListener {
	private RootActivity activity;
	private int timeCount = 1;
	Timer mTimer = new Timer();
	Animation scaleAnimation;

	public URLImageButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initActivity(context);
	}

	public URLImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initActivity(context);
	}

	public URLImageButton(Context context) {
		super(context);
		initActivity(context);
	}

	private void initActivity(Context context) {
		activity = (RootActivity) context;
	}

	@Override
	public void setImageFromUrl(URL url) {
		Bitmap bitmap = activity.getBitmapFromCache(url);
		if (null == bitmap) {
			new getUrlImageTask().execute(url);
		} else {
			setImageBitmap(bitmap);
		}
	}

	/**
	 * 获取网络图片的异步任务
	 * 
	 * @author canoe
	 * 
	 */
	private class getUrlImageTask extends AsyncTask<URL, Integer, String> {

		private URL url = null;
		private Bitmap bitmap = null;

		@Override
		protected String doInBackground(URL... params) {
			url = params[0];
			try {
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setConnectTimeout(6000);
				conn.setRequestMethod("GET");
				conn.setDoInput(true);

				if (200 == conn.getResponseCode()) {
					InputStream is = conn.getInputStream();
					bitmap = BitmapFactory.decodeStream(is);
					is.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
				return "false";
			}
			return "success";
		}

		@Override
		protected void onPostExecute(String result) {
			if (result.equals("success")) {
				setImageBitmap(bitmap);
				activity.addBitmapToCache(url, bitmap);
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}
	}

	@Override
	public void onFocusWithAnim(int resourceId) {

	}

	@Override
	public void blurFocus() {
		clearAnimation();
		mTimer.cancel();
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationEnd(Animation animation) {
	}

}
