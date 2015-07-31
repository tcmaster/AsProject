package com.android.tonight8.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.android.tonight8.base.AppConstants;

public class HSAnimationUtils {
	/**
	 * 播放折叠和打开的动画
	 * 
	 * @param v
	 */
	public static void playShowOrHideAnimation(View v,
			AnimationListener listener, boolean isShow, boolean upOrDown) {
		v.clearAnimation();
		if (!isShow)
			v.startAnimation(AnimationFactory.createShowAnimation(listener));
		else
			v.startAnimation(AnimationFactory.createHideAnimation(listener,
					upOrDown));
	}

	public static void playTranslationAnimation(View v, AnimationListener listener, int fromX, int toX) {
		v.clearAnimation();
		v.startAnimation(AnimationFactory.createTranslationAnimation(v.getContext(), fromX, toX, listener));
	}
	/**
	 * 动画工厂类
	 * 
	 * @Descripton
	 * @author LiXiaoSong
	 * @2015-4-9
	 * @Tonight8
	 */
	private static class AnimationFactory {
		public static Animation createShowAnimation(AnimationListener listener) {
			ScaleAnimation animation = new ScaleAnimation(1.0f, 1.0f, 0.0f,
					1.0f, 1.0f, 0.0f);
			animation.setDuration(100);
			animation.setInterpolator(new LinearInterpolator());
			if (listener != null)
				animation.setAnimationListener(listener);
			return animation;
		}

		public static Animation createHideAnimation(AnimationListener listener,
				boolean upOrDown) {
			ScaleAnimation animation;
			if (upOrDown)
				animation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f, 1.0f,
						0.0f);
			else
				animation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f, 1.0f,
						1.0f);
			animation.setDuration(100);
			if (listener != null)
				animation.setAnimationListener(listener);
			animation.setInterpolator(new LinearInterpolator());
			return animation;
		}

		public static Animation createTranslationAnimation(Context context, int fromX, int toX, AnimationListener listener) {
			TranslateAnimation tlAnim = new TranslateAnimation(fromX, toX, 0, 0);
			//自动计算时间
			long duration = (long) (Math.abs(toX - fromX) * 1.0f / AppConstants.widthPx * 4000);
			tlAnim.setDuration(duration);
			tlAnim.setInterpolator(new DecelerateInterpolator());
			tlAnim.setFillAfter(true);
			tlAnim.setAnimationListener(listener);
			return tlAnim;
		}
	}
}
