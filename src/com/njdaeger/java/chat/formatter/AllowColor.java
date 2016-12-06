package com.njdaeger.java.chat.formatter;

import org.bukkit.entity.Player;

import com.njdaeger.java.Holder;
import com.njdaeger.java.essentials.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class AllowColor {
	
	public static String translateCodes(String message) {
		String format = ChatColor.translateAlternateColorCodes('&', message);
		return format;
	}
	public static boolean canColorChat(Player player) {
		if (Holder.hasPermission(player, Permission.ESS_CHATCOLOR)) {
			return true;
		}
		else return false;
	}
	
}
