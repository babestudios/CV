package com.herrbert74.cvpresenter.dao;

import android.content.SharedPreferences;

import com.herrbert74.cvpresenter.CVApp;
import com.herrbert74.cvpresenter.CVConstants;

/**
 * The Class SharedPreferencesHelper. Used to store CV data.
 */
public class SharedPreferencesHelper implements CVConstants {

	// Helper
	/** The settings. */
	SharedPreferences settings;
	
	/** The editor. */
	SharedPreferences.Editor editor;

	// Variables
	/** The theme. */
	public int theme;

	/**
	 * Instantiates a new shared preferences helper.
	 */
	public SharedPreferencesHelper() {
		settings = CVApp.getContext().getSharedPreferences(PREFS_MAIN, 0);
	}

	/**
	 * Restore preferences.
	 */
	public void restorePreferences() {
		theme = settings.getInt("theme", 0);
	}

	// Restore CV stored as JSON string
	/**
	 * Restore cv.
	 *
	 * @param id the id
	 * @return the CV string in JSON format
	 */
	public String restoreCV(String id) {
		return settings.getString(id, "");
	}

	/**
	 * Save int.
	 *
	 * @param prefName the pref name
	 * @param i the integer to store
	 */
	public void saveInt(String prefName, int i) {
		editor = settings.edit();
		editor.putInt(prefName, i);
		editor.commit();
	}

	/**
	 * Save string.
	 *
	 * @param prefName the pref name
	 * @param s the string to store
	 */
	public void saveString(String prefName, String s) {
		editor = settings.edit();
		editor.putString(prefName, s);
		editor.commit();
	}

	/**
	 * Save boolean.
	 *
	 * @param prefName the pref name
	 * @param b the values to store
	 */
	public void saveBoolean(String prefName, boolean b) {
		editor = settings.edit();
		editor.putBoolean(prefName, b);
		editor.commit();
	}

	/**
	 * Gets the cVIds.
	 *
	 * @return the cVIds
	 */
	public int[] getCVIDs() {
		int count = settings.getInt("cv_count", 0);
		int[] result = new int[count];
		for (int n = 0; n < count; n++) {
			result[n] = settings.getInt("cv_id" + n, 0);
		}
		return result;
	}

	/**
	 * Gets the cV names.
	 *
	 * @return the cV names
	 */
	public String[] getCVNames() {
		int count = settings.getInt("cv_count", 0);
		String[] result = new String[count];
		for (int n = 0; n < count; n++) {
			result[n] = settings.getString("cv_name" + n, "");
		}
		return result;
	}

	/**
	 * Append cvid.
	 *
	 * @param id the id
	 */
	public void appendCVID(int id) {
		// restore count
		int count = settings.getInt("cv_count", 0);
		// store
		editor = settings.edit();
		editor.putInt("cv_id" + count, id);
		editor.putInt("cv_count", ++count);
		editor.commit();
	}

	/**
	 * Append cv name.
	 *
	 * @param text the text
	 */
	public void appendCVName(String text) {
		// restore count
		int count = settings.getInt("cv_count", 0) - 1;
		// store
		editor = settings.edit();
		editor.putString("cv_name" + count, text);
		editor.commit();

	}

	/**
	 * Contain cvid.
	 *
	 * @param id the id
	 * @return true, if the CVIDs contain the given id
	 */
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