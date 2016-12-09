package com.njdaeger.java.configuration.locations;

import com.njdaeger.java.configuration.LastLocation;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.ILastValues;

public class LastLocationData extends LastLocation implements ILastValues {
	
	public int getX() {
		return getPlayerFile(player).getInt(PlayerPaths.LAST_X.getPath());
	}

	public int getY() {
		return getPlayerFile(player).getInt(PlayerPaths.LAST_Y.getPath());
	}

	public int getZ() {
		return getPlayerFile(player).getInt(PlayerPaths.LAST_Z.getPath());
	}

	public int getYaw() {
		return getPlayerFile(player).getInt(PlayerPaths.LAST_YAW.getPath());
	}

	public int getPitch() {
		return getPlayerFile(player).getInt(PlayerPaths.LAST_PITCH.getPath());
	}

	public String getWorld() {
		return getPlayerFile(player).getString(PlayerPaths.LAST_WORLD.getPath());
	}
	
}
