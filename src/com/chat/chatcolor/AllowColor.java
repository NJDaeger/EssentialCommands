package com.chat.chatcolor;

import org.bukkit.entity.Player;

import com.njdaeger.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class AllowColor {
	
	public static String translateCodes(String message) {
		String format = ChatColor.translateAlternateColorCodes('&', message);
		return format;
	}
	public static boolean canColorChat(Player player) {
		if (player.hasPermission(Permission.ESS_ALL.getPermission()) || player.hasPermission(Permission.ESS_CHATCOLOR.getPermission()) || player.isOp()) {
			return true;
		}
		else return false;
	}
	
}
