package com.mainapp.mynoppaapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DisplaySearchResultsActivity extends Activity{
	private ArrayAdapter mAdapter;
    final ArrayList<String> list = new ArrayList<String>();
    private String message;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_search_results);
		Intent intent = getIntent();
		//message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		final ListView listview = (ListView) findViewById(R.id.listview);
		listview.setAdapter(mAdapter);
		getSearchResults();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_search_results, menu);
		
	

		return true;
	}

	public void getSearchResults() {

		new Thread(new Runnable() {
			public void run() {
				try {
					final String r = MainActivity.CONNECTOR.getResults("computer");
					//final TextView resultlist = (TextView) findViewById(R.id.search_results);
					final ListView listview = (ListView) findViewById(R.id.listview);
					listview.post(new Runnable() {
						public void run() {

							JSONTokener jsontokener = new JSONTokener(r);

							try {
								JSONArray jsonarray = new JSONArray(jsontokener);
								for (int i = 0; i < jsonarray.length(); i++) {
									JSONObject obj = (JSONObject) jsonarray.get(i);
									String name = (String) obj.get("name");
									//resultlist.setText(name);
									list.add(name);
								}

							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});

					// }
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}).start();

	

	}

}
