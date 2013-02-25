package com.herrbert74.cv.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
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

import com.herrbert74.cv.CVConstants;

	public class WebRequestHelper implements CVConstants{
		public interface JSONParserListener
		{
			public void parsingFinished(Object array);
			public void parsingFailed(Exception ex);
		}
		
		private static HttpResponse startHTTPRequest(Context context, String _url, List<NameValuePair> list) throws ClientProtocolException, IOException, Exception
		{
			ConnectivityManager  connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
	        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
	        
	        if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting())
	        {
	        	throw new Exception("Nincs internetkapcsolat!");
	        }
	        
			HttpResponse response = null;
			
	        HttpClient client = new DefaultHttpClient();
	        HttpGet post = new HttpGet(_url);
	        /*
	        HttpPost post = new HttpPost(_url);
	        post.setEntity(new StringEntity("body", HTTP.UTF_8));
	        if (list != null) post.setEntity(new UrlEncodedFormEntity(list));*/
	        response = client.execute(post);
	        
	        return response;
		}
		
		private static ArrayList<JSONObject> jsonToArray(JSONArray array)
		{
			ArrayList<JSONObject> list = new ArrayList<JSONObject>();     
			if (array != null) { 
			   int len = array.length();
			   for (int i=0;i<len;i++){ 
			    try {
					list.add((JSONObject) array.get(i));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			   } 
			}
			
			return list;
		}
		
		public static void parseArray(final Activity activity, final String _url, final List<NameValuePair> list, final JSONParserListener _listener)
		{
			final String request = (!_url.startsWith("http://") ? SERVER_DOMAIN + _url : _url);
			
			new Thread(new Runnable() {

				@Override
				public void run()
				{
					HttpResponse response;
					try {
						response = startHTTPRequest(activity, request, list);
						
						BufferedReader reader;
						try
						{
							reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
							StringBuilder builder = new StringBuilder();
							for (String line = null; (line = reader.readLine()) != null;) {
							    builder.append(line).append("\n");
							}
							final JSONTokener tokener = new JSONTokener(builder.toString());

							activity.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Object finalResult = null;
									try {
										finalResult = tokener.nextValue();
										if (finalResult instanceof JSONObject)
											_listener.parsingFinished(finalResult);
										else if (finalResult instanceof JSONArray)
											_listener.parsingFinished((JSONArray)finalResult);
										else
										{
											//if ((Boolean)finalResult == false)
											//{
												_listener.parsingFailed(new Exception("Command failed!"));
											//}
										}
											
									}
									catch (JSONException ex)
									{
										ex.printStackTrace();
									}
								}
							});
						}
						
						catch (final Exception ex)
						{
							activity.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_listener.parsingFailed(ex);							
								}
								
							});
						}
					}
					catch (final Exception ex) {
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
		
	    public static Bitmap downloadImage(String fileUrl)
	    {
	    	if (fileUrl == null || fileUrl.equals("") || fileUrl.equals("no-pic.png")) return null;
	    	
	        URL myFileUrl =null;          
	        try {
	             myFileUrl= new URL(fileUrl);
	        } catch (MalformedURLException e) {
	             e.printStackTrace();
	        }
	        try {
	             HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
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
