package com.njdaeger.java.configuration.exceptions.db;

public class DatabaseEntryMissing extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseEntryMissing() {
		super("Database entry does not exist.");
	}

}
