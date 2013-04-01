package com.herrbert74.cvpresenter.adapters;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.herrbert74.cvpresenter.CVConstants;
import com.herrbert74.cvpresenter.fragments.PageInfoFragment;
import com.herrbert74.cvpresenter.pojos.PageInfo;
import com.viewpagerindicator.IconPagerAdapter;

/**
 * The Class CVPagerAdapter. Provides the data in the CVPager activity.
 */
public class CVPagerAdapter extends FragmentStatePagerAdapter implements CVConstants, IconPagerAdapter {

	/** The m page info list. */
	ArrayList<PageInfo> mPageInfoList;
	
	/** The icons. */
	int[] icons;
	
	/**
	 * Instantiates a new cV pager adapter.
	 *
	 * @param fm the FragmentManager
	 * @param pageInfoList the page info list
	 * @param theme the theme
	 */
	public CVPagerAdapter(FragmentManager fm, ArrayList<PageInfo> pageInfoList, int theme) {
		super(fm);
		mPageInfoList = pageInfoList;
		if(theme == 0){
			icons = ICONS_ANDROID;
		}else{
			icons = ICONS_SQUARES;
		}
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	public int getCount() {
		return mPageInfoList.size();
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentStatePagerAdapter#getItem(int)
	 */
	@Override
	public Fragment getItem(int position) {
		return PageInfoFragment.newInstance(position, mPageInfoList.get(position));
	}
	
	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#destroyItem(android.view.View, int, java.lang.Object)
	 */
	@Override
    public void destroyItem(View collection, int position, Object o) {
        View view = (View)o;
        ((ViewPager) collection).removeView(view);
        view = null;
    }

	/* (non-Javadoc)
	 * @see com.viewpagerindicator.IconPagerAdapter#getIconResId(int)
	 */
	@Override
	public int getIconResId(int index) {
		return icons[index % icons.length];
	}
}