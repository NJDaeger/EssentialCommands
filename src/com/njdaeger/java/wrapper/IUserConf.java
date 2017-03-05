package com.njdaeger.java.wrapper;

import java.util.UUID;

import com.njdaeger.java.configuration.data.LastLocation;
import com.njdaeger.java.configuration.data.LogoutLocation;
import com.njdaeger.java.configuration.data.UserFile;
import com.njdaeger.java.configuration.interfaces.IOfflineHome;

public interface IUserConf {

	/**
	 * Checks if the User is muted or not.
	 * 
	 * @return True if muted, false otherwise.
	 */
	boolean isMuted();

	/**
	 * Sets a user muted or not.
	 * 
	 * @param value True will mute them, false will unmute.
	 */
	void setMuted(boolean value);

	/**
	 * Checks if the user is using socialspy.
	 * 
	 * @return True if using it, false otherwise.
	 */
	boolean isSpying();

	/**
	 * Turns a users socialspy on or off.
	 * 
	 * @param value True enables it, false disables it.
	 */
	void setSpying(boolean value);

	/**
	 * Checks if a user is in god mode or not.
	 * 
	 * @return True if is in god, false otherwise.
	 */
	boolean isGod();

	/**
	 * Sets a user into god mode.
	 * 
	 * @param value True enables it false disables.
	 */
	void setGod(boolean value);

	/**
	 * Checks if a user can be messaged or not.
	 * 
	 * @return True if messageable, false otherwise.
	 */
	boolean isMessageable();

	/**
	 * Sets a user to messageable or not.
	 * 
	 * @param value True enables messaging, false disables.
	 */
	void setMessageable(boolean value);

	/**
	 * Checks if a user is afk or not.
	 * 
	 * @return True if is afk, false otherwise.
	 */
	boolean isAfk();

	/**
	 * Sets a user afk or not.
	 * 
	 * @param value True enables it, false disables it.
	 */
	void setAfk(boolean value);

	/**
	 * Checks if a player can be teleported.
	 * 
	 * @return True if can be teleported, false otherwise.
	 */
	boolean isTeleportable();

	/**
	 * Sets teleportation for a user to enabled or not.
	 * 
	 * @param value True enables it, false disables it.
	 */
	void setTeleportable(boolean value);

	/**
	 * Checks if a user is in a specific group.
	 * 
	 * @param group Group to look for user in.
	 * @return True if the user is in the group, false otherwise.
	 */
	boolean isGroup(String group);

	/**
	 * Gets the name of the group the user is in.
	 * 
	 * @return Returns the groupname, if the player isn't in a group it returns
	 *         null.
	 */
	String getGroup();

	/**
	 * Sets the group of a user.
	 * 
	 * @param group The group to add the user too.
	 */
	void setGroup(String group);

	/**
	 * Checks if the user has a nickname.
	 * 
	 * @return True if user has a nickname, false otherwise.
	 */
	boolean hasNickname();

	/**
	 * Checks if the user has a specified nickname.
	 * 
	 * @param nickname The nickname to check the user for.
	 * @return True if the user's nickname matches, false otherwise.
	 */
	boolean hasNickname(String nickname);

	/**
	 * Gets the nickname of the user.
	 * 
	 * @return Returns the nickname. If the user doesn't have a nickname then it
	 *         will return its username.
	 */
	String getNickname();

	/**
	 * Sets the nickname of a user.
	 * 
	 * @param nickname The nickname to set the user to.
	 */
	void setNickname(String nickname);

	/**
	 * Checks if the user is flying or not.
	 * 
	 * @return True if the user is flying, false otherwise.
	 */
	boolean isFlying();

	/**
	 * Sets the user to flying.
	 * 
	 * @param value True to make the user fly, false otherwise.
	 */
	void setFlying(boolean value);

	/**
	 * Gets the fly speed of a user.
	 * 
	 * @return The fly speed.
	 */
	double getFlyingSpeed();

	/**
	 * Sets the fly speed of a user.
	 * 
	 * @param value Value 0-10
	 */
	void setFlyingSpeed(double value);

	/**
	 * Gets the walking speed of a user
	 * 
	 * @return The walking speed.
	 */
	int getWalkingSpeed();

	/**
	 * Sets the walking speed of a user.
	 * 
	 * @param value Value 0-10
	 */
	void setWalkingSpeed(double value);

	/**
	 * Checks if the user is Opped.
	 * 
	 * @return True if is opped, false otherwise.
	 */
	boolean isOp();

	/**
	 * Sets the user to opped mode or not.
	 * 
	 * @param value True ops the user, false deops.
	 */
	void setOp(boolean value);

	/**
	 * Get the gamemode of the user.
	 * 
	 * @return The user's gamemode.
	 */
	Gamemode getGamemode();

	/**
	 * Checks if the user is on a specific gamemode.
	 * 
	 * @param mode The gamemode to check for.
	 * @return True if gamemode matches the user's. False otherwise.
	 */
	boolean isGamemode(Gamemode mode);

	/**
	 * Sets the gamemode of the user.
	 * 
	 * @param mode The gamemode to set the user to.
	 */
	void setGamemode(Gamemode mode);

	/**
	 * Set the gamemode of the user. (via string)
	 * 
	 * @param mode The gamemode to set the user to.
	 */
	void setGamemode(String mode);

	/**
	 * Checks if the user is in bubble mode.
	 * 
	 * @return True if is bubbled, false otherwise.
	 */
	boolean isBubbled();

	/**
	 * Sets the user into bubble mode.
	 * 
	 * @param value True enables bubbling, false disables it.
	 */
	void setBubbled(boolean value);

	/**
	 * Checks if the user is hidden or not.
	 * 
	 * @return True if is hidden, false otherwise.
	 */
	boolean isHidden();

	/**
	 * Sets the user into hidden mode.
	 * 
	 * @param value True hides the user, false unhides the user.
	 */
	void setHidden(boolean value);

	/**
	 * Sets the logout time to the current time.
	 */
	void setLogoutTime();

	/**
	 * Gets the logout time of the user.
	 * 
	 * @return The users logout time.
	 */
	long getLogoutTime();

	/**
	 * Sets the login time to the current time.
	 */
	void setLoginTime();

	/**
	 * Gets the login time of the user.
	 * 
	 * @return The users login time.
	 */
	long getLoginTime();

	/**
	 * Gets a home from a user.
	 * 
	 * @param home The home to get.
	 * @return Returns the home if it exists.
	 */
	IOfflineHome getHome(String home);

	/**
	 * Get the users yml file.
	 * 
	 * @return The user yml file.
	 */
	UserFile getUserFile();

	/**
	 * Gets the last location data of a user.
	 * 
	 * @return The user's last location.
	 */
	LastLocation getLast();

	/**
	 * Gets the logout location of the user.
	 * 
	 * @return The users logout location information.
	 */
	LogoutLocation getLogout();

	/**
	 * Gets the name of the user.
	 * 
	 * @return The users in game name.
	 */
	String getName();

	/**
	 * Gets the users UUID
	 * 
	 * @return The users UUID
	 */
	UUID getId();
}
