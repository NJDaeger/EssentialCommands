package com.configapi.configuration.exceptions;

import net.md_5.bungee.api.ChatColor;

public class UnknownErrorException extends Exception{
	
	private static final long serialVersionUID = 1L;
	static String message = ChatColor.RED + "Unknown error.";
	
	public UnknownErrorException() {
		super(message);
	}
}
