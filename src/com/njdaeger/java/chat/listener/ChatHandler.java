package com.njdaeger.java.chat.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import com.njdaeger.java.Core;
import com.njdaeger.java.chat.formatter.AllowColor;

public class ChatHandler implements Listener{
	
	Plugin plugin = Bukkit.getPluginManager().getPlugin("EssentialCommands");
	public ChatHandler(Core plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onChat(AsyncPlayerChatEvent e) {
		if (AllowColor.canColorChat(e.getPlayer()) == true) {
			e.setMessage(AllowColor.translateCodes(e.getMessage()));
		}
		else return;
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void format(AsyncPlayerChatEvent e) {
		/*
		 * 
		 * Make a line in the regular config file called ChatFormat
		 * 
		 * Check for color codes 
		 * Check for the following:
		 * 		- Group
		 * 		- Username
		 * 		- Displayname
		 * 		- World
		 * 		- Health
		 *		- Hunger
		 *		- Worldshort
		 * 
		 */
	}
}
