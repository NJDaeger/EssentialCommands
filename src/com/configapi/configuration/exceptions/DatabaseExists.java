package com.configapi.configuration.exceptions;

public class DatabaseExists extends Exception{

	private static final long serialVersionUID = 1L;
	
	public DatabaseExists() {
		super("Database already exists.");
	}
	
	

}
