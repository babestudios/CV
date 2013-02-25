package com.herrbert74.cv.dao;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.herrbert74.cv.CVConstants;
import com.herrbert74.cv.pojos.LineOfInformation;
import com.herrbert74.cv.pojos.PageInfo;

public class WebRequests implements CVConstants {

	public interface GetCVLinesListener {
		public void parsingFinished(ArrayList<PageInfo> to);
	};

	public static void getCVLines(final int id, Context context, final GetCVLinesListener listener) {

		final Context ctx = context;
		WebRequestHelper.JSONParserListener jsonparserListener = new WebRequestHelper.JSONParserListener() {

			@Override
			public void parsingFinished(Object array) {
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
							int style = Integer.parseInt(json_loi.getString("id_style"));
							String image = json_loi.getString("image");
							String caption = json_loi.getString("caption");
							String text = json_loi.getString("line_text");
							String level = json_loi.getString("level");
							LineOfInformation loi = new LineOfInformation(style, image, caption, text, level);
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

		String url = String.format(URL_PATH_CVLINES, id);
		WebRequestHelper.parseArray((Activity) context, url, null, jsonparserListener);

	}
}
