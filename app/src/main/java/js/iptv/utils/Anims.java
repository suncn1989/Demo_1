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
	 *            ���ܵ�view
	 * @param p1
	 *            ����X��������ڵ�ǰVIEW��ƫ����
	 * @param p2
	 *            �յ��X��������ڵ�ǰVIEW��ƫ����
	 * @param p3
	 *            ����Y��������ڵ�ǰVIEW��ƫ����
	 * @param p4
	 *            �յ��Y��������ڵ�ǰVIEW��ƫ����
	 * @param durationMillis
	 *            ����ʱ��
	 * @param delayMillis
	 *            ��ʱ
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
	 * @param v����ǰview
	 * @param toX��X�����ϵ����ŵ��ı����� 
	 * @param toY��Y�����ϵ����ŵ��ı����� 
	 * @param pivotXType��X�������ģʽ������ȡֵΪABSOLUTE��RELATIVE_TO_SELF��RELATIVE_TO_PARENT��
	 * @param pivotX��X���������ֵ�� 
	 * @param pivotYType��Y�������ģʽ������ȡֵΪABSOLUTE��RELATIVE_TO_SELF��RELATIVE_TO_PARENT��
	 * @param pivotY��Y���������ֵ��
	 * @param durationMillis����������ʱ��
	 * @param delayMillis�������ӳ�ʱ��
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
	 * @param v����ǰview
	 * @param p1����ʼ͸����
	 * @param p2������͸����
	 * @param durationMillis����������ʱ��
	 * @param delayMillis�������ӳ�ʱ��
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
	 * @param v����ǰview
	 * @param fromDegrees����ת�Ŀ�ʼ�Ƕȡ�
	 * @param toDegrees����ת�Ľ����Ƕȡ� 
	 * @param pivotXType��X�������ģʽ������ȡֵΪABSOLUTE��RELATIVE_TO_SELF��RELATIVE_TO_PARENT��
	 * @param pivotXValue��X���������ֵ�� 
	 * @param pivotYType��Y�������ģʽ������ȡֵΪABSOLUTE��RELATIVE_TO_SELF��RELATIVE_TO_PARENT��
	 * @param pivotYValue��Y���������ֵ��
	 * @param durationMillis����������ʱ��
	 * @param delayMillis�������ӳ�ʱ��
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
