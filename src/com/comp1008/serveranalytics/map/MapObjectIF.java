package com.comp1008.serveranalytics.map;

import android.graphics.Canvas;
/*
 * Interface that map objects must adhere to
 */
public interface MapObjectIF {
	void draw(Canvas canvas);
	void setMoveable(boolean moveable);
	void setX(float x);
	void setY(float y);
	float getY();
	float getX();
	boolean getMoveable();

}
