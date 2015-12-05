package com.xiaoneiit.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

import com.xiaoneiit.entity.Hotballon;
import com.xiaoneiit.view.HotballonScrollView;
import com.xioneiit.definition.Message;
import com.xioneiit.definition.Name;
import com.xioneiit.definition.Popo;

public class HotballonFactory {
	
	private String tag = "HotballonFactory";
	public static HotballonFactory instance;
	
	
	public static HotballonFactory newInstance(){
		if(instance == null){
			instance = new HotballonFactory();
		}
		return instance;
	}
	
	public List<Hotballon> creatHotballons(){
		List<Hotballon> hotballons = new ArrayList<Hotballon>();
		for(int i = 0; i<HotballonScrollView.PAGE_COUNT; i++){
			Hotballon hb = new Hotballon();
			hb.setAvator(new Random().nextInt(2300));
			hb.setCountry(new Random().nextInt(205));
			int popo = i == 0 ? new Random().nextInt(5) : hotballons.get(i-1).getPopo()  == Popo.getSize() - 1 ? 0 : hotballons.get(i-1).getPopo() + 1;
			hb.setPopo(popo);
			hb.setName(new Random().nextInt(Name.getSize()));
			hb.setMessage(new Random().nextInt(Message.getSize()));
			hotballons.add(hb);
		}
		return hotballons;
	}
}
