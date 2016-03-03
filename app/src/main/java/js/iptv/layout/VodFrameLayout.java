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

public class VodFrameLayout extends RootFrameLayout implements View.OnFocusChangeListener {
	private URLImageButton vod1ImageButton, vod2ImageButton, vod3ImageButton, vod4ImageButton, vod5ImageButton,
			vod6ImageButton, vod7ImageButton;
	private int random = 0;

	public VodFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setContentView();
	}

	public VodFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setContentView();
	}

	public VodFrameLayout(Context context) {
		super(context);
		setContentView();
	}

	private void setContentView() {
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View portalView = inflater.inflate(R.layout.vod_framelayout, null);
		addView(portalView);
		initViews();
	}

	private void initViews() {
		vod1ImageButton = (URLImageButton) findViewById(R.id.vod_hot1);
		vod2ImageButton = (URLImageButton) findViewById(R.id.vod_hot2);
		vod3ImageButton = (URLImageButton) findViewById(R.id.vod_hot3);
		vod4ImageButton = (URLImageButton) findViewById(R.id.vod_hot4);
		vod5ImageButton = (URLImageButton) findViewById(R.id.vod_hot5);
		vod6ImageButton = (URLImageButton) findViewById(R.id.vod_hot6);
		vod7ImageButton = (URLImageButton) findViewById(R.id.vod_hot7);

		vod1ImageButton.setOnFocusChangeListener(this);
		vod2ImageButton.setOnFocusChangeListener(this);
		vod3ImageButton.setOnFocusChangeListener(this);
		vod4ImageButton.setOnFocusChangeListener(this);
		vod5ImageButton.setOnFocusChangeListener(this);
		vod6ImageButton.setOnFocusChangeListener(this);
		vod7ImageButton.setOnFocusChangeListener(this);
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
					Anims.rotateTo(v, 0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 750,
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
				if (vod1ImageButton.isFocused()) {
					((IPagable) this.getContext()).pageUp(false);
					return true;
				}
				break;

			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if (vod4ImageButton.isFocused() || vod7ImageButton.isFocused()) {
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
