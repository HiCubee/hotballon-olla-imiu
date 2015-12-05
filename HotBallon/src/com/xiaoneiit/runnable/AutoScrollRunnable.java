package com.xiaoneiit.runnable;

import android.os.Handler;

import com.xiaoneiit.category.HijasonCategory;
import com.xiaoneiit.view.HotballonScrollView;

public class AutoScrollRunnable implements Runnable{

	HijasonCategory category;
	
	public AutoScrollRunnable(HijasonCategory category){
		this.category = category;
	}
	
	@Override
	public void run() {
        int endIndex = Integer.MAX_VALUE;
        if (endIndex > 0) {  
        	getScrollView().scrollBy(0, 2);  
            if (getScrollView().getScrollY() == endIndex) {  
                Thread.currentThread().interrupt();  
            } else {  
                getHandler().postDelayed(this, getScrollView().getRiseSpeed());  
            }  
        }
	}
	
	public HotballonScrollView getScrollView(){
		return category.getScrollView();
	}
	
	public Handler getHandler(){
		return category.getHandler();
	}

}
