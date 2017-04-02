package com.njdaeger.java.command.util;

import org.bukkit.ChatColor;

public enum Error {
	
	NO_PERMISSION("&cError: &4You don't have permission to do that."),
	CONSOLE_ONLY("&cError: &4Command can only be executed by the console."),
	PLAYER_ONLY("&cError: &4Command can only be executed by a player."),
	BLOCK_ONLY("&cError: &4Command can only be executed by a command block."),
	PLAYER_CONSOLE_ONLY("&cError: &4Command is for player and console use only."),
	BLOCK_CONSOLE_ONLY("&cError: &4Command is for block and console use only."),
	PLAYER_BLOCK_ONLY("&cError: &4Command is for player and block use only."),
	TOO_MANY_ARGS("&cError: &4Too many arguments provided."),
	NOT_ENOUGH_ARGS("&cError: &4Not enough arguments provided.");
	
	String error;
	
	Error(String error) {
		this.error = error;
	}
	
	public String getError() {
		return error;
	}
	
	public String format() {
		return ChatColor.translateAlternateColorCodes('&', error);
	}
}
