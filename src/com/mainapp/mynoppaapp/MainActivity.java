package com.mainapp.mynoppaapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import connector.Connector;
import DataStructures.Course;
import DataStructures.Session;
import DataStructures.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.mainapp.mynoppaapp.MESSAGE";
    public final static String SESSION = "session_object";
    public final static Connector CONNECTOR = new Connector();
    
	private final static int BUFFERMAXLENGHT = 1000;
	public final static String FILENAME = "user_data";
	private Session session;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	public void sendMessage(View view){
		Intent intent = new Intent(this, DisplaySearchResultsActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		
		startActivity(intent);
		
	}
	public void openUserProfile(View view) {
		Intent intent = new Intent(this, DisplayUserProfile.class);
	
		startActivity(intent);
	}

	public void deleteFile(View view) {
		// write to file
		deleteFile(DisplayUserProfile.FILENAME);
		
	}
	
}
