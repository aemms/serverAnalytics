package com.comp1008.serveranalytics.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.comp1008.serveranalytics.R;

/* SettingsActivity will load a settings page that allows the user to chose
 * at what frequency they would like to poll the server for information about printers and computers */


public class SettingsActivity extends Activity implements OnCheckedChangeListener{

	TextView settingsTitle, pollingTitle, test;
	RadioGroup freqSelection;
	String setData;
	String selection;
	ArrayList<String> keys = new ArrayList<String>();
	
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		initialize();
		//Loads view and initializes buttons and radioGroup
	}
	
	@Override
	protected void onStop()
	{
		super.onStop();
		setPrefTrue(selection);
		
	}
 
	private void initialize() {
		keys = initializeKeys();
		pollingTitle = (TextView) findViewById(R.id.pollingTitle);
		freqSelection = (RadioGroup) findViewById(R.id.rgSettings);
		freqSelection.setOnCheckedChangeListener(this);
		//Creates on click and on checkedChangeListeners for the button and radiogroup
		loadPrefs();
	}
	
	public static ArrayList<String> initializeKeys()
	{
		ArrayList<String> keyList = new ArrayList<String>();
		keyList.add("0.02MINS"); keyList.add("5MINS"); keyList.add("1HOURS"); keyList.add("5HOURS"); keyList.add("10HOURS");
		return keyList;
	}
	
	private void loadPrefs(){
	    SharedPreferences sp = getSharedPreferences("polling", MODE_PRIVATE);
	    boolean radio0 = sp.getBoolean(keys.get(0), false); //gets the value of the boolean from the saved prefs, the 'false' is the default value
	    boolean radio1 = sp.getBoolean(keys.get(1), false);
	    boolean radio2 = sp.getBoolean(keys.get(2), false);
	    boolean radio3 = sp.getBoolean(keys.get(3), false);
	    boolean radio4 = sp.getBoolean(keys.get(4), false);
	    if(freqSelection.getCheckedRadioButtonId() == -1)
	    { 
	    	if (radio0)// if radio0 was set true in savePrefs, check the radiobutton this time
	    	{ 
	    		freqSelection.check(R.id.radio0); // this is what actually 'checks' the radiobutton (by check i mean select)
	    	} 
	    	else if (radio1)
	    	{
	    		freqSelection.check(R.id.radio1);
	    	} 
	    	else if (radio2)
	    	{
	    		freqSelection.check(R.id.radio2);
	    	} 
	    	else if (radio3)
	    	{
	    		freqSelection.check(R.id.radio3);	    
	    	} 
	    	else if (radio4)
	    	{
	    		freqSelection.check(R.id.radio4);
	    	}
	    }
	}
	
	private void setPrefTrue(String selection)
	{
		for (String key : keys)
		{
			if (selection.equals(key))
			{
				savePref(key,true);
			}
			else
			{
				savePref(key,false);
			}
		}
	}
	
	private void savePref(String key, boolean value){
		SharedPreferences sp = getSharedPreferences("polling", MODE_PRIVATE);
		Editor edit = sp.edit(); // Allow 'edit' mode
	    edit.putBoolean(key, value); //puts the value of a radio button to true (is checked)
	    edit.commit(); //commits changes
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		switch(arg1){
		case R.id.radio0:
			selection = keys.get(0);
			break;
		case R.id.radio1:
			selection = keys.get(1);
			break;
			
		case R.id.radio2:
			selection = keys.get(2);
			break;
			
		case R.id.radio3: 
			selection = keys.get(3);
			break;	
		
		case R.id.radio4:
			selection = keys.get(4);
			break;
		}
	}

}

