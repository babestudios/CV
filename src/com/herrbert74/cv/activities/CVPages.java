package com.herrbert74.cv.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.herrbert74.cv.CVApp;
import com.herrbert74.cv.CVConstants;
import com.herrbert74.cv.R;
import com.herrbert74.cv.adapters.MainPagerAdapter;
import com.herrbert74.cv.dao.XMLReader;
import com.herrbert74.cv.pojos.PageInfo;
import com.viewpagerindicator.CirclePageIndicator;

@EActivity(R.layout.activity_cvpages)
public class CVPages extends SherlockFragmentActivity implements CVConstants, ActionBar.OnNavigationListener {

	TextView lbl_title;
	Button btn_back, btn_next, btn_exit;
	String[] readme_array = new String[6];

	// Preferences
	SharedPreferences settings;
	Preferences prefs;

	@ViewById
	ViewPager pager;
	@ViewById
	CirclePageIndicator indicator;
	
	MainPagerAdapter adapter;
	
	Typeface mFont_titles;
	
	ArrayList<PageInfo> mPageInfoList;
	
	private String[] mThemes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		super.onCreate(savedInstanceState);
		
		try {
			mPageInfoList = XMLReader.getPagesFromXML(this);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mThemes = getResources().getStringArray(R.array.themes);

        Context context = getSupportActionBar().getThemedContext();
        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(context, R.array.themes, R.layout.sherlock_spinner_item);
        list.setDropDownViewResource(R.layout.spinner_dropdown_item);

        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setListNavigationCallbacks(list, this);
	}
	
	@AfterViews
	public void av(){
		
		// Pager
		adapter = new MainPagerAdapter(getSupportFragmentManager(), mPageInfoList);
		pager.setAdapter(adapter);
		indicator.setViewPager(pager);
		// myPager.setCurrentItem(1);
		indicator.setOnPageChangeListener(new MyPageChangeListener());
		// myPager.setOnPageChangeListener(new MyPageChangeListener());

/*		btn_back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				pager.setCurrentItem(pager.getCurrentItem() - 1, true);
			}
		});

		btn_next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				pager.setCurrentItem(pager.getCurrentItem() + 1, true);
			}
		});
		mFont_titles = Typeface.createFromAsset(getAssets(),
				TYPEFACE_DIALOG_TITLES);
		lbl_title.setTypeface(mFont_titles);*/
	}

	private class MyPageChangeListener extends
			ViewPager.SimpleOnPageChangeListener {
		@Override
		public void onPageSelected(int position) {
			/*btn_back.setEnabled(position == 0 ? false : true);
			btn_next
					.setEnabled(position == pager.getAdapter().getCount() - 1 ? false
							: true);
			lbl_title.setText(readme_array[position]);*/
		}
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(CVApp.getContext(), MainCV_.class);
		startActivity(intent);
		finish();
	}
}