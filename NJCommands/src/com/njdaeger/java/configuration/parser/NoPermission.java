package com.njdaeger.java.configuration.parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.entity.Player;

import com.njdaeger.java.enums.Permission;

import net.md_5.bungee.api.ChatColor;

public class NoPermission {

	public String parse(String errormessage, Player player, String rank, Permission... permissions) {
		DateFormat f = new SimpleDateFormat("MM:dd:yyyy");
		DateFormat g = new SimpleDateFormat("HH:mm:ss");
		Date d = new Date();
		String a = errormessage;
		if (a.contains("{NEEDSRANK}")) {
			a = a.replace("{NEEDSRANK}", rank);
		}
		if (a.contains("{PERMISSIONS}")) {
			List<String> t = new ArrayList<String>();
			int i = 0;
			for (Permission perm : permissions) {
				t.add(perm.getPermission());
				i++;
				if (i > t.size()) {
					t.clear();
					break;
				}
			}
			a = a.replace("{PERMISSIONS}", t.toString().replace("[", "").replace("]", ""));
		}
		if (a.contains("{PLAYER}")) {
			a = a.replace("{PLAYER}", player.getName());
		}
		if (a.contains("{DISPLAYNAME}")) {
			a = a.replace("{DISPLAYNAME}", player.getDisplayName());
		}
		if (a.contains("{TIME}")) {
			a = a.replace("{TIME}", g.format(d));
		}
		if (a.contains("{DATE}")) {
			a = a.replace("{DATE}", f.format(d));
		}
		if (a.contains("{WORLD}")) {
			a = a.replace("{WORLD}", player.getWorld().getName());
		}
		if (a.contains("{XPOS}")) {
			a = a.replace("{XPOS}", String.valueOf(player.getLocation().getBlockX()));
		}
		if (a.contains("{YPOS}")) {
			a = a.replace("{YPOS}", String.valueOf(player.getLocation().getBlockY()));
		}
		if (a.contains("{ZPOS}")) {
			a = a.replace("{ZPOS}", String.valueOf(player.getLocation().getBlockZ()));
		}
		return ChatColor.translateAlternateColorCodes('&', a);
	}

}
