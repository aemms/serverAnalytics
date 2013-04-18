package com.comp1008.serveranalytics.ui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

import com.comp1008.serveranalytics.R;
/*
 * Activity which shows all information available about a chosen computer
 */
public class ComputerActivity extends Activity {
	
	private String name;
	private String ip;
	private String status;
	private String room;
	private String user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_computer);
		
		//unpacks the computerName string from the bundle given
    	Bundle computerGiven = getIntent().getExtras();
    	if (computerGiven!=null)
    	{
    		name = computerGiven.getString("name");
    		ip = computerGiven.getString("ip");
    		status = computerGiven.getString("status");
    		room = computerGiven.getString("room");
    		user = computerGiven.getString("user");
    	}
    	
    	TextView compNameView = (TextView)findViewById(R.id.computername_content);
    	compNameView.setText(name);
    	TextView compIpView = (TextView)findViewById(R.id.computerip_content);
    	compIpView.setText(ip);
    	TextView compRoomView = (TextView)findViewById(R.id.computerlocation_content);
    	compRoomView.setText(room);
    	TextView compStatusView = (TextView)findViewById(R.id.computerstatus_content);
    	compStatusView.setText(status);
    	TextView compLoginView = (TextView)findViewById(R.id.computerlogin_content);
    	compLoginView.setText(user);
    	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_computer, menu);
		return true;
		
	}

}
