package com.xiaoneiit.definition;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Country {
	
	static String tag = "Country";
	
	static HashMap<Integer, String> countrys = new HashMap<Integer, String>();
	
	static{
		for(int i = 0; i<=205; i++){
			String name = "guoqi" + i + ".png";
			countrys.put(i, name);
		}
	}
	
	public static Bitmap getCountry(Context context, int index){
		return getImageFromAssetFile(context, countrys.get(index));
	}
	
	private static Bitmap getImageFromAssetFile(Context context, String name){
		Bitmap bitmap = null;
		AssetManager manager = getResources(context).getAssets();
		Log.d(tag, "manager: " + (manager == null));
		try{
			InputStream is = manager.open(name);
			bitmap = BitmapFactory.decodeStream(is);
			is.close();			
			Log.d(tag, "file name:  "  + name + " ,bitmap: " + (bitmap == null));
		}catch(IOException e){
			
		}
		return bitmap;
	}
	
	private static Resources getResources(Context context){
		return context.getResources();
	}
}
