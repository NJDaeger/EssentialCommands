package com.njdaeger.java.essentials.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import com.njdaeger.java.Core;
import com.njdaeger.java.wrapper.User;

public class PlayerLeaveListener implements Listener {

	Plugin plugin = Bukkit.getPluginManager().getPlugin("EssentialCommands");

	public PlayerLeaveListener(Core plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		User user = Core.getUser(e.getPlayer());
		user.logoutUpdate();
		Core.getOnlineUserMap().remove(user.getId());
		Core.getOnlineUsers().remove(user);
		/* 
		Player player = e.getPlayer();
		if (player.isBanned()) {
			Date expire = Bukkit.getServer().getBanList(Type.NAME).getBanEntry(player.getName()).getExpiration();
			if (expire.equals(null)) {
				return;
			}
			else {
				DateFormat format = new SimpleDateFormat("MM:dd:yy hh:mm:ss");
				Date date = new Date(System.currentTimeMillis());
				try {
					long expiration = format.parse(format.format(expire)).getTime();
					long current = format.parse(format.format(date)).getTime();
					if (expiration >= current) {
						e.setQuitMessage(null);
						return;
					}
					else {
						Bukkit.getServer().getBanList(Type.NAME).pardon(player.getName());
						return;
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
		/*
		if (PlayerConfig.getPlayerFile(e.getPlayer()) == null) {
			PlayerConfig.create(e.getPlayer());
			Bukkit.getLogger().info("Config file for " + e.getPlayer().getName() + " is being created.");
			return;
		}
		else {
			PlayerConfig.logoutUpdate(e.getPlayer());
			Bukkit.getLogger().info("Config file for " + e.getPlayer().getName() + " saved.");
			return;
		}
		*/
	}

}
