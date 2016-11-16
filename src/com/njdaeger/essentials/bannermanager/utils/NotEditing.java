package com.njdaeger.essentials.bannermanager.utils;


public class NotEditing extends Exception{

	private static final long serialVersionUID = 1L;
	
	public NotEditing() {
		super("Player is not currently editing.");
	}
	

}
