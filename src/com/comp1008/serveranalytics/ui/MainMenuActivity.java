package com.comp1008.serveranalytics.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.comp1008.serveranalytics.R;

/* MainMenuActivity will show the user an option to choose multiple tasks..
 * These include choosing to view Lab rooms, printers or the settings
 *  */

public class MainMenuActivity extends Activity implements View.OnClickListener {
    
    ImageButton labsIconButton, printerIconButton, settingsIconButton;
    TextView labsTV, printerTV, settingsTV, creditsTV;
    AlertDialog dialog;
    AlertDialog.Builder builder;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
	
    	// Start initialising our views
    	initialise();
    }
    
    public void initialise() {
    	// Creates our views, 3 ImageButtons and the 3 corresponding TextViews, then the credits TextView
    	labsIconButton = (ImageButton) findViewById(R.id.labsIconButton);
    	printerIconButton = (ImageButton) findViewById(R.id.printerIconButton);
    	settingsIconButton = (ImageButton) findViewById(R.id.settingsIconButton);
    	labsTV = (TextView) findViewById(R.id.labsTV);
    	printerTV = (TextView) findViewById(R.id.printerTV);
    	settingsTV = (TextView) findViewById(R.id.settingsTV);
    	creditsTV = (TextView) findViewById(R.id.creditsTV);
    	
    	// Creates our AlertBuilder and sets all information 
    	builder = new AlertDialog.Builder(this);
    	builder.setTitle("Credits");
    	builder.setMessage("Oscar Robinson\nComputer Science UCL \n\nSaagar Hemrajani\nComputer Science UCL\n\nAlex Emms\nComputer Science UCL");
    	builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Clicking ok closes AlertBox
            }
        });
    	// Creates our AlertDialog from our builder
    	dialog = builder.create();
    	
    	// Creates OnClickListeners for our ImageButtons and our TextViews
    	labsIconButton.setOnClickListener(this);
    	labsTV.setOnClickListener(this);
    	printerIconButton.setOnClickListener(this);
    	printerTV.setOnClickListener(this);
    	settingsIconButton.setOnClickListener(this);
    	settingsTV.setOnClickListener(this);
    	creditsTV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    	// Create switch for MainMenu ImageButtons and TextViews
    	switch(v.getId()){
    		// Opens LabListActivity when either ImageButton or TextView pressed
    		case(R.id.labsIconButton):
			intent = new Intent(MainMenuActivity.this, LabListActivity.class);
	    		MainMenuActivity.this.startActivity(intent);
	    		break;
    		case(R.id.labsTV):
    		    	intent = new Intent(MainMenuActivity.this, LabListActivity.class);
    			MainMenuActivity.this.startActivity(intent);
    			break;
    			
	    	// Opens the PrinterListActivity when either ImageButton or TextView pressed
    		case(R.id.printerIconButton):
    			intent = new Intent(MainMenuActivity.this, PrinterListActivity.class);
	    		MainMenuActivity.this.startActivity(intent);
	    		break;
    		case(R.id.printerTV):
		    	intent = new Intent(MainMenuActivity.this, PrinterListActivity.class);
			MainMenuActivity.this.startActivity(intent);
			break;
	
	    	// Opens the SettingsActivity when either ImageButton or TextView pressed
    		case(R.id.settingsIconButton):
    			intent = new Intent(MainMenuActivity.this, SettingsActivity.class);
			MainMenuActivity.this.startActivity(intent);
			break;
    		case(R.id.settingsTV):
		    	intent = new Intent(MainMenuActivity.this, SettingsActivity.class);
			MainMenuActivity.this.startActivity(intent);
			break;
		
		// Opens a alertbox showing credits
    		case(R.id.creditsTV):
    			// Shows our AlertBox
        		dialog.show();
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
