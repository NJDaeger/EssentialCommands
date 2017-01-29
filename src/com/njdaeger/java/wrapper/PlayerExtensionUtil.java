package com.njdaeger.java.wrapper;

import org.bukkit.entity.Player;

import com.njdaeger.java.essentials.enums.Error;

public class PlayerExtensionUtil {

	private Player player;

	public PlayerExtensionUtil(Player player) {
		this.player = player;
	}

	public void sendError(Error error) {
		player.sendMessage(error.sendError());
	}

}
