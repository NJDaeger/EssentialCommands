package com.njdaeger.essentials.exceptions;

public class UnknownStatusException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	static String message = "Unknown status type.";
	
	
	public UnknownStatusException() {
		super(message);
	}

}
