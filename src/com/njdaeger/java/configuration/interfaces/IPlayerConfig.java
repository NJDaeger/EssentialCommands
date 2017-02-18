package com.njdaeger.java.configuration.interfaces;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.Location;

/**
 * The main player configuration interface.
 * 
 * @author Noah
 *
 */
public interface IPlayerConfig {

	/**
	 * Update the configuration statistics while the player logs out.
	 */
	void logoutUpdate();

	/**
	 * Update the configuration statistics while the players logs in.
	 */
	void loginUpdate();

	/**
	 * Mute the specified player.
	 */
	void setMuted();

	/**
	 * Set a specified player to spying mode.
	 */
	void setSpying();

	/**
	 * Set a specified player into god mode.
	 */
	void setGod();

	/**
	 * Set a player to be messageable or not.
	 */
	void setMessageable();

	/**
	 * Set a specified player to afk.
	 */
	void setAfk();

	/**
	 * Set a player enabled or disabled for teleportation.
	 */
	void setTpToggled();

	/**
	 * Set a player to a specified group.
	 * 
	 * @param group The group to add the player to.
	 */
	void setGroup(String group);

	/**
	 * Set the nickname of a player.
	 * 
	 * @param nickname Set the nickname of this player.
	 */
	void setNick(String nickname);

	/**
	 * Set a player into flying mode or not.
	 */
	void setFlying();

	/**
	 * Set a players gamemode.
	 * 
	 * @param gamemode Gamemode to be set to.
	 */
	void setGamemode(String gamemode);

	/**
	 * Set a players fly speed.
	 * 
	 * @param speed Speed value 0-10
	 */
	void setFlySpeed(double speed);

	/**
	 * Set a players walking speed.
	 * 
	 * @param speed Speed value 0-10
	 */
	void setWalkingSpeed(double speed);

	/**
	 * Set the player to operator.
	 */
	void setOp();

	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Checks if a player is muted.
	 * 
	 * @return
	 */
	boolean isMuted();

	/**
	 * Checks if a player has spcialspy enabled.
	 * 
	 * @return
	 */
	boolean isSpying();

	/**
	 * Checks if a player has god mode enabled.
	 * 
	 * @return
	 */
	boolean isGod();

	/**
	 * Checks if a player has messaging enabled.
	 * 
	 * @return
	 */
	boolean isMessageable();

	/**
	 * Checks if a player is AFK
	 * 
	 * @return
	 */
	boolean isAfk();

	/**
	 * Checks if a player can be tp'ed
	 * 
	 * @return
	 */
	boolean isTpToggled();

	/**
	 * Checks if a player is hidden or not.
	 * 
	 * @return True if hidden, false otherwise.
	 */
	boolean isHidden();

	/**
	 * Sets the player hidden or not. If they are hidden it un-hides them and
	 * vice versa.
	 */
	void setHidden();

	/**
	 * Checks what group the player is in
	 * 
	 * @return
	 */
	String getGroup();

	/**
	 * Get the nickname of a player.
	 * 
	 * @return
	 */
	String getNick();

	/**
	 * Checks if a player is flying or not.
	 * 
	 * @return
	 */
	boolean isFlying();

	/**
	 * Gets the gamemode of a player.
	 * 
	 * @return
	 */
	String getGamemode();

	/**
	 * Gets the fly speed of a player.
	 * 
	 * @return
	 */
	int getFlySpeed();

	/**
	 * Gets the walk speed of a player.
	 * 
	 * @return
	 */
	int getWalkingSpeed();

	/**
	 * Checks if the player is an operator
	 * 
	 * @return
	 */
	boolean isOp();

	/**
	 * The location values.
	 * 
	 * @return
	 */
	Location getLocations();

	/**
	 * Checks if the player is in bubble mode.
	 * 
	 * @return
	 */
	boolean isBubbleMode();

	/**
	 * Sets the player in bubble mode if he isn't, and vice versa.
	 */
	void setBubbleMode();

	/**
	 * Gets the player the configuration is currently referencing to.
	 * 
	 * @return Player the configuration is pointing to.
	 */
	Player getPlayer();

}
