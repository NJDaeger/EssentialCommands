package com.njdaeger.java.configuration.enums;

import com.njdaeger.java.configuration.controllers.Database;

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
	BLACKLIST_BLOCK_MSG_PLR("%%%%%PARSER_HERE%%%%%", "&cYou cannot use a non-permitted block.", "blacklisted-block-player-notify"),
	
	/*
	 * These are all of the default error messages. 
	 * Add these tags for every error {PLAYER} {DISPLAYNAME} {TIME} {DATE} {WORLD}
	 */ 
	ERROR_NOT_ENOUGH_ARGS("%%%%%PARSER_HERE%%%%%", "&cError: &4Not enough arguments provided.", "error-not-enough-args"), //Custom {USAGE}
	ERROR_TOO_MANY_ARGS("%%%%%PARSER_HERE%%%%%", "&cError: &4Too many arguments provided.", "error-too-many-args"), //Custom {USAGE}
	ERROR_NO_PERMISSION("%%%%%PARSER_HERE%%%%%", "&cError: &4You don't have sufficient permission to do that.", "error-no-permission"), //Custom {NEEDSRANK} {PERMISSION}
	ERROR_UNKNOWN_PLAYER("%%%%%PARSER_HERE%%%%%", "&cError: &4Player provided does not exist.", "error-unknown-player"), //Custom {GIVEN}
	ERROR_PLAYER_ONLY("%%%%%PARSER_HERE%%%%%", "&cError: &4This action is restricted to players only.", "error-players-only"), //Take away {PLAYER} {DISPLAYNAME} and {WORLD}
	ERROR_UNKNOWN_GAMEMODE("%%%%%PARSER_HERE%%%%%", "&cError: &4Gamemode provided does not exist.", "error-unknown-gamemode"), //Custom {GIVEN} {GAMEMODES} {USAGE}
	ERROR_INPUT_NOT_NUM("%%%%%PARSER_HERE%%%%%", "&cError: &4Input provided must be an integer.", "error-input-not-number"), //Custom {GIVEN} {USAGE}
	ERROR_UNKNOWN_ITEM("%%%%%PARSER_HERE%%%%%", "&cError: &4Input provided is an unknown item.", "error-unknown-item"), //Custom {GIVEN} {USAGE}
	ERROR_INVENTORY_IS_FULL("%%%%%PARSER_HERE%%%%%", "&cError: &4Targets inventory is full.", "error-full-inventory"), //Custom {TARGET}
	ERROR_NICKNAME_TOO_LONG("%%%%%PARSER_HERE%%%%%", "&cError: &4The nickname given is too long.", "error-nickname-too-long"), //Custom {MAXLENGTH} {GIVEN} {DIFF} (how much longer it was)
	ERROR_PLAYER_IS_OPPED("%%%%%PARSER_HERE%%%%%", "&cError: &4Cannot perform action. Player is opped.", "error-player-is-opped"), //Custom {TARGET}
	ERROR_INPUT_TOO_LARGE("%%%%%PARSER_HERE%%%%%", "&cError: &4The input given is too large.", "error-input-too-large"), //Custom {MAX} {GIVEN}
	ERROR_INPUT_TOO_SMALL("%%%%%PARSER_HERE%%%%%", "&cError: &4The input given is too small.", "error-input-too-small"), //Custom {MIN} {GIVEN}
	ERROR_UNKNOWN_WALK_TYPE("%%%%%PARSER_HERE%%%%%", "&cError: &4The given walking type does not exist.", "error-unknown-walk-type"), //Custom {WALKTYPES}
	ERROR_MESSAGING_DISABLED("%%%%%PARSER_HERE%%%%%", "&cError: &4You have messaging disabled.", "error-messaging-disabled"),
	ERROR_MESSAGING_DISABLED_OTHER("%%%%%PARSER_HERE%%%%%", "&cError: &4The target has messaging disabled.", "error-messaging-disabled-other"), //Custom {TARGET}
	ERROR_CANNOT_BAN_TARGET("%%%%%PARSER_HERE%%%%%", "&cError: &4Target cannot be banned.", "error-cannot-ban-target"), //Custom {TARGET}
	ERROR_UNKNOWN_BAN_TYPE("%%%%%PARSER_HERE%%%%%", "&cError: &4Ban type given does not exist.", "error-unknown-ban-type"), //Custom {BANTYPES} {GIVEN}
	ERROR_BAN_WRONG_FORMAT("%%%%%PARSER_HERE%%%%%", "&cError: &4The issued ban is in the wrong format.", "error-ban-wrong-format"), //Custom {BANFORMAT} {USAGE} {BANTYPES}
	ERROR_PLAYER_NOT_BANNED("%%%%%PARSER_HERE%%%%%", "&cError: &4Target is not banned.", "error-player-not-banned"), //Custom {GIVEN}
	ERROR_TARGET_NOT_SIGN("%%%%%PARSER_HERE%%%%%", "&cError: &4Target block is not a sign.", "error-target-not-sign"), //Custom {BLOCK} {BLOCKID}
	ERROR_LINE_NUMBER_INVALID("%%%%%PARSER_HERE%%%%%", "&cError: &4The line value given is invalid.", "error-line-number-invalid"), //Custom {VALIDLINES} {GIVEN}
	ERROR_CANNOT_BE_HAT("%%%%%PARSER_HERE%%%%%", "&cError: &4The item given cannot be a hat.", "error-cannot-be-hat"), //Custom {GIVEN}
	ERROR_NO_PLAYERS_ONLINE("%%%%%PARSER_HERE%%%%%", "&cError: &4There are no players currently online.", "error-no-players-online"), //Take away {PLAYER} {DISPLAYNAME}
	ERROR_ADD_HOME_NAME("%%%%%PARSER_HERE%%%%%", "&cError: &4You must specify a name for your home.", "error-add-home-name"), //Custom {GIVEN}
	ERROR_HOME_EXISTS("%%%%%PARSER_HERE%%%%%", "&cError: &4The home given already exists.", "error-home-exists"), //Custom {GIVEN}
	ERROR_HOME_NOTEXIST("%%%%%PARSER_HERE%%%%%", "&cError: &4The home given does not exist.", "error-home-notexist"), //Custom {GIVEN}
	ERROR_WORLD_NOT_FOUND("%%%%%PARSER_HERE%%%%%", "&cError: &4The world cannot be found.", "error-world-not-found"), //Custom {GIVEN} {WORLDS}
	ERROR_NO_HOMES("%%%%%PARSER_HERE%%%%%", "&cError: &4No homes exist for the target.", "error-no-homes"),
	ERROR_DATABSE_NOT_FOUND("%%%%%PARSER_HERE%%%%%", "&cError: &4Database could not be found.", "error-database-not-found"), //Custom {DATABASE} {DATABASES}
	ERROR_WARP_EXISTS("%%%%%PARSER_HERE%%%%%", "&cError: &4The warp specified already exists.", "error-warp-exists"), //Custom {GIVEN}
	ERROR_WARP_NOTEXISTS("%%%%%PARSER_HERE%%%%%", "&cError: &4Warp could not be found.", "error-warp-notexists"), //Custom {GIVEN}
	ERROR_NO_BANNER_IN_HAND("%%%%%PARSER_HERE%%%%%", "&cError: &4You don't have a banner in your main hand.", "error-no-banner-in-hand"),
	ERROR_NOT_BANNER_MAKER("%%%%%PARSER_HERE%%%%%", "&cError: &4You did not create this banner.", "error-not-banner-maker"),
	ERROR_NO_BASE_COLOR("%%%%%PARSER_HERE%%%%%", "&cError: &4No base color was chosen.", "error-no-base-color"),
	ERROR_ALREADY_ON_PALLET("%%%%%PARSER_HERE%%%%%", "&cError: &4You are already on the color pallet.", "error-already-on-pallet"),
	ERROR_LAYER_CANNOT_BUMP("%%%%%PARSER_HERE%%%%%", "&cError: &4You cannot bump this layer.", "error-layer-cannot-bump"),
	ERROR_NO_NEXT_LAYER("%%%%%PARSER_HERE%%%%%", "&cError: &4No next layer exists.", "error-no-next-layer"),
	ERROR_NO_PREVIOUS_LAYER("%%%%%PARSER_HERE%%%%%", "&cError: &4No previous layer exists.", "error-no-previous-layer"),
	ERROR_INVALID_COMMAND_SENDER("%%%%%PARSER_HERE%%%%%", "&cError: &4Invalid command sender.", "error-invalid-command-sender"), //Take away {PLAYER} {DISPLAYNAME} {SENDER}
	ERROR_CANNOT_SEND_PM("%%%%%PARSER_HERE%%%%%", "&cError: &4Cannot send message.", "error-cannot-send-pm"); //Custom {GIVEN}
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
