package com.njdaeger.essentials.bannermanager.utils;

public class BannerExists extends Exception{

	private static final long serialVersionUID = 1L;
	
	public BannerExists() {
		super("Banner already exists.");
	}

}
