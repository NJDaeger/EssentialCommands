package com.njdaeger.java.configuration.exceptions;

public class ExistingDatabaseBackup extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ExistingDatabaseBackup() {
		super("Database backup exists.");
	}

}
