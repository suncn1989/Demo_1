package js.iptv.activity;

import js.iptv.R;
import js.iptv.utils.Anims;
import js.iptv.view.RootHorizontalScrollView;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends RootActivity implements View.OnFocusChangeListener, IPagable {
	RootHorizontalScrollView scrollView;
	ImageView portalBarGg;
	Button[] arrayButtons;
	private float curPortalBarGgLeft = 0;

	/**
	 * 控制翻页部分
	 */
	private int currentPage; // 当前页
	private int[] pageDownFocus; // 下页的焦点
	private int[] pageUpFocus; // 上页的焦点
	private int[] framelayoutviViewIds; // 所有view的集合//翻页的距离为当前view的宽度

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		scrollView = (RootHorizontalScrollView) findViewById(R.id.horizontalScrollView);
		arrayButtons = new Button[] { (Button) this.findViewById(R.id.homeButton),
				(Button) this.findViewById(R.id.hotButton), (Button) this.findViewById(R.id.categoryButton),
				(Button) this.findViewById(R.id.channelButton), (Button) this.findViewById(R.id.lbChannelButton),
				(Button) this.findViewById(R.id.newsButton), (Button) this.findViewById(R.id.appButton) };
		for (Button bt : arrayButtons) {
			bt.setOnFocusChangeListener(this);
		}
		portalBarGg = (ImageView) this.findViewById(R.id.portalBarGg);
		portalBarGg.requestFocus();
		// 初始化翻页控制数据
		currentPage = 0;
		pageDownFocus = new int[] { R.id.hot1, R.id.vod_hot1, R.id.channel_player, R.id.lbchannel1, R.id.news1, R.id.app1, 0 };
		pageUpFocus = new int[] { 0, R.id.setting, R.id.hot4, R.id.vod_hot4, R.id.channel_more ,R.id.lbchannel4, R.id.news5};
		framelayoutviViewIds = new int[] { R.id.portalFrameLayout, R.id.recommandFramelayout, R.id.vodFramelayout,
				R.id.channelFramelayout, R.id.lbChannelFramelayout ,R.id.newsFramelayout, R.id.appMarketFramelayout};
		// ((PortalFrameLayout)findViewById(R.id.portalFrameLayout)).getData();
	}

	@SuppressLint("NewApi")
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			((Button) v).setTextColor(getResources().getColor(R.color.portalBarOnFocus));
			((Button) v).setTextSize(TypedValue.COMPLEX_UNIT_PX,
					(float) ((getResources().getDimension(R.dimen.portalBarOnFocusTextSize))));
			int destLeftOffset = (int) (getResources().getDimension(R.dimen.portalbaremptysplace))
					+ (((Button) v).getWidth() - portalBarGg.getWidth()) / 2;
			int destLeft = ((Button) v).getLeft() + destLeftOffset;
			// int destTopOffset = 0;
			// int destTop = (int) (((Button) v).getTop() + destTopOffset);
			// Anims.slideTo(portalBarGg, curPortalBarGgLeft, destLeft,
			// curPortalBarGgTop, destTop, 1000, 0);
			Anims.translateTo(portalBarGg, curPortalBarGgLeft, destLeft, 0, 0, 1000, 0);
			curPortalBarGgLeft = destLeft;
			// curPortalBarGgTop = destTop;
		} else {
			((Button) v).setTextColor(getResources().getColor(R.color.portalBarOnBlur));
			((Button) v).setTextSize(TypedValue.COMPLEX_UNIT_PX,
					(float) ((getResources().getDimension(R.dimen.portalBarOnBlurTextSize))));
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
				for (int i = 1; i < arrayButtons.length; i++) {
					if (arrayButtons[i].isFocused()) {
						pageUp(true);
						return true;
					}
				}
				break;

			case KeyEvent.KEYCODE_DPAD_RIGHT:
				for (int i = 0; i < arrayButtons.length - 1; i++) {
					if (arrayButtons[i].isFocused()) {
						pageDown(true);
						return true;
					}
				}

			default:
				break;
			}
		} else if (event.getAction() == KeyEvent.ACTION_UP) {
			return true;
		}

		return super.dispatchKeyEvent(event);
	}

	/**
	 * 控制翻页部分
	 */

	@Override
	public void pageDown(boolean isBar) {
		if (currentPage < pageDownFocus.length-1) {
			scrollView = (RootHorizontalScrollView) findViewById(R.id.horizontalScrollView);
			View framelayout = findViewById(framelayoutviViewIds[currentPage]);
			scrollView.smoothScrollBy(framelayout.getWidth(), 0);
			arrayButtons[currentPage+1].requestFocus();
			if (!isBar) {
				View focusView = findViewById(pageDownFocus[currentPage]);
				focusView.requestFocus();
			}
			currentPage++;
		}
	}

	@Override
	public void pageUp(boolean isBar) {
		if (currentPage > 0) {
			scrollView = (RootHorizontalScrollView) findViewById(R.id.horizontalScrollView);
			View framelayout = findViewById(framelayoutviViewIds[currentPage]);
			scrollView.smoothScrollBy(-framelayout.getWidth(), 0);
			arrayButtons[currentPage-1].requestFocus();
			if (!isBar) {
				View focusView = findViewById(pageUpFocus[currentPage]);
				focusView.requestFocus();
			}
			currentPage--;
		}
	}

}
