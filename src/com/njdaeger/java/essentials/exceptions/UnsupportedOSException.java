package com.njdaeger.java.essentials.exceptions;


public class UnsupportedOSException extends Exception{
	private static final long serialVersionUID = 1L;
	static String message = "Unsupported Operating System. This command cannot be used properly.";
	public UnsupportedOSException() {
		super(message);
	}

}
