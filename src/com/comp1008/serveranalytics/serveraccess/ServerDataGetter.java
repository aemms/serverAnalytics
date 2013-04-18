package com.comp1008.serveranalytics.serveraccess;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import android.content.Context;
import android.widget.Toast;

public class ServerDataGetter {
	private String urlString = "http://o.scarrobinson.com/alldata.txt";
	InputStream in;
	
	public ServerDataGetter()
	{
		in = openHttpConnection();
	}
	/*
	 * Based on code from Beginning Android Application Development by Wei-Meng Lee pg285
	 */
	public InputStream openHttpConnection()
	{
		InputStream in = null;
		int response = -1;
		URL url = null;
		try {url = new URL(urlString);} 
		catch (MalformedURLException e) {
			Context context = ApplicationController.getContext();
			CharSequence text = "Error connection to server";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		
		URLConnection conn = null;
        try {conn = url.openConnection();}
        catch (IOException e)
        {
			Context context = ApplicationController.getContext();
			CharSequence text = "Error connection to server";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
        }
        
        try{in = conn.getInputStream();}
        catch(IOException e)
        {
			Context context = ApplicationController.getContext();
			CharSequence text = "Error connection to server";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
        }
        
        return in;   
	}
	
	public String getServerText()
	{
		Scanner scan = new Scanner(in);
		String data = "";
		while (scan.hasNext())
		{
			data = data + scan.nextLine() + "\n";
		}
		return data;
	}
}
