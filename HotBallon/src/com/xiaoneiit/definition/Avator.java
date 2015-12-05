package com.xiaoneiit.definition;

import java.util.HashMap;

public class Avator {
	public static HashMap<Integer, String> avators = new HashMap<Integer, String>();
	static{
		for(int i = 0; i<2300; i++){
			String name = "avator" + i + ".jpg";
			String url = "http://7xn0v9.com2.z0.glb.qiniucdn.com/public/avator/" + name;
			avators.put(i, url);
		}
	}
	
	public static String getAvator(int index){
		return avators.get(index);
	}
}
