package com.mainapp.mynoppaapp;


import java.util.ArrayList;

import datastructures.Course;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayUserProfileActivity extends Activity {

	public final static String FILENAME = "user_data";
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<Course> courselist = new ArrayList<Course>();
	public final static String EXTRA_MESSAGE = "com.mainapp.mynoppaapp.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				list);
		final ListView listview = (ListView) findViewById(R.id.listview);
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				openCourseDetails(position);
			}
		});
		listview.setAdapter(mAdapter);
		
		Session s = ((Session)getApplicationContext());
		courselist = s.getUser().courses;
		for (Course c: courselist){
			mAdapter.add(c.getName());
		}	
	}
	@Override
	protected void onResume(){
		super.onResume();
		mAdapter.clear();
		
		Session s = ((Session)getApplicationContext());
		courselist = s.getUser().courses;
		for (Course c: courselist){
			mAdapter.add(c.getName());
		}
		final ListView listview = (ListView) findViewById(R.id.listview);
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				openCourseDetails(position);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_user_profile, menu);
		return true;
	}
	private void openCourseDetails(int position) {
		Intent intent = new Intent(this, DisplayCourseDetailsActivity.class);
		final Course course = courselist.get(position);
		intent.putExtra(EXTRA_MESSAGE, course);
		startActivity(intent);
	}

}
