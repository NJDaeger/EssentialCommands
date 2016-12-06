package com.njdaeger.java.configuration.exceptions;

public class HomeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	static String message = "Home not found.";
	
	public HomeNotFoundException() {
		super(message);
	}

}
