package com.xiaoneiit.anim;

import java.util.Random;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class HotballonAnimation {
	public static RotateAnimation getRotateAnimation(){
		return new RotateAnimation(new Random().nextInt(5) * -1, new Random().nextInt(5));
	}
	
	public static void startPopoAnimtion(View view){
		RotateAnimation animation = getRotateAnimation();
		animation.setDuration(new Random().nextInt(2000) + 3000);
		animation.setRepeatCount(Integer.MAX_VALUE);
		animation.setRepeatMode(Animation.REVERSE);
		view.startAnimation(animation);
	}
}
