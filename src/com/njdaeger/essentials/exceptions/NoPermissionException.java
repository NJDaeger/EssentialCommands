package com.njdaeger.essentials.exceptions;

import net.md_5.bungee.api.ChatColor;

public class NoPermissionException extends Exception{

	private static final long serialVersionUID = 1L;
	static String message = ChatColor.RED + "You don't have permission to do that.";
	
	public NoPermissionException() {
		super(message);
	}
	
}
