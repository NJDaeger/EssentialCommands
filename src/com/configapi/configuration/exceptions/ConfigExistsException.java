package com.configapi.configuration.exceptions;

import net.md_5.bungee.api.ChatColor;

public class ConfigExistsException extends RuntimeException{
	static String message = ChatColor.RED + "File already exists.";
	static final long serialVersionUID = 1L;
	
	public ConfigExistsException(){
		super(message);
	}
}
