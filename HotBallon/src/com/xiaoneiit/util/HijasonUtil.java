package com.xiaoneiit.util;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

/**
 * @weibo 我看我还是玩儿蛋去吧
 * @zhihu 狗蛋哥
 * @Pet Cubee (  ˃᷄˶˶̫˶˂᷅  )
 * @OSC Hijason
 * @author Hijason
 * @Github HiCubee
 * #https://github.com/HiCubee
 * 
 */
public class HijasonUtil {

	
	public static void setNoTitlebar(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	public static void setFullScreen(Activity activity) {
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	
	public static int getWidth(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getWidth();
	}
	
}
