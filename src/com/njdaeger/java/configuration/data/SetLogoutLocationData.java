package com.njdaeger.java.configuration.data;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.Location;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.ISetValues;

public class SetLogoutLocationData extends Location implements ISetValues {

	private PlayerConfigData config;

	public SetLogoutLocationData(Player player) {
		super(player);
		config = new PlayerConfigData(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setX(double value) {
		config.setValue(PlayerPaths.LOGOUT_X.getPath(), value);
	}

	@Override
	public void setY(double value) {
		config.setValue(PlayerPaths.LOGOUT_Y.getPath(), value);
	}

	@Override
	public void setZ(double value) {
		config.setValue(PlayerPaths.LOGOUT_Z.getPath(), value);
	}

	@Override
	public void setYaw(float value) {
		config.setValue(PlayerPaths.LOGOUT_YAW.getPath(), value);
	}

	@Override
	public void setPitch(float value) {
		config.setValue(PlayerPaths.LOGOUT_PITCH.getPath(), value);
	}

	@Override
	public void setWorld(String value) {
		config.setValue(PlayerPaths.LOGOUT_WORLD.getPath(), value);
	}

}
