package com.njdaeger.java.configuration.enums;

import com.njdaeger.java.configuration.controllers.Database;

public enum Messages {

	/**
	 * The message to send to the console if a non-permitted command is
	 * executed. Add support for tags like {COMMAND} {XPOS} {YPOS} {ZPOS}
	 * {PLAYER} {DISPLAYNAME} {WORLD} {DATE} {TIME} and colorcodes.
	 */
	BLACKLIST_COMMAND_MSG_CSL("&c{PLAYER} attempted to execute a non-permitted command.", "blacklisted-command-console-notify"),
	BLACKLIST_COMMAND_MSG_OP("&c{PLAYER} attempted to execute a non-permitted command.", "blacklisted-command-op-notify"),
	BLACKLIST_COMMAND_MSG_PLR("&cYou cannot execute a non-permitted command.", "blacklisted-command-player-notify"),
	
	/**
	 * The notify message for the console when a non-permitted block is placed.
	 * Add support for tags like {BLOCK} {BLOCKID} {XPOS} {YPOS} {XPOS} {PLAYER}
	 * {DISPLAYNAME} {WORLD} {DATE} {TIME} and colorcodes.
	 */
	BLACKLIST_BLOCK_MSG_CSL("&c{PLAYER} attempted to place a non-permitted block.", "blacklisted-block-console-notify"),
	BLACKLIST_BLOCK_MSG_OP("&c{PLAYER} attempted to place a non-permitted block.", "blacklisted-block-op-notify"),
	BLACKLIST_BLOCK_MSG_PLR("&cYou cannot use a non-permitted block.", "blacklisted-block-player-notify"),
	
