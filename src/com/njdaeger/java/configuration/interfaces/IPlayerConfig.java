package com.njdaeger.java.configuration.interfaces;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.Location;


/**
 * The main player configuration interface.
 * @author Noah
 *
 */
public interface IPlayerConfig {
	
	YamlConfiguration getPlayerFile(Player player);
	/**
	 * Creates a new player configuration
	 * @param player The player to create a new configuration for.
	 */
	void createConfig(Player player);
	
	/**
	 * Update the configuration statistics while the player logs out.
	 * @param player The player whos configuration to update.
	 */
	void logoutUpdate(Player player);
	
	/**
	 * Update the configuration statistics while the players logs in.
	 * @param player The player whos configuration to update.
	 */
	void loginUpdate(Player player);
	
	/**
	 * Mute the specified player.
	 * @param player Player to mute.
	 */
	void setMuted(Player player);
	
	/**
	 * Set a specified player to spying mode.
	 * @param player Player to set into spying mode.
	 */
	void setSpying(Player player);
	
	/**
	 * Set a specified player into god mode.
	 * @param player Player to set into god mode.
	 */
	void setGod(Player player);
	
	/**
	 * Set a player to be messageable or not.
	 * @param player Player to set messageable.
	 */
	void setMessageable(Player player);
	
	/**
	 * Set a specified player to afk.
	 * @param player Player to set afk.
	 */
	void setAfk(Player player);
	
	/**
	 * Set a player enabled or disabled for teleportation.
	 * @param player
	 */
	void setTpToggled(Player player);
	
	/**
	 * Set a player to a specified group.
	 * @param player Player to change the group of.
	 * @param group The group to add the player to.
	 */
	void setGroup(Player player, String group);
	
	void setNick(Player player);
	
	void setFlying(Player player);
	
	void setGamemode(Player player, String gamemode);
	
	void setFlySpeed(Player player, float speed);
	
	void setWalkingSpeed(Player player, float speed);
	
	void setOp(Player player);
	
	void setLastLocation(Player player);
	
	void setLogoutLocation(Player player);
	
	void setLoginLocation(Player player);
	
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
	
	boolean isMuted(Player player);
	
	boolean isSpying(Player player);
	
	boolean isGod(Player player);
	
	boolean isMessageable(Player player);
	
	boolean isAfk(Player player);
	
	boolean isTpToggled(Player player);
	
	String getGroup(Player player);
	
	String getNick(Player player);
	
	boolean isFlying(Player player);
	
	String getGamemode(Player player);
	
	int getFlySpeed(Player player);
	
	int getWalkingSpeed(Player player);
	
	boolean isOp(Player player);
	
	Location is(Player player);
}
