package com.njdaeger.java.njcommands.listeners.events;

import org.bukkit.event.player.PlayerInteractEvent;

import com.njdaeger.java.Core;
import com.njdaeger.java.wrapper.User;

public class InteractEvent {

	public void whenAfk(PlayerInteractEvent e) {
		User user = Core.getUser(e.getPlayer());
		if (user.isAfk()) {
			user.setAfk(false);
		}
		return;
	}

}
