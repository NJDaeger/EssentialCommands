package com.njdaeger.java.configuration.parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class BlacklistedCommand {

	public String parse(String errormessage, Player player, String command) {
		DateFormat f = new SimpleDateFormat("MM:dd:yyyy");
		DateFormat g = new SimpleDateFormat("HH:mm:ss");
		Date d = new Date();
		String a = errormessage;
		if (a.contains("{COMMAND}")) {
			a.replace("{COMMAND}", command);
		}
		if (a.contains("{XPOS}")) {
			a.replace("{XPOS}", String.valueOf(player.getLocation().getBlockX()));
		}
		if (a.contains("{YPOS}")) {
			a.replace("{YPOS}", String.valueOf(player.getLocation().getBlockY()));
		}
		if (a.contains("{ZPOS}")) {
			a.replace("{ZPOS}", String.valueOf(player.getLocation().getBlockZ()));
		}
		if (a.contains("{PLAYER}")) {
			a.replace("{PLAYER}", player.getName());
		}
		if (a.contains("{DISPLAYNAME}")) {
			a.replace("{DISPLAYNAME}", player.getDisplayName());
		}
		if (a.contains("{WORLD}")) {
			a.replace("{WORLD}", player.getWorld().getName());
		}
		if (a.contains("{DATE}")) {
			a.replace("{DATE}", f.format(d));
		}
		if (a.contains("{TIME}")) {
			a.replace("{TIME}", g.format(d));
		}
		return ChatColor.translateAlternateColorCodes('&', a);
	}
}
