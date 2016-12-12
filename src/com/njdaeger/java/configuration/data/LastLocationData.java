package com.njdaeger.java.configuration.data;

import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.ILastValues;

public class LastLocationData extends PlayerConfig implements ILastValues{
	
	public int getX() {
		return PlayerConfig.getConfig(player).getValue().getInt(PlayerPaths.LAST_X.getPath());
	}

	public int getY() {
		return PlayerConfig.getConfig(player).getValue().getInt(PlayerPaths.LAST_Y.getPath());
	}

	public int getZ() {
		return PlayerConfig.getConfig(player).getValue().getInt(PlayerPaths.LAST_Z.getPath());
	}

	public int getYaw() {
		return PlayerConfig.getConfig(player).getValue().getInt(PlayerPaths.LAST_YAW.getPath());
	}

	public int getPitch() {
		return PlayerConfig.getConfig(player).getValue().getInt(PlayerPaths.LAST_PITCH.getPath());
	}

	public String getWorld() {
		return PlayerConfig.getConfig(player).getValue().getString(PlayerPaths.LAST_WORLD.getPath());
	}
	
}
