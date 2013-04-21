package com.comp1008.serveranalytics.datamanagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.widget.Toast;

/*
 * Contains methods for accessing data from ui activities
 */
public class DataController {
	private ArrayList<Computer> listOfComputers = new ArrayList<Computer>();	
	private ArrayList<Printer> listOfPrinters = new ArrayList<Printer>();
	private Context context;
	private LocalServerDataReader localDataReader;
	
	@SuppressWarnings("unchecked")
	public DataController(Context context)
	{
		this.context = context;
		try
		{
			this.localDataReader = new LocalServerDataReader(context);
		}
		catch (FileNotFoundException e)
		{
			CharSequence text = "Couldn't open data file";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		
		if (localDataReader!=null)
		{
			ArrayList<ArrayList> data = localDataReader.loadData();
			listOfComputers = (ArrayList<Computer>)data.get(0);
			listOfPrinters = (ArrayList<Printer>)data.get(1);
		}
	}
	
	
	/*get an Iterator over the machines list to access the list of current machines from
	anywhere in the app */
	public Iterator<Computer> getComputerIterator()
	{
		return listOfComputers.iterator();
	}
	
	/*get an Iterator over the printers list to access the list of current machines from
	anywhere in the app */
	public Iterator<Printer> getPrinterIterator()
	{
		return listOfPrinters.iterator();
	}
	
	public void addComputer(Computer m)
	{
		listOfComputers.add(m);
	}
	
	public void addPrinter(Printer p)
	{
		listOfPrinters.add(p);
	}
	
	public Computer getComputerByName(String name)
	{
		for (Computer c : listOfComputers)
		{
			if (c.getName().equals(name))
			{
				return c;
			}
		}
		return null;
	}


	public Printer getPrinterByName(String name) {
		for (Printer p : listOfPrinters)
		{
			if (p.getName().equals(name))
			{
				return p;
			}
		}
		return null;
	}
}
