package com.herrbert74.cvpresenter.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Functions {
	
	//With inSampleSize for loads of images
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int inSampleSize) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = inSampleSize;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	//Without inSampleSize
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		return BitmapFactory.decodeResource(res, resId, options);
	}

	public static int getPositionInArray(int[] numbers, int v)
	{
		int position = -1;
		for(int i=0; i<numbers.length; i++)
		{
			if(numbers[i] == v){
				position = i;
			}
		}
		return position;
	}
}
