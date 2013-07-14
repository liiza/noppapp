package com.mainapp.mynoppaapp;

import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONObject;
import org.json.JSONTokener;

import android.inputmethodservice.Keyboard.Row;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayCourseDetails extends Activity {

	private Course course;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_course_details);
		Intent intent = getIntent();
		course = (Course) intent
				.getSerializableExtra(DisplaySearchResultsActivity.EXTRA_MESSAGE);
		
		TextView row1 = (TextView) findViewById(R.id.Row1Text);
		row1.setText(course.getName());
		
		TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
		 //create a new TableRow
		TableRow row = new TableRow(
				DisplayCourseDetails.this);
		// create a new TextView for showing xml data
		TextView t = new TextView(DisplayCourseDetails.this);
		// set the text to "text xx"
		t.setText(course.getCourse_id());
		row.addView(t);
		table.addView(row, new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		
		 //create a new TableRow
		TableRow row2 = new TableRow(
				DisplayCourseDetails.this);
		// create a new TextView for showing xml data
		TextView t2 = new TextView(DisplayCourseDetails.this);
		// set the text to "text xx"
		t2.setText(course.getCourse_url());
		row2.addView(t2);
//		// add the TableRow to the TableLayout
		table.addView(row2, new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		
		
		 //create a new TableRow
		TableRow row3 = new TableRow(
				DisplayCourseDetails.this);
		// create a new TextView for showing xml data
		TextView t3 = new TextView(DisplayCourseDetails.this);
		// set the text to "text xx"
		t3.setText(course.getNoppa_language());
		row3.addView(t3);
//		// add the TableRow to the TableLayout
		table.addView(row3, new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		
//		table.post(new Runnable() {
//			public void run() {
//				try {
//					synchronized (course.getDetails()) {
//						TextView row2 = (TextView) findViewById(R.id.Row2Text);
//						row2.setText(course.getDetail("credits"));
//						Set set = course.getEntrySet();
//						Iterator it = set.iterator();
//
//						while (it.hasNext()) {
//							
//							// get a reference for the TableLayout
//							TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
//							// create a new TableRow
//							TableRow row = new TableRow(
//									DisplayCourseDetails.this);
//							// create a new TextView for showing xml data
//							TextView t = new TextView(DisplayCourseDetails.this);
//							// set the text to "text xx"
//							t.setText(it.next().toString());
//							// add the TextView to the new TableRow
//							row.addView(t);
//							// add the TableRow to the TableLayout
//							table.addView(row, new TableLayout.LayoutParams(
//									LayoutParams.WRAP_CONTENT,
//									LayoutParams.WRAP_CONTENT));
//						}
//					};
//
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
		
		
	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_course_details, menu);
		return true;
	}


	
	private void constructUI(){
		try {
			synchronized (course.getDetails()) {
				TextView row2 = (TextView) findViewById(R.id.Row2Text);
				row2.setText(course.getDetail("credits"));
				Set set = course.getEntrySet();
				Iterator it = set.iterator();

				while (it.hasNext()) {
					//it.next().toString();
					// get a reference for the TableLayout
					TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
					// create a new TableRow
					TableRow row = new TableRow(
							DisplayCourseDetails.this);
					// create a new TextView for showing xml data
					TextView t = new TextView(DisplayCourseDetails.this);
					// set the text to "text xx"
					t.setText(it.next().toString());
					// add the TextView to the new TableRow
					row.addView(t);
					// add the TableRow to the TableLayout
					table.addView(row, new TableLayout.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
				}
			};

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
