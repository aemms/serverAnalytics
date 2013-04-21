package com.comp1008.serveranalytics.serveraccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.comp1008.serveranalytics.ui.SettingsActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
/*
 * The main data thread where data is fetched from the server, converted into the format the app can read, and then stored locally
 */
public class DataThread extends Thread {
	
	int pollingTime = 30000; //default 30 second polling time
	public DataThread()
	{
		super();
		start();
	}
	
	public void run()
	{
		super.run();
		while(true)
		{
			Log.v("server", "getting data");
			ServerDataGetter dataGetter = null;
			try {dataGetter = new ServerDataGetter();} 
			catch (NoConnectionException e) 
			{
				//by starting again we can keep looping until a connection is made
				try {sleep(5000);}
				catch (InterruptedException e1)
				{
					run();
				}
				run();
			}
			DataConverter data = null;
			try {data = new DataConverter(dataGetter);} 
			catch (NoConnectionException e2) {
				//by starting again we can keep looping until a connection is made
				try {sleep(5000);}
				catch (InterruptedException e1)
				{
					run();
				}
				run();
			}
			if (data!=null)
			{
				try {data.commitData();} 
				catch (IOException e1) {
					//by starting again we can keep looping until a connection is made
					try {sleep(5000);}
					catch (InterruptedException e)
					{
						run();
					}
					run();
				}
			}
			
			pollingTime = getPollingTime();
			int remainingTime = pollingTime;
			while(remainingTime != 0)
			{
				try{sleep(1);}
				catch(InterruptedException e)
				{
					//by starting again we can keep looping until a connection is made
					try {sleep(5000);}
					catch (InterruptedException e1)
					{
						run();
					}
					run();
				}
				remainingTime--;
				//stop sleeping and poll again if the polling time is changed in settings
				int currentPollingTime = getPollingTime();
				if (currentPollingTime != pollingTime)
				{
					break;
				}
			}
		}

	}
	
	public void start()
	{
		super.start();
	}
	
	//loads the time between server polls that user selected in the settings
	private int getPollingTime()
	{	
		Context context = ApplicationController.getContext();
		SharedPreferences prefs = context.getSharedPreferences("polling", context.MODE_PRIVATE);
		ArrayList<String> keys = SettingsActivity.initializeKeys();
		int time = 120000;
		for(String key : keys)
		{
			boolean keyValue = prefs.getBoolean(key, false);
			if(keyValue)
			{
				time = prefToTime(key);
				break;
			}
		}
		return time;
	}
	
	//converts a polling time preference key into an integer thread sleep time in ms
	private int prefToTime(String key)
	{
		int time = 120000;
		int keyLength = key.length();
		if (key.charAt(keyLength-2) == 'N')
		{
			String timeString = "";
			int position = 0;
			while(key.charAt(position) != 'M')
			{
				timeString = timeString + key.charAt(position);
				position++;
			}
			float t = Float.parseFloat(timeString);
			time = (int)(t*1000*60);
		}
		else if (key.charAt(keyLength-2) == 'R')
		{
			String timeString = "";
			int position = 0;
			while(key.charAt(position) != 'H')
			{
				timeString = timeString + key.charAt(position);
				position++;
			}
			float t = Float.parseFloat(timeString);
			time = (int)(t*1000*60*60);
		}
		
		return time;
	}
}
