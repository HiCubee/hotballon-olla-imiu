package com.xiaoneiit.definition;

import java.util.HashMap;

import android.util.Log;

import com.xiaoneiit.hotballon.R;

public class Popo {
	
	static String tag = "Popo";
	
	static HashMap<Integer, Integer> popos = new HashMap<Integer, Integer>();
	static{
		popos.put(0, R.drawable.popo1);
		popos.put(1, R.drawable.popo2);
		popos.put(2, R.drawable.popo3);
		popos.put(3, R.drawable.popo4);
		popos.put(4, R.drawable.popo5);
	}
	
	public static int getPopo(int index){
		return popos.get(index);
	}
	
	public static int getSize(){
		return popos.size();
	}
}
