package js.iptv.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class Anims {
	/**
	 * 
	 * @param v
	 *            接受的view
	 * @param p1
	 *            起点的X坐标相对于当前VIEW的偏移量
	 * @param p2
	 *            终点的X坐标相对于当前VIEW的偏移量
	 * @param p3
	 *            起点的Y坐标相对于当前VIEW的偏移量
	 * @param p4
	 *            终点的Y坐标相对于当前VIEW的偏移量
	 * @param durationMillis
	 *            持续时间
	 * @param delayMillis
	 *            延时
	 */
	public static void translateTo(final View v, final float p1,
			final float p2, final float p3, final float p4, int durationMillis,
			int delayMillis) {
		TranslateAnimation animation = new TranslateAnimation(p1, p2, p3, p4);
		animation.setInterpolator(new OvershootInterpolator());
		animation.setDuration(durationMillis);
		animation.setStartOffset(delayMillis);
		animation.setFillAfter(true);
		v.startAnimation(animation);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// int left = (int) (p2);
				// int top = (int) (v.getTop() + (p4 -p3));
				// int width = v.getWidth();
				// int height = v.getHeight();
				// v.clearAnimation();
				// v.layout(left, top, left + width, top + height);
			}
		});

	}


	/**
	 * 
	 * @param v：当前view
	 * @param toX：X方向上的缩放到的倍数。 
	 * @param toY：Y方向上的缩放到的倍数。 
	 * @param pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
	 * @param pivotX：X坐标的伸缩值。 
	 * @param pivotYType：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
	 * @param pivotY：Y坐标的伸缩值。
	 * @param durationMillis：动画持续时间
	 * @param delayMillis：动画延迟时间
	 */
	public static void scaleTo(final View v, final float toX, final float toY,
			int pivotXType, final float pivotX, int pivotYType,
			final float pivotY, int durationMillis, int delayMillis) {
		v.bringToFront();
		ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, toX, 1.0f,
				toY, pivotXType, pivotX, pivotYType, pivotY);
		scaleAnimation.setInterpolator(new OvershootInterpolator());
		scaleAnimation.setDuration(durationMillis);
		scaleAnimation.setRepeatMode(Animation.REVERSE);
		scaleAnimation.setRepeatCount(Animation.INFINITE);
		scaleAnimation.setStartOffset(delayMillis);
		scaleAnimation.setFillAfter(true);
		v.startAnimation(scaleAnimation);
		scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {

			}
		});
	}

	/**
	 * 
	 * @param v：当前view
	 * @param p1：起始透明度
	 * @param p2：结束透明度
	 * @param durationMillis：动画持续时间
	 * @param delayMillis：动画延迟时间
	 */
	public static void alphaTo(View v, final float p1, final float p2,
			int durationMillis, int delayMillis) {
		v.bringToFront();
		AlphaAnimation alphaAnimation = new AlphaAnimation(p1, p2);
		alphaAnimation.setInterpolator(new OvershootInterpolator());
		alphaAnimation.setFillAfter(true);
		alphaAnimation.setRepeatMode(Animation.REVERSE);
		alphaAnimation.setRepeatCount(Animation.INFINITE);
		alphaAnimation.setDuration(durationMillis);
		alphaAnimation.setStartOffset(delayMillis);
		v.startAnimation(alphaAnimation);
		alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
			}
		});
	}

	/**
	 * 
	 * @param v：当前view
	 * @param fromDegrees：旋转的开始角度。
	 * @param toDegrees：旋转的结束角度。 
	 * @param pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
	 * @param pivotXValue：X坐标的伸缩值。 
	 * @param pivotYType：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
	 * @param pivotYValue：Y坐标的伸缩值。
	 * @param durationMillis：动画持续时间
	 * @param delayMillis：动画延迟时间
	 */
	public static void rotateTo(View v, float fromDegrees, float toDegrees,
			int pivotXType, float pivotXValue, int pivotYType,
			float pivotYValue, int durationMillis, int delayMillis) {
		v.bringToFront();
		RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees,
				toDegrees, pivotXType, 0.5f, pivotXType, 0.5f);
		rotateAnimation.setInterpolator(new OvershootInterpolator());
		rotateAnimation.setFillAfter(true);
		rotateAnimation.setRepeatMode(Animation.RESTART);
		rotateAnimation.setRepeatCount(Animation.INFINITE);
		rotateAnimation.setDuration(durationMillis);
		rotateAnimation.setStartOffset(delayMillis);
		v.startAnimation(rotateAnimation);
		rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
			}
		});

	}
}
