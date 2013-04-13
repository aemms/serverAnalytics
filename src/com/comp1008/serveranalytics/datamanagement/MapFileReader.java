package com.comp1008.serveranalytics.datamanagement;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.comp1008.serveranalytics.map.MapComputer;
import com.comp1008.serveranalytics.map.MapDoor;

import android.content.Context;
import android.widget.Toast;

public class MapFileReader {
	private FileReader file;
	private ArrayList<MapDoor> doors = new ArrayList<MapDoor>();
	private ArrayList<MapComputer> computers = new ArrayList<MapComputer>();
	private Scanner in;
	Context readerContext;
	
	public MapFileReader(String roomName, Context context) throws IOException
	{
		readerContext = context;
		file = new FileReader(roomName, context);
		InputStream input = file.getInputStream();
		in = new Scanner(input); 
		loadComputers();
	}
	
	public ArrayList<MapComputer> getComputers()
	{
		return computers;
	}
	
	private void loadComputers()
	{
		while (in.hasNext())
		{
			String line = in.nextLine();
			if (line.equals("COMPUTERS"))
			{
				while (in.hasNext())
				{
					line = in.nextLine();
					if(!line.equals("DOORS"))
					{
						parseComputer(line);
					}
					else
					{
						break;
					}
				}
			}
			break;
		}		
	}
	
	private void parseComputer(String line)
	{
		String name = "";
		String x = "";
		String y = "";
		int charCount = 0;
		
		for (charCount = 0; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == ',')
		    {
		    	break;
		    }
		    else
		    {
		    	name = name+Character.toString(c);
		    }
		}
		for (charCount = charCount+1; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == ',')
		    {
		    	break;
		    }
		    else
		    {
		    	x = x+Character.toString(c);
		    }
		}
		for (charCount = charCount+1; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == '\n')
		    {
		    	break;
		    }
		    else
		    {
		    	y = y+Character.toString(c);
		    }
		}
		
		float xF = 0;
		float yF = 0;
		
		try
		{
			xF = Float.parseFloat(x);
		}
		catch (NumberFormatException e)
		{
			CharSequence text = "Error Loading Map File";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(readerContext, text, duration);
			toast.show();
		}
		try
		{
			yF = Float.parseFloat(y);
		}
		catch (NumberFormatException e)
		{
			CharSequence text = "Error Loading Map File";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(readerContext, text, duration);
			toast.show();
		}
		computers.add(new MapComputer(xF,yF));
		
	}
	
	

}
