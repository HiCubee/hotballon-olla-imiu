package com.xiaoneiit.category;

import java.util.List;

import android.os.Handler;
import android.widget.RelativeLayout;

import com.xiaoneiit.entity.Hotballon;
import com.xiaoneiit.factory.HotballonFactory;
import com.xiaoneiit.hotballon.HijasonActivity;
import com.xiaoneiit.hotballon.R;
import com.xiaoneiit.listener.HijasonListener;
import com.xiaoneiit.runnable.AutoScrollRunnable;
import com.xiaoneiit.view.HotballonScrollView;

public class HijasonCategory {
	
	Handler handler;
	HijasonActivity activity;
	HijasonListener listener;
	HotballonScrollView scrollview;
	RelativeLayout root;
	
	public HijasonCategory(HijasonActivity activity){
		this.activity = activity;
		findViews();
		initListener();
		setListener();
		init();
	}
	
	protected void findViews(){
		root = new RelativeLayout(getActivity());
		scrollview = new HotballonScrollView(getActivity());
	}
	
	protected void initListener(){
		listener = new HijasonListener(this);
	}
	
	protected void setListener(){
		scrollview.setOnScrollToBottomListener(listener);
		scrollview.setOnViewClickListener(listener);
	}
	
	protected void init(){
		initHandler();
		setContentView();
		intScroll();
	}
	
	private void setContentView(){
		getActivity().setContentView(root);
		root.setBackgroundResource(R.drawable.bg_home_page);
		root.addView(scrollview);		
	}
	
	private void initHandler(){
		handler = new Handler();		
	}
	
	private void intScroll(){
		scrollview.init();
		onRefresh(HotballonFactory.newInstance().creatHotballons());
		scrollview.post(new AutoScrollRunnable(this));
	}
	
	public void onRefresh(List<Hotballon> hotballons){
		scrollview.onRefresh(hotballons);
	}
	
	public void onLoadmore(List<Hotballon> hotballons){
		scrollview.onLoadmore(hotballons);
	}
	
	public HijasonActivity getActivity(){
		return activity;
	}
	
	public HotballonScrollView getScrollView(){
		return scrollview;
	}
	
	public Handler getHandler(){
		return handler;
	}
	
	public List<Hotballon> getHotballons(){
		return scrollview.getHotballons();
	}
	
}
