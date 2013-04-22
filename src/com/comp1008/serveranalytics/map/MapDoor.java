package com.comp1008.serveranalytics.map;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;


public class MapDoor extends MapObject{
	private String orientation;
	
	public MapDoor(float x, float y, String orientation) {
		super(x, y);
		this.orientation = orientation;
	}

	public String getOrientation() {
		return orientation;
	}
	
	public void draw (Canvas canvas)
	{
		Paint paint = new Paint();
		paint.setColor(Color.parseColor("#1BB2E0"));
		paint.setStyle(Style.FILL);
		if (orientation.equals("horizontal"))
		{
			canvas.drawRect(super.getX(),super.getY(),super.getX()+70,super.getY()+10,paint);
		}
		else
		{
			canvas.drawRect(super.getX(),super.getY(),super.getX()+10,super.getY()+70,paint);
		}
	}

}
