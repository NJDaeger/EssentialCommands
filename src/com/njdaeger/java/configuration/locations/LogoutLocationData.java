package com.njdaeger.java.configuration.locations;

import com.njdaeger.java.configuration.LogoutLocation;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.ILastValues;

public class LogoutLocationData extends LogoutLocation implements ILastValues{
	
	public int getX() {
		return getPlayerFile(player).getInt(PlayerPaths.LOGOUT_X.getPath());
	}

	public int getY() {
		return getPlayerFile(player).getInt(PlayerPaths.LOGOUT_Y.getPath());
	}

	public int getZ() {
		return getPlayerFile(player).getInt(PlayerPaths.LOGOUT_Z.getPath());
	}

	public int getYaw() {
		return getPlayerFile(player).getInt(PlayerPaths.LOGOUT_YAW.getPath());
	}

	public int getPitch() {
		return getPlayerFile(player).getInt(PlayerPaths.LOGOUT_PITCH.getPath());
	}

	public String getWorld() {
		return getPlayerFile(player).getString(PlayerPaths.LOGOUT_WORLD.getPath());
	}
}
