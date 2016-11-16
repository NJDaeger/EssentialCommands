package com.configapi.configuration.exceptions;


public class DatabaseNotFound extends Exception{
	
	static String message = "Could not find database.";
	static final long serialVersionUID = 1L;

	public DatabaseNotFound() {
		super(message);
	}
}
