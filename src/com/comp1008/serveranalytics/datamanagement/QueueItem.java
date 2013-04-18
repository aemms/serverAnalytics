package com.comp1008.serveranalytics.datamanagement;

public class QueueItem {
	
	public String qnum;
	private String printer;
	private String fileName;
	private String status;
	private String user;
	private String pages;
	private String size;
	private String time;
	
	public QueueItem(String qnum, String printer, String fileName, String status, String user, String pages, String size, String time)
	{
		this.qnum = qnum;
		this.printer = printer;
		this.fileName = fileName;
		this.status = status;
		this.user = user;
		this.pages = pages;
		this.size = size;
		this.time = time;
	}

	public String getQnum() {
		return qnum;
	}

	public String getPrinter() {
		return printer;
	}
	
	public String getFileName() {
		return fileName;
	}

	public String getStatus() {
		return status;
	}

	public String getUser() {
		return user;
	}

	public String getPages() {
		return pages;
	}

	public String getSize() {
		return size;
	}

	public String getTime() {
		return time;
	}
	

}
