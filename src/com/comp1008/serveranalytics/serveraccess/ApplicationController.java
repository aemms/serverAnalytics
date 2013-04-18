package com.comp1008.serveranalytics.serveraccess;

import android.app.Application;
import android.content.Context;

public class ApplicationController extends Application{
	private static ApplicationController app;
	
	public ApplicationController()
	{
		super();
		app = this;
	}
	
	public void onCreate()
	{	
		super.onCreate();
		DataThread pollingThread = new DataThread();
	}
	
	public static Context getContext(){
	        return app.getApplicationContext();
	    }

}
