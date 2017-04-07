package com.njdaeger.java.configuration.exceptions;

public class WarpNotFound extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WarpNotFound(){
		super("Warp could not be found.");
	}

}
