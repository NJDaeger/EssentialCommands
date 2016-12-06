package com.njdaeger.java.essentials.exceptions;

public class UnknownActionException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private static String message = "An unknown action occured.";
	
	public UnknownActionException() {
		super(message);
	}

}
