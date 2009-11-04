package com.brightkeep.dropiologdemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.brightkeep.dropionotes.DroidDropNoteClient;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateLog extends Activity {

	Button logException;
	EditText note;
	Button addNote;

	DroidDropNoteClient dropClient= new DroidDropNoteClient("--YOUR DROP.IO DEVELOPER API KEY--");


	String results=null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updatelog);
		dropClient.setToken("remotedemo1028");

		logException= (Button)findViewById(R.id.LogException);
		logException.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					System.out.println("in try");
					int numerator = 10;
					int denominator = 0;
					int oops = numerator/denominator;
				} catch (java.lang.ArithmeticException e) {
					try {
						results = dropClient.createNote( "droiddropremotelog",  "UpdateLog code: " + e.getMessage(),  e.toString());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}


			}
		}); 

		note = (EditText)findViewById(R.id.Note);
		addNote= (Button)findViewById(R.id.AddNote);
		addNote.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					// clean user entered string
					String noteString = note.getText().toString();
	            	if (noteString!=null){
	            		noteString=noteString.trim();
	            		if (noteString.length()==0) noteString=null;
	            	}
	            	
	            	// Create a timestamp for title
	        		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	        		df.setTimeZone(TimeZone.getTimeZone("UTC"));

	            	
	            	//add to drop
					results = dropClient.createNote( "droiddropremotelog",  "User Entry "+ df.format(new Date()),  noteString);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}); 


	}

}