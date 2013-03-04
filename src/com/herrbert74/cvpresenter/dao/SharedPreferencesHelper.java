package com.herrbert74.cvpresenter.dao;

import java.util.Set;

import android.content.SharedPreferences;

import com.herrbert74.cvpresenter.CVApp;
import com.herrbert74.cvpresenter.CVConstants;

public class SharedPreferencesHelper implements CVConstants {

	// Helper
	SharedPreferences settings;
	SharedPreferences.Editor editor;

	// Variables
	public int theme;

	public SharedPreferencesHelper() {
		settings = CVApp.getContext().getSharedPreferences(PREFS_MAIN, 0);
	}

	public void restorePreferences() {
		theme = settings.getInt("theme", 0);
	}

	// Restore CV stored as JSON string
	public String restoreCV(String id) {
		return settings.getString(id, "");
	}

	public void saveInt(String prefName, int i) {
		editor = settings.edit();
		editor.putInt(prefName, i);
		editor.commit();
	}

	public void saveString(String prefName, String s) {
		editor = settings.edit();
		editor.putString(prefName, s);
		editor.commit();
	}

	public void saveBoolean(String prefName, boolean b) {
		editor = settings.edit();
		editor.putBoolean(prefName, b);
		editor.commit();
	}

	public int[] getCVIDs() {
		int count = settings.getInt("cv_count", 0);
		int[] result = new int[count];
		for (int n = 0; n < count; n++) {
			result[n] = settings.getInt("cv_id" + n, 0);
		}
		return result;
	}

	public String[] getCVNames() {
		int count = settings.getInt("cv_count", 0);
		String[] result = new String[count];
		for (int n = 0; n < count; n++) {
			result[n] = settings.getString("cv_name" + n, "");
		}
		return result;
	}

	public void appendCVID(int id) {
		// restore count
		int count = settings.getInt("cv_count", 0);
		// store
		editor = settings.edit();
		editor.putInt("cv_id" + count, id);
		editor.putInt("cv_count", ++count);
		editor.commit();
	}

	public void appendCVName(String text) {
		// restore count
		int count = settings.getInt("cv_count", 0) - 1;
		// store
		editor = settings.edit();
		editor.putString("cv_name" + count, text);
		editor.commit();

	}

	public boolean containCVID(int id) {
		int[] ids = getCVIDs();
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == id) {
				return true;
			}
		}
		return false;
	}
}