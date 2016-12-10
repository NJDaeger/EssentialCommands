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
	 * @param player Enable or disable teleportation for this player
	 */
	void setTpToggled(Player player);
	
	/**
	 * Set a player to a specified group.
	 * @param player Player to change the group of.
	 * @param group The group to add the player to.
	 */
	void setGroup(Player player, String group);
	
	/**
	 * Set the nickname of a player.
	 * @param player Set the nickname of this player.
	 */
	void setNick(Player player);
	
	/**
	 * Set a player into flying mode or not.
	 * @param player Set this player into flying mode or not.
	 */
	void setFlying(Player player);
	
	/**
	 * Set a players gamemode.
	 * @param player Player whos gamemode to change.
	 * @param gamemode Gamemode to be set to.
	 */
	void setGamemode(Player player, String gamemode);
	
	/**
	 * Set a players fly speed.
	 * @param player Player whos fly speed to set.
	 * @param speed Speed value 0-10
	 */
	void setFlySpeed(Player player, int speed);
	
	/**
	 * Set a players walking speed.
	 * @param player Player whos walking speed to set.
	 * @param speed Speed value 0-10
	 */
	void setWalkingSpeed(Player player, int speed);
	
	/**
	 * Set a player to operator mode.
	 * @param player Player to set into operator
	 */
	void setOp(Player player);
	
	/**
	 * Set the players last location.
	 * @param player Set the last location of this player.
	 * <p>		- This is used when the player tp's or does the back command</p>
	 */
	void setLastLocation(Player player);
	
	/**
	 * Set the players logout location upon logging out.
	 * @param player Player to set logout location
	 */
	void setLogoutLocation(Player player);
	
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Checks if a player is muted.
	 * @param player Player to check.
	 * @return
	 */
	boolean isMuted(Player player);
	
	/**
	 * Checks if a player has spcialspy enabled.
	 * @param player Player to check
	 * @return
	 */
	boolean isSpying(Player player);
	
	/**
	 * Checks if a player has god mode enabled.
	 * @param player Player to check
	 * @return
	 */
	boolean isGod(Player player);
	
	/**
	 * Checks if a player has messaging enabled.
	 * @param player Player to check.
	 * @return
	 */
	boolean isMessageable(Player player);
	
	/**
	 * Checks if a player is AFK
	 * @param player Player to check.
	 * @return
	 */
	boolean isAfk(Player player);
	
	/**
	 * Checks if a player can be tp'ed
	 * @param player Player to check
	 * @return
	 */
	boolean isTpToggled(Player player);
	
	/**
	 * Checks what group the player is in
	 * @param player Player to check
	 * @return
	 */
	String getGroup(Player player);
	
	/**
	 * Get the nickname of a player.
	 * @param player Player to get nickname from.
	 * @return
	 */
	String getNick(Player player);
	
	/**
	 * Checks if a player is flying or not.
	 * @param player Player to check.
	 * @return
	 */
	boolean isFlying(Player player);
	
	/**
	 * Gets the gamemode of a player.
	 * @param player Player to check
	 * @return
	 */
	String getGamemode(Player player);
	
	/**
	 * Gets the fly speed of a player.
	 * @param player Player to check
	 * @return
	 */
	int getFlySpeed(Player player);
	
	/**
	 * Gets the walk speed of a player.
	 * @param player Player to check
	 * @return
	 */
	int getWalkingSpeed(Player player);
	
	/**
	 * Checks if the player is an operator
	 * @param player Player to check
	 * @return
	 */
	boolean isOp(Player player);
	
	/**
	 * The location values.
	 * @return
	 */
	Location getLocations();
}
