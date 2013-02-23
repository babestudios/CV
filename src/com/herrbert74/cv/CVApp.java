package com.herrbert74.cv;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
//http://www.devahead.com/blog/2011/06/extending-the-android-application-class-and-dealing-with-singleton/
//Don't forget to add a reference in the Manifest file!!!

public class CVApp extends Application implements CVConstants {

	private static final String TAG = CVApp.class.getSimpleName();
	private static CVApp instance;
	private static Typeface mFont_headers;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "CVApp.onCreate was called");
		instance = this;
		initSingletons();
	}

	public static Context getContext() {
		return instance.getApplicationContext();
	}

	protected void initSingletons() {
		// this.getSharedPreferences( "PREFS_PRIVATE", Context.MODE_PRIVATE );
		mFont_headers = Typeface.createFromAsset(getAssets(), TYPEFACE_HEADINGS);
	}

	public static Typeface getFontHeaders() {
		return mFont_headers;
	}
}
