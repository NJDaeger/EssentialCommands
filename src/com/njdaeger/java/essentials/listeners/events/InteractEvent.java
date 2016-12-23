package com.njdaeger.java.essentials.listeners.events;

import org.bukkit.event.player.PlayerInteractEvent;

import com.njdaeger.java.configuration.controllers.PlayerConfig;

public class InteractEvent {
	
	public void whenAfk(PlayerInteractEvent e) {
		if (PlayerConfig.getConfig(e.getPlayer()).isAfk() == true) {
			PlayerConfig.getConfig(e.getPlayer()).setAfk();
		}
		return;
	}
	
}
