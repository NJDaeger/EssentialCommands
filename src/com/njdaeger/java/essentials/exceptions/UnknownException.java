package com.njdaeger.java.essentials.exceptions;

public class UnknownException extends Exception {
	
	private static final long serialVersionUID = 1L;
	static String message = "Unknown error.";
	
	
	public UnknownException() {
		super(message);
	}

}
