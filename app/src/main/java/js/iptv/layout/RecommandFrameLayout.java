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

public class RecommandFrameLayout extends RootFrameLayout implements View.OnFocusChangeListener {
	private URLImageButton hot1ImageButton, hot2ImageButton, hot3ImageButton, hot4ImageButton, hot5ImageButton,
			hot6ImageButton, hot7ImageButton;
	private int random = 0;

	public RecommandFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setContentView();
	}

	public RecommandFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setContentView();
	}

	public RecommandFrameLayout(Context context) {
		super(context);
		setContentView();
	}

	private void setContentView() {
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View portalView = inflater.inflate(R.layout.recommand_framelayout, null);
		addView(portalView);
		initViews();
	}

	private void initViews() {
		hot1ImageButton = (URLImageButton) findViewById(R.id.hot1);
		hot2ImageButton = (URLImageButton) findViewById(R.id.hot2);
		hot3ImageButton = (URLImageButton) findViewById(R.id.hot3);
		hot4ImageButton = (URLImageButton) findViewById(R.id.hot4);
		hot5ImageButton = (URLImageButton) findViewById(R.id.hot5);
		hot6ImageButton = (URLImageButton) findViewById(R.id.hot6);
		hot7ImageButton = (URLImageButton) findViewById(R.id.hot7);

		hot1ImageButton.setOnFocusChangeListener(this);
		hot2ImageButton.setOnFocusChangeListener(this);
		hot3ImageButton.setOnFocusChangeListener(this);
		hot4ImageButton.setOnFocusChangeListener(this);
		hot5ImageButton.setOnFocusChangeListener(this);
		hot6ImageButton.setOnFocusChangeListener(this);
		hot7ImageButton.setOnFocusChangeListener(this);
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
				if (hot1ImageButton.isFocused()) {
					((IPagable) this.getContext()).pageUp(false);
					return true;
				}
				break;

			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if (hot4ImageButton.isFocused() || hot7ImageButton.isFocused()) {
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
