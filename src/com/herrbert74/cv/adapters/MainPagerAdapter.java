package com.herrbert74.cv.adapters;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.herrbert74.cv.fragments.PageInfoFragment;
import com.herrbert74.cv.pojos.PageInfo;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

	ArrayList<PageInfo> mPageInfoList;
	
	public MainPagerAdapter(FragmentManager fm, ArrayList<PageInfo> pageInfoList) {
		super(fm);
		mPageInfoList = pageInfoList;
	}

	public int getCount() {
		return mPageInfoList.size();
	}

	@Override
	public Fragment getItem(int position) {
		Log.d("cv", "page: " + Integer.toString(position));
		return PageInfoFragment.newInstance(position, mPageInfoList.get(position));
	}
}