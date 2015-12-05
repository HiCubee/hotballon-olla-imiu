package com.xiaoneiit.asyn;

import java.util.List;

import com.xiaoneiit.category.HijasonCategory;
import com.xiaoneiit.entity.Hotballon;
import com.xiaoneiit.factory.HotballonFactory;

import android.os.AsyncTask;

public class GetMoreHotBallon extends AsyncTask<String, String , List<Hotballon>>{
	
	HijasonCategory category;
	
	public GetMoreHotBallon(HijasonCategory category){
		this.category = category;
	}

	@Override
	protected List<Hotballon> doInBackground(String... arg0) {
		return HotballonFactory.newInstance().creatHotballons();
	}
	
	@Override
	protected void onPostExecute(List<Hotballon> hotballons) {
		super.onPostExecute(hotballons);
		category.onLoadmore(hotballons);
	}

}
