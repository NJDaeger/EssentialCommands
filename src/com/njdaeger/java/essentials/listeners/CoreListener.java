package com.njdaeger.java.essentials.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.ServerCommandEvent;

import com.njdaeger.java.Core;
import com.njdaeger.java.essentials.listeners.events.ChatEvent;
import com.njdaeger.java.essentials.listeners.events.CommandEvent;
import com.njdaeger.java.essentials.listeners.events.InteractEvent;
import com.njdaeger.java.essentials.listeners.events.MoveEvent;

public class CoreListener implements Listener {

	private CommandEvent commandEvent = new CommandEvent();
	private ChatEvent chatEvent = new ChatEvent();
	private InteractEvent interactEvent = new InteractEvent();
	private MoveEvent moveEvent = new MoveEvent();

	public CoreListener(Core plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		interactEvent.whenAfk(e);
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		commandEvent.whenAfk(e);
		commandEvent.whenBlocked(e);
		commandEvent.checkReload(e);
		commandEvent.whenSpied(e);
	}

	@EventHandler
	public void onConsoleCommand(ServerCommandEvent e) {
		commandEvent.checkServerReload(e);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		moveEvent.whenAfk(e);
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		chatEvent.forAfk(e);
		chatEvent.forColor(e);
	}
}
