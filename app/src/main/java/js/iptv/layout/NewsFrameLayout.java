package js.iptv.layout;

import js.iptv.R;
import js.iptv.activity.IPagable;
import js.iptv.utils.Anims;
import js.iptv.view.IUrlImage;
import js.iptv.view.URLImageButton;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

public class NewsFrameLayout extends RootFrameLayout implements View.OnFocusChangeListener {

	private View[] newsViews;
	private int random = 0;

	public NewsFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setContentView();
	}

	public NewsFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setContentView();
	}

	public NewsFrameLayout(Context context) {
		super(context);
		setContentView();
	}

	private void setContentView() {
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View newsView = inflater.inflate(R.layout.news_framelayout, null);
		addView(newsView);
		initViewsSize();
	}

	private void initViewsSize() {
		int marginLeft = (int) getResources().getDimension(R.dimen.newsFramelayoutMarginLeft);
		int marginTop = (int) getResources().getDimension(R.dimen.newsFramelayoutMarginTop);
		int posterWidth = (int) getResources().getDimension(R.dimen.newsFramelayoutPosterWidth);
		int posterHeight = (int) getResources().getDimension(R.dimen.newsFramelayoutPosterHeight);
		int intervalWidth = (int) getResources().getDimension(R.dimen.newsFramelayoutWidthInterval);
		int intervalHeight = (int) getResources().getDimension(R.dimen.newsFramelayoutHeightInterval);

		newsViews = new View[] { findViewById(R.id.news1), findViewById(R.id.news2), findViewById(R.id.news3),
				findViewById(R.id.news4), findViewById(R.id.news5), findViewById(R.id.news6), findViewById(R.id.news7),
				findViewById(R.id.news8), findViewById(R.id.news9), findViewById(R.id.news10), };

		int[] resources = new int[] { R.drawable.news_1, R.drawable.news_2, R.drawable.news_3, R.drawable.news_4,
				R.drawable.news_5, R.drawable.news_6, R.drawable.news_7, R.drawable.news_8, R.drawable.news_9,
				R.drawable.news_10 };

		LayoutParams _params;
		for (int i = 0; i < newsViews.length; i++) {
			int row = i / 5;
			int column = i % 5;
			View view = newsViews[i];
			_params = (LayoutParams) view.getLayoutParams();
			_params.width = posterWidth;
			_params.height = posterHeight;
			_params.leftMargin = marginLeft + column * (posterWidth + intervalWidth);
			_params.topMargin = marginTop + row * (posterHeight + intervalHeight);
			view.setLayoutParams(_params);
			((URLImageButton) view).setImageResource(resources[i]);
			view.setOnFocusChangeListener(this);
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (v instanceof IUrlImage) {
			IUrlImage view = (IUrlImage) v;
			if (hasFocus) {
				random++;
				if (0 == (random % 3)) {
					Anims.scaleTo(v, 1.1f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f,
							1000, 0);
				} else if (1 == (random % 3)) {
					Anims.alphaTo(v, 1.0f, 0.2f, 1000, 0);
				} else if (2 == (random % 3)) {
					Anims.rotateTo(v, 0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 2000,
							0);
				}
			} else {
				view.blurFocus();
			}
		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {

		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_DPAD_UP:

				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				break;

			case KeyEvent.KEYCODE_DPAD_LEFT:
				if (newsViews[0].isFocused() || newsViews[5].isFocused()) {
					((IPagable) this.getContext()).pageUp(false);
					return true;
				}
				break;

			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if (newsViews[4].isFocused() || newsViews[9].isFocused()) {
					((IPagable) this.getContext()).pageDown(false);
					return true;
				}
				break;

			default:
				break;
			}
		} else if (event.getAction() == KeyEvent.ACTION_UP) {
			return true;
		}

		return super.dispatchKeyEvent(event);
	}

}
