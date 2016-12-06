package com.njdaeger.java.essentials.exceptions;

import net.md_5.bungee.api.ChatColor;

public class TooManyArgsException extends Exception{

	private static final long serialVersionUID = 1L;
	static String message = ChatColor.RED + "Too many arguments.";
	
	
	public TooManyArgsException() {
		super(message);
	}
}
