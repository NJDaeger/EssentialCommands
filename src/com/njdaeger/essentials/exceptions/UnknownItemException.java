package com.njdaeger.essentials.exceptions;

public class UnknownItemException extends Exception{

	private static final long serialVersionUID = 1L;
	static String message = "Unknown item exception.";
	
	
	public UnknownItemException() {
		super(message);
	}
	
	

}
