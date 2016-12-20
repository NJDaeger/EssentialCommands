package com.njdaeger.java.descrete.bannermanager.exceptions;

public class BannerExists extends Exception{

	private static final long serialVersionUID = 1L;
	
	public BannerExists() {
		super("Banner already exists.");
	}

}
