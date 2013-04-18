package com.comp1008.serveranalytics.ui;

import java.util.ArrayList;
import java.util.Iterator;

import com.comp1008.serveranalytics.R;
import com.comp1008.serveranalytics.R.layout;
import com.comp1008.serveranalytics.R.menu;
import com.comp1008.serveranalytics.datamanagement.DataController;
import com.comp1008.serveranalytics.datamanagement.Printer;
import com.comp1008.serveranalytics.datamanagement.QueueItem;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
/*
 * Activity which shows all information available about a chosen printer
 */
public class PrinterActivity extends Activity {

	private String printerName;
	private Printer printer;
	private DataController data;
	private QueueItem qitem;
	private int count;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_printer);
		data = new DataController(getBaseContext());
		//unpack printer name string from bundle given
    	Bundle printerGiven = getIntent().getExtras();
    	if (printerGiven!=null)
    	{
    		printerName = printerGiven.getString("printer");
    	}
    	//gets the relevant printer object for the name passed to the activity
    	printer = data.getPrinterByName(printerName);
        
    	//fills in text fields for printer info
    	TextView printerNameView = (TextView)findViewById(R.id.printername_content);
    	printerNameView.setText(printer.getName());
    	TextView printerIpView = (TextView)findViewById(R.id.printerip_content);
    	printerIpView.setText(printer.getIpAddress());
    	TextView printerStatusView = (TextView)findViewById(R.id.printerstatus_content);
    	printerStatusView.setText(printer.getStatus());
    	TextView printerTonerView = (TextView)findViewById(R.id.tonerremaining_content);
    	printerTonerView.setText(printer.getTonerRemaining());
    	
    	//Fills a table with the printer queue data
    	count = 0;
    	//Creates table layout
    	TableLayout qtable = (TableLayout) findViewById(R.id.queueTable);
    	
    	//Create row of headings
    	TableRow header = new TableRow(this);
    	header.setId(10);
    	header.setBackgroundColor(Color.DKGRAY);
    	header.setLayoutParams(new LayoutParams(
    	LayoutParams.FILL_PARENT,
    	LayoutParams.WRAP_CONTENT));
    	
    	TextView headerQueue = new TextView(this);
    	headerQueue.setId(20); 
    	headerQueue.setText("Queue");
    	headerQueue.setPadding(7, 7, 7, 7);
    	headerQueue.setTextColor(Color.WHITE);
		header.addView(headerQueue);
    	
		TextView headerName = new TextView(this);
		headerName.setId(20); 
		headerName.setText("Name");
		headerName.setPadding(7, 7, 7, 7);
		headerName.setTextColor(Color.WHITE);
		header.addView(headerName);
		
		TextView headerStatus = new TextView(this);
    	headerStatus.setId(20); 
    	headerStatus.setText("Status");
    	headerStatus.setPadding(7, 7, 7, 7);
    	headerStatus.setTextColor(Color.WHITE);
		header.addView(headerStatus);
		
		TextView headerUser = new TextView(this);
    	headerUser.setId(20); 
    	headerUser.setText("User");
    	headerUser.setPadding(7, 7, 7, 7);
    	headerUser.setTextColor(Color.WHITE);
		header.addView(headerUser);
		
		TextView headerPages = new TextView(this);
    	headerPages.setId(20); 
    	headerPages.setText("Pages");
    	headerPages.setPadding(7, 7, 7, 7);
    	headerPages.setTextColor(Color.WHITE);
		header.addView(headerPages);
		
		TextView headerSize = new TextView(this);
		headerSize.setId(20); 
		headerSize.setText("Size");
		headerSize.setPadding(7, 7, 7, 7);
		headerSize.setTextColor(Color.WHITE);
		header.addView(headerSize);
		
		TextView headerTime = new TextView(this);
		headerTime.setId(20); 
		headerTime.setText("Time");
		headerTime.setPadding(7, 7, 7, 7);
		headerTime.setTextColor(Color.WHITE);
		header.addView(headerTime);
		
		//Populates row of headings
		qtable.addView(header, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
		
		//Iterator goes through queueItems
    	Iterator<QueueItem> itr = printer.getQueueItemsIterator();
    	while (itr.hasNext()) {
    		qitem = itr.next();
    		String queue = qitem.getQnum();
    		String name = qitem.getFileName();
    		String status = qitem.getStatus();
    		String user = qitem.getUser();
    		String pages = qitem.getPages();
    		String size = qitem.getSize();
    		String time = qitem.getTime();
    		
    		//Creates new row to store item in queue
    		TableRow tr = new TableRow(this); 
    		if(count%2!=0) tr.setBackgroundColor(Color.GRAY);
    		tr.setId(100+count);
    		tr.setLayoutParams(new LayoutParams(
    				LayoutParams.FILL_PARENT,
    				LayoutParams.WRAP_CONTENT));
    		
    		
    		//Creates Textviews and stores individual information about print job
    		TextView queueTextView = new TextView(this);
    		queueTextView.setId(200+count); 
    		queueTextView.setText(queue);
    		queueTextView.setPadding(2, 0, 5, 0);
    		if (count%2!=0) queueTextView.setTextColor(Color.WHITE);
    		if (count%2 ==0) queueTextView.setTextColor(Color.BLACK);
    		tr.addView(queueTextView);
    		
    		TextView nameTextView = new TextView(this);
    		nameTextView.setId(200+count); 
    		nameTextView.setText(name);
    		nameTextView.setPadding(2, 0, 5, 0);
    		if (count%2!=0) nameTextView.setTextColor(Color.WHITE);
    		if (count%2 ==0) nameTextView.setTextColor(Color.BLACK);
    		tr.addView(nameTextView);
    	
    		TextView statusTextView = new TextView(this);
    		statusTextView.setId(200+count); 
    		statusTextView.setText(status);
    		statusTextView.setPadding(2, 0, 5, 0);
    		if (count%2!=0) statusTextView.setTextColor(Color.WHITE);
    		if (count%2 ==0) statusTextView.setTextColor(Color.BLACK);
    		tr.addView(statusTextView);
    	
    		TextView userTextView = new TextView(this);
    		userTextView.setId(200+count); 
    		userTextView.setText(user);
    		userTextView.setPadding(2, 0, 5, 0);
    		if (count%2!=0) userTextView.setTextColor(Color.WHITE);
    		if (count%2 ==0) userTextView.setTextColor(Color.BLACK);
    		tr.addView(userTextView);
    		
    		TextView pagesTextView = new TextView(this);
    		pagesTextView.setId(200+count); 
    		pagesTextView.setText(pages);
    		pagesTextView.setPadding(2, 0, 5, 0);
    		if (count%2!=0) pagesTextView.setTextColor(Color.WHITE);
    		if (count%2 ==0) pagesTextView.setTextColor(Color.BLACK);
    		tr.addView(pagesTextView);
    		
    		TextView sizeTextView = new TextView(this);
    		sizeTextView.setId(200+count); 
    		sizeTextView.setText(size);
    		sizeTextView.setPadding(2, 0, 5, 0);
    		if (count%2!=0) sizeTextView.setTextColor(Color.WHITE);
    		if (count%2 ==0) sizeTextView.setTextColor(Color.BLACK);
    		tr.addView(sizeTextView);
    	
    		
    		TextView timeTextView = new TextView(this);
    		timeTextView.setId(200+count); 
    		timeTextView.setText(time);
    		timeTextView.setPadding(2, 0, 5, 0);
    		if (count%2!=0) timeTextView.setTextColor(Color.WHITE);
    		if (count%2 ==0) timeTextView.setTextColor(Color.BLACK);
    		tr.addView(timeTextView);
    	
    		//Appends row to the Queue Table and increments counter
    		qtable.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
	       count++;
	    
    	}
    
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_printer, menu);
		return true;
	}

}
