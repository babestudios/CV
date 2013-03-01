package com.herrbert74.cv.activities;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.AdapterView;
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
import com.herrbert74.cv.dao.SharedPreferencesHelper;
import com.herrbert74.cv.dao.WebRequests;
import com.herrbert74.cv.dao.WebRequests.GetCVLinesListener;
import com.herrbert74.cv.pojos.PageInfo;
import com.viewpagerindicator.IconPageIndicator;

@EActivity(R.layout.activity_cvpages)
public class CVPages extends SherlockFragmentActivity implements CVConstants, ActionBar.OnNavigationListener {

	TextView lbl_title;
	Button btn_back, btn_next, btn_exit;
	String[] readme_array = new String[6];

	// Preferences
	SharedPreferences settings;
	SharedPreferencesHelper prefs;

	@ViewById
	ViewPager pager;
	@ViewById
	IconPageIndicator indicator;

	MainPagerAdapter adapter;

	Typeface mFont_titles;

	ArrayList<PageInfo> mPageInfoList;

	private int mTheme;

	//Requested id from the web
	private int mRequestedCVID;
	//Requested id to load from sharedpreferences
	private int mRequestedCVNo;

	// Needed for don't trigger actionbar.onnavigationselected actions(restart activity)
	private boolean isLoading = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		prefs = new SharedPreferencesHelper();
		prefs.restorePreferences();
		
		// If theme was changed, get it from intent. Otherwise, get it from sharedpreferences
		mTheme = getIntent().getIntExtra("theme", prefs.theme);

		mRequestedCVID = getIntent().getIntExtra("requested_cv_id", -1);
		mRequestedCVNo = getIntent().getIntExtra("requested_cv_no", -1);

		switch (mTheme) {
		case 0:
			setTheme(R.style.Theme_Android);
			break;
		case 1:
			setTheme(R.style.Theme_Squares);
			break;
		default:
		}
		super.onCreate(savedInstanceState);

		/*try {
			mPageInfoList = XMLReader.getPagesFromXML(this);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		// mThemes = getResources().getStringArray(R.array.themes);

		Context context = getSupportActionBar().getThemedContext();
		ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(context, R.array.themes, R.layout.sherlock_spinner_item);
		list.setDropDownViewResource(R.layout.spinner_dropdown_item);

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getSupportActionBar().setListNavigationCallbacks(list, this);
		getSupportActionBar().setSelectedNavigationItem(mTheme);

	}

	@AfterViews
	public void av() {
		boolean isWebRequest = mRequestedCVID > -1;
		WebRequests.getCVLines(isWebRequest ? mRequestedCVID : mRequestedCVNo, CVPages.this, isWebRequest, mRequestedCVNo, new GetCVLinesListener() {

			@Override
			public void parsingFinished(ArrayList<PageInfo> cv) {
				mPageInfoList = cv;
				// Pager
				adapter = new MainPagerAdapter(getSupportFragmentManager(), mPageInfoList, mTheme);
				pager.setAdapter(adapter);
				indicator.setViewPager(pager);
				indicator.setOnPageChangeListener(new MyPageChangeListener());
			}
		});
	}

	private class MyPageChangeListener extends ViewPager.SimpleOnPageChangeListener {
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
		if (isLoading) {
			isLoading = false;
		} else {
			Intent intent = new Intent(this, CVPages_.class);
			intent.putExtra("theme", itemPosition);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
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