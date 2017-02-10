package com.njdaeger.java.configuration.data;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.Location;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.ISetValues;

public class SetLastLocationData extends Location implements ISetValues {

	private PlayerConfigData config;

	public SetLastLocationData(Player player) {
		super(player);
		config = new PlayerConfigData(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setX(double value) {
		config.setValue(PlayerPaths.LAST_X.getPath(), value);
	}

	@Override
	public void setY(double value) {
		config.setValue(PlayerPaths.LAST_Y.getPath(), value);
	}

	@Override
	public void setZ(double value) {
		config.setValue(PlayerPaths.LAST_Z.getPath(), value);
	}

	@Override
	public void setYaw(float value) {
		config.setValue(PlayerPaths.LAST_YAW.getPath(), value);
	}

	@Override
	public void setPitch(float value) {
		config.setValue(PlayerPaths.LAST_PITCH.getPath(), value);
	}

	@Override
	public void setWorld(String value) {
		config.setValue(PlayerPaths.LAST_WORLD.getPath(), value);
	}

}
