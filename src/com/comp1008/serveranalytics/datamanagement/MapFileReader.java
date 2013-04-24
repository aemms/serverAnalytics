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
	private float bgHf = 0;
	private float bgWf = 0;
	private float bgXf = 0;
	private float bgYf = 0;
	
	public float getBgHf() {
		return bgHf;
	}

	public float getBgWf() {
		return bgWf;
	}

	public float getBgXf() {
		return bgXf;
	}

	public float getBgYf() {
		return bgYf;
	}

	public MapFileReader(String roomName, Context context) throws IOException
	{
		readerContext = context;
		file = new FileReader(roomName, context);
		InputStream input = file.getInputStream();
		in = new Scanner(input); 
		loadMap();
	}
	
	public ArrayList<MapComputer> getComputers()
	{
		return computers;
	}
	
	public ArrayList<MapDoor> getDoors()
	{
		return doors;
	}
	
	private void loadMap()
	{
		while (in.hasNext())
		{
			String line = in.nextLine();
			if (line.equals("COMPUTERS"))
			{
				while (in.hasNext())
				{
					line = in.nextLine();
					if(!line.equals("BACKGROUND"))
					{
						parseComputer(line);
					}
					else
					{
						line = in.nextLine();
						parseBackground(line);
						break;
					}
				}
				in.nextLine();
				while (in.hasNext())
				{
					line = in.nextLine();
					if (!line.equals("END"))
					{
						parseDoor(line);
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
	
	private void parseDoor(String line) 
	{
		String x = Parser.parseItemFromLine(',', ',', line, 0);
		String y = Parser.parseItemFromLine(',', ',', line, 1);
		String o = Parser.parseItemFromLine(',', '\n', line, 2);

		float doorX = 0;
		float doorY = 0;
		try
		{
			doorX = Float.parseFloat(x);
			doorY = Float.parseFloat(y);
		}
		catch (NumberFormatException e)
		{
			CharSequence text = "Error Loading Map File";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(readerContext, text, duration);
			toast.show();
		}
		
		MapDoor door = new MapDoor(doorX,doorY,o);
		doors.add(door);
	}

	private void parseBackground(String line) 
	{
		String bgX = Parser.parseItemFromLine(',', ',', line, 0);
		String bgY = Parser.parseItemFromLine(',', ',', line, 1);
		String bgWidth = Parser.parseItemFromLine(',', ',', line, 2);
		String bgHeight = Parser.parseItemFromLine(',', '\n', line, 3);

		try
		{
			bgHf = Float.parseFloat(bgHeight);
			bgWf = Float.parseFloat(bgWidth);
			bgXf = Float.parseFloat(bgX);
			bgYf = Float.parseFloat(bgY);
		}
		catch (NumberFormatException e)
		{
			CharSequence text = "Error Loading Map File";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(readerContext, text, duration);
			toast.show();
		}
		
		
	}

	private void parseComputer(String line)
	{
		String x = Parser.parseItemFromLine(',', ',', line, 0);
		String y = Parser.parseItemFromLine(',', ',', line, 1);
		String computerName = Parser.parseItemFromLine(',', '\n', line, 2);

		float xF = 0;
		float yF = 0;
		
		try
		{
			xF = Float.parseFloat(x);
			yF = Float.parseFloat(y);
		}
		catch (NumberFormatException e)
		{
			CharSequence text = "Error Loading Map File";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(readerContext, text, duration);
			toast.show();
		}
		DataController data = new DataController(readerContext);
		Computer computer = data.getComputerByName(computerName);
		//if computer not found then the computer that the MapComputer is initialised with is null
		//this will mean no info is displayed for that mapcomputer on the map but the map will load fine
		computers.add(new MapComputer(xF,yF,computer));
		
	}
	
	

}
