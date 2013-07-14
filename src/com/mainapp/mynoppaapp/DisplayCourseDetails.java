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
//		TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
//		table.post(new Runnable() {
//			public void run() {
//				try {
////					synchronized (course.getDetails()) {
//						TextView row2 = (TextView) findViewById(R.id.Row2Text);
//						row2.setText(course.getDetail("credits"));
//						Set set = course.getEntrySet();
//						Iterator it = set.iterator();
//
//						while (it.hasNext()) {
//							it.next().toString();
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
////					};
//
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
		
		
		new DownloadFilesTask().execute("urli1");

		// .start();

		// TextView row2 = (TextView) findViewById(R.id.Row2Text);
		// row2.setText(course.getDetail("credits"));
		// Set set = course.getEntrySet();
		// Iterator it = set.iterator();
		// while (it.hasNext()){
		// // get a reference for the TableLayout
		// TableLayout table = (TableLayout)findViewById(R.id.TableLayout01);
		// // create a new TableRow
		// TableRow row = new TableRow(this);
		// // create a new TextView for showing xml data
		// TextView t = new TextView(this);
		// // set the text to "text xx"
		// t.setText(it.next().toString());
		// // add the TextView to the new TableRow
		// row.addView(t);
		// // add the TableRow to the TableLayout
		// table.addView(row, new
		// TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));
		// }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_course_details, menu);
		return true;
	}

	private class DownloadFilesTask extends AsyncTask<String, Integer, Long> {
		

		protected void onProgressUpdate(Integer... progress) {
			setProgress(progress[0]);
		}

		protected void onPostExecute(Long result) {
			constructUI();
		}

		@Override
		protected Long doInBackground(String... params) {
//			// TODO Auto-generated method stub
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			try {
//				if (course==null){
//					System.out.print("course null!!!");
//				}
//				synchronized (course.getDetails()) {
//					TextView row2 = (TextView) findViewById(R.id.Row2Text);
//					row2.setText(course.getDetail("credits"));
//					Set set = course.getEntrySet();
//					Iterator it = set.iterator();
//
//					while (it.hasNext()) {
//						it.next().toString();
//						// get a reference for the TableLayout
//						TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
//						// create a new TableRow
//						TableRow row = new TableRow(
//								DisplayCourseDetails.this);
//						// create a new TextView for showing xml data
//						TextView t = new TextView(DisplayCourseDetails.this);
//						// set the text to "text xx"
//						t.setText(it.next().toString());
//						// add the TextView to the new TableRow
//						row.addView(t);
//						// add the TableRow to the TableLayout
//						table.addView(row, new TableLayout.LayoutParams(
//								LayoutParams.WRAP_CONTENT,
//								LayoutParams.WRAP_CONTENT));
//					}
//				}
//				;
//
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			return null;
		}
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
