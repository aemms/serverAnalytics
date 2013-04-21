package com.comp1008.serveranalytics.serveraccess;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class DataConverter {
	String data;
	ServerDataGetter dataGetter;
	
	public DataConverter(ServerDataGetter dataGetter)
	{
		this.dataGetter = dataGetter;
		try { data = convertData(dataGetter.getServerText());} 
		catch (NoConnectionException e) {
			
		}
	}
	
	public void commitData() throws IOException
	{	
		Context context = ApplicationController.getContext();
		String fileName = "alldata";
		context.deleteFile(fileName);
		FileOutputStream file = context.openFileOutput(fileName, Context.MODE_PRIVATE);
		BufferedOutputStream buffFile = new BufferedOutputStream(file);
		buffFile.write(data.getBytes());
		buffFile.close();
		file.close();
		Log.v("data", "successfully downloaded and loaded data");
	}
	
	private String convertData(String serverData)
	{
		//No conversions needed for dummy data
		String convertedData = serverData;
		return convertedData;
	}

}
