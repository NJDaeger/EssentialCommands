package com.njdaeger.java.configuration;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.parser.BlacklistedCommand;
import com.njdaeger.java.configuration.parser.NoPermission;
import com.njdaeger.java.essentials.enums.Permission;

public class Parser {

	/**
	 * Parser for BlacklistedCommands error.
	 * 
	 * @param errormessage Gets the error from the messages.yml to parse.
	 * @param player Player variable in the parse.
	 * @param command Command variable in the parse.
	 * @return Returns fully parsed error.
	 * 
	 *         <p>
	 *         Possible tags: {COMMAND}, {XPOS}, {YPOS}, {ZPOS}, {PLAYER},
	 *         {WORLD}, {DATE}, {TIME}
	 *         </p>
	 */
	public static String parse(String errormessage, Player player, String command) {
		return new BlacklistedCommand().parse(errormessage, player, command);
	}

	/**
	 * Parser for NoPermission error.
	 * 
	 * @param errormessage Gets the error from the messages.yml to parse.
	 * @param player Player variable in the parse.
	 * @param rank Rank variable in the parse.
	 * @param permissions Permissions variable in the parse.
	 * @return Returns fully parsed error.
	 * 
	 *         <p>
	 *         Possible tags: {NEEDSRANK}, {PERMISSIONS}, {PLAYER},
	 *         {DISPLAYNAME}, {TIME}, {DATE}, {WORLD}, {XPOS}, {YPOS}, {ZPOS}
	 *         </p>
	 */
	public static String parse(String errormessage, Player player, String rank, Permission... permissions) {
		return new NoPermission().parse(errormessage, player, rank, permissions);
	}

}
