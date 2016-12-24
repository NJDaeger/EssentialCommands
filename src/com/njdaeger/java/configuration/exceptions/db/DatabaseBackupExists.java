package com.njdaeger.java.configuration.exceptions.db;

public class DatabaseBackupExists extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseBackupExists() {
		super("Database backup already exists.");
	}

}
