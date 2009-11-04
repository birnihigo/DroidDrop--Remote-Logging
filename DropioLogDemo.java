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
		DroidDropNoteClient dropClient= new DroidDropNoteClient("0e7824af253fab32035a978dc9ccc2f5c2336e9b");

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
            	
            	//https://drop.io/purchase/upgrade?affiliate_code=DroidDrop
            	
            	}
		

			}

		}); 
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		String firstUse= settings.getString("first", "true");
		if (firstUse.equals("true")){
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("first", "false");
	        editor.commit();
	  		showDemoDialog();
		}

        



	}
	public void showDemoDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(DropioLogDemo.this);
		builder.setTitle("DroidDrop Beta");
		StringBuffer message =new StringBuffer();
		//message.append("The Android App for drop.io. \n");
		message.append("Use drop.io to privately share your files & notes in \"drops.\"\n\n");

		message.append("Use DropDroid to:\n");
		message.append("-Create & View Drops\n");
		//message.append("View Drops, ");
		message.append("-Add Notes, Links, and Photos\n");
		//message.append("Add Links, ");
		message.append("-Add Photos from the Gallery\n");
		message.append("\n");
		message.append("Try demo drop:droiddropdemo\n");
		//message.append("is a live drop with more info.\n");
		message.append("View and update in the demo and then create your own drops.\n\n");
		message.append("Feedback and ideas wanted! Error reports, too.\n");
		message.append("http://twitter.com/droiddrop  \n");

		//message.append(" a drop of your own\n");
  		builder.setMessage(message)
  		.setCancelable(false)

  		.setPositiveButton("Go to Demo Drop", new DialogInterface.OnClickListener() {
  			public void onClick(DialogInterface dialog, int id) {
  				//DomainList.this.finish();
  				// startActivity(new Intent("com.brightkeep.android.aws.main")); 
  				dialog.cancel();
  				// refresh drop block
  				String errorMsg;
  				String token=null; 
  				try {
  					String results = dropClient.getDrop("droiddropdemo", token);
  					if (results==null){
  						Toast.makeText(getBaseContext(),  "Error Getting Demo Drop. ", 
  								Toast.LENGTH_LONG).show();
  					}else{
  						//System.out.println(results);


  						Intent i = new Intent(".AssetListActivity");
  						Bundle b = new Bundle();
  						b.putString("DropInfo", results);
  						i.putExtras(b);
  						startActivity (i);


  					}
  				} catch (ClientProtocolException e) {
  					Toast.makeText(getBaseContext(),  "Error Going to Drop.  Connection Error", 
  							Toast.LENGTH_LONG).show();
  				} catch (UnsupportedEncodingException e) {
  					Toast.makeText(getBaseContext(),  "Error Going to  Drop.  Could not encode values ", 
  							Toast.LENGTH_LONG).show();
  				} catch (ParseException e) {
  					Toast.makeText(getBaseContext(),  "Error Going to  Drop.  Could not undertand response ", 
  							Toast.LENGTH_LONG).show();
  				} catch (Exception e) {
  					if (e.getMessage()!=null) errorMsg= e.getMessage(); else errorMsg= "Something went wrong ";

  					Toast.makeText(getBaseContext(),  "Error Going to  Drop: " + errorMsg, 
  							Toast.LENGTH_LONG).show();
  				}
  				// refresh drop block



  			}
  		})

  		.setNegativeButton("Close", new DialogInterface.OnClickListener() {
  			public void onClick(DialogInterface dialog, int id) {
  				dialog.cancel();
  			}
  		});

  		AlertDialog alert = builder.create();
  		alert.show();
	}
}