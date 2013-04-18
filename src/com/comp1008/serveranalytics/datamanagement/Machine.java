package com.comp1008.serveranalytics.datamanagement;
/*
 * Class to hold information about a machine
 */
public class Machine {
	
	private String ipAddress;
	private String status;
	private String labRoom;
	private String currentLogin;
	private String name;
	
	public Machine()
	{
		//initialize machine variables
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}

}
