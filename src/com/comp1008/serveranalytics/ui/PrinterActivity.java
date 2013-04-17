package com.comp1008.serveranalytics.ui;

import com.comp1008.serveranalytics.R;
import com.comp1008.serveranalytics.R.layout;
import com.comp1008.serveranalytics.R.menu;
import com.comp1008.serveranalytics.datamanagement.DataController;
import com.comp1008.serveranalytics.datamanagement.Printer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
/*
 * Activity which shows all information available about a chosen printer
 */
public class PrinterActivity extends Activity {

	private String printerName;
	private Printer printer;
	private DataController data;
	
	
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
    	printer = data.getPrinterByName(printerName);
    	
    	TextView printerNameView = (TextView)findViewById(R.id.printername_content);
    	printerNameView.setText(printer.getName());
    	TextView printerIpView = (TextView)findViewById(R.id.printerip_content);
    	printerIpView.setText(printer.getIpAddress());
    	TextView printerStatusView = (TextView)findViewById(R.id.printerstatus_content);
    	printerStatusView.setText(printer.getStatus());
    	TextView printerTonerView = (TextView)findViewById(R.id.tonerremaining_content);
    	printerTonerView.setText(printer.getTonerRemaining());
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_printer, menu);
		return true;
	}

}
