package com.comp1008.serveranalytics.datamanagement;
/*
 * Class to hold information about a machine
 */
public class Computer {
	
	private String ipAddress;
	private String status;
	private String labRoom;
	private String currentLogin;
	private String name;
	
	public Computer()
	{
		//initialize machine variables
	}
	

	
	public Computer(String ipAddress, String status, String labRoom, String currentLogin, String name) 
	{
		this.ipAddress = ipAddress;
		this.status = status;
		this.labRoom = labRoom;
		this.currentLogin = currentLogin;
		this.name = name;
	}



	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}



	public String getIpAddress() {
		return ipAddress;
	}



	public String getLabRoom() {
		return labRoom;
	}



	public String getCurrentLogin() {
		return currentLogin;
	}



	public String getName() {
		return name;
	}

}
