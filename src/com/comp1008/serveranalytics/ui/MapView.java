package com.comp1008.serveranalytics.ui;

import java.util.Iterator;

import com.comp1008.serveranalytics.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.comp1008.serveranalytics.map.Map;
import com.comp1008.serveranalytics.map.MapComputer;


/*
 * This class is the canvas where the map is drawn
 * It can be embedded in regular activities
 */

public class MapView extends View {
	private int l;
	private int r;
	private int t;
	private int b;
	private Paint genericPaint = new Paint();
    private int canvasWidth;
    private int canvasHeight;
    
    private float mPosX;
    private float mPosY;
    
    private float mLastTouchX;
    private float mLastTouchY;
    
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.0f;
    private float mScaleCenterX;
    private float mScaleCenterY;
    private Map map;
    private Rect clipBounds;
	
    //overload all the possible constructors
	public MapView(Context context) {
		super(context);
		LabMapActivity mapActivity = (LabMapActivity)this.getContext();
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
		map = new Map(context, mapActivity.getLabName());
	}
	
	public MapView(Context context, AttributeSet attribs) {
		super(context, attribs);
		LabMapActivity mapActivity = (LabMapActivity)this.getContext();
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
		map = new Map(context, mapActivity.getLabName());
	}
	
    public MapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
		LabMapActivity mapActivity = (LabMapActivity)this.getContext();
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
		map = new Map(context, mapActivity.getLabName());
    }
	
	@Override
	protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        l = this.getLeft();
        r = this.getRight();
        t = this.getTop();
        b = this.getBottom();

        
        canvas.save();
        //keep the panning in certain bounds
        //Log.v("mPos", "mPosY = " + mPosY + " || " + "mPosX = " + mPosX + " || " + "mScaleFactor = " + mScaleFactor);
        if (mPosX > 200*mScaleFactor*mScaleFactor) { mPosX = 200*mScaleFactor*mScaleFactor; }
        if (mPosX < -200*mScaleFactor*mScaleFactor) { mPosX = -200*mScaleFactor*mScaleFactor; }
        if (mPosY > 200*mScaleFactor*mScaleFactor) { mPosY = 200*mScaleFactor*mScaleFactor; }
        if (mPosY < -200*mScaleFactor*mScaleFactor) { mPosY = -200*mScaleFactor*mScaleFactor; }
        canvas.translate(mPosX, mPosY);
        canvas.scale(mScaleFactor, mScaleFactor, mScaleCenterX, mScaleCenterY);
        genericPaint.setColor(Color.BLACK);
        canvas.drawRect(l-10000, t-10000,r+10000,b+10000, genericPaint);
        
        genericPaint.setColor(Color.RED);
        canvas.drawRect(l, t,r,b, genericPaint);
        clipBounds = canvas.getClipBounds();    	
        map.draw(canvas);
        
        canvas.restore();
    }
	
	//based on code from http://stackoverflow.com/questions/8122177/canvas-how-i-use-canvas-of-view-extended-class-into-activity-class
    @Override
	protected void onMeasure(int widthMS, int heightMS) {
        canvasWidth = View.MeasureSpec.getSize(widthMS);
        canvasHeight = View.MeasureSpec.getSize(heightMS);
        setMeasuredDimension(canvasWidth, canvasHeight);
    }
    
    /*code to recognise touch events that will allow panning and zooming of map
    *mostly code from http://android-developers.blogspot.co.uk/2010/06/making-sense-of-multitouch.html
    *made some adjustments to center zoom on focus of zoom gesture rather than focusing zoom on point (0,0)
    */
    
    private static final int INVALID_POINTER_ID = -1;

    // The 'active pointer' is the one currently moving our object.
    private int mActivePointerId = INVALID_POINTER_ID;
    public boolean onTouchEvent(MotionEvent ev) {
    	// Let the ScaleGestureDetector inspect all events.
    	mScaleDetector.onTouchEvent(ev);
    
    	final int action = ev.getAction();
    	switch (action & MotionEvent.ACTION_MASK) {
    		case MotionEvent.ACTION_DOWN: {
    			
    			final float x = ev.getX();
        		final float y = ev.getY();
        		Log.v("touch", "x = " + x + " || " + "y = " + y);
        
        		mLastTouchX = x;
        		mLastTouchY = y;
        		mActivePointerId = ev.getPointerId(0);
        		
        		//check if a computer has been tapped
        		Iterator<MapComputer> computers = map.getMapComputers();
        		while (computers.hasNext())
        		{
        			MapComputer computer = computers.next();
        			float compX = computer.getX();
        			float compY = computer.getY();
        			float width = computer.getImage().getWidth();
        			
        			float newX = x / mScaleFactor + clipBounds.left;
        			float newY = y / mScaleFactor + clipBounds.top;
        			
        			Log.v("touch", "compX = " + compX + " || compY = " + compY + " || width = " + width);
        			if(newX > compX && newX < compX+width && newY > compY && newY <compY+width)
        			{
        				Log.v("touch", "computer touched");
        				LabMapActivity activity = (LabMapActivity) this.getContext();
        				activity.startComputerActivity(computer.getAssignedComputer());
        			}
        		}
        		
        		
        		break;
    		}
        
    		case MotionEvent.ACTION_MOVE: {
    			final int pointerIndex = ev.findPointerIndex(mActivePointerId);
    			final float x = ev.getX(pointerIndex);
    			final float y = ev.getY(pointerIndex);

    			// Only move if the ScaleGestureDetector isn't processing a gesture.
    			if (!mScaleDetector.isInProgress())
    			{
    					final float dx = x - mLastTouchX;
    					final float dy = y - mLastTouchY;

    					mPosX += dx;
    					mPosY += dy;

    					invalidate();
    			}

    			mLastTouchX = x;
    			mLastTouchY = y;

    			break;
    		}
        
    		case MotionEvent.ACTION_UP: {
    			mActivePointerId = INVALID_POINTER_ID;
    			break;
    		}
        
    		case MotionEvent.ACTION_CANCEL: {
    			mActivePointerId = INVALID_POINTER_ID;
    			break;
    		}	
    
    		case MotionEvent.ACTION_POINTER_UP: {
    			final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) 
    					>> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                final int pointerId = ev.getPointerId(pointerIndex);
                if (pointerId == mActivePointerId) {
                	// This was our active pointer going up. Choose a new
                	// active pointer and adjust accordingly.
                	final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                	mLastTouchX = ev.getX(newPointerIndex);
                	mLastTouchY = ev.getY(newPointerIndex);
                	mActivePointerId = ev.getPointerId(newPointerIndex);
                }		
                break;
    		}
    	}
    
    	return true;
    }

    //checks current touch state for scale gesture
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    @Override
    	public boolean onScale(ScaleGestureDetector detector) {
    		mScaleFactor *= detector.getScaleFactor();
        
    		// Don't let the object get too small or too large.
    		mScaleFactor = Math.max(0.5f, Math.min(mScaleFactor, 5.0f));
    		//get the midpoint between the two fingers zooming (to control zoom center)
    		mScaleCenterX = detector.getFocusX();
    		mScaleCenterY = detector.getFocusY();
    		invalidate();
    		return true;
    	}
    }

}


