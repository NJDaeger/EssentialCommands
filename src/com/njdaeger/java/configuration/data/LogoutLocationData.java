package com.njdaeger.java.configuration.data;

import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.IValues;

public class LogoutLocationData extends PlayerConfig implements IValues {

	@Override
	public double getX() {
		return PlayerConfig.getConfig(player).getValue().getDouble(PlayerPaths.LOGOUT_X.getPath());
	}

	@Override
	public double getY() {
		return PlayerConfig.getConfig(player).getValue().getDouble(PlayerPaths.LOGOUT_Y.getPath());
	}

	@Override
	public double getZ() {
		return PlayerConfig.getConfig(player).getValue().getDouble(PlayerPaths.LOGOUT_Z.getPath());
	}

	@Override
	public int getYaw() {
		return PlayerConfig.getConfig(player).getValue().getInt(PlayerPaths.LOGOUT_YAW.getPath());
	}

	@Override
	public int getPitch() {
		return PlayerConfig.getConfig(player).getValue().getInt(PlayerPaths.LOGOUT_PITCH.getPath());
	}

	@Override
	public String getWorld() {
		return PlayerConfig.getConfig(player).getValue().getString(PlayerPaths.LOGOUT_WORLD.getPath());
	}
}
