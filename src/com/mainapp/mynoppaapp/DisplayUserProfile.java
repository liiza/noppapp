package com.mainapp.mynoppaapp;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import DataStructures.Course;
import DataStructures.Session;
import DataStructures.User;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayUserProfile extends Activity {

	public final static String FILENAME = "user_data";
	private final static int BUFFERMAXLENGHT = 1000;
	private ArrayAdapter<String> mAdapter;
	final ArrayList<String> list = new ArrayList<String>();
	final ArrayList<Course> courselist = new ArrayList<Course>();
	public final static String EXTRA_MESSAGE = "com.mainapp.mynoppaapp.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_user_profile, menu);
		Intent intent = getIntent();
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
		
		
		//read file
		FileInputStream inputStream;
		byte[] buffer = new byte[BUFFERMAXLENGHT];
		int fileLenght;

		try {
			byte a = (byte) ';';
			String c = "";
			inputStream = openFileInput(FILENAME);
			fileLenght = inputStream.read(buffer);
			for (int i = 0; i < fileLenght; i++) {
				byte b = buffer[i];
				
				if (a == b) {
					String[] parts = c.split(":");
					c="";
					Course course = new Course (parts[1], parts[0]);
					courselist.add(course);
					mAdapter.add(course.getName());
				}
				else {
					c = c + (char)b;
				}

			}
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
	private void openCourseDetails(int position) {
		Intent intent = new Intent(this, DisplayCourseDetails.class);
		final Course course = courselist.get(position);
		intent.putExtra(EXTRA_MESSAGE, course);
		startActivity(intent);
	}

}
