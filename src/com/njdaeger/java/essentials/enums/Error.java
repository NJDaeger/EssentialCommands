package com.njdaeger.java.essentials.enums;

import net.md_5.bungee.api.ChatColor;

public enum Error{
	
	NOT_ENOUGH_ARGS {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Not enough arguments.";
			return error;
		}
	},
	TOO_MANY_ARGS {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Too many arguments.";
			return error;
		}
	},
	NO_PERMISSION {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "You don't have permission.";
			return error;
		}
	},
	UNKNOWN_PLAYER {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Unknown player.";
			return error;
		}
	},
	PLAYER_ONLY {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Only players can do that.";
			return error;
		}
	},
	UNKNOWN_GAMEMODE {

		@Override
		public String sendError() {
			String error = ChatColor.RED + "Unknown gamemode.";
			return error;
		}
		
	},
	ALREADY_GAMEMODE_P { // player

		@Override
		public String sendError() {
			String error = ChatColor.RED + "You are already in that gamemode.";
			return error;
		}
		
	},
	ALREADY_GAMEMODE_C { //console

		@Override
		public String sendError() {
			String error = ChatColor.RED + "That player is already in that gamemode.";
			return error;
		}
		
	},
	INPUT_NOT_NUM {

		@Override
		public String sendError() {
			String error = ChatColor.RED + "Input has to be an integer.";
			return error;
		}
		
	},
	UNKNOWN_ITEM {

		@Override
		public String sendError() {
			String error = ChatColor.RED + "Unknown item.";
			return error;
		}
		
	},
	INVENTORY_IS_FULL_P {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Your inventory is full!";
			return error;
		}
	},
	INVENTORY_IS_FULL_PO {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "That player's inventory is full!";
			return error;
		}
	},
	INVENTORY_IS_FULL_C {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "That player's inventory is full!";
			return error;
		}
	},
	NICKNAME_TOO_LONG {

		@Override
		public String sendError() {
			String error = ChatColor.RED + "The nickname given is too long.";
			return error;
		}
		
	},
	PLAYER_IS_OPPED {
		
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Cannot execute command. Target is opped.";
			return error;
		}
		
	},
	INPUT_TOO_LARGE {

		@Override
		public String sendError() {
			String error = ChatColor.RED + "The input used is too large.";
			return error;
		}
		
	},
	UNKNOWN_WALK_TYPE {

		@Override
		public String sendError() {
			String error = ChatColor.RED + "That walking type is unknown.";
			return error;
		}
		
	},
	INPUT_TOO_SMALL {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "The input used is too small.";
			return error;
		}
	},
	MESSAGING_DISABLED_TARGET {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "The target specified has their messaging disabled.";
			return error;
		}
	},
	MESSAGING_DISABLED_SENDER {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "You have your messaging disabled.";
			return error;
		}
	},
	CANNOT_BAN_TARGET {

		@Override
		public String sendError() {
			String error = ChatColor.RED + "Player cannot be banned.";
			return error;
		}
		
	},
	UNKNOWN_BAN_TYPE {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Unknown ban type.";
			return error;
		}
	},
	BAN_WRONG_FORMAT {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Insert a \":\" between the time and the ban type.";
			return error;
		}
	},
	PLAYER_NOT_BANNED {

		@Override
		public String sendError() {
			String error = ChatColor.RED + "Target player is not banned.";
			return error;
		}
		
	},
	TARGET_NOT_SIGN {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Target block is not a sign.";
			return error;
		}
	},
	LINE_NUMBER_INVALID {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "The line number you entered is invalid.";
			return error;
		}
	},
	CANNOT_BE_HAT {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Item cannot be a hat.";
			return error;
		}
	},
	NO_PLAYERS_ONLINE {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "There are no players online.";
			return error;
		}
	},
	ADD_HOME_NAME {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "You must name your home.";
			return error;
		}
	},
	HOME_EXISTS {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Home already exists.";
			return error;
		}
	},
	HOME_NOTEXIST {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Home does not exist.";
			return error;
		}
	},
	WORLD_NOT_FOUND {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "World cannot be found.";
			return error;
		}
	},
	NO_HOMES {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "No homes exist for target.";
			return error;
		}
	},
	DATABASE_NOT_FOUND {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Database could not be found.";
			return error;
		}
	},
	WARP_EXISTS {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Warp already exists.";
			return error;
		}
	},
	WARP_NOTEXISTS {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Warp could not be found.";
			return error;
		}
	},
	NO_BANNER_IN_HAND {

		@Override
		public String sendError() {
			String error = ChatColor.RED + "No banner in main hand.";
			return error;
		}
		
	},
	NOT_BANNER_MAKER {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "You did not create this banner.";
			return error;
		}
	},
	NO_BASE_COLOR {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "No base color chosen.";
			return error;
		}
	},
	ALREADY_ON_PALLET {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "You are on the color pallet.";
			return error;
		}
	},
	LAYER_CANNOT_BUMP {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "You cannot bump this layer.";
			return error;
		}
	},
	NO_NEXT_LAYER {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "No next layer.";
			return error;
		}
	},
	NO_PREVIOUS_LAYER {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "No previous layer.";
			return error;
		}
	},
	ON_CURRENT_LAYER {;
		@Override
		public String sendError() {
			String error = ChatColor.RED + "No previous layer.";
			return error;
		}
	},
	INVALID_COMMAND_SENDER{
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Invalid command sender.";
			return error;
		}
	},
	CANNOT_SEND_PM {
		@Override
		public String sendError() {
			String error = ChatColor.RED + "Cannot send message.";
			return error;
		}
	};
	
	public abstract String sendError();
	
}
