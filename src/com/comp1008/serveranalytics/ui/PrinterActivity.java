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
    	
    	//Fills the 3 rows of the table with the printer queue data
    	Iterator<QueueItem> it = printer.getQueueItemsIterator();
    	while (it.hasNext()) {
    		qitem = it.next();	
    		TextView qnum1view = (TextView)findViewById(R.id.qnum1);
        	qnum1view.setText(qitem.getQnum());
        	
        	TextView name1view = (TextView)findViewById(R.id.name1);
        	name1view.setText(qitem.getFileName());
        	
        	TextView status1view = (TextView)findViewById(R.id.status1);
        	status1view.setText(qitem.getStatus());
        	
        	TextView user1view = (TextView)findViewById(R.id.user1);
        	user1view.setText(qitem.getStatus());
        	
        	TextView pages1view = (TextView)findViewById(R.id.pages1);
        	pages1view.setText(qitem.getPages());
        	
        	TextView size1view = (TextView)findViewById(R.id.size1);
        	size1view.setText(qitem.getSize());
        	
        	TextView time1view = (TextView)findViewById(R.id.time1);
        	time1view.setText(qitem.getTime());
        	
        	qitem = it.next();	
        	TextView qnum2view = (TextView)findViewById(R.id.qnum2);
        	qnum2view.setText(qitem.getQnum());
        	
        	TextView name2view = (TextView)findViewById(R.id.name2);
        	name2view.setText(qitem.getFileName());
        	
        	TextView status2view = (TextView)findViewById(R.id.status2);
        	status2view.setText(qitem.getStatus());
        	
        	TextView user2view = (TextView)findViewById(R.id.user2);
        	user2view.setText(qitem.getStatus());
        	
        	TextView pages2view = (TextView)findViewById(R.id.pages2);
        	pages2view.setText(qitem.getPages());
        	
        	TextView size2view = (TextView)findViewById(R.id.size2);
        	size2view.setText(qitem.getSize());
        	
        	TextView time2view = (TextView)findViewById(R.id.time2);
        	time2view.setText(qitem.getTime());
        	
        	qitem = it.next();	
        	TextView qnum3view = (TextView)findViewById(R.id.qnum3);
        	qnum3view.setText(qitem.getQnum());
        	
        	TextView name3view = (TextView)findViewById(R.id.name3);
        	name3view.setText(qitem.getFileName());
        	
        	TextView status3view = (TextView)findViewById(R.id.status3);
        	status3view.setText(qitem.getStatus());
        	
        	TextView user3view = (TextView)findViewById(R.id.user3);
        	user3view.setText(qitem.getStatus());
        	
        	TextView pages3view = (TextView)findViewById(R.id.pages3);
        	pages3view.setText(qitem.getPages());
        	
        	TextView size3view = (TextView)findViewById(R.id.size3);
        	size3view.setText(qitem.getSize());
        	
        	TextView time3view = (TextView)findViewById(R.id.time3);
        	time3view.setText(qitem.getTime());
        	
    	  }
    
    	
    	/*uses iterator to Display 3 row table of printer queue
    	 * TO BE DONE: 
    	 * - Make the table dynamic
    	 * - Make it fit the screen
    	 * - Make it organize itself by queue
    	 * 
    	*/
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_printer, menu);
		return true;
	}

}
