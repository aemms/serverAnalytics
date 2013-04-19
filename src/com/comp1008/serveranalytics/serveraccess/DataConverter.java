package com.comp1008.serveranalytics.serveraccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Scanner;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

public class DataConverter {
	String data;
	ServerDataGetter dataGetter;
	
	public DataConverter(ServerDataGetter dataGetter)
	{
		this.dataGetter = dataGetter;
		try { data = dataGetter.getServerText();} 
		catch (NoConnectionException e) {
			
		}
	}
	
	public void commitData()
	{
		
		
	}
	
	private String convertData(String serverData)
	{
		//No conversions needed for dummy data
		String convertedData = serverData;
		return convertedData;
	}

}
