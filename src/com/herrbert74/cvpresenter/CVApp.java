package com.herrbert74.cvpresenter;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

//http://www.devahead.com/blog/2011/06/extending-the-android-application-class-and-dealing-with-singleton/
//Don't forget to add a reference in the Manifest file!!!

/**
 * The Class CVApp used to store the context and sigletons like typefaces.
 */
public class CVApp extends Application implements CVConstants {

	/** The Constant TAG. */
	private static final String TAG = CVApp.class.getSimpleName();
	
	/** The instance. */
	private static CVApp instance;
	
	/** The m font_headers. */
	private static Typeface mFont_headers;

	/* (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		initSingletons();
	}

	/**
	 * Gets the context.
	 *
	 * @return the context
	 */
	public static Context getContext() {
		return instance.getApplicationContext();
	}

	/**
	 * Inits the singletons.
	 */
	protected void initSingletons() {
		mFont_headers = Typeface.createFromAsset(getAssets(), TYPEFACE_HEADINGS);
	}

	/**
	 * Gets the font headers.
	 *
	 * @return the font headers
	 */
	public static Typeface getFontHeaders() {
		return mFont_headers;
	}
}
