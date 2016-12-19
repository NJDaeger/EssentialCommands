package com.njdaeger.java.essentials.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import com.njdaeger.java.Core;
import com.njdaeger.java.Groups;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.essentials.commands.messaging.BroadcastCommand;
import com.njdaeger.java.essentials.commands.messaging.MessageCommand;
import com.njdaeger.java.essentials.commands.messaging.ReplyCommand;
import com.njdaeger.java.essentials.commands.player.AfkCommand;
import com.njdaeger.java.essentials.commands.player.VanishCommand;
import com.njdaeger.java.essentials.exceptions.UnknownActionException;

public class AfkListener implements Listener{
	Plugin plugin = Bukkit.getPluginManager().getPlugin("EssentialCommands");
	public AfkListener(Core plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onMove(PlayerInteractEvent e) throws UnknownActionException {
		if (PlayerConfig.getConfig(e.getPlayer()).isAfk() == true) {
			if (
					e.getAction() == Action.LEFT_CLICK_AIR ||
					e.getAction() == Action.LEFT_CLICK_BLOCK ||
					e.getAction() == Action.PHYSICAL ||
					e.getAction() == Action.RIGHT_CLICK_AIR ||
					e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				PlayerConfig.getConfig(e.getPlayer()).setAfk();
				return;
			}
			else throw new UnknownActionException();
		}
		return;
	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		String message = e.getMessage();
		if (PlayerConfig.getConfig(e.getPlayer()).isAfk() == true) {
			if (
					new AfkCommand().getAliases().contains(message) ||
					new MessageCommand().getAliases().contains(message) ||
					new ReplyCommand().getAliases().contains(message) ||
					new BroadcastCommand().getAliases().contains(message) ||
					new VanishCommand().getAliases().contains(message)) {	
			}
			else {
				PlayerConfig.getConfig(e.getPlayer()).setAfk();
				return;
			}
		}
		return;
	}
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Location loc = e.getTo();
		Location afk = Groups.afkloc.getOrDefault(e.getPlayer().getName(), e.getPlayer().getLocation());
		if (PlayerConfig.getConfig(e.getPlayer()).isAfk() == true){
			if (afk.distance(loc) >= 3) {
				PlayerConfig.getConfig(e.getPlayer()).setAfk();
				return;
			}
		}
		return;
	}
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (PlayerConfig.getConfig(e.getPlayer()).isAfk() == true) {
			PlayerConfig.getConfig(e.getPlayer()).setAfk();
		}
		return;
	}
}
