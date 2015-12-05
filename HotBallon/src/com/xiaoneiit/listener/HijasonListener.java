package com.xiaoneiit.listener;

import java.util.List;

import android.view.View;
import android.widget.Toast;

import com.xiaoneiit.asyn.GetMoreHotBallon;
import com.xiaoneiit.category.HijasonCategory;
import com.xiaoneiit.entity.Hotballon;
import com.xiaoneiit.hotballon.HijasonActivity;
import com.xiaoneiit.view.HotballonScrollView.OnScrollToBottomListener;
import com.xiaoneiit.view.HotballonScrollView.OnViewClickListener;
import com.xioneiit.definition.Hijason;
import com.xioneiit.definition.Message;
import com.xioneiit.definition.Name;

/**
 * 
 * @author 狗蛋哥
 *
 */
public class HijasonListener implements OnScrollToBottomListener, OnViewClickListener{
	
	HijasonCategory category;
	
	public HijasonListener(HijasonCategory category){
		this.category = category;
	}

	@Override
	public void onScrollToBottom() {
		/**
		 * coding here should be executed asynchronously
		 */
		new GetMoreHotBallon(getCategory()).execute("");
		
	}

	@Override
	public void onViewClick(View view, int position) {
		switch (view.getId()) {
		case Hijason.R.id.HOME_SCROLLVIEW_AVATOR:
			showToast("click avator...");
			break;
		case Hijason.R.id.HOME_SCROLLVIEW_COUNTRY:
			showToast("click country...");
			break;
		case Hijason.R.id.HOME_SCROLLVIEW_MESSAGE:
			showToast("message: " + Message.getMessage(getHotballons().get(position).getMessage()));
			break;
		case Hijason.R.id.HOME_SCROLLVIEW_NAME:
			showToast("name: " + Name.getName(getHotballons().get(position).getName()));
			break;
		case Hijason.R.id.HOME_SCROLLVIEW_POPO:
			showToast("click popo + message: " + Message.getMessage(getHotballons().get(position).getMessage()));
			break;
		default:
			break;
		}
	}
	
	public void showToast(String msg){
		Toast.makeText(getActivity(), msg, 0).show();
	}
	
	public HijasonCategory getCategory(){
		return category;
	}
	
	public HijasonActivity getActivity(){
		return category.getActivity();
	}
	
	public List<Hotballon> getHotballons(){
		return category.getHotballons();
	}
	
}