package com.herrbert74.cvpresenter.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.herrbert74.cvpresenter.CVConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class WebRequestHelper.
 * 
 * Defines an interface to deal with the data in the WebRequest class(@see JSPNParserListener)
 */
public class WebRequestHelper implements CVConstants {
	
	/**
	 * The listener interface for receiving JSONParser events.
	 * The class that is interested in processing a JSONParser
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addJSONParserListener<code> method. When
	 * the JSONParser event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see JSONParserEvent
	 */
	public interface JSONParserListener {
		
		/**
		 * Parsing finished.
		 *
		 * @param array the array
		 * @param response the response
		 */
		public void parsingFinished(Object array, String response);

		/**
		 * Parsing failed.
		 *
		 * @param ex the ex
		 */
		public void parsingFailed(Exception ex);
	}

	/**
	 * Start http request.
	 *
	 * @param context the context
	 * @param _url the _url to download
	 * @return the http response
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws Exception the exception
	 */
	private static HttpResponse startHTTPRequest(Context context, String _url) throws ClientProtocolException,
			IOException, Exception {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

		if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting()) {
			throw new Exception("There is no internet connection!");
		}

		HttpResponse response = null;

		HttpClient client = new DefaultHttpClient();
		HttpGet post = new HttpGet(_url);
		response = client.execute(post);

		return response;
	}

	/**
	 * Json to array.
	 *
	 * @param array the array
	 * @return the array list
	 */
	@Deprecated
	public static ArrayList<JSONObject> jsonToArray(JSONArray array) {
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		if (array != null) {
			int len = array.length();
			for (int i = 0; i < len; i++) {
				try {
					list.add((JSONObject) array.get(i));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * Parses the array.
	 *
	 * @param activity the activity
	 * @param _url the _url
	 * @param passCode the pass code
	 * @param isPassCodeSaved if saved, restore form preferences
	 * @param _listener the listener interface to deal with the data in the WebRequests class
	 */
	public static void parseArray(final Activity activity, final String _url, final int passCode, final boolean isPassCodeSaved, final JSONParserListener _listener) {
		final String request = (!_url.startsWith("http://") ? SERVER_DOMAIN + _url : _url);
		final SharedPreferencesHelper prefs = new SharedPreferencesHelper();
		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpResponse response;
				try {
					BufferedReader reader;
					try {
						final JSONTokener tokener;
						final String responseString;
						//Web request
						if (!isPassCodeSaved) {
							response = startHTTPRequest(activity, request);
							reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
							StringBuilder builder = new StringBuilder();
							for (String line = null; (line = reader.readLine()) != null;) {
								builder.append(line).append("\n");
							}
							tokener = new JSONTokener(builder.toString());

							responseString = builder.toString();
						} 
						//Restore from preferences
						else {
							String restoreCV = prefs.restoreCV(Integer.toString(passCode));
							tokener = new JSONTokener(restoreCV);
							responseString = restoreCV;

						}
						activity.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Object finalResult = null;
								try {
									parseJSON(_listener, tokener, responseString);
								} catch (JSONException ex) {
									ex.printStackTrace();
								}
							}

						});
					}

					catch (final Exception ex) {
						activity.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_listener.parsingFailed(ex);
							}

						});
					}
				} catch (final Exception ex) {
					activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_listener.parsingFailed(ex);
						}

					});
				}

			}

		}).start();
	}

	/**
	 * Parses the JSON data.
	 *
	 * @param _listener the _listener
	 * @param tokener the tokener
	 * @param responseString the response string
	 * @throws JSONException the jSON exception
	 */
	public static void parseJSON(final JSONParserListener _listener, final JSONTokener tokener, final String responseString)
			throws JSONException {
		Object finalResult;
		finalResult = tokener.nextValue();
		if (finalResult instanceof JSONObject)
			_listener.parsingFinished(finalResult, responseString);
		else if (finalResult instanceof JSONArray)
			_listener.parsingFinished((JSONArray) finalResult, responseString);
		else {
			_listener.parsingFailed(new Exception("Command failed!"));
		}
	}

	/**
	 * Download image.
	 *
	 * @param fileUrl the file url
	 * @return the bitmap
	 */
	public static Bitmap downloadImage(String fileUrl) {
		if (fileUrl == null || fileUrl.equals("") || fileUrl.equals("no-pic.png"))
			return null;

		URL myFileUrl = null;
		try {
			myFileUrl = new URL(fileUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();

			return BitmapFactory.decodeStream(is);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
