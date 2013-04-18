package com.comp1008.serveranalytics.ui;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.comp1008.serveranalytics.R;
import com.comp1008.serveranalytics.datamanagement.Computer;
import com.comp1008.serveranalytics.datamanagement.DataController;
/*
 * Tabbed activity which contains the mapview on one tab and the list of machines in the lab
 * on the other tab.
 */
public class LabMapActivity extends Activity implements AdapterView.OnItemClickListener {
	
	private String labName;
	ArrayList<Computer> computers;
	String[] computerNames;
	DataController data;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	Bundle labNameGiven = getIntent().getExtras();
    	labName = labNameGiven.getString("lab");  	
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_map);
        
        //get the list of computers for this lab
        data = new DataController(getBaseContext());
        computers = getComputersInLab(data, labName);
        computerNames = getComputerNameArray(computers);
        
        //initialise the tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        
        TabSpec tabSpec;
        tabSpec = tabHost.newTabSpec("Lab Map");
        tabSpec.setContent(R.id.mapview);
        tabSpec.setIndicator("Lab Map");
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec("List of Computers");
        tabSpec.setContent(R.id.listview);
        tabSpec.setIndicator("List Of Computers");
        tabHost.addTab(tabSpec);
        
        // Creates an ArrayAdapter for the String array computers.
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(LabMapActivity.this, R.layout.lab_list_custom, computerNames);
    	// This ListView is in the XML activity_lab_map
    	ListView listOfComputers = (ListView) findViewById(R.id.listOfComputers);
    	listOfComputers.setAdapter(adapter);

    	listOfComputers.setOnItemClickListener(this);
        

    }

	private String[] getComputerNameArray(ArrayList<Computer> computers) {
		ArrayList<String> names = new ArrayList<String>();
		for (Computer computer : computers)
		{
			names.add(computer.getName());
		}
		String []namesArray = new String[names.size()];
		names.toArray(namesArray);
		return namesArray;
	}

	private ArrayList<Computer> getComputersInLab(DataController data, String lab) {
		Iterator<Computer> computerIterator = data.getComputerIterator();
		ArrayList<Computer> labComputerList = new ArrayList<Computer>();
		while (computerIterator.hasNext())
		{
			Computer computer = computerIterator.next();
			if (computer.getLabRoom().equals(lab))
			{
				labComputerList.add(computer);
			}
		}
		
		return labComputerList;
	}

	public void onItemClick(AdapterView parent, View v, int position, long id) {

		/* This Method should get list of computers in lab on button click, 
		 * Passes string of lab name corresponding to button pressed to labMapActivity  */
		String computerName = computerNames[position];
		Log.v("labClick", "Clicked computer " + computerName);
		Computer computer = data.getComputerByName(computerName);
		startComputerActivity(computer);

		 
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_lab_map, menu);
        return true;
    }
    
    public String getLabName()
    {
    	return labName;
    }
    
    public void startComputerActivity(Computer computer)
    {
		Intent intent = new Intent(LabMapActivity.this, ComputerActivity.class);
		Bundle computerBundle = new Bundle();
		computerBundle.putString("ip", computer.getIpAddress());
		computerBundle.putString("user", computer.getCurrentLogin());
		computerBundle.putString("name", computer.getName());
		computerBundle.putString("room", computer.getLabRoom());
		computerBundle.putString("status", computer.getStatus());
		intent.putExtras(computerBundle);
		startActivity(intent);
    }
    
}
