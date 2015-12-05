package com.xiaoneiit.db;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.app.Application;

public class HijasonApplication extends Application{
	
	ImageLoader imageLoader;
	DisplayImageOptions options;
	
	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader();
	}
	
	private void initImageLoader(){
		initOptions();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
	}
	
	private void initOptions(){
		options = new DisplayImageOptions.Builder()
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.displayer(new FadeInBitmapDisplayer(500)) 
		.build();
	}
	
	public ImageLoader getImageLoader(){
		return imageLoader;
	}
		
	public DisplayImageOptions getOptions(){
		return options;
	}
}
