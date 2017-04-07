package com.njdaeger.java.configuration.enums;

public enum InternalDatabase {
	
	/**
	 * Messages database
	 */
	MESSAGES("messages"),
	/**
	 * Playerdata database.
	 */
	PLAYERDATA("playerdata");
	
	String name;
	
	private InternalDatabase(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name of the database.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
}
