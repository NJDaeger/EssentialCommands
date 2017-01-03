package com.njdaeger.java.essentials.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import com.njdaeger.java.Core;
import com.njdaeger.java.essentials.listeners.events.ChatEvent;
import com.njdaeger.java.essentials.listeners.events.CommandEvent;
import com.njdaeger.java.essentials.listeners.events.InteractEvent;
import com.njdaeger.java.essentials.listeners.events.MoveEvent;

public class CoreListener implements Listener {

	Plugin plugin = Bukkit.getPluginManager().getPlugin("EssentialCommands");

	public CoreListener(Core plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		new InteractEvent().whenAfk(e);
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		new CommandEvent().whenAfk(e);
		new CommandEvent().whenBlocked(e);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		new MoveEvent().whenAfk(e);
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		new ChatEvent().forAfk(e);
		new ChatEvent().forColor(e);
	}
}
