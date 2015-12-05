package com.xiaoneiit.hotballon;


import com.xiaoneiit.category.HijasonCategory;
import com.xiaoneiit.db.HijasonApplication;
import com.xiaoneiit.util.HijasonUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * @weibo 我看我还是玩儿蛋去吧
 * @zhihu 狗蛋哥
 * @Pet Cubee (  ˃᷄˶˶̫˶˂᷅  )
 * @OSC Hijason
 * @author Hijason
 * @Github HiCubee
 * #https://github.com/HiCubee
 */
public class HijasonActivity extends Activity {

	HijasonApplication app;
	HijasonCategory category;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HijasonUtil.setNoTitlebar(this);
		HijasonUtil.setFullScreen(this);
		setContentView();
		init();
	}

	/**
	 * this method should be abstract if this activity is BaseActivity
	 * #HijasonActivity
	 */
	protected void setContentView(){
	}
	
	@Override
	public void setContentView(View view){
		super.setContentView(view);
	}
	
	private void init(){
		initApp();
		initCategory();
	}
	
	/**
	 * this method should be abstract if this activity is BaseActivity
	 * #HijasonActivity
	 */
	protected void initCategory(){
		category = new HijasonCategory(this);
	}
	
	public HijasonCategory getCategory(){
		return category;
	}
	
	private void initApp(){
		app = (HijasonApplication)this.getApplicationContext();
	}
	
	public HijasonApplication getApp(){
		return app;
	}
	

}
