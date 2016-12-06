package com.njdaeger.java.configuration.exceptions;

public class WarpExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public WarpExistsException() {
		super("Warp already exists.");
	}

}
