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

public class ChannelFrameLayout extends RootFrameLayout implements View.OnFocusChangeListener {
	private URLImageButton channelPlayerButton;
	private URLImageButton channel1ImageButton, channelMoreImageButton, channel2ImageButton, channel3ImageButton,
	channel4ImageButton, channel5ImageButton, channel6ImageButton;
	private int random = 0;

	public ChannelFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setContentView();
	}

	public ChannelFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setContentView();
	}

	public ChannelFrameLayout(Context context) {
		super(context);
		setContentView();
	}

	private void setContentView() {
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View portalView = inflater.inflate(R.layout.channel_framelayout, null);
		addView(portalView);
		initViews();
	}

	private void initViews() {		
		channelPlayerButton = (URLImageButton) findViewById(R.id.channel_player);
		channel1ImageButton = (URLImageButton) findViewById(R.id.channel1);
		channelMoreImageButton = (URLImageButton) findViewById(R.id.channel_more);
		channel2ImageButton = (URLImageButton) findViewById(R.id.channel2);
		channel3ImageButton = (URLImageButton) findViewById(R.id.channel3);
		channel4ImageButton = (URLImageButton) findViewById(R.id.channel4);
		channel5ImageButton = (URLImageButton) findViewById(R.id.channel5);
		channel6ImageButton = (URLImageButton) findViewById(R.id.channel6);

		channelPlayerButton.setOnFocusChangeListener(this);
		channel1ImageButton.setOnFocusChangeListener(this);
		channelMoreImageButton.setOnFocusChangeListener(this);
		channel2ImageButton.setOnFocusChangeListener(this);
		channel3ImageButton.setOnFocusChangeListener(this);
		channel4ImageButton.setOnFocusChangeListener(this);
		channel5ImageButton.setOnFocusChangeListener(this);
		channel6ImageButton.setOnFocusChangeListener(this);
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
				if (channelPlayerButton.isFocused() || channel4ImageButton.isFocused()) {
					((IPagable) this.getContext()).pageUp(false);
					return true;
				}
				break;

			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if (channelMoreImageButton.isFocused() || channel3ImageButton.isFocused()||channel6ImageButton.isFocused()) {
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
