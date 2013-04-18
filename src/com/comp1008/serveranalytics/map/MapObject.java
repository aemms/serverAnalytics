package com.comp1008.serveranalytics.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/*
 * Holds information about a generic object on the map
 */
public class MapObject implements MapObjectIF {
	
	private boolean moveable;
	private float x;
	private float y;
	private Bitmap image;
	private Paint paint;
	public MapObject(float x, float y)
	{
		this.x = x;
		this.y = y;
		this.paint = new Paint();
		
	}
	public MapObject(float x, float y, Bitmap image)
	{
		this.x = x;
		this.y = y;
		this.image = image;
		this.paint = new Paint();
		
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(image, x,y, paint);
		
	}

	@Override
	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
		
	}

	@Override
	public void setX(float x) {
		this.x = x;
		
	}

	@Override
	public void setY(float y) {
		this.y = y;
		
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public boolean getMoveable() {
		return moveable;
	}
	public void setImage(Bitmap image)
	{
		this.image = image;
	}
	public Bitmap getImage()
	{
		return image;
	}
	
	

}
