package com.comp1008.serveranalytics.datamanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import android.content.Context;
/*
 * This class reads data from the local CSV file into the currently active data controller
 */

public class LocalServerDataReader {
	
	private FileReader file;
	private Scanner in;
	private String line;
	
	public LocalServerDataReader(Context context) throws IOException
	{
	
		file = new FileReader("alldata.txt", context);
		in = new Scanner(file.getInputStream());


	}
	
	public ArrayList<ArrayList> loadData()
	{
		ArrayList<ArrayList> data = new ArrayList<ArrayList>();
		data.add(loadComputerList());
		data.add(loadPrinterList());
		return data;
	}
	
	private ArrayList<Computer> loadComputerList()
	{
		ArrayList<Computer> computers = new ArrayList<Computer>();
		while (in.hasNext())
		{
			String line = in.nextLine();
			if (line.equals("COMPUTERS"))
			{
				while (in.hasNext())
				{
					line = in.nextLine();
					if(!line.equals("PRINTERS"))
					{
						computers.add(parseComputer(line));
					}
					else
					{
						break;
					}
				}
			}
			break;
		}
		return computers;
	}
	
	private Computer parseComputer(String line)
	{
		
		int charCount = 0;
		String ip = "";
		String status = "";
		String room = "";
		String user = "";
		String name = "";
		
		for (charCount = 0; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == ',')
		    {
		    	break;
		    }
		    else
		    {
		    	ip = ip+Character.toString(c);
		    }
		}
		for (charCount = charCount+1; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == ',')
		    {
		    	break;
		    }
		    else
		    {
		    	status = status+Character.toString(c);
		    }
		}
		for (charCount = charCount+1; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == ',')
		    {
		    	break;
		    }
		    else
		    {
		    	room = room+Character.toString(c);
		    }
		}
		for (charCount = charCount+1; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == ',')
		    {
		    	break;
		    }
		    else
		    {
		    	user = user+Character.toString(c);
		    }
		}
		for (charCount = charCount+1; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == '\n')
		    {
		    	break;
		    }
		    else
		    {
		    	name = name+Character.toString(c);
		    }
		}
		Computer computer = new Computer(ip, status, room, user, name);
		return computer;
	}
	
	private ArrayList<Printer> loadPrinterList()
	{
		ArrayList<Printer> printers = new ArrayList<Printer>();
		while (in.hasNext())
		{
			String line = in.nextLine();
			if(line.equals("PRINTER"))
			{
				line = in.nextLine();
				printers.add(parsePrinter(in, line));
			}
			else if (line.equals("END"))
			{
				break;
			}
			else
			{
				printers.add(parsePrinter(in,line));
			}
		}
		return printers;
	}
	
	private Printer parsePrinter(Scanner in, String line)
	{
		int charCount = 0;
		String ip = "";
		String status = "";
		String name = "";
		String tonerRemaining = "";
		
		ArrayList<QueueItem> queue = new ArrayList<QueueItem>();
		boolean gotPrinter = false;
		while(in.hasNext())
		{
			if (line.equals("END") || line.equals("PRINTER"))
			{
				break;
			}
			else if (line.equals("QUEUE"))
			{
				queue = parseQueue(in);
				break;
			}
			else if (!gotPrinter)
			{
				for (charCount = 0; charCount < line.length(); charCount++){
				    char c = line.charAt(charCount);        
				    if (c == ',')
				    {
				    	break;
				    }
				    else
				    {
				    	name = name+Character.toString(c);
				    }
				}
				for (charCount = charCount+1; charCount < line.length(); charCount++){
				    char c = line.charAt(charCount);        
				    if (c == ',')
				    {
				    	break;
				    }
				    else
				    {
				    	ip = ip+Character.toString(c);
				    }
				}
				for (charCount = charCount+1; charCount < line.length(); charCount++){
				    char c = line.charAt(charCount);        
				    if (c == ',')
				    {
				    	break;
				    }
				    else
				    {
				    	status = status+Character.toString(c);
				    }
				}
				for (charCount = charCount+1; charCount < line.length(); charCount++){
				    char c = line.charAt(charCount);        
				    if (c == '\n')
				    {
				    	break;
				    }
				    else
				    {
				    	tonerRemaining = tonerRemaining+Character.toString(c);
				    }
				}
				gotPrinter = true;
			}
			line = in.nextLine();
		}
		
		
		return new Printer(ip,status,name,tonerRemaining,queue);
	}
	
	
	private ArrayList<QueueItem> parseQueue(Scanner in)
	{
		ArrayList<QueueItem> queue = new ArrayList<QueueItem>();
		while (in.hasNext())
		{
			String line = in.nextLine();
			if (!line.equals("END") && !line.equals("PRINTER"))
			{
				queue.add(parseQueueItem(line));
			}
			else
			{
				break;
			}
		}
		return queue;
	}
	
	private QueueItem parseQueueItem(String line)
	{
		String qnum = "";
		String printer = "";
		String name = "";
		String status = "";
		String user = "";
		String pages = "";
		String size = "";
		String time = "";
		int charCount = 0;
		for (charCount = 0; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == ',')
		    {
		    	break;
		    }
		    else
		    {
		    	qnum = qnum+Character.toString(c);
		    }
		}
		for (charCount = charCount+1; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == ',')
		    {
		    	break;
		    }
		    else
		    {
		    	printer = qnum+Character.toString(c);
		    }
		}
		for (charCount = charCount+1; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == ',')
		    {
		    	break;
		    }
		    else
		    {
		    	name = name+Character.toString(c);
		    }
		}
		for (charCount = charCount+1; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == ',')
		    {
		    	break;
		    }
		    else
		    {
		    	status = status+Character.toString(c);
		    }
		}
		for (charCount = charCount+1; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == ',')
		    {
		    	break;
		    }
		    else
		    {
		    	user = user+Character.toString(c);
		    }
		}
		for (charCount = charCount+1; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == ',')
		    {
		    	break;
		    }
		    else
		    {
		    	pages = pages+Character.toString(c);
		    }
		}
		for (charCount = charCount+1; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == ',')
		    {
		    	break;
		    }
		    else
		    {
		    	size = size+Character.toString(c);
		    }
		}
		for (charCount = charCount+1; charCount < line.length(); charCount++){
		    char c = line.charAt(charCount);        
		    if (c == '\n')
		    {
		    	break;
		    }
		    else
		    {
		    	time = time+Character.toString(c);
		    }
		}
		
		return new QueueItem (qnum, printer, name, status, user, pages, size, time);
	}
}