package com.njdaeger.java.configuration;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.interfaces.IParser;
import com.njdaeger.java.configuration.parser.BlacklistedCommand;

public class Parse implements IParser {

	public static String parseCommand(String errormessage, Player player, String command) {
		return new BlacklistedCommand().parse(errormessage, player, command);
	}

}
