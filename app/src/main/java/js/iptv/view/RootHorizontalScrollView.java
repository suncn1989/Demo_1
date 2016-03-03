package js.iptv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class RootHorizontalScrollView extends HorizontalScrollView {

	public RootHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public RootHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RootHorizontalScrollView(Context context) {
		super(context);
	}

	@Override
	public boolean executeKeyEvent(KeyEvent event) {
		return super.executeKeyEvent(event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return super.onTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		return super.dispatchKeyEvent(event);
	}
	

}
