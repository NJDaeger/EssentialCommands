package com.njdaeger.essentials.bannermanager.utils;

import net.md_5.bungee.api.ChatColor;

public enum Errors {
	
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
	};
	
	public abstract String sendError();
}
