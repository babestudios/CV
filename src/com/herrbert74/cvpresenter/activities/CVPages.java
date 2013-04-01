package com.herrbert74.cvpresenter.activities;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.herrbert74.cvpresenter.CVApp;
import com.herrbert74.cvpresenter.CVConstants;
import com.herrbert74.cvpresenter.R;
import com.herrbert74.cvpresenter.adapters.CVPagerAdapter;
import com.herrbert74.cvpresenter.dao.SharedPreferencesHelper;
import com.herrbert74.cvpresenter.dao.WebRequests;
import com.herrbert74.cvpresenter.dao.WebRequests.GetCVLinesListener;
import com.herrbert74.cvpresenter.pojos.PageInfo;
import com.viewpagerindicator.IconPageIndicator;

/**
 * The Class CVPages.
 */
@EActivity(R.layout.activity_cvpages)
public class CVPages extends SherlockFragmentActivity implements CVConstants, ActionBar.OnNavigationListener {

	/** SharedPreference helper class. */
	SharedPreferencesHelper prefs;

	/** The CV pager. */
	@ViewById
	ViewPager pager;
	
	/** The CV page indicator. */
	@ViewById
	IconPageIndicator indicator;

	/** The adapter that provides the data for the CV Pages. */
	CVPagerAdapter adapter;

	/** The list fed to the adapter. */
	ArrayList<PageInfo> mPageInfoList;

	/** The actual theme for the activity. */
	private int mTheme;

	/** The pass code of the requested CV. */
	private int mPassCode;
	
	/** True, if the pass code was saved. We load the data from SavedPreferences*/
	boolean isPassCodeSaved;
	
	/** Used to prevent actionbar.onnavigationselected actions(restart activity). */
	private boolean isLoading = true;

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		prefs = new SharedPreferencesHelper();
		prefs.restorePreferences();
		
		// If theme was changed, get it from intent. Otherwise, get it from sharedpreferences
		mTheme = getIntent().getIntExtra("theme", prefs.theme);

		mPassCode = getIntent().getIntExtra("passcode", -1);
		isPassCodeSaved = getIntent().getIntExtra("ispasscodesaved", -1) == 1;
		
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

		Context context = getSupportActionBar().getThemedContext();
		ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(context, R.array.themes, R.layout.sherlock_spinner_item);
		list.setDropDownViewResource(R.layout.spinner_dropdown_item);

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getSupportActionBar().setListNavigationCallbacks(list, this);
		getSupportActionBar().setSelectedNavigationItem(mTheme);

	}

	@AfterViews
	public void av() {
		WebRequests.getCVLines( CVPages.this, mPassCode, isPassCodeSaved, new GetCVLinesListener() {

			@Override
			public void parsingFinished(ArrayList<PageInfo> cv) {
				mPageInfoList = cv;
				// Pager
				adapter = new CVPagerAdapter(getSupportFragmentManager(), mPageInfoList, mTheme);
				pager.setAdapter(adapter);
				indicator.setViewPager(pager);
				indicator.setOnPageChangeListener(new MyPageChangeListener());
			}
		});
	}

	/**
	 * The listener interface for receiving myPageChange events.
	 * The class that is interested in processing a myPageChange
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addMyPageChangeListener<code> method. When
	 * the myPageChange event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see MyPageChangeEvent
	 */
	private class MyPageChangeListener extends ViewPager.SimpleOnPageChangeListener {
		
		/* (non-Javadoc)
		 * @see android.support.v4.view.ViewPager.SimpleOnPageChangeListener#onPageSelected(int)
		 */
		/*@Override
		public void onPageSelected(int position) {		}*/
	}

	/* (non-Javadoc)
	 * @see com.actionbarsherlock.app.ActionBar.OnNavigationListener#onNavigationItemSelected(int, long)
	 * 
	 * Used for theme changing
	 */
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		if (isLoading) {
			isLoading = false;
		} else {
			Intent intent = new Intent(this, CVPages_.class);
			intent.putExtra("theme", itemPosition);
			intent.putExtra("passcode", mPassCode);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(CVApp.getContext(), MainCV_.class);
		intent.putExtra("theme", getSupportActionBar().getSelectedNavigationIndex());
		startActivity(intent);
		finish();
	}
}