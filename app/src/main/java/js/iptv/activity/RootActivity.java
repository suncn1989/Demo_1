package js.iptv.activity;

import java.net.URL;
import java.util.Hashtable;
import java.util.Map.Entry;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

public class RootActivity extends Activity {

	private Hashtable<URL, Bitmap> bitmapCache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bitmapCache = new Hashtable<URL, Bitmap>();
	}

	@Override
	protected void onDestroy() {
		for (Entry<URL, Bitmap> entry : bitmapCache.entrySet()) {
			Bitmap bitmap = entry.getValue();
			if (null != bitmap) {
				bitmap.recycle();
			}
		}
		super.onDestroy();
	}

	public void addBitmapToCache(URL url, Bitmap bitmap) {
		bitmapCache.put(url, bitmap);
	}

	public Bitmap getBitmapFromCache(URL url) {
		return bitmapCache.get(url);
	}

}
