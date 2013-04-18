package com.comp1008.serveranalytics.datamanagement;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
/*
 * Generic class for handling the opening of a filestream
 * Requires android context to open the file
 */


public class FileReader {
	
	private InputStream fileStream;
	private AssetManager assetManager;
	
	public FileReader(String fileName, Context context) throws IOException
	{
		assetManager = context.getAssets();
		initialiseInputStream(fileName);		
	}
	
	private void initialiseInputStream(String fileName) throws IOException
	{
		this.fileStream = assetManager.open(fileName);
	}
	
	public InputStream getInputStream()
	{
		return fileStream;
	}

	
}