	/*
	 * These are all of the default error messages. 
	 * Add these tags for every error {PLAYER} {DISPLAYNAME} {TIME} {DATE} {WORLD}
	 */ 
	ERROR_NOT_ENOUGH_ARGS("&cError: &4Not enough arguments provided.", "error-not-enough-args"), //Custom {USAGE} 1
	ERROR_TOO_MANY_ARGS("&cError: &4Too many arguments provided.", "error-too-many-args"), //Custom {USAGE} 1
	ERROR_NO_PERMISSION("&cError: &4You don't have sufficient permission to do that.", "error-no-permission"), //Custom {NEEDSRANK} {PERMISSION} 2
	ERROR_UNKNOWN_PLAYER("&cError: &4Player provided does not exist.", "error-unknown-player"), //Custom {GIVEN} 3
	ERROR_PLAYER_ONLY("&cError: &4This action is restricted to players only.", "error-players-only"), //Take away {PLAYER} {DISPLAYNAME} and {WORLD} 4
	ERROR_UNKNOWN_GAMEMODE("&cError: &4Gamemode provided does not exist.", "error-unknown-gamemode"), //Custom {GIVEN} {GAMEMODES} {USAGE} 5
	ERROR_INPUT_NOT_NUM("&cError: &4Input provided must be an integer.", "error-input-not-number"), //Custom {GIVEN} {USAGE} 6
	ERROR_UNKNOWN_ITEM("&cError: &4Input provided is an unknown item.", "error-unknown-item"), //Custom {GIVEN} {USAGE} 6
	ERROR_INVENTORY_IS_FULL("&cError: &4Targets inventory is full.", "error-full-inventory"), //Custom {TARGET} 7
	ERROR_NICKNAME_TOO_LONG("&cError: &4The nickname given is too long.", "error-nickname-too-long"), //Custom {MAXLENGTH} {GIVEN} {DIFF} (how much longer it was) 8
	ERROR_PLAYER_IS_OPPED("&cError: &4Cannot perform action. Player is opped.", "error-player-is-opped"), //Custom {TARGET} 7
	ERROR_INPUT_TOO_LARGE("&cError: &4The input given is too large.", "error-input-too-large"), //Custom {MAX} {GIVEN} 9
	ERROR_INPUT_TOO_SMALL("&cError: &4The input given is too small.", "error-input-too-small"), //Custom {MIN} {GIVEN} 10
	ERROR_UNKNOWN_WALK_TYPE("&cError: &4The given walking type does not exist.", "error-unknown-walk-type"), //Custom {WALKTYPES} 11
	ERROR_MESSAGING_DISABLED("&cError: &4You have messaging disabled.", "error-messaging-disabled"), 
	ERROR_MESSAGING_DISABLED_OTHER("&cError: &4The target has messaging disabled.", "error-messaging-disabled-other"), //Custom {TARGET} 7
	ERROR_CANNOT_BAN_TARGET("&cError: &4Target cannot be banned.", "error-cannot-ban-target"), //Custom {TARGET} 7
	ERROR_UNKNOWN_BAN_TYPE("&cError: &4Ban type given does not exist.", "error-unknown-ban-type"), //Custom {BANTYPES} {GIVEN} 12
	ERROR_BAN_WRONG_FORMAT("&cError: &4The issued ban is in the wrong format.", "error-ban-wrong-format"), //Custom {BANFORMAT} {USAGE} {BANTYPES} 13
	ERROR_PLAYER_NOT_BANNED("&cError: &4Target is not banned.", "error-player-not-banned"), //Custom {GIVEN} 3
	ERROR_TARGET_NOT_SIGN("&cError: &4Target block is not a sign.", "error-target-not-sign"), //Custom {BLOCK} {BLOCKID} 14
	ERROR_LINE_NUMBER_INVALID("&cError: &4The line value given is invalid.", "error-line-number-invalid"), //Custom {VALIDLINES} {GIVEN} 15
	ERROR_CANNOT_BE_HAT("&cError: &4The item given cannot be a hat.", "error-cannot-be-hat"), //Custom {GIVEN} 3 
	ERROR_NO_PLAYERS_ONLINE("&cError: &4There are no players currently online.", "error-no-players-online"), //Take away {PLAYER} {DISPLAYNAME} 16 
	ERROR_ADD_HOME_NAME("&cError: &4You must specify a name for your home.", "error-add-home-name"), //Custom {GIVEN} 3 
	ERROR_HOME_EXISTS("&cError: &4The home given already exists.", "error-home-exists"), //Custom {GIVEN} 3
	ERROR_HOME_NOTEXIST("&cError: &4The home given does not exist.", "error-home-notexist"), //Custom {GIVEN} 3
	ERROR_WORLD_NOT_FOUND("&cError: &4The world cannot be found.", "error-world-not-found"), //Custom {GIVEN} {WORLDS} 17 
	ERROR_NO_HOMES("&cError: &4No homes exist for the target.", "error-no-homes"),
	ERROR_DATABSE_NOT_FOUND("&cError: &4Database could not be found.", "error-database-not-found"), //Custom {DATABASE} {DATABASES} 18 
	ERROR_WARP_EXISTS("&cError: &4The warp specified already exists.", "error-warp-exists"), //Custom {GIVEN} 3 
	ERROR_WARP_NOTEXISTS("&cError: &4Warp could not be found.", "error-warp-notexists"), //Custom {GIVEN} 3
	ERROR_INVALID_COMMAND_SENDER("&cError: &4Invalid command sender.", "error-invalid-command-sender"), //Take away {PLAYER} {DISPLAYNAME} {SENDER} 19 
	ERROR_CANNOT_SEND_PM("&cError: &4Cannot send message.", "error-cannot-send-pm"); //Custom {GIVEN} 3
	/*
	 * Needed parsers
	 * ##GLOBAL##
	 * {TIME}
	 * {DATE}
	 * 
	 * 
	 * ##CUSTOM##
	 * {WORLD} -
	 * {GIVEN} -
	 * {PLAYER} -
	 * {DISPLAYNAME} -
	 * {COMMAND} -
	 * {XPOS} -
	 * (YPOS) -
	 * {ZPOS} -
	 * {BLOCK} -
	 * {BLOCKID} -
	 * {USAGE} -
	 * {REQUIREDRANK} -
	 * {PERMISSION} -
	 * {GAMEMODES} -
	 * {TARGET} -
	 * {MAXLENGTH} -
	 * {DIFF} -
	 * {MAX} -
	 * {MIN} -
	 * {WALKTYPES} -
	 * {BANTYPES} -
	 * {BANFORMAT} -
	 * {VALIDLINES}
	 * {DATABASE} -
	 * {DATABASES} -
	 * {SENDER} -
	 */
	
	
	public String defaultmessage;
	public String path;

	Messages(String defaultmessage, String path) {
		this.defaultmessage = defaultmessage;
		this.path = path;
	}

	/**
	 * Gets the custom message.
	 * 
	 * @return Returns the custom message, otherwise it returns the default
	 *         message.
	 */
	public String getMessage() {
		if (Database.getDatabase("messages").getEntry(path) == null) {
			return defaultmessage;
		}
		return Database.getDatabase("messages").getEntry(path);
	}

	/**
	 * Get the default message.
	 * 
	 * @return The default message.
	 */
	public String defMessage() {
		return this.defaultmessage;
	}

	/**
	 * Gets the path in the messages.yml
	 * 
	 * @return Path to the message.
	 */
	public String getPath() {
		return this.path;
	}
}
