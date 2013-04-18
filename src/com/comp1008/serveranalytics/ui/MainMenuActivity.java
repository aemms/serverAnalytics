package com.comp1008.serveranalytics.ui;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.comp1008.serveranalytics.R;
import com.comp1008.serveranalytics.datamanagement.Computer;
import com.comp1008.serveranalytics.datamanagement.DataController;

/* MainMenuActivity will show the user an option to choose multiple tasks..
 * These include choosing to view Lab rooms, printers or the settings
 *  */

public class MainMenuActivity extends Activity implements View.OnClickListener {
    
    Button labsButton, printersButton, settingsButton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
	
    	initialise();
	

    }
    
    public void initialise() {
    	// Creates our views, in this case 3 buttons
    	labsButton = (Button) findViewById(R.id.labsButton);
    	printersButton = (Button) findViewById(R.id.printersButton);
    	settingsButton = (Button) findViewById(R.id.settingsButton);
    	// Creates OnClickListeners for our Buttons
    	labsButton.setOnClickListener(this);	
    	printersButton.setOnClickListener(this);
    	settingsButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
    	// Create switch for MainMenu buttons
    	switch(v.getId()){
    		// Opens LabListActivity when pressed
    		case(R.id.labsButton):
    			intent = new Intent(MainMenuActivity.this, LabListActivity.class);
	    		MainMenuActivity.this.startActivity(intent);
	    		break;
	
	    	// Opens the PrinterListActivity when pressed
    		case(R.id.printersButton):
    			intent = new Intent(MainMenuActivity.this, PrinterListActivity.class);
	    		MainMenuActivity.this.startActivity(intent);
	    		break;
	
	    	// Opens the SettingsActivity when pressed
    		case(R.id.settingsButton):
    			intent = new Intent(MainMenuActivity.this, SettingsActivity.class);
				MainMenuActivity.this.startActivity(intent);
				break;
	
    		default:
    			break;
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    

}
