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

public class AppMarketFrameLayout extends RootFrameLayout implements View.OnFocusChangeListener {

	private View[] appMarketViews;
	private int random = 0;

	public AppMarketFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setContentView();
	}

	public AppMarketFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setContentView();
	}

	public AppMarketFrameLayout(Context context) {
		super(context);
		setContentView();
	}

	private void setContentView() {
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View appView = inflater.inflate(R.layout.app_framelayout, null);
		addView(appView);
		initViewsSize();
	}

	private void initViewsSize() {
		int marginLeft = (int) getResources().getDimension(R.dimen.appFramelayoutMarginLeft);
		int marginTop = (int) getResources().getDimension(R.dimen.appFramelayoutMarginTop);
		int posterWidth = (int) getResources().getDimension(R.dimen.appFramelayoutPosterWidth);
		int posterHeight = (int) getResources().getDimension(R.dimen.appFramelayoutPosterHeight);
		int intervalWidth = (int) getResources().getDimension(R.dimen.appFramelayoutWidthInterval);
		int intervalHeight = (int) getResources().getDimension(R.dimen.appFramelayoutHeightInterval);

		appMarketViews = new View[] { findViewById(R.id.app1), findViewById(R.id.app2), findViewById(R.id.app3),
				findViewById(R.id.app4), findViewById(R.id.app5), findViewById(R.id.app6), findViewById(R.id.app7),
				findViewById(R.id.app8), findViewById(R.id.app9), findViewById(R.id.app10), };

		int[] resources = new int[] { R.drawable.app_1, R.drawable.app_2, R.drawable.app_3, R.drawable.app_4,
				R.drawable.app_5, R.drawable.app_6, R.drawable.app_7, R.drawable.app_8, R.drawable.app_9,
				R.drawable.app_10 };

		LayoutParams _params;
		for (int i = 0; i < appMarketViews.length; i++) {
			int row=i/5;
			int column=i%5;
			View view = appMarketViews[i];
			_params = (LayoutParams) view.getLayoutParams();
			_params.width = posterWidth;
			_params.height = posterHeight;
			_params.leftMargin = marginLeft+column*(posterWidth+intervalWidth);
			_params.topMargin =marginTop+row*(posterHeight+intervalHeight);
			view.setLayoutParams(_params);
			((URLImageButton) view).setImageResource(resources[i]);
			view.setOnFocusChangeListener(this);
			if(0==row){
				view.setNextFocusUpId(R.id.appButton);
			}
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
				if (appMarketViews[0].isFocused() || appMarketViews[5].isFocused()) {
					((IPagable) this.getContext()).pageUp(false);
					return true;
				}
				break;

			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if (appMarketViews[4].isFocused() || appMarketViews[9].isFocused()) {
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
