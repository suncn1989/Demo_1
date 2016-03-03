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

public class LbChannelFrameLayout extends RootFrameLayout implements View.OnFocusChangeListener {
	private URLImageButton lbChannel1ImageButton, lbChannel2ImageButton, lbChannel3ImageButton, lbChannel4ImageButton,
			lbChannel5ImageButton, lbChannel6ImageButton, lbChannel7ImageButton, lbChannel8ImageButton,
			lbChannel9ImageButton, lbChannel10ImageButton, lbChannel11ImageButton, lbChannel12ImageButton;
	private int random = 0;

	public LbChannelFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setContentView();
	}

	public LbChannelFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setContentView();
	}

	public LbChannelFrameLayout(Context context) {
		super(context);
		setContentView();
	}

	private void setContentView() {
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View portalView = inflater.inflate(R.layout.lbchannel_framelayout, null);
		addView(portalView);
		initViews();
	}

	private void initViews() {
		lbChannel1ImageButton = (URLImageButton) findViewById(R.id.lbchannel1);
		lbChannel2ImageButton = (URLImageButton) findViewById(R.id.lbchannel2);
		lbChannel3ImageButton = (URLImageButton) findViewById(R.id.lbchannel3);
		lbChannel4ImageButton = (URLImageButton) findViewById(R.id.lbchannel4);
		lbChannel5ImageButton = (URLImageButton) findViewById(R.id.lbchannel5);
		lbChannel6ImageButton = (URLImageButton) findViewById(R.id.lbchannel6);
		lbChannel7ImageButton = (URLImageButton) findViewById(R.id.lbchannel7);
		lbChannel8ImageButton = (URLImageButton) findViewById(R.id.lbchannel8);
		lbChannel9ImageButton = (URLImageButton) findViewById(R.id.lbchannel9);
		lbChannel10ImageButton = (URLImageButton) findViewById(R.id.lbchannel10);
		lbChannel11ImageButton = (URLImageButton) findViewById(R.id.lbchannel11);
		lbChannel12ImageButton = (URLImageButton) findViewById(R.id.lbchannel12);

		lbChannel1ImageButton.setOnFocusChangeListener(this);
		lbChannel2ImageButton.setOnFocusChangeListener(this);
		lbChannel3ImageButton.setOnFocusChangeListener(this);
		lbChannel4ImageButton.setOnFocusChangeListener(this);
		lbChannel5ImageButton.setOnFocusChangeListener(this);
		lbChannel6ImageButton.setOnFocusChangeListener(this);
		lbChannel7ImageButton.setOnFocusChangeListener(this);
		lbChannel8ImageButton.setOnFocusChangeListener(this);
		lbChannel9ImageButton.setOnFocusChangeListener(this);
		lbChannel10ImageButton.setOnFocusChangeListener(this);
		lbChannel11ImageButton.setOnFocusChangeListener(this);
		lbChannel12ImageButton.setOnFocusChangeListener(this);
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
				if (lbChannel1ImageButton.isFocused() || lbChannel5ImageButton.isFocused()
						|| lbChannel9ImageButton.isFocused()) {
					((IPagable) this.getContext()).pageUp(false);
					return true;
				}
				break;

			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if (lbChannel4ImageButton.isFocused() || lbChannel8ImageButton.isFocused()
						|| lbChannel12ImageButton.isFocused()) {
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
