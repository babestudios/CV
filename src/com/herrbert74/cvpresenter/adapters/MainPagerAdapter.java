package com.herrbert74.cvpresenter.adapters;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.herrbert74.cvpresenter.CVConstants;
import com.herrbert74.cvpresenter.fragments.PageInfoFragment;
import com.herrbert74.cvpresenter.pojos.PageInfo;
import com.viewpagerindicator.IconPagerAdapter;

public class MainPagerAdapter extends FragmentStatePagerAdapter implements CVConstants, IconPagerAdapter {

	ArrayList<PageInfo> mPageInfoList;
	int[] icons;
	
	public MainPagerAdapter(FragmentManager fm, ArrayList<PageInfo> pageInfoList, int theme) {
		super(fm);
		mPageInfoList = pageInfoList;
		if(theme == 0){
			icons = ICONS_ANDROID;
		}else{
			icons = ICONS_SQUARES;
		}
	}

	public int getCount() {
		return mPageInfoList.size();
	}

	@Override
	public Fragment getItem(int position) {
		Log.d("cv", "page: " + Integer.toString(position));
		return PageInfoFragment.newInstance(position, mPageInfoList.get(position));
	}
	
	@Override
    public void destroyItem(View collection, int position, Object o) {
        View view = (View)o;
        ((ViewPager) collection).removeView(view);
        view = null;
    }

	@Override
	public int getIconResId(int index) {
		return icons[index % icons.length];
	}
}