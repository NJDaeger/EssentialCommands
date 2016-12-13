package com.njdaeger.java.configuration.data;

import com.njdaeger.java.configuration.controllers.PlayerConfig;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.ISetLastValues;

public class SetLogoutLocationData extends PlayerConfig implements ISetLastValues {

	@Override
	public void setX(double value) {
		getConfig(player).getValue().set(PlayerPaths.LOGOUT_X.getPath(), value);
		return;
	}

	@Override
	public void setY(double value) {
		getConfig(player).getValue().set(PlayerPaths.LOGOUT_Y.getPath(), value);
		return;
	}

	@Override
	public void setZ(double value) {
		getConfig(player).getValue().set(PlayerPaths.LOGOUT_Z.getPath(), value);
		return;
	}

	@Override
	public void setYaw(float value) {
		getConfig(player).getValue().set(PlayerPaths.LOGOUT_YAW.getPath(), value);
		return;
	}

	@Override
	public void setPitch(float value) {
		getConfig(player).getValue().set(PlayerPaths.LOGOUT_PITCH.getPath(), value);
		return;
	}

	@Override
	public void setWorld(String value) {
		getConfig(player).getValue().set(PlayerPaths.LOGOUT_WORLD.getPath(), value);
		return;
	}
	
}
