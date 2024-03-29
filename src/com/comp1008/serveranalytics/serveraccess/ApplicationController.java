package com.comp1008.serveranalytics.serveraccess;

import android.app.Application;
import android.content.Context;
/*
 * This is created when the application launches
 * it starts the server polling thread
 */
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
	/*
	 * allows pollingThread to access the context of the application 
	 */
	public static Context getContext(){
	        return app.getApplicationContext();
	    }

}
