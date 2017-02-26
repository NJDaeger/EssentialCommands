package com.njdaeger.java.essentials.listeners.events;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;

import com.njdaeger.java.Core;
import com.njdaeger.java.Groups;
import com.njdaeger.java.wrapper.User;

public class MoveEvent {

	public void whenAfk(PlayerMoveEvent e) {
		Location loc = e.getTo();
		Location a = Groups.afkloc.get(e.getPlayer().getName());
		User user = Core.getUser(e.getPlayer());
		if (user.isAfk()) {
			if (a.distance(loc) >= 3) {
				user.setAfk(!user.isAfk());
				return;
			}
		}
		return;
	}

}
