package com.mainapp.mynoppaapp;

import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.inputmethodservice.Keyboard.Row;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayCourseDetails extends Activity {

	private Course course;
	private final String key = "key=cdda4ae4833c0114005de5b5c4371bb8";
	private final String host = "http://noppa-api-dev.aalto.fi/api/v1/";

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
		// create a new TableRow
		TableRow row = new TableRow(DisplayCourseDetails.this);
		// create a new TextView for showing xml data
		TextView t = new TextView(DisplayCourseDetails.this);
		// set the text to "text xx"
		t.setText(course.getCourse_id());
		row.addView(t);
		table.addView(row, new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		// create a new TableRow
		TableRow row2 = new TableRow(DisplayCourseDetails.this);
		// create a new TextView for showing xml data
		TextView t2 = new TextView(DisplayCourseDetails.this);
		// set the text to "text xx"
		t2.setText(course.getCourse_url());
		row2.addView(t2);
		// // add the TableRow to the TableLayout
		table.addView(row2, new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		// create a new TableRow
		TableRow row3 = new TableRow(DisplayCourseDetails.this);
		// create a new TextView for showing xml data
		TextView t3 = new TextView(DisplayCourseDetails.this);
		// set the text to "text xx"
		t3.setText(course.getNoppa_language());
		row3.addView(t3);
		// // add the TableRow to the TableLayout
		table.addView(row3, new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		final String url = host + "courses/" + course.getCourse_id()
				+ "/overview?" + key;
		new DownloadFilesTask().execute(url);

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_course_details, menu);
		return true;
	}

	private class DownloadFilesTask extends AsyncTask<String, Integer, Long> {
		private void setRow(String text){
			TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
			TableRow row4 = new TableRow(
					DisplayCourseDetails.this);
			// create a new TextView for showing xml
			// data
			TextView t4 = new TextView(
					DisplayCourseDetails.this);
			// set the text to "text xx"
			t4.setText(text);
			row4.addView(t4);
			// // add the TableRow to the
			// TableLayout
			table.addView(row4,
					new TableLayout.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
		}
		protected Long doInBackground(final String... url) {
		
			try {
				final String r = MainActivity.CONNECTOR.getResults(url[0]);
				JSONTokener jsontokener = new JSONTokener(r);
				final JSONObject obj = new JSONObject(jsontokener);
				TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
				table.post(new Runnable() {
					public void run() {

						synchronized (course) {
							try {
								Iterator it = obj.keys();
								while (it.hasNext())
								{
									setRow((String) obj.get((String) it.next()));
								}
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				});

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			
		}

		protected void onPostExecute(Long result) {
			TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
			TableRow row5 = new TableRow(DisplayCourseDetails.this);
			// create a new TextView for showing xml data
			TextView t5 = new TextView(DisplayCourseDetails.this);
			// set the text to "text xx"
			t5.setText("dummy");
			row5.addView(t5);
			// // add the TableRow to the TableLayout
			table.addView(row5, new TableLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar1);
			mProgress.setVisibility(View.GONE);
		}
	}

}
