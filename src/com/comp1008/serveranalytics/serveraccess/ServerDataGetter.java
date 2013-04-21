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
	
	public ServerDataGetter() throws NoConnectionException
	{
		in = openHttpConnection();
	}
	/*
	 * Based on code from Beginning Android Application Development by Wei-Meng Lee pg285
	 */
	public InputStream openHttpConnection() throws NoConnectionException
	{
		InputStream in = null;
		int response = -1;
		URL url = null;
		try {url = new URL(urlString);} 
		catch (MalformedURLException e) {
			throw new NoConnectionException();
		}
		
		URLConnection conn = null;
        try {conn = url.openConnection();}
        catch (IOException e)
        {
        	throw new NoConnectionException();
        }
        
        try{in = conn.getInputStream();}
        catch(IOException e)
        {
        	throw new NoConnectionException();
        }
        
        return in;   
	}
	
	public String getServerText() throws NoConnectionException
	{
		if (in==null) {throw new NoConnectionException();}
		Scanner scan = new Scanner(in);
		String data = "";
		while (scan.hasNext())
		{
			data = data + scan.nextLine() + "\n";
		}
		return data;
	}
	
}
