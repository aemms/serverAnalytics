package com.comp1008.serveranalytics.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.widget.Toast;

import com.comp1008.serveranalytics.R;
import com.comp1008.serveranalytics.datamanagement.MapFileReader;
/*
 * Objects of this class hold all the objects in a map, the map can then be drawn to
 * a canvas using the draw(Canvas canvas) function.
 * Will later be able to load in objects on the map from a file and save objects on map
 * to a file.
 */
public class Map {
	
	private ArrayList<MapDoor> doors = new ArrayList<MapDoor>();
	private ArrayList<MapComputer> computers = new ArrayList<MapComputer>();
	private MapFileReader mapFile;
	
	
	public Map(Context context, String labName)
	{
		String mapFileName = getLabFileName(labName);
		Bitmap computerImage = getResizedBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.computer_pic),70,70);
		try
		{
			mapFile = new MapFileReader(mapFileName, context); //get lab map for given lab (using lab406 for dummy), chosen lab decided by given lab clicked
		}
		catch(IOException e)
		{
			CharSequence text = "Couldn't load map file";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		
		if (mapFile != null)
		{	
			computers = mapFile.getComputers();
			for (MapComputer computer: computers)
			{
				computer.setImage(computerImage);
			}
		}
		
		
	}
	
	private String getLabFileName(String labName) {
		String labFileName = null;
		if (labName.equals("Room 4.06"))
		{
			labFileName = "lab406.txt";
		}
		else if (labName.equals("Room 1.21"))
		{
			labFileName = "lab121.txt";
		}
		else if (labName.equals("Room 1.05"))
		{
			labFileName = "lab105.txt";
		}
		
		return labFileName;
	}

	public void draw(Canvas canvas)
	{
		for(MapComputer computer : computers)
		{
			computer.draw(canvas);
		}
	}
	
	//getResizedBitmap() taken from http://stackoverflow.com/questions/8327846/how-to-resize-a-bitmap-eficiently-and-with-out-losing-quality-in-android
	private Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
	{
		//get current bitmap width and height
	    int width = bm.getWidth();
	    int height = bm.getHeight();
	    //calculate the scale factor for width and height
	    float scaleWidth = ((float) newWidth) / width;
	    float scaleHeight = ((float) newHeight) / height;
	    // create a matrix for the manipulation
	    Matrix matrix = new Matrix();
	    // resize the bit map
	    matrix.postScale(scaleWidth, scaleHeight);
	    // recreate the new Bitmap with the scaling matrix
	    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
	    return resizedBitmap;
	}
	
	public Iterator<MapComputer> getMapComputers()
	{
		return computers.iterator();
	}

}
