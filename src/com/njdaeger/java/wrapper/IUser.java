package com.njdaeger.java.wrapper;

import com.njdaeger.java.configuration.data.LastLocation;
import com.njdaeger.java.configuration.data.LogoutLocation;
import com.njdaeger.java.configuration.data.UserFile;
import com.njdaeger.java.essentials.enums.Error;

public interface IUser {

	/**
	 * CHecks if the User is muted or not.
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

	boolean isGod();

	void setGod(boolean value);

	boolean isMessageable();

	void setMessageable(boolean value);

	boolean isAfk();

	void setAfk(boolean value);

	boolean isTeleportable();

	void setTeleportable(boolean value);

	boolean isGroup(String group);

	String getGroup();

	void setGroup(String group);

	boolean hasNickname();

	boolean hasNickname(String nickname);

	String getNickname();

	void setNickname(String nickname);

	boolean isFlying();

	void setFlying(boolean value);

	boolean isFlyingMode();

	void setFlyingMode(boolean value);

	double getFlyingSpeed();

	void setFlyingSpeed(double value);

	double getWalkingSpeed();

	void setWalkingSpeed(double value);

	boolean isOp();

	void setOp(boolean value);

	Gamemode getGamemode();

	void setGamemode(Gamemode mode);

	boolean isBubbled();

	void setBubbled(boolean value);

	boolean isHidden();

	void setHidden(boolean value);

	void sudo(String command);

	void sendError(Error error);

	UserFile getUserFile();

	LastLocation getLast();

	LogoutLocation getLogout();

}
