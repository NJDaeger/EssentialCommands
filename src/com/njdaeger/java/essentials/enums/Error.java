package com.njdaeger.java.essentials.enums;

import com.njdaeger.java.configuration.enums.Messages;

import net.md_5.bungee.api.ChatColor;

public enum Error {

	NOT_ENOUGH_ARGS("Not enough arguments provided."),
	TOO_MANY_ARGS("Too many arguments provided."),
	NO_PERMISSION(Messages.ERROR_NO_PERMISSION.getMessage()),
	UNKNOWN_PLAYER("Player provided does not exist."),
	PLAYER_ONLY("This action is restricted to players only."),
	UNKNOWN_GAMEMODE("Gamemode provided does not exist."),
	INPUT_NOT_NUM("Input provided must be an integer."),
	UNKNOWN_ITEM("Input provided is an unknown item."),
	INVENTORY_IS_FULL("Targets inventory is full."),
	NO_ITEM_IN_HAND("Invalid item in hand"),
	ITEM_ALREADY_MAXED("Item currently held is already maxed."),
	NICKNAME_TOO_LONG("The nickname given is too long."),
	PLAYER_IS_OPPED("Cannot perform action. Player is opped."),
	INPUT_TOO_LARGE("The input given is too large."),
	INPUT_TOO_SMALL("The input given is too small."),
	UNKNOWN_WALK_TYPE("The given walking type does not exist."),
	MESSAGING_DISABLED("You have messaging disabled."),
	MESSAGING_DISABLED_OTHER("The target has messaging disabled."),
	CANNOT_BAN_TARGET("Target cannot be banned."),
	UNKNOWN_BAN_TYPE("Ban type given does not exist."),
	BAN_WRONG_FORMAT("The issued ban is in the wrong format."),
	PLAYER_NOT_BANNED("Target is not banned."),
	TARGET_NOT_SIGN("Target block is not a sign."),
	LINE_NUMBER_INVALID("The line value given is invalid."),
	CANNOT_BE_HAT("The item given cannot be a hat."),
	NO_PLAYERS_ONLINE("There are no players currently online."),
	ADD_HOME_NAME("You must specify a name for your home."),
	HOME_EXISTS("The home given already exists."),
	HOME_NOTEXIST("The home given does not exist."),
	WORLD_NOT_FOUND("The world cannot be found."),
	NO_HOMES("No homes exist for the target."),
	DATABASE_NOT_FOUND("Database could not be found."),
	WARP_EXISTS("The warp specified already exists."),
	WARP_NOTEXISTS("Warp could not be found."),
	NO_BANNER_IN_HAND("You don't have a banner in your main hand."),
	NOT_BANNER_MAKER("You did not create this banner."),
	NO_BASE_COLOR("No base color was chosen."),
	ALREADY_ON_PALLET("You are already on the color pallet."),
	LAYER_CANNOT_BUMP("You cannot bump this layer."),
	NO_NEXT_LAYER("No next layer exists."),
	NO_PREVIOUS_LAYER("No previous layer exists."),
	INVALID_COMMAND_SENDER("Invalid command sender."),
	CANNOT_SEND_PM("Cannot send message."),
	CONSOLE_ONLY("This action is restricted to console only."),
	BLOCKED_COMMAND(Messages.BLACKLIST_COMMAND_MSG_PLR.getMessage()),
	CANNOT_BURN_TARGET("Target cannot be burned."),
	CANNOT_CHANGE_TO_TIME("Time cannot be changed to that."),
	CANNOT_CHANGE_WEATHER("Weather cannot be changed to that.");

	public final String error;

	Error(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public String sendError() {
		return ChatColor.RED + "Error: " + ChatColor.DARK_RED + error;
	}

	public static String throwError(String error) {
		return ChatColor.RED + error;
	}
}
