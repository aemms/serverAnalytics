package com.comp1008.serveranalytics.ui;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.comp1008.serveranalytics.R;
import com.comp1008.serveranalytics.datamanagement.Computer;
import com.comp1008.serveranalytics.datamanagement.DataController;
import com.comp1008.serveranalytics.datamanagement.Printer;

/* This class should show a list of printers, which are clickable and on click
 * will load a page with the printer's details. getPrinters() function should be added. */

public class PrinterListActivity extends Activity implements AdapterView.OnItemClickListener{

	private DataController data;
	private String printers[];
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_printer_list);
	data = new DataController(getBaseContext());
	
	printers = getPrinterNameArray();
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(PrinterListActivity.this, R.layout.lab_list_custom, printers);
	
	// Creates an ArrayAdapter for the String array printers.
	ListView listOfPrinters = (ListView) findViewById(R.id.PrintersList);
	// This ListView is in the XML activity_printer_list

	listOfPrinters.setAdapter(adapter);
	listOfPrinters.setOnItemClickListener(this);
    }
    
	private String[] getPrinterNameArray() {
		Iterator<Printer> printers = data.getPrinterIterator();
		ArrayList<String> names = new ArrayList<String>();
		while(printers.hasNext())
		{
			Printer printer = printers.next();
			names.add(printer.getName());
		}
		String []namesArray = new String[names.size()];
		names.toArray(namesArray);
		return namesArray;
	}


    
    
    /* This Method should get list of printers in lab on button click, 
	 * Passes string of printer name corresponding to button pressed to its respective page   */
    // For now it just passes the string to the main menu
    public void onItemClick(AdapterView adapter, View v, int position, long id){
    	String printerName = printers[position];
    	Intent intent = new Intent(PrinterListActivity.this, PrinterActivity.class);
    	Bundle printer = new Bundle();
    	printer.putString("printer", printerName);
    	intent.putExtras(printer);
    	startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.printer_list, menu);
	return true;
    }

}
