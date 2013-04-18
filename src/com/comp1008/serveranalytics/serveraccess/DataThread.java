package com.comp1008.serveranalytics.serveraccess;

import java.util.ArrayList;
import java.util.Hashtable;

import com.comp1008.serveranalytics.ui.SettingsActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class DataThread extends Thread {
	
	int pollingTime = 120000;
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
			pollingTime = getPollingTime();
			Log.v("server", "getting data");
			int remainingTime = pollingTime;
			while(remainingTime != 0)
			{
				try{sleep(1);}
				catch(InterruptedException e)
				{
					//do stufff
				}
				remainingTime--;
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
