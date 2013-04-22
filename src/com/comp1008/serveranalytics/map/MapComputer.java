package com.comp1008.serveranalytics.map;

import com.comp1008.serveranalytics.datamanagement.Computer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MapComputer extends MapObject{
	
	Computer assignedComputer;
	Paint textPaint = new Paint();
	public MapComputer(float x, float y) {
		super(x, y);
		
		textPaint.setColor(Color.WHITE);
		textPaint.setAntiAlias(true);
		
	}
	public MapComputer(float x, float y, Bitmap image) {
		super(x, y, image);
		
		textPaint.setColor(Color.WHITE);
		textPaint.setAntiAlias(true);
		
	}
	public MapComputer(float x, float y, Computer assignedComputer) {
		super(x, y);
		this.assignedComputer = assignedComputer;
		textPaint.setColor(Color.WHITE);
		textPaint.setAntiAlias(true);
		
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);		
		float height = super.getImage().getHeight();
		
		if (assignedComputer != null)
		{
			canvas.drawText(assignedComputer.getName(), super.getX(), super.getY()+height+10, textPaint);
			canvas.drawText(assignedComputer.getStatus(), super.getX(), super.getY()+height+25, textPaint);
		}
		else
		{
			canvas.drawText("NO MACHINE", super.getX(), super.getY()+height+10, textPaint);
		}
		
	}
	
	public Computer getAssignedComputer()
	{
		return assignedComputer;
	}

}
