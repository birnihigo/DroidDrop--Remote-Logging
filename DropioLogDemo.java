package com.brightkeep.dropiologdemo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;

import com.brightkeep.dropionotes.DroidDropNoteClient;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


//http://drop.io/droiddropremotelog/m?view_type=blog#contents
public class DropioLogDemo extends Activity {
	ArrayList<String> domainlist = new ArrayList<String>();
	String output="123456789";
	ListView choices;
	Button getDomainInfo;
	Button browse;
	Button listItems;
	Button voiceQuery;
	Button savedQueries;
	TextView t;
	String key;
	String secret;
	String url="";
	 static final String[] CHOICES = new String[] {
		     "Update Demo Log","View Demo Log in Browser", "Short Video","Get DroidDrop", "Upgrade Your Drop"
		  };
		public static final String PREFS_NAME = "com.brightkeep.dropio.droid.adc.Settings";
		DroidDropNoteClient dropClient= new DroidDropNoteClient("--YOUR DROP.IO DEVELOPER API KEY--");

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.droiddrop);
      	choices = (ListView)findViewById(R.id.choices);
      	
    	ArrayAdapter <String> choicesList = new ArrayAdapter<String>(this,
      			android.R.layout.simple_list_item_1 ,CHOICES);
    	
        choices.setAdapter(choicesList);
        choices.setTextFilterEnabled(true);
		choices.setOnItemClickListener(new OnItemClickListener(){


			public void onItemClick(AdapterView l, View v,
					int position, long id) {
            	//int i = choices.getSelectedItemPosition();
            	switch (position){
            	
            	case 0: startActivity(new Intent(".UpdateLog"));
            	break;
            	
            	case 1:
				url = "http://drop.io/droiddropremotelog/m?view_type=blog#contents";
				Intent showURL = new Intent("android.intent.action.VIEW");
				showURL.setData(Uri.parse(url));
				startActivity(showURL);
            	
            	
            	break;
            	

            	
            	case 2: 
                	url = "http://www.youtube.com/watch?v=OWwfp1cQ8WA";
    				showURL = new Intent("android.intent.action.VIEW");
    				showURL.setData(Uri.parse(url));
    				startActivity(showURL);
    				break;


            	case 3: 		
            	url = "http://market.android.com/search?q=pname:com.brightkeep.droiddrop";
				showURL = new Intent("android.intent.action.VIEW");
				showURL.setData(Uri.parse(url));
				startActivity(showURL);
				break;

            	
            	case 4: 
            	url = "https://drop.io/purchase/upgrade?affiliate_code=DroidDrop";
				 showURL = new Intent("android.intent.action.VIEW");
				showURL.setData(Uri.parse(url));
				startActivity(showURL);
            	break;
            	

            	
            	}
		

			}

		}); 

        



	}
	
}