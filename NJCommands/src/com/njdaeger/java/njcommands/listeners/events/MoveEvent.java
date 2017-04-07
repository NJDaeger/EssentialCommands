package com.njdaeger.java.njcommands.listeners.events;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;

import com.njdaeger.java.Core;
import com.njdaeger.java.Groups;
import com.njdaeger.java.wrapper.User;

public class MoveEvent {

	public void whenAfk(PlayerMoveEvent e) {
		User user = Core.getUser(e.getPlayer().getName());
		if (user.isAfk()) {
			Location loc = e.getTo();
			Location a = Groups.afkloc.get(e.getPlayer().getName());
			if (a.distance(loc) >= 3) {
				user.setAfk(false);
				return;
			}
		}
		return;
	}

}
