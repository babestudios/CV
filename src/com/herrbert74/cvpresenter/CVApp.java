package com.herrbert74.cvpresenter;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
//http://www.devahead.com/blog/2011/06/extending-the-android-application-class-and-dealing-with-singleton/
//Don't forget to add a reference in the Manifest file!!!

public class CVApp extends Application implements CVConstants {

	private static final String TAG = CVApp.class.getSimpleName();
	private static CVApp instance;
	private static Typeface mFont_headers;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		initSingletons();
	}

	public static Context getContext() {
		return instance.getApplicationContext();
	}

	protected void initSingletons() {
		mFont_headers = Typeface.createFromAsset(getAssets(), TYPEFACE_HEADINGS);
	}

	public static Typeface getFontHeaders() {
		return mFont_headers;
	}
}
