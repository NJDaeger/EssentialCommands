package com.njdaeger.java.descrete.bannermanager.utils;

public class BannerMissing extends Exception{
	
	private static final long serialVersionUID = 1L;

	public BannerMissing() {
		super("Banner does not exist.");
	}
	
}
