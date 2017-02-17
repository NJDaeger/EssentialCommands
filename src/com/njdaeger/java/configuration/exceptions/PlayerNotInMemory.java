package com.njdaeger.java.configuration.exceptions;

public class PlayerNotInMemory extends Exception {

	private static final long serialVersionUID = 1L;

	public PlayerNotInMemory() {
		super("The player confifuration is not loaded in memory.");
	}

}
