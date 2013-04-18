package com.comp1008.serveranalytics.datamanagement;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Class to hold information about a printer
 */
public class Printer {
	private String ipAddress;
	private String status;
	private String name;
	private String tonerRemaining;
	private ArrayList<QueueItem> queueItems;
	
	public Printer(String ipAddress, String status, String name,
			String tonerRemaining, ArrayList<QueueItem> queueItems) 
	{
		this.ipAddress = ipAddress;
		this.status = status;
		this.name = name;
		this.tonerRemaining = tonerRemaining;
		this.queueItems = queueItems;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}

	public String getTonerRemaining() {
		return tonerRemaining;
	}

	public Iterator<QueueItem> getQueueItemsIterator() {
		return queueItems.iterator();
	}

}
