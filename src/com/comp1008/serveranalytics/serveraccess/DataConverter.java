package com.comp1008.serveranalytics.serveraccess;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;
/*
 * this class handles conversion and saving of data from the server
 */
public class DataConverter {
	String data;
	ServerDataGetter dataGetter;
	
	//gets the data string from the server and calls convertData to convert it into correct format for the app
	public DataConverter(ServerDataGetter dataGetter) throws NoConnectionException
	{
		this.dataGetter = dataGetter;
		data = convertData(dataGetter.getServerText());
	}
	
	//saves the data in the local alldata file
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
	
	//this function converts server data into format app can understand
	private String convertData(String serverData)
	{
		//No conversions needed for dummy data so just return data as is
		String convertedData = serverData;
		return convertedData;
	}

}
