package com.mainapp.mynoppaapp;

import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import android.graphics.Point;
import android.inputmethodservice.Keyboard.Row;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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

		setRow(course.getName());
		setRow(course.getCourse_id());
		setRow(course.getCourse_url());
		setRow(course.getNoppa_language());

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

	@SuppressLint("NewApi")
	private void setRow(String text) {
		TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
		TableRow row = new TableRow(DisplayCourseDetails.this);
		// create a new TextView for showing xml
		TextView t = new TextView(DisplayCourseDetails.this);

		// check whether given text has some html tags..
		// Log.e("MYAPP", "exception", e);
		text = Html.fromHtml(text).toString();

		
		t.setText(text);
		t.setTextIsSelectable(true);
		t.setTextSize(22);

		TableRow.LayoutParams l = new TableRow.LayoutParams(
				TableLayout.LayoutParams.WRAP_CONTENT,
				TableLayout.LayoutParams.WRAP_CONTENT, 1f);
		t.setLayoutParams(l);
		row.addView(t);
		// // add the TableRow to the
		// TableLayout

		table.addView(row, new TableLayout.LayoutParams(
				TableLayout.LayoutParams.MATCH_PARENT,
				TableLayout.LayoutParams.MATCH_PARENT));

	}

	private class DownloadFilesTask extends AsyncTask<String, Integer, Long> {

		protected Long doInBackground(final String... url) {
			ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar1);
			mProgress.setVisibility(View.VISIBLE);

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
								while (it.hasNext()) {
									// TODO consider saving this values
									// somewhere for later use
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

			ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar1);
			mProgress.setVisibility(View.GONE);
		}
	}

}
