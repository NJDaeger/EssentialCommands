package com.njdaeger.java.configuration.data;

import org.bukkit.entity.Player;

import com.njdaeger.java.configuration.Location;
import com.njdaeger.java.configuration.enums.PlayerPaths;
import com.njdaeger.java.configuration.interfaces.ISetValues;
import com.njdaeger.java.configuration.interfaces.IValues;

public class LastLocationData extends Location implements IValues, ISetValues {

	private PlayerConfigData config;

	public LastLocationData(Player player) {
		super(player);
		config = new PlayerConfigData(player);
	}

	@Override
	public double getX() {
		return (double) config.getValue(PlayerPaths.LAST_X.getPath());
	}

	@Override
	public double getY() {
		return (double) config.getValue(PlayerPaths.LAST_X.getPath());
	}

	@Override
	public double getZ() {
		return (double) config.getValue(PlayerPaths.LAST_Z.getPath());
	}

	@Override
	public int getYaw() {
		return (int) config.getValue(PlayerPaths.LAST_YAW.getPath());
	}

	@Override
	public int getPitch() {
		return (int) config.getValue(PlayerPaths.LAST_PITCH.getPath());
	}

	@Override
	public String getWorld() {
		return (String) config.getValue(PlayerPaths.LAST_WORLD.getPath());
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
