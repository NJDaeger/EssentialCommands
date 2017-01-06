package com.njdaeger.java.essentials.listeners.events;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;

import com.njdaeger.java.Groups;
import com.njdaeger.java.configuration.controllers.PlayerConfig;

public class MoveEvent {

	public void whenAfk(PlayerMoveEvent e) {
		Location loc = e.getTo();
		Location a = Groups.afkloc.get(e.getPlayer().getName());
		if (PlayerConfig.getConfig(e.getPlayer()).isAfk() == true) {
			if (a.distance(loc) >= 3) {
				PlayerConfig.getConfig(e.getPlayer()).setAfk();
				return;
			}
		}
		return;
	}

}
