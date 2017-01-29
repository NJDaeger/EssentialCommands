package com.njdaeger.java.wrapper;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.data.PlayerConfigData;

public interface EssPlayer extends Player {

	/**
	 * Gets the configuration of a player.
	 * 
	 * @return
	 */
	default PlayerConfigData getConfig() {
		return PlayerConfig.getConfig(getPlayer());
	}

	/**
	 * Transforms the EssPlayer into a Bukkit Player.
	 * 
	 * @return
	 */
	default Player transform() {
		return getPlayer();
	}

}
