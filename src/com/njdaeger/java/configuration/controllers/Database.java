package com.njdaeger.java.configuration.controllers;

import com.njdaeger.java.configuration.data.DatabaseData;

public class Database {

	public static String database;
	private static DatabaseData databaseData = new DatabaseData();

	public static DatabaseData getDatabase(String database) {
		Database.database = database;
		return databaseData;
	}
}
