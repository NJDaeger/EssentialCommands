package com.njdaeger.java.configuration.data;

import com.njdaeger.java.configuration.Location;
import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.ILastValues;

public class LogoutLocationData extends Location implements ILastValues{
	
	public int getX() {
		return PlayerConfig.getConfig(player).getValue().getInt(PlayerPaths.LOGOUT_X.getPath());
	}

	public int getY() {
		return PlayerConfig.getConfig(player).getValue().getInt(PlayerPaths.LOGOUT_Y.getPath());
	}

	public int getZ() {
		return PlayerConfig.getConfig(player).getValue().getInt(PlayerPaths.LOGOUT_Z.getPath());
	}

	public int getYaw() {
		return PlayerConfig.getConfig(player).getValue().getInt(PlayerPaths.LOGOUT_YAW.getPath());
	}

	public int getPitch() {
		return PlayerConfig.getConfig(player).getValue().getInt(PlayerPaths.LOGOUT_PITCH.getPath());
	}

	public String getWorld() {
		return PlayerConfig.getConfig(player).getValue().getString(PlayerPaths.LOGOUT_WORLD.getPath());
	}
}
