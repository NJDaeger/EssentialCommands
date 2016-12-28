package com.njdaeger.java.configuration.exceptions.homes;

public class HomeNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public HomeNotFound() {
		super("No homes were found for this player.");
	}

}
