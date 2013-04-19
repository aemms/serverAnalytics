package com.comp1008.serveranalytics.serveraccess;

public class NoConnectionException extends Exception {
	  
	public NoConnectionException(){
	        super();
	    }

	    public NoConnectionException(String message){
	        super(message);
	    }

}
