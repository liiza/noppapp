package com.mainapp.mynoppaapp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class DisplaySearchResultsActivity extends Activity {

	private final String key = "key=cdda4ae4833c0114005de5b5c4371bb8";
	private final String host = "http://noppa-api-dev.aalto.fi/api/v1/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_search_results);
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		getSearchResults();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_search_results, menu);

		return true;
	}

	public void getSearchResults() {
		final String url = host + "courses?" + key + "&search=" + "computer";
		
		new Thread(new Runnable() {
			public void run() {
				try {

					HttpClient client = new DefaultHttpClient();
					String getURL = url;
					HttpGet get = new HttpGet(getURL);
					HttpResponse responseGet = client.execute(get);
					HttpEntity resEntityGet = responseGet.getEntity();
					if (resEntityGet != null) {
						// do something with the response
						final String r = EntityUtils.toString(resEntityGet);
						final TextView resultlist = (TextView) findViewById(R.id.search_results);
						// resultlist.setText(course);
						resultlist.post(new Runnable() {
							public void run() {
								//resultlist.setText(r);
								JSONTokener jsontokener = new JSONTokener(r);

								try {
									JSONArray jsonarray = new JSONArray(jsontokener);
									JSONObject obj = (JSONObject) jsonarray.get(0);
									String name = (String)obj.get("name");
									TextView resultlist = (TextView) findViewById(R.id.search_results);
									resultlist.setText(name);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});

					}
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}).start();

		// String results = MainActivity.CONNECTOR.getResults("computer", this);
		// JSONTokener jsontokener = new JSONTokener(results);
		//
		// try {
		// JSONObject jsonob = new JSONObject(jsontokener);
		// JSONArray courses = jsonob.names();
		// String course =courses.get(0).toString();
		// TextView resultlist = (TextView) findViewById(R.id.search_results);
		// resultlist.setText(course);
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
