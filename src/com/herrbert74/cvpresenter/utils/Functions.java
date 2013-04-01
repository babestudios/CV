package com.herrbert74.cvpresenter.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * The Class Functions. Contains static functions.
 */
public class Functions {
	
	/**
	 * Decode sampled bitmap from resource with inSampleSize. For lots of images.
	 *
	 * @param res the res
	 * @param resId the res id
	 * @param inSampleSize the in sample size
	 * @return the bitmap
	 */
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int inSampleSize) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = inSampleSize;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	//
	/**
	 * Decode sampled bitmap from resource without inSampleSize. For a small number of images.
	 *
	 * @param res the res
	 * @param resId the res id
	 * @return the bitmap
	 */
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		return BitmapFactory.decodeResource(res, resId, options);
	}
}
