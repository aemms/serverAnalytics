package com.comp1008.serveranalytics.datamanagement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import android.content.Context;
/*
 * This class reads data from the local CSV file into the currently active data controller
 */

public class LocalServerDataReader {
	
	private FileInputStream file;
	private Scanner in;
	private String line;
	
	public LocalServerDataReader(Context context) throws FileNotFoundException
	{
		//open the local file to read
		file = context.openFileInput("alldata");
		in = new Scanner(file);


	}
	//all code below is to parse the data file into data structures
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
		String ip = Parser.parseItemFromLine(',', ',', line, 0);
		String status = Parser.parseItemFromLine(',', ',', line, 1);
		String room = Parser.parseItemFromLine(',', ',', line, 2);
		String user = Parser.parseItemFromLine(',', ',', line, 3);
		String name = Parser.parseItemFromLine(',', '\n', line, 4);      

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
				name = Parser.parseItemFromLine(',', ',', line, 0);
				ip = Parser.parseItemFromLine(',', ',', line, 1);
				status = Parser.parseItemFromLine(',', ',', line, 2);
				tonerRemaining = Parser.parseItemFromLine(',', '\n', line, 3);    
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
		String qnum = Parser.parseItemFromLine(',', ',', line, 0);
		String printer = Parser.parseItemFromLine(',', ',', line, 1);
		String name = Parser.parseItemFromLine(',', ',', line, 2);
		String status = Parser.parseItemFromLine(',', ',', line, 3);
		String user = Parser.parseItemFromLine(',', ',', line, 4);
		String pages = Parser.parseItemFromLine(',', ',', line, 5);
		String size = Parser.parseItemFromLine(',', ',', line, 6);
		String time = Parser.parseItemFromLine(',', '\n', line, 7);

	
		
		return new QueueItem (qnum, printer, name, status, user, pages, size, time);
	}
}