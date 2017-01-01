package com.njdaeger.java.configuration.enums;

public enum Messages {

	/**
	 * The message to send to the console if a non-permitted command is
	 * executed. Add support for tags like {COMMAND} {XPOS} {YPOS} {ZPOS}
	 * {PLAYER} {DISPLAYNAME} {WORLD} {DATE} {TIME} and colorcodes.
	 */
	BLACKLIST_COMMAND_MSG_CSL("%%%%%PARSER_HERE%%%%%", "&c{PLAYER} attempted to execute a non-permitted command.", "blacklisted-command-console-notify"),
	BLACKLIST_COMMAND_MSG_OP("%%%%%PARSER_HERE%%%%%", "&c{PLAYER} attempted to execute a non-permitted command.", "blacklisted-command-op-notify"),
	BLACKLIST_COMMAND_MSG_PLR("%%%%%PARSER_HERE%%%%%", "&cYou cannot execute a non-permitted command.", "blacklisted-command-player-notify"),
	
	/**
	 * The notify message for the console when a non-permitted block is placed.
	 * Add support for tags like {BLOCK} {BLOCKID} {XPOS} {YPOS} {XPOS} {PLAYER}
	 * {DISPLAYNAME} {WORLD} {DATE} {TIME} and colorcodes.
	 */
	BLACKLIST_BLOCK_MSG_CSL("%%%%%PARSER_HERE%%%%%", "&c{PLAYER} attempted to place a non-permitted block.", "blacklisted-block-console-notify"),
	BLACKLIST_BLOCK_MSG_OP("%%%%%PARSER_HERE%%%%%", "&c{PLAYER} attempted to place a non-permitted block.", "blacklisted-block-op-notify"),
	BLACKLIST_BLOCK_MSG_PLR("%%%%%PARSER_HERE%%%%%", "&cYou cannot use a non-permitted block.", "blacklisted-block-player-notify");

	public String message;
	public String defaultmessage;
	public String path;

	Messages(String message, String defaultmessage, String path) {
		this.message = message;
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
		if (this.message == null) {
			return defaultmessage;
		}
		return this.message;
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
