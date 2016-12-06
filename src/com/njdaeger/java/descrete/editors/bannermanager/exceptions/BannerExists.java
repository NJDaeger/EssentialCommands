package com.njdaeger.java.descrete.editors.bannermanager.exceptions;

public class BannerExists extends Exception{

	private static final long serialVersionUID = 1L;
	
	public BannerExists() {
		super("Banner already exists.");
	}

}
