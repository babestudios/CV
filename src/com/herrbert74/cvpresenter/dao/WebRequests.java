package com.herrbert74.cvpresenter.dao;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.herrbert74.cvpresenter.CVConstants;
import com.herrbert74.cvpresenter.pojos.LineOfInformation;
import com.herrbert74.cvpresenter.pojos.PageInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class WebRequests.
 */
public class WebRequests implements CVConstants {

	/**
	 * The listener interface for receiving getCVLines events.
	 * The class that is interested in processing a getCVLines
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addGetCVLinesListener<code> method. When
	 * the getCVLines event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see GetCVLinesEvent
	 */
	public interface GetCVLinesListener {
		
		/**
		 * Parsing finished.
		 *
		 * @param to the to
		 */
		public void parsingFinished(ArrayList<PageInfo> to);
	};

	/**
	 * Gets the CV lines through a helper class.
	 *
	 * @param context the context
	 * @param passCode the requested CV pass code
	 * @param isPassCodeSaved if saved, restore form preferences
	 * @param listener the listener interface to deal with the data in the activity
	 */
	public static void getCVLines(Context context, final int passCode, final boolean isPassCodeSaved, final GetCVLinesListener listener) {

		final Context ctx = context;
		WebRequestHelper.JSONParserListener jsonparserListener = new WebRequestHelper.JSONParserListener() {

			@Override
			public void parsingFinished(Object array, String response) {

				// build object array from json string
				JSONArray jArray = (JSONArray) array;
				ArrayList<PageInfo> pageInfos = new ArrayList<PageInfo>();

				for (int i = 0; i < jArray.length(); i++) {
					try {
						JSONObject json_data = jArray.getJSONObject(i);
						int page_id = Integer.parseInt(json_data.getString("id_user"));
						String page_name = json_data.getString("page_name");
						JSONArray page_lines = json_data.getJSONArray("0");
						String page_description = json_data.getString("page_description");

						ArrayList<LineOfInformation> lines = new ArrayList<LineOfInformation>();

						for (int i2 = 0; i2 < page_lines.length(); i2++) {
							JSONObject json_loi = page_lines.getJSONObject(i2);
							int id_line = Integer.parseInt(json_loi.getString("id_line"));
							int style = Integer.parseInt(json_loi.getString("id_style"));
							String caption = json_loi.getString("caption");
							String text = json_loi.getString("line_text");
							String detail = json_loi.getString("detail");
							String link = json_loi.getString("link");
							
							if (caption.equals("Name")) {
								SharedPreferencesHelper prefs = new SharedPreferencesHelper();
								// If this code was not saved yet, save id and name to preferences
								if (!isPassCodeSaved) {
									prefs.saveString(Integer.toString(passCode), response);
									if (!prefs.containCVID(passCode)) {
										prefs.appendCVID(passCode);
										prefs.appendCVName(text);
									}
								}
							}
							LineOfInformation loi = new LineOfInformation(id_line, style, caption, text, detail, link);
							lines.add(loi);
						}

						PageInfo pi = new PageInfo(page_id, page_name, page_description, lines);
						pageInfos.add(pi);
					} catch (JSONException e) {
						Log.e("log_tag", "Error parsing data " + e.toString());
					}
				}
				listener.parsingFinished(pageInfos);
			}

			@Override
			public void parsingFailed(Exception ex) {
				Toast.makeText(ctx, ex.getMessage(), Toast.LENGTH_LONG).show();
			}
		};

		String url = String.format(URL_PATH_CVLINES, passCode);
		WebRequestHelper.parseArray((Activity) context, url, passCode, isPassCodeSaved, jsonparserListener);

	}
}
