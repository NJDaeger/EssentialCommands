package com.njdaeger.java.wrapper;

import com.njdaeger.java.configuration.data.LastLocation;
import com.njdaeger.java.configuration.data.LogoutLocation;
import com.njdaeger.java.configuration.data.UserFile;

public interface IUser {

	boolean isMuted();

	void setMuted(boolean value);

	boolean isSpying();

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

	UserFile getUserFile();

	LastLocation getLast();

	LogoutLocation getLogout();

}
