package com.njdaeger.essentials.exceptions;

import net.md_5.bungee.api.ChatColor;

public class SenderNotPlayerException extends Exception{
	private static final long serialVersionUID = 1L;
	
	static String message = ChatColor.RED + "You are not a player.";
	
	public SenderNotPlayerException() {
		super(message);
	}

}
