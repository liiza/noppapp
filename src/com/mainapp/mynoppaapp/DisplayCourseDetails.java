package com.mainapp.mynoppaapp;

import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import Animation.DropDownAnimation;
import DataStructures.Course;
import DataStructures.Session;
import android.graphics.Point;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard.Row;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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

	private boolean detailsDownloaded = false;
	private boolean lecturesDownloaded = false;
	private boolean assigmentsDownloaded = false;
	private boolean resultsDownloaded = false;

	// DATA TYPES for loading course data
	private final int DETAILS = 1;
	private final int LECTURES = 2;
	private final int ASSIGMENTS = 3;
	private final int RESULTS = 4;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_course_details);
		Intent intent = getIntent();
		course = (Course) intent
				.getSerializableExtra(DisplaySearchResultsActivity.EXTRA_MESSAGE);
	
		TextView header = (TextView) findViewById(R.id.header);
		header.setText(course.getCourse_id() + " - " + course.getName());
		header.setTextSize(20);
		header.setTypeface(null, Typeface.BOLD);

		TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
		if (table.getVisibility() == View.VISIBLE) {
			table.setVisibility(View.GONE);
		}
		TableLayout table2 = (TableLayout) findViewById(R.id.TableLayout02);
		if (table2.getVisibility() == View.VISIBLE) {
			table.setVisibility(View.GONE);
		}

		ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar1);
		mProgress.setVisibility(View.GONE);

		ProgressBar mProgress2 = (ProgressBar) findViewById(R.id.progressBar2);
		mProgress2.setVisibility(View.GONE);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_course_details, menu);
		return true;
	}

	@SuppressLint("NewApi")
	private void setRow(String text, boolean bolded, int id) {
		TableLayout table = (TableLayout) findViewById(id);
		TableRow row = new TableRow(DisplayCourseDetails.this);
		// create a new TextView for showing xml
		TextView t = new TextView(DisplayCourseDetails.this);

		// check whether given text has some html tags..
		// Log.e("MYAPP", "exception", e);
		text = Html.fromHtml(text).toString();

		t.setText(text);
		t.setTextIsSelectable(true);
		t.setTextSize(18);
		if (bolded) {
			t.setTypeface(null, Typeface.BOLD);
		}

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

	public void expandCollapseTable(View view) {
		TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
		if (table.getVisibility() == View.VISIBLE) {
			table.setVisibility(View.GONE);
		} else if (table.getVisibility() == View.GONE) {
			table.setVisibility(View.VISIBLE);
			if (!this.detailsDownloaded) {

				final String url = host + "courses/" + course.getCourse_id()
						+ "/overview?" + key;
				new DownloadFilesTask(DETAILS).execute(url);
				this.detailsDownloaded = true;
			}
		}

	}

	public void expandCollapseLectures(View view) {
		TableLayout table = (TableLayout) findViewById(R.id.TableLayout02);
		if (table.getVisibility() == View.VISIBLE && this.lecturesDownloaded) {
			table.setVisibility(View.GONE);
		} else if (table.getVisibility() == View.GONE
				|| !this.lecturesDownloaded) {
			table.setVisibility(View.VISIBLE);
			if (!this.lecturesDownloaded) {

				final String url = host + "courses/" + course.getCourse_id()
						+ "/lectures?" + key;
				final String url2 = host + "courses/" + course.getCourse_id()
						+ "/exercises?" + key;
				new DownloadFilesTask(LECTURES).execute(url, url2);
				this.lecturesDownloaded = true;
			}
		}
	}

	public void expandCollapseAssigments(View view) {
		TableLayout table = (TableLayout) findViewById(R.id.TableLayout03);
		if (table.getVisibility() == View.VISIBLE && this.assigmentsDownloaded) {
			table.setVisibility(View.GONE);
		} else if (table.getVisibility() == View.GONE
				|| !this.assigmentsDownloaded) {
			table.setVisibility(View.VISIBLE);
			if (!this.assigmentsDownloaded) {

				final String url = host + "courses/" + course.getCourse_id()
						+ "/assignments?" + key;
				new DownloadFilesTask(ASSIGMENTS).execute(url);
				this.assigmentsDownloaded = true;
			}
		}

	}

	public void expandCollapseResults(View view) {
		TableLayout table = (TableLayout) findViewById(R.id.TableLayout04);
		if (table.getVisibility() == View.VISIBLE && this.resultsDownloaded) {
			table.setVisibility(View.GONE);
		} else if (table.getVisibility() == View.GONE
				|| !this.resultsDownloaded) {
			table.setVisibility(View.VISIBLE);
			if (!this.resultsDownloaded) {

				final String url = host + "courses/" + course.getCourse_id()
						+ "/results?" + key;
				new DownloadFilesTask(RESULTS).execute(url);
				this.resultsDownloaded = true;
			}
		}
	}

	public void writeToFile(View view) {
		// write to file
		FileOutputStream outputStream;
		String string = course.getName()+ ":" + course.getCourse_id() + ";";
		try {
			outputStream = openFileOutput(DisplayUserProfile.FILENAME, Context.MODE_APPEND);
			outputStream.write(string.getBytes());
			outputStream.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private class DownloadFilesTask extends AsyncTask<String, Integer, Long> {

		private int datatype;

		public DownloadFilesTask(int type) {
			super();
			this.datatype = type;
		}

		protected Long doInBackground(final String... url) {
			if (this.datatype == DisplayCourseDetails.this.DETAILS) {
				ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar1);
				mProgress.setVisibility(View.VISIBLE);

				try {
					final String r = MainActivity.CONNECTOR.getResults(url[0]);
					JSONTokener jsontokener = new JSONTokener(r);
					final JSONObject obj = new JSONObject(jsontokener);
					TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
					table.post(new Runnable() {
						public void run() {

							try {
								Iterator it = obj.keys();
								while (it.hasNext()) {
									// TODO consider saving this values
									// somewhere for later use
									Object key = it.next();
									setRow((String) key, true,
											R.id.TableLayout01);
									setRow(textToString(obj.get((String) key)),
											false, R.id.TableLayout01);

								}

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						private String textToString(Object o) {
							String text = "";
							try {
								text = (String) o;
							} catch (ClassCastException ce) {
								ce.printStackTrace();
								// TODO some handling here if data is not string
							}
							return text;
						}
					});

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (this.datatype == DisplayCourseDetails.this.LECTURES) {
				try {
					final String r = MainActivity.CONNECTOR.getResults(url[0]);
					JSONTokener jsontokener = new JSONTokener(r);
					final JSONArray array = new JSONArray(jsontokener);

					final String r2 = MainActivity.CONNECTOR.getResults(url[1]);
					JSONTokener jsontokener2 = new JSONTokener(r2);
					final JSONArray exercises = new JSONArray(jsontokener2);

					TableLayout table = (TableLayout) findViewById(R.id.TableLayout02);
					table.post(new Runnable() {
						public void run() {

							try {
								int i = 0;
								setRow("Lectures", true, R.id.TableLayout02);
								while (i < array.length()) {
									// TODO consider saving this values
									// somewhere for later use
									JSONObject lecture = (JSONObject) array
											.get(i);
									setRow((String) lecture.get("title"), true,
											R.id.TableLayout02);
									setRow((String) lecture.get("date") + " "
											+ lecture.get("start_time") + " - "
											+ (String) lecture.get("end_time")
											+ " "
											+ (String) lecture.get("location"),
											false, R.id.TableLayout02);

									i++;
								}
								int j = 0;
								setRow("Exercises", true, R.id.TableLayout02);
								while (j < exercises.length()) {
									JSONObject lecture = (JSONObject) exercises
											.get(j);
									setRow((String) lecture.get("group") + " "
											+ (String) lecture.get("weekday")
											+ " " + lecture.get("start_time")
											+ " - "
											+ (String) lecture.get("end_time")
											+ " "
											+ (String) lecture.get("location"),
											true, R.id.TableLayout02);

									setRow(lecture.get("start_date") + " - "
											+ (String) lecture.get("end_date"),
											false, R.id.TableLayout02);
									j++;
								}

							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						private String textToString(Object o) {
							String text = "";
							try {
								text = (String) o;
							} catch (ClassCastException ce) {
								ce.printStackTrace();

							}
							return text;
						}
					});

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (this.datatype == DisplayCourseDetails.this.ASSIGMENTS) {
				try {
					final String r = MainActivity.CONNECTOR.getResults(url[0]);
					JSONTokener jsontokener = new JSONTokener(r);
					final JSONArray array = new JSONArray(jsontokener);
					TableLayout table = (TableLayout) findViewById(R.id.TableLayout03);
					table.post(new Runnable() {
						public void run() {

							try {
								int i = 0;
								while (i < array.length()) {
									// TODO consider saving this values
									// somewhere for later use
									JSONObject assigment = (JSONObject) array
											.get(i);

									setRow((String) assigment.get("deadline")
											+ " "
											+ (String) assigment.get("title"),
											true, R.id.TableLayout03);

									setRow((String) assigment.get("content"),
											false, R.id.TableLayout03);
									i++;
								}

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						private String textToString(Object o) {
							String text = "";
							try {
								text = (String) o;
							} catch (ClassCastException ce) {
								ce.printStackTrace();
								// TODO some handling here if data is not string
							}
							return text;
						}
					});

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if (this.datatype == DisplayCourseDetails.this.RESULTS) {
				try {
					final String r = MainActivity.CONNECTOR.getResults(url[0]);
					JSONTokener jsontokener = new JSONTokener(r);
					final JSONArray array = new JSONArray(jsontokener);
					TableLayout table = (TableLayout) findViewById(R.id.TableLayout04);
					table.post(new Runnable() {

						public void run() {

							try {

								int len = array.length();
								JSONObject[] list = new JSONObject[len];

								for (int i = 0; i < len; i++) {
									list[i] = (JSONObject) array.get(i);
								}
								Arrays.sort(list, new Comparator<JSONObject>() {
									public int compare(JSONObject a,
											JSONObject b) {
										try {
											String date1str = (String) ((JSONObject) a)
													.get("date");
											String date2str = (String) ((JSONObject) b)
													.get("date");
											SimpleDateFormat formatter = new SimpleDateFormat(
													"yyyy-MM-dd");
											Date date1 = (Date) formatter
													.parse(date1str);
											Date date2 = (Date) formatter
													.parse(date2str);
											if (date1.after(date2)) {
												return -1;
											}
											if (date2.after(date1)) {
												return 1;
											}

										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											return 0;
										}
										return 0;

									}
								});

								// show five first results
								for (int i = 0; i < Math.min(5, list.length); i++) {
									// TODO consider saving this values
									// somewhere for later use
									JSONObject assigment = list[i];

									setRow((String) assigment.get("date")
											+ " "
											+ (String) assigment
													.get("grade_name"), true,
											R.id.TableLayout04);

									setRow((String) assigment.get("url"),
											false, R.id.TableLayout04);
								}

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						private String textToString(Object o) {
							String text = "";
							try {
								text = (String) o;
							} catch (ClassCastException ce) {
								ce.printStackTrace();
								// TODO some handling here if data is not string
							}
							return text;
						}
					});

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
