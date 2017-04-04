package com.njdaeger.java.configuration.exceptions.db;

public class DatabaseEntryExists extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseEntryExists() {
		super("Database entry already exists.");
	}

}
